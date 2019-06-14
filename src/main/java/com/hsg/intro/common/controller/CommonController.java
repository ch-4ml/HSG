package com.hsg.intro.common.controller;

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

@Controller
@SessionAttributes("loginUser")
public class CommonController {
	
	@RequestMapping(value="update.co", method=RequestMethod.POST)
	public void updateCommon(
			@RequestParam(required=false) MultipartFile file ,
			HttpServletRequest request) {
		try {
			System.out.println("#################### update.ee file.isEmpty() : " + file.isEmpty() + "####################");
			
			if(!file.isEmpty()) { // 파일이 null 일 경우
				String root = request.getSession().getServletContext().getRealPath("resources");
				String filePath = root + "/uploadFiles/common_upload_file";
				String fileName = "";
				String updatefilePath = "";

				// 파일명 새이름 설정
//				int randomNumber = (int)((Math.random()*10000)+1);
//				SimpleDateFormat format = new SimpleDateFormat ("yyyyMMddHHmmss");
//				Date nowTime = new Date();
//				String newfileName = format.format(nowTime) + String.valueOf(randomNumber);	
				
				// 확장자 구하기
				int pos = file.getOriginalFilename().lastIndexOf(".");
				String ext = file.getOriginalFilename().substring(pos);
				fileName = file.getOriginalFilename() + ext;
				
				// 폴더 없으면 생성
				File uploadPath = new File(filePath);
				if(!uploadPath.exists()) {
					uploadPath.mkdirs();
				}
				
				updatefilePath = filePath + "/" + fileName;
				System.out.println("#################### update.ee updatefilePath : " + updatefilePath + "####################");
				
				// 해당 폴더에 파일 생성
				file.transferTo(new File(updatefilePath));
			}
			
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
