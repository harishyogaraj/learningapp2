package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.dto.Register;

@Repository
public interface UserRepo extends JpaRepository<Register, Integer> {

	boolean existsByEmail(String email);
	boolean existsByEmailAndPassword(String email,String password);
}
