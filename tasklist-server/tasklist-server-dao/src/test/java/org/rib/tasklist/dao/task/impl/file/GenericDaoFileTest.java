package org.rib.tasklist.dao.task.impl.file;

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
import org.rib.tasklist.ctrl.TasklistException;
import org.rib.tasklist.dao.task.GenericDao;

@RunWith(JUnit4.class)
public class GenericDaoFileTest {
	
	private User user;
    private Path rootPath = Paths.get("target", this.getClass().getSimpleName().toLowerCase());
    
    private GenericDao<Task> taskDao;
    private GenericDao<User> userDao;
    
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
	@Before
	public void before() throws IOException {		
		Files.createDirectories(rootPath);
		FileUtils.cleanDirectory(rootPath.toFile());
		user = new User("hossa", "system");		
		taskDao = new GenericDaoFile<Task>(rootPath);
		userDao = new GenericDaoFile<User>(rootPath);
	}
	
	
	@Test
	public void createAndGetAndDeleteTask() {
		
		Task task = new Task(TaskSeverity.HIGH, this.user);
		taskDao.create(task);
		
		String id = task.getId();		
		Task reloadedTask = taskDao.get(id);
		
		assertThat(task.toString(), is(reloadedTask.toString()));
		
		taskDao.delete(id);
		
		expectedException.expect(TasklistException.class);
		expectedException.expectMessage("Can not read");
		taskDao.get(id);
	}

	@Test
	public void createAndGetAllTask() {
		
		Task task = new Task(TaskSeverity.HIGH, this.user);		
		
		taskDao.create(task);
		taskDao.create(task);
		taskDao.create(task);
				
		List<Task> tasks = taskDao.getAll();
		assertThat(tasks.size(), is(3));
	}
	
	@Test
	public void createAndGetAndDeleteUser() {
		
		User user = new User("hossa", "system");
		userDao.create(user);
		
		String id = user.getId();		
		User reloadedUser = userDao.get(id);
		
		assertThat(user.toString(), is(reloadedUser.toString()));
		
		userDao.delete(id);
		
		expectedException.expect(TasklistException.class);
		expectedException.expectMessage("Can not read");
		userDao.get(id);
	}

}

