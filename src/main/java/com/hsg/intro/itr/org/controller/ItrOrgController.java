package com.hsg.intro.itr.org.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
public class ItrOrgController {
	@Autowired
	private ContentsServiceImpl cs;
	// 주로 사용할 페이지의 이름입니다. contents 테이블의 page_id 컬럼에 들어갈 값입니다.
	// page_id는 간단한 내용을 뿌려주는 단일 페이지에서 같은 테이블을 사용할 수 있도록 만든 컬럼입니다.
	private String pageId = "itr/org/itr_org_00001"; 
	
	// CEO 인사말 보기
	@RequestMapping(value="view.io", method=RequestMethod.GET) // DI 의존성 주입
	public ModelAndView viewContents(ModelAndView mv){		
		try {
			List<Contents> contents = cs.findByPageId(pageId);// select로 데이터 가져옴
			
			mv.addObject("contents", contents);
		} catch(ContentsException e) {
			e.printStackTrace();
		}
		mv.setViewName("itr/org/itr_org_00001");
		
		return mv;
	}
	
	// CEO 인사말 업데이트
	@RequestMapping(value="update.io", method=RequestMethod.POST) // DI 의존성 주입
	public ModelAndView updateContents(Contents c, ModelAndView mv, HttpServletRequest request) {
		
		// 매개변수로 줄 예정
		int id = Integer.parseInt(request.getParameter("id"));
		String text = request.getParameter("text"); 
		c.setId(id);
		c.setText(text);
		
		System.out.println("in update.io : " + c);
		
		try {
			cs.update(c);
			List<Contents> contents = cs.findByPageId(pageId);
			
			mv.addObject("contents", contents);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("itr/org/itr_org_00001");
		
		return mv;
	}
	
	// CEO 인사말 업데이트 폼
	@RequestMapping(value="viewUpdateImg.io")
	public ModelAndView viewUpdateImg(ModelAndView mv, @RequestParam(value="id") int id) {
		
		mv.addObject("id", id);
		mv.setViewName("itr/org/itr_org_00002");
		return mv;
	}
	
	// CEO 인사말 이미지 업데이트
	@RequestMapping(value="updateImg.io", method=RequestMethod.POST)
	public ModelAndView updateContentsImg(Contents c, ModelAndView mv,
			@RequestParam(required=false) MultipartFile gfile,
			HttpServletRequest request) {
		
		// POST로 보낸 업로드할 파일 체크
		System.out.println("controller gfile : " + gfile);
		
		int id = Integer.parseInt(request.getParameter("id"));
		String root = request.getSession().getServletContext().getRealPath("resources");
		System.out.println(root);
		String filePath = root + "\\uploadFiles\\itrorg_upload_file";
		try {
			// 파일 명 새 이름 설정
			int randomNumber = (int)((Math.random()*10000)+1);
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			Date now = new Date();
			String newFileName = format.format(now) + String.valueOf(randomNumber);
			
			// 확장자 구하기
			int pos = gfile.getOriginalFilename().lastIndexOf(".");
			String ext = gfile.getOriginalFilename().substring(pos);
			
			// 폴더 존재 여부 확인 및 생성
			File dir = new File(filePath);
			if(!dir.isDirectory()) {
				dir.mkdirs();
			}

			// 파일 경로를 itrorg 객체에 넣어줌
			filePath = filePath + "\\" + newFileName + ext;
			System.out.println("controller filePath: " + filePath);
			c.setImage(filePath);
			
			// 해당 폴더에 파일 생성
			gfile.transferTo(new File(filePath));
		} catch(IllegalStateException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
				
		try {
			c.setId(id);
			System.out.println("controller org : " + c);
			cs.updateImage(c);
			List<Contents> contents = cs.findByPageId(pageId);
			
			mv.addObject("contents", contents);
			mv.setViewName("itr/org/itr_org_00001");
		}
		catch (Exception e) {
			mv.addObject("message", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}
}
