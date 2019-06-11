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
import com.hsg.intro.common.contents.model.domain.ContentsDomain;
import com.hsg.intro.common.contents.model.service.ContentsServiceImpl;
import com.hsg.intro.common.contents.model.vo.Contents;

@Controller
@SessionAttributes("loginUser")
public class ItrBokController {
	@Autowired
	private ContentsServiceImpl csi;
	
	private String pageId = "itr/bok";
	// 서적/특허 추가
	@RequestMapping(value = "insert.ib", method = RequestMethod.POST) // DI 의존성 주입
	public ModelAndView insertIbkBok(Contents c, ModelAndView mv
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
			c.setContents(fileName);
			//파일경로를 itrbok 객체에 넣어줌
			filePath = filePath + "/" + fileName;

			// 해당 폴더에 파일 생성
			file.transferTo(new File(filePath));
			
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		c.setPageId(pageId);
		
		try {
			csi.insert(c);			
			mv.setViewName("redirect:view.ib");
			
		} catch (Exception e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");

		}
		return mv;
		
	}
	
	// 서적/특허 수정
		@RequestMapping(value = "update.ib", method = RequestMethod.POST) // DI 의존성 주입
		public ModelAndView updateIbkBok(Contents c, ModelAndView mv
				, @RequestParam(required=false) MultipartFile file
				, HttpServletRequest request){
			
			try {
				// 현재 DB에 저장되어 있는 파일명 넘겨 받은 파일명이 일치할 경우, 이미지 링크를 지우지 않고 그대로 넘겨주어야함 
				// 다를 경우, 해당 이미지를 삭제하고 새로운 이미지를 업로드 해야함

					// 파일명 새이름 설정
					String root = request.getSession().getServletContext().getRealPath("resources");
					
					String filePath = root + "/uploadFiles/itrbok_upload_file";
						
					String fileName = "";
					
					int randomNumber = (int)((Math.random()*10000)+1);
					
					SimpleDateFormat format = new SimpleDateFormat ( "yyyyMMddHHmmss");
					
					Date nowTime = new Date();
					
					String newfileName = format.format(nowTime) + String.valueOf(randomNumber);
					
					// 확장자 구하기
					
					int pos = file.getOriginalFilename().lastIndexOf(".");

					String ext = file.getOriginalFilename().substring(pos);
					
					fileName = newfileName + ext;
					//파일경로를 itrbok 객체에 넣어줌
					filePath = filePath + "/" + fileName;
					c.setContents(fileName);
					
					// 해당 폴더에 파일 생성
					file.transferTo(new File(filePath));
					
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			c.setPageId(pageId);
			c.setText("<URL - " + request.getParameter("url") + " - URLEND>" + c.getText());
			
			try {
				csi.update(c);
				
				List<Contents> cs = csi.findByPageId(pageId);
				mv.addObject("cs", cs);
				
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
		Contents c;
		ContentsDomain cd = new ContentsDomain();
		try {
			c = csi.findById(id);
			String url = "";
			String urlWithTag = "";
			if(c.getText().indexOf("<URL - ") != -1) {
				url = c.getText().substring(c.getText().indexOf("<URL - ") + 7, c.getText().indexOf(" - URLEND>"));
				urlWithTag = c.getText().substring(c.getText().indexOf("<URL - "), c.getText().indexOf(" - URLEND>") + 10);
			}
			cd.setId(c.getId());
			cd.setCategory(c.getCategory());
			cd.setTitle(c.getTitle());
			cd.setUrl(url);
			cd.setContents(c.getContents());
			cd.setText(c.getText().replace(urlWithTag, ""));
			cd.setPostDate(c.getPostDate());
			
			mv.addObject("cd", cd);
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
			csi.delete(id);
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
			List<Contents> cs = csi.findByPageId(pageId);
			List<ContentsDomain> cds = new ArrayList<ContentsDomain>();
			for(Contents c: cs) {
				ContentsDomain cd = new ContentsDomain();
				String url = "";
				String urlWithTag = "";
				if(c.getContents().indexOf("<URL - ") != -1) {
					url = c.getContents().substring(c.getContents().indexOf("<URL - ") + 7, c.getContents().indexOf(" - URLEND>"));
					urlWithTag = c.getContents().substring(c.getContents().indexOf("<URL - "), c.getContents().indexOf(" - URLEND>") + 10);
				}
				cd.setId(c.getId());
				cd.setCategory(c.getCategory());
				cd.setTitle(c.getTitle());
				cd.setUrl(url);
				cd.setContents(c.getContents().replace(urlWithTag, ""));
				cd.setText(c.getText());
				cd.setPostDate(c.getPostDate());
				cds.add(cd);
			}
			mv.addObject("cds", cds);
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
