package com.training.educationsystem.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * 
 * @author aniket.
 *
 */
@Entity
@Table(name = "trainer_table")
public class Trainer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "trainer_id", nullable = false)
	private int trainerId;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "middle_name", nullable = false)
	private String middleName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "study_material_fk", nullable = true)
	@OneToMany(targetEntity = StudyMaterial.class, cascade = CascadeType.ALL)
	private List<StudyMaterial> studyMaterial;
	
	@ManyToMany
	@Column(name = "course_fk", nullable = true)
	private List<Course> courses;
	
	/**
	 * Empty Constructor.
	 */
	public Trainer() {
		super();
	}
	/**
	 * 
	 * @param trainerId
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param studyMaterial
	 */
	public Trainer(final int trainerId, final String firstName, final String middleName, final String lastName,
			final List<StudyMaterial> studyMaterial) {
		super();
		this.trainerId = trainerId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.studyMaterial = studyMaterial;
	}

	public int getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(final int trainerId) {
		this.trainerId = trainerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public List<StudyMaterial> getStudyMaterial() {
		return studyMaterial;
	}

	public void setStudyMaterial(final List<StudyMaterial> studyMaterial) {
		this.studyMaterial.addAll(studyMaterial);
	}

	@Override
	public String toString() {
		return "Trainer [trainerId=" + trainerId + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", studyMaterial=" + studyMaterial + "]";
	}

}
