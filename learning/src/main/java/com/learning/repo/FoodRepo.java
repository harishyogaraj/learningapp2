package com.learning.repo;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.dto.Food;
import com.learning.dto.FoodType;
import com.learning.payload.request.FoodRequest;
import com.learning.dto.EFOODTYPE;

@Repository
public interface FoodRepo extends JpaRepository<Food, Integer> {

	public boolean existsByFoodName(String foodName);



	public Optional<List<Food>> findByFoodTypes(FoodType foodType);



	public boolean existsById(Integer foodId);



	public Food save(FoodRequest food);
	
}
