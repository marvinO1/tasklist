package org.rib.tasklist.rest;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.rib.tasklist.api.Task;
import org.rib.tasklist.api.User;
import org.rib.tasklist.dao.task.GenericDao;
import org.rib.tasklist.dao.task.impl.file.GenericDaoFile;
import org.rib.tasklist.services.api.TaskListService;
import org.rib.tasklist.services.impl.TaskListServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskListConfiguration {

	private final Path rootPath = Paths.get(".");
		
	@Bean
	public GenericDao<Task> taskDao() {
		return new GenericDaoFile<Task>(rootPath.resolve("tasks"));
	}
	
	@Bean
	public GenericDao<User> userDao() {
		return new GenericDaoFile<User>(rootPath.resolve("users"));
	}
	
	@Bean
	public TaskListService taskListService() {
		return new TaskListServiceImpl(taskDao(), userDao());
	}
}
