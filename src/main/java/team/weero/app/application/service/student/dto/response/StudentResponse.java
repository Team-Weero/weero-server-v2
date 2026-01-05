package team.weero.app.application.service.student.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team.weero.app.domain.student.model.Student;
import team.weero.app.domain.student.model.StudentRole;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class StudentResponse {
    private UUID id;
    private String name;
    private String nickname;
    private String accountId;
    private String gcn;
    private StudentRole role;
    private UUID userId;

    public static StudentResponse from(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .nickname(student.getNickname())
                .accountId(student.getAccountId())
                .gcn(student.getGcn())
                .role(student.getRole())
                .userId(student.getUserId())
                .build();
    }
}
