package com.learning.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.learning.dto.Food;
import com.learning.dto.FoodType;
import com.learning.dto.Register;
import com.learning.exception.IdNotFoundException;
import com.learning.payload.request.FoodRequest;

@Service
public interface FoodService {

	public Food addFood(Food food);
	public Optional<List<Food>> getAllUsers();
	public Food getFoodById(Integer id);
	public String deleteFoodById(Integer id);
	public Food updateFood(Integer id, FoodRequest food) throws IdNotFoundException;
	public Optional<List<Food>> getAllfoodbytypes(FoodType set);
}
