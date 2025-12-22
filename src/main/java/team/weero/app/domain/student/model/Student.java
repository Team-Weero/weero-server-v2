package team.weero.app.domain.student.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private UUID id;
    private String name;
    private String nickname;
    private String accountId;
    private String gcn;
    private StudentRole role;
    private UUID userId;
}
