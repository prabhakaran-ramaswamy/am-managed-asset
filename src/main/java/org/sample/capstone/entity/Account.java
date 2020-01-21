package org.sample.capstone.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "account_detail")
public class Account {

    @Id
    @SequenceGenerator(name = "accountGen", sequenceName = "ACCOUNT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountGen")
    @Column(name = "ACCOUNT_ID")
    private Long id;

    @NotNull
    @Column(name = "FIRST_NAME")
    @Size(min = 3, max = 30)
    private String firstName;

    @NotNull
    @Column(name = "LAST_NAME")
    @Size(min = 3, max = 30)
    private String lastName;

    @NotNull
    @Column(name = "EMAIL")
    @Size(min = 3, max = 30)
    private String email;

    @NotNull
    @Column(name = "MOBILE")
    @Size(min = 2, max = 5)
    private String mobile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
