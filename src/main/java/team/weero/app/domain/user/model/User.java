package team.weero.app.domain.user.model;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import team.weero.app.domain.auth.type.Authority;

@Getter
@Builder
public class User {
  private UUID id;
  private String email;
  private String password;
  private Authority authority;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;

  public boolean isDeleted() {
    return deletedAt != null;
  }
}
