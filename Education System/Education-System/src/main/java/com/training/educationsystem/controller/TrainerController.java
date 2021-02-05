package com.training.educationsystem.controller;

import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.training.educationsystem.entities.StudyMaterial;
import com.training.educationsystem.entities.Trainer;
import com.training.educationsystem.exceptions.AlreadyExistsException;
import com.training.educationsystem.exceptions.ErrorMessages;
import com.training.educationsystem.exceptions.InvalidTrainerException;
import com.training.educationsystem.exceptions.ListEmptyException;
import com.training.educationsystem.exceptions.TrainerNotFoundException;
import com.training.educationsystem.services.ITrainerService;

/**
 * This is the controller for Trainer module
 * 
 * @author Afeeda A.H.
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/educationsystem/trainer")
public class TrainerController {

	/**
	 * Initializing Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);

	@Autowired
	private ITrainerService service;

	/**
	 * This method adds Trainer in the System
	 * 
	 * @param trainer
	 * @return Trainer 
	 * @throws InvalidTrainerException
	 */
	@PostMapping("/add-trainer")
	public Trainer addTrainer(@RequestBody final Trainer trainer) throws InvalidTrainerException {
		LOGGER.info("Add trainer  (Service) - START");
		final String namePattern = "^[a-zA-Z]+$";
		if (trainer.getFirstName() == "" || trainer.getMiddleName() == "" || trainer.getLastName() == "") {
			LOGGER.error("First name, middle name and last name cannot be Empty!");
			throw new InvalidTrainerException("First name, middle name and last name cannot be Empty!");
		} else if (!Pattern.matches(namePattern, trainer.getFirstName())
				|| !Pattern.matches(namePattern, trainer.getMiddleName())
				|| !Pattern.matches(namePattern, trainer.getLastName())) {
			LOGGER.error("First name, middle name and last name must contain alphabets only!");
			throw new InvalidTrainerException("First name, middle name and last name must contain alphabets only!");
		} else {
			LOGGER.info("Adding Trainer");
			final Trainer addedTrainer = service.addTrainer(trainer);
			LOGGER.info("Add trainer  (Service) - END");
			return addedTrainer;
		}
	}

	/**
	 * This is the Exception Handling method
	 * 
	 * @param e
	 * @return Error Message
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidTrainerException.class)
	public ErrorMessages exceptionHandler(final InvalidTrainerException invalidTrainerEx) {
		return new ErrorMessages("400", invalidTrainerEx.message);
	}

	/**
	 * This is the Exception Handling method
	 * 
	 * @param e
	 * @return Error Message
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(TrainerNotFoundException.class)
	public ErrorMessages exceptionHandler(final TrainerNotFoundException trainerNotFoundEx) {
		return new ErrorMessages("404", trainerNotFoundEx.message);
	}

	/**
	 * This is the Exception Handling method
	 * 
	 * @param e
	 * @return Error Message
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ListEmptyException.class)
	public ErrorMessages exceptionHandler(final ListEmptyException listEmptyEx) {
		return new ErrorMessages("404", listEmptyEx.message);
	}

	/**
	 * This is the Exception Handling method
	 * 
	 * @param e
	 * @return Error Message
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(AlreadyExistsException.class)
	public ErrorMessages exceptionHandler(final AlreadyExistsException alreadyExistEx) {
		return new ErrorMessages("400", alreadyExistEx.message);
	}

	/**
	 * This method deletes each Trainer from the system
	 * 
	 * @param trainerId
	 * @return String
	 * @throws TrainerNotFoundException 
	 * @throws ListEmptyException 
	 */
	@DeleteMapping("/delete-trainer/{trainerId}")
	public List<Trainer> deleteTrainer(@PathVariable("trainerId") final int trainerId) throws TrainerNotFoundException, ListEmptyException {
		LOGGER.info("Delete trainer  (Service) - START");
		LOGGER.info("Deleting Trainer");
		service.deleteTrainer(trainerId);
		LOGGER.info("Delete trainer  (Service) - END");
		return viewAllTrainers();
	}

	/**
	 * This method displays the specified Trainer
	 * 
	 * @param trainerId
	 * @return Trainer 
	 * @throws TrainerNotFoundException
	 */
	@GetMapping("/view-trainer/{trainerId}")
	public Trainer viewTrainer(@PathVariable("trainerId") final int trainerId ) throws TrainerNotFoundException {
		LOGGER.info("View Trainer (Service) -START");
		LOGGER.info("Fetching Trainer");
		final Trainer trainer = service.viewTrainer(trainerId);
		LOGGER.info("View Trainer (Service) -END");
		return trainer;
	}

	/**
	 * This method displays all the Trainers
	 * 
	 * @return List of Trainers
	 * @throws ListEmptyException
	 */
	@GetMapping("/view-all-trainers")
	public List<Trainer> viewAllTrainers() throws ListEmptyException {
		LOGGER.info("View All Trainers  (Service) -START");
		LOGGER.info("Fetching Trainers");
		final List<Trainer> trainerList = service.viewAllTrainers();
		LOGGER.info("View All Trainers  (Service) -END");
		return trainerList;
	}

	/**
	 * This method displays study material for each trainer specified
	 * 
	 * @param trainerId
	 * @return List of Study Materials
	 * @throws ListEmptyException
	 */
	@GetMapping("/view-study-material/{trainerId}")
	public List<StudyMaterial> viewStudyMaterial(@PathVariable("trainerId") final int trainerId) throws ListEmptyException {
		LOGGER.info("View Study Materials  (Service) -START!");
		LOGGER.info("Fetching Study Materials");
		final List<StudyMaterial> studyMaterial = service.viewStudyMaterial(trainerId);
		LOGGER.info("View Study Materials  (Service) -END!");
		return studyMaterial;
	}

	/**
	 * This method adds Study Material for individual trainers
	 * 
	 * @param trainerId
	 * @param content
	 * @return Trainer 
	 * @throws TrainerNotFoundException
	 */
	@PatchMapping("/update-study-material")
	public Trainer updateTrainerForStudyMaterial(@RequestParam("trainerId") final int trainerId,
			@RequestParam("content") final String content) throws TrainerNotFoundException {
		LOGGER.info("Update Trainer for Study Material  (Service) -START");
		LOGGER.info("Adding Study Material");
		final Trainer trainer = service.updateTrainerForStudyMaterial(trainerId, content);
		LOGGER.info("Update Trainer for Study Material  (Service) -END");
		return trainer;
	}
}
