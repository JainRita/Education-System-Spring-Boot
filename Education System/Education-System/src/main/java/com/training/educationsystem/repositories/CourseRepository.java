package com.training.educationsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.educationsystem.entities.Course;

/**
 * 
 * @author aniket
 *
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
	
	public Course findByCourseName(String name);
}
