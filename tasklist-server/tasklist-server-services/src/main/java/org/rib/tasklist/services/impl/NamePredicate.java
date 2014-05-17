package org.rib.tasklist.services.impl;

import org.rib.tasklist.api.User;

import com.google.common.base.Predicate;

class NamePredicate implements Predicate<User> {

	private final String name;
	
	public NamePredicate(String name) {
		this.name = name;
	}

	@Override
	public boolean apply(User input) {
		return name.equalsIgnoreCase(input.getName());
	}

}
