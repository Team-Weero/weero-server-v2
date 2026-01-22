package team.weero.app.adapter.in.web.answer;

import java.util.UUID;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import team.weero.app.adapter.in.web.answer.dto.request.CreateAnswerRequest;
import team.weero.app.adapter.in.web.answer.dto.response.GetAnswerResponse;
import team.weero.app.application.port.in.answer.CreateAnswerUseCase;
import team.weero.app.application.port.in.answer.DeleteAnswerUseCase;
import team.weero.app.application.port.in.answer.GetAnswerUseCase;
import team.weero.app.global.security.CustomUserDetails;

@RestController
@RequestMapping("/api/answers")
@RequiredArgsConstructor
public class AnswerController {

  private final CreateAnswerUseCase createAnswerUseCase;
  private final GetAnswerUseCase getAnswerUseCase;
  private final DeleteAnswerUseCase deleteAnswerUseCase;

  @PostMapping("/{postId}")
  public void create(
      @Valid @RequestBody CreateAnswerRequest request,
      @AuthenticationPrincipal CustomUserDetails userDetails,
      @PathVariable UUID postId) {
    createAnswerUseCase.execute(request, userDetails.getUserId(), postId);
  }

  @GetMapping("/{postId}")
  public GetAnswerResponse get(@PathVariable UUID postId) {
    return getAnswerUseCase.execute(postId);
  }

  @DeleteMapping("/{answerId}")
  public void delete(
      @AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable UUID answerId) {
    deleteAnswerUseCase.execute(userDetails.getUserId(), answerId);
  }
}
