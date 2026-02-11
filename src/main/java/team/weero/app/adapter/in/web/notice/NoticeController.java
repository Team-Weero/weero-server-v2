package team.weero.app.adapter.in.web.notice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.weero.app.adapter.in.web.notice.dto.request.CreateNoticeRequest;
import team.weero.app.adapter.in.web.notice.dto.request.UpdateNoticeRequest;
import team.weero.app.adapter.in.web.notice.dto.response.NoticeListResponse;
import team.weero.app.adapter.in.web.notice.dto.response.NoticeResponse;
import team.weero.app.application.port.in.notice.CreateNoticeUseCase;
import team.weero.app.application.port.in.notice.DeleteNoticeUseCase;
import team.weero.app.application.port.in.notice.GetNoticeListUseCase;
import team.weero.app.application.port.in.notice.GetNoticeUseCase;
import team.weero.app.application.port.in.notice.UpdateNoticeUseCase;
import team.weero.app.application.port.in.notice.dto.request.CreateNoticeCommand;
import team.weero.app.application.port.in.notice.dto.request.UpdateNoticeCommand;
import team.weero.app.application.port.in.notice.dto.response.NoticeInfo;

@Tag(name = "Notices", description = "공지사항 관리 API")
@RestController
@RequestMapping("/api/notices")
@RequiredArgsConstructor
@Validated
public class NoticeController {

  private final CreateNoticeUseCase createNoticeUseCase;
  private final UpdateNoticeUseCase updateNoticeUseCase;
  private final DeleteNoticeUseCase deleteNoticeUseCase;
  private final GetNoticeUseCase getNoticeUseCase;
  private final GetNoticeListUseCase getNoticeListUseCase;

  @Operation(summary = "공지사항 생성", description = "새로운 공지사항을 생성합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "생성 성공"),
    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
    @ApiResponse(responseCode = "401", description = "인증 실패")
  })
  @SecurityRequirement(name = "bearer-key")
  @PostMapping
  public NoticeResponse createNotice(@RequestBody @Valid CreateNoticeRequest request) {

    CreateNoticeCommand command = new CreateNoticeCommand(request.title(), request.content());

    NoticeInfo noticeInfo = createNoticeUseCase.execute(command);
    return NoticeResponse.from(noticeInfo);
  }

  @Operation(summary = "공지사항 수정", description = "기존 공지사항을 수정합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "수정 성공"),
    @ApiResponse(responseCode = "400", description = "잘못된 요청"),
    @ApiResponse(responseCode = "401", description = "인증 실패"),
    @ApiResponse(responseCode = "404", description = "공지사항을 찾을 수 없음")
  })
  @SecurityRequirement(name = "bearer-key")
  @PutMapping("/{id}")
  public NoticeResponse updateNotice(
      @PathVariable UUID id, @RequestBody @Valid UpdateNoticeRequest request) {
    UpdateNoticeCommand command = new UpdateNoticeCommand(id, request.title(), request.content());

    NoticeInfo noticeInfo = updateNoticeUseCase.execute(command);
    return NoticeResponse.from(noticeInfo);
  }

  @Operation(summary = "공지사항 삭제", description = "공지사항을 삭제합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "204", description = "삭제 성공"),
    @ApiResponse(responseCode = "401", description = "인증 실패"),
    @ApiResponse(responseCode = "404", description = "공지사항을 찾을 수 없음")
  })
  @SecurityRequirement(name = "bearer-key")
  @DeleteMapping("/{id}")
  public void deleteNotice(@PathVariable UUID id) {
    deleteNoticeUseCase.execute(id);
  }

  @Operation(summary = "공지사항 상세 조회", description = "ID로 특정 공지사항의 상세 정보를 조회합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "조회 성공"),
    @ApiResponse(responseCode = "404", description = "공지사항을 찾을 수 없음")
  })
  @GetMapping("/{id}")
  public NoticeResponse getNotice(@PathVariable UUID id) {
    return NoticeResponse.from(getNoticeUseCase.execute(id));
  }

  @Operation(summary = "공지사항 목록 조회", description = "페이지네이션을 적용한 공지사항 목록을 조회합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "조회 성공"),
    @ApiResponse(responseCode = "400", description = "잘못된 페이지 파라미터")
  })
  @GetMapping
  public NoticeListResponse getNoticeList(
      @RequestParam(defaultValue = "0") @Min(0) int page,
      @RequestParam(defaultValue = "10") @Positive @Max(100) int size) {
    return NoticeListResponse.from(getNoticeListUseCase.execute(page, size));
  }
}
