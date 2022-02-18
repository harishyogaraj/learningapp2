package com.learning.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.dto.Food;
import com.learning.dto.FoodType;
import com.learning.exception.IdNotFoundException;
import com.learning.payload.request.FoodRequest;
import com.learning.repo.FoodRepo;
import com.learning.service.FoodService;

@Service
public class FoodServiceImpl implements FoodService {
	
	@Autowired
	FoodRepo foodRepo;

	@Override
	public Food addFood(Food food) {
		// TODO Auto-generated method stub
		Food optional=foodRepo.save(food);
		if(optional!=null)
			return optional;
		return null;
	}
	

	@Override
	public Optional<List<Food>> getAllUsers() {
		// TODO Auto-generated method stub
		return Optional.ofNullable(foodRepo.findAll());
	}

	@Override
	public Food getFoodById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Food> optional=foodRepo.findById(id);
		if(optional.isEmpty()) {
		//	throw new IdNotFound("id doesnot exists");
			return null;
		}
		else {
			return optional.get();
		}
		}

	@Override
	public String deleteFoodById(Integer id) {
		// TODO Auto-generated method stub
		Food optioanl=this.getFoodById(id);
		if(optioanl==null)
		{
			return null;
		}
		else
		{
			foodRepo.deleteById(id);
			return "success";
		}
	}

	@Override
	public Optional<List<Food>> getAllfoodbytypes(FoodType foodType) {
		// TODO Auto-generated method stub
		return foodRepo.findByFoodTypes(foodType);
		}


	@Override
	public Food updateFood(Integer foodId, FoodRequest food) throws IdNotFoundException {
		// TODO Auto-generated method stub
		if(!this.foodRepo.existsById(foodId))
			throw new IdNotFoundException("invalid id");
		
		return foodRepo.save(food);
	}
}
