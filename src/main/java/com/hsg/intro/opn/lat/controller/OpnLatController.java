package com.hsg.intro.opn.lat.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.hsg.intro.common.PageInfo;
import com.hsg.intro.common.contents.model.service.ContentsServiceImpl;
import com.hsg.intro.common.contents.model.vo.Contents;

@Controller
@SessionAttributes("loginUser")
public class OpnLatController {
	@Autowired
	private ContentsServiceImpl csi;

	private String pageId = "opn/let";

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.KOREA);
	Date currentDate = new Date();
	String postDate = formatter.format(currentDate);

	//
	@RequestMapping(value = "view.lt", method = RequestMethod.POST) // DI 의존성 주입
	public ModelAndView pagingContentList(ModelAndView mv, @RequestParam(defaultValue = "1") Integer currentPage) {
		/**
		 * param currentPage : 현재페이지 (defaultValue : 1)
		 * 
		 */

		Contents c = new Contents();

		System.out.println("noticeController noticeSelectList");
		int limit;
		int maxPage;
		int startPage;
		int endPage;

		limit = 10; //

		System.out.println("currentPage : " + currentPage);

		int listCount = 0;
		System.out.println("noticeController noticeSelectList getLstCount");

		try {
			listCount = csi.getListCount(pageId); // 총페이지수
		} catch (ContentsException e) {
			e.printStackTrace();
		}

		maxPage = (int) ((double) listCount / limit + 0.9);
		startPage = ((int) ((double) currentPage / limit + 0.9) - 1) * limit + 1;
		endPage = startPage + limit - 1;
		if (maxPage < endPage) {
			endPage = maxPage;
		}

		PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage, currentPage);

		c.setPageId(pageId);
		c.setPostDate(postDate);

		mv.addObject("pi", pi);

		try {
			List<Contents> cs = csi.findByPageId(pageId, pi);
			mv.addObject("cs", cs);
			mv.setViewName("jsonView");
		} catch (ContentsException e) {
			e.printStackTrace();
		}
		
		return mv;
	}
	
	@RequestMapping(value = "insert.lt", method = RequestMethod.POST) // DI 의존성 주입 
	public ModelAndView insertOpnLat(Contents c, ModelAndView mv,
	  @RequestParam(required=false) MultipartFile file, HttpServletRequest request) {
	  
		  // ################### 파일 업로드 ################### 
		  String root = request.getSession().getServletContext().getRealPath("resources"); 
		  String filePath = root + "/uploadFiles/opnlat_upload_file"; 
		  String fileName = "";
		  String originFileName = "";
		  try { // 파일명 새이름 설정 
			  int randomNumber = (int)((Math.random()*10000)+1);
			  SimpleDateFormat format = new SimpleDateFormat ( "yyyyMMddHHmmss"); 
			  Date nowTime = new Date(); String newfileName = format.format(nowTime) + String.valueOf(randomNumber);
			  
			  // 확장자 구하기 
			  int pos = file.getOriginalFilename().lastIndexOf("."); 
			  String ext = file.getOriginalFilename().substring(pos); 
			  fileName = newfileName + ext;
			  originFileName = file.getOriginalFilename();
			  
			  c.setContents(fileName);
			  c.setOrigin(originFileName);
			  
			  // 폴더 없으면 생성 
			  File uploadPath = new File(filePath); 
			  if(!uploadPath.exists()) {
				  uploadPath.mkdirs(); 
			  }
			  
			  // 파일경로를 itrbok 객체에 넣어줌 
			  filePath = filePath + "/" + fileName;
			  
			  // 해당 폴더에 파일 생성 
			  file.transferTo(new File(filePath));
			  
		  } catch (IllegalStateException e1) { 
			  e1.printStackTrace(); 
		  } catch (IOException e1) { 
			  e1.printStackTrace(); 
		  } 
		  // ################### 파일 업로드###################
		  
		  c.setPageId(pageId); c.setPostDate(postDate);
		  
		  try { 
			  csi.insert(c); 
			  mv.setViewName("redirect:view.lt");
		  
		  } catch (Exception e) { mv.addObject("message",e.getMessage());
		  	  mv.setViewName("common/errorPage");
		  
		  } 
		  return mv;
	  
	  }
	
	  
	 
}