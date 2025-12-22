package team.weero.app.presentation.answer;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import team.weero.app.application.answer.dto.request.CreateAnswerRequest;
import team.weero.app.application.answer.dto.response.AnswerResponse;
import team.weero.app.application.answer.usecase.CreateAnswerUseCase;
import team.weero.app.application.answer.usecase.DeleteAnswerUseCase;
import team.weero.app.application.answer.usecase.GetAnswersByConcernUseCase;
import team.weero.app.application.answer.usecase.GetMyAnswersUseCase;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    private final CreateAnswerUseCase createAnswerUseCase;
    private final GetAnswersByConcernUseCase getAnswersByConcernUseCase;
    private final GetMyAnswersUseCase getMyAnswersUseCase;
    private final DeleteAnswerUseCase deleteAnswerUseCase;

    public AnswerController(CreateAnswerUseCase createAnswerUseCase,
                           GetAnswersByConcernUseCase getAnswersByConcernUseCase,
                           GetMyAnswersUseCase getMyAnswersUseCase,
                           DeleteAnswerUseCase deleteAnswerUseCase) {
        this.createAnswerUseCase = createAnswerUseCase;
        this.getAnswersByConcernUseCase = getAnswersByConcernUseCase;
        this.getMyAnswersUseCase = getMyAnswersUseCase;
        this.deleteAnswerUseCase = deleteAnswerUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAnswer(@RequestBody @Valid CreateAnswerRequest request,
                            Authentication authentication) {
        createAnswerUseCase.execute(request, authentication.getName());
    }

    @GetMapping("/concern/{concernId}")
    public List<AnswerResponse> getAnswersByConcernId(@PathVariable UUID concernId) {
        return getAnswersByConcernUseCase.execute(concernId);
    }

    @GetMapping("/my")
    public List<AnswerResponse> getMyAnswers(Authentication authentication) {
        return getMyAnswersUseCase.execute(authentication.getName());
    }

    @DeleteMapping("/{answerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnswer(@PathVariable UUID answerId, Authentication authentication) {
        deleteAnswerUseCase.execute(answerId, authentication.getName());
    }
}
