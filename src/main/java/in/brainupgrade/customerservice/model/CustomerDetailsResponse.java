package in.brainupgrade.customerservice.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Table;

@Table
public class CustomerDetailsResponse {
	private String userid;
	private String username;
	private String password;
	private Date dateOfBirth;
	private String pan;
	private String address;
	private List<Account> accounts = new ArrayList<>();

	public CustomerDetailsResponse(final String userid, final String username, final String password, final Date dateOfBirth, final String pan, final String address, final List<Account> accounts) {
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.pan = pan;
		this.address = address;
		this.accounts = accounts;
	}

	public CustomerDetailsResponse() {
	}

	public String getUserid() {
		return this.userid;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public String getPan() {
		return this.pan;
	}

	public String getAddress() {
		return this.address;
	}

	public List<Account> getAccounts() {
		return this.accounts;
	}

	public void setUserid(final String userid) {
		this.userid = userid;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public void setDateOfBirth(final Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setPan(final String pan) {
		this.pan = pan;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public void setAccounts(final List<Account> accounts) {
		this.accounts = accounts;
	}
}
