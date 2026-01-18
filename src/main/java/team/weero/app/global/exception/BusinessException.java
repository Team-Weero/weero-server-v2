package team.weero.app.global.exception;

public abstract class BusinessException extends RuntimeException {

  private final ErrorProperty errorProperty;

  protected BusinessException(ErrorProperty errorProperty) {
    super(errorProperty.message());
    this.errorProperty = errorProperty;
  }

  public ErrorProperty getErrorProperty() {
    return errorProperty;
  }
}
