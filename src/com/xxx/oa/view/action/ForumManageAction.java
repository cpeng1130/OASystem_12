package com.xxx.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.xxx.oa.base.BaseAction;
import com.xxx.oa.domain.Forum;
@Controller
@Scope("prototype")
public class ForumManageAction extends BaseAction<Forum>{
	
	
	/**list*/
	public String list()throws Exception{
		List<Forum> forumList= forumService.findAll();
		ActionContext.getContext().put("forumList",forumList);
		return "list";
	}
	
	/**delete*/
	public String delete()throws Exception{
		forumService.delete(model.getId());
		return "toList";
	}
	
	/**addUI*/
	public String addUI()throws Exception{
		return "saveUI";
	}
	
	/**add*/
	public String add()throws Exception{
	
		forumService.save(model);
		System.out.println(model.getName()+model.getId());
		return "toList";
	}
	
	/**editUI*/
	public String editUI()throws Exception{
		//prepare callback data
		Forum forum= forumService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(forum);
		
		return "saveUI";
	}
	
	/**edit*/
	public String edit()throws Exception{
		//1. get entity from DB
		Forum forum= forumService.getById(model.getId());
		
		//2. set attribute
			forum.setName(model.getName());
			forum.setDescription(model.getDescription());
		
		//3. save data to DB
		forumService.update(forum);
		
		return "toList";
	}
	
	/**moveup*/
	public String moveUp()throws Exception{
		forumService.moveUp(model.getId());
		return "toList";
	}
	
	/**movedown*/
	public String moveDown()throws Exception{
		forumService.moveDown(model.getId());
		return "toList";
	}
	
}
