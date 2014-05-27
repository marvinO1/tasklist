package
org.rib.tasklist.services.api;

import java.util.List;

import org.rib.tasklist.api.ManagedItem;
import org.rib.tasklist.api.Task;
import org.rib.tasklist.api.User;
import org.rib.tasklist.ctrl.TasklistException;

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
	
	/** 
	 * @param id
	 * @return Task referencd by id.
	 * @throws TasklistException in case the {@link ManagedItem} can not be found.
	 */
	Task getTask(String id);
	
	/**
	 * @return List<Task> of all found {@link Task}, never null.
	 */
	List<Task> getAllTasks();
	
    /**
     * 	
     * @param user
     * @return List<Task> of all found {@link Task} referenced by {@link User}, never null.
     */
	List<Task> getAllTasks(User user);
	
	/**
	 * Assign given tasks to given user.
	 * @param tasks
	 * @param user
	 * @return @return List<Task> assigned tasks.
	 */
	List<Task> assingUser(List<Task> tasks, User user);	
}
