package org.sample.capstone.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "MANAGED_ASSERT")
public class ManagedAssert {

    @Id
    @SequenceGenerator(name = "managedAssertGen", sequenceName = "MANAGED_ASSERT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "managedAssertGen")
    @Column(name = "MANAGED_ASSERT_ID")
    private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


    
}
