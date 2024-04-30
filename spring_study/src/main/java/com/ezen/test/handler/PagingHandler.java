package com.ezen.test.handler;

import com.ezen.test.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class PagingHandler {
	// list 화면 하단의 페이지 조작부 기능을 담당하는 변수들을 생성
	// 시작 페이지 번호/ 끝 페이지 번호( 한 페이지에서 보여지는 페이지네이션)
	// 진짜 끝 페이지 번호
	// 이전, 다음이 생성되는 여부
	// 전체 게시글 수 , 현재 페이지 번호, 한페이지에 들어갈 게시글 수
	private int startPage;
	private int endPage;
	private boolean prev, next;
	private int realEnd;
	private int totalCount;
	private PagingVO pgvo;
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		
		//1~10 11~20 21~30
		//pageNo => 1 / 10 => 0.1(올림) => 1*10 => 10
		//pageNo => 2 / 10 => 0.2(올림) => 1*10 => 10
		//pageNo => 9 / 10 => 0.1(올림) => 1*10 => 10
		//pageNo => 11 / 10 => 1.1(올림) => 2*10 => 20
		this.endPage = (int)Math.ceil(pgvo.getPageNo() / (double)10)*10;
		this.startPage = endPage - 9;
		
		// 최종 끝 페이지
		// 게시글 103개 => 103 / 10 => 10.3(올림) => 11페이지
		this.realEnd = (int)Math.ceil(totalCount / (double)pgvo.getQty());
		
		if(realEnd < endPage) {
			this.endPage = realEnd;
			
		}
		this.prev = this.startPage > 1; // 1 / 11 / 21
		this.next = this.endPage < realEnd;
	}
}
