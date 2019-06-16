package com.hsg.intro.opn.lat.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.hsg.intro.common.PageInfo;
import com.hsg.intro.common.contents.model.service.ContentsServiceImpl;
import com.hsg.intro.common.contents.model.vo.Contents;

@Controller
@SessionAttributes("loginUser")
public class OpnLatController {
	@Autowired
	private ContentsServiceImpl csi;

	private String pageId = "opn/let";

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.KOREA);
	Date currentDate = new Date();
	String postDate = formatter.format(currentDate);

	@RequestMapping(value = "view.ol")
	public ModelAndView view(ModelAndView mv) {
		
		mv.setViewName("opn/lat/opn_lat_00001");
		return mv;		
	}
	
	@RequestMapping(value = "get.ol") // DI ������ ����
	public ModelAndView pagingContentList(ModelAndView mv, @RequestParam(defaultValue = "1") Integer currentPage) {
		/**
		 * param currentPage : ���������� (defaultValue : 1)
		 * 
		 */
		Contents c = new Contents();

		int limit;
		int maxPage;
		int startPage;
		int endPage;

		limit = 10; //

		System.out.println("currentPage : " + currentPage);

		int listCount = 0;

		try {
			listCount = csi.getListCount(pageId); // ����������
		} catch (ContentsException e) {
			e.printStackTrace();
		}

		maxPage = (int) ((double) listCount / limit + 0.9);
		startPage = ((int) ((double) currentPage / limit + 0.9) - 1) * limit + 1;
		endPage = startPage + limit - 1;
		if (maxPage < endPage) {
			endPage = maxPage;
		}

		PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage, currentPage);

		c.setPageId(pageId);
		c.setPostDate(postDate);

		mv.addObject("pi", pi);

		try {
			List<Contents> cs = csi.findByPageId(pageId, pi);
			mv.addObject("cs", cs);
			mv.setViewName("jsonView");
		} catch (ContentsException e) {
			e.printStackTrace();
		}
		
		return mv;
	}
	
	@RequestMapping(value = "insert.ol", method = RequestMethod.GET) // DI ������ ����
	public ModelAndView insertView(ModelAndView mv) {
		
		mv.setViewName("opn/lat/opn_lat_00002");
		return mv;
	}
	
	// contents �߰�
	@RequestMapping(value = "insert.ol", method = RequestMethod.POST) // DI ������ ���� 
	public ModelAndView insertOpnLat(Contents c, ModelAndView mv,
			@RequestParam(required=false) MultipartFile file, HttpServletRequest request) {
	  
		// ################### ���� ���ε� ################### 
		String root = request.getSession().getServletContext().getRealPath("resources"); 
		String filePath = root + "/uploadFiles/opnlat_upload_file"; 
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
			  
			c.setContents(fileName);
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
			  
		} catch (IllegalStateException e1) { 
			e1.printStackTrace(); 
		} catch (IOException e1) { 
			e1.printStackTrace(); 
		} 
		// ################### ���� ���ε�###################
		  
		c.setPageId(pageId); c.setPostDate(postDate);
		  
		try { 
			csi.insert(c); 
			mv.setViewName("redirect:view.ol");
		  
		} catch (Exception e) { mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
				  
		} 
		
		return mv;  
	}
	
	// contents ����
	@RequestMapping(value = "update.ol", method = RequestMethod.POST) // DI ������ ����
	public ModelAndView updateOpnLat(Contents c, ModelAndView mv, 
			@RequestParam(required=false) MultipartFile file, 
			HttpServletRequest request) throws ContentsException {
		try {
				System.out.println("#################### update.lt file.isEmpty() : " + file.isEmpty() + "####################");
				System.out.println("#################### update.lt content : " + c + "####################");
				
				if(!file.isEmpty()) { // ������ null �� ���
					String root = request.getSession().getServletContext().getRealPath("resources");
					String filePath = root + "/uploadFiles/opnlat_upload_file";
					String fileName = "";
					String updatefilePath = "";
					String originFileName = "";
					
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
					fileName = newfileName + ext;
					originFileName = file.getOriginalFilename();
					
					//���ϰ�θ� contents ��ü�� �־���
					c.setContents(fileName);
					c.setOrigin(originFileName);
					
					System.out.println("#################### update.lt content : " + c + "####################");
					updatefilePath = filePath + "/" + fileName;
					System.out.println("#################### update.lt updatefilePath : " + updatefilePath + "####################");
					
					// �ش� ������ ���� ����
					file.transferTo(new File(updatefilePath));
					
					// ##################### ���� ���� ó�� #######################
					String deleteFilePath = filePath + "/" + deleteFileName;
					
					// ##################### ���� ���� ó�� #######################
					File deleteFile = new File(deleteFilePath); // ���� URL
					
					System.out.println("#################### update.lt deleteFilePath : " + deleteFilePath + "####################");
					
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
		
		c.setPageId(pageId);
		c.setPostDate(postDate);
		
		try {
			csi.update(c);
			
			List<Contents> cs = csi.findByPageId(pageId);
			mv.addObject("cs", cs);
			mv.setViewName("redirect:view.ol");
		} catch (Exception e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("redirect:/common/errorPage");

		}
		return mv;
	}
	
	// contents ���� 
	@RequestMapping(value = "delete.ol", method = RequestMethod.GET) // DI ������ ����
	public ModelAndView deleteOpnLat(
			ModelAndView mv,
			@RequestParam(value="id") int id,
			HttpServletRequest request){
		
		try {
			String root = request.getSession().getServletContext().getRealPath("resources");
			String filePath = root + "/uploadFiles/opnlat_upload_file";
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
			mv.setViewName("redirect:view.ib");
		} catch (ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("redirect:/common/errorPage");
		}
		return mv;
	}
	
	// contents ���� 
	@RequestMapping(value = "find.ol", method = RequestMethod.GET) // DI ������ ����
	public ModelAndView findOpnLat(
			ModelAndView mv,
			@RequestParam(defaultValue="1") String con,
			@RequestParam(defaultValue="") String value,
			@RequestParam(defaultValue = "1") Integer currentPage){
			
			HashMap<String, String> hmap = new HashMap<String, String>();
			
			hmap.put("pageId", pageId);
			hmap.put("con", con); // ���� : 1 : ���� 2 : ���� 3 : ����+����
			hmap.put("value", value); // �˻���
			
		try {
			int limit;
			int maxPage;
			int startPage;
			int endPage;
			int listCount = 0;

			limit = 10; //

			System.out.println("currentPage : " + currentPage);
			
			listCount = csi.getListCount(hmap); // ����������

			maxPage = (int) ((double) listCount / limit + 0.9);
			startPage = ((int) ((double) currentPage / limit + 0.9) - 1) * limit + 1;
			endPage = startPage + limit - 1;
			if (maxPage < endPage) {
				endPage = maxPage;
			}

			PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage, currentPage);
			

			try {
				List<Contents> cList = csi.findByPageId(hmap, pi);
				mv.addObject("cList", cList);
			} catch(ContentsException e) {
				e.printStackTrace();
			}
			
			mv.addObject("pi", pi);

			
			mv.setViewName("redirect:view.ol");
		} catch (ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("redirect:/common/errorPage");
		}
		return mv;
	}
}