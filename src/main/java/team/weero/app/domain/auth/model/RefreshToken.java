package team.weero.app.domain.auth.model;

import lombok.*;


 * RefreshToken Domain Model
 * Pure domain object representing a refresh token
 */
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RefreshToken {
    private String accountId;
    private String refreshToken;
    private Long ttl;

    
     * Create a new RefreshToken
     */
    public static RefreshToken create(String accountId, String refreshToken, Long ttl) {
        return RefreshToken.builder()
                .accountId(accountId)
                .refreshToken(refreshToken)
                .ttl(ttl)
                .build();
    }
}
