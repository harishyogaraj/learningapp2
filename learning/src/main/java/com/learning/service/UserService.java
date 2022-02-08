package com.learning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.learning.dto.Register;

@Service
public interface UserService {

	public Register addUser(Register register);
	public Optional<List<Register>> getAllUsers();
	public Register getUserById(Integer id);
	public String deleteUserById(Integer id);
}
