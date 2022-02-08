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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.Food;
import com.learning.dto.Register;
import com.learning.exception.AlreadyExistException;
import com.learning.exception.IdNotFoundException;
import com.learning.service.FoodService;
import com.learning.service.UserService;

@RestController
@RequestMapping
public class FoodController {

	@Autowired
	FoodService foodService;
	
	@PostMapping("/food")
	public ResponseEntity<?> addFood(@RequestBody Food food) throws AlreadyExistException
	{
	
		Food result=	foodService.addFood(food);
		System.out.println(result);
		return ResponseEntity.status(201).body(result);
	}
	
	@GetMapping("food/{id}")
	public ResponseEntity<?> getFoodById(@Valid@PathVariable("id") Integer id) throws IdNotFoundException
	{
		Food register= foodService.getFoodById(id);
		return ResponseEntity.ok(register);
	}
	
	@GetMapping("/food/")
	public ResponseEntity<?> getAllFood()
	{
		Optional<List<Food>> optional= foodService.getAllUsers();
		
		if(optional.isEmpty())
		{
			Map<String,String> map=new HashMap<>();
			map.put("message", "no records found");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		return ResponseEntity.ok(optional.get());
	}
	
	@DeleteMapping("/food/delete{id}")
	public ResponseEntity<?> deleteStudentById(@Valid@PathVariable("id") Integer id)
	{
		String res= foodService.deleteFoodById(id);
		if(res.equals("success"))
		{
			Map<String, String> map=new HashMap<>();
			map.put("message", "success");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(map);
		}
		return null;
	}
}
