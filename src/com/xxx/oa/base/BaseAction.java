package com.xxx.oa.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xxx.oa.domain.User;
import com.xxx.oa.service.DepartmentService;
import com.xxx.oa.service.ForumService;
import com.xxx.oa.service.PrivilegeService;
import com.xxx.oa.service.ReplyService;
import com.xxx.oa.service.RoleService;
import com.xxx.oa.service.TopicService;
import com.xxx.oa.service.UserService;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -301767124548311706L;
	@Resource
	protected RoleService roleService;
	@Resource
	protected DepartmentService departmentService;
	@Resource
	protected UserService userService;
	@Resource
	protected PrivilegeService privilegeService;

	@Resource
	protected ForumService forumService;
	@Resource
	protected TopicService topicService;
	@Resource
	protected ReplyService replyService;
	
	
	protected T model;
	@SuppressWarnings("unchecked")
	public BaseAction(){
		
		try {
			
			ParameterizedType pt= (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz =(Class<T>) pt.getActualTypeArguments()[0];
			model=(T) clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public T getModel(){
		
		return model;
	}
	protected User getCurrentUser(){
		return (User) ActionContext.getContext().getSession().get("user");
	}
	
	// default value of beginpagenum=1
		protected  int pageNum=1;
		
		
		//-------------------------------------------
		public int getPageNum() {
			return pageNum;
		}
		public void setPageNum(int pageNum) {
			this.pageNum = pageNum;
		}
}
