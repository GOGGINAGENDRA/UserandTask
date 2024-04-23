package com.taskForUser.repositorypack;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskForUser.entitypack.Users;
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	
	Optional<Users> findByEmail(String email);


}
