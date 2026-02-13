package team.weero.app.adapter.in.web.notice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.UUID;
import team.weero.app.application.port.in.notice.dto.response.NoticeInfo;

@Schema(description = "공지사항 응답")
public record NoticeResponse(
    @Schema(description = "공지사항 ID", example = "550e8400-e29b-41d4-a716-446655440000") UUID id,
    @Schema(description = "공지사항 제목", example = "공지사항 제목입니다") String title,
    @Schema(description = "공지사항 내용", example = "공지사항 내용입니다") String content,
    @Schema(description = "작성자 ID", example = "550e8400-e29b-41d4-a716-446655440001") UUID writerId,
    @Schema(description = "생성 일시", example = "2024-01-01T10:00:00") LocalDateTime createdAt) {

  public static NoticeResponse from(NoticeInfo notice) {
    return new NoticeResponse(
        notice.id(), notice.title(), notice.content(), notice.writerId(), notice.createdAt());
  }
}
