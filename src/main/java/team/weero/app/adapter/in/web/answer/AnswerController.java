package team.weero.app.adapter.in.web.answer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import team.weero.app.adapter.in.web.answer.dto.request.CreateAnswerRequest;
import team.weero.app.adapter.in.web.answer.dto.response.GetAnswerResponse;
import team.weero.app.application.port.in.answer.CreateAnswerUseCase;
import team.weero.app.application.port.in.answer.DeleteAnswerUseCase;
import team.weero.app.application.port.in.answer.GetAnswerUseCase;
import team.weero.app.application.port.in.answer.dto.request.CreateAnswerCommand;
import team.weero.app.global.security.CustomUserDetails;

@Tag(name = "Answers", description = "답변 관리 API")
@RestController
@RequestMapping("/api/answers")
@RequiredArgsConstructor
public class AnswerController {

  private final CreateAnswerUseCase createAnswerUseCase;
  private final GetAnswerUseCase getAnswerUseCase;
  private final DeleteAnswerUseCase deleteAnswerUseCase;

  @Operation(summary = "답변 생성", description = "게시글에 대한 답변을 생성합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "생성 성공"),
    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
    @ApiResponse(responseCode = "401", description = "인증 실패"),
    @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음")
  })
  @SecurityRequirement(name = "bearer-key")
  @PostMapping("/{postId}")
  public void create(
      @Valid @RequestBody CreateAnswerRequest request,
      @AuthenticationPrincipal CustomUserDetails userDetails,
      @PathVariable UUID postId) {
    createAnswerUseCase.execute(
        new CreateAnswerCommand(request.answer(), userDetails.getUserId(), postId));
  }

  @Operation(summary = "게시글의 답변 목록 조회", description = "특정 게시글에 달린 모든 답변을 조회합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "조회 성공"),
    @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음")
  })
  @GetMapping("/{postId}")
  public GetAnswerResponse get(@PathVariable UUID postId) {
    return GetAnswerResponse.from(getAnswerUseCase.execute(postId));
  }

  @Operation(summary = "답변 삭제", description = "본인의 답변을 삭제합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "삭제 성공"),
    @ApiResponse(responseCode = "401", description = "인증 실패"),
    @ApiResponse(responseCode = "404", description = "답변을 찾을 수 없음")
  })
  @SecurityRequirement(name = "bearer-key")
  @DeleteMapping("/{answerId}")
  public void delete(
      @AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable UUID answerId) {
    deleteAnswerUseCase.execute(userDetails.getUserId(), answerId);
  }
}
