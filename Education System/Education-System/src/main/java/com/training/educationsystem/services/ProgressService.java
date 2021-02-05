package com.training.educationsystem.services;

import java.util.List;

import com.training.educationsystem.entities.Progress;

/**
 * progress service interface to declare all the methods
 * @author Aarzoo
 *
 */
public interface ProgressService {
	
	 Progress addProgress(int completedHours);

	 Progress  viewProgress(int progressId);

	 List<Progress> viewAllProgresses();
	 
	 Progress updateProgress(int id, int hours);
}
