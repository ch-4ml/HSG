package com.hsg.intro.itr.ptn.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hsg.intro.Exception.ContentsException;
import com.hsg.intro.common.PageInfo;
import com.hsg.intro.common.contents.model.service.ContentsServiceImpl;
import com.hsg.intro.common.contents.model.vo.Contents;

@Controller
@SessionAttributes("loginUser")
public class ItrPtnController {
	@Autowired
	private ContentsServiceImpl csi;

	private String pageId = "itr/ptn";

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.KOREA);
	Date currentDate = new Date();
	String postDate = formatter.format(currentDate);

	// ������ �̵�
	@RequestMapping(value = "view.ip")
	public ModelAndView view(ModelAndView mv) {
		
		mv.setViewName("itr/ptn/itr_ptn_00001");
		return mv;		
	}
	
	// ������ ajax call
	@RequestMapping(value = "get.ip", produces = "application/json;charset=utf8") // DI ������ ����
	public ModelAndView get(ModelAndView mv) {
		/**
		 * param currentPage : ���������� (defaultValue : 1)
		 * 
		 */
		Contents c = new Contents();
		
		c.setPageId(pageId);
		c.setPostDate(postDate);

		try {
			//List<Contents> cs = csi.findByPageId(pageId);
			//mv.addObject("images", images);
			mv.setViewName("jsonView");
		//} catch (ContentsException e) {
		//	e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}

	// contents ��
	@RequestMapping(value = "detail.ip")
	public ModelAndView detail(ModelAndView mv, @RequestParam(value="id") int id) {
		try {
			Contents c = csi.findById(id);
			mv.addObject("c", c);
			mv.setViewName("itr/ptn/itr_ptn_00003");
		} catch (ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");	  
		}
		return mv;
	}
	
	// contents �߰�
	@RequestMapping(value = "insert.ip", method = RequestMethod.POST) // DI ������ ���� 
	public ModelAndView insert(Contents c, ModelAndView mv,
			@RequestParam(required=false) MultipartFile file, HttpServletRequest request) {
	  
		// ################### ���� ���ε� ################### 
		if(!file.isEmpty()) { 
			String root = request.getSession().getServletContext().getRealPath("resources"); 
			String filePath = root + "/uploadFiles/itrptn_upload_file"; 
			String fileName = "";
			String originFileName = "";
			
			try { // ���ϸ� ���̸� ���� 
				
				int randomNumber = (int)((Math.random()*10000)+1);
				SimpleDateFormat format = new SimpleDateFormat ( "yyyyMMddHHmmss"); 
				Date nowTime = new Date(); String newfileName = format.format(nowTime) + String.valueOf(randomNumber);
				  
				// Ȯ���� ���ϱ� 
				int pos = file.getOriginalFilename().lastIndexOf("."); 
				String ext = file.getOriginalFilename().substring(pos); 
				fileName = newfileName + ext;
				originFileName = file.getOriginalFilename();
				  
				c.setText(fileName);
				c.setOrigin(originFileName);
				  
				// ���� ������ ���� 
				File uploadPath = new File(filePath); 
			if(!uploadPath.exists()) {
				uploadPath.mkdirs(); 
			}
			  
			// ���ϰ�θ� itrbok ��ü�� �־��� 
			filePath = filePath + "/" + fileName;
			  
			// �ش� ������ ���� ���� 
			file.transferTo(new File(filePath));
				  
			} catch (IllegalStateException e) { 
				mv.addObject("message",e.getMessage());
				mv.setViewName("common/errorPage");	  
			} catch (IOException e) { 
				mv.addObject("message",e.getMessage());
				mv.setViewName("common/errorPage");	  
			} 
		}
		// ################### ���� ���ε�###################
		  
		c.setPageId(pageId); c.setPostDate(postDate);
		  
		try { 
			csi.insert(c); 
			mv.setViewName("redirect:view.ip");
		  
		} catch (Exception e) { 
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");	  
		} 
		
		return mv;  
	}
	
	// contents ���� ��
	@RequestMapping(value = "updateView.ip")
	public ModelAndView updateView(ModelAndView mv, @RequestParam(value="id") int id) {
		try {
			Contents c = csi.findById(id);
			mv.addObject("c", c);
			mv.setViewName("itr/ptn/itr_ptn_00004");
		} catch (ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");	  
		}
		return mv;
	}
	// contents ����
	@RequestMapping(value = "update.ip", method = RequestMethod.POST) // DI ������ ����
	public ModelAndView update(Contents c, ModelAndView mv, 
			@RequestParam(required=false) MultipartFile file, 
			HttpServletRequest request) throws ContentsException {
		try {
				System.out.println("#################### update.lt file.isEmpty() : " + file.isEmpty() + "####################");
				System.out.println("#################### update.lt content : " + c + "####################");
				
				if(!file.isEmpty()) { // ������ null �� ���
					String root = request.getSession().getServletContext().getRealPath("resources");
					String filePath = root + "/uploadFiles/itrptn_upload_file";
					String fileName = "";
					String updatefilePath = "";
					String originFileName = "";
					
					// ##################### ���� ���� ó�� #######################
					// String deleteFileName = csi.findById(c.getId()).getContents();

					// ���ϸ� ���̸� ����
					int randomNumber = (int)((Math.random()*10000)+1);
					SimpleDateFormat format = new SimpleDateFormat ("yyyyMMddHHmmss");
					Date nowTime = new Date();
					String newfileName = format.format(nowTime) + String.valueOf(randomNumber);	
					
					// Ȯ���� ���ϱ�
					int pos = file.getOriginalFilename().lastIndexOf(".");
					String ext = file.getOriginalFilename().substring(pos);
					fileName = newfileName + ext;
					originFileName = file.getOriginalFilename();
					
					//���ϰ�θ� contents ��ü�� �־���
					c.setText(fileName);
					c.setOrigin(originFileName);
					
					System.out.println("#################### update.lt content : " + c + "####################");
					updatefilePath = filePath + "/" + fileName;
					System.out.println("#################### update.lt updatefilePath : " + updatefilePath + "####################");
					
					// �ش� ������ ���� ����
					file.transferTo(new File(updatefilePath));
					
					// ##################### ���� ���� ó�� #######################
					// String deleteFilePath = filePath + "/" + deleteFileName;
					
					// ##################### ���� ���� ó�� #######################
					// File deleteFile = new File(deleteFilePath); // ���� URL
					
					// System.out.println("#################### update.lt deleteFilePath : " + deleteFilePath + "####################");
					
					/*
					if(deleteFile.exists()) {
						if(deleteFile.delete()) {
							System.out.println("���� ���� �Ϸ�");
						} else {
							System.out.println("���� ���� ����");
						}
					} else {
						System.out.println("������ �������� �ʽ��ϴ�.");
					}
					*/
				}
				
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		c.setPageId(pageId);
		c.setPostDate(postDate);
		
		try {
			csi.update(c);
			
			List<Contents> cs = csi.findByPageId(pageId);
			mv.addObject("c", c);
			mv.setViewName("redirect:detailView.ip?id=" + c.getId());
		} catch (Exception e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("redirect:/common/errorPage");

		}
		return mv;
	}
	
	// contents ���� 
	@RequestMapping(value = "delete.ip", method = RequestMethod.GET) // DI ������ ����
	public ModelAndView delete(
			ModelAndView mv,
			@RequestParam(value="id") int id,
			HttpServletRequest request){
		
		try {
			String root = request.getSession().getServletContext().getRealPath("resources");
			String filePath = root + "/uploadFiles/itrptn_upload_file";
			String deleteFileName = csi.findById(id).getContents();
			// ##################### ���� ���� ó�� #######################
			String deleteFilePath = filePath + "/" + deleteFileName;
			
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
			mv.setViewName("redirect:view.ip");
		} catch (ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("redirect:/common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="download.ip")
	public void download(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="id") int id) {
		String root = request.getSession().getServletContext().getRealPath("resources");
		String filePath = root + "/uploadFiles/itrptn_upload_file";
	
		try {
			Contents c = csi.findById(id);
			String downloadFileName = c.getText();
			String downloadFilePath = filePath + "/" + downloadFileName;
			byte fileByte[] = FileUtils.readFileToByteArray(new File(downloadFilePath));
			
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; fileName=" + URLEncoder.encode(c.getOrigin(), "UTF-8") + ";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.getOutputStream().write(fileByte);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch(ContentsException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}