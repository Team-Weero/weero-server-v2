package team.weero.app.core.auth.spi;

import team.weero.app.core.auth.dto.response.TokenResponse;
import team.weero.app.persistence.student.type.StudentRole;
import team.weero.app.persistence.user.type.UserRole;

public interface TokenPort {

    TokenResponse generateTokenResponse(String subject, UserRole userRole, StudentRole studentRole);

    TokenResponse refreshToken(String refreshToken);
}
