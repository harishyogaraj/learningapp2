package com.learning.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.dto.Food;
import com.learning.dto.Register;
import com.learning.repo.UserRepo;
import com.learning.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo userRepo;

	@Override
	public Register addUser(Register register) {
		// TODO Auto-generated method stub
		Register optional=userRepo.save(register);
		if(optional!=null)
			return optional;
		return null;
	}

	@Override
	public Optional<List<Register>> getAllUsers() {
		// TODO Auto-generated method stub
		return Optional.ofNullable(userRepo.findAll());
	}

	@Override
	public Register getUserById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Register> optional=userRepo.findById(id);
		if(optional.isEmpty()) {
		//	throw new IdNotFound("id doesnot exists");
			return null;
		}
		else {
			return optional.get();
		}

		
	}

	@Override
	public String deleteUserById(Integer id) {
		// TODO Auto-generated method stub
		Register optioanl=this.getUserById(id);
		if(optioanl==null)
		{
//			throw new IdNotFound("record not found");
			return null;
		}
		else
		{
			userRepo.deleteById(id);
			return "success";
		}
	}

	
}
