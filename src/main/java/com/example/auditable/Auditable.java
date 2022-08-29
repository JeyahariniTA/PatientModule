package com.example.auditable;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable {

	@CreatedBy
	@Column(name = "createdBy", columnDefinition = " varchar(50) default  'Harini'", nullable = false, updatable = false)
	private String createdBy;

	@LastModifiedBy
	@Column(name = "updatedBy", columnDefinition = "varchar(50) default  'Harini'", nullable = false)
	private String updatedBy;

	@CreationTimestamp
	@Column(name = "createdOn", nullable = false, updatable = false)
	private LocalDateTime createdOn;

	@UpdateTimestamp
	@Column(name = "updatedOn", nullable = false, updatable = true)
	private LocalDateTime updatedOn;

	@Version
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
