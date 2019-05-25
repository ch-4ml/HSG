package com.hsg.intro.itr.bok.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
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

@Controller
@SessionAttributes("loginUser")
public class ItrBokController {
	@Autowired
	private ContentsServiceImpl cs;
	
	private String pageId = "itr/bok";
	// ����/Ư�� �߰�
	@RequestMapping(value = "insert.ib", method = RequestMethod.POST) // DI ������ ����
	public ModelAndView insertIbkBok(Contents c, ModelAndView mv
			, @RequestParam(required=false) MultipartFile file
			, HttpServletRequest request){
		
		System.out.println("controller file : " + file);

		String root = request.getSession().getServletContext().getRealPath("resources");
		String filePath = root + "\\uploadFiles\\itrbok_upload_file";
		
		String fileName = "";
		
		try {
			
			// ���ϸ� ���̸� ����
			
			int randomNumber = (int)((Math.random()*10000)+1);
			
			SimpleDateFormat format = new SimpleDateFormat ( "yyyyMMddHHmmss");
			
			Date nowTime = new Date();
			
			String newfileName = format.format(nowTime) + String.valueOf(randomNumber);
			
			// Ȯ���� ���ϱ�
			
			int pos = file.getOriginalFilename().lastIndexOf(".");

			String ext = file.getOriginalFilename().substring(pos);
			
			//���ϰ�θ� itrbok ��ü�� �־���
			
			fileName = newfileName + ext;
			
			c.setPageId(pageId);
			c.setImage(fileName);
			
			// �ش� ������ ���� ����
			file.transferTo(new File(filePath));
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("controller bok : " + c);
		
		try {
			cs.insert(c);			
			mv.setViewName("redirect:view.ib");
			
		} catch (Exception e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");

		}
		return mv;
		
	}
	
	// ����/Ư�� ����
		@RequestMapping(value = "update.ib", method = RequestMethod.POST) // DI ������ ����
		public ModelAndView updateIbkBok(Contents c, ModelAndView mv
				, @RequestParam(required=false) MultipartFile file
				, HttpServletRequest request){
			
			try {
				if(!file.getOriginalFilename().equals("")) {
					// ���ϸ� ���̸� ����
					
					String root = request.getSession().getServletContext().getRealPath("resources");
					
					String filePath = root + "\\uploadFiles\\itrbok_upload_file";
						
					int randomNumber = (int)((Math.random()*10000)+1);
					
					SimpleDateFormat format = new SimpleDateFormat ( "yyyyMMddHHmmss");
					
					Date nowTime = new Date();
					
					String newfileName = format.format(nowTime) + String.valueOf(randomNumber);
					
					// Ȯ���� ���ϱ�
					
					int pos = file.getOriginalFilename().lastIndexOf(".");

					String ext = file.getOriginalFilename().substring(pos);
					
					//���ϰ�θ� itrbok ��ü�� �־���
					
					filePath = filePath + "\\" + newfileName + ext;
					
					System.out.println("controller filePath : " + filePath);
					
					c.setImage(filePath);
					
					// �ش� ������ ���� ����
					file.transferTo(new File(filePath));
				} else {
					c.setImage(null);
				}
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			System.out.println("controller bok : " + c);
			
			try {
				cs.update(c);
				
				List<Contents> contents = cs.findByPageId(pageId);
				mv.addObject("contents", contents);
				
				mv.setViewName("redirect:view.ib");
				
			} catch (Exception e) {
				mv.addObject("message",e.getMessage());
				mv.setViewName("redirect:/common/errorPage");

			}
			return mv;
			
		}
	
	// ����/Ư�� �߰� ������ �̵�
	@RequestMapping(value = "insertView.ib", method = RequestMethod.GET) // DI ������ ����
	public ModelAndView insertViewIbkBok(ModelAndView mv){
		
		mv.setViewName("itr/bok/itr_bok_00002");
		
		return mv;
		
	}
	
	// ����/Ư�� ���������� �̵�
	@RequestMapping(value = "updateView.ib", method = RequestMethod.GET) // DI ������ ����
	public ModelAndView updateViewIbkBok(
			ModelAndView mv, 
			@RequestParam(value="id") int id) {
		Contents content;
		try {
			content = cs.findById(id);	
			mv.addObject("content", content);
			mv.setViewName("itr/bok/itr_bok_00003");
			
		} catch (ContentsException e) {
			e.printStackTrace();
		}
		
		return mv;
		
	}
	
	// ����/Ư�� ���� ó��
	@RequestMapping(value = "delete.ib", method = RequestMethod.GET) // DI ������ ����
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
	
	// ����/Ư�� ������ �̵�
	@RequestMapping(value = "view.ib", method = RequestMethod.GET) // DI ������ ����
	public ModelAndView viewIbkBok(ModelAndView mv){
		try {
			List<Contents> contents = cs.findByPageId(pageId);
			mv.addObject("contents", contents);
			mv.setViewName("itr/bok/itr_bok_00001");
		} catch (ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("redirect:/common/errorPage");
		}
		return mv;
		
	}

}
