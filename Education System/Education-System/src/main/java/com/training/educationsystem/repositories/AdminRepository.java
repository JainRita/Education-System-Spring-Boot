package com.training.educationsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.educationsystem.entities.Admin;



/*
 * admin repository extending jpa repository with type as admin 
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer>{

	public Admin findByUserNameAndPassword(String userName, String password);

}
