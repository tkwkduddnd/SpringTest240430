package com.ezen.www.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.www.domain.FileVO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@Component // 사용자 클래스를 반으로 등록
public class FileHandler {
	private final String UP_DIR ="C:\\_myProject\\_java\\_fileUpload";
	
	public List<FileVO> uploadFiles(MultipartFile[] files){
		List<FileVO> flist = new ArrayList<>();
		//FileVO 생성, 파일을 저장, 썸네일 저장
		// 날짜로 폴더를 생성하여 업로드 파일을 관리
		LocalDate date = LocalDate.now(); // 2024-05-03
		String today = date.toString(); // String으로 변환
		today = today.replace("-", File.separator); // -를 파일 경로 표시로 변환 \(win)   /(mac)
		
//		C:\\_myProject\\_java\\_fileUpload\\2024\\05\\03
		File folders = new File(UP_DIR, today);
		
		
		//폴더 생성
		if(!folders.exists()) {
			folders.mkdirs(); // mkdirs() : 하위 폴더까지 생성
			
		}
		
		// files를 가지고 객체 설정
		for(MultipartFile file: files) {
			FileVO fvo = new FileVO();
			fvo.setSaveDir(today);
			fvo.setFileSize(file.getSize());
			
			String originalFileName = file.getOriginalFilename();
			String fileName = originalFileName.substring(
					originalFileName.lastIndexOf(File.separator)+1);
			fvo.setFileName(fileName);
			
			UUID uuid = UUID.randomUUID();
			String uuidStr = uuid.toString();
			fvo.setUuid(uuidStr);
			
			String fullFileName = uuidStr + "_" + fileName;
			File storeFile = new File(folders, fullFileName);
			
			try {
				file.transferTo(storeFile); // 저장
				if(isImageFIle(storeFile)) {
					fvo.setFileType(1); // 이미지는 타입이 1
					File thumbNail = new File(folders, uuidStr+"_th_"+fileName);
					Thumbnails.of(storeFile).size(100, 100).toFile(thumbNail);
				}
			} catch (Exception e) {
				log.info("파일 저장 오류");
				e.printStackTrace();
			}
			flist.add(fvo);
		}
		return flist;

		
	}
	private boolean isImageFIle(File storeFile) throws IOException{
		String mimeType = new Tika().detect(storeFile);
		return mimeType.startsWith("image")? true : false;
	}
	
}
