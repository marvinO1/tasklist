package org.rib.tasklist.services.impl;

import com.google.common.base.Predicate;

import org.rib.tasklist.api.Task;
import org.rib.tasklist.api.User;

class AssignedUserPredicate implements Predicate<Task> {

	private final User user;
	
	public AssignedUserPredicate(User user) {
		this.user = user;
	}

	@Override
	public boolean apply(Task input) {
		User assignedUser = input.getAssignedTo();
		if (assignedUser != null) {
			return assignedUser.getId().equalsIgnoreCase(this.user.getId());
		}
		return false;
	}

}
