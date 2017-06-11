package xyz.jeevan.api.exception;

/**
 * 
 * @author jeevan
 * @date 03-Sep-2016 11:13:53 pm
 * @purpose Custom runtime exception.
 *
 */
public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private ErrorResponseEnum errorResponse;

	public ApplicationException(ErrorResponseEnum errorResponse) {
		super(errorResponse.getErrorText());
		this.errorResponse = errorResponse;
	}

	public ApplicationException(ErrorResponseEnum errorResponse, Throwable throwable) {
		super(throwable);
		this.errorResponse = errorResponse;
	}

	public ErrorResponseEnum getErrorResponse() {
		return errorResponse;
	}

	public void setErrorResponse(ErrorResponseEnum errorResponse) {
		this.errorResponse = errorResponse;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
