package com.training.educationsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.educationsystem.entities.Question;

/**
 * Creating QuestionRepository that extends JpaRepository
 * @author HP
 *
 */
public interface QuestionRepository extends JpaRepository<Question, Integer>{}
