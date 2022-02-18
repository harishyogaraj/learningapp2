package com.learning.repo;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.dto.EFOODTYPE;

import com.learning.dto.Food;
import com.learning.dto.FoodType;


@Repository
public interface FoodTypeRepo extends JpaRepository<FoodType, Integer> {
	 Optional<FoodType> findByFoodType(EFOODTYPE foodType);

	
}
