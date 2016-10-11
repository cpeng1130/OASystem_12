package com.xxx.oa.service;


import java.util.List;

import com.xxx.oa.base.BaseDao;
import com.xxx.oa.domain.Forum;
import com.xxx.oa.domain.PageBean;
import com.xxx.oa.domain.Topic;

public interface TopicService extends BaseDao<Topic>{

	
	/**
	 * get pagination information
	 * find specific topic ,sort by time(type-top is in the top area)
	 * @param forum
	 * @return list 
	 */
	@Deprecated	
	List<Topic> findByForum(Forum forum);
	@Deprecated	
	PageBean getPageBean(int pageNum, Forum forum);


}
