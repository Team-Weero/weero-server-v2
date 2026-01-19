package team.weero.app.global.error.exception;

import team.weero.app.global.error.ErrorProperty;

public abstract class WeeRoException extends RuntimeException {

  private final ErrorProperty errorProperty;

  protected WeeRoException(ErrorProperty errorProperty) {
    super(errorProperty.message());
    this.errorProperty = errorProperty;
  }

  public ErrorProperty getErrorProperty() {
    return errorProperty;
  }
}
