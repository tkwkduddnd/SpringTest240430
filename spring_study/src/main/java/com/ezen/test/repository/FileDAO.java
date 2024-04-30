package com.ezen.test.repository;

import java.util.List;

import com.ezen.test.domain.BoardVO;
import com.ezen.test.domain.FileVO;
import com.ezen.test.domain.PagingVO;

public interface FileDAO {

	int insertFile(FileVO fvo);

	List<FileVO> getFileList(int bno);

	int removeFile(String uuid);

	int updateqty(BoardVO bvo);

}
