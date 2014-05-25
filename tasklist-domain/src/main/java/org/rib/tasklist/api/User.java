package org.rib.tasklist.api;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class User extends ManagedItem {

	private static final long serialVersionUID = 1L;
	private String name;

	public User() {
		
	}

	public User(String name, String createdBy) {
		setName(name);
		setCreatedBy(createdBy);
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
