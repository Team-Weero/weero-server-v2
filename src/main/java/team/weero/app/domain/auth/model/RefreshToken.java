package team.weero.app.domain.auth.model;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RefreshToken {
  private String accountId;
  private String refreshToken;
  private Long ttl;

  public static RefreshToken create(String accountId, String refreshToken, Long ttl) {
    return RefreshToken.builder().accountId(accountId).refreshToken(refreshToken).ttl(ttl).build();
  }
}
