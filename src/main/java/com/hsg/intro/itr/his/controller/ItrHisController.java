package com.hsg.intro.itr.his.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
public class ItrHisController {
	@Autowired
	private ContentsServiceImpl csi;
	// 주로 사용할 페이지의 이름입니다. contents 테이블의 page_id 컬럼에 들어갈 값입니다.
	// page_id는 간단한 내용을 뿌려주는 단일 페이지에서 같은 테이블을 사용할 수 있도록 만든 컬럼입니다.
	private String pageId = "itr/his";

	// CEO 인사말 보기
	@RequestMapping(value = "view.ih", method = RequestMethod.GET) // DI 의존성 주입
	public ModelAndView viewContents(ModelAndView mv) {
		try {
			Contents c = csi.findOneByPageId(pageId);// select로 데이터 가져옴
			mv.addObject("c", c);
			mv.setViewName("itr/his/itr_his_00001");
		} catch (ContentsException e) {
			mv.addObject("message", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}

	// 일반현황 및 연혁 업데이트
	@RequestMapping(value = "update.ih", method = RequestMethod.POST) // DI 의존성 주입
	public ModelAndView updateContents(Contents c, ModelAndView mv) {
		c.setPageId(pageId);
		try {
			csi.update(c);
			mv.setViewName("redirect:view.ih");
		} catch (ContentsException e) {
			mv.addObject("message", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
}
