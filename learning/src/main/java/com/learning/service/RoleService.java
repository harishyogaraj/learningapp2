package com.learning.service;

import org.springframework.stereotype.Service;

import com.learning.dto.Role;

@Service
public interface RoleService {
	public String addRole(Role role);
	public void deleteRole(int roleId);
}
