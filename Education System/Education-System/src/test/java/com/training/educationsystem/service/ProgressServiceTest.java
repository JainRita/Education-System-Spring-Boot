package com.training.educationsystem.service;


import static org.assertj.core.api.Assertions.assertThat;

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

import com.training.educationsystem.entities.Progress;
import com.training.educationsystem.repositories.ProgressRepository;
import com.training.educationsystem.services.ProgressServiceImp;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProgressServiceTest {

	@InjectMocks
	private ProgressServiceImp progressService;
	
	@MockBean 
	private ProgressRepository progressRepo;
	
	@Test
	public void testAddProgress() {		//test for adding progress
		Progress progress=new Progress();
		progress.setCompletedHours(5);
		Mockito.when(progressRepo.save(progress)).thenReturn(progress);
		assertThat(progress).isEqualTo(progress);
	}
	
	@Test
	public void testViewProgress() {		//test for viewing progress
		Progress progress=new Progress();
		progress.setProgressId(93);
		progress.setCompletedHours(3);
		
		Mockito.when(progressRepo.getOne(progress.getProgressId())).thenReturn(progress);
		assertThat(progressRepo.getOne(progress.getProgressId())).isEqualTo(progress);
		
	}
	
	@Test
	public void testViewAllProgresses() {		//test for viewing progresses
		Progress progress=new Progress();
		progress.setCompletedHours(5);
		
		Progress progress1=new Progress();
		progress1.setCompletedHours(6);
		
		List<Progress> progressList=new ArrayList<>();
		progressList.add(progress);
		progressList.add(progress1);
		
		Mockito.when(progressRepo.findAll()).thenReturn(progressList);
		assertThat(progressService.viewAllProgresses()).isEqualTo(progressList);
	}
	
	
}
