package com.xxx.oa.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
	
	private ApplicationContext ac= new ClassPathXmlApplicationContext("applicationContext.xml");
	
	
	private Log log= LogFactory.getLog(getClass());
	
	
	public  void testLog()throws Exception{
		log.debug("this is debug");
		log.info("this is info");
		log.warn("this is warn");
		log.error("this is error");
		log.fatal("this is fatal");
	}
	
	
	//test SessionFactory
	//@Test
	public void testSessionFactory()throws Exception{
		SessionFactory sessionFactory=(SessionFactory) ac.getBean("sessionFactory");
		System.out.println(sessionFactory);
	}
	//test Transaction
	//@Test
	public void testTransaction()throws Exception{
		TestService testService=(TestService) ac.getBean("testService");
		testService.save2Users();
	}
	
	//test Transaction
		@Test
		public void testUser()throws Exception{
			TestService testService=(TestService) ac.getBean("testService");
			testService.saveUsers_23();
		}
}
