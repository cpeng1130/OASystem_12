package com.xxx.oa.service;


import java.util.List;

import com.xxx.oa.base.BaseDao;
import com.xxx.oa.domain.Department;

public interface DepartmentService extends BaseDao<Department>{

	/**
	 * find all top level department
	 * @return
	 */
	List<Department> findTopList();

	/**
	 * search sub-department
	 * @param parentId
	 * @return
	 */
	List<Department> findChildren(Long parentId);


}
