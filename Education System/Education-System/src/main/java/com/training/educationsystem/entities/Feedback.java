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
@Table(name = "Feedback")
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "sname")
	private String sname;
	@Column(name = "feedback")
	private String feedback;
	@Column(name = "reply")
	private String reply;
	
	/**
	 * Empty Constructor.
	 */
	public Feedback() {

	}
	/**
	 * 
	 * @param id
	 * @param sname
	 * @param feedback
	 * @param reply
	 */
	public Feedback(final int id, final String sname, final String feedback, final String reply) {
		super();
		this.id = id;
		this.sname = sname;
		this.feedback = feedback;
		this.reply = reply;
	}

	public int getId() {
		return id;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(final String sname) {
		this.sname = sname;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(final String feedback) {
		this.feedback = feedback;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(final String reply) {
		this.reply = reply;
	}

	@Override
	public String toString() {
		return "Feedback [id=" + id + ",  sname=" + sname + ", feedback=" + feedback + ", reply=" + reply + "]";
	}

}

// reply=" + reply + ",