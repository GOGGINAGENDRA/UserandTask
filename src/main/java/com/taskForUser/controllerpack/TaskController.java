package com.taskForUser.controllerpack;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskForUser.payloadpack.TaskDto;
import com.taskForUser.servicrpack.TaskService;

@RestController
@RequestMapping("/Api/auth")
public class TaskController {
	@Autowired
	private TaskService taskService;

	@PostMapping("/{userid}/task")
	public ResponseEntity<TaskDto> taskDto(@PathVariable(name = "userid") long userid, @RequestBody TaskDto taskDto) {
		return new ResponseEntity<>(taskService.taskDto(userid, taskDto), HttpStatus.CREATED);
	}

	@GetMapping("/{userid}/getTask")
	public ResponseEntity<List<TaskDto>> gettaskDto(@PathVariable(name = "userid") long userid) {
		return new ResponseEntity<>(taskService.gettaskDto(userid), HttpStatus.OK);
	}

	@GetMapping("/{userid}/getTask/{taskid}")
	public ResponseEntity<TaskDto> getOneTask(@PathVariable(name = "userid") long userid,
			@PathVariable(name = "taskid") long taskid) {
		return new ResponseEntity<>(taskService.getOneTask(userid, taskid), HttpStatus.OK);
	}

	@DeleteMapping("/{userid}/deleteTask/{taskid}")
	public ResponseEntity<String> deleteTask(@PathVariable(name = "userid") long userid,
			@PathVariable(name = "taskid") long taskid) {
		taskService.deleteTask(userid, taskid);
		return new ResponseEntity<>("task record deleted successfully ", HttpStatus.OK);
		 
	}
}
