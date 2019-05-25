package com.hsg.intro.edu.eln.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hsg.intro.Exception.ContentsException;
import com.hsg.intro.common.contents.model.service.ContentsServiceImpl;
import com.hsg.intro.common.contents.model.vo.Contents;

@Controller
@SessionAttributes("loginUser")
public class EduElnController {
	
	@Autowired
	private ContentsServiceImpl cd;
	
	private String pageId = "edu/eln";
	
	SimpleDateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd HH:mm", Locale.KOREA);
	Date currentDate = new Date();
	String postDate = formatter.format (currentDate);
	
	// 추가
	@RequestMapping(value="insert.ee", method=RequestMethod.POST)
	public ModelAndView insertEduEln(
			Contents c, ModelAndView mv, 
			@RequestParam(value="category") int category,
			@RequestParam(value="title") String title,
			@RequestParam(value="text") String text) {
		
		String image = text.substring(
				text.indexOf("embed/") + 6, // 첫 번째에 위치한
				text.indexOf("embed/") + 17); // 유튜브 동영상 id 추출
		
		c.setPageId(pageId);
		c.setCategory(category);
		c.setTitle(title);
		c.setText(text);
		c.setImage(image);
		c.setPostDate(postDate);
		
		System.out.println("in insert : " + c);
		
		try {
			cd.insert(c);
			List<Contents> contents = cd.findByPageId(pageId);
			
			mv.addObject("contents", contents);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("edu/eln/edu_eln_00001");
		return mv;
	}
	
	// 추가 페이지
	@RequestMapping(value="insertView.ee")
	public ModelAndView insertView(ModelAndView mv) {
		mv.setViewName("edu/eln/edu_eln_00002");
		return mv;
	}
	
	// 메인 페이지
	@RequestMapping(value="view.ee")
	public ModelAndView viewEduEln(ModelAndView mv) {		
		try {
			List<Contents> contents = cd.findByPageId(pageId);
			
			mv.addObject("contents", contents);
		} catch(ContentsException e) {
			e.printStackTrace();
		}
		mv.setViewName("edu/eln/edu_eln_00001");
		return mv;
	}
	
	// 상세 보기
	@RequestMapping(value="viewDetail.ee")
	public ModelAndView viewEduElnDetail(ModelAndView mv,
			@RequestParam(value="id") int id) {
		try {
			Contents content = cd.findById(id);
			
			mv.addObject("content", content);
		} catch(ContentsException e) {
			e.printStackTrace();
		}
		mv.setViewName("edu/eln/edu_eln_00003");
		return mv;
	}
	
	
	// 업데이트(검색한 페이지로 돌아가게 하려면 파라미터 추가)
	@RequestMapping(value="update.ee", method=RequestMethod.POST) // DI 의존성 주입
	public ModelAndView updateEduEln(
			Contents c, ModelAndView mv, 
			@RequestParam(value="id") int id,
			@RequestParam(value="category") int category,
			@RequestParam(value="title") String title,
			@RequestParam(value="text") String text) {

		String param = ""; // 검색 기록 남길 때
		String image = text.substring(
				text.indexOf("embed/") + 6, // 첫 번째에 위치한
				text.indexOf("embed/") + 17); // 유튜브 동영상 id 추출
		
		c.setId(id);
		c.setPageId(pageId);
		c.setCategory(category);
		c.setTitle(title);
		c.setText(text);
		c.setImage(image);
		c.setPostDate(postDate);
		
		System.out.println("in update : " + c);
		
		try {
			cd.update(c);
			List<Contents> contents = cd.findByPageId(pageId);
			
			mv.addObject("contents", contents);
		} catch (ContentsException e) {
			e.printStackTrace();
		}
		mv.setViewName("redirect:viewDetail.ee?id=" + id);
		return mv;
	}
	
	// 삭제 (검색한 페이지로 돌아가게 하려면 파라미터 추가)
	@RequestMapping(value="delete.ee")
	public ModelAndView deleteEduEln(ModelAndView mv,
		@RequestParam(value="id") int id) {
		String param = ""; // 검색 기록 남길 때
		try {
			cd.delete(id);
			List<Contents> contents = cd.findByPageId(pageId);
			
			mv.addObject("contents", contents);
		} catch(ContentsException e) {
			e.printStackTrace();
		}
		mv.setViewName("edu/eln/edu_eln_00001" + param);
		return mv;
	}
	
	// insert view랑 update view 필요 
}
