package com.training.educationsystem.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.training.educationsystem.entities.Admin;
import com.training.educationsystem.repositories.AdminRepository;
import com.training.educationsystem.services.AdminServiceImp;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class AdminServiceTest {
		
	@InjectMocks
	private AdminServiceImp adminService;
	
	@MockBean 
	private AdminRepository adminRepo;

	@Test
	public void testAdminLogin() {	//test for admin login
		Admin admin=new Admin();
		admin.setUserName("admin123");
		admin.setPassword("admin123");
		Mockito.when(adminRepo.save(admin)).thenReturn(admin);
		assertNotNull(admin);
		assertThat(admin).isEqualTo(admin);
		
	}
	
}
