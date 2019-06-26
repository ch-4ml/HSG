package com.hsg.intro.edu.eln.controller;

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

import com.hsg.intro.Exception.ContentsException;
import com.hsg.intro.common.contents.model.service.ContentsServiceImpl;
import com.hsg.intro.common.contents.model.vo.Contents;

@Controller
@SessionAttributes("loginUser")
public class EduElnController {
	
	@Autowired
	private ContentsServiceImpl csi;
	
	private String pageId = "edu/eln";
	
	private SimpleDateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd HH:mm", Locale.KOREA);
	private Date currentDate = new Date();
	private String postDate = formatter.format (currentDate);
	
	private String root = "/ark9659/tomcat/webapps/var/HSG/uploadFiles";
	private String filePath = "/edueln_upload_files";
	
	// 추가 페이지
	@RequestMapping(value="insertView.ee")
	public ModelAndView insertView(ModelAndView mv) {
		mv.setViewName("edu/eln/edu_eln_00002");
		return mv;
	}
	
	// 추가
	@RequestMapping(value="insert.ee", method=RequestMethod.POST)
	public ModelAndView insertEduEln(Contents c, ModelAndView mv, 
			@RequestParam(required=false) MultipartFile file, 
			HttpServletRequest request){
		// ################### 파일 업로드 ###################
		String fileName = "";
		try {
			// 파일명 새이름 설정
			int randomNumber = (int)((Math.random()*10000)+1);
			SimpleDateFormat format = new SimpleDateFormat ( "yyyyMMddHHmmss");
			Date nowTime = new Date();
			String newfileName = format.format(nowTime) + String.valueOf(randomNumber);
			
			// 확장자 구하기
			int pos = file.getOriginalFilename().lastIndexOf(".");
			String ext = file.getOriginalFilename().substring(pos);

			// /xxxxxx_upload_files/파일명.ext 형태로 객체에 넣음 
			fileName = filePath + "/" + newfileName + ext;
			c.setContents(fileName);
			
			// 폴더 없으면 생성
			File uploadPath = new File(root + filePath);
			if(!uploadPath.exists()) {
				uploadPath.mkdirs();
			}
		
			// 해당 폴더에 파일 생성
			file.transferTo(new File(root + fileName));
			
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// ################### 파일 업로드 ###################
		
		c.setPageId(pageId);
		c.setPostDate(postDate);
		
		try {
			csi.insert(c);
			mv.setViewName("redirect:view.ee");
		} catch (Exception e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 메인 페이지
	@RequestMapping(value="view.ee")
	public ModelAndView viewEduEln(ModelAndView mv) {		
		try {
			List<Contents> cs = csi.findByPageId(pageId);
			mv.addObject("cs", cs);
			mv.setViewName("edu/eln/edu_eln_00001");
		} catch(ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 상세 보기
	@RequestMapping(value="viewDetail.ee")
	public ModelAndView viewEduElnDetail(ModelAndView mv,
			@RequestParam(value="id") int id) {
		try {
			Contents c = csi.findById(id);
			mv.addObject("c", c);
			mv.setViewName("edu/eln/edu_eln_00003");
		} catch(ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 업데이트 폼
		@RequestMapping(value="updateView.ee")
		public ModelAndView ubdateEmpLec(ModelAndView mv, @RequestParam(value="id") int id) {
			try {
				Contents c = csi.findById(id);
				mv.addObject("c", c);
				mv.setViewName("edu/eln/edu_eln_00004");
			} catch (ContentsException e) {
				mv.addObject("message",e.getMessage());
				mv.setViewName("redirect:/common/errorPage");
			}
			return mv;
		}
	
	// 업데이트(검색한 페이지로 돌아가게 하려면 파라미터 추가)
	@RequestMapping(value="update.ee", method=RequestMethod.POST) // DI 의존성 주입
	public ModelAndView updateEduEln(Contents c, ModelAndView mv, 
			@RequestParam(required=false) MultipartFile file, 
			HttpServletRequest request) throws ContentsException {
		try {
			System.out.println("#################### update.ee file.isEmpty() : " + file.isEmpty() + "####################");
			System.out.println("#################### update.ee content : " + c + "####################");
			
			if(!file.isEmpty()) { // 파일이 null 일 경우
				String fileName = "";
				// ##################### 파일 삭제 처리 #######################
				String deleteFileName = csi.findById(c.getId()).getContents();

				// 파일명 새이름 설정
				int randomNumber = (int)((Math.random()*10000)+1);
				SimpleDateFormat format = new SimpleDateFormat ("yyyyMMddHHmmss");
				Date nowTime = new Date();
				String newfileName = format.format(nowTime) + String.valueOf(randomNumber);	
				
				// 확장자 구하기
				int pos = file.getOriginalFilename().lastIndexOf(".");
				String ext = file.getOriginalFilename().substring(pos);
				
				// /xxxxxx_upload_files/파일명.ext 형태로 객체에 넣음 
				fileName = filePath + "/" + newfileName + ext;
				c.setContents(fileName);
				
				// 폴더 없으면 생성
				File uploadPath = new File(root + filePath);
				if(!uploadPath.exists()) {
					uploadPath.mkdirs();
				}
			
				// 해당 폴더에 파일 생성
				file.transferTo(new File(root + fileName));
				
				// ##################### 파일 삭제 처리 #######################
				String deleteFilePath = root + deleteFileName;
				
				// ##################### 파일 삭제 처리 #######################
				File deleteFile = new File(deleteFilePath); // 파일 URL
				
				System.out.println("#################### update.ee deleteFilePath : " + deleteFilePath + "####################");
				
				if(deleteFile.exists()) {
					if(deleteFile.delete()) {
						System.out.println("파일 삭제 완료");
					} else {
						System.out.println("파일 삭제 실패");
					}
				} else {
					System.out.println("파일이 존재하지 않습니다.");
				}
			}
			
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		String param = ""; // 검색 기록 남길 때
		c.setPageId(pageId);
		c.setPostDate(postDate);
		
		try {
			csi.update(c);
			mv.setViewName("redirect:viewDetail.ee?id=" + c.getId());
		} catch (ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}		
		return mv;
	}
	
	// 삭제 (검색한 페이지로 돌아가게 하려면 파라미터 추가)
	@RequestMapping(value="delete.ee")
	public ModelAndView deleteEduEln(ModelAndView mv,
		@RequestParam(value="id") int id, 
		HttpServletRequest request) {
		
		String param = ""; // 검색 기록 남길 때
		try {
			String deleteFileName = csi.findById(id).getContents();
			// ##################### 파일 삭제 처리 #######################
			String deleteFilePath = root + deleteFileName;
			
			// ##################### 파일 삭제 처리 #######################
			File deleteFile = new File(deleteFilePath); // 파일 URL
			
			System.out.println("#################### update.ee deleteFilePath : " + deleteFilePath + "####################");
			
			if(deleteFile.exists()) {
				if(deleteFile.delete()) {
					System.out.println("파일 삭제 완료");
				} else {
					System.out.println("파일 삭제 실패");
				}
			} else {
				System.out.println("파일이 존재하지 않습니다.");
			}
			csi.delete(id);
			mv.setViewName("redirect:view.ee");
		} catch(ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 외부 url 접근
	@RequestMapping(value = "redirect.ee", method = RequestMethod.GET) // DI 의존성 주입
	public ModelAndView viewIbkBokDetail(ModelAndView mv, @RequestParam(value="url") String url){
		if(url.indexOf("http://") != -1)
			mv.setViewName("redirect:" + url);
		else if(url.indexOf("https://") != -1)
			mv.setViewName("redirect:" + url);
		else	
			mv.setViewName("redirect:http://" + url);
		return mv;
	}
}
