package team.weero.app.core.concern.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConcernResponse(
    UUID id,
    String title,
    String contents,
    String studentName,
    String studentNickname,
    LocalDateTime createdAt,
    boolean isResolved,
    int answerCount
) {
}
