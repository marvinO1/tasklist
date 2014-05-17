package org.rib.tasklist.rest;

import org.rib.tasklist.api.Task;
import org.rib.tasklist.services.api.TaskListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/task")
public class TaskListController {

	private static final Logger logger = LoggerFactory.getLogger(TaskListController.class);
	
	@Autowired
	TaskListService service;
	  

	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody Task getTask(@RequestParam(value="id", required=true) String id) {   	
	   logger.info("getTask, id={}", id);
	   return this.service.getTask(id);
	}
}
