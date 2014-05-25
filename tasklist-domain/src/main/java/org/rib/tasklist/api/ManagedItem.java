package org.rib.tasklist.api;

import java.io.Serializable;

import org.joda.time.LocalDateTime;
import org.springframework.util.Assert;

public class ManagedItem implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private final String createdAt;
	private String changedAt;
	private String createdBy;

	public ManagedItem(String createdBy) {				
		Assert.notNull(createdBy, "'createdBy' must not be null!");
		
		this.createdAt = LocalDateTime.now().toString();
	    this.changedAt = this.createdAt;
		this.createdBy = createdBy;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}
	
	public String getCreatedAt() {
		return this.createdAt;
	}
	
	public String getChangedAt() {
		return this.changedAt;
	}
	
	public String getCreatedBy() {
		return this.createdBy;
	}
	
	protected void changed() {
		this.changedAt = LocalDateTime.now().toString();
	}	
}
