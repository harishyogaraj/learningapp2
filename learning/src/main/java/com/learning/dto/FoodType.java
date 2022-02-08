package com.learning.dto;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@Entity  
@Table(name="foodtype") 
@NoArgsConstructor
@AllArgsConstructor


public class FoodType {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int foodId;
	
	@Enumerated(EnumType.STRING)
	private EFoodType eFoodType;
}
