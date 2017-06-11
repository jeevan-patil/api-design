package xyz.jeevan.api.exception;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author jeevan
 * @date 03-Sep-2016 11:13:19 pm
 * @purpose Exception / Error messages repository.
 *
 */
public enum ErrorResponseEnum {
	GENERAL_ERROR(100, "An exception has occured while processing your request.", HttpStatus.INTERNAL_SERVER_ERROR),
	VALIDATION_ERROR(101, "There was one or more validation error(s)", HttpStatus.BAD_REQUEST),
	INVALID_URL(102, "Invalid url, request not found", HttpStatus.NOT_FOUND),
	ENTITY_NOT_FOUND(103, "The requested entity could not be found", HttpStatus.BAD_REQUEST),
	FILE_UPLOAD_ERROR(104, "An exception has occured while uploading file.", HttpStatus.BAD_REQUEST);

	private int code;
	private String errorText;
	private HttpStatus httpStatus;

	private ErrorResponseEnum(int code, String errorText, HttpStatus httpStatus) {
		this.code = code;
		this.errorText = errorText;
		this.httpStatus = httpStatus;
	}

	public int getCode() {
		return code;
	}

	public String getErrorText() {
		return errorText;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
