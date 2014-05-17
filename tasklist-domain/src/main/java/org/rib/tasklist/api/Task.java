package org.rib.tasklist.api;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.Assert;

public class Task extends ManagedItem {

	private static final long serialVersionUID = 1L;
	private TaskStatus status;
	private TaskSeverity severity;
	
	// when user assigned user must exist!
	private User assignedTo;
	private Description description;
	
	public Task(TaskSeverity severity, User createdBy) {
		super(createdBy.getName());			
		Assert.notNull(severity, "'severity' must not be null!");

		this.status = TaskStatus.NEW;
        this.severity = severity;
	}
	
    public void setStatus(TaskStatus status) {
    	this.status = status;
    	changed();
    }
    
    public TaskStatus getStatus() {
    	return this.status;
    }
	       
	public void setSeverity(TaskSeverity severity) {
	    this.severity = severity;
		changed();
	}
	
	public TaskSeverity getSeverity() {
		return this.severity;
	}
	
	public void setDescription(Description description) {
		this.description = description;
		changed();
	}
	
	public Description getDescription() {
	  return this.description;
	}	
	
	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
		changed();		
	}
	
	public User getAssignedTo() {
		return this.assignedTo;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
