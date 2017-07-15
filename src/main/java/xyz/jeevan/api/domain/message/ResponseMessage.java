package xyz.jeevan.api.domain.message;

/**
 * Created by jeevan on 15/7/17.
 */
public class ResponseMessage {

  private String status;
  private String message;

  public ResponseMessage(String status, String message) {
    this.status = status;
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

}
