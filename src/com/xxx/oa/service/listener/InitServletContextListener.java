package com.xxx.oa.service.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.xxx.oa.domain.Privilege;
import com.xxx.oa.service.PrivilegeService;

public class InitServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application =sce.getServletContext();
		
		
		//get entity of privilegeServic
		ApplicationContext ac=WebApplicationContextUtils.getWebApplicationContext(application);
		
		PrivilegeService privilegeService=(PrivilegeService) ac.getBean("privilegeServiceImpl");
		// prepare all Top level menu
		List <Privilege> topPrivilegeList =privilegeService.findTopList();
		
		application.setAttribute("topPrivilegeList", topPrivilegeList);
		
		List<String> allPrivilegeUrls=privilegeService.getAllPrivilegeUrls();
		application.setAttribute("allPrivilegeUrls", allPrivilegeUrls);
		
		
//		jps   click       click struct     broswer    tomcat
//		page->request->Struts2 ValueStack->session->application
		
		System.out.println("Application is ready");
	}
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}


}
