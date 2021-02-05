package com.training.educationsystem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.educationsystem.entities.Admin;
import com.training.educationsystem.exceptions.InvalidAdminException;
import com.training.educationsystem.exceptions.InvalidInputException;
import com.training.educationsystem.services.AdminService;
/**
 * 
 * @author aniket.
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/educationsystem/admin")
public class AdminController {

	/**
	 * Declaring Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class); // Initializing Logger

	// autowiring AdminService service interfaces
	@Autowired
	private AdminService adminService;

	/*
	 * @param adminUername
	 * 
	 * @param adminPassword admin logs into the system using user name and password
	 * 
	 * @return successful when throws invalidInputException if the fields are not
	 * valid
	 */
	@GetMapping("/admin-login")
	public Admin adminLogin(@RequestParam("adminUsername") final String username,
			@RequestParam("adminPassword") final String password) throws InvalidAdminException {
		// LOGGER for admin logging
		LOGGER.info("admin is logging-START");

		final String pattern = "^[a-zA-Z0-9]*$";
		if (username == "") {
			LOGGER.error("username empty error thrown.....");
			throw new InvalidInputException("Username cannot be null :");
		}
		else if (password == "") {
			LOGGER.error("password empty error thrown.....");
			throw new InvalidInputException("Password cannot be null :");
		}
		else if (!(username.matches(pattern))) {
			LOGGER.error("username not valid error thrown.....");
			throw new InvalidInputException("Username can only contain alphanumeric characters ");
		}
		else if (!(password.matches(pattern))) {
			LOGGER.error("password not valid error thrown.....");
			throw new InvalidInputException("Password can only contain alphanumeric characters ");
		} else {
			LOGGER.info("admin logged in successfully-END");
			return adminService.adminLogin(username, password);
		}
	}

}
