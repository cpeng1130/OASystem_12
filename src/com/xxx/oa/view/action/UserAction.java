package com.xxx.oa.view.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.xxx.oa.base.BaseAction;
import com.xxx.oa.domain.Department;
import com.xxx.oa.domain.Role;
import com.xxx.oa.domain.User;
import com.xxx.oa.utils.DepartmentUtils;
import com.xxx.oa.utils.HqlHelper;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	private Long departmentId;
	private Long[] roleIds;

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7072131797676310015L;

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String list() throws Exception {
		//List<User> userList = userService.findAll();
		//ActionContext.getContext().put("userList", userList);
		 new HqlHelper(User.class).buildPageBeanForStruts2(pageNum, userService);
		return "list";
	}

	public String delete() throws Exception {
		userService.delete(model.getId());
		return "toList";
	}

	public String add() throws Exception {
		
	
		// data encapsulation
		// ---1. setDepartment
		model.setDepartment(departmentService.getById(departmentId));

		// ---2. setRoles
		
		List<Role> roleList = roleService.getByIds(roleIds);
		model.setRoles(new HashSet<Role>(roleList));
	

		// ---3. setInitPassword
		model.setPassword("1234");
		// save to DB
		userService.save(model);
		return "toList";
	}

	public String addUI() throws Exception {

		// 1. basic data departmentList date
		// ---need a tree type to display all information
		//
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartment(topList);
		ActionContext.getContext().put("departmentList", departmentList);

		// 2. basic date roleList date
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		return "saveUI";
	}

	public String editUI() throws Exception {

		// 1. basic data departmentList date
		// ---need a tree type to display all information
		//
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartment(topList);
		ActionContext.getContext().put("departmentList", departmentList);

		// 2. basic date roleList date
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		// 3.callback data
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);

		if (departmentId != null) {
			departmentId = user.getDepartment().getId();
		}
		if (user.getRoles() != null) {
			roleIds=new Long[user.getRoles().size()];
			int index=0;
			for(Role role:user.getRoles()){
				roleIds[index++]=role.getId();
			}
		}
		return "saveUI";
	}

	public String edit() throws Exception {
		// 1. get Object from DB 
		User user= userService.getById(model.getId());
		// 2. update data
		user.setDescription(model.getDescription());
		user.setEmail(model.getEmail());
		user.setGender(model.getGender());
		user.setName(model.getName());
		user.setPassword(model.getPassword());
		user.setPhoneNumber(model.getPhoneNumber());
		
		
				// data encapsulation
				// ---1. setDepartment
				user.setDepartment(departmentService.getById(departmentId));

				// ---2. setRoles
				Set<Role> roleSet = new HashSet<Role>();
				List<Role> roleList = roleService.getByIds(roleIds);
				roleSet.addAll(roleList);
				user.setRoles(roleSet);
		
		// 3. saving to DB
		userService.update(user);
		
		return "toList";
	}

	/**
	 * initpassword is "1234"
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initPassword() throws Exception {
		// 1. get Object from DB 
		User user= userService.getById(model.getId());
		// 2. update data
		user.setPassword("1234");
		
		// 3. saving to DB
		userService.update(user);
		
		return "toList";

	}
	//********************LOGIN *****************************
	
	// 1. login UI
	
	public String loginUI()throws Exception{
		
		return "loginUI";
	}
	
	// 2. login
	
	public String login()throws Exception{
	
		// check 
		User user= userService.getByLoginNameAndPassword(model.getLoginName(),model.getPassword());
	
		if(user==null){
			// username or password is invalid
			addFieldError("login","username or password is invalid");
			
			return "loginUI";
		}else{
			// login system
			ActionContext.getContext().getSession().put("user", user);
			return "toIndex";
		}
		
	}
	
	
	// 3. logout
	
	public String logout()throws Exception{
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}
}
