package com.example.ppmtool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ppmtool.domain.User;
import com.example.ppmtool.exceptions.UsernameExistException;
import com.example.ppmtool.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public User saveUser(User newUser) {
		try {
			
			newUser.setPassword(encoder.encode(newUser.getPassword()));
			newUser.setEmail(newUser.getEmail());
			
			return userRepository.save(newUser);
			
		} catch (Exception e) {
			throw new UsernameExistException("Email already exists");
		}
		
	}

}
