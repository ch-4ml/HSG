package com.hsg.intro.common.files.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hsg.intro.common.files.model.vo.Files;

@Controller
@SessionAttributes("loginUser")
public class FilesController {
	
	private String root = "/hsglobal03/tomcat/webapps/var/HSG/uploadFiles";
	private String filePath = "/common_upload_files";
	
	@RequestMapping(value="update.fi", method=RequestMethod.POST)
	public ModelAndView update(ModelAndView mv,
			@RequestParam(required=false) MultipartFile file ,
			HttpServletRequest request) {
		try {
			if(!file.isEmpty()) {				
				String storedFileName = file.getOriginalFilename();
					
				// ���� ������ ����
				File uploadPath = new File(root + filePath);
				if(!uploadPath.exists()) {
					uploadPath.mkdirs();
				}
				
				String storedFilePath = filePath + storedFileName;
				
				// �ش� ������ ���� ����
				file.transferTo(new File(root + storedFilePath));
				
				mv.addObject("location", storedFilePath);
				mv.setViewName("jsonView");
			}
			
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping(value="delete.fi")
	public ModelAndView delete(ModelAndView mv, @RequestParam String stored) {
		if(stored.indexOf("?") != -1) {
			stored.substring(0, stored.indexOf("?"));
			stored.substring(stored.indexOf("blobid"));
		}
		File deleteFile = new File(stored);
		if(deleteFile.exists()) {
			if(deleteFile.delete()) {
				System.out.println("���� ���� �Ϸ�");
			} else {
				System.out.println("���� ���� ����");
			}
		} else {
			System.out.println("������ �������� �ʽ��ϴ�.");
		}
		mv.setViewName("jsonView");
		return mv;
	}
}