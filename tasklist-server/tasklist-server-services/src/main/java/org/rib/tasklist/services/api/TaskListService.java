package
org.rib.tasklist.services.api;

import java.util.List;

import org.rib.tasklist.api.Task;
import org.rib.tasklist.api.User;
import org.rib.tasklist.ctrl.TransientException;

public interface TaskListService {

	/**
	 * Creates the given user.
	 * @param user
	 * @return Created user, if user already exists the existing user will be returned.
	 */
	User createUser(User user);
	
	/**
	 * Lists all existing users.
	 * 
	 * @return List<User> empty if no user exists. 
	 */
	List<User> getAllUsers();
	
	/**
	 * Reads user.
	 * @param id
	 * @return User or null if not exists
	 */
	User getUser(String id);
	
	/**
	 * Removes user.
	 * @param userId id of the user to remove. If user does not exists no action takes place.
	 */
	void removeUser(String userId);
	
	
	/**
	 * Create the given task.
	 * @param task
	 * @return task created.
	 */
	Task createTask(Task task);
	
	/**
	 * Update task.
	 * @param task
	 * @return Task updated.
	 */
	Task updateTask(Task task);
	
	/**
	 * Removes task.
	 * 
	 * @param taskId id of the task to remove. If task does not exists no action takes place.
	 */
	void removeTask(String taskId);
	
	Task getTask(String id);
	List<Task> getAllTasks();
	List<Task> getAllTasks(User user);
	List<Task> assingUser(List<Task> tasks, User user);	
}
