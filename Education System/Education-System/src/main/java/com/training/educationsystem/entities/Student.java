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
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
/**
 * Student Entity class
 * @author aniket.
 *
 */
@Entity
@Table(name = "student_table")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "student_id")
	private int studentId;
	
	@NotNull(message = "First name should not be empty")
	@Size(min=2, message = "First name should not be less than 2 characters")
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	
	@NotNull(message = "middle name should not be empty")
	@Size(min=2, message = "middle name should not be less than 2 characters")
	@Column(name = "middle_name", nullable = false)
	private String middleName;

	@NotNull(message = "Last name should not be empty")
	@Size(min=2, message = "Last name should not be less than 2 characters")
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Email(message = "Invalid E-mail Id")
	@Column(name = "email_id", nullable = false)
	private String emailId;
	
	@NotNull(message = "Username should not be empty")
	@Size(min = 2, message = "Username should not be less than 2 characters")
	@Column(name = "user_name", nullable = false)
	private String userName;
	
	@Pattern(regexp = "\\d{10}", message = "Invalid contact details")
	@Column(name = "contact_number", nullable = false)
	private String contactNumber;
	
	@NotNull(message="Password is a required field")
	@Column(name = "password", nullable = false)
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message= "Password Should contain:\n1)a digit must occur at least once\n 2)a lower case letter must occur at least once\n\r\n 3)an upper case letter must occur at least once\n4)a special character must occur at least once\n\r\n5)no whitespace allowed in the entire string\n6)at least 8 characters")
	private String password;

	@Transient
	@NotNull(message="Confirm Password is a required field")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message= "Password Should contain:\n1)a digit must occur at least once\n 2)a lower case letter must occur at least once\n\r\n 3)an upper case letter must occur at least once\n4)a special character must occur at least once\n\r\n5)no whitespace allowed in the entire string\n6)at least 8 characters")
	private String confirmPassword;

	@Column(name = "course_fk", nullable = true)
	//@ManyToMany(mappedBy = "students", cascade = CascadeType.ALL)
	@ManyToMany(targetEntity = Course.class)
	private List<Course> courses;
	
	@Column(name = "feedback_fk", nullable = true)
	@OneToMany(targetEntity = Feedback.class, cascade = CascadeType.ALL)
	private List<Feedback> feedback;
	
	private boolean isValidate = false;
	
	/**
	 * Empty Constructor.
	 */
	public Student() {
		super();
	}
	/**
	 * 
	 * @param studentId
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param emailId
	 * @param userName
	 * @param contactNumber
	 * @param password
	 * @param confirmPassword
	 * @param courses
	 * @param feedback
	 * @param isValidate
	 */
	public Student(final int studentId, final String firstName, final String middleName, final String lastName, final String emailId, final String userName,
			final String contactNumber, final String password, final String confirmPassword, final List<Course> courses,
			final List<Feedback> feedback, final boolean isValidate) {
		super();
		this.studentId = studentId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.userName = userName;
		this.contactNumber = contactNumber;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.courses = courses;
		this.feedback = feedback;
		this.isValidate = isValidate;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(final int studentId) {
		this.studentId = studentId;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(final String emailId) {
		this.emailId = emailId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(final String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(final String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(final List<Course> courses) {
		this.courses.addAll(courses);
		//this.courses = courses;
	}

	public List<Feedback> getFeedback() {
		return feedback;
	}

	public void setFeedback(final List<Feedback> feedback) {
		this.feedback = feedback;
	}

	public boolean isValidate() {
		return isValidate;
	}

	public void setValidate(final boolean isValidate) {
		this.isValidate = isValidate;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", emailId=" + emailId + ", userName=" + userName + ", contactNumber="
				+ contactNumber + ", password=" + password + ", confirmPassword=" + confirmPassword + ", courses="
				+ courses + ", feedback=" + feedback + ", isValidate=" + isValidate + "]";
	}

}

