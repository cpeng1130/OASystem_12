package com.xxx.oa.service;

import com.xxx.oa.base.BaseDao;
import com.xxx.oa.domain.User;

public interface UserService extends BaseDao<User> {
	
	
	/**
	 * login check
	 * @param loginName
	 * @param password 
	 * @return
	 */
	User getByLoginNameAndPassword(String loginName, String password);

}
