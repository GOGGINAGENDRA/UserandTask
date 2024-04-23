package com.taskForUser.serviceimplpack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskForUser.entitypack.Users;
import com.taskForUser.payloadpack.UserDto;
import com.taskForUser.repositorypack.UserRepository;
import com.taskForUser.servicrpack.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto createUser(UserDto UserDto) {

		Users user = userDtoToEntity(UserDto);
		System.out.println(user.toString());
		Users saved = userRepository.save(user);
		System.out.println(saved.toString());
		UserDto dto = entityTouserDto(saved);
		return dto;
	}

	private Users userDtoToEntity(UserDto userDto) {
		Users usersave = new Users();
		usersave.setEmail(userDto.getEmail());
		usersave.setName(userDto.getName());
		usersave.setPassword(userDto.getPassword());
		return usersave;

	}

	private UserDto entityTouserDto(Users used) {
		UserDto userDto = new UserDto();
		userDto.setId(used.getId());
		userDto.setEmail(used.getEmail());
		userDto.setName(used.getName());
		userDto.setPassword(used.getPassword());
		return userDto;
	}
}
