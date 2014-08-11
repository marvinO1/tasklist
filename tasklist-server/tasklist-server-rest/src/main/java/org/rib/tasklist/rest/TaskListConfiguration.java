package org.rib.tasklist.rest;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.rib.tasklist.api.Task;
import org.rib.tasklist.api.User;
import org.rib.tasklist.dao.task.GenericDao;
import org.rib.tasklist.dao.task.StorageType;
import org.rib.tasklist.dao.task.impl.file.GenericDaoFile;
import org.rib.tasklist.services.api.TaskListService;
import org.rib.tasklist.services.impl.TaskListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration

public class TaskListConfiguration {

	@Autowired
	private Environment env;
	
	@Bean
	public GenericDao<Task> taskDao() {
		StorageType storage = getStorageType();
		switch (storage) {
		case FILE:
			return getFileTaskDao();
		case NEO4J:
			return getNeo4JTaskDao();
		default:
			throw new IllegalStateException("Given storage=" + storage + " is not known!");
		}
	}
	
	@Bean
	public GenericDao<User> userDao() {
		StorageType storage = getStorageType();
		switch (storage) {
		case FILE:
			return getFileUserDao();
		case NEO4J:
			return getNeo4JUserDao();
		default:
			throw new IllegalStateException("Given storage=" + storage + " is not known!");
		}
	}
	
	@Bean
	public TaskListService taskListService() {
		return new TaskListServiceImpl(taskDao(), userDao());
	}
	
	protected GenericDao<Task> getNeo4JTaskDao() {
		return new GenericDaoFile<Task>(getFileRootPath().resolve("tasks"));
	}
	
	protected GenericDao<User> getNeo4JUserDao() {
		return new GenericDaoFile<User>(getFileRootPath().resolve("tasks"));
	}
	
	protected GenericDao<Task> getFileTaskDao() {
		return new GenericDaoFile<Task>(getFileRootPath().resolve("tasks"));
	}
	
	protected GenericDao<User> getFileUserDao() {
		return new GenericDaoFile<User>(getFileRootPath().resolve("users"));
	}
		
	protected Path getFileRootPath() {
		return Paths.get(this.env.getProperty("storage.file.rootpath", "."));
	}
	
	protected StorageType getStorageType() {
		return StorageType.valueOf(this.env.getProperty("storage.type", "file").toUpperCase());
	}	
}
