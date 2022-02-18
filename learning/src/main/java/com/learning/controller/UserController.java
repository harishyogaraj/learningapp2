package com.learning.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.EROLE;
import com.learning.dto.Register;
import com.learning.dto.Role;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.payload.request.LoginRequest;
import com.learning.payload.request.SignupRequest;
import com.learning.payload.response.JwtResponse;
import com.learning.payload.response.MessageResponse;
import com.learning.repo.RoleRepo;
import com.learning.repo.UserRepo;
import com.learning.security.jwt.JwtUtils;
import com.learning.security.services.UserDetailsImpl;
import com.learning.service.UserService;



@RestController
@RequestMapping("/api/auth")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	RoleRepo roleRepo;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest)
	{
		Authentication authentication=authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
						loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt=jwtUtils.generateToken(authentication);
		UserDetailsImpl userDetailsImpl=(UserDetailsImpl) authentication.getPrincipal();
		
		List<String> roles =userDetailsImpl.getAuthorities()
				.stream()
				.map(i->i.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtResponse(jwt,userDetailsImpl.getId(),
				userDetailsImpl.getUsername(), userDetailsImpl.getEmail(),
				roles));
	
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest)
	{
		if(userRepo.existsByUsername(signupRequest.getUsername())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}
		
		if(userRepo.existsByEmail(signupRequest.getEmail())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Email is already taken!"));
		}
		
		Register register=new Register(signupRequest.getUsername(), signupRequest.getEmail(),
				passwordEncoder.encode(signupRequest.getPassword()) , signupRequest.getName(),
				signupRequest.getAddress());
		Set<String> strRoles=signupRequest.getRole();
		System.out.println(strRoles);
		Set<Role> roles=new HashSet<>();
		
		if(strRoles==null) {
			Role userRole=roleRepo.findByRoleName(EROLE.ROLE_USER)
					.orElseThrow(()->new RuntimeException("Error: role not found"));
			roles.add(userRole);
		}
		else
		{
			strRoles.forEach(e->{
				switch(e) {
				case "admin":
					Role roleAdmin=roleRepo.findByRoleName(EROLE.ROLE_ADMIN)
					.orElseThrow(
							()->new RuntimeException("Error: role not found")
							);
					roles.add(roleAdmin);
					System.out.println("123456");
					break;
				
				default :
					Role userRole=roleRepo.findByRoleName(EROLE.ROLE_USER)
					.orElseThrow(
							()->new RuntimeException("Error: role not found")
							);
					roles.add(userRole);
				
				
				}
			});
			
		}
		System.out.println(roles);
		register.setRoles(roles);
	
		userRepo.save(register);
		return ResponseEntity.status(201).body(new MessageResponse("user created successfully"));
	}
	
	
	
	@GetMapping("users/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> getUserById(@Valid@PathVariable("id") Integer id) throws IdNotFoundException
	{
		Register register= userService.getUserById(id);
		return ResponseEntity.ok(register);
	}
	
	@GetMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
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
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
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
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> updateUser(@RequestBody Register register)
	{
	
		Register result =userService.addUser(register);
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
