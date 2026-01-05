package team.weero.app.application.notice.dto.response;

import java.util.UUID;

public record NoticeResponse(
    UUID id,
    String title,
    String contents,
    String teacherName
) {
}
