package team.weero.app.presentation.answer;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import team.weero.app.core.answer.dto.request.CreateAnswerRequest;
import team.weero.app.core.answer.dto.response.AnswerResponse;
import team.weero.app.core.answer.usecase.CreateAnswerUseCase;
import team.weero.app.core.answer.service.AnswerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/answers")
public class AnswerWebAdapter {

    private final CreateAnswerUseCase createAnswerUseCase;
    private final AnswerService answerService;

    public AnswerWebAdapter(CreateAnswerUseCase createAnswerUseCase, AnswerService answerService) {
        this.createAnswerUseCase = createAnswerUseCase;
        this.answerService = answerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAnswer(@RequestBody @Valid CreateAnswerRequest request,
                            Authentication authentication) {
        createAnswerUseCase.execute(request, authentication.getName());
    }

    @GetMapping("/concern/{concernId}")
    public List<AnswerResponse> getAnswersByConcernId(@PathVariable UUID concernId) {
        return answerService.getAnswersByConcernId(concernId);
    }

    @GetMapping("/my")
    public List<AnswerResponse> getMyAnswers(Authentication authentication) {
        return answerService.getMyAnswers(authentication.getName());
    }

    @DeleteMapping("/{answerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnswer(@PathVariable UUID answerId, Authentication authentication) {
        answerService.deleteAnswer(answerId, authentication.getName());
    }
}
