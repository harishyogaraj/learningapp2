package com.learning.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.dto.Food;
import com.learning.dto.FoodType;
import com.learning.repo.FoodTypeRepo;
import com.learning.service.FoodTypeService;

@Service
public class FoodTypeServiceImpl implements FoodTypeService {

	@Autowired
	FoodTypeRepo foodTypeRepo;
	
	@Override
	public FoodType addFoodType(FoodType foodType) {
		// TODO Auto-generated method stub
		FoodType optional=foodTypeRepo.save(foodType);
		if(optional!=null)
			return optional;
		return null;
	}

	@Override
	public String deleteFoodTypeById(int foodId) {
		// TODO Auto-generated method stub
		FoodType optioanl=this.getFoodTypeById(foodId);
		if(optioanl==null)
		{
//			throw new IdNotFound("record not found");
			return null;
		}
		else
		{
			foodTypeRepo.deleteById(foodId);
			return "Deleted Successfuly";
		}
		
		
	}

	@Override
	public FoodType getFoodTypeById(int foodId) {
		// TODO Auto-generated method stub
		Optional<FoodType> optional=foodTypeRepo.findById(foodId);
		if(optional.isEmpty())
			return null;
		return optional.get();
	}

//	@Override
//	public Optional<List<Food>> getAllfoodbytypes(Set<FoodType> foodTypes) {
//		// TODO Auto-generated method stub
//		return foodTypeRepo.findByFoodType(foodTypes);
//	}

}
