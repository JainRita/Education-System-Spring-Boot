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
@Table(name = "progress_table")
public class Progress {
	@Id
	@Column(name = "progress_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int progressId;

	@Column(name = "completed_hours")
	private int completedHours;
	
	/**
	 * Empty Constructor.
	 */
	public Progress() {
		super();
	}
	/**
	 * 
	 * @param progressId
	 * @param completedHours
	 */
	public Progress(final int progressId, final int completedHours) {
		super();
		this.progressId = progressId;
		this.completedHours = completedHours;
	}

	public int getProgressId() {
		return progressId;
	}

	public void setProgressId(final int progressId) {
		this.progressId = progressId;
	}

	public int getCompletedHours() {
		return completedHours;
	}

	public void setCompletedHours(final int completedHours) {
		this.completedHours = completedHours;
	}

	@Override
	public String toString() {
		return "Progress [progressId=" + progressId + ", completedHours=" + completedHours + "]";
	}

}
