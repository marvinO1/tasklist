package org.rib.tasklist.services.impl;

import java.util.List;

import org.rib.tasklist.api.Task;
import org.rib.tasklist.api.User;
import org.rib.tasklist.ctrl.DomainException;
import org.rib.tasklist.dao.task.GenericDao;
import org.rib.tasklist.services.api.TaskListService;

import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;


public class TaskListServiceImpl implements TaskListService {
	
	private final GenericDao<Task> taskDao;
	private final GenericDao<User> userDao;

	public TaskListServiceImpl(GenericDao<Task> taskDao, GenericDao<User> userDao) {
		this.taskDao = taskDao;
		this.userDao = userDao;
	}

	@Override
	public User createUser(User user) {
		
		// when id is provided it does not make sence to create user again!
		String id = user.getId();
		if (id != null) {
			throw new DomainException(String.format("Given user contains already an id=%s!", id));
		}
		
		Optional<User> existingUser = Iterables.tryFind(userDao.getAll(), new NamePredicate(user.getName()));
		if (existingUser.isPresent()) {
			return existingUser.get();
		}
		return  userDao.create(user);	
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAll();
	}

	@Override
	public User getUser(String id) {
		return userDao.get(id);
	}

	@Override
	public void removeUser(String userId) {
		User user = getUser(userId);
		int numberOfAssignedTasks = getAllTasks(user).size();
		if (numberOfAssignedTasks > 0) {
			throw new DomainException(String.format("user=%s can not be deleted since there are %d tasks assigned!", user, numberOfAssignedTasks));
		}
		userDao.delete(userId);		
	}

	@Override
	public Task createTask(Task task) {
		validateAssignedUser(task);
		return taskDao.create(task);
	}

	@Override
	public Task updateTask(Task task) {
		validateAssignedUser(task);
		return taskDao.update(task);
	}

	@Override
	public void removeTask(String taskId) {
		taskDao.delete(taskId);		
	}
	
	@Override
	public Task getTask(String id) {
		return taskDao.get(id);
	}

	@Override
	public List<Task> getAllTasks() {
        return taskDao.getAll();		
	}

	@Override
	public List<Task> getAllTasks(User user) {
		return Lists.newArrayList(Iterables.filter(taskDao.getAll(), new AssignedUserPredicate(user)));
	}

	@Override
	public List<Task> assingUser(List<Task> tasks, User userToAssign) {
		if (!userDao.exists(userToAssign.getId())) {
			throw new DomainException(String.format("new assigned user=%s does not exist!", userToAssign));
		}
		
		// TODO, here we could use function!
		for (Task task : tasks) {
			task.setAssignedTo(userToAssign);
			taskDao.update(task);
		}		
		return tasks;
	}
			
	protected void validateAssignedUser(Task task) {		
		User assignedUser = task.getAssignedTo();
		
		if (assignedUser == null) {
			// no further check required
			return;
		}
				
		if (userDao.exists(assignedUser.getId())) {
			return;
		}
		
		throw new DomainException(String.format("Assigned user=%s does not exist!", assignedUser));
	}

}

