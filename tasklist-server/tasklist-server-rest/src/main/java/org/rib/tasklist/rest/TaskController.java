package org.rib.tasklist.rest;

import java.util.List;

import org.rib.tasklist.api.Task;
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
@RequestMapping("/tasklist/tasks")
public class TaskController {

	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
	
	@Autowired
	TaskListService service;
	 
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<Task> createTask(@RequestBody Task task, UriComponentsBuilder builder) {   	
	   logger.info("createTask, task={}", task);
	   
	   Task createdTask = this.service.createTask(task);	   
	   
	   HttpHeaders headers = new HttpHeaders();
	   headers.setLocation(builder.path("/tasklist/tasks/{id}").buildAndExpand(createdTask.getId()).toUri());
	   return new ResponseEntity<Task>(createdTask, headers, HttpStatus.CREATED); 
	}

	@RequestMapping(method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<Task> updateTask(@RequestBody Task task, UriComponentsBuilder builder) {   	
	   logger.info("updateTask, task={}", task);
	   
	   Task updatedTask = this.service.updateTask(task);	   
	   
	   HttpHeaders headers = new HttpHeaders();
	   headers.setLocation(builder.path("/tasklist/tasks/{id}").buildAndExpand(updatedTask.getId()).toUri());
	   return new ResponseEntity<Task>(updatedTask, headers, HttpStatus.ACCEPTED); 
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody List<Task> getTasks() {   	
	   logger.info("getTasks");
	   return this.service.getAllTasks();
	}
		
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public @ResponseBody Task getTask(@PathVariable String id) {   	
	   logger.info("getTask, id={}", id);
	   return this.service.getTask(id);
	}

	@RequestMapping(value="/by/user/{name}", method=RequestMethod.GET)
	public @ResponseBody List<Task> getTaskForUser(@PathVariable String name) {   	
	   logger.info("getTaskForUser, name={}", name);	   
	   return this.service.getAllTasks(new User(name, null));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public @ResponseBody void deleteTask(@PathVariable String id) {   	
	   logger.info("deleteTask, id={}", id);
	   this.service.removeTask(id);
	}	
	
	@RequestMapping(value="/by/user/{name}", method=RequestMethod.DELETE)
	public @ResponseBody void deleteAllTaskForUser(@PathVariable String name) {   	
	   logger.info("deleteAllTaskForUser, name={}", name);
	   // TODO ....
	}	
}
