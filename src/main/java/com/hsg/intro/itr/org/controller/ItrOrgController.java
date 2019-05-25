package com.hsg.intro.itr.org.controller;

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
public class ItrOrgController {
	@Autowired
	private ContentsServiceImpl cs;
	// �ַ� ����� �������� �̸��Դϴ�. contents ���̺��� page_id �÷��� �� ���Դϴ�.
	// page_id�� ������ ������ �ѷ��ִ� ���� ���������� ���� ���̺��� ����� �� �ֵ��� ���� �÷��Դϴ�.
	private String pageId = "itr/org/itr_org_00001"; 
	
	// CEO �λ縻 ����
	@RequestMapping(value="view.io", method=RequestMethod.GET) // DI ������ ����
	public ModelAndView viewContents(ModelAndView mv){		
		try {
			List<Contents> contents = cs.findByPageId(pageId);// select�� ������ ������
			
			mv.addObject("contents", contents);
		} catch(ContentsException e) {
			e.printStackTrace();
		}
		mv.setViewName("itr/org/itr_org_00001");
		
		return mv;
	}
	
	// CEO �λ縻 ������Ʈ
	@RequestMapping(value="update.io", method=RequestMethod.POST) // DI ������ ����
	public ModelAndView updateContents(Contents c, ModelAndView mv, HttpServletRequest request) {
		
		// �Ű������� �� ����
		int id = Integer.parseInt(request.getParameter("id"));
		String text = request.getParameter("text"); 
		c.setId(id);
		c.setText(text);
		
		System.out.println("in update.io : " + c);
		
		try {
			cs.update(c);
			List<Contents> contents = cs.findByPageId(pageId);
			
			mv.addObject("contents", contents);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("itr/org/itr_org_00001");
		
		return mv;
	}
	
	// CEO �λ縻 ������Ʈ ��
	@RequestMapping(value="viewUpdateImg.io")
	public ModelAndView viewUpdateImg(ModelAndView mv, @RequestParam(value="id") int id) {
		
		mv.addObject("id", id);
		mv.setViewName("itr/org/itr_org_00002");
		return mv;
	}
	
	// CEO �λ縻 �̹��� ������Ʈ
	@RequestMapping(value="updateImg.io", method=RequestMethod.POST)
	public ModelAndView updateContentsImg(Contents c, ModelAndView mv,
			@RequestParam(required=false) MultipartFile gfile,
			HttpServletRequest request) {
		
		// POST�� ���� ���ε��� ���� üũ
		System.out.println("controller gfile : " + gfile);
		
		int id = Integer.parseInt(request.getParameter("id"));
		String root = request.getSession().getServletContext().getRealPath("resources");
		System.out.println(root);
		String filePath = root + "\\uploadFiles\\itrorg_upload_file";
		try {
			// ���� �� �� �̸� ����
			int randomNumber = (int)((Math.random()*10000)+1);
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			Date now = new Date();
			String newFileName = format.format(now) + String.valueOf(randomNumber);
			
			// Ȯ���� ���ϱ�
			int pos = gfile.getOriginalFilename().lastIndexOf(".");
			String ext = gfile.getOriginalFilename().substring(pos);
			
			// ���� ���� ���� Ȯ�� �� ����
			File dir = new File(filePath);
			if(!dir.isDirectory()) {
				dir.mkdirs();
			}

			// ���� ��θ� itrorg ��ü�� �־���
			filePath = filePath + "\\" + newFileName + ext;
			System.out.println("controller filePath: " + filePath);
			c.setImage(filePath);
			
			// �ش� ������ ���� ����
			gfile.transferTo(new File(filePath));
		} catch(IllegalStateException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
				
		try {
			c.setId(id);
			System.out.println("controller org : " + c);
			cs.updateImage(c);
			List<Contents> contents = cs.findByPageId(pageId);
			
			mv.addObject("contents", contents);
			mv.setViewName("itr/org/itr_org_00001");
		}
		catch (Exception e) {
			mv.addObject("message", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}
}
