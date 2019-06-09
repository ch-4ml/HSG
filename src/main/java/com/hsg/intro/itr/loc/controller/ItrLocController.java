package com.hsg.intro.itr.loc.controller;

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
public class ItrLocController {

	@Autowired
	private ContentsServiceImpl cs;

	private String pageId = "itr/loc";

	@RequestMapping(value = "view.il", method = RequestMethod.GET) // DI 의존성 주입
	public ModelAndView viewContents(ModelAndView mv) {
		try {
			Contents content = cs.findOneByPageId(pageId);
			mv.addObject("content", content);
			mv.setViewName("itr/loc/itr_loc_00001");
		} catch (ContentsException e) {
			mv.addObject("message", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}

	@RequestMapping(value = "update.il", method = RequestMethod.POST)
	public ModelAndView update(Contents c, ModelAndView mv) {
		try {
			cs.update(c);
			mv.setViewName("redirect:view.il");
		} catch (ContentsException e) {
			mv.addObject("message", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
}