package com.hsg.intro.itr.bok.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
	private ContentsServiceImpl csi;
	
	private String pageId = "itr/bok";
	
	private SimpleDateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd HH:mm", Locale.KOREA);
	private Date currentDate = new Date();
	private String postDate = formatter.format (currentDate);
	
	private String root = "/hsglobal03/tomcat/webapps/var/HSG/uploadFiles";
	private String filePath = "/itrbok_upload_files";
	
	// ����/Ư�� �߰�
	@RequestMapping(value = "insert.ib", method = RequestMethod.POST) // DI ������ ����
	public ModelAndView insertIbkBok(Contents c, ModelAndView mv, 
			@RequestParam(required=false) MultipartFile file, 
			HttpServletRequest request) {

		// ################### ���� ���ε� ###################
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
			
			// /xxxxxx_upload_files/���ϸ�.ext ���·� ��ü�� ���� 
			fileName = filePath + "/" + newfileName + ext;
			c.setContents(fileName);
			
			// ���� ������ ����
			File uploadPath = new File(root + filePath);
			if(!uploadPath.exists()) {
				uploadPath.mkdirs();
			}
		
			// �ش� ������ ���� ����
			file.transferTo(new File(root + fileName));
			
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// ################### ���� ���ε� ###################
		
		c.setPageId(pageId);
		c.setPostDate(postDate);
		
		try {
			csi.insert(c);			
			mv.setViewName("redirect:view.ib");
			
		} catch (Exception e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");

		}
		return mv;
		
	}
	
	// ����/Ư�� ����
		@RequestMapping(value = "update.ib", method = RequestMethod.POST) // DI ������ ����
		public ModelAndView updateIbkBok(Contents c, ModelAndView mv, 
				@RequestParam(required=false) MultipartFile file, 
				HttpServletRequest request) throws ContentsException {
			try {
					System.out.println("#################### update.ib file.isEmpty() : " + file.isEmpty() + "####################");
					System.out.println("#################### update.ib content : " + c + "####################");
					
					if(!file.isEmpty()) { // ������ null �� ���
						String fileName = "";
						// ##################### ���� ���� ó�� #######################
						String deleteFileName = csi.findById(c.getId()).getContents();

						// ���ϸ� ���̸� ����
						int randomNumber = (int)((Math.random()*10000)+1);
						SimpleDateFormat format = new SimpleDateFormat ("yyyyMMddHHmmss");
						Date nowTime = new Date();
						String newfileName = format.format(nowTime) + String.valueOf(randomNumber);	
						
						// Ȯ���� ���ϱ�
						int pos = file.getOriginalFilename().lastIndexOf(".");
						String ext = file.getOriginalFilename().substring(pos);
						
						// /xxxxxx_upload_files/���ϸ�.ext ���·� ��ü�� ���� 
						fileName = filePath + "/" + newfileName + ext;
						c.setContents(fileName);
						
						// ���� ������ ����
						File uploadPath = new File(root + filePath);
						if(!uploadPath.exists()) {
							uploadPath.mkdirs();
						}
					
						// �ش� ������ ���� ����
						file.transferTo(new File(root + fileName));
						
						// ##################### ���� ���� ó�� #######################
						String deleteFilePath = root + deleteFileName;
						
						// ##################### ���� ���� ó�� #######################
						File deleteFile = new File(deleteFilePath); // ���� URL
						
						System.out.println("#################### update.ee deleteFilePath : " + deleteFilePath + "####################");
						
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
				mv.addObject("message",e1.getMessage());
				mv.setViewName("redirect:/common/errorPage");
			} catch (IOException e1) {
				mv.addObject("message",e1.getMessage());
				mv.setViewName("redirect:/common/errorPage");
			}
			
			c.setPageId(pageId);
			c.setPostDate(postDate);
			
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
	
	// ����/Ư�� �߰� ������ �̵�
	@RequestMapping(value = "insertView.ib", method = RequestMethod.GET) // DI ������ ����
	public ModelAndView insertViewIbkBok(ModelAndView mv){
		
		mv.setViewName("itr/bok/itr_bok_00002");
		
		return mv;
		
	}
	
	// ����/Ư�� ���������� �̵�
	@RequestMapping(value = "updateView.ib", method = RequestMethod.GET) // DI ������ ����
	public ModelAndView updateViewIbkBok(Contents c, ModelAndView mv) {
		try {
			c = csi.findById(c.getId());
			
			mv.addObject("c", c);
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
			@RequestParam(value="id") int id,
			HttpServletRequest request){
		
		try {
			String deleteFileName = csi.findById(id).getContents();
			// ##################### ���� ���� ó�� #######################
			String deleteFilePath = root + deleteFileName;
			
			// ##################### ���� ���� ó�� #######################
			File deleteFile = new File(deleteFilePath); // ���� URL
			
			System.out.println("#################### update.ib deleteFilePath : " + deleteFilePath + "####################");
			
			if(deleteFile.exists()) {
				if(deleteFile.delete()) {
					System.out.println("���� ���� �Ϸ�");
				} else {
					System.out.println("���� ���� ����");
				}
			} else {
				System.out.println("������ �������� �ʽ��ϴ�.");
			}
			
			csi.delete(id);
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
			List<Contents> cs = csi.findByPageId(pageId);
			mv.addObject("cs", cs);
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