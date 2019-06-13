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
				category = "�ؿ��İ�";
				break;
		}
		
		String from = request.getParameter("email");
		
		// ���� ���� �ּ� ����
		List<String> toAddress = new ArrayList<String>();
		// ��ǥ�԰� ������ �̸��Ϸ� ����, �߰� ����
		toAddress.add("ark9659@gmail.com");
		toAddress.add("cksgud1350@naver.com");
		String subject = "[HS�۷ι� ���� �Ƿ�] " + request.getParameter("company");
		String contents = "ȸ��� : " + request.getParameter("company") + "\r\n" +  
					  	  "���ð��� : " + category + "\r\n" +
					  	  "�����ȣ : " + request.getParameter("zip_code") + "\r\n" +
					  	  "�ּ� : " + request.getParameter("address") + " " + 
					  			 	request.getParameter("d_address") + 
					  			 	request.getParameter("e_address") + "\r\n" +
					  	  "����ڸ� : " + request.getParameter("name") + "\r\n" +  
					  	  "����ó : " + request.getParameter("phone") + "\r\n" + 
					  	  "�̸��� : " + request.getParameter("email") + "\r\n" + 
					  	  "�䱸���� : " + request.getParameter("message");
		// ����
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
