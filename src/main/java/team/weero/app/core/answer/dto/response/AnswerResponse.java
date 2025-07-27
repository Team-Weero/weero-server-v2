package team.weero.app.core.answer.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record AnswerResponse(
    UUID id,
    UUID concernId,
    String content,
    String studentName,
    String studentNickname,
    LocalDateTime createdAt
) {
}
