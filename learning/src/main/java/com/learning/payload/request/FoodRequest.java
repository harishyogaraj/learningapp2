package com.learning.payload.request;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class FoodRequest {

	@NotBlank
	private String foodName;
	
	@NotNull
	private int foodCast;
	
	private String discription;
	
	private String foodPic;
	
	private Set<String> foodTypes;
}
