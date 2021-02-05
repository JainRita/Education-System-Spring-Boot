package com.training.educationsystem.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.educationsystem.entities.Payment;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PaymentRepositoryTest {
	@Autowired
	private PaymentRepository paytestRepo;

	public Payment getPayment() {
		Payment pay = new Payment();
		pay.setCardNumber(2345);
		pay.setCardType("debit");
		pay.setBankName("HDFC");
		pay.setAmount(30000);
		pay.setDescription("good");
		pay.setPaymentDate("29/07/2020");
		return pay;
	}

	@Test
	@Rollback(false)
	public void testAddPayment() {
		Payment payment = getPayment();
		Payment addPay = paytestRepo.save(payment);
		Payment getPay = paytestRepo.getOne(addPay.getTransactionId());
		assertThat(getPay).isEqualTo(addPay);
	}

	@Test
	public void getAllPayment() {
		List<Payment> payList = paytestRepo.findAll();
		assertThat(payList).size().isGreaterThan(0);
	}

}
