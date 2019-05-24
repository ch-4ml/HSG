package com.hsg.intro.itr.bok.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
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

import com.hsg.intro.Exception.ItrBokException;
import com.hsg.intro.itr.bok.model.service.ItrBokServiceImpl;
import com.hsg.intro.itr.bok.model.vo.ItrBok;

@Controller
@SessionAttributes("loginUser")
public class ItrBokController {
	@Autowired
	private ItrBokServiceImpl ibs;
	
	// ����/Ư�� �߰�
	@RequestMapping(value = "insert.ib", method = RequestMethod.POST) // DI ������ ����
	public ModelAndView insertIbkBok(ItrBok b, ModelAndView mv
			, @RequestParam(required=false) MultipartFile bfile
			, HttpServletRequest request){
		
		System.out.println("controller bfile : " + bfile);

		String root = request.getSession().getServletContext().getRealPath("resources");
		String filePath = root + "\\uploadFiles\\itrbok_upload_file";
		try {
			
			// ���ϸ� ���̸� ����
			
			int randomNumber = (int)((Math.random()*10000)+1);
			
			SimpleDateFormat format = new SimpleDateFormat ( "yyyyMMddHHmmss");
			
			Date nowTime = new Date();
			
			String newfileName = format.format(nowTime) + String.valueOf(randomNumber);
			
			// Ȯ���� ���ϱ�
			
			int pos = bfile.getOriginalFilename().lastIndexOf(".");

			String ext = bfile.getOriginalFilename().substring(pos);
			
			//���ϰ�θ� itrbok ��ü�� �־���
			
			filePath = filePath + "\\" + newfileName + ext;
			
			System.out.println("controller filePath : " + filePath);
			
			b.setBimg(filePath);
			
			// �ش� ������ ���� ����
			bfile.transferTo(new File(filePath));
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("controller bok : " + b);
		
		try {
			ibs.insertIbkBok(b);
			
			List<ItrBok> itrBokList = ibs.selectitrBokList();
			mv.addObject("itrBokList", itrBokList);
			
			mv.setViewName("redirect:view.ib");
			
		} catch (Exception e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");

		}
		return mv;
		
	}
	
	// ����/Ư�� ����
		@RequestMapping(value = "update.ib", method = RequestMethod.POST) // DI ������ ����
		public ModelAndView updateIbkBok(ItrBok b, ModelAndView mv
				, @RequestParam(required=false) MultipartFile bfile
				, HttpServletRequest request){
			
			try {
				if(!bfile.getOriginalFilename().equals("")) {
					// ���ϸ� ���̸� ����
					
					String root = request.getSession().getServletContext().getRealPath("resources");
					
					String filePath = root + "\\uploadFiles\\itrbok_upload_file";
						
					int randomNumber = (int)((Math.random()*10000)+1);
					
					SimpleDateFormat format = new SimpleDateFormat ( "yyyyMMddHHmmss");
					
					Date nowTime = new Date();
					
					String newfileName = format.format(nowTime) + String.valueOf(randomNumber);
					
					// Ȯ���� ���ϱ�
					
					int pos = bfile.getOriginalFilename().lastIndexOf(".");

					String ext = bfile.getOriginalFilename().substring(pos);
					
					//���ϰ�θ� itrbok ��ü�� �־���
					
					filePath = filePath + "\\" + newfileName + ext;
					
					System.out.println("controller filePath : " + filePath);
					
					b.setBimg(filePath);
					
					// �ش� ������ ���� ����
					bfile.transferTo(new File(filePath));
				} else {
					b.setBimg(null);
				}
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			System.out.println("controller bok : " + b);
			
			try {
				ibs.updateIbkBok(b);
				
				List<ItrBok> itrBokList = ibs.selectitrBokList();
				mv.addObject("itrBokList", itrBokList);
				
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
	public ModelAndView updateViewIbkBok(ModelAndView mv, String bid){
		
		ItrBok itrBok;
		try {
			itrBok = ibs.selectItrBok(bid);
			
			mv.addObject("itrBok", itrBok);
			mv.setViewName("itr/bok/itr_bok_00003");
			
		} catch (ItrBokException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return mv;
		
	}
	
	// ����/Ư�� ���� ó��
	@RequestMapping(value = "delete.ib", method = RequestMethod.GET) // DI ������ ����
	public ModelAndView deleteIbkBok(ModelAndView mv, String bid){
		
		try {
			ibs.deleteIbkBok(bid);
			
			List<ItrBok> itrBokList = ibs.selectitrBokList();
			mv.addObject("itrBokList", itrBokList);
			
			mv.setViewName("redirect:view.ib");
			
		} catch (ItrBokException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("redirect:/common/errorPage");
		}
		return mv;
	}
	//
	// ����/Ư�� ������ �̵�
	@RequestMapping(value = "view.ib", method = RequestMethod.GET) // DI ������ ����
	public ModelAndView viewIbkBok(ModelAndView mv){
		
		try {
			List<ItrBok> itrBokList = ibs.selectitrBokList();
			mv.addObject("itrBokList", itrBokList);
			
			mv.setViewName("itr/bok/itr_bok_00001");
			
		} catch (ItrBokException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("redirect:/common/errorPage");
		}
		
		return mv;
		
	}

}
