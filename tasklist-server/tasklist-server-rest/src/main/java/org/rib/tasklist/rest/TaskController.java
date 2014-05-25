package org.rib.tasklist.rest;

import org.rib.tasklist.api.Task;
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
@RequestMapping("/tasklist/tasks")
public class TaskController {

	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
	
	@Autowired
	TaskListService service;
	 
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody Task createTask(@PathVariable Task task) {   	
	   logger.info("createTask, task={}", task);
	   return this.service.createTask(task);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public @ResponseBody Task getTask(@PathVariable String id) {   	
	   logger.info("getTask, id={}", id);
	   return this.service.getTask(id);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public @ResponseBody void deleteTask(@PathVariable String id) {   	
	   logger.info("deleteTask, id={}", id);
	   this.service.removeTask(id);
	}
}
