package com.hsg.intro.emp.lec.controller;

import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hsg.intro.Exception.ContentsException;
import com.hsg.intro.common.contents.model.service.ContentsServiceImpl;
import com.hsg.intro.common.contents.model.vo.Contents;

@Controller
@SessionAttributes("loginUser")
public class EmpLecController {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private ContentsServiceImpl cs;
	
	private String pageId = "emp/lec";
	
	@RequestMapping(value="view.el")
	public ModelAndView view(ModelAndView mv) {
		try {
			List<Contents> contents = cs.findByPageId(pageId);
			
			mv.addObject("contents", contents);
		} catch(ContentsException e) {
			e.printStackTrace();
		}
		mv.setViewName("emp/lec/emp_lec_00001");
		return mv;
	}
	
	@RequestMapping(value="viewDetail.el")
	public String viewDetail() {
		return "emp/lec/emp_lec_00002";
	}
	
	@RequestMapping(value="sendForm.el")
	public String sendForm() {
		return "emp/lec/emp_lec_00003";
	}
	
	@RequestMapping(value="send.el")
	public String send(HttpServletRequest request) {
		
		String from = request.getParameter("email");
		String to = "ark9659@gmail.com"; // 대표님 이메일로 변경
		String subject = "[HS글로벌 강사 지원] " + request.getParameter("name");
		String text = "지원자 이름 : " + request.getParameter("name") + "\r\n"
					   + "분야 : " + request.getParameter("field") + "\r\n"
					   + "연락처 : " + request.getParameter("phone") + "\r\n"
					   + "이메일 : " + request.getParameter("email") + "\r\n"
					   + "경력사항 : " + request.getParameter("career");
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			
			messageHelper.setFrom(from);
			messageHelper.setTo(to);
			messageHelper.setSubject(subject);
			messageHelper.setText(text);
			
			mailSender.send(message);
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return "redirect:view.el";
	}
}
