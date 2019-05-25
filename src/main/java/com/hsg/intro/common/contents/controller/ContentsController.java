///*
// * �����
// */
//
//package com.hsg.intro.common.contents.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.hsg.intro.Exception.ContentsException;
//import com.hsg.intro.common.contents.model.service.ContentsServiceImpl;
//import com.hsg.intro.common.contents.model.vo.Contents;
//
//@Controller
//@SessionAttributes("loginUser")
//public class ContentsController {
//	@Autowired
//	private ContentsServiceImpl cs;
//	
//	@RequestMapping(value="view.ct") // DI ������ ����
//	public ModelAndView viewContents(ModelAndView mv,
//			@RequestParam(value="pageId") String pageId) {		
//		try {
//			List<Contents> contents = cs.findByPageId(pageId);// select�� ������ ������
//			mv.addObject("contents", contents);
//		} catch(ContentsException e) {
//			e.printStackTrace();
//		}
//		mv.setViewName(pageId);
//		return mv;
//	}
//	
//	// ������Ʈ
//	@RequestMapping(value="update.ct", method=RequestMethod.POST) // DI ������ ����
//	public ModelAndView updateContents(
//			Contents c, ModelAndView mv, 
//			@RequestParam(value="id") int id,
//			@RequestParam(value="title") String title,
//			@RequestParam(value="text") String text,
//			@RequestParam(value="pageId") String pageId) {
//		
//		// �Ű������� �� ����
//		c.setContentsId(id);
//		c.setText(title);
//		c.setText(text);
//		
//		System.out.println("in update : " + c);
//		
//		try {
//			cs.updateContents(c);
//			List<Contents> contents = cs.findByPageId(pageId);
//			
//			mv.addObject("contents", contents);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		mv.setViewName(pageId);
//		
//		return mv;
//	}
//}
