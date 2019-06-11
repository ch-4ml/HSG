package com.hsg.intro.edu.sft.controller;

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
public class EduSftController {
	@Autowired
	private ContentsServiceImpl csi;
	// �ַ� ����� �������� �̸��Դϴ�. cs ���̺��� page_id �÷��� �� ���Դϴ�.
	// page_id�� ������ ������ �ѷ��ִ� ���� ���������� ���� ���̺��� ����� �� �ֵ��� ���� �÷��Դϴ�.
	private String pageId = "edu/sft";

	// CEO �λ縻 ����
	@RequestMapping(value = "view.es") // DI ������ ����
	public ModelAndView viewContents(ModelAndView mv) {
		try {
			Contents c = csi.findOneByPageId(pageId);// select�� ������ ������
			mv.addObject("c", c);
			mv.setViewName("edu/sft/edu_sft_00001");
		} catch (ContentsException e) {
			mv.addObject("message", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}

	// CEO �λ縻 ������Ʈ
	@RequestMapping(value = "update.es", method = RequestMethod.POST) // DI ������ ����
	public ModelAndView updateContents(Contents c, ModelAndView mv) {
		try {
			csi.update(c);
			mv.setViewName("redirect:view.es");
		} catch (ContentsException e) {
			mv.addObject("message", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
}
