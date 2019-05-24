/*
 * �����
 */

package com.hsg.intro.common.contents.controller;

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
public class ContentsController {
	@Autowired
	private ContentsServiceImpl cs;
	
	@RequestMapping(value="view.ct") // DI ������ ����
	public ModelAndView viewContents(ModelAndView mv,
			@RequestParam(value="pageId") String pageId) {		
		try {
			List<Contents> contents = cs.findByPageId(pageId);// select�� ������ ������
			for(Contents c : contents) {
				String text = c.getText();
				text.substring(text.indexOf("embed/"), text.indexOf("embed/") + 11);
				System.out.println("��� : " + text);
			}
			mv.addObject("contents", contents);
		} catch(ContentsException e) {
			e.printStackTrace();
		}
		mv.setViewName(pageId);
		return mv;
	}
	
	// ������Ʈ
	@RequestMapping(value="update.ct", method=RequestMethod.POST) // DI ������ ����
	public ModelAndView updateContents(
			Contents c, ModelAndView mv, 
			@RequestParam(value="id") int id,
			@RequestParam(value="text") String text,
			@RequestParam(value="pageId") String pageId) {
		
		// �Ű������� �� ����
		c.setContentsId(id);
		c.setText(text);
		
		System.out.println("in update : " + c);
		
		try {
			cs.updateContents(c);
			List<Contents> contents = cs.findByPageId(pageId);
			
			mv.addObject("contents", contents);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName(pageId);
		
		return mv;
	}
	
	// �̹��� ������Ʈ ��
	@RequestMapping(value="viewUpdateImg.ct")
	public ModelAndView updateImgView(ModelAndView mv,
			@RequestParam(value="id") int id,
			@RequestParam(value="pageId") String pageId) {
		
		mv.addObject("id", id);
		mv.setViewName(pageId);
		return mv;
	}
	
	// �̹��� ������Ʈ
	@RequestMapping(value="updateImg.ct", method=RequestMethod.POST)
	public ModelAndView updateContentsImg(
			Contents c, ModelAndView mv,
			@RequestParam(required=false) MultipartFile gfile,
			@RequestParam(value="id") int id,
			@RequestParam(value="pageId") String pageId,
			HttpServletRequest request) {
		
		// POST�� ���� ���ε��� ���� üũ
		System.out.println("controller gfile : " + gfile);
		
		String root = request.getSession().getServletContext().getRealPath("resources");
		// ���ε� ������ ����
		String category = pageId.substring(0, pageId.indexOf("0")-1);
		String filePath = root + "\\uploadFiles\\" + category;
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
			c.setImage(filePath);
			
			// �ش� ������ ���� ����
			gfile.transferTo(new File(filePath));
		} catch(IllegalStateException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
				
		try {
			c.setContentsId(id);
			cs.updateContentsImg(c);
			List<Contents> contents = cs.findByPageId(pageId);
			
			mv.addObject("contents", contents);
			mv.setViewName(pageId);
		}
		catch (Exception e) {
			mv.addObject("message", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}
}
