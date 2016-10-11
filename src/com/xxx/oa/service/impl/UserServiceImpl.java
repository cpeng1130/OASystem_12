package com.xxx.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xxx.oa.base.BaseDaoImpl;
import com.xxx.oa.domain.User;
import com.xxx.oa.service.UserService;
import com.xxx.oa.utils.DigistUtils;
@Service
@Transactional
public class UserServiceImpl extends BaseDaoImpl<User> implements UserService {

	@Override
	public User getByLoginNameAndPassword(String loginName, String password) {
		return (User) getSession().createQuery(
				"FROM User u where u.loginName =? AND u.password=?"
				).setParameter(0, loginName)
				.setParameter(1, DigistUtils.md5Hex(password))
				.uniqueResult();
	}

}
