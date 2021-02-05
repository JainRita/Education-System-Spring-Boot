package com.training.educationsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.educationsystem.entities.Test;
/**
 * 
 * @author aniket
 *
 */
public interface TestRepository extends JpaRepository<Test,Integer> {

}
