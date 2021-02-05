package com.training.educationsystem.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.educationsystem.entities.Trainer;
import com.training.educationsystem.repositories.TrainerRepository;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace= Replace.NONE)
@DataJpaTest
public class TrainerRepoTest {

	@Autowired
	private TrainerRepository trainerRepository;
	
	@Test
	public void testSaveTrainer() {
		Trainer trainer=new Trainer();
		trainer.setTrainerId(3);
		trainer.setFirstName("Karan");
		trainer.setMiddleName("Veer");
		trainer.setLastName("Singh");
		
		trainerRepository.save(trainer);
		
		assertNotNull(trainer);
		
	}
	
	@Test
	public void testGetTrainer() {
		Trainer trainer=new Trainer(1,"Tom","Derek","Frank",null);
		
		Trainer t=trainerRepository.save(trainer);
		
		
		assertNotNull(t);
		assertEquals(trainer.getFirstName(),t.getFirstName());
		assertEquals(trainer.getMiddleName(),t.getMiddleName());
		assertEquals(trainer.getLastName(),t.getLastName());
		
	}
	
	@Test
	public void testDeleteTrainer() {

		Trainer trainer=new Trainer(1,"Tom","Derek","Frank",null);
		
		Trainer t=trainerRepository.save(trainer);
		
		trainerRepository.deleteById(t.getTrainerId());;
		
		assertFalse(trainerRepository.existsById(1));
		
	}
	
	@Test
	public void testFindAllTrainers() {
		Trainer trainer=new Trainer(2,"Frank","Mason","Geller",null);
		trainerRepository.save(trainer);
		assertNotNull(trainerRepository.findAll());
	}
	
	
}
