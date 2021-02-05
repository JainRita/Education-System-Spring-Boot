package com.training.educationsystem.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.educationsystem.entities.Progress;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
class ProgressRepositoryTest {

	@Autowired
	private ProgressRepository progressRepository;

	public Progress viewProgress() {
		Progress progress = new Progress();
		progress.setCompletedHours(10);
		return progress;

	}

	@Test
	@Rollback(false)
	public void testAddProgress() {
		Progress p = viewProgress();
		Progress actual = progressRepository.save(p);
		Progress expected = progressRepository.getOne(actual.getProgressId());
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testViewProgress() {
		Progress p = viewProgress();
		Progress expected = progressRepository.save(p);
		Progress actual = progressRepository.getOne(expected.getProgressId());
		assertNotNull(progressRepository.getOne(actual.getProgressId()));
		assertThat(actual.getProgressId()).isEqualTo(expected.getProgressId());
		assertThat(actual.getCompletedHours()).isEqualTo(expected.getCompletedHours());
	}

	@Test
	public void testViewAllProgress() {
		List<Progress> progressList = progressRepository.findAll();
		assertNotNull(progressList);
		assertThat(progressList).size().isGreaterThan(0);
	}

}
