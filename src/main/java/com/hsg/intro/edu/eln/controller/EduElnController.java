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
	private ContentsServiceImpl cs;
	
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
	public ModelAndView insertEduEln(Contents content, ModelAndView mv) {
		
		String image = content.getText().substring(
				content.getText().indexOf("embed/") + 6, // 첫 번째에 위치한
				content.getText().indexOf("embed/") + 17); // 유튜브 동영상 id 추출
		
		content.setPageId(pageId);
		content.setImage(image);
		content.setPostDate(postDate);
		
		System.out.println("in insert : " + content);
		
		try {
			cs.insert(content);
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
			List<Contents> contents = cs.findByPageId(pageId);
			List<ContentsDomain> cds = new ArrayList<ContentsDomain>();
			for(Contents content: contents) {
				ContentsDomain cd = new ContentsDomain();
				String comment = "";
				String video = "";
				String image = "";
				if(content.getText().indexOf("www.youtube.com/embed/") != -1) {
					video = content.getText().substring(content.getText().indexOf("www.youtube.com/embed/") - 15, content.getText().lastIndexOf("allowfullscreen") + 26);
				}
				if(content.getText().indexOf("data:image/jpeg;base64") != -1 ||
				   content.getText().indexOf("data:image/jpg;base64") != -1 ||
				   content.getText().indexOf("data:image/png;base64") != -1 || 
				   content.getText().indexOf("https://") != -1 ||
				   content.getText().indexOf("http://") != -1 || 
				   content.getText().indexOf("ftp://") != -1 || 
				   content.getText().indexOf("ssh://") != -1)
					image = content.getText().substring(content.getText().indexOf("<img ")).substring(0, content.getText().substring(content.getText().indexOf("<img ")).indexOf("\" />") + 4);
				comment = content.getText().replace(video, "").replace(image, "");

				cd.setId(content.getId());
				cd.setCategory(content.getCategory());
				cd.setTitle(content.getTitle());
				cd.setComment(comment);
				cd.setText(content.getText());
				cd.setImage(content.getImage());
				cd.setPostDate(content.getPostDate());
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
			Contents content = cs.findById(id);
			mv.addObject("content", content);
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
				Contents content = cs.findById(id);
				mv.addObject("content", content);
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
			Contents content, ModelAndView mv) {
		String image = "";
		String param = ""; // 검색 기록 남길 때
		if(content.getText().indexOf("embed/") != -1) {
		image = content.getText().substring(
				content.getText().indexOf("embed/") + 6, // 첫 번째에 위치한
				content.getText().indexOf("embed/") + 17); // 유튜브 동영상 id 추출
		} else image = "X";
		content.setPageId(pageId);
		content.setImage(image);
		content.setPostDate(postDate);
		
		try {
			cs.update(content);
			mv.setViewName("redirect:viewDetail.ee?id=" + content.getId());
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
			cs.delete(id);
			mv.setViewName("redirect:view.ee");
		} catch(ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	} 
}
