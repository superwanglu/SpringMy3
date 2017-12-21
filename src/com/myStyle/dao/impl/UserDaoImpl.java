package com.myStyle.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.myStyle.dao.UserDao;
import com.myStyle.entity.User;

@Repository("userDao")
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao{

	@Override
	public User querySingleUser1(String userName) {
		return (User)getSqlSession().selectOne("user.queryUserName",userName);
	}

}
