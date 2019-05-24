package com.hsg.intro.emp.lec.controller;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("loginUser")
public class EmpLecController {

	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping(value="view.el")
	public String view() {
		return "emp/lec/emp_lec_00001";
	}
	
	@RequestMapping(value="sendForm.el")
	public String sendForm() {
		return "emp/lec/emp_lec_00002";
	}
	
	@RequestMapping(value="send.el")
	public String send(HttpServletRequest request) {
		
		String from = request.getParameter("email");
		String to = "ㅇ"; // 대표님 이메일로 변경
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
