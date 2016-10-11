package com.xxx.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xxx.oa.base.BaseDaoImpl;
import com.xxx.oa.domain.Privilege;
import com.xxx.oa.service.PrivilegeService;
@Service
@Transactional
public class PrivilegeServiceImpl extends BaseDaoImpl<Privilege> implements PrivilegeService{

	@SuppressWarnings("unchecked")
	@Override
	public List<Privilege> findTopList() {
		return getSession().createQuery("FROM Privilege p WHERE p.parent IS NULL").list();
	}

	@Override
	public List<String> getAllPrivilegeUrls() {
		return getSession().createQuery("SELECT DISTINCT p.url FROM Privilege p WHERE p.url IS NOT NULL ").list();
	}

}
