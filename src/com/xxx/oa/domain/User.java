package com.xxx.oa.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @author Peng
 *
 */
public class User {
	private long id;
	private Department department;
	private Set<Role> roles = new HashSet<Role>();
	
	private String loginName;
	private String password;
	private String name;
	private String gender;
	private String phoneNumber;
	private String email;
	private String description;
	
	
	public boolean hasPrivilegeByUrl(String privilegeUrl){
		// superAdmin
		if(isAdmin()){
			return true;
		}
		
		
		//if the privilegeUrl endwith "UI"  ,then remove it , so  addUI and add have some privilege
		if(privilegeUrl.endsWith("UI")){
			privilegeUrl=privilegeUrl.substring(0,privilegeUrl.length()-2);
		}
		
		// Privilege has 2 part : 
			//1. first part is need to manage.
			//2. second part is common privilege(everyone has this right)
		//General user
		
		List <String> allPrivilegeUrls=(List<String>) ActionContext.getContext().getApplication().get("allPrivilegeUrls");
		if(allPrivilegeUrls.contains(privilegeUrl)){
			
			return true;
			// second part privilege
			
		}else{// first part privilege
			for(Role role:roles){
				for(Privilege privilege :role.getPrivileges()){
					if(privilegeUrl.equals(privilege.getUrl())){
						return true;
					}
				}
			}
			return false;
			
		}
		
	}
	
	/**
	 * Super Admin -- check in 
	 * @return
	 */
	private boolean isAdmin() {
		
		return "admin".equals(loginName);
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
