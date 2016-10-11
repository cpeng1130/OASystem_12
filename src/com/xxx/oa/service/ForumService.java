package com.xxx.oa.service;

import com.xxx.oa.base.BaseDao;
import com.xxx.oa.domain.Forum;

public interface ForumService extends BaseDao<Forum>{
	/**
	 * move uP,(keep position when forum's the highest )
	 * @param id
	 */
	void moveUp(Long id);

	
	/**
	 * move down,(keep position when forum's the lowest )
	 * @param id
	 */
	void moveDown(Long id);

}
