/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename Page.java
 * @package psychlab.core.jpa
 * @author lenovoo
 * @date 2013-8-16
 */
package cn.ttsales.work.core.jpa;

import java.util.List;

/**
 * @author lenovoo
 *
 */
public class PageModel<T> {
	private List<T> rows;  
	private long total;
	private int currPage;
	
	public PageModel(){
		
	}
	
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
}
