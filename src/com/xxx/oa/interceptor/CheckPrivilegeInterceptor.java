package com.xxx.oa.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.xxx.oa.domain.User;

public class CheckPrivilegeInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation invocation) throws Exception {

		// get current user
		User user = (User) ActionContext.getContext().getSession().get("user");

		String namespace = invocation.getProxy().getNamespace();
		String actionName = invocation.getProxy().getActionName();

		// get current url. and remove the prefixion
		// url==(namespaceName+actionName)
		String privilegeUrl =null;
		if(namespace.endsWith("/")){
			privilegeUrl=namespace+actionName;
		}else{
			privilegeUrl=namespace+"/"+actionName;
		}
		
		// remove '/'
		if (privilegeUrl.startsWith("/")) {
			privilegeUrl = privilegeUrl.substring(1);
		}

		/*
		 * System.out.println("--->before"); String result=invocation.invoke();
		 * System.out.println("--->after");
		 */
		// if username is null
		if (user == null) {
			// 1. if action is not going to Login-page then redirect to
			// login-page
			if (privilegeUrl.startsWith("userAction_login")) {// userAction_login
																// or
																// userAction_loginUI
				return invocation.invoke();
			} else {
				return "loginUI";// going to stuts2 configuration file .
			}
			// 2. else if page is login then release it.
		} else {
			// else username is valid
			if (user.hasPrivilegeByUrl(privilegeUrl)) { // 1. if user have
														// authority
				return invocation.invoke();
			} else {
				// 2. else redirect to warning page
				return "";
			}
		}

	}

}
