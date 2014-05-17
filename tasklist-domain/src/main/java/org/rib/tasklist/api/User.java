package org.rib.tasklist.api;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class User extends ManagedItem {

	private static final long serialVersionUID = 1L;
	private final String name;

	public User(String name, String createdBy) {
		super(createdBy);
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
