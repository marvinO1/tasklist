package org.rib.tasklist.services.api;

import java.util.List;

import org.rib.tasklist.api.Task;
import org.rib.tasklist.api.User;

public interface TaskListService {

	/**
	 * Creates the given user.
	 * @param user
	 * @return Created user, if user already exists the existing user will be returned.
	 */
	User createUser(User user);
	List<User> getAllUsers();
	User getUser(String id);
	void removeUser(User user);
	
	Task createTask(Task task);
	Task updateTask(Task task);
	void removeTask(Task task);
	
	List<Task> getAllTasks();
	List<Task> getAllTasks(User user);
	List<Task> assingUser(List<Task> tasks, User user);
	
}
