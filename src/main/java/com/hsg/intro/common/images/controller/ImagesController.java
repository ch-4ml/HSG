package com.hsg.intro.common.images.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("loginUser")
public class ImagesController {
	
	@RequestMapping(value="updateImg.co", method=RequestMethod.POST)
	public ModelAndView updateCommon(ModelAndView mv,
			@RequestParam(required=false) MultipartFile file ,
			HttpServletRequest request) {
		try {
			System.out.println("#################### update.ee file.isEmpty() : " + file.isEmpty() + "####################");
			
			if(!file.isEmpty()) {
				String root = request.getSession().getServletContext().getRealPath("resources");				
				System.out.println(root);
				String filePath = root + "/uploadFiles/common_upload_file";
				String fileName = file.getOriginalFilename();
				String updatefilePath = "";

				// 파일명 새이름 설정
//				int randomNumber = (int)((Math.random()*10000)+1);
//				SimpleDateFormat format = new SimpleDateFormat ("yyyyMMddHHmmss");
//				Date nowTime = new Date();
//				String newfileName = format.format(nowTime) + String.valueOf(randomNumber);	
				
				// 폴더 없으면 생성
				File uploadPath = new File(filePath);
				if(!uploadPath.exists()) {
					uploadPath.mkdirs();
				}
				
				updatefilePath = filePath + "/" + fileName;
				System.out.println("#################### update.ee updatefilePath : " + updatefilePath + "####################");
				
				// 해당 폴더에 파일 생성
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
