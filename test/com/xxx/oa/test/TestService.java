package com.xxx.oa.test;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xxx.oa.domain.User;
@Service
public class TestService {
	@Resource
	private SessionFactory  sessionFactory;
	@Transactional
	public void save2Users(){
		
		Session session =sessionFactory.getCurrentSession();
		session.save(new User());
		session.save(new User());
	}
	
	@Transactional
	public void saveUsers_23(){
		Session session = sessionFactory.getCurrentSession();
		for(int i=1;i<23;i++){
			User user = new User();
			user.setName("test-"+i);
			session.save(user);
		}
	}
}
