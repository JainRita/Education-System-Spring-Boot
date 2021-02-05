package com.training.educationsystem.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.educationsystem.controller.CourseController;
import com.training.educationsystem.entities.StudyMaterial;
import com.training.educationsystem.entities.Trainer;
import com.training.educationsystem.exceptions.ListEmptyException;
import com.training.educationsystem.exceptions.TrainerNotFoundException;
import com.training.educationsystem.repositories.StudyMaterialRepository;
import com.training.educationsystem.repositories.TrainerRepository;

/**
 * This is Service class for Trainer module
 * 
 * @author Afeeda A.H
 *
 */
@Transactional
@Service
public class TrainerService implements ITrainerService {
	
	/**
	 * Initializing Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);

	@Autowired
	transient private TrainerRepository trainerRepo;

	@Autowired
	transient private StudyMaterialRepository studyRepo;

	/**
	 * This method adds Trainer in the System
	 * 
	 * @param trainer
	 * @return Trainer
	 */
	@Override
	public Trainer addTrainer(final Trainer trainer) {
		LOGGER.info("Add trainer  (Service) - START");
		trainerRepo.save(trainer);
		LOGGER.info("Trainer Added Successfully!");
		LOGGER.info("Add trainer  (Service) - END");
		return trainer;
	}

	/**
	 * This method deletes each Trainer from the system
	 * 
	 * @param id
	 * @return Nothing
	 * @throws TrainerNotFoundException
	 */
	@Override
	public void deleteTrainer(final int id) throws TrainerNotFoundException {
		LOGGER.info("Delete trainer  (Service) - START");
		if (trainerRepo.existsById(id)) {
			trainerRepo.deleteById(id);
			LOGGER.info("Trainer Deleted Successfully!");
			LOGGER.info("Delete trainer  (Service) - END");
		} else {
			LOGGER.error("Trainer cannot be deletes as this trainer is not found!");
			throw new TrainerNotFoundException("Trainer cannot be deletes as this trainer is not found!");
		}
	}

	/**
	 * This method displays the specified Trainer
	 * 
	 * @param trainerId
	 * @return Trainer
	 * @throws TrainerNotFoundException
	 */
	@Override
	public Trainer viewTrainer(final int trainerId) throws TrainerNotFoundException {
		LOGGER.info("View Trainer (Service) -START");
		final Trainer trainer = trainerRepo.findById(trainerId).orElse(null);
		if (trainer == null) {
			LOGGER.error("Trainer cannot be found!");
			throw new TrainerNotFoundException("Trainer cannot be found!");
		} else {
			LOGGER.info("Displaying Trainer!");
			LOGGER.info("View Trainer (Service) -END");
			return trainer;
		}

	}

	/**
	 * This method displays all the Trainers
	 * 
	 * @return List of Trainers
	 * @throws ListEmptyException
	 */
	@Override
	public List<Trainer> viewAllTrainers() throws ListEmptyException {
		LOGGER.info("View All Trainers  (Service) -START");
		final List<Trainer> trainerList = trainerRepo.findAll();
		if (trainerList.size() > 0) {
			LOGGER.info("Displaying Trainers!");
			LOGGER.info("View All Trainers  (Service) -END");
			return trainerList;
		} else {
			LOGGER.error("No Trainers to show!");
			throw new ListEmptyException("No Trainers to show!");
		}

	}

	/**
	 * This method adds Study Material for individual trainers
	 * 
	 * @param trainerId
	 * @param content
	 * @return Trainer
	 * @throws TrainerNotFoundException
	 */
	@Override
	public Trainer updateTrainerForStudyMaterial(final int trainerId,final String content) throws TrainerNotFoundException {
		LOGGER.info("Update Trainer for Study Material  (Service) -START");
		final Trainer trainer = trainerRepo.findById(trainerId).orElse(null);
		final StudyMaterial studyMaterial = new StudyMaterial();
		final List<StudyMaterial> studyMaterialList = new ArrayList<StudyMaterial>();
		if (trainer == null) {
			LOGGER.error("Study Material cannot be added as Trainer cannot be found!");
			throw new TrainerNotFoundException("Study Material cannot be added as Trainer cannot be found!");
		} else {
			studyMaterialList.add(studyMaterial);
			studyMaterial.setContent(content);
			studyRepo.save(studyMaterial);
			trainer.setStudyMaterial(studyMaterialList);
			trainerRepo.save(trainer);
			LOGGER.info("Study Material Added Successfully!");
			LOGGER.info("Update Trainer for Study Material  (Service) -END");
			return trainer;
		}

	}

	/**
	 * This method displays study material for each trainer specified
	 * 
	 * @param trainerId
	 * @return List of Study Materials
	 * @throws ListEmptyException
	 */
	@Override
	public List<StudyMaterial> viewStudyMaterial(final int trainerId) throws ListEmptyException {
		LOGGER.info("View Study Materials  (Service) -START!");
		final Trainer trainer = trainerRepo.getOne(trainerId);
		final List<StudyMaterial> studyMaterialList = trainer.getStudyMaterial();
		if (studyMaterialList.size() > 0) {
			LOGGER.info("Displaying Study Materials!");
			LOGGER.info("View Study Materials  (Service) -END!");
			return studyMaterialList;
		} else {
			LOGGER.error("No Study Materials to show!");
			throw new ListEmptyException("No Study Materials to show!");
		}

	}

}
