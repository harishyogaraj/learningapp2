package com.learning.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
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
@Table(name="food") 
@NoArgsConstructor
@AllArgsConstructor

public class Food {

	@NotBlank
	private String foodName;
	
	@NotNull
	private int foodCast;
	
	
	private String discription;
	
	private String foodPic;
	
	@Id 
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToMany
	@JoinTable(name="food_types",joinColumns = @JoinColumn(name="id")
	, inverseJoinColumns = @JoinColumn(name="foodId"))
	private Set<FoodType> foodType=new HashSet<FoodType>();
}
