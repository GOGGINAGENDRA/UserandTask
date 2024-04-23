package com.taskForUser.serviceimplpack;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.taskForUser.entitypack.Task;
import com.taskForUser.entitypack.Users;
import com.taskForUser.exceptionpack.ApiException;
import com.taskForUser.exceptionpack.TaskNotFound;
import com.taskForUser.exceptionpack.UserNotFound;
import com.taskForUser.payloadpack.TaskDto;
import com.taskForUser.repositorypack.TaskRepository;
import com.taskForUser.repositorypack.UserRepository;
import com.taskForUser.servicrpack.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public TaskDto taskDto(long userid, TaskDto taskDto) {
		Users users = userRepository.findById(userid)
				.orElseThrow(() -> new UserNotFound(String.format("user id %d not found on Users", userid)));
		Task task = modelMapper.map(taskDto, Task.class);
		task.setUsers(users);
		Task task1 = taskRepository.save(task);
		TaskDto taskDto1 = modelMapper.map(task1, TaskDto.class);
		return taskDto1;
	}

	@Override
	public List<TaskDto> gettaskDto(long userid) {
		userRepository.findById(userid)
				.orElseThrow(() -> new TaskNotFound(String.format("user id %d not found on Task", userid)));
		List<Task> tasks = taskRepository.findAllByUsersId(userid);
		List<TaskDto> getTaskDetails = tasks.stream().map(t -> modelMapper.map(t, TaskDto.class))
				.collect(Collectors.toList());
		return getTaskDetails;
	}

	@Override
	public TaskDto getOneTask(long userid, long taskid) {
		Users user = userRepository.findById(userid)
				.orElseThrow(() -> new TaskNotFound(String.format("user id %d not found on Task", userid)));
		Task task = taskRepository.findById(taskid)
				.orElseThrow(() -> new UserNotFound(String.format("user id %d not found on Task", taskid)));
		if(user.getId()!=task.getUsers().getId()) {
		throw new ApiException(String.format("user id %d is not belongs to task id %d", userid,taskid));	
		}
		return modelMapper.map(task, TaskDto.class);
	}

	@Override
	public void deleteTask(long userid, long taskid) {
		Users user = userRepository.findById(userid)
				.orElseThrow(() -> new TaskNotFound(String.format("user id %d not found on Task", userid)));
		Task task = taskRepository.findById(taskid)
				.orElseThrow(() -> new UserNotFound(String.format("user id %d not found on Task", taskid)));
		if(user.getId()!=task.getUsers().getId()) {
			throw new ApiException(String.format("user id %d is not belongs to task id %d", userid,taskid));	
			}
		taskRepository.deleteById(taskid);
	}

}
