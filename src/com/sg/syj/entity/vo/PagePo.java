package com.sg.syj.entity.vo;

import java.util.List;

/***
 * 分页对象
 * @author czm
 *
 */
public class PagePo<T> {
	//结果集
	public List<T> rows = null;
  //总条数
	public long total = -1;
	//总页数
	public int totalPage= 0;
	//当前页
	protected int pageNumber = 1;
	//页大小
	protected int pageSize = 10;

	public PagePo(int pageNumber, int pageSize, long total, List<T> rows) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.total = total;
		this.rows = rows;
	}
	public PagePo() { }

	public List<T> getList() {
		return rows;
	}
	public void setList(List<T> list) {
		this.rows = list;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public int getTotalPage() {
		totalPage = (int)Math.ceil((double)total / (double)getPageSize());
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNo) {
		this.pageNumber = pageNo;
		if(pageNo < 1) {
			this.pageNumber = 1;
		}
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		if(pageSize < 1) {
			this.pageSize = 1;
		}
	}
}
