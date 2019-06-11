package com.hsg.intro.edu.eln.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hsg.intro.Exception.ContentsException;
import com.hsg.intro.common.contents.model.domain.ContentsDomain;
import com.hsg.intro.common.contents.model.service.ContentsServiceImpl;
import com.hsg.intro.common.contents.model.vo.Contents;

@Controller
@SessionAttributes("loginUser")
public class EduElnController {
	
	@Autowired
	private ContentsServiceImpl csi;
	
	private String pageId = "edu/eln";
	
	SimpleDateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd HH:mm", Locale.KOREA);
	Date currentDate = new Date();
	String postDate = formatter.format (currentDate);
	
	// 추가 페이지
	@RequestMapping(value="insertView.ee")
	public ModelAndView insertView(ModelAndView mv) {
		mv.setViewName("edu/eln/edu_eln_00002");
		return mv;
	}
	
	// 추가
	@RequestMapping(value="insert.ee", method=RequestMethod.POST)
	public ModelAndView insertEduEln(Contents c, ModelAndView mv) {
		
		String text = c.getContents().substring(
				c.getContents().indexOf("embed/") + 6, // 첫 번째에 위치한
				c.getContents().indexOf("embed/") + 17); // 유튜브 동영상 id 추출
		
		c.setPageId(pageId);
		c.setText(text);
		c.setPostDate(postDate);
		
		System.out.println("in insert : " + c);
		
		try {
			csi.insert(c);
			mv.setViewName("redirect:view.ee");
		} catch (Exception e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 메인 페이지
	@RequestMapping(value="view.ee")
	public ModelAndView viewEduEln(ModelAndView mv) {		
		try {
			List<Contents> cs = csi.findByPageId(pageId);
			List<ContentsDomain> cds = new ArrayList<ContentsDomain>();
			for(Contents c: cs) {
				ContentsDomain cd = new ContentsDomain();
//				String comment = "";
//				String video = "";
//				String contents = "";
//				if(c.getContents().indexOf("www.youtube.com/embed/") != -1) {
//					video = c.getContents().substring(c.getContents().indexOf("www.youtube.com/embed/") - 15, c.getContents().lastIndexOf("allowfullscreen") + 26);
//				}
//				if(c.getContents().indexOf("data:text/jpeg;base64") != -1 ||
//				   c.getContents().indexOf("data:text/jpg;base64") != -1 ||
//				   c.getContents().indexOf("data:text/png;base64") != -1 || 
//				   c.getContents().indexOf("https://") != -1 ||
//				   c.getContents().indexOf("http://") != -1 || 
//				   c.getContents().indexOf("ftp://") != -1 || 
//				   c.getContents().indexOf("ssh://") != -1)
//					contents = c.getContents().substring(c.getContents().indexOf("<img ")).substring(0, c.getContents().substring(c.getContents().indexOf("<img ")).indexOf("\" />") + 4);
//				comment = c.getContents().replace(video, "").replace(contents, "");

				cd.setId(c.getId());
				cd.setCategory(c.getCategory());
				cd.setTitle(c.getTitle());
//				cd.setComment(comment);
				cd.setContents(c.getContents());
				cd.setText(c.getText());
				cd.setPostDate(c.getPostDate());
				cds.add(cd);
			}
			mv.addObject("cds", cds);
			mv.setViewName("edu/eln/edu_eln_00001");
		} catch(ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 상세 보기
	@RequestMapping(value="viewDetail.ee")
	public ModelAndView viewEduElnDetail(ModelAndView mv,
			@RequestParam(value="id") int id) {
		try {
			Contents c = csi.findById(id);
			mv.addObject("c", c);
			mv.setViewName("edu/eln/edu_eln_00003");
		} catch(ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 업데이트 폼
		@RequestMapping(value="updateView.ee")
		public ModelAndView ubdateEmpLec(ModelAndView mv, @RequestParam(value="id") int id) {
			try {
				Contents c = csi.findById(id);
				mv.addObject("c", c);
				mv.setViewName("edu/eln/edu_eln_00004");
			} catch (ContentsException e) {
				mv.addObject("message",e.getMessage());
				mv.setViewName("redirect:/common/errorPage");
			}
			return mv;
		}
	
	// 업데이트(검색한 페이지로 돌아가게 하려면 파라미터 추가)
	@RequestMapping(value="update.ee", method=RequestMethod.POST) // DI 의존성 주입
	public ModelAndView updateEduEln(
			Contents c, ModelAndView mv) {
		String contents = "";
		String param = ""; // 검색 기록 남길 때
		if(c.getContents().indexOf("embed/") != -1) {
		contents = c.getContents().substring(
				c.getContents().indexOf("embed/") + 6, // 첫 번째에 위치한
				c.getContents().indexOf("embed/") + 17); // 유튜브 동영상 id 추출
		} else contents = "X";
		c.setPageId(pageId);
		c.setText(contents);
		c.setPostDate(postDate);
		
		try {
			csi.update(c);
			mv.setViewName("redirect:viewDetail.ee?id=" + c.getId());
		} catch (ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}		
		return mv;
	}
	
	// 삭제 (검색한 페이지로 돌아가게 하려면 파라미터 추가)
	@RequestMapping(value="delete.ee")
	public ModelAndView deleteEduEln(ModelAndView mv,
		@RequestParam(value="id") int id) {
		String param = ""; // 검색 기록 남길 때
		try {
			csi.delete(id);
			mv.setViewName("redirect:view.ee");
		} catch(ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	} 
}
