package com.training.educationsystem.repositories;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.educationsystem.entities.StudyMaterial;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class StudyMaterialRepositoryTest {
	
	@Autowired
	private StudyMaterialRepository studyRepo;
	
	public StudyMaterial getStudyMaterial()
	{
		StudyMaterial study = new StudyMaterial();
		study.setContent("Testing");
		return study;
	}
	
	@Test
	@Rollback(false)
	public void testAddStudyMaterial()
	{
		StudyMaterial stud = getStudyMaterial();
		StudyMaterial saveStud = studyRepo.save(stud);
		StudyMaterial getStud = studyRepo.getOne(saveStud.getMaterialId());
		assertThat(getStud).isEqualTo(saveStud);
	}
	
	@Test
	public void getAllMaterial()
	{
		List<StudyMaterial> studList = studyRepo.findAll();
		assertThat(studList).size().isGreaterThan(0);
	}
	
	@Test
	public void deleteMaterial()
	{
		StudyMaterial stud=new StudyMaterial();
		stud.setMaterialId(36);
		stud.setContent("Spring Data JPA");
		StudyMaterial study = studyRepo.save(stud);
		studyRepo.deleteById(study.getMaterialId());
		assertFalse(studyRepo.existsById(36));
		
	}
	
	@Test
	public void viewId()
	{
		StudyMaterial stud=new StudyMaterial();
		stud.setMaterialId(59);
		stud.setContent("Spring Data REST");
		StudyMaterial study = studyRepo.save(stud);
		studyRepo.findById(study.getMaterialId());
		assertFalse(studyRepo.existsById(59));
		
	}

}
