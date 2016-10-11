package com.xxx.oa.view.action;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.xxx.oa.base.BaseAction;
import com.xxx.oa.domain.Privilege;
import com.xxx.oa.domain.Role;
import com.xxx.oa.service.RoleService;
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{
	
	
	private static final long serialVersionUID = 3897528735713734934L;
	private Long [] privilegeIds;
	
	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}
	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
	//----------------------------------------------
	@Autowired
	private RoleService roleService;
	public String list() throws Exception{
		
		List<Role> roleList=roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "list";
	}
	public String delete() throws Exception{
		roleService.delete(model.getId());
		
		return "toList";
	}
	
	public String add() throws Exception{
		//1. get parameter;
		
	/*	Role role= new Role();
		role.setName(name);
		role.setDescription(description);
		
		//2. save to DB
		roleService.save(role);*/
		roleService.save(model);
		return "toList";
	}
	public String addUI() throws Exception{
		
		return "addUI";
	}
	
	
	public String editUI() throws Exception{
		Role role=roleService.getById(model.getId());
	/*	this.name=role.getName();
		this.description=role.getDescription();*/
		ActionContext.getContext().getValueStack().push(role);
		
		return "editUI";
	}
	public String edit() throws Exception{
		//1. get the entity from DB
		Role role=roleService.getById(model.getId());
		//2. update info 
		role.setName(model.getName());
		role.setDescription(model.getDescription());
		//3. save to DB
		roleService.update(role);
		return "toList";
	}
	// go page-->set privilege
	public String setPrivilegeUI()throws Exception{
		// prepare data 
		Role role= roleService.getById(model.getId());
		ActionContext.getContext().put("role", role);

	
		List<Privilege> topPrivilegeList=privilegeService.findTopList();
		ActionContext.getContext().put("topPrivilegeList", topPrivilegeList);
	
		// display callback data
		privilegeIds= new Long[role.getPrivileges().size()];
		int index=0;
		for(Privilege privilege :role.getPrivileges()){
			privilegeIds[index++]=privilege.getId();
		}
		
		
		
		return "setPrivilegeUI";
	}
	// set privilege
	public String setPrivilege() throws Exception{
		//1. get the entity from DB
		Role role=roleService.getById(model.getId());
		//2. update attribute 
		List <Privilege> privilegeList= privilegeService.getByIds(privilegeIds);
		role.setPrivileges(new HashSet<Privilege>(privilegeList));

		//3. save to DB
		roleService.update(role);
		return "toList";
	}
}
