package com.ezen.www.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.handler.PagingHandler;
import com.ezen.www.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@RequestMapping("/comment/*")
@Slf4j
public class CommentController {
	private final CommentService csv;
	
	@PostMapping(value="/post", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> post(@RequestBody CommentVO cvo){
		log.info("cvo {}", cvo);
		int isOk = csv.post(cvo);
		return isOk >0 ?
				new ResponseEntity<String>("1", HttpStatus.OK) :
					new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
		
	
	}
	@GetMapping(value = "/{bno}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PagingHandler> list(@PathVariable("bno")int bno, @PathVariable("page")int page){
		log.info(">>>> bno >> {}", bno);
		// pagingVO / PagingHandler 사용
		PagingVO pgvo = new PagingVO(page, 5);
		/* List<CommentVO> list = csv.getList(bno); */
		PagingHandler ph = csv.getList(bno, pgvo);
		return new ResponseEntity<PagingHandler>(ph, HttpStatus.OK);
	}
	
	
	  @PutMapping(value ="/modify", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE) 
	  public ResponseEntity<String> modify(@RequestBody CommentVO cvo){ 
		  int isOk = csv.modify(cvo); 
		  return isOk > 0 ? 
		  new ResponseEntity<String>("1", HttpStatus.OK) : 
			  new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR); 
		  }
	 
	
	@DeleteMapping(value = "/{cno}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> remove(@PathVariable("cno")int cno){
		int isOk = csv.delete(cno);
		
		return isOk>0 ? new ResponseEntity<String>("1", HttpStatus.OK) : 
			new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
