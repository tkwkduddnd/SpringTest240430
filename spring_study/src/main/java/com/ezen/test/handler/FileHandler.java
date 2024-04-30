package com.ezen.test.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.test.domain.FileVO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@Component
public class FileHandler {
	
	private final String UP_DIR ="C:\\_myProject\\_java\\_fileUpload";

	public List<FileVO> uploadFiles(MultipartFile[] files) {
		// 리턴 객체 생성
		List<FileVO> flist = new ArrayList<FileVO>();
		
		// MultipartFile[] 받아서 FileVO 형태의 List로 생성 후 리턴
		// 오늘 날짜로 경로 생성(가변 형태로 저장) 년 월 일 폴더를 구성
		LocalDate date = LocalDate.now();
		String today = date.toString();
		log.info("today {}", today);
		// 오늘 날짜를 폴더형식으로 구성
		today = today.replace("-", File.separator);
		File folders = new File(UP_DIR, today);
		
		// 폴더 생성 => mkdir (폴더 1개 생성), mkdir(하위 폴더까지 구조로 생성)
		// exists : 있는지 없는지 확인
		if(!folders.exists()) {
			folders.mkdirs(); // 폴더 생성
		}
		// 리스트 설정
		for(MultipartFile file : files) {
			FileVO fvo = new FileVO();
			fvo.setSave_dir(today);
			fvo.setFile_size(file.getSize()); // return long
			
			//getOriginalFilename() : 경로 + 파일명. 파일경로를 포함하는 케이스도 있음
			String originalFileName = file.getOriginalFilename();
			String onlyFileName = originalFileName.substring(
					originalFileName.lastIndexOf(File.separator)+1); // 실제 파일명만 추출
			fvo.setFile_name(onlyFileName);
			
			// UUId 생성
			UUID uuid = UUID.randomUUID();
			String uuidStr = uuid.toString();
			fvo.setUuid(uuidStr);
			
			// 여기까지 fvo Setting 완료
			// bno, file_type
			// 디스크에 저장
			// 디스크에 저장할 파일 객체 생성 -> 저장
			String fullFileName = uuidStr+"_"+onlyFileName;
			File storeFile = new File(folders, fullFileName);
			// 저장 => 저장경로 또는 파일이 없다면 IOException 발생
			
			try {
				file.transferTo(storeFile);
				//파일 타입을 결정 => 이미지만 썸네일 저장
				if(isImageFile(storeFile)) {
					fvo.setFile_type(1);
					
					File thumbNail = new File(folders, uuidStr+"_th_"+onlyFileName);
					Thumbnails.of(storeFile).size(75, 75).toFile(thumbNail);
				}
			} catch (Exception e) {
				log.info("file 에러");
				e.printStackTrace();
			}
			flist.add(fvo);
		}
		return flist;
		
	}
//tika를 활용한 파일 형식 체크 => 이미지 파일이 맞는지 확인
	public boolean isImageFile(File storeFile) throws IOException {
		String mimeType = new Tika().detect(storeFile); // image/png   image/jpg
		return mimeType.startsWith("image")? true : false;
	}
	
	
}


