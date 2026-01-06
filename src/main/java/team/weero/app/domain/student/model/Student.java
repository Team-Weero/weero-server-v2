package team.weero.app.domain.student.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
