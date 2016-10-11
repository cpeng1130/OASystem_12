package com.xxx.oa.view.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.xxx.oa.base.BaseAction;
import com.xxx.oa.domain.Forum;
import com.xxx.oa.domain.PageBean;
import com.xxx.oa.domain.Reply;
import com.xxx.oa.domain.Topic;
@Controller
@Scope("prototype")
public class TopicAction extends BaseAction<Topic>{
	
	private Long forumId;
	
	
	
	public Long getForumId() {
		return forumId;
	}
	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}
	//-------------------------------------------
	
	/** simple Topic(main topic + reply)*/
	public String show() throws Exception{
		
		// prepare date  topic, forum
		Topic topic =topicService.getById(model.getId());
		ActionContext.getContext().put("topic", topic);
		
		
		// prepare date  replyList's pagination information
	/*	List<Reply> replyList= replyService.findByTopic(topic);
		ActionContext.getContext().put("replyList",replyList);
		
		
		PageBean pageBean= replyService.getPageBean(pageNum,topic);
		ActionContext.getContext().getValueStack().push(pageBean);*/
		
		// use public method to get pagination information
		String hql="FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC";
		Object [] parameters= new Object[]{topic};
		PageBean pageBean= replyService.getPageBean(pageNum,hql,parameters);
		ActionContext.getContext().getValueStack().push(pageBean);
		
		return "show";
	}
	/**
	 * new Topic page
	 * */
	public String addUI() throws Exception{
		
		Forum forum=forumService.getById(forumId);
		ActionContext.getContext().put("forum", forum);
		return "addUI";
	}
	/**
	 * new Topic
	 * */
	public String add() throws Exception{
		
		//forum field 
		model.setForum(forumService.getById(forumId));
		/*model.setTitle(title);
		model.setContent(content);
		model.setFaceIcon(faceIcon);
		*/
		
		//current  information
		
		model.setAuthor(getCurrentUser());
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date());
		
		/*// business method 
		model.setType(type);
		model.setLastReply(lastReply);
		model.setLastUpdateTime(lastUpdateTime);
		model.setReplyCount(replyCount);
	*/
		topicService.save(model);
		//
		return "toShow";
	}
	
}
