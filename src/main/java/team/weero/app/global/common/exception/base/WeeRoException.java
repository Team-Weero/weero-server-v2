package team.weero.app.global.common.exception.base;

import team.weero.app.global.error.dto.ErrorProperty;

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
