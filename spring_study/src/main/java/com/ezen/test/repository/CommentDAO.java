package com.ezen.test.repository;

import java.util.List;

import com.ezen.test.domain.CommentVO;

public interface CommentDAO {

	int post(CommentVO cvo);

	List<CommentVO> getList(int bno);

	int modify(CommentVO cvo);

	int delete(int cno);

}
