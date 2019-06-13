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
	// �ַ� ����� �������� �̸��Դϴ�. contents ���̺��� page_id �÷��� �� ���Դϴ�.
	// page_id�� ������ ������ �ѷ��ִ� ���� ���������� ���� ���̺��� ����� �� �ֵ��� ���� �÷��Դϴ�.
	private String pageId = "itr/his";

	// CEO �λ縻 ����
	@RequestMapping(value = "view.ih", method = RequestMethod.GET) // DI ������ ����
	public ModelAndView viewContents(ModelAndView mv) {
		try {
			Contents c = csi.findOneByPageId(pageId);// select�� ������ ������
			mv.addObject("c", c);
			mv.setViewName("itr/his/itr_his_00001");
		} catch (ContentsException e) {
			mv.addObject("message", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}

	// �Ϲ���Ȳ �� ���� ������Ʈ
	@RequestMapping(value = "update.ih", method = RequestMethod.POST) // DI ������ ����
	public ModelAndView updateContents(Contents c, ModelAndView mv,
			@RequestParam(required=false) MultipartFile file, 
			HttpServletRequest request) throws ContentsException {
		try {
			System.out.println("#################### update.ee file.isEmpty() : " + file.isEmpty() + "####################");
			System.out.println("#################### update.ee content : " + c + "####################");
			
			if(!file.isEmpty()) {
				String root = request.getSession().getServletContext().getRealPath("resources");
				String filePath = root + "/uploadFiles/itrhis_upload_file";
				String fileName = "";
				String updatefilePath = "";
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
				fileName = newfileName + ext;
				c.setContents(fileName);
				
				// ���� ������ ����
				File uploadPath = new File(filePath);
				if(!uploadPath.exists()) {
					uploadPath.mkdirs();
				}
				
				//���ϰ�θ� edueln ��ü�� �־���
				System.out.println("#################### update.ee content : " + c + "####################");
				updatefilePath = filePath + "/" + fileName;
				System.out.println("#################### update.ee updatefilePath : " + updatefilePath + "####################");
				
				// �ش� ������ ���� ����
				file.transferTo(new File(updatefilePath));
				
				// ##################### ���� ���� ó�� #######################
				String deleteFilePath = filePath + "/" + deleteFileName;
				
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
