package team.weero.app.application.port.out.auth;

public interface PasswordEncoderPort {

  String encode(String rawPassword);

  boolean matches(String rawPassword, String encodedPassword);
}
