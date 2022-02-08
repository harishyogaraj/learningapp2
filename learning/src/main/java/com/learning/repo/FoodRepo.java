package com.learning.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.dto.Food;
import com.learning.dto.EFoodType;

@Repository
public interface FoodRepo extends JpaRepository<Food, Integer> {

	public Optional<List<Food>> findByFoodType(EFoodType foodType);
}
