package xyz.jeevan.api.exception;

public class ValidationError {
	private String fieldName;

	private String errorMessage;

	public ValidationError(String fieldName, String errorMessage) {
		this.fieldName = fieldName;
		this.errorMessage = errorMessage;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public String toString() {
		return " { fieldName : " + this.fieldName + ", errorMessage : " + this.errorMessage + " }";
	}
}
