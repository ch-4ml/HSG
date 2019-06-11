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
	private ContentsServiceImpl csi;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private String pageId = "";
	
	SimpleDateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd HH:mm", Locale.KOREA);
	Date currentDate = new Date();
	String postDate = formatter.format (currentDate);
	
	@RequestMapping(value = "/")
	public ModelAndView home(ModelAndView mv, Locale locale) {
		logger.info("Welcome home! The client locale is {}.", locale);	
		String contents = "";
		try {
			// 메인 화면 인사말
			pageId = "main/gre";
			Contents c = csi.findOneByPageId(pageId);
			
			System.out.println("왜 안되지 : " + c.toString());
			
			// 태그를 포함한 이미지 추출
			if(c.getContents().indexOf("data:image/jpeg;base64") != -1) {
				contents = c.getContents().substring(c.getContents().indexOf("<img ")).substring(0, c.getContents().substring(c.getContents().indexOf("<img ")).indexOf("\" />") + 4);
			}
			if(c.getContents().indexOf("data:image/png;base64") != -1) {
				contents = c.getContents().substring(c.getContents().indexOf("<img ")).substring(0, c.getContents().substring(c.getContents().indexOf("<img ")).indexOf("\" />") + 4);
			}
			if(c.getContents().indexOf("data:image/jpg;base64") != -1) {
				contents = c.getContents().substring(c.getContents().indexOf("<img ")).substring(0, c.getContents().substring(c.getContents().indexOf("<img ")).indexOf("\" />") + 4);
			}
			if(c.getContents().indexOf("data:image/gif;base64") != -1) {
				contents = c.getContents().substring(c.getContents().indexOf("<img ")).substring(0, c.getContents().substring(c.getContents().indexOf("<img ")).indexOf("\" />") + 4);
			}
			if(c.getContents().indexOf("https://") != -1) {
				contents = c.getContents().substring(c.getContents().indexOf("<img ")).substring(0, c.getContents().substring(c.getContents().indexOf("<img ")).indexOf("\" />") + 4);
			}
			if(c.getContents().indexOf("http://") != -1) {
				contents = c.getContents().substring(c.getContents().indexOf("<img ")).substring(0, c.getContents().substring(c.getContents().indexOf("<img ")).indexOf("\" />") + 4);
			}
			if(c.getContents().indexOf("ftp://") != -1) {
				contents = c.getContents().substring(c.getContents().indexOf("<img ")).substring(0, c.getContents().substring(c.getContents().indexOf("<img ")).indexOf("\" />") + 4);
			}
			if(c.getContents().indexOf("ssh://") != -1) {
				contents = c.getContents().substring(c.getContents().indexOf("<img ")).substring(0, c.getContents().substring(c.getContents().indexOf("<img ")).indexOf("\" />") + 4);
			}
			c.setText(c.getContents().replace(contents, ""));
			c.setContents(contents);
			
			System.out.println("왜 안되지 진짜 : " + c.toString());
			
			mv.addObject("gre", c);
			
			// 메인 화면 MOOC
			pageId = "main/mooc";
			contents = "";
			c = csi.findOneByPageId(pageId);
			
			//System.out.println("c : " + c.toString());
			
			// MOOC 데이터 가공
			if(c.getContents() != null) {
				if(c.getContents().indexOf("www.youtube.com/embed/") != -1) {
					// 유튜브 동영상 태그 추출
					contents = c.getContents().substring(c.getContents().indexOf("www.youtube.com/embed/") - 15, c.getContents().lastIndexOf("allowfullscreen") + 26);
				}
			}
			c.setText(c.getContents().replace(contents, ""));
			c.setContents(contents);
			mv.addObject("mooc", c);
			
			// 메인 화면 개발
			pageId = "main/dev";
			List<Contents> cs = csi.findByPageId(pageId);
			for(Contents c1: cs) {
				// 이미지 src 부분 추출
				contents = "";
				// 태그를 포함한 이미지 추출
				if(c1.getContents() != null) {
					if(c1.getContents().indexOf("data:image/jpeg;base64") != -1) {
						contents = c1.getContents().substring(c1.getContents().indexOf("data:image/jpeg;base64")).substring(0, c1.getContents().substring(c1.getContents().indexOf("data:image/jpeg;base64")).indexOf("\" alt=\""));
					}
					if(c1.getContents().indexOf("data:image/png;base64") != -1) {
						contents = c1.getContents().substring(c1.getContents().indexOf("data:image/png;base64")).substring(0, c1.getContents().substring(c1.getContents().indexOf("data:image/png;base64")).indexOf("\" alt=\""));
					}
					if(c1.getContents().indexOf("data:image/jpg;base64") != -1) {
						contents = c1.getContents().substring(c1.getContents().indexOf("data:image/jpg;base64")).substring(0, c1.getContents().substring(c1.getContents().indexOf("data:image/jpg;base64")).indexOf("\" alt=\""));
					}
					if(c1.getContents().indexOf("data:image/gif;base64") != -1) {
						contents = c1.getContents().substring(c1.getContents().indexOf("data:image/gif;base64")).substring(0, c1.getContents().substring(c1.getContents().indexOf("data:image/gif;base64")).indexOf("\" alt=\""));
					}
					if(c1.getContents().indexOf("https://") != -1) {
						contents = c1.getContents().substring(c1.getContents().indexOf("https://")).substring(0, c1.getContents().substring(c1.getContents().indexOf("https")).indexOf("\" alt=\""));
					}
					if(c1.getContents().indexOf("http://") != -1) {
						contents = c1.getContents().substring(c1.getContents().indexOf("http://")).substring(0, c1.getContents().substring(c1.getContents().indexOf("http")).indexOf("\" alt=\""));
					}
					if(c1.getContents().indexOf("ftp://") != -1) {
						contents = c1.getContents().substring(c1.getContents().indexOf("ftp://")).substring(0, c1.getContents().substring(c1.getContents().indexOf("ftp")).indexOf("\" alt=\""));
					}
					if(c1.getContents().indexOf("ssh://") != -1) {
						contents = c1.getContents().substring(c1.getContents().indexOf("ssh://")).substring(0, c1.getContents().substring(c1.getContents().indexOf("ssh")).indexOf("\" alt=\""));
					}
					c1.setContents(contents);
				}				
			}
			mv.addObject("dev", cs);
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
			csi.update(c);
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
			csi.update(c);
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
			csi.insert(c);
			mv.setViewName("redirect:/");
		} catch(ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}		
		return mv;
	}
	
	@RequestMapping(value="updateDevView.ma")
	public ModelAndView updateDevView(
			ModelAndView mv, @RequestParam(value="id") int id){
		try {
			Contents c = csi.findById(id);
			mv.addObject("c", c);
			mv.setViewName("main/dev/main_dev_00002");
		} catch(ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}		
		return mv;
	}
	
	@RequestMapping(value="updateDev.ma", method=RequestMethod.POST)
	public ModelAndView updateDev(Contents c, ModelAndView mv) {
		pageId = "main/dev";
		c.setPageId(pageId);
		c.setPostDate(postDate);
		try {
			csi.update(c);
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
			csi.delete(id);
			mv.setViewName("redirect:/");
		} catch(ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
}
