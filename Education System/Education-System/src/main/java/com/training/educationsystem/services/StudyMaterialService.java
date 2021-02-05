package com.training.educationsystem.services;

import java.util.List;

import com.training.educationsystem.entities.StudyMaterial;
import com.training.educationsystem.exceptions.StudyMaterialException;

/**
 * 
 * @author Anisha
 *
 */
public interface StudyMaterialService {
	 StudyMaterial addStudyMaterial(StudyMaterial studymaterial);

	 StudyMaterial getStudyMaterialById(int id) throws StudyMaterialException;

	 List<StudyMaterial> viewStudyMaterial() throws StudyMaterialException;

	 void deleteStudyMaterial(int id) throws StudyMaterialException;

}
