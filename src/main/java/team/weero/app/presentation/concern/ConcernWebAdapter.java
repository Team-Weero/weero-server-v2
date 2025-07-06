package team.weero.app.presentation.concern;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import team.weero.app.core.concern.dto.request.CreateConcernRequest;
import team.weero.app.core.concern.dto.response.ConcernResponse;
import team.weero.app.core.concern.usecase.CreateConcernUseCase;
import team.weero.app.core.concern.usecase.GetAllConcernsUseCase;
import team.weero.app.core.concern.service.ConcernService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/concerns")
public class ConcernWebAdapter {

    private final CreateConcernUseCase createConcernUseCase;
    private final GetAllConcernsUseCase getAllConcernsUseCase;
    private final ConcernService concernService;

    public ConcernWebAdapter(CreateConcernUseCase createConcernUseCase, 
                           GetAllConcernsUseCase getAllConcernsUseCase, 
                           ConcernService concernService) {
        this.createConcernUseCase = createConcernUseCase;
        this.getAllConcernsUseCase = getAllConcernsUseCase;
        this.concernService = concernService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createConcern(@RequestBody @Valid CreateConcernRequest request,
                             Authentication authentication) {
        createConcernUseCase.execute(request, authentication.getName());
    }

    @GetMapping("/{id}")
    public ConcernResponse getConcernById(@PathVariable UUID id) {
        return concernService.getConcernById(id);
    }

    @GetMapping
    public List<ConcernResponse> getAllConcerns() {
        return getAllConcernsUseCase.execute();
    }

    @GetMapping("/my")
    public List<ConcernResponse> getMyConcerns(Authentication authentication) {
        return concernService.getMyConcerns(authentication.getName());
    }

    @GetMapping("/unresolved")
    public List<ConcernResponse> getUnresolvedConcerns() {
        return concernService.getUnresolvedConcerns();
    }

    @PutMapping("/{id}/resolve")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void resolveConcern(@PathVariable UUID id) {
        concernService.resolveConcern(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConcern(@PathVariable UUID id, Authentication authentication) {
        concernService.deleteConcern(id, authentication.getName());
    }
}
