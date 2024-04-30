package com.ezen.test.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class PagingVO {
	// select * from board limit 0, 10;
	// 페이징 => pageNo / qty
	// 검색 => type / keyword
	private int pageNo;
	private int qty; // 한 화면에 보여줄 게시글 수 (10개)
	
	private String type;
	private String keyword;
	
	public PagingVO() {
		this.pageNo = 1;
		this.qty = 10;
		
	}
	public int getPageStart() {
		// DB 상에서 limit의 시작 번지를 구하는 메서드
		// 1페이지일때 0, 2페이지일때 10, 3페이지일때 20
		return(this.pageNo - 1) * this.qty;
	}
	// 여러가지 타입을 같이 검색하기 위해서 타입을 배열로 구분
	// tcw => t c w 
	public String[] getTypeToArray() {
		return this.type == null ? new String[] {} : this.type.split("");
	}
	
	
}
