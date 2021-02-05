package com.training.educationsystem.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * 
 * @author aniket.
 *
 */
@Entity
@Table(name = "course_table")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "course_Id", nullable = false)
	private int courseId;

	@Column(name = "course_name", nullable = false)
	private String courseName;
	
	@Column(name = "course_amount", nullable = false)
	private double courseAmount;


	@Column(name = "student_fk", nullable = true)
	//@ManyToMany(cascade = CascadeType.ALL)
	@ManyToMany(targetEntity = Student.class)
	private List<Student> students;

	@Column(name = "trainer_fk", nullable = true)
	@ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
	private List<Trainer> trainers;

	@Column(name = "hours")
	private float hours;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "test_fk")
	private Test test;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_fk")
	private Payment payment;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "progress_id", nullable = true)
	private Progress progress;
	/**
	 * Empty Constructor.
	 */
	public Course() {
		super();
	}
	/**
	 * 
	 * @param courseId
	 * @param courseName
	 * @param students
	 * @param trainers
	 * @param hours
	 * @param test
	 * @param payment
	 * @param progress
	 */

	public Course(final int courseId, final String courseName, final List<Student> students, final List<Trainer> trainers, final float hours,
			final Test test, final Payment payment, final Progress progress) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.students = students;
		this.trainers = trainers;
		this.hours = hours;
		this.test = test;
		this.payment = payment;
		this.progress = progress;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(final int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(final String courseName) {
		this.courseName = courseName;
	}

	
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(final List<Student> students) {
		this.students = students;
	}

	public List<Trainer> getTrainers() {
		return trainers;
	}

	public void setTrainers(final List<Trainer> trainers) {
		this.trainers = trainers;
	}

	public float getHours() {
		return hours;
	}

	public void setHours(final float hours) {
		this.hours = hours;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(final Test test) {
		this.test = test;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(final Payment payment) {
		this.payment = payment;
	}

	public Progress getProgress() {
		return progress;
	}

	public void setProgress(final Progress progress) {
		this.progress = progress;
	}
	
	
	public double getCourseAmount() {
		return courseAmount;
	}
	public void setCourseAmount(double courseAmount) {
		this.courseAmount = courseAmount;
	}
	
	
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", courseAmount=" + courseAmount
				+ ", students=" + students + ", trainers=" + trainers + ", hours=" + hours + ", test=" + test
				+ ", payment=" + payment + ", progress=" + progress + "]";
	}

	

}

