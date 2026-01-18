package team.weero.app.global.security.jwt;

public class JwtConstants {

  public static final String CLAIM_USER_ID = "userId";
  public static final String CLAIM_EMAIL = "email";
  public static final String CLAIM_AUTHORITY = "authority";
  public static final String HEADER_TOKEN_TYPE = "typ";
  public static final String TYPE_ACCESS = "access";
  public static final String TYPE_REFRESH = "refresh";

  private JwtConstants() {}
}
