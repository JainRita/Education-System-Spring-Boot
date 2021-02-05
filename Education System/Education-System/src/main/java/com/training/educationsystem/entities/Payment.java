package com.training.educationsystem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * @author aniket.
 *
 */
@Entity
@Table(name = "payment_table")
public class Payment {
	@Id
	@Column(name = "transaction_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int transactionId;

	@Column(name = "card_number", nullable = false)
	private int cardNumber;

	@Column(name = "card_type", nullable = false)
	private String cardType;

	@Column(name = "bank_name", nullable = false)
	private String bankName;

	@Column(name = "amount", nullable = false)
	private double amount;

	@Column(name = "description", nullable = true)
	private String description;

	@Column(name = "payment_date", nullable = false)
	private String paymentDate;
	
	/**
	 * Empty Constructor.
	 */
	public Payment() {
		super();
	}
	/**
	 * 
	 * @param transactionId
	 * @param cardNumber
	 * @param cardType
	 * @param bankName
	 * @param amount
	 * @param description
	 * @param paymentDate
	 */
	public Payment(final int transactionId, final int cardNumber, final String cardType, final String bankName, final double amount,
			final String description, final String paymentDate) {
		super();
		this.transactionId = transactionId;
		this.cardNumber = cardNumber;
		this.cardType = cardType;
		this.bankName = bankName;
		this.amount = amount;
		this.description = description;
		this.paymentDate = paymentDate;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(final int transactionId) {
		this.transactionId = transactionId;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(final int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(final String cardType) {
		this.cardType = cardType;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(final String bankName) {
		this.bankName = bankName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(final double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(final String paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Override
	public String toString() {
		return "Payment [transactionId=" + transactionId + ", cardNumber=" + cardNumber + ", cardType=" + cardType
				+ ", bankName=" + bankName + ", amount=" + amount + ", description=" + description + ", paymentDate="
				+ paymentDate + "]";
	}

}
