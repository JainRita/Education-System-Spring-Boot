package com.training.educationsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.educationsystem.entities.StudyMaterial;
/**
 * 
 * @author aniket
 *
 */
public interface StudyMaterialRepository extends JpaRepository<StudyMaterial,Integer> {

}
