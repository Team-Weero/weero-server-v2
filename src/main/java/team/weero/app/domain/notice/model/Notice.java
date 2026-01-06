package team.weero.app.domain.notice.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Notice {
  private UUID id;
  private UUID userId;
  private String title;
  private String contents;

  public void update(String title, String contents) {
    this.title = title;
    this.contents = contents;
  }

  public boolean isOwnedBy(UUID userId) {
    return this.userId.equals(userId);
  }
}
