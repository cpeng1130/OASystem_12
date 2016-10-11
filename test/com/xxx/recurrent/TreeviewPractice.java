package com.xxx.recurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.xxx.oa.domain.Department;

public class TreeviewPractice {
	
	@Test
	public void printAllDepts_1(){
		List <Department> topList=findTopLevelDepartmentList();
		/*	for(int i=0;i<topList.size();i++){
				showTree(topList.get(i));
			}*/
		showTreeList(topList);
		System.out.println("**************************************************************");
		showTreeList_02(topList,"|-");
		
	}
	
	/**
	 * display -----> single tree
	 * @return
	 */
	public void showTree(Department dept){
	
		System.out.println(dept.getName());
		Set<Department> depts=dept.getChildren();
		for(Department d:depts){
			showTree(d);
		}
	}
	
	/**
	 * display-----> tree list
	 * 
	 */
	public void showTreeList(Collection<Department> topList ){
		for(Department top:topList){
			System.out.println("--|"+top.getName());
			showTreeList(top.getChildren());
		}
	}
	public void showTreeList_02(Collection<Department> topList ,String space){
		
		for(Department top:topList){
			System.out.println(space+top.getName());
			showTreeList_02(top.getChildren(),"  "+space);
		}
	}
	
	
	public void printAllDepts_2(){
		List<Department> topList=findTopLevelDepartmentList();
	}
	
	public static List<Department> findTopLevelDepartmentList() {
		Department dept_1_1 = new Department();
		dept_1_1.setId(new Long(11));
		dept_1_1.setName("宣传部");

		Department dept_1_2 = new Department();
		dept_1_2.setId(new Long(12));
		dept_1_2.setName("业务部");

		Department dept_1_2_1 = new Department();
		dept_1_2_1.setId(new Long(121));
		dept_1_2_1.setName("业务一部");

		Department dept_1_2_2 = new Department();
		dept_1_2_2.setId(new Long(122));
		dept_1_2_2.setName("业务二部");

		dept_1_2_1.setParent(dept_1_2);
		dept_1_2_2.setParent(dept_1_2);
		Set<Department> children_0 = new LinkedHashSet<Department>();
		children_0.add(dept_1_2_1);
		children_0.add(dept_1_2_2);
		dept_1_2.setChildren(children_0);

		// ================================

		Department dept_1 = new Department();
		dept_1.setId(new Long(1));
		dept_1.setName("市场部");

		dept_1_1.setParent(dept_1);
		dept_1_2.setParent(dept_1);
		Set<Department> children_1 = new LinkedHashSet<Department>();
		children_1.add(dept_1_1);
		children_1.add(dept_1_2);
		dept_1.setChildren(children_1);

		// ---

		Department dept_2_1 = new Department();
		dept_2_1.setId(new Long(21));
		dept_2_1.setName("开发一部");

		Department dept_2_2 = new Department();
		dept_2_2.setId((new Long(22)));
		dept_2_2.setName("开发二部");

		Department dept_2 = new Department();
		dept_2.setId(new Long(2));
		dept_2.setName("开发部");

		dept_2_1.setParent(dept_2);
		dept_2_2.setParent(dept_2);
		Set<Department> children_2 = new LinkedHashSet<Department>();
		children_2.add(dept_2_1);
		children_2.add(dept_2_2);
		dept_2.setChildren(children_2);

		// ---

		List<Department> depts = new ArrayList<Department>();
		depts.add(dept_1);
		depts.add(dept_2);
		return depts;
	}

}
