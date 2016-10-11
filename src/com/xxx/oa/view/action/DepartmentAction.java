package com.xxx.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.xxx.oa.base.BaseAction;
import com.xxx.oa.domain.Department;
import com.xxx.oa.utils.DepartmentUtils;


@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -992585744745374164L;
	private Long parentId;

	public String list() throws Exception {
		List<Department> departmentList=null;
		if (parentId == null) {

			departmentList = departmentService.findTopList();
		} else {

			departmentList = departmentService.findChildren(parentId);
			Department parent=departmentService.getById(parentId);
			ActionContext.getContext().put("parent", parent);
		}
		ActionContext.getContext().put("departmentList", departmentList);
		return "list";
	}

	public String delete() throws Exception {
		departmentService.delete(model.getId());

		return "toList";
	}

	public String add() throws Exception {

		model.setParent(departmentService.getById(parentId));
		departmentService.save(model);
		return "toList";
	}

	public String addUI() throws Exception {
		// 1. basic information----department info
		// 2. TODO tree-type
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList=DepartmentUtils.getAllDepartment(topList);
		ActionContext.getContext().put("departmentList", departmentList);

		return "addUI";
	}

	public String edit() throws Exception {

		// 1. get entity from DB
		Department department = departmentService.getById(model.getId());

		// 2. set attribute
		department.setName(model.getName());
		department.setDescription(model.getDescription());

		model.setParent(departmentService.getById(parentId));// superior
																// departments

		// 3.update DB
		departmentService.update(department);
		return "toList";
	}

	public String editUI() throws Exception {

		// 1.Initialization data
		// TODO tree-type
		List<Department> departmentList = null;
		if (parentId == null) {

			departmentList = departmentService.findTopList();
		} else {

			departmentList = departmentService.findChildren(parentId);
		}
		ActionContext.getContext().put("departmentList", departmentList);

		// 2.recall data and display
		Department department = departmentService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(department);
		if (department.getParent() != null) {
			parentId = department.getParent().getId();
		}

		return "editUI";
	}

	// ****************************************

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
