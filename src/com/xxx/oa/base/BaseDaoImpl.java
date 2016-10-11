package com.xxx.oa.base;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.xxx.oa.cfg.Configuration;
import com.xxx.oa.domain.PageBean;
import com.xxx.oa.domain.Reply;
import com.xxx.oa.utils.HqlHelper;




@SuppressWarnings("unchecked")
@Transactional
public class BaseDaoImpl<T> implements BaseDao<T> {
	@Resource
	private SessionFactory sessionFactory;
	private Class<T> clazz;
	
	public BaseDaoImpl(){
		
		
		// use reflect to get type of  T
		ParameterizedType pt=(ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz=(Class<T>) pt.getActualTypeArguments()[0];
		System.out.println("class= "+clazz.getName());
	}

	public void save(T entity) {
		getSession().save(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	public void delete(long id) {
		Object obj = getSession().get(clazz, id);
		getSession().delete(obj);
	}


	public T getById(Long id) {
		if(id==null){
			return null;
		}
		return (T) getSession().get(clazz, id);
	}

	public List<T> getByIds(Long[] ids) {
		if(ids==null || ids.length==0){
			return  Collections.EMPTY_LIST;
		}
		
		return getSession().createQuery(" FROM "+clazz.getSimpleName()+" WHERE id IN (:ids)").setParameterList("ids", ids).list();
	}

	public List<T> findAll() {
		return getSession().createQuery(" FROM "
	+ clazz.getSimpleName())
	.list();
	}

	/**
	 * get current session
	 * 
	 * @return
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}


	//public method to pagination
	public PageBean getPageBean(int pageNum, String queryListHQL,
			Object[] parameters) {
		System.out.println("==================== ");
		int pageSize= Configuration.getPageSize();
		
		// get content of currentpage
		
		Query listQuery= getSession().createQuery(queryListHQL);
		if(parameters!=null && parameters.length>0){
			for(int i=0;i<parameters.length;i++){
				listQuery.setParameter(i, parameters[i]);
			}
		}
		listQuery.setFirstResult((pageNum-1)*pageSize);
		listQuery.setMaxResults(pageSize);
		List list=listQuery.list();
		
		
		//
		Query countQuery=getSession().createQuery(" SELECT COUNT(*) "+queryListHQL);
		if(parameters!=null && parameters.length>0){
			for(int i=0;i<parameters.length;i++){
				
				countQuery.setParameter(i, parameters[i]);
			}
		}
			Long count=	(Long) countQuery.uniqueResult();
		return new PageBean(pageNum, pageSize, list, count.intValue());
	
	}

	
	// Final version 
	@Override
	public PageBean getPageBean(int pageNum, HqlHelper hqlHelper) {
		
		System.out.println("==================== getPageBean(int pageNum, HqlHelper hqlHelper)");
		int pageSize= Configuration.getPageSize();
		
		List<Object> parameters =hqlHelper.getParameters();
		// get content of currentpage
		
		Query listQuery= getSession().createQuery(hqlHelper.getQueryListHql());
		if(parameters!=null && parameters.size()>0){
			for(int i=0;i<parameters.size();i++){
				listQuery.setParameter(i, parameters.get(i));
			}
		}
		listQuery.setFirstResult((pageNum-1)*pageSize);
		listQuery.setMaxResults(pageSize);
		List list=listQuery.list();
		
		
		//
		Query countQuery=getSession().createQuery(hqlHelper.getQueryCountHql());
		if(parameters!=null && parameters.size()>0){
			for(int i=0;i<parameters.size();i++){
				
				countQuery.setParameter(i, parameters.get(i));
			}
		}
			Long count=	(Long) countQuery.uniqueResult();
		return new PageBean(pageNum, pageSize, list, count.intValue());
	}
}
