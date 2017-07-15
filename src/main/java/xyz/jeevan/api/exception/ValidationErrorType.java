package xyz.jeevan.api.exception;

public enum ValidationErrorType {
  REQUIRED_FIELD_MISSING("REQUIRED_FIELD_MISSING"),
  INVALID_VALUE("INVALID_VALUE"),
  MULTIPLE_CONTENT_OF_SAME_LANG("MULTIPLE_CONTENT_OF_SAME_LANG"),
  VALUE_ALREADY_IN_USE("VALUE_ALREADY_IN_USE"),
  USERNAME_TAKEN("USERNAME_TAKEN"),
  EMAIL_EXISTS("EMAIL_EXISTS");

  private String errorType;

  ValidationErrorType(String errorType) {
    this.errorType = errorType;
  }

  public String getErrorType() {
    return errorType;
  }

  public void setErrorType(String errorType) {
    this.errorType = errorType;
  }
}
