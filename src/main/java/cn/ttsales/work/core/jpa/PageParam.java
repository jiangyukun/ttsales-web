/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename DataPageModel.java
 * @package ratan.bds.web.common
 * @author lenovoo
 * @date 2013-9-10
 */
package cn.ttsales.work.core.jpa;

import java.io.Serializable;

/**
 * @author lenovoo
 *
 */
public class PageParam implements Serializable{
	private static final long serialVersionUID = 1L;
	private int page = 1;
	private int rows = 10;
	
	public PageParam(){
		
	}
	
	public PageParam(int page,int rows){
		this.page = page;
		this.rows = rows;
	}
		
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
}	
