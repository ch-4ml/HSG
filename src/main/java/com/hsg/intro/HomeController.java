package com.hsg.intro;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hsg.intro.Exception.ContentsException;
import com.hsg.intro.common.contents.model.service.ContentsServiceImpl;
import com.hsg.intro.common.contents.model.vo.Contents;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private ContentsServiceImpl csi;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private String pageId = "";
	
	SimpleDateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd HH:mm", Locale.KOREA);
	Date currentDate = new Date();
	String postDate = formatter.format (currentDate);
	
	@RequestMapping(value = "/")
	public ModelAndView home(ModelAndView mv, Locale locale) {
		logger.info("Welcome home! The client locale is {}.", locale);	
		String contents = "";
		try {
			// 메인 화면 소개 영상
			pageId = "main/itr";
			Contents c = csi.findOneByPageId(pageId);	
			
			System.out.println("c : " + c.toString());
			
			// 소개 영상 데이터 가공
			if(c.getContents() != null) {
				if(c.getContents().indexOf("www.youtube.com/embed/") != -1) {
					// 유튜브 동영상 태그 추출
					contents = c.getContents().substring(c.getContents().indexOf("www.youtube.com/embed/") - 15, c.getContents().lastIndexOf("allowfullscreen") + 26);
				}
			}
			c.setText(c.getContents().replace(contents, ""));
			c.setContents(contents);
			mv.addObject("itr", c);
			
			// 메인 화면 개발
			pageId = "main/dev";
			List<Contents> cs = csi.findByPageId(pageId);
			
			mv.addObject("dev", cs);
			mv.setViewName("main/index");
		} catch(ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="view.ma")
	public String view() {
		return "redirect:/";
	}
	
	@RequestMapping(value="updateItr.ma")
	public ModelAndView updateItr(Contents c, ModelAndView mv) {
		pageId = "main/mooc";
		c.setPageId(pageId);
		try {
			csi.update(c);
			mv.setViewName("redirect:/");
		} catch (ContentsException e) {
			mv.addObject("message", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="insertDevView.ma")
	public ModelAndView insertDevView(ModelAndView mv){
		
		mv.setViewName("main/dev/main_dev_00001");
		return mv;
	}
	
	@RequestMapping(value="insertDev.ma", method=RequestMethod.POST)
	public ModelAndView insertDev(Contents c, ModelAndView mv, 
			@RequestParam(required=false) MultipartFile file,
			HttpServletRequest request) {
		pageId = "main/dev";
		
		// ################### 파일 업로드 ###################
		String root = request.getSession().getServletContext().getRealPath("resources");
		String filePath = root + "/uploadFiles/maindev_upload_file";
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
			fileName = newfileName + ext;
			c.setContents(fileName);
			
			// 폴더 없으면 생성
			File uploadPath = new File(filePath);
			if(!uploadPath.exists()) {
				uploadPath.mkdirs();
			}
			
			//파일경로를 maindev 객체에 넣어줌
			filePath = filePath + "/" + fileName;
		
			// 해당 폴더에 파일 생성
			file.transferTo(new File(filePath));
			
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// ################### 파일 업로드 ###################
		
		c.setPageId(pageId);
		c.setPostDate(postDate);
		
		System.out.println("insertdev c : " + c.toString());
		
		try {
			csi.insert(c);
			mv.setViewName("redirect:/");
		} catch(ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}		
		return mv;
	}
	
	@RequestMapping(value="updateDevView.ma")
	public ModelAndView updateDevView(
			ModelAndView mv, @RequestParam(value="id") int id){
		try {
			Contents c = csi.findById(id);
			mv.addObject("c", c);
			mv.setViewName("main/dev/main_dev_00002");
		} catch(ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}		
		return mv;
	}
	
	@RequestMapping(value="updateDev.ma", method=RequestMethod.POST)
	public ModelAndView updateDev(Contents c, ModelAndView mv, 
			@RequestParam(required=false) MultipartFile file,
			HttpServletRequest request) throws ContentsException {
		pageId = "main/dev";
		
		try {
			System.out.println("#################### update.ib file.isEmpty() : " + file.isEmpty() + "####################");
			System.out.println("#################### update.ib content : " + c + "####################");
			
			if(!file.isEmpty()) { // 파일이 null 일 경우
				String root = request.getSession().getServletContext().getRealPath("resources");
				System.out.println(root);
				String filePath = root + "/uploadFiles/maindev_upload_file";
				String fileName = "";
				String updatefilePath = "";
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
				fileName = newfileName + ext;
				c.setContents(fileName);
				
				// 폴더 없으면 생성
				File uploadPath = new File(filePath);
				if(!uploadPath.exists()) {
					uploadPath.mkdirs();
				}
				
				//파일경로를 maindev 객체에 넣어줌
				System.out.println("#################### update.ib content : " + c + "####################");
				updatefilePath = filePath + "/" + fileName;
				System.out.println("#################### update.ib updatefilePath : " + updatefilePath + "####################");
				
				// 해당 폴더에 파일 생성
				file.transferTo(new File(updatefilePath));
				
				// ##################### 파일 삭제 처리 #######################
				String deleteFilePath = filePath + "/" + deleteFileName;
				
				// ##################### 파일 삭제 처리 #######################
				File deleteFile = new File(deleteFilePath); // 파일 URL
				
				System.out.println("#################### update.ib deleteFilePath : " + deleteFilePath + "####################");
				
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
		
		c.setPageId(pageId);
		c.setPostDate(postDate);
		try {
			csi.update(c);
			mv.setViewName("redirect:/");
		} catch(ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}		
		return mv;
	}
	
	@RequestMapping(value="deleteDev.ma")
	public ModelAndView deleteDev(ModelAndView mv,
			@RequestParam(value="id") int id,
			HttpServletRequest request) {	
		try {
			String root = request.getSession().getServletContext().getRealPath("resources");
			String filePath = root + "/uploadFiles/maindev_upload_file";
			String deleteFileName = csi.findById(id).getContents();
			// ##################### 파일 삭제 처리 #######################
			String deleteFilePath = filePath + "/" + deleteFileName;
			
			// ##################### 파일 삭제 처리 #######################
			File deleteFile = new File(deleteFilePath); // 파일 URL
			
			System.out.println("#################### update.ib deleteFilePath : " + deleteFilePath + "####################");
			
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
			mv.setViewName("redirect:/");
		} catch(ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
}
