package com.training.educationsystem.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * 
 * @author aniket.
 *
 */
@Entity
@Table(name = "admin_table")
public class Admin {

	@Id
	@Column(name = "admin_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int adminId; 

	@Column(name = "admin_username")
	private String userName;

	@Column(name = "admin_password")
	private String password;

	@Column(name = "message_id")
	@OneToMany(targetEntity = Message.class)
	private List<Message> messageId;
	
	/**
	 * Empty Constructor.
	 */
	public Admin() {
		super();
	}
	/**
	 * 
	 * @param adminId
	 * @param userName
	 * @param password
	 * @param messageId
	 */
	public Admin(final int adminId, final String userName, final String password, final List<Message> messageId) {
		super();
		this.adminId = adminId;
		this.userName = userName;
		this.password = password;
		this.messageId = messageId;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(final int adminId) {
		this.adminId = adminId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public List<Message> getMessageId() {
		return messageId;
	}

	public void setMessageId(final List<Message> messageId) {
		this.messageId = messageId;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", userName=" + userName + ", password=" + password + ", messageId="
				+ messageId + "]";
	}

}

