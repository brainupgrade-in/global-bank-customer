package in.brainupgrade.customerservice.model;

public class AuthenticationResponse {
	private String userid;
	private String name;
	private boolean isValid;

	public AuthenticationResponse(final String userid, final String name, final boolean isValid) {
		this.userid = userid;
		this.name = name;
		this.isValid = isValid;
	}

	public AuthenticationResponse() {
	}

	public String getUserid() {
		return this.userid;
	}

	public String getName() {
		return this.name;
	}

	public boolean isValid() {
		return this.isValid;
	}

	public void setUserid(final String userid) {
		this.userid = userid;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setValid(final boolean isValid) {
		this.isValid = isValid;
	}
}
