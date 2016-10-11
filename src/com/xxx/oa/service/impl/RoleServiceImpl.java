package com.xxx.oa.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xxx.oa.base.BaseDaoImpl;
import com.xxx.oa.domain.Role;
import com.xxx.oa.service.RoleService;
@Service
@Transactional
public class RoleServiceImpl extends BaseDaoImpl<Role> implements RoleService{

}
