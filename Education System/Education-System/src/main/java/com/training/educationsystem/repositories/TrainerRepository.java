package com.training.educationsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.educationsystem.entities.Trainer;
/**
 * 
 * @author aniket
 *
 */
public interface TrainerRepository  extends JpaRepository<Trainer,Integer> {
	
	public Trainer findByFirstName(String name);
}
