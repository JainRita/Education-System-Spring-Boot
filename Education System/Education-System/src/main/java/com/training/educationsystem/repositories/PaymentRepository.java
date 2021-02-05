package com.training.educationsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.educationsystem.entities.Payment;
/**
 * 
 * @author aniket
 *
 */
public interface PaymentRepository extends JpaRepository<Payment,Integer> {

}
