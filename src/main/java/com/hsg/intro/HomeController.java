package com.hsg.intro;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
	
	private String root = "/hsglobal03/tomcat/webapps/var/HSG/uploadFiles";
	
	
	@RequestMapping(value = "/")
	public ModelAndView home(ModelAndView mv, Locale locale) {
		logger.info("Welcome home! The client locale is {}.", locale);	
		String contents = "";
		try {
			// title area ������ ����
			pageId = "main/tit";
			Contents c = csi.findOneByPageId(pageId);
			mv.addObject("tit", c);
			
			// banner area ������ ����
			pageId = "main/ban";
			c = csi.findOneByPageId(pageId);
			mv.addObject("ban", c);
			
			// feature area(cat) ������ ����
			pageId = "main/cat";
			List<Contents> cs = csi.findByPageId(pageId);
			mv.addObject("cat", cs);
			
			
			// ���� ȭ�� �Ұ� ����
			pageId = "main/itr";
			c = csi.findOneByPageId(pageId);	
			
			// �Ұ� ���� ������ ����
			if(c.getContents() != null) {
				if(c.getContents().indexOf("www.youtube.com/embed/") != -1) {
					// ��Ʃ�� ������ �±� ����
					contents = c.getContents().substring(c.getContents().indexOf("www.youtube.com/embed/") - 15, c.getContents().lastIndexOf("allowfullscreen") + 26);
				}
			}
			c.setText(c.getContents().replace(contents, ""));
			c.setContents(contents);
			mv.addObject("itr", c);
			
			// ���� ȭ�� ����
			pageId = "main/dev";
			cs = csi.findByPageId(pageId);
			
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
	
	@RequestMapping(value="updateTitView.ma")
	public ModelAndView updateTitView(ModelAndView mv) {
		pageId = "main/tit";
		try {
			Contents c = csi.findOneByPageId(pageId);
			mv.addObject("c", c);
			mv.setViewName("main/tit/main_tit_00001");
		} catch(ContentsException e) {
			mv.addObject("message", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="updateTit.ma")
	public ModelAndView updateTit(Contents c, ModelAndView mv) {
		pageId = "main/tit";
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
	
	@RequestMapping(value="updateBanView.ma")
	public ModelAndView updateBanView(ModelAndView mv) {
		pageId = "main/ban";
		try {
			Contents c = csi.findOneByPageId(pageId);
			mv.addObject("c", c);
			mv.setViewName("main/ban/main_ban_00001");
		} catch(ContentsException e) {
			mv.addObject("message", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="updateBan.ma")
	public ModelAndView updateBan(Contents c, ModelAndView mv) {
		pageId = "main/ban";
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
	
	@RequestMapping(value="updateCatView.ma")
	public ModelAndView updateCatView(ModelAndView mv, @RequestParam(value="id") int id) {
		try {
			Contents c = csi.findById(id);
			mv.addObject("c", c);
			mv.setViewName("main/cat/main_cat_00001");
		} catch(ContentsException e) {
			mv.addObject("message", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="updateCat.ma")
	public ModelAndView updateCat(Contents c, ModelAndView mv) {
		pageId = "main/cat";
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
	
	@RequestMapping(value="updateItr.ma")
	public ModelAndView updateItr(Contents c, ModelAndView mv) {
		pageId = "main/itr";
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
	public ModelAndView insertDev(Contents c, ModelAndView mv, 
			@RequestParam(required=false) MultipartFile file,
			HttpServletRequest request) {
		pageId = "main/dev";
		
		// ################### ���� ���ε� ###################
		String filePath = "/maindev_upload_file";
		String fileName = "";
		try {
			// ���ϸ� ���̸� ����
			int randomNumber = (int)((Math.random()*10000)+1);
			SimpleDateFormat format = new SimpleDateFormat ( "yyyyMMddHHmmss");
			Date nowTime = new Date();
			String newfileName = format.format(nowTime) + String.valueOf(randomNumber);
			
			// Ȯ���� ���ϱ�
			int pos = file.getOriginalFilename().lastIndexOf(".");
			String ext = file.getOriginalFilename().substring(pos);
			// /xxxxxx_upload_files/���ϸ�.ext ���·� ��ü�� ���� 
			fileName = filePath + "/" + newfileName + ext;
			c.setContents(fileName);
			
			// ���� ������ ����
			File uploadPath = new File(root + filePath);
			if(!uploadPath.exists()) {
				uploadPath.mkdirs();
			}
		
			// �ش� ������ ���� ����
			file.transferTo(new File(root + fileName));
			
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// ################### ���� ���ε� ###################
		
		c.setPageId(pageId);
		c.setPostDate(postDate);
		
		System.out.println("insertdev c : " + c.toString());
		
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
	public ModelAndView updateDev(Contents c, ModelAndView mv, 
			@RequestParam(required=false) MultipartFile file,
			HttpServletRequest request) throws ContentsException {
		pageId = "main/dev";
		
		try {
			System.out.println("#################### update.ib file.isEmpty() : " + file.isEmpty() + "####################");
			System.out.println("#################### update.ib content : " + c + "####################");
			
			if(!file.isEmpty()) { // ������ null �� ���
				String filePath = "/maindev_upload_file";
				String fileName = "";
				// ##################### ���� ���� ó�� #######################
				String deleteFileName = csi.findById(c.getId()).getContents();

				// ���ϸ� ���̸� ����
				int randomNumber = (int)((Math.random()*10000)+1);
				SimpleDateFormat format = new SimpleDateFormat ("yyyyMMddHHmmss");
				Date nowTime = new Date();
				String newfileName = format.format(nowTime) + String.valueOf(randomNumber);	
				
				// Ȯ���� ���ϱ�
				int pos = file.getOriginalFilename().lastIndexOf(".");
				String ext = file.getOriginalFilename().substring(pos);
				// /xxxxxx_upload_files/���ϸ�.ext ���·� ��ü�� ���� 
				fileName = filePath + "/" + newfileName + ext;
				c.setContents(fileName);
				
				// ���� ������ ����
				File uploadPath = new File(root + filePath);
				if(!uploadPath.exists()) {
					uploadPath.mkdirs();
				}
			
				// �ش� ������ ���� ����
				file.transferTo(new File(root + fileName));
				
				// ##################### ���� ���� ó�� #######################
				String deleteFilePath = root + deleteFileName;
				
				// ##################### ���� ���� ó�� #######################
				File deleteFile = new File(deleteFilePath); // ���� URL
				
				System.out.println("#################### update.ee deleteFilePath : " + deleteFilePath + "####################");
				
				if(deleteFile.exists()) {
					if(deleteFile.delete()) {
						System.out.println("���� ���� �Ϸ�");
					} else {
						System.out.println("���� ���� ����");
					}
				} else {
					System.out.println("������ �������� �ʽ��ϴ�.");
				}
			}
			
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
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
			@RequestParam(value="id") int id,
			HttpServletRequest request) {	
		try {
			String deleteFileName = csi.findById(id).getContents();
			// ##################### ���� ���� ó�� #######################
			String deleteFilePath = root + deleteFileName;
			
			// ##################### ���� ���� ó�� #######################
			File deleteFile = new File(deleteFilePath); // ���� URL
			
			System.out.println("#################### update.ib deleteFilePath : " + deleteFilePath + "####################");
			
			if(deleteFile.exists()) {
				if(deleteFile.delete()) {
					System.out.println("���� ���� �Ϸ�");
				} else {
					System.out.println("���� ���� ����");
				}
			} else {
				System.out.println("������ �������� �ʽ��ϴ�.");
			}
			
			csi.delete(id);
			mv.setViewName("redirect:/");
		} catch(ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
}
