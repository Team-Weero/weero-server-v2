package team.weero.app.adapter.in.web.concern;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import team.weero.app.application.port.in.concern.*;
import team.weero.app.application.service.concern.dto.request.CreateConcernRequest;
import team.weero.app.application.service.concern.dto.response.ConcernResponse;

@RestController
@RequestMapping("/concerns")
public class ConcernController {

  private final CreateConcernUseCase createConcernUseCase;
  private final GetAllConcernsUseCase getAllConcernsUseCase;
  private final GetConcernByIdUseCase getConcernByIdUseCase;
  private final GetMyConcernsUseCase getMyConcernsUseCase;
  private final GetUnresolvedConcernsUseCase getUnresolvedConcernsUseCase;
  private final ResolveConcernUseCase resolveConcernUseCase;
  private final DeleteConcernUseCase deleteConcernUseCase;

  public ConcernController(
      CreateConcernUseCase createConcernUseCase,
      GetAllConcernsUseCase getAllConcernsUseCase,
      GetConcernByIdUseCase getConcernByIdUseCase,
      GetMyConcernsUseCase getMyConcernsUseCase,
      GetUnresolvedConcernsUseCase getUnresolvedConcernsUseCase,
      ResolveConcernUseCase resolveConcernUseCase,
      DeleteConcernUseCase deleteConcernUseCase) {
    this.createConcernUseCase = createConcernUseCase;
    this.getAllConcernsUseCase = getAllConcernsUseCase;
    this.getConcernByIdUseCase = getConcernByIdUseCase;
    this.getMyConcernsUseCase = getMyConcernsUseCase;
    this.getUnresolvedConcernsUseCase = getUnresolvedConcernsUseCase;
    this.resolveConcernUseCase = resolveConcernUseCase;
    this.deleteConcernUseCase = deleteConcernUseCase;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createConcern(
      @RequestBody @Valid CreateConcernRequest request, Authentication authentication) {
    createConcernUseCase.execute(request, authentication.getName());
  }

  @GetMapping("/{id}")
  public ConcernResponse getConcernById(@PathVariable UUID id) {
    return getConcernByIdUseCase.execute(id);
  }

  @GetMapping
  public List<ConcernResponse> getAllConcerns() {
    return getAllConcernsUseCase.execute();
  }

  @GetMapping("/my")
  public List<ConcernResponse> getMyConcerns(Authentication authentication) {
    return getMyConcernsUseCase.execute(authentication.getName());
  }

  @GetMapping("/unresolved")
  public List<ConcernResponse> getUnresolvedConcerns() {
    return getUnresolvedConcernsUseCase.execute();
  }

  @PutMapping("/{id}/resolve")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void resolveConcern(@PathVariable UUID id) {
    resolveConcernUseCase.execute(id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteConcern(@PathVariable UUID id, Authentication authentication) {
    deleteConcernUseCase.execute(id, authentication.getName());
  }
}
