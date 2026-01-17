package team.weero.app.domain.auth;

import java.util.UUID;
import team.weero.app.domain.auth.type.Authority;

public class AuthUser {

  private final UUID id;
  private final String email;
  private final Authority authority;

  public AuthUser(UUID id, String email, Authority authority) {
    this.id = id;
    this.email = email;
    this.authority = authority;
  }

  public UUID getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public Authority getAuthority() {
    return authority;
  }

  public boolean isStudent() {
    return authority == Authority.STUDENT;
  }

  public boolean isTeacher() {
    return authority == Authority.TEACHER;
  }
}
