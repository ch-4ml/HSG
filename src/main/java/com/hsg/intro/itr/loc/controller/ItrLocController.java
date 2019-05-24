package com.hsg.intro.itr.loc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("loginUser")
public class ItrLocController {
	
	@RequestMapping(value="view.il", method=RequestMethod.GET) // DI 의존성 주입
	public ModelAndView viewContents(ModelAndView mv){		
		
		mv.setViewName("itr/loc/itr_loc_00001");
		
		return mv;
	}
}