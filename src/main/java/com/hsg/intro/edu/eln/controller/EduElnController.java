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

import com.hsg.intro.Exception.EduElnException;
import com.hsg.intro.edu.eln.model.service.EduElnServiceImpl;
import com.hsg.intro.edu.eln.model.vo.EduEln;

@Controller
@SessionAttributes("loginUser")
public class EduElnController {
	
	@Autowired
	private EduElnServiceImpl ees;
	
	SimpleDateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd HH:mm", Locale.KOREA);
	Date currentDate = new Date();
	String postDate = formatter.format (currentDate);
	
	@RequestMapping(value="insert.ee", method=RequestMethod.POST)
	public ModelAndView insertEduEln(
			EduEln e, ModelAndView mv, 
			@RequestParam(value="category") int category,
			@RequestParam(value="title") String title,
			@RequestParam(value="text") String text) {
		
		String video = text.substring(
				text.indexOf("embed/") + 6, // ù ��°�� ��ġ��
				text.indexOf("embed/") + 17); // ��Ʃ�� ������ id ����
		
		e.setCategory(category);
		e.setTitle(title);
		e.setText(text);
		e.setVideo(video);
		e.setPostDate(postDate);
		
		System.out.println("in insert : " + e);
		
		try {
			ees.insertEduEln(e);
			List<EduEln> eln = ees.findAll();
			
			mv.addObject("eln", eln);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		mv.setViewName("edu/eln/edu_eln_00001");
		return mv;
	}
	
	@RequestMapping(value="insertView.ee")
	public ModelAndView insertView(ModelAndView mv) {
		mv.setViewName("edu/eln/edu_eln_00002");
		return mv;
	}
	
	@RequestMapping(value="view.ee")
	public ModelAndView viewEduEln(ModelAndView mv) {		
		try {
			List<EduEln> eln = ees.findAll();
			
			mv.addObject("eln", eln);
		} catch(EduElnException ex) {
			ex.printStackTrace();
		}
		mv.setViewName("edu/eln/edu_eln_00001");
		return mv;
	}
	
	@RequestMapping(value="viewDetail.ee")
	public ModelAndView viewEduElnDetail(ModelAndView mv,
			@RequestParam(value="id") int id) {
		try {
			EduEln eln = ees.findById(id);
			
			mv.addObject("eln", eln);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		mv.setViewName("edu/eln/edu_eln_00003");
		return mv;
	}
	
	
	// ������Ʈ(�˻��� �������� ���ư��� �Ϸ��� �Ķ���� �߰�)
	@RequestMapping(value="update.ee", method=RequestMethod.POST) // DI ������ ����
	public ModelAndView updateEduEln(
			EduEln e, ModelAndView mv, 
			@RequestParam(value="id") int id,
			@RequestParam(value="category") int category,
			@RequestParam(value="title") String title,
			@RequestParam(value="text") String text) {
		
		String param = ""; // �˻� ��� ���� ��
		String video = text.substring(
				text.indexOf("embed/") + 6, // ù ��°�� ��ġ��
				text.indexOf("embed/") + 17); // ��Ʃ�� ������ id ����
		
		e.setId(id);
		e.setCategory(category);
		e.setTitle(title);
		e.setText(text);
		e.setVideo(video);
		e.setPostDate(postDate);
		
		System.out.println("in update : " + e);
		
		try {
			ees.updateEduEln(e);
			List<EduEln> eln = ees.findAll();
			
			mv.addObject("eln", eln);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		mv.setViewName("edu/eln/edu_eln_00001");
		return mv;
	}
	
	// ���� (�˻��� �������� ���ư��� �Ϸ��� �Ķ���� �߰�)
	@RequestMapping(value="delete.ee")
	public ModelAndView deleteEduEln(ModelAndView mv,
		@RequestParam(value="id") int id) {
		String param = ""; // �˻� ��� ���� ��
		try {
			ees.deleteEduEln(id);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		mv.setViewName("" + param);
		return mv;
	}
	
	// insert view�� update view �ʿ� 
}
