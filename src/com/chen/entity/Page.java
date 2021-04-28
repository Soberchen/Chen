package com.chen.entity;

import java.util.List;

public class Page<T> {
		private int pageCount;
		
		private int totalCount;
		
		private int currPageNo;
		
		private int pageSize;
		
		private List<T> pageList;
	
		public Page() {}

		public Page(int currPageNo, int pageSize) {
			this.currPageNo = currPageNo;
			this.pageSize = pageSize;
		}

		public int getPageCount() {
			return pageCount;
		}

		public void setPageCount(int pageCount) {
			this.pageCount = pageCount;
		}

		public int getTotalCount() {
			return totalCount;
		}

		public void setTotalCount(int totalCount) {
		
			if(totalCount > 0) {
				this.totalCount = totalCount;
			
				pageCount = totalCount % pageSize == 0 ?(this.totalCount / pageSize):(this.totalCount / pageSize +1);
			}
		}

		public int getCurrPageNo() {
			return currPageNo;
		}

		public void setCurrPageNo(int currPageNo) {
			this.currPageNo = currPageNo;
		}

		public int getPageSize() {
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			
			if(pageSize > 0 ) {
				this.pageSize = pageSize;
			}
		}

		public List<T> getPageList() {
			return pageList;
		}

		public void setPageList(List<T> pageList) {
			this.pageList = pageList;
		}

}
