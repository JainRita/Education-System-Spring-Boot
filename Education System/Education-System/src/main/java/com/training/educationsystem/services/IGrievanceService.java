package com.training.educationsystem.services;

import java.util.List;

import com.training.educationsystem.entities.Grievance;
import com.training.educationsystem.exceptions.InvalidGrievanceException;

/**
 * 
 * @author Prajakta
 *
 */
public interface IGrievanceService {

	Grievance addGrievance(String sname, String grievance);

	List<Grievance> getAllGrievance() throws InvalidGrievanceException;

	Grievance updateGrievanceForReply(int id, String reply) throws InvalidGrievanceException;

	String viewReply(int id) throws InvalidGrievanceException;
}
