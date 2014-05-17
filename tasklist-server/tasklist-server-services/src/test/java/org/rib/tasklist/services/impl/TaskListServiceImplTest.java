package org.rib.tasklist.services.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.rib.tasklist.api.Task;
import org.rib.tasklist.api.TaskSeverity;
import org.rib.tasklist.api.User;
import org.rib.tasklist.dao.task.GenericDao;
import org.rib.tasklist.dao.task.impl.file.GenericDaoFile;
import org.rib.tasklist.services.api.TaskListService;

import com.google.common.collect.Lists;

@RunWith(JUnit4.class)
public class TaskListServiceImplTest {

	
	private User user;
    private Path userRootPath = Paths.get("target", User.class.getSimpleName().toLowerCase());
    private Path taskRootPath = Paths.get("target", Task.class.getSimpleName().toLowerCase());
    
    private GenericDao<Task> taskDao;
    private GenericDao<User> userDao;
    private TaskListService service;
    
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
	@Before
	public void before() throws IOException {		
		Files.createDirectories(userRootPath);
		FileUtils.cleanDirectory(userRootPath.toFile());
		
		Files.createDirectories(taskRootPath);
		FileUtils.cleanDirectory(taskRootPath.toFile());
		
		user = new User("hossa", "system");		
		taskDao = new GenericDaoFile<Task>(taskRootPath);
		userDao = new GenericDaoFile<User>(userRootPath);
		service = new TaskListServiceImpl(taskDao, userDao);
	}
	
	@Test
	public void basicFunctions() {
		User testUser = service.createUser(user);		
		
		List<Task> tasks = service.getAllTasks(testUser);
		assertThat(tasks.isEmpty(), is(true));
		
		Task testTask = new Task(TaskSeverity.HIGH, testUser);
		service.createTask(testTask);
		
		// task not assigned to any user ...
		tasks = service.getAllTasks(testUser);
		assertThat(tasks.isEmpty(), is(true));
		
		// now we assign the testUser to the task.
		service.assingUser(Lists.newArrayList(testTask), testUser);
		
		// should be found now ...
		tasks = service.getAllTasks(testUser);
		assertThat(testTask.toString(), is(tasks.get(0).toString()));		
	}
	
}
