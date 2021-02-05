package com.training.educationsystem.services;

import java.util.List;

import com.training.educationsystem.entities.StudyMaterial;
import com.training.educationsystem.entities.Trainer;
import com.training.educationsystem.exceptions.ListEmptyException;
import com.training.educationsystem.exceptions.TrainerNotFoundException;

/**
 * 
 * @author Afeeda A.H
 *
 */
public interface ITrainerService {
	Trainer addTrainer(Trainer trainer);

	void deleteTrainer(int trainerId) throws TrainerNotFoundException;

	Trainer viewTrainer(int trainerId) throws TrainerNotFoundException;

	List<Trainer> viewAllTrainers() throws ListEmptyException;

	Trainer updateTrainerForStudyMaterial(int trainerId, String content) throws TrainerNotFoundException;

	List<StudyMaterial> viewStudyMaterial(int trainerId) throws ListEmptyException;
}
