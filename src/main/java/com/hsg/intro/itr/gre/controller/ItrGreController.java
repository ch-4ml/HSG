package com.hsg.intro.itr.gre.controller;

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
public class ItrGreController {
	@Autowired
	private ContentsServiceImpl csi;
	// 주로 사용할 페이지의 이름입니다. contents 테이블의 page_id 컬럼에 들어갈 값입니다.
	// page_id는 간단한 내용을 뿌려주는 단일 페이지에서 같은 테이블을 사용할 수 있도록 만든 컬럼입니다.
	private String pageId = "itr/gre";

	// CEO 인사말 보기
	@RequestMapping(value = "view.ig") // DI 의존성 주입
	public ModelAndView viewContents(ModelAndView mv) {
		try {
			Contents c = csi.findOneByPageId(pageId);// select로 데이터 가져옴
			mv.addObject("c", c);
			mv.setViewName("itr/gre/itr_gre_00001");
		} catch (ContentsException e) {
			mv.addObject("message", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}

	// CEO 인사말 업데이트
	@RequestMapping(value = "update.ig", method = RequestMethod.POST) // DI 의존성 주입
	public ModelAndView updateContents(Contents c, ModelAndView mv) {
		try {
			csi.update(c);
			mv.setViewName("redirect:view.ig");
		} catch (ContentsException e) {
			mv.addObject("message", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
}
