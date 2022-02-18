package com.learning.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.EFOODTYPE;
import com.learning.dto.EROLE;
import com.learning.dto.Food;
import com.learning.dto.FoodType;
import com.learning.dto.Register;
import com.learning.dto.Role;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
import com.learning.payload.request.FoodRequest;
import com.learning.payload.response.MessageResponse;
import com.learning.repo.FoodRepo;
import com.learning.repo.FoodTypeRepo;
import com.learning.service.FoodService;
import com.learning.service.UserService;

@RestController
@RequestMapping("/food")
public class FoodController {

	@Autowired
	FoodService foodService;
	
	@Autowired
	FoodRepo foodRepo;
	
	
@Autowired
FoodTypeRepo foodTypeRepo;
	
	  @PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/addFood")
	public ResponseEntity<?> addFood(@Valid @RequestBody FoodRequest foodRequest) throws AlreadyExistsException
	{
	
		  if(foodRepo.existsByFoodName(foodRequest.getFoodName())) {
				return ResponseEntity.badRequest()
						 .body(new MessageResponse("Error: Episode with name: "+foodRequest.getFoodName()+" exists!"));
			}
			
			Food food = new Food(foodRequest.getFoodName(),
					foodRequest.getFoodCast(),foodRequest.getDiscription(),
					foodRequest.getFoodPic()
					);
			
			Set<String> foodTypes=foodRequest.getFoodTypes();
			
			Set<FoodType> types=new HashSet<>();
			
			if(foodTypes==null) {
				FoodType indFood=foodTypeRepo.findByFoodType(EFOODTYPE.Indian)
						.orElseThrow(()->new RuntimeException("Error: food not found"));
				types.add(indFood);
			}
			else
			{
				foodTypes.forEach(e->{
					switch(e) {
					case "Mexican":
						FoodType mexFood=foodTypeRepo.findByFoodType(EFOODTYPE.Mexican)
						.orElseThrow(
								()->new RuntimeException("Error: Food Type not found")
								);
						types.add(mexFood);
						System.out.println("123456");
						break;
						
					case "Chinese":
						FoodType chiFood=foodTypeRepo.findByFoodType(EFOODTYPE.Chinese)
						.orElseThrow(
								()->new RuntimeException("Error: role not found")
								);
						types.add(chiFood);
						break;
					
					default :
						FoodType indFood=foodTypeRepo.findByFoodType(EFOODTYPE.Indian)
						.orElseThrow(
								()->new RuntimeException("Error: role not found")
								);
						types.add(indFood);
					
					
					}
				});
				
			}
			food.setFoodTypes(types);
			
			foodRepo.save(food);
			return ResponseEntity.status(201).body(new MessageResponse("Food added successfully"));
		  
	}
	
	@GetMapping("/food{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> getFoodById(@Valid@PathVariable("id") Integer id) throws IdNotFoundException
	{
		Food register= foodService.getFoodById(id);
		return ResponseEntity.ok(register);
	}
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
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
	
	@DeleteMapping("/delete{id}")
	  @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteFoodById(@Valid@PathVariable("id") Integer id)
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
	
	
	@PutMapping("/update/food{foodId}")
	public ResponseEntity<?> updateFood(@PathVariable("foodId") Integer foodId, @RequestBody FoodRequest food) throws IdNotFoundException
	{
		Food result = foodService.updateFood(foodId, food);
		Map<String, String> map = new HashMap<>();
		map.put("message", "success updated");
		return ResponseEntity.status(201).body(result);
	}
}
