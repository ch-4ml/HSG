package com.hsg.intro;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hsg.intro.Exception.ContentsException;
import com.hsg.intro.common.contents.model.service.ContentsServiceImpl;
import com.hsg.intro.common.contents.model.vo.Contents;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private ContentsServiceImpl cs;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private String pageId = "main/dev";
	
	SimpleDateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd HH:mm", Locale.KOREA);
	Date currentDate = new Date();
	String postDate = formatter.format (currentDate);
	
	@RequestMapping(value = "/")
	public ModelAndView home(ModelAndView mv, Locale locale) {
		logger.info("Welcome home! The client locale is {}.", locale);	
		
		try {
			List<Contents> contents = cs.findByPageId(pageId);
			for(Contents c: contents) {
				// base64 형식의 이미지 src 부분 추출
				String image = "";
				if(c.getText().indexOf("data:image/png;base64") != -1)
					image = c.getText().substring(c.getText().indexOf("data:image/png;base64")).substring(0, c.getText().substring(c.getText().indexOf("data:image/png;base64")).indexOf("\" alt=\""));
				if(c.getText().indexOf("http") != -1)
					image = c.getText().substring(c.getText().indexOf("http")).substring(0, c.getText().substring(c.getText().indexOf("http")).indexOf("\" alt=\""));
				if(c.getText().indexOf("ftp") != -1)
					image = c.getText().substring(c.getText().indexOf("ftp")).substring(0, c.getText().substring(c.getText().indexOf("ftp")).indexOf("\" alt=\""));
				if(c.getText().indexOf("ssh") != -1)
					image = c.getText().substring(c.getText().indexOf("ssh")).substring(0, c.getText().substring(c.getText().indexOf("ssh")).indexOf("\" alt=\""));
				System.out.println("image : " + image);
				// base64 형식의 이미지 src 부분 추출(태그 포함)
				String imageWithTag = c.getText().substring(c.getText().indexOf("<img ")).substring(0, c.getText().substring(c.getText().indexOf("<img ")).indexOf("\" />") + 4);
				System.out.println("imageWithTag : " + imageWithTag);
				c.setImage(image);
				c.setText(c.getText().replace(imageWithTag, ""));
			}
			mv.addObject("contents", contents);
			mv.setViewName("main/index");
		} catch(ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="view.ma")
	public String view() {
		return "redirect:/";
	}
	
	@RequestMapping(value="insertDevView.ma")
	public ModelAndView insertDevView(ModelAndView mv){
		
		mv.setViewName("main/dev/main_dev_00001");
		return mv;
	}
	
	@RequestMapping(value="insertDev.ma", method=RequestMethod.POST)
	public ModelAndView insertDev(Contents c, ModelAndView mv) {
		
		c.setPageId(pageId);
		c.setPostDate(postDate);
		try {
			cs.insert(c);
			mv.setViewName("redirect:/");
		} catch(ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}		
		return mv;
	}
	
	@RequestMapping(value="updateDevView.ma")
	public ModelAndView updateDevView(
			Contents c, ModelAndView mv,
			@RequestParam(value="id") int id){
		
		try {
			Contents content = cs.findById(id);
			mv.addObject("content", content);
			mv.setViewName("main/dev/main_dev_00002");
		} catch(ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}		
		return mv;
	}
	
	@RequestMapping(value="updateDev.ma", method=RequestMethod.POST)
	public ModelAndView updateDev(
			Contents c, ModelAndView mv,
			@RequestParam(value="id") int id) {
		
		c.setId(id);
		c.setPageId(pageId);
		c.setPostDate(postDate);
		try {
			cs.update(c);
			mv.setViewName("redirect:/");
		} catch(ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}		
		return mv;
	}
	
	@RequestMapping(value="deleteDev.ma")
	public ModelAndView deleteDev(ModelAndView mv,
			@RequestParam(value="id") int id) {	
		try {
			cs.delete(id);
			mv.setViewName("redirect:/");
		} catch(ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
}
