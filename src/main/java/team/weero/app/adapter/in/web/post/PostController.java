package team.weero.app.adapter.in.web.post;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import team.weero.app.adapter.in.web.post.dto.request.CreatePostRequest;
import team.weero.app.adapter.in.web.post.dto.request.UpdatePostRequest;
import team.weero.app.adapter.in.web.post.dto.response.GetAllPostResponse;
import team.weero.app.adapter.in.web.post.dto.response.GetPostResponse;
import team.weero.app.application.port.in.heart.ToggleHeartUseCase;
import team.weero.app.application.port.in.post.*;
import team.weero.app.application.port.in.post.dto.request.CreatePostCommand;
import team.weero.app.application.port.in.post.dto.request.UpdatePostCommand;
import team.weero.app.application.port.in.post.dto.response.GetAllPostInfo;
import team.weero.app.application.port.in.post.dto.response.GetPostInfo;
import team.weero.app.global.security.principal.CustomUserDetails;

@Tag(name = "Posts", description = "게시글 관리 API")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

  private final CreatePostUseCase createPostUseCase;
  private final GetAllPostUseCase getAllPostUseCase;
  private final GetPostUseCase getPostUseCase;
  private final GetMyPostsUseCase getMyPostsUseCase;
  private final DeletePostUseCase deletePostUseCase;
  private final UpdatePostUseCase updatePostUseCase;
  private final ToggleHeartUseCase toggleHeartUseCase;

  @Operation(summary = "게시글 생성", description = "새로운 게시글을 생성합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "생성 성공"),
    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
    @ApiResponse(responseCode = "401", description = "인증 실패")
  })
  @SecurityRequirement(name = "bearer-key")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public void create(
      @Valid @RequestBody CreatePostRequest request,
      @AuthenticationPrincipal CustomUserDetails userDetails) {

    CreatePostCommand command =
        new CreatePostCommand(request.title(), request.content(), userDetails.getUserId());

    createPostUseCase.execute(command);
  }

  @Operation(summary = "모든 게시글 조회", description = "모든 게시글 목록을 조회합니다.")
  @ApiResponses({@ApiResponse(responseCode = "200", description = "조회 성공")})
  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public GetAllPostResponse getAll(@AuthenticationPrincipal CustomUserDetails userDetails) {
    GetAllPostInfo postListInfo = getAllPostUseCase.execute(userDetails.getUserId());

    return GetAllPostResponse.from(postListInfo);
  }

  @Operation(summary = "내 게시글 목록 조회", description = "현재 로그인한 사용자의 게시글을 조회합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "조회 성공"),
    @ApiResponse(responseCode = "401", description = "인증 실패")
  })
  @SecurityRequirement(name = "bearer-key")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/my")
  public GetAllPostResponse getMyPosts(@AuthenticationPrincipal CustomUserDetails userDetails) {
    GetAllPostInfo postListInfo = getMyPostsUseCase.execute(userDetails.getUserId());

    return GetAllPostResponse.from(postListInfo);
  }

  @Operation(summary = "게시글 상세 조회", description = "ID로 특정 게시글의 상세 정보를 조회합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "조회 성공"),
    @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음")
  })
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{postId}")
  public GetPostResponse get(
      @PathVariable UUID postId, @AuthenticationPrincipal CustomUserDetails userDetails) {
    GetPostInfo postInfo = getPostUseCase.execute(postId, userDetails.getUserId());

    return GetPostResponse.from(postInfo);
  }

  @Operation(summary = "게시글 삭제", description = "본인의 게시글을 삭제합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "204", description = "삭제 성공"),
    @ApiResponse(responseCode = "401", description = "인증 실패"),
    @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음")
  })
  @SecurityRequirement(name = "bearer-key")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{postId}")
  public void delete(
      @PathVariable UUID postId, @AuthenticationPrincipal CustomUserDetails userDetails) {
    deletePostUseCase.execute(postId, userDetails.getUserId());
  }

  @Operation(summary = "게시글 수정", description = "본인의 게시글을 수정합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "수정 성공"),
    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
    @ApiResponse(responseCode = "401", description = "인증 실패"),
    @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음")
  })
  @SecurityRequirement(name = "bearer-key")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PatchMapping("/{postId}")
  public void update(
      @PathVariable UUID postId,
      @AuthenticationPrincipal CustomUserDetails userDetails,
      @Valid @RequestBody UpdatePostRequest request) {
    UpdatePostCommand command =
        new UpdatePostCommand(postId, userDetails.getUserId(), request.title(), request.content());

    updatePostUseCase.execute(command);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PostMapping("/{postId}/heart")
  public void toggleHeart(
      @PathVariable UUID postId, @AuthenticationPrincipal CustomUserDetails userDetails) {
    toggleHeartUseCase.execute(postId, userDetails.getUserId());
  }
}
