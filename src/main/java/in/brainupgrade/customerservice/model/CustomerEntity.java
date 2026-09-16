package in.brainupgrade.customerservice.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
public class CustomerEntity {
	@Id
	@Column(name = "userid", length = 20, unique = true)
	private String userid;
	@Column(name = "username", length = 20)
	@NotBlank
	private String username;
	@Column(name = "password")
	@NotBlank
	private String password;
	@Column(name = "dateOfBirth")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	@Column(name = "pan", length = 10)
	@NotBlank
	private String pan;
	@Column(name = "address")
	@NotBlank
	private String address;
	@Transient
	private List<Account> accounts = new ArrayList<>();

	public CustomerEntity(final String userid, final String username, final String password, final Date dateOfBirth, final String pan, final String address, final List<Account> accounts) {
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.pan = pan;
		this.address = address;
		this.accounts = accounts;
	}

	public CustomerEntity() {
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

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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
