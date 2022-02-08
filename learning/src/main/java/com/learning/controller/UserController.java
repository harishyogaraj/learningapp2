package com.learning.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.Register;
import com.learning.exception.AlreadyExistException;
import com.learning.exception.IdNotFoundException;
import com.learning.repo.UserRepo;
import com.learning.service.UserService;

@RestController
@RequestMapping
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepo userRepo;
	
	@PostMapping("/register")
	public ResponseEntity<?> addUser(@RequestBody Register register) throws AlreadyExistException
	{
	
		Register result=	userService.addUser(register);
		System.out.println(result);
		return ResponseEntity.status(201).body(result);
	}
	
	@GetMapping("users/{id}")
	public ResponseEntity<?> getUserById(@Valid@PathVariable("id") Integer id) throws IdNotFoundException
	{
		Register register= userService.getUserById(id);
		return ResponseEntity.ok(register);
	}
	
	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers()
	{
		Optional<List<Register>> optional= userService.getAllUsers();
		
		if(optional.isEmpty())
		{
			Map<String,String> map=new HashMap<>();
			map.put("message", "no records found");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		return ResponseEntity.ok(optional.get());
	}
	
	@DeleteMapping("/users/delete{id}")
	public ResponseEntity<?> deleteStudentById(@Valid@PathVariable("id") Integer id)
	{
		String res= userService.deleteUserById(id);
		if(res.equals("success"))
		{
			Map<String, String> map=new HashMap<>();
			map.put("message", "deleted successfuly");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(map);
		}
		return null;
	}
	
	@PutMapping("/users/update{id}")
	public ResponseEntity<?> updateUser(@RequestBody Register register)
	{
	
		Register result =userService.addUser(register);
	//	Register result=	userService.addUser(register);
		System.out.println(result);
		return ResponseEntity.status(201).body(result);
	}
	
	@PostMapping("/users/authenticate")
	public ResponseEntity<?> check(@RequestBody Register register)
	{
		Map<String, String> map=new HashMap<>();
		if(userRepo.existsByEmailAndPassword(register.getEmail(),register.getPassword()))
		{
			map.put("message", "success");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(map);

	}


}
