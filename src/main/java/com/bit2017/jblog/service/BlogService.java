package com.bit2017.jblog.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bit2017.jblog.repository.BlogDao;
import com.bit2017.jblog.vo.BlogVo;


@Service
public class BlogService {

	private static final String SAVE_PATH = "/jblog_logo";

	@Autowired
	private BlogDao blogDao;
	
	public BlogVo getInfo(String id) {
		return blogDao.getInfo(id);
	}
	
	public boolean updateInfo(BlogVo blogVo, MultipartFile file) {
		
		
		if( ! file.isEmpty()) {
		
			try {
			//blogID 로 기존 이미지 삭제
				String existFileName = getInfo(blogVo.getBlogID()).getLogo(); 
				System.out.println(existFileName);
				deletFile(existFileName);
				
				String oriFileName = file.getOriginalFilename(); 
				String extName = oriFileName.substring(oriFileName.lastIndexOf(".")+1, oriFileName.length());
				String saveFileName = GenerateSaveFileName(extName); 
			
				writeFile(file, saveFileName);
				blogVo.setLogo(saveFileName);
				
			} catch (IOException e) {
				new RuntimeException( "Upload file:" + e );
			}
			
			
		}
		
		return blogDao.updateInfo(blogVo);
	}
	
	
	private String GenerateSaveFileName(String extName) {
		String fileName = "";
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += ("." + extName); 

		return fileName;
	}
	
	private void writeFile(MultipartFile file, String saveFileName) throws IOException {
		byte[] data = file.getBytes();
		
		FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
		fos.write(data);
		fos.close();
	}
	
	private void deletFile(String deleteFileName) throws IOException {
		File file = new File(SAVE_PATH + "/" + deleteFileName);
		if ( file.delete() ){
			System.out.println("정상삭제");
		}
	}
	
	
}
