package com.training.educationsystem.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import com.training.educationsystem.entities.Grievance;
import com.training.educationsystem.repositories.GrievanceRepository;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
public class GrievanceRepositoryTest {

	@Autowired
	private GrievanceRepository grievanceRepository;

	public Grievance getGrievance() {
		Grievance grievance = new Grievance();
		grievance.setSname("praju");
		grievance.setGrievance("good");
		return grievance;

	}

	@Test
	@Rollback(false)
	@Order(1)
	public void testAddGrievance() {
		Grievance g = getGrievance();
		Grievance actual = grievanceRepository.save(g);
		Grievance expected = grievanceRepository.getOne(actual.getId());
		assertNotNull(actual);
		assertThat(actual).isEqualTo(expected);
	} //

	@Test
	@Order(2)
	public void testViewGrievance() {
		Grievance gr = getGrievance();
		Grievance expected = grievanceRepository.save(gr);
		Grievance actual = grievanceRepository.getOne(expected.getId());
		assertNotNull(grievanceRepository.getOne(expected.getId()));
		assertThat(actual.getId()).isEqualTo(expected.getId());
	}

	@Test
	@Order(3)
	public void testViewAllGrievance() {
		List<Grievance> grievanceList = new ArrayList<>();
		grievanceList = grievanceRepository.findAll();
		assertNotNull(grievanceList);
		assertThat(grievanceList).size().isGreaterThan(0);
	}

	@Test
	@Order(4)
	public void testUpdateGrievance() {
		Grievance savedGrievance = grievanceRepository.getOne(8);
		savedGrievance.setReply("hello");
		Grievance updatedGrievance = grievanceRepository.save(savedGrievance);
		assertEquals("hello", updatedGrievance.getReply());

	}
}