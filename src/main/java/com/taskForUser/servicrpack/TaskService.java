package com.taskForUser.servicrpack;

import java.util.List;

import com.taskForUser.payloadpack.TaskDto;

public interface TaskService {
	public TaskDto taskDto(long userid, TaskDto TaskDto);
	public List<TaskDto> gettaskDto(long userid);
	public TaskDto getOneTask(long userid,long taskid);
	public void deleteTask(long userid,long taskid);
}
