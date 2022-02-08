package com.learning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.learning.dto.Food;
import com.learning.dto.Register;

@Service
public interface FoodService {

	public Food addFood(Food food);
	public Optional<List<Food>> getAllUsers();
	public Food getFoodById(Integer id);
	public String deleteFoodById(Integer id);
}
