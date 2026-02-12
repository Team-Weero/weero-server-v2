package team.weero.app.global.error.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team.weero.app.global.common.exception.ErrorCode;
import team.weero.app.global.common.exception.base.WeeRoException;
import team.weero.app.global.error.dto.ErrorProperty;
import team.weero.app.global.error.dto.ErrorResponse;
import team.weero.app.global.error.notifier.DiscordWebhookNotifier;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private final DiscordWebhookNotifier discordWebhookNotifier;

  public GlobalExceptionHandler(DiscordWebhookNotifier discordWebhookNotifier) {
    this.discordWebhookNotifier = discordWebhookNotifier;
  }

  @ExceptionHandler(WeeRoException.class)
  public ResponseEntity<ErrorResponse> handleBusinessException(
      WeeRoException exception, HttpServletRequest request) {
    ErrorProperty errorProperty = exception.getErrorProperty();
    ErrorResponse errorResponse = ErrorResponse.of(errorProperty);

    if (errorProperty.status() >= 500) {
      String endpoint = request.getMethod() + " " + request.getRequestURI();
      discordWebhookNotifier.sendErrorNotification(exception, endpoint);
    }

    return ResponseEntity.status(errorProperty.status()).body(errorResponse);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(
      Exception exception, HttpServletRequest request) {
    ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);

    String endpoint = request.getMethod() + " " + request.getRequestURI();
    discordWebhookNotifier.sendErrorNotification(exception, endpoint);

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
  }
}
