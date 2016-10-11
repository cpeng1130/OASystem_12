package com.xxx.oa.service;

import java.util.List;

import com.xxx.oa.base.BaseDao;
import com.xxx.oa.domain.Privilege;

public interface PrivilegeService extends BaseDao<Privilege>{

	
	/**
	 * get All Top Privilege
	 * @return
	 */
	List<Privilege> findTopList();

	/**
	 * get All Privilege(debate null ,url can not be repeat)
	 * @return
	 */
	List<String> getAllPrivilegeUrls();

}
