package com.desarrolloweb.spring.app.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import org.springframework.data.annotation.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Audit implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "created_by", nullable = false, updatable = false, length = 50)
	@CreatedBy
	private String createdBy;

	@Column(name = "updated_by", nullable = false, updatable = true, length = 50)
	@LastModifiedBy
	private String lastModifiedBy;

	@Column(name = "created_on", nullable = false, updatable = false)
	@CreatedDate
	private Timestamp createdDate;

	@Column(name = "updated_on", nullable = false, updatable = true)
	@LastModifiedDate
	private Timestamp lastModifiedDate;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
}
