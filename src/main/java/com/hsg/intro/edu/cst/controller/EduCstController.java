package com.hsg.intro.edu.cst.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hsg.intro.Exception.ContentsException;
import com.hsg.intro.common.contents.model.service.ContentsServiceImpl;
import com.hsg.intro.common.contents.model.vo.Contents;

@Controller
@SessionAttributes("loginUser")
public class EduCstController {
	@Autowired
	private ContentsServiceImpl cs;
	// �ַ� ����� �������� �̸��Դϴ�. contents ���̺��� page_id �÷��� �� ���Դϴ�.
	// page_id�� ������ ������ �ѷ��ִ� ���� ���������� ���� ���̺��� ����� �� �ֵ��� ���� �÷��Դϴ�.
	private String pageId = "edu/cst"; 
	
	// CEO �λ縻 ����
	@RequestMapping(value="view.ec") // DI ������ ����
	public ModelAndView viewContents(ModelAndView mv){		
		try {
			List<Contents> contents = cs.findByPageId(pageId);// select�� ������ ������
			mv.addObject("contents", contents);
			mv.setViewName("edu/cst/edu_cst_00001");
		} catch(ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}		
		return mv;
	}
	
	// CEO �λ縻 ������Ʈ
	@RequestMapping(value="update.ec", method=RequestMethod.POST) // DI ������ ����
	public ModelAndView updateContents(Contents c, ModelAndView mv, HttpServletRequest request) {
		
		// �Ű������� �� ����
		int id = Integer.parseInt(request.getParameter("id"));
		String text = request.getParameter("text"); 
		c.setId(id);
		c.setText(text);
		
		System.out.println("in update.ec : " + c);
		
		try {
			cs.update(c);
			mv.setViewName("redirect:view.ec");
		} catch (ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}		
		return mv;
	}
}
