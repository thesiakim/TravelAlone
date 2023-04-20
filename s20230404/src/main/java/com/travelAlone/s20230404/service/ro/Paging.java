package com.travelAlone.s20230404.service.ro;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Paging {
	private int currentPage = 1;	private int rowPage = 10;
	private int	pageBlock	= 10;
	private int startRow;			private int endRow;
	private int startPage;			private int endPage;
	private int totalRow;				private int totalPage;
	
	public Paging(int totalRow, String currentPage1) {
		this.totalRow = totalRow;
		
		if(currentPage1 != null) {
			this.currentPage = Integer.parseInt(currentPage1);
		}
		
		startRow = (currentPage - 1) * rowPage + 1;
		endRow	 = startRow + rowPage - 1;
		
		
		startPage = currentPage - (currentPage - 1) % pageBlock;
		endPage	  = startPage + pageBlock - 1;
		
		
		// 전체 Page 확인
		totalPage = (int) Math.ceil((double)totalRow / rowPage);
		
		// 공갈 Page 제거
		if (endPage > totalPage) {
			endPage = totalPage;
		}
	}
}
