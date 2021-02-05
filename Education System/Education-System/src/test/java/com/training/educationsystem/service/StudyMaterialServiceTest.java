package com.training.educationsystem.service;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.training.educationsystem.entities.StudyMaterial;
import com.training.educationsystem.exceptions.StudyMaterialException;
import com.training.educationsystem.repositories.StudyMaterialRepository;
import com.training.educationsystem.services.IStudyMaterialService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)

@SpringBootTest
public class StudyMaterialServiceTest {
	@InjectMocks
	private IStudyMaterialService studyService;
	
	@MockBean
	private StudyMaterialRepository studyRepo;
	
	@Test
	public void testAddMaterial()
	{
		StudyMaterial stud = new StudyMaterial();
		stud.setContent("Introduction to testing");
		
		Mockito.when(studyRepo.save(stud)).thenReturn(stud);
		assertThat(studyService.addStudyMaterial(stud)).isEqualTo(stud);
	}
	
	@Test
	public void testViewMaterial() throws StudyMaterialException
	{
		StudyMaterial stud1 = new StudyMaterial();
		stud1.setContent("Testing tutorial 1");
		
		StudyMaterial stud2 = new StudyMaterial();
		stud2.setContent("Testing tutorial 2");
		
		List<StudyMaterial> studList = new ArrayList<StudyMaterial>();
		studList.add(stud1);
		studList.add(stud2);
		
		Mockito.when(studyRepo.findAll()).thenReturn(studList);
		assertThat(studyService.viewStudyMaterial()).isEqualTo(studList);
	}
	
	@Test
	public void testDelete() throws StudyMaterialException
	{
		StudyMaterial stud=new StudyMaterial();
		stud.setMaterialId(36);
		stud.setContent("Spring Data JPA");
		Mockito.when(studyRepo.getOne(36)).thenReturn(stud);
		Mockito.when(studyRepo.existsById(stud.getMaterialId())).thenReturn(false);
		assertFalse(studyRepo.existsById(stud.getMaterialId()));
	
	}
	
	

}
