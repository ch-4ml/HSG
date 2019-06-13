package com.hsg.intro.edu.req.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.activation.MimetypesFileTypeMap;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hsg.intro.Exception.ContentsException;
import com.hsg.intro.common.contents.model.service.ContentsServiceImpl;
import com.hsg.intro.common.contents.model.vo.Contents;

@Controller
@SessionAttributes("loginUser")
public class EduReqController {

	@Autowired
	private JavaMailSender mailSender;

	SimpleDateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd HH:mm", Locale.KOREA);
	Date currentDate = new Date();
	String postDate = formatter.format (currentDate);
		
	@RequestMapping(value="view.er")
	public ModelAndView sendForm(ModelAndView mv) {
		mv.setViewName("edu/req/edu_req_00001");
		return mv;
	}
	
	
	@RequestMapping(value="send.er")
	public String send(MultipartHttpServletRequest request) {
		
		String c = request.getParameter("category");
		String category = "";
		switch(Integer.parseInt(c)) {
			case 1 :
				category = "H/W";
				break;
			case 2 :
				category = "S/W";
				break;
			case 3 :
				category = "해외파견";
				break;
		}
		
		String from = request.getParameter("email");
		
		// 메일 받을 주소 지정
		List<String> toAddress = new ArrayList<String>();
		// 대표님과 관리자 이메일로 변경, 추가 가능
		toAddress.add("ark9659@gmail.com");
		toAddress.add("cksgud1350@naver.com");
		String subject = "[HS글로벌 교육 의뢰] " + request.getParameter("company");
		String contents = "회사명 : " + request.getParameter("company") + "\r\n" +  
					  	  "선택강좌 : " + category + "\r\n" +
					  	  "우편번호 : " + request.getParameter("zip_code") + "\r\n" +
					  	  "주소 : " + request.getParameter("address") + " " + 
					  			 	request.getParameter("d_address") + 
					  			 	request.getParameter("e_address") + "\r\n" +
					  	  "담당자명 : " + request.getParameter("name") + "\r\n" +  
					  	  "연락처 : " + request.getParameter("phone") + "\r\n" + 
					  	  "이메일 : " + request.getParameter("email") + "\r\n" + 
					  	  "요구사항 : " + request.getParameter("message");
		// 파일
		List<MultipartFile> fileList = request.getFiles("file");
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			
			messageHelper.setFrom(from);
			for(String to: toAddress) {
				messageHelper.addTo(to);
			}
			for (MultipartFile f: fileList) {
				if(f.getOriginalFilename() != "") {
					File file = new File(f.getOriginalFilename());
					String mimeType = new MimetypesFileTypeMap().getContentType(file);
					
					messageHelper.addAttachment(f.getOriginalFilename(), new ByteArrayDataSource(f.getBytes(), mimeType)); // string, datasource
	//				messageHelper.addAttachment(arg0, arg1); // string, dataSource				
	//				messageHelper.addAttachment(attachmentFilename, file); // string, file
	//				messageHelper.addAttachment(attachmentFilename, inputStreamSource); // string, inputstreamsource
	//				messageHelper.addAttachment(attachmentFilename, inputStreamSource, contentType); // string, inputstreamsource, string
				}
			}
			messageHelper.setSubject(subject);
			messageHelper.setText(contents);
			
			mailSender.send(message);
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return "edu/req/edu_req_00002";
	}
}
