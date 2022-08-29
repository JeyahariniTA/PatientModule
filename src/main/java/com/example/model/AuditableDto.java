package com.example.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AuditableDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1429500593292406446L;
	private String createdBy;
	private String updatedBy;
//	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
//	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime createdOn;
//	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
//	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime updatedOn;
	private int rowVersion;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	public int getRowVersion() {
		return rowVersion;
	}

	public void setRowVersion(int rowVersion) {
		this.rowVersion = rowVersion;
	}

}
