package cn.sjlm.sjlmapi.util;

import java.io.Serializable;

public class PageObject implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -8753809986545361268L;
	
	/**当前页*/
	private int pageCurrent = 1;
	/**每页显示的记录数*/
	private int pageSize = 5;
	/**总记录数*/
	private int rowCount;
	/**总页数*/
	private int pageCount;
	/**起始页*/
	private int startIndex;
	
	public int getPageCurrent() {
		return pageCurrent;
	}
	public void setPageCurrent(int pageCurrent) {
		this.pageCurrent = pageCurrent;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getPageCount() {
		this.pageCount = rowCount/pageSize;
		if (rowCount%pageSize != 0) {
			this.pageCount++;
		}
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getStartIndex() {
		this.startIndex = (pageCurrent-1) * pageSize;
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
