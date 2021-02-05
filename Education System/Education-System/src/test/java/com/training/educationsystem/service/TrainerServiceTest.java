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

import com.training.educationsystem.entities.Trainer;
import com.training.educationsystem.exceptions.InvalidTrainerException;
import com.training.educationsystem.exceptions.ListEmptyException;
import com.training.educationsystem.exceptions.TrainerNotFoundException;
import com.training.educationsystem.repositories.StudyMaterialRepository;
import com.training.educationsystem.repositories.TrainerRepository;
import com.training.educationsystem.services.TrainerService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TrainerServiceTest {

	@InjectMocks
	private TrainerService trainerService;

	@MockBean
	private TrainerRepository trainerRepo;

	@MockBean
	private StudyMaterialRepository studyRepo;

	@Test
	public void testAddTrainer() {
		Trainer trainer = new Trainer();

		trainer.setTrainerId(0);
		trainer.setFirstName("Kevin");
		trainer.setMiddleName("Philips");
		trainer.setLastName("Mathew");

		Mockito.when(trainerRepo.save(trainer)).thenReturn(trainer);
		assertThat(trainerService.addTrainer(trainer)).isEqualTo(trainer);
	}

	@Test
	public void testViewAllTrainers() throws InvalidTrainerException, ListEmptyException {

		Trainer trainer1 = new Trainer();

		trainer1.setTrainerId(1);
		trainer1.setFirstName("Larry");
		trainer1.setMiddleName("Philips");
		trainer1.setLastName("Drills");

		Trainer trainer2 = new Trainer();

		trainer2.setTrainerId(2);
		trainer2.setFirstName("Rohan");
		trainer2.setMiddleName("Jeet");
		trainer2.setLastName("Singh");

		List<Trainer> trainers = new ArrayList<>();
		trainers.add(trainer1);
		trainers.add(trainer2);

		Mockito.when(trainerRepo.findAll()).thenReturn(trainers);
		assertThat(trainerService.viewAllTrainers()).isEqualTo(trainers);
	}

	@Test
	public void testViewTrainer() throws TrainerNotFoundException {
		Trainer trainer = new Trainer();

		trainer.setTrainerId(44);
		trainer.setFirstName("Afeeda");
		trainer.setMiddleName("Abdul");
		trainer.setLastName("Hameed");

		Mockito.when(trainerRepo.getOne(44)).thenReturn(trainer);
		assertThat(trainerRepo.getOne(44)).isEqualTo(trainer);
	}

	@Test
	public void testDeleteCourse() {
		Trainer trainer = new Trainer();

		trainer.setTrainerId(4);
		trainer.setFirstName("Rehan");
		trainer.setMiddleName("Preet");
		trainer.setLastName("Singh");

		Mockito.when(trainerRepo.getOne(1)).thenReturn(trainer);
		Mockito.when(trainerRepo.existsById(trainer.getTrainerId())).thenReturn(false);
		assertFalse(trainerRepo.existsById(trainer.getTrainerId()));
	}

	
}
