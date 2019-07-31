package com.hsg.intro.itr.ptn.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.hsg.intro.Exception.FilesException;
import com.hsg.intro.common.files.model.service.FilesServiceImpl;
import com.hsg.intro.common.files.model.vo.Files;

@Controller
@SessionAttributes("loginUser")
public class ItrPtnController {
	@Autowired
	private FilesServiceImpl fsi;

	private String pageId = "itr/ptn";
	
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.KOREA);
	private Date currentDate = new Date();
	private String postDate = formatter.format(currentDate);

	private String root = "/hsglobal03/tomcat/webapps/var/HSG/uploadFiles";
	private String filePath = "/itrptn_upload_files";
	
	// 페이지 이동
	@RequestMapping(value = "view.ip")
	public ModelAndView view(ModelAndView mv) {
		try {
			List<Files> fs = fsi.findByPageId(pageId);
			mv.addObject("fs", fs);
		} catch(FilesException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");	  
		}
		mv.setViewName("itr/ptn/itr_ptn_00001");
		return mv;		
	}
	
	// contents 추가
	@RequestMapping(value = "insert.ip", method = RequestMethod.POST) // DI 의존성 주입 
	public ModelAndView insert(ModelAndView mv,
			@RequestParam(required=false) MultipartFile file, HttpServletRequest request) {
		Files f = new Files();
		// ################### 파일 업로드 ################### 
		if(!file.isEmpty()) { 
			String fileName = file.getOriginalFilename(); // 파일 명
			long fileSize = file.getSize(); // 파일 크기
			
			try { // 파일명 새이름 설정 
				
				int randomNumber = (int)((Math.random()*10000)+1);
				SimpleDateFormat format = new SimpleDateFormat ( "yyyyMMddHHmmss"); 
				Date nowTime = new Date();
				String newFileName = format.format(nowTime) + String.valueOf(randomNumber);
				  
				// 확장자 구하기 
				int pos = file.getOriginalFilename().lastIndexOf("."); 
				String ext = file.getOriginalFilename().substring(pos); 
				
				String storedFileName = filePath + "/" + newFileName + ext;
				String originFileName = fileName;
				
				f.setStored(storedFileName);
				f.setOrigin(originFileName);
				f.setSize(fileSize);

				// 폴더 없으면 생성 
				File uploadPath = new File(root + filePath); 
				if(!uploadPath.exists()) {
					uploadPath.mkdirs(); 
				}
				  
				// 해당 폴더에 파일 생성 
				file.transferTo(new File(root + storedFileName));
					  
			} catch (IllegalStateException e) { 
				mv.addObject("message",e.getMessage());
				mv.setViewName("common/errorPage");	  
			} catch (IOException e) { 
				mv.addObject("message",e.getMessage());
				mv.setViewName("common/errorPage");	  
			} 
		}
		// ################### 파일 업로드###################
		f.setPageId(pageId);
		f.setPostDate(postDate);
		f.setCategory(0); // 0: 이미지 / 1: 파일
		
		System.out.println(f.toString());
		
		try {
			f.setContentsId(fsi.getListCount(pageId));
			fsi.insert(f);
			List<Files> fs = fsi.findByPageId(pageId);
			mv.addObject("fs", fs);
			mv.setViewName("jsonView");		  
		} catch (Exception e) { 
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");	  
		} 
		
		return mv;  
	}
	
	// 데이터 ajax call
	@RequestMapping(value = "get.ip", produces = "application/json;charset=utf8") // DI 의존성 주입
	public ModelAndView get(ModelAndView mv, @RequestParam(defaultValue = "0") Integer currentCount) {
		try {
			List<Files> fs = fsi.findByPageId(pageId, currentCount);
			int count = fs.size();
			mv.addObject("fs", fs);
			mv.addObject("count", count);
			mv.setViewName("itr/ptn/itr_ptn_00001");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	// contents 삭제 
	@RequestMapping(value = "delete.ip", method = RequestMethod.GET) // DI 의존성 주입
	public ModelAndView delete(ModelAndView mv,
			@RequestParam(value="id") int id,
			HttpServletRequest request) throws FilesException {
		try {
			String deleteFilePath = root + fsi.findById(id).getStored();
			File deleteFile = new File(deleteFilePath); // 파일 URL
			
			if(deleteFile.exists()) {
				if(deleteFile.delete()) {
					System.out.println("파일 삭제 완료");
				} else {
					System.out.println("파일 삭제 실패");
				}
			} else {
				System.out.println("파일이 존재하지 않습니다.");
			}
			
			fsi.delete(id);
			mv.addObject("id", id);
			mv.setViewName("jsonView");		  
		} catch (FilesException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("redirect:/common/errorPage");
		}
		return mv;
	}	
}