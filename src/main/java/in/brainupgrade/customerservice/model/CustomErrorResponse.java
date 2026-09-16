package in.brainupgrade.customerservice.model;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

public class CustomErrorResponse {
	private LocalDateTime timestamp;
	private HttpStatus status;
	private String reason;
	private String message;

	public CustomErrorResponse(final LocalDateTime timestamp, final HttpStatus status, final String reason, final String message) {
		this.timestamp = timestamp;
		this.status = status;
		this.reason = reason;
		this.message = message;
	}

	public CustomErrorResponse() {
	}

	public LocalDateTime getTimestamp() {
		return this.timestamp;
	}

	public HttpStatus getStatus() {
		return this.status;
	}

	public String getReason() {
		return this.reason;
	}

	public String getMessage() {
		return this.message;
	}

	public void setTimestamp(final LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public void setStatus(final HttpStatus status) {
		this.status = status;
	}

	public void setReason(final String reason) {
		this.reason = reason;
	}

	public void setMessage(final String message) {
		this.message = message;
	}
}
