package xyz.jeevan.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import xyz.jeevan.api.exception.ValidationError;

public class ErrorResponse {

  private int code;
  private String errorMessage;

  @JsonInclude(Include.NON_NULL)
  private List<ValidationError> errors; // To throw multiple error

  @JsonInclude(Include.NON_NULL)
  private ValidationError error; // To throw single error.

  public ErrorResponse(int code, String errorMessage) {
    this.code = code;
    this.errorMessage = errorMessage;
  }

  public ErrorResponse(int code, String errorMessage, ValidationError error,
      List<ValidationError> errors) {
    this.code = code;
    this.errorMessage = errorMessage;
    this.error = error;
    this.errors = errors;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public List<ValidationError> getErrors() {
    return errors;
  }

  public void setErrors(List<ValidationError> errors) {
    this.errors = errors;
  }

  public ValidationError getError() {
    return error;
  }

  public void setError(ValidationError error) {
    this.error = error;
  }

  @Override
  public String toString() {
    StringBuilder errorString = new StringBuilder();

    errorString.append("{ code : " + code);
    errorString.append(", errorMessage : " + errorMessage);
    if (null != this.error) {
      errorString.append(", error : { " + this.error.toString());
    }
    if (null != this.errors) {
      errorString.append(", errors : { " + this.errors.toString());
    }
    errorString.append(" }");
    return errorString.toString();
  }

}
