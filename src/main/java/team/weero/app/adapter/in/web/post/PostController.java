package team.weero.app.adapter.in.web.post;

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
import team.weero.app.application.port.in.post.*;
import team.weero.app.global.security.CustomUserDetails;

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

  @PostMapping("/")
  public void create(
      @Valid @RequestBody CreatePostRequest request,
      @AuthenticationPrincipal CustomUserDetails userDetails) {
    createPostUseCase.execute(request, userDetails.getUserId());
  }

  @GetMapping("/")
  @ResponseStatus(HttpStatus.OK)
  public GetAllPostResponse getAll() {
    return getAllPostUseCase.execute();
  }

  @GetMapping("/my")
  @ResponseStatus(HttpStatus.OK)
  public GetAllPostResponse getMyPosts(@AuthenticationPrincipal CustomUserDetails userDetails) {
    return getMyPostsUseCase.execute(userDetails.getUserId());
  }

  @GetMapping("/{postId}")
  @ResponseStatus(HttpStatus.OK)
  public GetPostResponse get(@PathVariable UUID postId) {
    return getPostUseCase.execute(postId);
  }

  @DeleteMapping("/{postId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(
      @PathVariable UUID postId, @AuthenticationPrincipal CustomUserDetails userDetails) {
    deletePostUseCase.execute(postId, userDetails.getUserId());
  }

  @PatchMapping("/{postId}")
  @ResponseStatus(HttpStatus.OK)
  public void update(@PathVariable UUID postId, @Valid @RequestBody UpdatePostRequest request) {
    updatePostUseCase.execute(postId, request);
  }
}
