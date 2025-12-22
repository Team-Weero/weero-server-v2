package team.weero.app.domain.answer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class Answer {
    private UUID id;
    private UUID concernId;
    private UUID studentId;
    private String content;
    private LocalDateTime createdAt;

    // Domain logic methods can be added here
    public boolean isWrittenBy(UUID studentId) {
        return this.studentId.equals(studentId);
    }
}
