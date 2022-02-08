package com.learning.service;

import org.springframework.stereotype.Service;

import com.learning.dto.FoodType;

@Service
public interface FoodTypeService {

	public FoodType addFoodType(FoodType foodType);
	public String deleteFoodTypeById(int foodId);
	public FoodType getFoodTypeById(int foodId);
}
