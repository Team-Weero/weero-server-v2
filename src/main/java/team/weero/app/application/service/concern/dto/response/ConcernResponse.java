package team.weero.app.application.concern.dto.response;

import team.weero.app.domain.concern.model.Concern;
import team.weero.app.domain.student.model.Student;

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
    public static ConcernResponse from(Concern concern, Student student, int answerCount) {
        return new ConcernResponse(
            concern.getId(),
            concern.getTitle(),
            concern.getContents(),
            student != null ? student.getName() : null,
            student != null ? student.getNickname() : null,
            concern.getCreatedAt(),
            concern.isResolved(),
            answerCount
        );
    }
}
