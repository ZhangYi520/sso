package com.zy.sso.file.vo;


public class PageParam{

	/**
	 * 当前页编号默认为1
	 */
	private Integer page = 1;
	/**
	 * 每页显示条目数
	 */
	private Integer pageSize = 10;
	/**
	 * 总页数
	 */
	private Integer pages = 0;
	/**
	 * 总条数
	 */
	private Integer total = 0;
	/**
	 * 分页-开始位置
	 */
	private Integer startIndex=0;
	/**
	 * 分页-结束位置
	 */
	private Integer endIndex=pageSize;
	
	
//	this.startIndex = (this.page-1) * pageSize;
//	this.endIndex = pageSize;
	/**
	 * 第三方标识
	 */
	private String tag="";
	/**
	 * 是否分页
	 */
	private boolean isExecutePage=true;
	
	public boolean isExecutePage() {
	
		return isExecutePage;
	}

	public void setExecutePage(boolean isExecutePage) {
	
		this.isExecutePage = isExecutePage;
	}

	public String getTag() {
	
		return tag;
	}

	public void setTag(String tag) {
	
		this.tag = tag;
	}

	public void calc(int total) {
		// 计算startIndex和endIndex
		this.total = total;
		// 设置基本参数
		this.pages = (this.total%this.pageSize)!=0?(this.total/this.pageSize+1):(this.total/this.pageSize);

		// 根据输入可能错误的当前号码进行自动纠正
		if (page < 1) {
			this.page = 1;
		} else if (page > this.pages) {
			this.page = this.pages;
		}
		
		this.startIndex = (this.page-1) * pageSize;
		this.endIndex = pageSize;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}

	public Integer getStartIndex() {
		return this.startIndex;
	}

	public Integer getEndIndex() {
		return this.endIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPages() {
	
		return pages;
	}

	public void setPages(Integer pages) {
	
		this.pages = pages;
	}

	public Integer getTotal() {
	
		return total;
	}

	public void setTotal(Integer total) {
	
		this.total = total;
	}
}
