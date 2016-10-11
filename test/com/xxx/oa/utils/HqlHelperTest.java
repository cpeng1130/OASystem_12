package com.xxx.oa.utils;

import org.junit.Test;

import com.xxx.oa.domain.Forum;
import com.xxx.oa.domain.Topic;

public class HqlHelperTest {

	/**
	 * 0 represent all topics <br>
	 * 1 represent gorgeous topics
	 */
	private int viewType = 1;

	/**
	 * default sort orderBy=0;<br>
	 * last update .......=1;<br>
	 * created time of topoc=2;<br>
	 * count of reply.......=3; <br>
	 */
	private int orderBy = 0;
	/**
	 * true represent <br>
	 * false represent
	 */
	private boolean asc = false;

	private Forum forum = new Forum();

	@Test
	public void testHqlHelper() {

		HqlHelper hqlHelper = new HqlHelper(Topic.class, "t")
		// hqlHelper.addWhereCondition(viewType==1,"t.forum=? ", forum);

		// option of filter
		/*
		 * if(viewType==1){ hqlHelper.addWhereCondition("t.type=? ",
		 * Topic.TYPE_BEST); }
		 */
		// option of sort
		/*
		 * if(orderBy==0){
		 * hqlHelper.addOrder(orderBy==0,"(CASE t.type WHEN 2 THEN 2 ELSE 0 END) "
		 * , false); hqlHelper.addOrder(orderBy==0," t.lastUpdateTime ", false);
		 * //}else if(orderBy==1){
		 * hqlHelper.addOrder(orderBy==1," t.lastUpdateTime ", asc); //}else
		 * if(orderBy==2){ hqlHelper.addOrder(orderBy==2," t.postTime ", asc);
		 * 
		 * //}else if(orderBy==3){
		 * hqlHelper.addOrder(orderBy==3," t.replyCount ", asc);
		 */

		.addWhereCondition("t.forum=? ", forum)
				.addWhereCondition(viewType == 1, "t.type=? ", Topic.TYPE_BEST)
				.addOrder(orderBy == 1, " t.lastUpdateTime ", asc)
				.addOrder(orderBy == 2, " t.postTime ", asc)
				.addOrder(orderBy == 3, " t.replyCount ", asc)
				.addOrder(orderBy == 0, " t.type ", false)
				.addOrder(orderBy == 0, " t.lastUpdateTime ", false);
		System.out.println(hqlHelper.getQueryListHql());
		System.out.println(hqlHelper.getParameters());

	}

}
