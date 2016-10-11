package com.xxx.oa.domain;

import java.util.List;

public class PageBean {
	
	// parameters
	private int currentPage;
	private int pageSize;
	
	// database data
	private List<Reply> recordList;
	private int recordCount;
	
	// calculation result
	private int pageCount;
	private int beginPageIndex;
	private int endPageIndex;
	
	
	
	
	public PageBean() {
		super();
	}
	public PageBean(int currentPage, int pageSize, List<Reply> recordList,
			int recordCount) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordList = recordList;
		this.recordCount = recordCount;
		System.out.println(recordList);
		
		//  ****************************
		pageCount =(recordCount+pageSize-1)/pageSize;
		
		//   beginPageIndex, endPageIndex
		// 1. pageSize<10
		
		if(pageCount<=10){
			beginPageIndex=1;
			endPageIndex=pageCount;
		}
		
		// 2. pageSize>10  
		else{
			// display currentPageNum-4 + currentPageNum  +   currentPage+5
			beginPageIndex=currentPage-4;
			endPageIndex=currentPage+5;
			
			// if beginPageIndex<1
			if(beginPageIndex<1){
				beginPageIndex=1;
				endPageIndex=10;
			}// endpageIndex >pageCount  then display all last 10 pages
			else if(endPageIndex>pageCount){
				endPageIndex=pageCount;
				beginPageIndex=pageCount-9;
			}
		} 
		
	}
	

	
	
	
	
	public List<Reply> getRecordList() {
		return recordList;
	}
	public void setRecordList(List<Reply> recordList) {
		this.recordList = recordList;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public int getBeginPageIndex() {
		return beginPageIndex;
	}
	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}
	public int getEndPageIndex() {
		return endPageIndex;
	}
	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}
	 
}
