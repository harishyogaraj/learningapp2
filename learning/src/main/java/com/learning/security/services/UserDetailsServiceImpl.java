package com.learning.security.services;

import javax.naming.NameNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learning.dto.Register;
import com.learning.repo.UserRepo;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepo userRepo;
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Register register=userRepo.findByUsername(username)
				.orElseThrow(()->new UsernameNotFoundException("user not found with "
				+ "username"+username));
		return UserDetailsImpl.build(register);
	}

}
