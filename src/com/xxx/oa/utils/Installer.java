package com.xxx.oa.utils;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxx.oa.domain.Privilege;
import com.xxx.oa.domain.User;

@Component
public class Installer {
	@Resource
	private SessionFactory sessionFactory;

	@Transactional
	public void install() {
		Session session = sessionFactory.getCurrentSession();
		//
		User user = new User();
		user.setName("super-admin");
		user.setLoginName("admin");
		user.setPassword(DigistUtils.md5Hex("admin"));
		session.save(user);
		// =========================================================================
		// 2.add data-->Privilege
		Privilege menu, menu1, menu2, menu3, menu4, menu5;
		// --------------------------------

		menu = new Privilege("System management", null, "FUNC20082.gif", null);
		menu1 = new Privilege("Post management", "roleAction_list", null, menu);
		menu2 = new Privilege("Department management", "departmentAction_list",
				null, menu);
		menu3 = new Privilege("User management", "userAction_list", null, menu);

		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);

		session.save(new Privilege("Post--list", "roleAction_list", null, menu1));
		session.save(new Privilege("Post-- add", "roleAction_add", null, menu1));
		session.save(new Privilege("Post--edit", "roleAction_edit", null, menu1));
		session.save(new Privilege("Post--delete", "roleAction_delete", null,
				menu1));

		session.save(new Privilege("Department--list", "departmentAction_list",
				null, menu2));
		session.save(new Privilege("Department-- add", "departmentAction_add",
				null, menu2));
		session.save(new Privilege("Department--edit", "departmentAction_edit",
				null, menu2));
		session.save(new Privilege("Department--delete",
				"departmentAction_delete", null, menu2));

		session.save(new Privilege("User--list", "userAction_list", null, menu3));
		session.save(new Privilege("User-- add", "userAction_add", null, menu3));
		session.save(new Privilege("User--edit", "userAction_edit", null, menu3));
		session.save(new Privilege("User--delete", "userAction_delete", null,
				menu3));
		session.save(new Privilege("reset--password",
				"userAction_initPassword", null, menu3));

		// -----------------------------------------------
		menu = new Privilege("Information Communication", null,
				"FUNC20064.gif", null);
		menu1 = new Privilege("ForumManage", "forumManageAction_list", null,
				menu);
		menu2 = new Privilege("forum", "forumAction_list", null, menu);
		// -----------------------------------------------
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		// ----------------------------------------------
		menu = new Privilege("Process Management", null, "FUNC20057.gif", null);
		menu1 = new Privilege("Process Defination Management",
				"processDefinationAction_list", null, menu);
		menu2 = new Privilege("Application Template Management",
				"applicationTemplateAction_list", null, menu);
		menu3 = new Privilege("Flow Template Application",
				"flowAction_applicationTemplate", null, menu);
		menu4 = new Privilege("Flow Mytask", "flowAction_myTaskList", null,
				menu);
		menu5 = new Privilege("Flow MyApplication",
				"flowAction_myApplicationList", null, menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		session.save(menu5);

	}

	public static void main(String[] a) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		Installer installer = (Installer) ac.getBean("installer");
		installer.install();
		System.out.println("-------------");
	}

}
