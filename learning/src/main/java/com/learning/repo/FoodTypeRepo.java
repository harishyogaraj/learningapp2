package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.learning.dto.FoodType;

@Repository
public interface FoodTypeRepo extends JpaRepository<FoodType, Integer> {

	
}
