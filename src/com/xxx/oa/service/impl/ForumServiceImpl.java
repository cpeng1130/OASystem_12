package com.xxx.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xxx.oa.base.BaseDaoImpl;
import com.xxx.oa.domain.Forum;
import com.xxx.oa.service.ForumService;
@Service
public class ForumServiceImpl extends BaseDaoImpl<Forum> implements ForumService{

	@Override
	public void moveUp(Long id) {
		//get 2 forum
		Forum forum = getById(id);
		Forum other = (Forum) getSession().createQuery(
				"FROM Forum f WHERE f.position<? ORDER BY f.position DESC"
				).setParameter(0, forum.getPosition()).setFirstResult(0).setMaxResults(1).uniqueResult();
		
		
		if(other==null){
			return;
		}
	
		// exchange position
		int temp=forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);
		
		// save to DB
		
	}

	@Override
	public void moveDown(Long id) {
		//get 2 forum
		Forum forum = getById(id);
		Forum other = (Forum) getSession().createQuery(
				"FROM Forum f WHERE f.position>? ORDER BY f.position ASC"
				).setParameter(0, forum.getPosition()).setFirstResult(0).setMaxResults(1).uniqueResult();
		
		// if position is the uppest  return null
		
		if(other==null){
			return;
		}
		
		
		// exchange position
		int temp=forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);
		
		// save to DB
		
		
	}
	
	public List<Forum> findAll() {
		return getSession().createQuery("FROM Forum f ORDER BY f.position ASC").list();
	}
	public void save(Forum forum){
		System.out.println(forum.getName()+forum.getId()+" ------------------------------- ");
		// save forum get ID,
		getSession().save(forum);
		//System.out.println(forum.getName()+forum.getId()+"-------------------------------------------------");
		//set attribute position is the max  SELECT MAX(f postion)+1 from Forum
		forum.setPosition(forum.getId().intValue());// 
		//save to DB
	
	}
}
