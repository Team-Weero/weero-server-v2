package team.weero.app.adapter.in.web.notice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import team.weero.app.application.port.in.notice.dto.response.NoticeListInfo;

@Schema(description = "공지사항 목록 응답")
public record NoticeListResponse(
    @Schema(description = "공지사항 목록", example = "[]") List<NoticeResponse> notices,
    @Schema(description = "전체 페이지 수", example = "10") int totalPages,
    @Schema(description = "현재 페이지", example = "0") int currentPage) {

  public static NoticeListResponse from(NoticeListInfo info) {
    List<NoticeResponse> noticeResponses =
        info.notices().stream().map(NoticeResponse::from).collect(Collectors.toList());

    return new NoticeListResponse(noticeResponses, info.totalPages(), info.currentPage());
  }
}
