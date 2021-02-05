package com.training.educationsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.educationsystem.entities.Progress;

/**
 * 
 * @author aniket
 *
 */
public interface ProgressRepository extends JpaRepository<Progress,Integer>{

}
