package com.learning.dto;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Table(name="register",uniqueConstraints = {@UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email")}) 

public class Register implements Comparable<Register> {

	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String password;
	
	
	private String address;
	
	@Id 
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="user_roles",joinColumns = @JoinColumn(name="id")
	, inverseJoinColumns = @JoinColumn(name="roleId"))
	private Set<Role> roles=new HashSet<Role>();
	
	@Override
	public int compareTo(Register o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public Register() {
		// TODO Auto-generated constructor stub
	}
	public Register(String username,String email,String password,String name,String address) {
		this.username=username;
		this.email=email;
		this.password=password;
		this.name=name;
		this.address=address;
	
	}
	
}
