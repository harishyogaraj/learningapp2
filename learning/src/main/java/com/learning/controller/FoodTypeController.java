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

import com.learning.dto.EFoodType;
import com.learning.dto.Food;
import com.learning.dto.FoodType;
import com.learning.exception.AlreadyExistException;
import com.learning.exception.IdNotFoundException;
import com.learning.service.FoodService;
import com.learning.service.FoodTypeService;

@RestController
@RequestMapping
public class FoodTypeController {

	@Autowired
	FoodTypeService foodTypeService;
	
	@PostMapping("/foodtype")
	public ResponseEntity<?> addFoodType(@RequestBody FoodType foodType) throws AlreadyExistException
	{
	
		FoodType result=	foodTypeService.addFoodType(foodType);
		System.out.println(result);
		return ResponseEntity.status(201).body(result);
	}
	
	@GetMapping("foodtype/{id}")
	public ResponseEntity<?> getFoodTypeById(@Valid@PathVariable("id") Integer id) throws IdNotFoundException
	{
		FoodType register= foodTypeService.getFoodTypeById(id);
		return ResponseEntity.ok(register);
	}
	
	
	@DeleteMapping("/foodtype/delete{id}")
	public ResponseEntity<?> deleteFoodTypeById(@Valid@PathVariable("id") Integer id)
	{
		String res= foodTypeService.deleteFoodTypeById(id);
		if(res.equals("success"))
		{
			Map<String, String> map=new HashMap<>();
			map.put("message", "deleted successfuly");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(map);
		}
		return null;
	}

}
