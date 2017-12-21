package com.myStyle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myStyle.dao.UserDao;
import com.myStyle.entity.User;
import com.myStyle.service.UserService;

@Transactional
@Service("userService")
public class UserServiceimpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public User querySingleUser1(String userName) {
		return userDao.querySingleUser1(userName);
	}

}
