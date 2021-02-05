package com.training.educationsystem.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.training.educationsystem.entities.Student;
/**
 * 
 * @author aniket
 *
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	public Student findByUserName(String username);

	public Student findByEmailId(String emailId);

	public List<Student> findByIsValidateFalse();
	
	public Student findByUserNameAndPassword(String username, String password);
	
	public List<Student> findByIsValidateTrue();
	
}

