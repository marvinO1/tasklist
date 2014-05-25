package org.rib.tasklist.rest;

import java.util.List;

import org.rib.tasklist.api.User;
import org.rib.tasklist.services.api.TaskListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;


@Controller
@RequestMapping("/tasklist/users")
public class UserListController {

	private static final Logger logger = LoggerFactory.getLogger(UserListController.class);
	
	@Autowired
	TaskListService service;

	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<User> createUser(@RequestBody User user, UriComponentsBuilder builder) {   	
	   logger.info("createUser, user={}", user);
	   
	   User createdUser = this.service.createUser(user);
		   
	   HttpHeaders headers = new HttpHeaders();
	   headers.setLocation(builder.path("/tasklist/users/{id}").buildAndExpand(createdUser.getId()).toUri());
	   return new ResponseEntity<User>(createdUser, headers, HttpStatus.CREATED); 
	}	
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody List<User> getUsers() {   	
	   logger.info("getUsers");
	   return this.service.getAllUsers();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public @ResponseBody User getUser(@PathVariable String id) {   	
	   logger.info("getUser, id={}", id);
	   return this.service.getUser(id);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public @ResponseBody void deleteUser(@PathVariable String id) {   	
	   logger.info("deleteUser, id={}", id);
	   this.service.removeUser(id);
	}
}