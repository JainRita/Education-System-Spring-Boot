package com.training.educationsystem.services;

import com.training.educationsystem.entities.Admin;

/**
 * admin service interface to declare all the methods
 * @author Aniket
 *
 */
public interface AdminService {
	
	/**
	 * 
	 * @param adminUsername
	 * @param adminPassword
	 * @return String
	 */
	 Admin adminLogin(String adminUsername,String adminPassword); 

}
