package com.training.educationsystem.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.educationsystem.entities.Progress;
import com.training.educationsystem.exceptions.InvalidInputException;
import com.training.educationsystem.exceptions.InvalidProgressException;
import com.training.educationsystem.services.ProgressService;
import org.springframework.web.bind.annotation.CrossOrigin;
/**
 * 
 * @author aniket.
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/api/educationsystem/progress")
public class ProgressController {
	
	/**
	 * Initialzing Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ProgressController.class);
			
	@Autowired
	transient private ProgressService progressService;

	/*
	 * @param completedhours
	 * student will add the completed hours of the course to the progress table
	 * @return the added progress in the progress table
	 * throws exception if the field is invalid
	 */
	@PostMapping("/add-progress")
	public Progress addProgress(@RequestParam("completedHours") final int hours) throws InvalidProgressException {
		//LOGGER for adding progress
		LOGGER.info("adding progress-START");
		if (hours < 0) {
			LOGGER.error("progress hours negative error thrown.....");
			throw new InvalidInputException("Progress hours cannot be negative :" + hours);
		}
		if (hours == 0) {
			LOGGER.error("progress hours zero error thrown.....");
			throw new InvalidInputException("Progress hours cannot be zero :" + hours);
		}
		if (hours > 10) {
			LOGGER.error("progress hours invalid error thrown.....");
			throw new InvalidInputException("Progress hours cannot be greater than 9999 :" + hours);
		} else {
			LOGGER.info("progress added successfully-END");
			return progressService.addProgress(hours);
		}
	}
	/**
	 * student will update the completed hours of the course to the progress table
	 * @param progressId
	 * @param completedhours
	 * @return the added progress in the progress table
	 * @throws exception if the field is invalid
	 */
	@PatchMapping("/update-progress")
	public Progress updateProgress(@RequestParam("progressId") int id, @RequestParam("completedHours") int hours) {
		
		LOGGER.info("updating progress-START");
		if (id < 0) {
			LOGGER.error("progress id negative error thrown.....");
			throw new InvalidInputException("Progress id cannot be negative :" + id);
		}
		if (id == 0) {
			LOGGER.error("progress id zero error thrown.....");
			throw new InvalidInputException("Progress id cannot be zero :" + id);
		}
		if (hours < 0) {
			LOGGER.error("progress hours negative error thrown.....");
			throw new InvalidInputException("Progress hours cannot be negative :" + hours);
		}
		
		if (hours > 10) {
			LOGGER.error("progress hours invalid error thrown.....");
			throw new InvalidInputException("Progress hours cannot be greater than 9999 :" + hours);
		} else {
			LOGGER.info("progress updated successfully-END");
			return progressService.updateProgress(id,hours);
		}
	}

	/*
	 * @param progressId 
	 * student/admin will view the progress of each course from the progress table
	 * @return the progress by progress id specified
	 * throws exception if the fields are invalid
	 */
	@GetMapping("/view-progress")
	public Progress viewProgress(@RequestParam("progressId") final int progressId) throws InvalidProgressException {
		//LOGGER for viewing progress
		LOGGER.info("viewing progress-START");
		if (progressId < 0) {
			LOGGER.error("progress id negative error.....");
			throw new InvalidInputException("Progress id cannot be negative :" + progressId);
		}
		if (progressId == 0) {
			LOGGER.error("progress id zero error.....");
			throw new InvalidInputException("Progress id cannot be zero :" + progressId);
		}
		if (progressId > 9999) {
			LOGGER.error("progress id invalid error.....");
			throw new InvalidInputException("Progress id cannot be greater than 9999 :" + progressId);
		} else {
			LOGGER.info("progress viewed-END");
			return progressService.viewProgress(progressId);
		}

	}

	/*
	 * student/admin will view the progress of all courses from the progress table
	 * @return all progresses
	 */
	@GetMapping("/view-all-progresses")
	public List<Progress> viewAllProgresses() throws InvalidProgressException {
		//LOGGER for viewing all the progresses
		LOGGER.info("viewing all the progresses-START-END");
		return progressService.viewAllProgresses();
	}

}

