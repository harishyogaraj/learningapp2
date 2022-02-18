package com.learning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.dto.Role;
import com.learning.repo.RoleRepo;
import com.learning.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepo roleRepo;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String addRole(Role role) {
		// TODO Auto-generated method stub
		Role role2=roleRepo.save(role);
		if(role2!=null)
			return "success";
		else
			return "fail";
	}

	@Override
	public void deleteRole(int roleId) {
		// TODO Auto-generated method stub

	}

}
