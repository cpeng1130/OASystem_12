package com.xxx.oa.view.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.xxx.oa.base.BaseAction;
import com.xxx.oa.domain.Reply;
import com.xxx.oa.domain.Topic;
@Controller
@Scope("prototype")
public class ReplyAction extends BaseAction<Reply> {
	
	private Long topicId;
	
	
	/**
	 *  new Reply page
	 * */
	public String addUI()throws Exception{
		Topic topic= topicService.getById(topicId);
		ActionContext.getContext().put("topic", topic);
		return "addUI";
	}
	/**
	 * new Reply
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception{
		
		
		// 1. render
	
		model.setTopic(topicService.getById(topicId));

		
		
		
		model.setAuthor(getCurrentUser());
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date());
		// 2. save
		replyService.save(model);
		
		
		return "toTopicShow"; // back to relative topic page
	}
	
	
	//------------------------------------------------------
	public Long getTopicId() {
		return topicId;
	}
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	
	
}
