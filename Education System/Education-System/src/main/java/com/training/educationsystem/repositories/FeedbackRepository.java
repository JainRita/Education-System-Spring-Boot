package com.training.educationsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.educationsystem.entities.Feedback;

/**
 * 
 * @author aniket
 *
 */
public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {

}
