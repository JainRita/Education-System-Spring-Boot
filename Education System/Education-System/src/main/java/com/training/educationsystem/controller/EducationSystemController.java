package com.training.educationsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author aniket.
 *
 */
@RestController
@RequestMapping("/api/educationsystem")
public class EducationSystemController {

	/**
	 * This is just a testing route
	 * 
	 * @return Route is working...
	 */
	@GetMapping("/hello")
	public String sayHi() {
		return "Route is working...";
	}

}

