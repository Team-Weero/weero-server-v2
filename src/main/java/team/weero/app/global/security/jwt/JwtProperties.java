package team.weero.app.global.security.jwt;

import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.jwt")
public class JwtProperties {

  private final String secretKey;
  private final int accessExp;
  private final int refreshExp;
  private final String header;
  private final String prefix;
  private final SecretKey key;

  public JwtProperties(
      String secretKey, int accessExp, int refreshExp, String header, String prefix) {
    if (secretKey == null || secretKey.trim().isEmpty()) {
      throw new IllegalArgumentException("JWT secret key cannot be null or empty");
    }
    if (secretKey.getBytes(StandardCharsets.UTF_8).length < 64) {
      throw new IllegalArgumentException(
          "JWT secret key must be at least 64 bytes for HS512 (current: "
              + secretKey.getBytes(StandardCharsets.UTF_8).length
              + " bytes)");
    }
    this.secretKey = secretKey;
    this.accessExp = accessExp;
    this.refreshExp = refreshExp;
    this.header = header;
    this.prefix = prefix;
    this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
  }

  public String getSecretKey() {
    return secretKey;
  }

  public int getAccessExp() {
    return accessExp;
  }

  public int getRefreshExp() {
    return refreshExp;
  }

  public String getHeader() {
    return header;
  }

  public String getPrefix() {
    return prefix;
  }

  public SecretKey getKey() {
    return key;
  }
}
