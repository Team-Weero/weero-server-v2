package team.weero.app.global.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team.weero.app.global.common.exception.error.ErrorCode;
import team.weero.app.global.error.exception.WeeRoException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(WeeRoException.class)
  public ResponseEntity<ErrorResponse> handleBusinessException(WeeRoException exception) {
    ErrorProperty errorProperty = exception.getErrorProperty();
    ErrorResponse errorResponse = ErrorResponse.of(errorProperty);
    return ResponseEntity.status(errorProperty.status()).body(errorResponse);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception exception) {
    ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
  }
}
