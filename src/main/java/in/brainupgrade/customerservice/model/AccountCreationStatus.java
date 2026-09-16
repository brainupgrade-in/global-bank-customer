package in.brainupgrade.customerservice.model;

import jakarta.persistence.Id;

public class AccountCreationStatus {
	@Id
	long accountId;
	String message;

	public AccountCreationStatus(final long accountId, final String message) {
		this.accountId = accountId;
		this.message = message;
	}

	public AccountCreationStatus() {
	}

	public long getAccountId() {
		return this.accountId;
	}

	public String getMessage() {
		return this.message;
	}

	public void setAccountId(final long accountId) {
		this.accountId = accountId;
	}

	public void setMessage(final String message) {
		this.message = message;
	}
}
