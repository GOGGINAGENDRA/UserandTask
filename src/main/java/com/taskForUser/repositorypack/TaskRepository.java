package com.taskForUser.repositorypack;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskForUser.entitypack.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {


	List<Task> findAllByUsersId(long userid);

}
