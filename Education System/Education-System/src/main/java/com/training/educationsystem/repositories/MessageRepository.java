package com.training.educationsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.educationsystem.entities.Message;

/*
 * message repository extending jpa repository with type as admin 
 */
@Repository
public interface MessageRepository extends JpaRepository<Message,Integer>{

}
