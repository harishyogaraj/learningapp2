package com.learning.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.dto.Register;

@Repository
public interface UserRepo extends JpaRepository<Register, Integer> {

	boolean existsByEmail(String email);
	boolean existsByEmailAndPassword(String email,String password);
	Optional<Register> findByEmail(String email);
	boolean existsByUsername(String username);
	Optional<Register> findByUsername(String username);
}
