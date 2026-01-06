package team.weero.app.infrastructure.security.jwt.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import team.weero.app.infrastructure.error.exception.ErrorCode;

@Getter
@RequiredArgsConstructor
public class WeeRoException extends RuntimeException {

  private final ErrorCode errorCode;
}
