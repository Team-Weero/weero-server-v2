package team.weero.app.adapter.in.web.counsel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import team.weero.app.adapter.in.web.counsel.dto.response.CounselRequestListResponse;
import team.weero.app.adapter.in.web.counsel.dto.response.CounselRequestResponse;
import team.weero.app.application.exception.teacher.TeacherNotFoundException;
import team.weero.app.application.port.in.counsel.ApproveCounselRequestUseCase;
import team.weero.app.application.port.in.counsel.GetAllCounselRequestsUseCase;
import team.weero.app.application.port.in.counsel.GetCounselRequestUseCase;
import team.weero.app.application.port.in.counsel.RejectCounselRequestUseCase;
import team.weero.app.application.port.in.counsel.dto.response.CounselRequestInfo;
import team.weero.app.application.port.in.teacher.dto.response.TeacherInfo;
import team.weero.app.application.port.out.teacher.LoadTeacherPort;
import team.weero.app.global.security.principal.CustomUserDetails;

@Tag(name = "Teacher Counsel Requests", description = "교사 상담 요청 관리 API")
@RestController
@RequestMapping("/api/counsel-requests/teacher")
@RequiredArgsConstructor
public class TeacherCounselRequestController {

  private final GetAllCounselRequestsUseCase getAllCounselRequestsUseCase;
  private final GetCounselRequestUseCase getCounselRequestUseCase;
  private final ApproveCounselRequestUseCase approveCounselRequestUseCase;
  private final RejectCounselRequestUseCase rejectCounselRequestUseCase;
  private final LoadTeacherPort loadTeacherPort;

  @Operation(summary = "교사의 모든 상담 요청 조회", description = "현재 로그인한 교사에게 할당된 모든 상담 요청을 조회합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "조회 성공"),
    @ApiResponse(responseCode = "401", description = "인증 실패"),
    @ApiResponse(responseCode = "404", description = "교사 정보를 찾을 수 없음")
  })
  @SecurityRequirement(name = "bearer-key")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public CounselRequestListResponse getAll(@AuthenticationPrincipal CustomUserDetails userDetails) {
    return CounselRequestListResponse.from(
        getAllCounselRequestsUseCase.execute(userDetails.getUserId()));
  }

  @Operation(summary = "특정 상담 요청 상세 조회", description = "ID로 특정 상담 요청의 상세 정보를 조회합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "조회 성공"),
    @ApiResponse(responseCode = "401", description = "인증 실패"),
    @ApiResponse(responseCode = "404", description = "상담 요청 또는 교사 정보를 찾을 수 없음")
  })
  @SecurityRequirement(name = "bearer-key")
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CounselRequestResponse get(
      @PathVariable UUID id, @AuthenticationPrincipal CustomUserDetails userDetails) {

    TeacherInfo teacherInfo =
        loadTeacherPort
            .loadByUserId(userDetails.getUserId())
            .orElseThrow(TeacherNotFoundException::new);

    CounselRequestInfo info = getCounselRequestUseCase.execute(id, teacherInfo.id());

    return CounselRequestResponse.from(info);
  }

  @Operation(summary = "상담 요청 승인", description = "학생의 상담 요청을 승인합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "승인 성공"),
    @ApiResponse(responseCode = "401", description = "인증 실패"),
    @ApiResponse(responseCode = "404", description = "상담 요청을 찾을 수 없음")
  })
  @SecurityRequirement(name = "bearer-key")
  @PostMapping("/{id}/approve")
  @ResponseStatus(HttpStatus.OK)
  public CounselRequestResponse approve(
      @PathVariable UUID id, @AuthenticationPrincipal CustomUserDetails userDetails) {
    CounselRequestInfo info = approveCounselRequestUseCase.execute(id, userDetails.getUserId());

    return CounselRequestResponse.from(info);
  }

  @Operation(summary = "상담 요청 거절", description = "학생의 상담 요청을 거절합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "204", description = "거절 성공"),
    @ApiResponse(responseCode = "401", description = "인증 실패"),
    @ApiResponse(responseCode = "404", description = "상담 요청을 찾을 수 없음")
  })
  @SecurityRequirement(name = "bearer-key")
  @PostMapping("/{id}/reject")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void reject(
      @PathVariable UUID id, @AuthenticationPrincipal CustomUserDetails userDetails) {
    rejectCounselRequestUseCase.execute(id, userDetails.getUserId());
  }
}
