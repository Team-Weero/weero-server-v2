package team.weero.app.adapter.in.web.counsel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import team.weero.app.adapter.in.web.counsel.dto.request.CreateCounselRequestRequest;
import team.weero.app.adapter.in.web.counsel.dto.response.CounselRequestListResponse;
import team.weero.app.adapter.in.web.counsel.dto.response.CounselRequestResponse;
import team.weero.app.application.port.in.counsel.CancelCounselRequestUseCase;
import team.weero.app.application.port.in.counsel.CreateCounselRequestUseCase;
import team.weero.app.application.port.in.counsel.GetMyCounselRequestsUseCase;
import team.weero.app.application.port.in.counsel.dto.request.CreateCounselRequestCommand;
import team.weero.app.application.port.in.counsel.dto.response.CounselRequestListInfo;
import team.weero.app.global.security.CustomUserDetails;

@Tag(name = "Student Counsel Requests", description = "학생 상담 요청 관리 API")
@RestController
@RequestMapping("/api/counsel-requests/student")
@RequiredArgsConstructor
public class StudentCounselRequestController {

  private final CreateCounselRequestUseCase createCounselRequestUseCase;
  private final CancelCounselRequestUseCase cancelCounselRequestUseCase;
  private final GetMyCounselRequestsUseCase getMyCounselRequestsUseCase;

  @Operation(summary = "상담 요청 생성", description = "학생이 교사에게 상담 요청을 생성합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "생성 성공"),
    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
    @ApiResponse(responseCode = "401", description = "인증 실패")
  })
  @SecurityRequirement(name = "bearer-key")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CounselRequestResponse create(
      @Valid @RequestBody CreateCounselRequestRequest request,
      @AuthenticationPrincipal CustomUserDetails userDetails) {
    return CounselRequestResponse.from(
        createCounselRequestUseCase.execute(
            new CreateCounselRequestCommand(
                request.gender(),
                request.hasCounselingExperience(),
                request.category(),
                request.teacherId(),
                userDetails.getUserId())));
  }

  @Operation(summary = "상담 요청 취소", description = "학생이 본인의 상담 요청을 취소합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "204", description = "취소 성공"),
    @ApiResponse(responseCode = "401", description = "인증 실패"),
    @ApiResponse(responseCode = "404", description = "상담 요청을 찾을 수 없음")
  })
  @SecurityRequirement(name = "bearer-key")
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void cancel(
      @PathVariable UUID id, @AuthenticationPrincipal CustomUserDetails userDetails) {
    cancelCounselRequestUseCase.execute(id, userDetails.getUserId());
  }

  @Operation(summary = "내 상담 요청 목록 조회", description = "현재 로그인한 학생의 모든 상담 요청을 조회합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "조회 성공"),
    @ApiResponse(responseCode = "401", description = "인증 실패")
  })
  @SecurityRequirement(name = "bearer-key")
  @GetMapping("/my")
  @ResponseStatus(HttpStatus.OK)
  public CounselRequestListResponse getMy(@AuthenticationPrincipal CustomUserDetails userDetails) {
    CounselRequestListInfo listInfo = getMyCounselRequestsUseCase.execute(userDetails.getUserId());
    List<CounselRequestResponse> responses = CounselRequestResponse.fromList(listInfo);
    return new CounselRequestListResponse(responses);
  }
}
