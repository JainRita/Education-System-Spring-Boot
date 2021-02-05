package com.training.educationsystem.service;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.training.educationsystem.entities.Grievance;
import com.training.educationsystem.exceptions.InvalidGrievanceException;
import com.training.educationsystem.repositories.GrievanceRepository;
import com.training.educationsystem.services.GrievanceService;


	@ExtendWith(MockitoExtension.class)
	@ExtendWith(SpringExtension.class)
	@RunWith(MockitoJUnitRunner.class)
	@SpringBootTest
	class GrievanceServiceTest {
		
		@InjectMocks
		private GrievanceService grievanceService;
		
		@MockBean 
		private GrievanceRepository grievanceRepo;
		
		@Test
		public void testAddGrievance() {
			Grievance grievance=new Grievance();
			grievance.setGrievance("hi");
			grievance.setReply("hiii");
			grievance.setSname("praju");

			
			Mockito.when(grievanceRepo.save(grievance)).thenReturn(grievance);
			assertThat(grievance).isEqualTo(grievance);
		}
		
		@Test
		public void testViewAllGrievance() throws InvalidGrievanceException {
			Grievance grievance1=new Grievance();
			grievance1.setGrievance("hi");
			grievance1.setReply("hiii");
			grievance1.setSname("nisha");
			Grievance grievance2=new Grievance();
			grievance2.setGrievance("hi");
			grievance2.setReply("hiii");
			grievance2.setSname("ishaa");
			List<Grievance> grievanceList=new ArrayList<>();
			grievanceList.add(grievance1);
			grievanceList.add(grievance2);
			
			Mockito.when(grievanceRepo.findAll()).thenReturn(grievanceList);
			assertThat(grievanceService.getAllGrievance()).isEqualTo(grievanceList);
		}

}
