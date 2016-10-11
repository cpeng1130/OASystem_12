package com.xxx.oa.service.impl;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xxx.oa.base.BaseDaoImpl;
import com.xxx.oa.cfg.Configuration;
import com.xxx.oa.domain.Forum;
import com.xxx.oa.domain.PageBean;
import com.xxx.oa.domain.Reply;
import com.xxx.oa.domain.Topic;
import com.xxx.oa.service.ReplyService;
@SuppressWarnings("unchecked")
@Service
@Transactional
public class ReplyServiceImpl extends BaseDaoImpl<Reply> implements ReplyService {

	@Override
	public List<Reply> findByTopic(Topic topic) {
		return getSession().createQuery("FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC")
				.setParameter(0, topic)
				.list();
	}

	@Override
	public void save(Reply reply) {
		
		// save to db
		getSession().save(reply);
		
		// maintain relative attribute
		Topic topic=reply.getTopic();
		Forum forum=topic.getForum();
		
		
	//	update forum.count
		forum.setArticleCount(forum.getArticleCount()+1);
	//	update topic.Count() topic.LastUpdateTime topic.LastReply()
		topic.setReplyCount(topic.getReplyCount()+1);
		topic.setLastReply(reply);
		topic.setLastUpdateTime(reply.getPostTime());
		
		getSession().save(forum);
		getSession().save(topic);
		
		super.save(reply);
	}

	@Override
	public PageBean getPageBean(int pageNum, Topic topic) {
			int pageSize= Configuration.getPageSize();
			
			// get content of currentpage
			
			List<Reply> list= getSession().createQuery("FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC")
					.setParameter(0, topic)
					.setFirstResult((pageNum-1)*pageSize)
					.setMaxResults(pageSize)
					.list();
			//
			Long count=(Long) getSession().createQuery("SELECT COUNT(*) FROM Reply r WHERE r.topic=?")
					.setParameter(0, topic)
					.uniqueResult();
			return new PageBean(pageNum, pageSize, list, count.intValue());
	}

	
}
