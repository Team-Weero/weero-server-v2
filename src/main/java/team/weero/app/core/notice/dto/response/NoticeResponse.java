package team.weero.app.core.notice.dto.response;

import java.util.UUID;

public record NoticeResponse(
    UUID id,
    String title,
    String contents,
    String teacherName
) {
}
