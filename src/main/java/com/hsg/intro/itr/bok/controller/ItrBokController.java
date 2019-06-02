package com.hsg.intro.itr.bok.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hsg.intro.Exception.ContentsException;
import com.hsg.intro.common.contents.model.service.ContentsServiceImpl;
import com.hsg.intro.common.contents.model.vo.Contents;
import com.hsg.intro.itr.bok.model.dto.ItrBokDto;

@Controller
@SessionAttributes("loginUser")
public class ItrBokController {
	@Autowired
	private ContentsServiceImpl cs;
	
	private String pageId = "itr/bok";
	// 서적/특허 추가
	@RequestMapping(value = "insert.ib", method = RequestMethod.POST) // DI 의존성 주입
	public ModelAndView insertIbkBok(Contents content, ModelAndView mv
			, @RequestParam(required=false) MultipartFile file
			, HttpServletRequest request){

		String root = request.getSession().getServletContext().getRealPath("resources");
		String filePath = root + "/uploadFiles/itrbok_upload_file";
		
		String fileName = "";
		try {
			// 파일명 새이름 설정
			int randomNumber = (int)((Math.random()*10000)+1);
			SimpleDateFormat format = new SimpleDateFormat ( "yyyyMMddHHmmss");
			Date nowTime = new Date();
			String newfileName = format.format(nowTime) + String.valueOf(randomNumber);
			// 확장자 구하기
			int pos = file.getOriginalFilename().lastIndexOf(".");
			String ext = file.getOriginalFilename().substring(pos);
			fileName = newfileName + ext;
			content.setImage(fileName);
			//파일경로를 itrbok 객체에 넣어줌
			filePath = filePath + "/" + fileName;

			// 해당 폴더에 파일 생성
			file.transferTo(new File(filePath));
			
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		content.setPageId(pageId);
		content.setText("<URL - " + request.getParameter("url") + " - URLEND>" + content.getText());
		
		System.out.println("controller bok : " + content);
		try {
			cs.insert(content);			
			mv.setViewName("redirect:view.ib");
			
		} catch (Exception e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");

		}
		return mv;
		
	}
	
	// 서적/특허 수정
		@RequestMapping(value = "update.ib", method = RequestMethod.POST) // DI 의존성 주입
		public ModelAndView updateIbkBok(Contents content, ModelAndView mv
				, @RequestParam(required=false) MultipartFile file
				, HttpServletRequest request){
			
			try {
				if(!file.getOriginalFilename().equals("")) {
					// 파일명 새이름 설정
					
					String root = request.getSession().getServletContext().getRealPath("resources");
					
					String filePath = root + "/uploadFiles/itrbok_upload_file";
						
					int randomNumber = (int)((Math.random()*10000)+1);
					
					SimpleDateFormat format = new SimpleDateFormat ( "yyyyMMddHHmmss");
					
					Date nowTime = new Date();
					
					String newfileName = format.format(nowTime) + String.valueOf(randomNumber);
					
					// 확장자 구하기
					
					int pos = file.getOriginalFilename().lastIndexOf(".");

					String ext = file.getOriginalFilename().substring(pos);
					
					//파일경로를 itrbok 객체에 넣어줌
					
					filePath = filePath + "/" + newfileName + ext;
					
					System.out.println("controller filePath : " + filePath);
					
					content.setImage(filePath);
					
					// 해당 폴더에 파일 생성
					file.transferTo(new File(filePath));
				} else {
					content.setImage("");
				}
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			content.setPageId(pageId);
			content.setText("<URL - " + request.getParameter("url") + " - URLEND>" + content.getText());
			
			try {
				cs.update(content);
				
				List<Contents> contents = cs.findByPageId(pageId);
				mv.addObject("contents", contents);
				
				mv.setViewName("redirect:view.ib");
				
			} catch (Exception e) {
				mv.addObject("message",e.getMessage());
				mv.setViewName("redirect:/common/errorPage");

			}
			return mv;
			
		}
	
	// 서적/특허 추가 폼으로 이동
	@RequestMapping(value = "insertView.ib", method = RequestMethod.GET) // DI 의존성 주입
	public ModelAndView insertViewIbkBok(ModelAndView mv){
		
		mv.setViewName("itr/bok/itr_bok_00002");
		
		return mv;
		
	}
	
	// 서적/특허 수정폼으로 이동
	@RequestMapping(value = "updateView.ib", method = RequestMethod.GET) // DI 의존성 주입
	public ModelAndView updateViewIbkBok(
			ModelAndView mv, 
			@RequestParam(value="id") int id) {
		Contents content;
		ItrBokDto ibd = new ItrBokDto();
		try {
			content = cs.findById(id);
			String url = "";
			String urlWithTag = "";
			if(content.getText().indexOf("<URL - ") != -1) {
				url = content.getText().substring(content.getText().indexOf("<URL - ") + 7, content.getText().indexOf(" - URLEND>"));
				urlWithTag = content.getText().substring(content.getText().indexOf("<URL - "), content.getText().indexOf(" - URLEND>") + 10);
			}
			ibd.setId(content.getId());
			ibd.setCategory(content.getCategory());
			ibd.setTitle(content.getTitle());
			ibd.setUrl(url);
			ibd.setText(content.getText().replace(urlWithTag, ""));
			ibd.setImage(content.getImage());
			ibd.setPostDate(content.getPostDate());
			
			mv.addObject("ibd", ibd);
			mv.setViewName("itr/bok/itr_bok_00003");
			
		} catch (ContentsException e) {
			e.printStackTrace();
		}
		
		return mv;
		
	}
	
	// 서적/특허 삭제 처리
	@RequestMapping(value = "delete.ib", method = RequestMethod.GET) // DI 의존성 주입
	public ModelAndView deleteIbkBok(
			ModelAndView mv,
			@RequestParam(value="id") int id){
		
		try {
			cs.delete(id);
			mv.setViewName("redirect:view.ib");
		} catch (ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("redirect:/common/errorPage");
		}
		return mv;
	}
	
	// 서적/특허 페이지 이동
	@RequestMapping(value = "view.ib", method = RequestMethod.GET) // DI 의존성 주입
	public ModelAndView viewIbkBok(ModelAndView mv){
		try {
			List<Contents> contents = cs.findByPageId(pageId);
			List<ItrBokDto> ibds = new ArrayList<ItrBokDto>();
			for(Contents content: contents) {
				ItrBokDto ibd = new ItrBokDto();
				String url = "";
				String urlWithTag = "";
				if(content.getText().indexOf("<URL - ") != -1) {
					url = content.getText().substring(content.getText().indexOf("<URL - ") + 7, content.getText().indexOf(" - URLEND>"));
					urlWithTag = content.getText().substring(content.getText().indexOf("<URL - "), content.getText().indexOf(" - URLEND>") + 10);
				}
				ibd.setId(content.getId());
				ibd.setCategory(content.getCategory());
				ibd.setTitle(content.getTitle());
				ibd.setUrl(url);
				ibd.setText(content.getText().replace(urlWithTag, ""));
				ibd.setImage(content.getImage());
				ibd.setPostDate(content.getPostDate());
				ibds.add(ibd);
			}
			mv.addObject("ibds", ibds);
			mv.setViewName("itr/bok/itr_bok_00001");
		} catch (ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("redirect:/common/errorPage");
		}
		return mv;
		
	}
	
	// 외부 url 접근
	@RequestMapping(value = "redirect.ib", method = RequestMethod.GET) // DI 의존성 주입
	public ModelAndView viewIbkBokDetail(ModelAndView mv, @RequestParam(value="url") String url){
		if(url.indexOf("http://") != -1)
			mv.setViewName("redirect:" + url);
		else if(url.indexOf("https://") != -1)
			mv.setViewName("redirect:" + url);
		else	
			mv.setViewName("redirect:http://" + url);
		return mv;
	}

}
