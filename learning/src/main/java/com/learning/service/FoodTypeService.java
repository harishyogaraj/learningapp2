package com.learning.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.learning.dto.Food;
import com.learning.dto.FoodType;

@Service
public interface FoodTypeService {

	public FoodType addFoodType(FoodType foodType);
	public String deleteFoodTypeById(int foodId);
	public FoodType getFoodTypeById(int foodId);
	//public Optional<List<Food>> getAllfoodbytypes(Set<FoodType> foodTypes);
}
