package com.xxx.oa.utils;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.xxx.oa.base.BaseDao;
import com.xxx.oa.domain.PageBean;


/**
 * used to assist to generate HQL sentence
 * 
 * @author Peng
 *
 */
public class HqlHelper {
	// 1. From sentence (indispensable)
	// 2. Where sentence (dispensable)
	// 3. orderby sentence (dispensable)

	private String fromClause="";
	private String whereClause = "";
	private String orderByClause="";

	private List<Object> parameters = new ArrayList<Object>(); // parameter list

	/**
	 * @return
	 */
	public List<Object> getParameters() {
		return parameters;
	}

	/**
	 * generate a fromClause, default name is o
	 * 
	 * @param clazz
	 */
	public HqlHelper(Class clazz) {
		this.fromClause = " FROM " + clazz.getSimpleName() + " o ";
	}

	/**
	 * generate a fromClause, use specific nickName alias
	 * 
	 * @param clazz
	 */
	public HqlHelper(Class clazz, String alias) {
		this.fromClause = " FROM " + clazz.getSimpleName() + " " + alias;
	}

	/**
	 * joint whereClause
	 * 
	 * @param condition
	 * @param params
	 */
	/*
	 * public void addWhereCondition(String condition, Object... params){
	 * 
	 * }
	 */
	public HqlHelper addWhereCondition(String condition, Object... params) {
		//joint whereClause
		if (whereClause.length() == 0) {
			whereClause = " WHERE " + condition;
		} else {
			whereClause += " AND " + condition;
		}
		// append parameters
		if(params !=null && params.length>0){
			for(Object obj: params){
				parameters.add(obj);
				
			}
		}
		return this;
	}

	/**
	 * joint whereClause
	 * 
	 * @param append
	 *            if(append is true) joint clause
	 * @param condition
	 * @param params
	 */
	/*
	 * public void addWhereCondition(boolean append,String condition, Object...
	 * params){ if(append){ addWhereCondition(condition, params); } }
	 */
	public HqlHelper addWhereCondition(boolean append, String condition,
			Object... params) {
		if (append) {
			addWhereCondition(condition, params);
		}
		return this;
	}

	/**
	 * joint orderByCLause
	 *
	 * @param propertyName
	 * @param isAsc
	 *            ?(true : ase ; false: desc)
	 */
	/*
	 * public void addOrder(String propertyName, boolean isAsc){
	 * 
	 * }
	 */

	public HqlHelper addOrder(String propertyName, boolean isAsc) {
		
		if(orderByClause.length()==0){
			orderByClause=" ORDER BY "+propertyName +(isAsc ? " ASC " : " DESC ");
		}else{
			orderByClause += ", " +propertyName+(isAsc ? " ASC " : " DESC ");
		}
		return this;
	}

	/**
	 * joint orderByCLause
	 *
	 * @param if(append is true) joint clause
	 * @param propertyName
	 * @param isAsc
	 *            ?(true : ase ; false: desc)
	 */
	/*
	 * public void addOrder(boolean append,String propertyName, boolean isAsc){
	 * if(append){ addOrder(propertyName, isAsc); } }
	 */

	public HqlHelper addOrder(boolean append, String propertyName, boolean isAsc) {
		if (append) {
			addOrder(propertyName, isAsc);
		}
		return this;
	}

	/**
	 * get generation of QueryListHql
	 * @return a integral Hql
	 */
	public String getQueryListHql() {
		return fromClause + whereClause + orderByClause;
	}
	/**
	 * get generation of QueryCountHql
	 * @return a integral Hql
	 */
	public String getQueryCountHql() {
		return "SELECT COUNT(*) "+ fromClause + whereClause ;
	}

	/**
	 * 
	 * @param pageNum
	 * @param hqlHelper
	 * @return
	 */
	public HqlHelper buildPageBeanForStruts2(int pageNum, BaseDao<?> service) {
		PageBean pageBean = service.getPageBean(pageNum, this);
		ActionContext.getContext().getValueStack().push(pageBean);
		return this;
	}
}
