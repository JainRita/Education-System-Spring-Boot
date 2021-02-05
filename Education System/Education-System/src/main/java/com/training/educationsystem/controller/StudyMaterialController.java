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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.training.educationsystem.entities.StudyMaterial;
import com.training.educationsystem.exceptions.ErrorMessages;
import com.training.educationsystem.exceptions.InvalidStudyMaterialException;
import com.training.educationsystem.exceptions.StudyMaterialException;
import com.training.educationsystem.services.IStudyMaterialService;

/**
 * 
 * @author Anisha.
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/api/educationsystem")
public class StudyMaterialController {
	
	/**
	 * Initializing Logger.
	 */
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StudyMaterialController.class);
	@Autowired
	transient private IStudyMaterialService studymaterialService;

	/**
	 * This method adds the study material details after enrollment
	 * 
	 * @param studymaterial
	 * @return StudyMaterial
	 */
	@PostMapping("/add-Studymaterial")
	public StudyMaterial addStudyMaterial(@RequestBody final StudyMaterial studymaterial)
			throws InvalidStudyMaterialException {
		LOGGER.info("adding study material details - start");

		final String materialPattern = "[a-zA-Z0-9\\s]+";
		if (!(Pattern.matches(materialPattern, studymaterial.getContent()))) {
			LOGGER.error("Checking for content type");
			throw new InvalidStudyMaterialException(
					"Content should not be null and should contain alphanumeric values only");
		} else {
			StudyMaterial addStud = studymaterialService.addStudyMaterial(studymaterial);
			LOGGER.info("adding study material details - end");
			return addStud;

		}

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidStudyMaterialException.class)
	public ErrorMessages exceptionHandler(final InvalidStudyMaterialException invalidMaterialEx) {
		return new ErrorMessages("400", invalidMaterialEx.str);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(StudyMaterialException.class)
	public ErrorMessages exceptionHandler(final StudyMaterialException studyMaterialEx) {
		return new ErrorMessages("400", studyMaterialEx.str);
	}

	/**
	 * This method views study material details by respective Id
	 * 
	 * @param id
	 * @return StudyMaterial
	 */
	@GetMapping("/get-Material/{materialId}")
	public StudyMaterial getStudyMaterialById(@PathVariable("materialId") final int id) throws StudyMaterialException {
		LOGGER.info("viewing study material details by id - start");
		final StudyMaterial getStudyMaterial = studymaterialService.getStudyMaterialById(id);
		LOGGER.info("viewing study material details by id - end");
		return getStudyMaterial;
	}

	/**
	 * This method views all study material details in a list
	 * 
	 * @return List
	 */
	@GetMapping("/view-Studymaterial")
	public List<StudyMaterial> viewStudyMaterial() throws StudyMaterialException {
		LOGGER.info("viewing study material list - start");
		final List<StudyMaterial> getStudyMaterialList = studymaterialService.viewStudyMaterial();
		LOGGER.info("viewing study material list - end");
		return getStudyMaterialList;
	}

	/**
	 * This method deletes a study material object by the respective Id
	 * 
	 * @param id
	 * @return String Message
	 * @throws StudyMaterialException
	 */
	@DeleteMapping("/remove-Studymaterial/{materialId}")
	public String deleteStudyMaterial(@PathVariable("materialId") final int materialId) throws StudyMaterialException {
		LOGGER.info("deleting study material details by id - start");
		studymaterialService.deleteStudyMaterial(materialId);
		LOGGER.info("deleting study material details by id - end");
		return "Study Material removes!";
	}

}
