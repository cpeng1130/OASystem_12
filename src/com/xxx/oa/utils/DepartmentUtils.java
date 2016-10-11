package com.xxx.oa.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.xxx.oa.domain.Department;

public class DepartmentUtils {

	public static List<Department> getAllDepartment(List<Department> topList) {
		List<Department> list= new ArrayList<Department>();
		scanDepartmentTree(topList,"",list);
		
		return list;
	}
	public static void scanDepartmentTree(Collection<Department> topList,String prefix,List<Department> list){
		for(Department top:topList){
			top.setName(prefix+top.getName());
			list.add(top);
			scanDepartmentTree(top.getChildren(),prefix,list);
		}
		
	}
}
