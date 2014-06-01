package org.rib.tasklist.rest;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.rib.tasklist.api.Task;
import org.rib.tasklist.api.TaskSeverity;
import org.rib.tasklist.api.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TaskTest {

	private HttpHeaders headers = new HttpHeaders();	
	private RestTemplate template;
	
	@Before
	public void before() {
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    template = new RestTemplate();
	    
	    System.out.println("Assigned error handler=" + template.getErrorHandler());
	}
	
	@Test
	public void thatUserCanBeAddedAndRead() {		
		
		// get user
		User user = template.getForObject("http://localhost:8080/tasklist/users/1401024414903", User.class);
		
		Task newTask = new Task(TaskSeverity.MEDIUM, user);
		HttpEntity<Task> newEntity = new HttpEntity<Task>(newTask, headers);
		
		ResponseEntity<Task> entity = template.postForEntity("http://localhost:8080/tasklist/tasks", newEntity, Task.class);
		
		String path = entity.getHeaders().getLocation().getPath();
        System.out.println(path);
        System.out.println(entity.getStatusCode());
        Task createdTask = entity.getBody();
        System.out.println("CREATED: " + createdTask);
        
        Task reloadedTask = template.getForObject("http://localhost:8080" + path.toString(), Task.class);
        System.out.println("RELOADED: " + reloadedTask);
	}
	
	@Test
	public void thatTaskCanBeRead() {						
		Task task = template.getForObject("http://localhost:8080/tasklist/tasks/1401024414904", Task.class);				
        System.out.println(task);        
	}	
}
