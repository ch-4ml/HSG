package com.hsg.intro.itr.his.controller;

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
public class ItrHisController {
	@Autowired
	private ContentsServiceImpl cs;
	// �ַ� ����� �������� �̸��Դϴ�. contents ���̺��� page_id �÷��� �� ���Դϴ�.
	// page_id�� ������ ������ �ѷ��ִ� ���� ���������� ���� ���̺��� ����� �� �ֵ��� ���� �÷��Դϴ�.
	private String pageId = "itr/his";

	// CEO �λ縻 ����
	@RequestMapping(value = "view.ih", method = RequestMethod.GET) // DI ������ ����
	public ModelAndView viewContents(ModelAndView mv) {
		try {
			Contents content = cs.findOneByPageId(pageId);// select�� ������ ������
			mv.addObject("content", content);
			mv.setViewName("itr/his/itr_his_00001");
		} catch (ContentsException e) {
			mv.addObject("message", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}

	// �Ϲ���Ȳ �� ���� ������Ʈ
	@RequestMapping(value = "update.ih", method = RequestMethod.POST) // DI ������ ����
	public ModelAndView updateContents(Contents c, ModelAndView mv) {
		try {
			cs.update(c);
			mv.setViewName("redirect:view.ih");
		} catch (ContentsException e) {
			mv.addObject("message", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
}
