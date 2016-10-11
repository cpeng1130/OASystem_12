package com.xxx.oa.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xxx.oa.base.BaseDaoImpl;
import com.xxx.oa.domain.Department;
import com.xxx.oa.service.DepartmentService;
@SuppressWarnings("unchecked")
@Service
@Transactional
public class DepartmentServiceImpl extends BaseDaoImpl<Department> implements DepartmentService{

	
	@Override
	public List<Department> findTopList() {
		
		return getSession().createQuery("From Department d WHERE d.parent IS NULL").list();
	}

	@Override
	public List<Department> findChildren(Long parentId) {
		
		return getSession().createQuery("FROM Department d where d.parent.id=?").setParameter(0, parentId).list();
	}

	
	
}
