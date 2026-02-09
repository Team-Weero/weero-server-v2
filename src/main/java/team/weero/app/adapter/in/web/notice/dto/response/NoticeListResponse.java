package team.weero.app.adapter.in.web.notice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "공지사항 목록 응답")
public record NoticeListResponse(
    @Schema(description = "공지사항 목록", example = "[]") List<NoticeResponse> notices,
    @Schema(description = "전체 페이지 수", example = "10") int totalPages,
    @Schema(description = "현재 페이지", example = "0") int currentPage) {}
