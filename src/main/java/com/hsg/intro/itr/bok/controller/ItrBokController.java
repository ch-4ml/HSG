package com.hsg.intro.itr.bok.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.hsg.intro.common.contents.model.domain.ContentsDomain;
import com.hsg.intro.common.contents.model.service.ContentsServiceImpl;
import com.hsg.intro.common.contents.model.vo.Contents;

@Controller
@SessionAttributes("loginUser")
public class ItrBokController {
	@Autowired
	private ContentsServiceImpl csi;
	
	private String pageId = "itr/bok";
	// ����/Ư�� �߰�
	@RequestMapping(value = "insert.ib", method = RequestMethod.POST) // DI ������ ����
	public ModelAndView insertIbkBok(Contents c, ModelAndView mv
			, @RequestParam(required=false) MultipartFile file
			, HttpServletRequest request){

		String root = request.getSession().getServletContext().getRealPath("resources");
		String filePath = root + "/uploadFiles/itrbok_upload_file";
		
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
			fileName = newfileName + ext;
			c.setContents(fileName);
			//���ϰ�θ� itrbok ��ü�� �־���
			filePath = filePath + "/" + fileName;

			// �ش� ������ ���� ����
			file.transferTo(new File(filePath));
			
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		c.setPageId(pageId);
		
		try {
			csi.insert(c);			
			mv.setViewName("redirect:view.ib");
			
		} catch (Exception e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");

		}
		return mv;
		
	}
	
	// ����/Ư�� ����
		@RequestMapping(value = "update.ib", method = RequestMethod.POST) // DI ������ ����
		public ModelAndView updateIbkBok(Contents c, ModelAndView mv
				, @RequestParam(required=false) MultipartFile file
				, HttpServletRequest request){
			
			try {
				// ���� DB�� ����Ǿ� �ִ� ���ϸ� �Ѱ� ���� ���ϸ��� ��ġ�� ���, �̹��� ��ũ�� ������ �ʰ� �״�� �Ѱ��־���� 
				// �ٸ� ���, �ش� �̹����� �����ϰ� ���ο� �̹����� ���ε� �ؾ���

					// ���ϸ� ���̸� ����
					String root = request.getSession().getServletContext().getRealPath("resources");
					
					String filePath = root + "/uploadFiles/itrbok_upload_file";
						
					String fileName = "";
					
					int randomNumber = (int)((Math.random()*10000)+1);
					
					SimpleDateFormat format = new SimpleDateFormat ( "yyyyMMddHHmmss");
					
					Date nowTime = new Date();
					
					String newfileName = format.format(nowTime) + String.valueOf(randomNumber);
					
					// Ȯ���� ���ϱ�
					
					int pos = file.getOriginalFilename().lastIndexOf(".");

					String ext = file.getOriginalFilename().substring(pos);
					
					fileName = newfileName + ext;
					//���ϰ�θ� itrbok ��ü�� �־���
					filePath = filePath + "/" + fileName;
					c.setContents(fileName);
					
					// �ش� ������ ���� ����
					file.transferTo(new File(filePath));
					
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			c.setPageId(pageId);
			c.setText("<URL - " + request.getParameter("url") + " - URLEND>" + c.getText());
			
			try {
				csi.update(c);
				
				List<Contents> cs = csi.findByPageId(pageId);
				mv.addObject("cs", cs);
				
				mv.setViewName("redirect:view.ib");
				
			} catch (Exception e) {
				mv.addObject("message",e.getMessage());
				mv.setViewName("redirect:/common/errorPage");

			}
			return mv;
			
		}
	
	// ����/Ư�� �߰� ������ �̵�
	@RequestMapping(value = "insertView.ib", method = RequestMethod.GET) // DI ������ ����
	public ModelAndView insertViewIbkBok(ModelAndView mv){
		
		mv.setViewName("itr/bok/itr_bok_00002");
		
		return mv;
		
	}
	
	// ����/Ư�� ���������� �̵�
	@RequestMapping(value = "updateView.ib", method = RequestMethod.GET) // DI ������ ����
	public ModelAndView updateViewIbkBok(
			ModelAndView mv, 
			@RequestParam(value="id") int id) {
		Contents c;
		ContentsDomain cd = new ContentsDomain();
		try {
			c = csi.findById(id);
			String url = "";
			String urlWithTag = "";
			if(c.getText().indexOf("<URL - ") != -1) {
				url = c.getText().substring(c.getText().indexOf("<URL - ") + 7, c.getText().indexOf(" - URLEND>"));
				urlWithTag = c.getText().substring(c.getText().indexOf("<URL - "), c.getText().indexOf(" - URLEND>") + 10);
			}
			cd.setId(c.getId());
			cd.setCategory(c.getCategory());
			cd.setTitle(c.getTitle());
			cd.setUrl(url);
			cd.setContents(c.getContents());
			cd.setText(c.getText().replace(urlWithTag, ""));
			cd.setPostDate(c.getPostDate());
			
			mv.addObject("cd", cd);
			mv.setViewName("itr/bok/itr_bok_00003");
			
		} catch (ContentsException e) {
			e.printStackTrace();
		}
		
		return mv;
		
	}
	
	// ����/Ư�� ���� ó��
	@RequestMapping(value = "delete.ib", method = RequestMethod.GET) // DI ������ ����
	public ModelAndView deleteIbkBok(
			ModelAndView mv,
			@RequestParam(value="id") int id){
		
		try {
			csi.delete(id);
			mv.setViewName("redirect:view.ib");
		} catch (ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("redirect:/common/errorPage");
		}
		return mv;
	}
	
	// ����/Ư�� ������ �̵�
	@RequestMapping(value = "view.ib", method = RequestMethod.GET) // DI ������ ����
	public ModelAndView viewIbkBok(ModelAndView mv){
		try {
			List<Contents> cs = csi.findByPageId(pageId);
			List<ContentsDomain> cds = new ArrayList<ContentsDomain>();
			for(Contents c: cs) {
				ContentsDomain cd = new ContentsDomain();
				String url = "";
				String urlWithTag = "";
				if(c.getContents().indexOf("<URL - ") != -1) {
					url = c.getContents().substring(c.getContents().indexOf("<URL - ") + 7, c.getContents().indexOf(" - URLEND>"));
					urlWithTag = c.getContents().substring(c.getContents().indexOf("<URL - "), c.getContents().indexOf(" - URLEND>") + 10);
				}
				cd.setId(c.getId());
				cd.setCategory(c.getCategory());
				cd.setTitle(c.getTitle());
				cd.setUrl(url);
				cd.setContents(c.getContents().replace(urlWithTag, ""));
				cd.setText(c.getText());
				cd.setPostDate(c.getPostDate());
				cds.add(cd);
			}
			mv.addObject("cds", cds);
			mv.setViewName("itr/bok/itr_bok_00001");
		} catch (ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("redirect:/common/errorPage");
		}
		return mv;
		
	}
	
	// �ܺ� url ����
	@RequestMapping(value = "redirect.ib", method = RequestMethod.GET) // DI ������ ����
	public ModelAndView viewIbkBokDetail(ModelAndView mv, @RequestParam(value="url") String url){
		if(url.indexOf("http://") != -1)
			mv.setViewName("redirect:" + url);
		else if(url.indexOf("https://") != -1)
			mv.setViewName("redirect:" + url);
		else	
			mv.setViewName("redirect:http://" + url);
		return mv;
	}

}
