package com.hsg.intro.emp.lec.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.activation.MimetypesFileTypeMap;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hsg.intro.Exception.ContentsException;
import com.hsg.intro.common.contents.model.service.ContentsServiceImpl;
import com.hsg.intro.common.contents.model.vo.Contents;

@Controller
@SessionAttributes("loginUser")
public class EmpLecController {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private ContentsServiceImpl csi;
	
	private String pageId = "emp/lec";

	SimpleDateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd HH:mm", Locale.KOREA);
	Date currentDate = new Date();
	String postDate = formatter.format (currentDate);
	
	// 추가 페이지
	@RequestMapping(value="insertView.el")
	public ModelAndView insertView(ModelAndView mv) {
		mv.setViewName("emp/lec/emp_lec_00002");
		return mv;
	}
	
	// 추가
	@RequestMapping(value="insert.el")
	public ModelAndView insert(Contents c, ModelAndView mv) {
		c.setPageId(pageId);
		c.setPostDate(postDate);
		
		try {
			csi.insert(c);
			mv.setViewName("redirect:view.el");
		} catch(ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="view.el")
	public ModelAndView view(ModelAndView mv) {
		try {
			List<Contents> cs = csi.findByPageId(pageId);
			for(Contents c: cs) {
				String contents = "";
				String imageWithTag = "";
				if(c.getText().indexOf("data:image/jpeg;base64") != -1) {
					contents = c.getText().substring(c.getText().indexOf("data:image/jpeg;base64")).substring(0, c.getText().substring(c.getText().indexOf("data:image/jpeg;base64")).indexOf("\" alt=\""));
					imageWithTag = c.getText().substring(c.getText().indexOf("<img ")).substring(0, c.getText().substring(c.getText().indexOf("<img ")).indexOf("\" />") + 4);
				}
				if(c.getText().indexOf("data:image/png;base64") != -1) {
					contents = c.getText().substring(c.getText().indexOf("data:image/png;base64")).substring(0, c.getText().substring(c.getText().indexOf("data:image/png;base64")).indexOf("\" alt=\""));
					imageWithTag = c.getText().substring(c.getText().indexOf("<img ")).substring(0, c.getText().substring(c.getText().indexOf("<img ")).indexOf("\" />") + 4);
				}
				if(c.getText().indexOf("data:image/jpg;base64") != -1) {
					contents = c.getText().substring(c.getText().indexOf("data:image/jpg;base64")).substring(0, c.getText().substring(c.getText().indexOf("data:image/jpg;base64")).indexOf("\" alt=\""));
					imageWithTag = c.getText().substring(c.getText().indexOf("<img ")).substring(0, c.getText().substring(c.getText().indexOf("<img ")).indexOf("\" />") + 4);
				}
				if(c.getText().indexOf("https://") != -1) {
					contents = c.getText().substring(c.getText().indexOf("https://")).substring(0, c.getText().substring(c.getText().indexOf("https")).indexOf("\" alt=\""));
					imageWithTag = c.getText().substring(c.getText().indexOf("<img ")).substring(0, c.getText().substring(c.getText().indexOf("<img ")).indexOf("\" />") + 4);
				}
				if(c.getText().indexOf("http://") != -1) {
					contents = c.getText().substring(c.getText().indexOf("http://")).substring(0, c.getText().substring(c.getText().indexOf("http")).indexOf("\" alt=\""));
					imageWithTag = c.getText().substring(c.getText().indexOf("<img ")).substring(0, c.getText().substring(c.getText().indexOf("<img ")).indexOf("\" />") + 4);
				}
				if(c.getText().indexOf("ftp://") != -1) {
					contents = c.getText().substring(c.getText().indexOf("ftp://")).substring(0, c.getText().substring(c.getText().indexOf("ftp")).indexOf("\" alt=\""));
					imageWithTag = c.getText().substring(c.getText().indexOf("<img ")).substring(0, c.getText().substring(c.getText().indexOf("<img ")).indexOf("\" />") + 4);
				}
				if(c.getText().indexOf("ssh://") != -1) {
					contents = c.getText().substring(c.getText().indexOf("ssh://")).substring(0, c.getText().substring(c.getText().indexOf("ssh")).indexOf("\" alt=\""));
					imageWithTag = c.getText().substring(c.getText().indexOf("<img ")).substring(0, c.getText().substring(c.getText().indexOf("<img ")).indexOf("\" />") + 4);
				}
				// base64 형식의 이미지 src 부분 추출(태그 포함)
				
				c.setContents(contents);
				c.setText(c.getText().replace(imageWithTag, ""));
			}
			mv.addObject("cs", cs);
			mv.setViewName("emp/lec/emp_lec_00001");
		} catch(ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}

		return mv;
	}
	
	@RequestMapping(value="viewDetail.el")
	public ModelAndView viewDetail(ModelAndView mv,
			@RequestParam(value="id") int id) {
		try {
			Contents c = csi.findById(id);
			mv.addObject("c", c);
			mv.setViewName("emp/lec/emp_lec_00003");
		} catch(ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 업데이트 폼
	@RequestMapping(value="updateView.el")
	public ModelAndView ubdateEmpLec(ModelAndView mv, @RequestParam(value="id") int id) {
		Contents c;
		try {
			c = csi.findById(id);
			mv.addObject("c", c);
			mv.setViewName("emp/lec/emp_lec_00004");
		} catch (ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("redirect:/common/errorPage");
		}
		return mv;
	}
	
	// 업데이트(검색한 페이지로 돌아가게 하려면 파라미터 추가)
	@RequestMapping(value="update.el", method=RequestMethod.POST) // DI 의존성 주입
	public ModelAndView updateEduEln(
			Contents c, ModelAndView mv, 
			@RequestParam(value="id") int id) {

		String param = ""; // 검색 기록 남길 때
		
		c.setId(id);
		c.setPageId(pageId);
		c.setPostDate(postDate);

		try {
			csi.update(c);
			mv.setViewName("redirect:viewDetail.el?id=" + id);
		} catch (ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}		
		return mv;
	}
	
	// 삭제 (검색한 페이지로 돌아가게 하려면 파라미터 추가)
	@RequestMapping(value="delete.el")
	public ModelAndView deleteEduEln(ModelAndView mv,
		@RequestParam(value="id") int id) {
		String param = ""; // 검색 기록 남길 때
		try {
			csi.delete(id);
			mv.setViewName("redirect:view.el");
		} catch(ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
		
	@RequestMapping(value="sendForm.el")
	public String sendForm() {
		return "emp/lec/emp_lec_00005";
	}
	
	
	@RequestMapping(value="send.el")
	public String send(MultipartHttpServletRequest request) {
		
		String from = request.getParameter("email");
		
		// 메일 받을 주소 지정
		List<String> toAddress = new ArrayList<String>();
		// 대표님과 관리자 이메일로 변경, 추가 가능
		toAddress.add("ark9659@gmail.com");
		toAddress.add("cksgud1350@naver.com");
		String subject = "[HS글로벌 강사 지원] " + request.getParameter("name");
		String contents = "지원자 이름 : " + request.getParameter("name") + "\r\n" + 
					  "분야 : " + request.getParameter("field") + "\r\n" + 
					  "연락처 : " + request.getParameter("phone") + "\r\n" + 
					  "이메일 : " + request.getParameter("email") + "\r\n" + 
					  "경력사항 : " + request.getParameter("career");
		// 파일
		List<MultipartFile> fileList = request.getFiles("file");		
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			
			messageHelper.setFrom(from);
			for(String to: toAddress) {
				messageHelper.addTo(to);
			}
			
			for (MultipartFile f: fileList) {
				if(f.getOriginalFilename() != "") {
					File file = new File(f.getOriginalFilename());
					String mimeType = new MimetypesFileTypeMap().getContentType(file);
					
					messageHelper.addAttachment(f.getOriginalFilename(), new ByteArrayDataSource(f.getBytes(), mimeType)); // string, datasource
	//				messageHelper.addAttachment(arg0, arg1); // string, dataSource				
	//				messageHelper.addAttachment(attachmentFilename, file); // string, file
	//				messageHelper.addAttachment(attachmentFilename, inputStreamSource); // string, inputstreamsource
	//				messageHelper.addAttachment(attachmentFilename, inputStreamSource, contentType); // string, inputstreamsource, string
				}
			}
			
			messageHelper.setSubject(subject);
			messageHelper.setText(contents);
			
			mailSender.send(message);
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return "emp/lec/emp_lec_00006";
	}
}
