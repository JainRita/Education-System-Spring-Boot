package com.training.educationsystem.services;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.educationsystem.entities.Grievance;
import com.training.educationsystem.exceptions.InvalidGrievanceException;
import com.training.educationsystem.repositories.GrievanceRepository;

/**
 * 
 * @author Prajakta.
 *
 */
@Transactional
@Service
public class GrievanceService implements IGrievanceService {
	/**
	 * Initializing Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(GrievanceService.class);

	/**
	 * Initializing GrievanceRepository.
	 */
	@Autowired
	transient private GrievanceRepository grievancerepo;

	/**
	 * adding grievance
	 * 
	 * @param sname
	 * @param grievance
	 * @return Grievance object
	 * 
	 */
	// adding grievance
	@Override
	public Grievance addGrievance(final String sname, final String grievance) {
		LOGGER.info("adding grievance data in grievance service");
		final Grievance grievance1 = new Grievance();
		grievance1.setSname(sname);
		grievance1.setGrievance(grievance);
		final Grievance addedGrievance = grievancerepo.save(grievance1);
		LOGGER.info("done in grievance service");
		return addedGrievance;
	}

	/**
	 * updating Grievance reply
	 * 
	 * @param id
	 * @param reply
	 * @return Grievance object
	 * @throws InvalidGrievanceException
	 * 
	 */
	// updating the reply for given id
	@Override
	public Grievance updateGrievanceForReply(final int id, final String reply) throws InvalidGrievanceException {
		LOGGER.info("Updating reply in grievance service-START");
		final Grievance aadReply = grievancerepo.getOne(id);
		if (aadReply == null) {
			LOGGER.error("Grievance of mentioned Id cannot be FOUND");
			throw new InvalidGrievanceException("Grievance of mentioned Id cannot be found");
		} else {
			aadReply.setReply(reply);
			Grievance grievance = grievancerepo.save(aadReply);
			LOGGER.info("Done in grievance service-END");
			return grievance;
		}
	}

	/**
	 * shows all Grievance present
	 * 
	 * @return Grievance List
	 * @throws InvalidGrievanceException
	 * 
	 */
	@Override
	public List<Grievance> getAllGrievance() throws InvalidGrievanceException {
		LOGGER.info("Fetching all grievance data in grievance service-START");
		final List<Grievance> grievanceList = grievancerepo.findAll();
		if (grievanceList.size() > 0) {
			LOGGER.info("Done in grievance service-END");
			return grievanceList;
		} else {
			LOGGER.error("NO grievance to show");
			throw new InvalidGrievanceException("NO grievance to show");
		}
	}

	/**
	 * shows reply of the mentioned Id
	 * 
	 * @return reply of id
	 * @throws InvalidGrievanceException
	 * 
	 */
	@Override
	public String viewReply(final int id) throws InvalidGrievanceException {
		LOGGER.info("Showing reply in grievance service-START");
		final Grievance viewReply = grievancerepo.findById(id).orElse(null);
		if (viewReply == null) {
			LOGGER.error("Reply of mentioned Id cannot be FOUND");
			throw new InvalidGrievanceException("Reply of mentioned Id cannot be FOUND");
		} else {
			final String reply = viewReply.getReply();
			LOGGER.info("Done in grievance service-END");
			return reply;
		}
	}

}
