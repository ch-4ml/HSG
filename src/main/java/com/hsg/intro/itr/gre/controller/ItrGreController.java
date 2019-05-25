package com.hsg.intro.itr.gre.controller;

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
public class ItrGreController {
	@Autowired
	private ContentsServiceImpl cs;
	// �ַ� ����� �������� �̸��Դϴ�. contents ���̺��� page_id �÷��� �� ���Դϴ�.
	// page_id�� ������ ������ �ѷ��ִ� ���� ���������� ���� ���̺��� ����� �� �ֵ��� ���� �÷��Դϴ�.
	private String pageId = "itr/gre"; 
	
	// CEO �λ縻 ����
	@RequestMapping(value="view.ig") // DI ������ ����
	public ModelAndView viewContents(ModelAndView mv){		
		try {
			List<Contents> contents = cs.findByPageId(pageId);// select�� ������ ������
			mv.addObject("contents", contents);
		} catch(ContentsException e) {
			e.printStackTrace();
		}
		mv.setViewName("itr/gre/itr_gre_00001");
		
		return mv;
	}
	
	// CEO �λ縻 ������Ʈ
	@RequestMapping(value="update.ig", method=RequestMethod.POST) // DI ������ ����
	public ModelAndView updateContents(Contents g, ModelAndView mv, HttpServletRequest request) {
		
		// �Ű������� �� ����
		int id = Integer.parseInt(request.getParameter("id"));
		String text = request.getParameter("text"); 
		g.setContentsId(id);
		g.setText(text);
		
		System.out.println("in update.ig : " + g);
		
		try {
			cs.updateContents(g);
			List<Contents> contents = cs.findByPageId(pageId);
			
			mv.addObject("contents", contents);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("itr/gre/itr_gre_00001");
		
		return mv;
	}
	
	// CEO �λ縻 ������Ʈ ��
	@RequestMapping(value="viewUpdateImg.ig")
	public ModelAndView viewUpdateImg(ModelAndView mv, @RequestParam(value="id") int id) {
		
		mv.addObject("id", id);
		mv.setViewName("itr/gre/itr_gre_00002");
		return mv;
	}
	
	// CEO �λ縻 �̹��� ������Ʈ
	@RequestMapping(value="updateImg.ig", method=RequestMethod.POST)
	public ModelAndView updateContentsImg(Contents g, ModelAndView mv,
			@RequestParam(required=false) MultipartFile gfile,
			HttpServletRequest request) {
		
		// POST�� ���� ���ε��� ���� üũ
		System.out.println("controller gfile : " + gfile);
		
		int id = Integer.parseInt(request.getParameter("id"));
		String root = request.getSession().getServletContext().getRealPath("resources");
		System.out.println(root);
		String filePath = root + "\\uploadFiles\\itrgre_upload_file";
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

			// ���� ��θ� itrgre ��ü�� �־���
			filePath = filePath + "\\" + newFileName + ext;
			System.out.println("controller filePath: " + filePath);
			g.setImage(filePath);
			
			// �ش� ������ ���� ����
			gfile.transferTo(new File(filePath));
		} catch(IllegalStateException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
				
		try {
			g.setContentsId(id);
			System.out.println("controller gre : " + g);
			cs.updateContentsImg(g);
			List<Contents> contents = cs.findByPageId(pageId);
			
			mv.addObject("contents", contents);
			mv.setViewName("itr/gre/itr_gre_00001");
		}
		catch (Exception e) {
			mv.addObject("message", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}
}
