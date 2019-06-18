package com.hsg.intro.common.files.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hsg.intro.common.files.model.vo.Files;

@Controller
@SessionAttributes("loginUser")
public class FilesController {
	
	@RequestMapping(value="updateImg.fs", method=RequestMethod.POST)
	public ModelAndView updateCommon(ModelAndView mv,
			@RequestParam(required=false) MultipartFile file ,
			HttpServletRequest request) {
		Files f = new Files();
		try {	
			if(!file.isEmpty()) {
				String root = request.getSession().getServletContext().getRealPath("resources");				
				System.out.println(root);
				String filePath = root + "/uploadFiles/common_upload_file";
				String fileName = file.getOriginalFilename();
				String updatefilePath = "";

				// ���ϸ� ���̸� ����
				int randomNumber = (int)((Math.random()*10000)+1);
				SimpleDateFormat format = new SimpleDateFormat ("yyyyMMddHHmmss");
				Date nowTime = new Date();
				String newFileName = format.format(nowTime) + String.valueOf(randomNumber);	
				
				// Ȯ���� ���ϱ�
				int pos = file.getOriginalFilename().lastIndexOf(".");
				String ext = file.getOriginalFilename().substring(pos);
				fileName = newFileName + ext;
				f.setOrigin(fileName);
				f.setStored(newFileName);
				
				// ���� ������ ����
				File uploadPath = new File(filePath);
				if(!uploadPath.exists()) {
					uploadPath.mkdirs();
				}
				
				updatefilePath = filePath + "/" + fileName;
				
				// �ش� ������ ���� ����
				file.transferTo(new File(updatefilePath));
				
				mv.addObject("location", fileName);
				mv.setViewName("jsonView");
			}
			
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return mv;
	}
}
