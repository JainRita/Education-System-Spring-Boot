package com.training.educationsystem.services;

import java.util.List;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.educationsystem.entities.Progress;
import com.training.educationsystem.exceptions.InvalidProgressException;
import com.training.educationsystem.repositories.ProgressRepository;

/*
 * progress service implementation class to implement 
 * all the methods of service interface and provide its defintion
 */
@Transactional
@Service
public class ProgressServiceImp implements ProgressService{
	
	/**
	 * Initializing Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ProgressServiceImp.class);
	
	@Autowired
	transient private ProgressRepository progressRepo; // autowiring repository interface
	
	/*
	 * @param completedHours
	 * method for adding the progress
	 * here completed hours of the course will be added into progress table by calling the save function of jpa repository
	 * @return the progress after saving it 
	 */
	@Override
	public Progress addProgress(final int completedHours) throws InvalidProgressException {
		LOGGER.info("ProgressServiceImp class's addProgress method called-START");
		final Progress progress=new Progress();
		progress.setCompletedHours(completedHours);
		LOGGER.info("saving and returning the progress-END");
		return progressRepo.save(progress);
	}
	
	/*
	 * @param progressId
	 * method to view the progress
	 * here progress of each course is found by calling the getOne function of jpa repository
	 * @return the progress in the database 
	 */
	@Override
	public Progress viewProgress(final int progressId) throws InvalidProgressException {		
		LOGGER.info("ProgressServiceImp class's viewProgress method called-START");
		LOGGER.info("finding and returning the progress.....");
		if(progressRepo.existsById(progressId)) {
			LOGGER.info("returning the progress-END");
			return progressRepo.getOne(progressId);
		}
		else {
			LOGGER.error("throwing progress with id not found exception");
			throw new InvalidProgressException("No such progress found with this id : " + progressId);
		}
	}

	/*
	 * method to view all the progresses
	 * here progress of all course is found by calling the findAll function of jpa repository 
	 * @return all the progresses in the database
	 */
	@Override
	public List<Progress> viewAllProgresses() throws InvalidProgressException {
		LOGGER.info("ProgressServiceImp class's viewAllProgresses method called-START");
		LOGGER.info("finding and returning all the progresses.....");
		if(progressRepo.findAll().size()>0) {
			LOGGER.info("returning all the progress-END");
			return progressRepo.findAll();
		}
		else {
			LOGGER.error("throwing progress not found exception");
			throw new InvalidProgressException("No progresses found!");
		}
	}
	/**
	 * This method to view all the progresses
	 * @return updated progress in the database
	 * @throws InvlidProgressException if no progresses exists 
	 */
	@Override
	public Progress updateProgress(int id, int hours) {
		LOGGER.info("ProgressServiceImp class's updateProgress method called-START");
		if(progressRepo.existsById(id)) {
			Progress progress=progressRepo.getOne(id);
			progress.setCompletedHours(hours);
			LOGGER.info("updating and returning the progress-END");
			return progressRepo.saveAndFlush(progress);
		}
		else {
			LOGGER.error("throwing progress with id not found exception");
			throw new InvalidProgressException("No such progress found with this id : " + id);
		}
	}
}

