package com.taskForUser.controllerpack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskForUser.entitypack.JwtAuthResponse;
import com.taskForUser.payloadpack.LoginDto;
import com.taskForUser.payloadpack.UserDto;
import com.taskForUser.security.JWtTokenGenerate;
import com.taskForUser.servicrpack.UserService;

@RestController
@RequestMapping("/auth/api")
public class UserController {
	@Autowired
	private JWtTokenGenerate jWtTokenGenerate;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@PostMapping("/user")
	public ResponseEntity<UserDto> createuser(@RequestBody UserDto userdto) {
		userdto.setPassword(passwordEncoder.encode(userdto.getPassword()));
		return new ResponseEntity<>(userService.createUser(userdto), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> loginUser(@RequestBody LoginDto loginDto) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		if(authentication.isAuthenticated()) {
		String token = jWtTokenGenerate.generateToken(loginDto.getEmail());
		return ResponseEntity.ok(new JwtAuthResponse(token));
		}else {
			throw new UsernameNotFoundException("invalid username");
		}
	}
}
