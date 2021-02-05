package com.training.educationsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.educationsystem.entities.Grievance;

/**
 * 
 * @author aniket
 *
 */
public interface GrievanceRepository extends JpaRepository<Grievance,Integer>  {

}
