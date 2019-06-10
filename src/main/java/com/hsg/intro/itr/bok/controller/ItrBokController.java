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
	private ContentsServiceImpl cs;
	
	private String pageId = "itr/bok";
	// ����/Ư�� �߰�
	@RequestMapping(value = "insert.ib", method = RequestMethod.POST) // DI ������ ����
	public ModelAndView insertIbkBok(Contents content, ModelAndView mv
			, @RequestParam(required=false) MultipartFile file
			, HttpServletRequest request){

		String root = request.getSession().getServletContext().getRealPath("resources");
		String filePath = root + "/uploadFiles/itrbok_upload_file";
		
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
			fileName = newfileName + ext;
			content.setImage(fileName);
			//���ϰ�θ� itrbok ��ü�� �־���
			filePath = filePath + "/" + fileName;

			// �ش� ������ ���� ����
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
	
	// ����/Ư�� ����
		@RequestMapping(value = "update.ib", method = RequestMethod.POST) // DI ������ ����
		public ModelAndView updateIbkBok(Contents content, ModelAndView mv
				, @RequestParam(required=false) MultipartFile file
				, HttpServletRequest request){
			
			try {
				
					System.out.println("#################### update.ib file : " + file + "####################");
					System.out.println("#################### update.ib content : " + content + "####################");
					
					if(file == null) { // ������ null �� ���
						String root = request.getSession().getServletContext().getRealPath("resources");
						String filePath = root + "/uploadFiles/itrbok_upload_file";
						String fileName = "";
						String updatefilePath = "";
						// ##################### ���� ���� ó�� #######################
						String deleteFileName = cs.findById(content.getId()).getImage();

						// ���ϸ� ���̸� ����
						int randomNumber = (int)((Math.random()*10000)+1);
						
						SimpleDateFormat format = new SimpleDateFormat ("yyyyMMddHHmmss");
						
						Date nowTime = new Date();
						
						String newfileName = format.format(nowTime) + String.valueOf(randomNumber);	
						
						// Ȯ���� ���ϱ�
						
						int pos = file.getOriginalFilename().lastIndexOf(".");
						
						String ext = file.getOriginalFilename().substring(pos);
						
						fileName = newfileName + ext;
						
						content.setImage(fileName);
						//���ϰ�θ� itrbok ��ü�� �־���
						updatefilePath = filePath + "/" + fileName;
						
						content.setImage(fileName);
						
						// �ش� ������ ���� ����
						file.transferTo(new File(updatefilePath));
						// ##################### ���� ���� ó�� #######################
						String deleteFilePath = filePath + "/" + deleteFileName;
						
						// ##################### ���� ���� ó�� #######################
						File deleteFile = new File(deleteFilePath); // ���� URL
						
						if(deleteFile.exists()) {
							if(deleteFile.delete()) {
								System.out.println("���� ���� �Ϸ�");
							} else {
								System.out.println("���� ���� ����");
							}
						} else {
							System.out.println("������ �������� �ʽ��ϴ�.");
						}
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
		ContentsDomain cd = new ContentsDomain();
		try {
			content = cs.findById(id);
			String url = "";
			String urlWithTag = "";
			if(content.getText().indexOf("<URL - ") != -1) {
				url = content.getText().substring(content.getText().indexOf("<URL - ") + 7, content.getText().indexOf(" - URLEND>"));
				urlWithTag = content.getText().substring(content.getText().indexOf("<URL - "), content.getText().indexOf(" - URLEND>") + 10);
			}
			cd.setId(content.getId());
			cd.setCategory(content.getCategory());
			cd.setTitle(content.getTitle());
			cd.setUrl(url);
			cd.setText(content.getText().replace(urlWithTag, ""));
			cd.setImage(content.getImage());
			cd.setPostDate(content.getPostDate());
			
			mv.addObject("cd", cd);
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
			List<ContentsDomain> cds = new ArrayList<ContentsDomain>();
			for(Contents content: contents) {
				ContentsDomain cd = new ContentsDomain();
				String url = "";
				String urlWithTag = "";
				if(content.getText().indexOf("<URL - ") != -1) {
					url = content.getText().substring(content.getText().indexOf("<URL - ") + 7, content.getText().indexOf(" - URLEND>"));
					urlWithTag = content.getText().substring(content.getText().indexOf("<URL - "), content.getText().indexOf(" - URLEND>") + 10);
				}
				cd.setId(content.getId());
				cd.setCategory(content.getCategory());
				cd.setTitle(content.getTitle());
				cd.setUrl(url);
				cd.setText(content.getText().replace(urlWithTag, ""));
				cd.setImage(content.getImage());
				cd.setPostDate(content.getPostDate());
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
	
	// �ܺ� url ����
	@RequestMapping(value = "redirect.ib", method = RequestMethod.GET) // DI ������ ����
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
