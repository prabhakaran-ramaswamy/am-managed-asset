package org.sample.capstone.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;


@Entity
@Table(name = "ASSERTDETAIL")
public class AssertDetail {

    @Id
    @SequenceGenerator(name = "assertDetailsGen", sequenceName = "ASSERTDETAIL_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "assertDetailsGen")
    @Column(name = "ASSERTDETAIL_ID")
    private Long id;

    @Version
    @Column(name = "VERSION")
    private Long version;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ASSERT_NUMBER")
    private String assertNumber;

    @Column(name = "SERIAL")
    private String serial;

    @Column(name = "TAGGED_TO")
    private String taggedTo;

    @Column(name = "STATUS")
    private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAssertNumber() {
		return assertNumber;
	}

	public void setAssertNumber(String assertNumber) {
		this.assertNumber = assertNumber;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getTaggedTo() {
		return taggedTo;
	}

	public void setTaggedTo(String taggedTo) {
		this.taggedTo = taggedTo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		AssertDetail other = (AssertDetail) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
