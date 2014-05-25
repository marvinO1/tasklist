package org.rib.tasklist.rest;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.rib.tasklist.api.Task;
import org.rib.tasklist.api.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UserTest {

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
		
		User user = new User("berta", "system");		
		
		HttpEntity<User> requestEntity = new HttpEntity<User>(user, headers);
		
		ResponseEntity<User> entity = template.postForEntity("http://localhost:8090/tasklist/users", requestEntity, User.class);
		
		String path = entity.getHeaders().getLocation().getPath();
        System.out.println(path);
        System.out.println(entity.getStatusCode());
        User createdUser = entity.getBody();
        System.out.println("CREATED: " + createdUser);
        
        User reloadedUser = template.getForObject("http://localhost:8080" + path.toString(), User.class);
        System.out.println("RELOADED: " + reloadedUser);
	}
	
	
	@Test
	public void thatUserCanBeRead() {						
		User user = template.getForObject("http://localhost:8080/tasklist/users/1401024414903", User.class);				
        System.out.println(user);        
	}

	@Test
	public void thatUsersCanBeRead() {		
		User[] list = template.getForObject("http://localhost:8080/tasklist/users", User[].class);
		for (User u : list) {
			System.out.println(u);	
		}
	}
	
	@Test
	public void thatTaskCanBeRead() {						
		Task task = template.getForObject("http://localhost:8080/tasklist/tasks/1401024414904", Task.class);				
        System.out.println(task);        
	}	
}
