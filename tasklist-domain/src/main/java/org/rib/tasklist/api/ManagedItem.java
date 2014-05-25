package org.rib.tasklist.api;

import java.io.Serializable;

import org.joda.time.LocalDateTime;
import org.springframework.util.Assert;

public class ManagedItem implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private final LocalDateTime createdAt;
	private LocalDateTime changedAt;
	private String createdBy;

	public ManagedItem(String createdBy) {				
		Assert.notNull(createdBy, "'createdBy' must not be null!");
		
		this.createdAt = LocalDateTime.now();
		this.changedAt = this.createdAt;
		this.createdBy = createdBy;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}
	
	public LocalDateTime getCreatedAt() {
		return this.createdAt;
	}
	
	public LocalDateTime getChangedAt() {
		return this.changedAt;
	}
	
	public String getCreatedBy() {
		return this.createdBy;
	}
	
	protected void changed() {
		this.changedAt = LocalDateTime.now();
	}	
}
