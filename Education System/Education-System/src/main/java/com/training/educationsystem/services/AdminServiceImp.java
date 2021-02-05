package com.training.educationsystem.services;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.educationsystem.entities.Admin;
import com.training.educationsystem.exceptions.InvalidAdminException;
import com.training.educationsystem.repositories.AdminRepository;

/**
 * admin service implementation class to implement all the methods of service
 * interface and provide its definition
 * 
 * @author Aniket.
 *
 */
@Transactional
@Service
public class AdminServiceImp implements AdminService {

	/**
	 * LOGGER Definition
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImp.class);

	/**
	 * AdminRepository object
	 */
	@Autowired
	public AdminRepository adminRepo; // autowiring repository interface

	/**
	 * @param username
	 * @param password
	 *            check if the admin with same username and password exists
	 * @return success message if admin exists throws exception if not exists
	 */
	@Override
	public Admin adminLogin(final String adminUsername, final String adminPassword) throws InvalidAdminException {

		LOGGER.info("admin login method of adminServiceImp class called-START");

		final Admin admin = adminRepo.findByUserNameAndPassword(adminUsername, adminPassword);

		if (admin == null) {
			LOGGER.error("error finding admin-END");
			throw new InvalidAdminException("Invalid admin username and password!");
		} else {
			LOGGER.info("admin logged in successful message-END");
			// return "Admin Logged In Sucessfully";
			return admin;
		}
	}

}
