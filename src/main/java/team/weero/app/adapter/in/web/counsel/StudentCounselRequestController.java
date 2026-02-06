package team.weero.app.adapter.in.web.counsel;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import team.weero.app.adapter.in.web.counsel.dto.request.CreateCounselRequestRequest;
import team.weero.app.adapter.in.web.counsel.dto.request.UpdateCounselRequestRequest;
import team.weero.app.adapter.in.web.counsel.dto.response.CounselRequestListResponse;
import team.weero.app.adapter.in.web.counsel.dto.response.CounselRequestResponse;
import team.weero.app.application.port.in.counsel.CancelCounselRequestUseCase;
import team.weero.app.application.port.in.counsel.CreateCounselRequestUseCase;
import team.weero.app.application.port.in.counsel.GetMyCounselRequestsUseCase;
import team.weero.app.application.port.in.counsel.UpdateCounselRequestUseCase;
import team.weero.app.global.security.CustomUserDetails;

@RestController
@RequestMapping("/api/counsel-requests/student")
@RequiredArgsConstructor
public class StudentCounselRequestController {

  private final CreateCounselRequestUseCase createCounselRequestUseCase;
  private final UpdateCounselRequestUseCase updateCounselRequestUseCase;
  private final CancelCounselRequestUseCase cancelCounselRequestUseCase;
  private final GetMyCounselRequestsUseCase getMyCounselRequestsUseCase;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CounselRequestResponse create(
      @Valid @RequestBody CreateCounselRequestRequest request,
      @AuthenticationPrincipal CustomUserDetails userDetails) {
    return createCounselRequestUseCase.execute(request, userDetails.getUserId());
  }

  @PatchMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CounselRequestResponse update(
      @PathVariable UUID id,
      @Valid @RequestBody UpdateCounselRequestRequest request,
      @AuthenticationPrincipal CustomUserDetails userDetails) {
    return updateCounselRequestUseCase.execute(id, request, userDetails.getUserId());
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void cancel(
      @PathVariable UUID id, @AuthenticationPrincipal CustomUserDetails userDetails) {
    cancelCounselRequestUseCase.execute(id, userDetails.getUserId());
  }

  @GetMapping("/my")
  @ResponseStatus(HttpStatus.OK)
  public CounselRequestListResponse getMy(@AuthenticationPrincipal CustomUserDetails userDetails) {
    return getMyCounselRequestsUseCase.execute(userDetails.getUserId());
  }
}
