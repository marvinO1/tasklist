package org.rib.tasklist.rest;

import java.util.List;

import org.rib.tasklist.api.User;
import org.rib.tasklist.services.api.TaskListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/tasklist/users")
public class UserListController {

	private static final Logger logger = LoggerFactory.getLogger(UserListController.class);
	
	@Autowired
	TaskListService service;
	 
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody User createUser(@PathVariable User user) {   	
	   logger.info("createUser, user={}", user);
	   return this.service.createUser(user);
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