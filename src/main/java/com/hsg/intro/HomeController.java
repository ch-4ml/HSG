package com.hsg.intro;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	private String pageId = "";
	
	SimpleDateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd HH:mm", Locale.KOREA);
	Date currentDate = new Date();
	String postDate = formatter.format (currentDate);
	
	@RequestMapping(value = "/")
	public ModelAndView home(ModelAndView mv, Locale locale) {
		logger.info("Welcome home! The client locale is {}.", locale);	
		try {
			// 메인 화면 인사말
			pageId = "main/gre";
			Contents content = cs.findOneByPageId(pageId);
			// 인사말 데이터 가공
			String image = "";
			// 태그를 포함한 이미지 추출
			String imageWithTag = "";
			if(content.getText().indexOf("data:image/jpeg;base64") != -1) {
				image = content.getText().substring(content.getText().indexOf("data:image/jpeg;base64")).substring(0, content.getText().substring(content.getText().indexOf("data:image/jpeg;base64")).indexOf("\" alt=\""));
				imageWithTag = content.getText().substring(content.getText().indexOf("<img ")).substring(0, content.getText().substring(content.getText().indexOf("<img ")).indexOf("\" />") + 4);
			}
			if(content.getText().indexOf("data:image/png;base64") != -1) {
				image = content.getText().substring(content.getText().indexOf("data:image/png;base64")).substring(0, content.getText().substring(content.getText().indexOf("data:image/png;base64")).indexOf("\" alt=\""));
				imageWithTag = content.getText().substring(content.getText().indexOf("<img ")).substring(0, content.getText().substring(content.getText().indexOf("<img ")).indexOf("\" />") + 4);
			}
			if(content.getText().indexOf("data:image/jpg;base64") != -1) {
				image = content.getText().substring(content.getText().indexOf("data:image/jpg;base64")).substring(0, content.getText().substring(content.getText().indexOf("data:image/jpg;base64")).indexOf("\" alt=\""));
				imageWithTag = content.getText().substring(content.getText().indexOf("<img ")).substring(0, content.getText().substring(content.getText().indexOf("<img ")).indexOf("\" />") + 4);
			}
			if(content.getText().indexOf("https://") != -1) {
				image = content.getText().substring(content.getText().indexOf("https://")).substring(0, content.getText().substring(content.getText().indexOf("https")).indexOf("\" alt=\""));
				imageWithTag = content.getText().substring(content.getText().indexOf("<img ")).substring(0, content.getText().substring(content.getText().indexOf("<img ")).indexOf("\" />") + 4);
			}
			if(content.getText().indexOf("http://") != -1) {
				image = content.getText().substring(content.getText().indexOf("http://")).substring(0, content.getText().substring(content.getText().indexOf("http")).indexOf("\" alt=\""));
				imageWithTag = content.getText().substring(content.getText().indexOf("<img ")).substring(0, content.getText().substring(content.getText().indexOf("<img ")).indexOf("\" />") + 4);
			}
			if(content.getText().indexOf("ftp://") != -1) {
				image = content.getText().substring(content.getText().indexOf("ftp://")).substring(0, content.getText().substring(content.getText().indexOf("ftp")).indexOf("\" alt=\""));
				imageWithTag = content.getText().substring(content.getText().indexOf("<img ")).substring(0, content.getText().substring(content.getText().indexOf("<img ")).indexOf("\" />") + 4);
			}if(content.getText().indexOf("ssh://") != -1) {
				image = content.getText().substring(content.getText().indexOf("ssh://")).substring(0, content.getText().substring(content.getText().indexOf("ssh")).indexOf("\" alt=\""));
				imageWithTag = content.getText().substring(content.getText().indexOf("<img ")).substring(0, content.getText().substring(content.getText().indexOf("<img ")).indexOf("\" />") + 4);
			}
			content.setImage(imageWithTag);
			content.setText(content.getText().replace(imageWithTag, ""));
			mv.addObject("gre", content);
			
			// 메인 화면 MOOC
			pageId = "main/mooc";
			image = "";
			content = cs.findOneByPageId(pageId);
			// MOOC 데이터 가공
			if(content.getText().indexOf("www.youtube.com/embed/") != -1) {
				// 유튜브 동영상 태그 추출
				image = content.getText().substring(content.getText().indexOf("www.youtube.com/embed/") - 15, content.getText().lastIndexOf("allowfullscreen") + 26);
			}
			content.setImage(image);
			content.setText(content.getText().replace(image, ""));
			mv.addObject("mooc", content);
			
			// 메인 화면 개발
			pageId = "main/dev";
			List<Contents> contents = cs.findByPageId(pageId);
			for(Contents c: contents) {
				// 이미지 src 부분 추출
				image = "";
				// 태그를 포함한 이미지 추출
				imageWithTag = "";
				if(c.getText().indexOf("data:image/jpeg;base64") != -1) {
					image = c.getText().substring(c.getText().indexOf("data:image/jpeg;base64")).substring(0, c.getText().substring(c.getText().indexOf("data:image/jpeg;base64")).indexOf("\" alt=\""));
					imageWithTag = c.getText().substring(c.getText().indexOf("<img ")).substring(0, c.getText().substring(c.getText().indexOf("<img ")).indexOf("\" />") + 4);
				}
				if(c.getText().indexOf("data:image/png;base64") != -1) {
					image = c.getText().substring(c.getText().indexOf("data:image/png;base64")).substring(0, c.getText().substring(c.getText().indexOf("data:image/png;base64")).indexOf("\" alt=\""));
					imageWithTag = c.getText().substring(c.getText().indexOf("<img ")).substring(0, c.getText().substring(c.getText().indexOf("<img ")).indexOf("\" />") + 4);
				}
				if(c.getText().indexOf("data:image/jpg;base64") != -1) {
					image = c.getText().substring(c.getText().indexOf("data:image/jpg;base64")).substring(0, c.getText().substring(c.getText().indexOf("data:image/jpg;base64")).indexOf("\" alt=\""));
					imageWithTag = c.getText().substring(c.getText().indexOf("<img ")).substring(0, c.getText().substring(c.getText().indexOf("<img ")).indexOf("\" />") + 4);
				}
				if(c.getText().indexOf("https://") != -1) {
					image = c.getText().substring(c.getText().indexOf("https://")).substring(0, c.getText().substring(c.getText().indexOf("https")).indexOf("\" alt=\""));
					imageWithTag = c.getText().substring(c.getText().indexOf("<img ")).substring(0, c.getText().substring(c.getText().indexOf("<img ")).indexOf("\" />") + 4);
				}
				if(c.getText().indexOf("http://") != -1) {
					image = c.getText().substring(c.getText().indexOf("http://")).substring(0, c.getText().substring(c.getText().indexOf("http")).indexOf("\" alt=\""));
					imageWithTag = c.getText().substring(c.getText().indexOf("<img ")).substring(0, c.getText().substring(c.getText().indexOf("<img ")).indexOf("\" />") + 4);
				}
				if(c.getText().indexOf("ftp://") != -1) {
					image = c.getText().substring(c.getText().indexOf("ftp://")).substring(0, c.getText().substring(c.getText().indexOf("ftp")).indexOf("\" alt=\""));
					imageWithTag = c.getText().substring(c.getText().indexOf("<img ")).substring(0, c.getText().substring(c.getText().indexOf("<img ")).indexOf("\" />") + 4);
				}if(c.getText().indexOf("ssh://") != -1) {
					image = c.getText().substring(c.getText().indexOf("ssh://")).substring(0, c.getText().substring(c.getText().indexOf("ssh")).indexOf("\" alt=\""));
					imageWithTag = c.getText().substring(c.getText().indexOf("<img ")).substring(0, c.getText().substring(c.getText().indexOf("<img ")).indexOf("\" />") + 4);
				}
				c.setImage(image);
				c.setText(c.getText().replace(imageWithTag, ""));
			}
			mv.addObject("dev", contents);
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
	
	@RequestMapping(value="updateGre.ma")
	public ModelAndView updateGre(Contents c, ModelAndView mv) {
		pageId = "main/gre";
		c.setPageId(pageId);
		try {
			cs.update(c);
			mv.setViewName("redirect:/");
		} catch (ContentsException e) {
			mv.addObject("message", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="updateMooc.ma")
	public ModelAndView updateMooc(Contents c, ModelAndView mv) {
		pageId = "main/mooc";
		c.setPageId(pageId);
		try {
			cs.update(c);
			mv.setViewName("redirect:/");
		} catch (ContentsException e) {
			mv.addObject("message", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="insertDevView.ma")
	public ModelAndView insertDevView(ModelAndView mv){
		
		mv.setViewName("main/dev/main_dev_00001");
		return mv;
	}
	
	@RequestMapping(value="insertDev.ma", method=RequestMethod.POST)
	public ModelAndView insertDev(Contents c, ModelAndView mv) {
		pageId = "main/dev";
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
			Contents c, ModelAndView mv) {
		pageId = "main/dev";
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
