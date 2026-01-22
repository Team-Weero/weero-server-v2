package team.weero.app.domain.student.domain;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import team.weero.app.domain.student.type.StudentRole;

@Getter
@Builder
public class Student {
  private UUID id;
  private String accountId;
  private String name;
  private String nickname;
  private int grade;
  private int classRoom;
  private int number;
  private StudentRole role;
  private UUID userId;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
