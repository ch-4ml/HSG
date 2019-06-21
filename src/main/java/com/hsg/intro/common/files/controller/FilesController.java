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
	
	@RequestMapping(value="update.fi", method=RequestMethod.POST)
	public ModelAndView update(ModelAndView mv,
			@RequestParam(required=false) MultipartFile file ,
			HttpServletRequest request) {
		try {
			if(!file.isEmpty()) {
				Files f = new Files();
				
				String root = "/ark9659/var/webapps/uploadFiles";
				String filePath = root + "/common_upload_file";
				String storedFileName = file.getOriginalFilename();
					
				// 폴더 없으면 생성
				File uploadPath = new File(filePath);
				if(!uploadPath.exists()) {
					uploadPath.mkdirs();
				}
				
				String storedfilePath = filePath + "/" + storedFileName;
				f.setStored(storedFileName);
				
				// 해당 폴더에 파일 생성
				file.transferTo(new File(storedfilePath));
				
				mv.addObject("location", storedFileName);
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
				System.out.println("파일 삭제 완료");
			} else {
				System.out.println("파일 삭제 실패");
			}
		} else {
			System.out.println("파일이 존재하지 않습니다.");
		}
		mv.setViewName("jsonView");
		return mv;
	}
}