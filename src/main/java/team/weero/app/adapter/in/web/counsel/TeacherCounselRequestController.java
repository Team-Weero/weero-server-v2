package team.weero.app.adapter.in.web.counsel;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import team.weero.app.adapter.in.web.counsel.dto.response.CounselRequestListResponse;
import team.weero.app.adapter.in.web.counsel.dto.response.CounselRequestResponse;
import team.weero.app.adapter.in.web.teacher.dto.response.TeacherInfo;
import team.weero.app.application.exception.teacher.TeacherNotFoundException;
import team.weero.app.application.port.in.counsel.ApproveCounselRequestUseCase;
import team.weero.app.application.port.in.counsel.GetAllCounselRequestsUseCase;
import team.weero.app.application.port.in.counsel.GetCounselRequestUseCase;
import team.weero.app.application.port.in.counsel.RejectCounselRequestUseCase;
import team.weero.app.application.port.out.teacher.LoadTeacherPort;
import team.weero.app.global.security.CustomUserDetails;

@RestController
@RequestMapping("/api/counsel-requests/teacher")
@RequiredArgsConstructor
public class TeacherCounselRequestController {

  private final GetAllCounselRequestsUseCase getAllCounselRequestsUseCase;
  private final GetCounselRequestUseCase getCounselRequestUseCase;
  private final ApproveCounselRequestUseCase approveCounselRequestUseCase;
  private final RejectCounselRequestUseCase rejectCounselRequestUseCase;
  private final LoadTeacherPort loadTeacherPort;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public CounselRequestListResponse getAll(@AuthenticationPrincipal CustomUserDetails userDetails) {
    TeacherInfo teacherInfo =
        loadTeacherPort
            .loadByUserId(userDetails.getUserId())
            .orElseThrow(TeacherNotFoundException::new);
    return getAllCounselRequestsUseCase.execute(teacherInfo.id());
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CounselRequestResponse get(
      @PathVariable UUID id, @AuthenticationPrincipal CustomUserDetails userDetails) {
    TeacherInfo teacherInfo =
        loadTeacherPort
            .loadByUserId(userDetails.getUserId())
            .orElseThrow(TeacherNotFoundException::new);
    return getCounselRequestUseCase.execute(id, teacherInfo.id());
  }

  @PostMapping("/{id}/approve")
  @ResponseStatus(HttpStatus.OK)
  public CounselRequestResponse approve(
      @PathVariable UUID id, @AuthenticationPrincipal CustomUserDetails userDetails) {
    return approveCounselRequestUseCase.execute(id, userDetails.getUserId());
  }

  @PostMapping("/{id}/reject")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void reject(
      @PathVariable UUID id, @AuthenticationPrincipal CustomUserDetails userDetails) {
    rejectCounselRequestUseCase.execute(id, userDetails.getUserId());
  }
}
