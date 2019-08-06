package com.hsg.intro.emp.lec.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.activation.MimetypesFileTypeMap;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletRequest;

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

	private SimpleDateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd HH:mm", Locale.KOREA);
	private Date currentDate = new Date();
	private String postDate = formatter.format (currentDate);
	
	private String root = "/hsglobal03/tomcat/webapps/var/HSG/uploadFiles";
	private String filePath = "/emplec_upload_files";
	
	// �߰� ������
	@RequestMapping(value="insertView.el")
	public ModelAndView insertView(ModelAndView mv) {
		mv.setViewName("emp/lec/emp_lec_00002");
		return mv;
	}
	
	// �߰�
	@RequestMapping(value="insert.el")
	public ModelAndView insert(Contents c, ModelAndView mv, 
			@RequestParam(required=false) MultipartFile file, 
			HttpServletRequest request) {
		
		// ################### ���� ���ε� ###################
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
	
	// ������Ʈ ��
	@RequestMapping(value="updateView.el")
	public ModelAndView ubdateEmpLec(ModelAndView mv, @RequestParam(value="id") int id) {
		try {
			Contents c = csi.findById(id);
			mv.addObject("c", c);
			mv.setViewName("emp/lec/emp_lec_00004");
		} catch (ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("redirect:/common/errorPage");
		}
		return mv;
	}
	
	// ������Ʈ(�˻��� �������� ���ư��� �Ϸ��� �Ķ���� �߰�)
	@RequestMapping(value="update.el", method=RequestMethod.POST) // DI ������ ����
	public ModelAndView updateEduEln(Contents c, ModelAndView mv, 
			@RequestParam(required=false) MultipartFile file, 
			HttpServletRequest request) throws ContentsException {

		try {
			System.out.println("#################### update.el file.isEmpty() : " + file.isEmpty() + "####################");
			System.out.println("#################### update.el content : " + c + "####################");
			
			if(!file.isEmpty()) { // ������ null �� ���
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
		
		String param = ""; // �˻� ��� ���� ��
		c.setPageId(pageId);
		c.setPostDate(postDate);

		try {
			csi.update(c);
			mv.setViewName("redirect:viewDetail.el?id=" + c.getId());
		} catch (ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");
		}		
		return mv;
	}
	
	// ���� (�˻��� �������� ���ư��� �Ϸ��� �Ķ���� �߰�)
	@RequestMapping(value="delete.el")
	public ModelAndView deleteEduEln(ModelAndView mv,
		@RequestParam(value="id") int id,
		HttpServletRequest request) {
		String param = ""; // �˻� ��� ���� ��
		try {
			String deleteFileName = csi.findById(id).getContents();
			// ##################### ���� ���� ó�� #######################
			String deleteFilePath = root + deleteFileName;
			
			// ##################### ���� ���� ó�� #######################
			File deleteFile = new File(deleteFilePath); // ���� URL
			
			System.out.println("#################### update.el deleteFilePath : " + deleteFilePath + "####################");
			
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
		
		// ���� ���� �ּ� ����
		List<String> toAddress = new ArrayList<String>();
		// ��ǥ�԰� ������ �̸��Ϸ� ����, �߰� ����
		toAddress.add("ark9659@gmail.com");
		toAddress.add("junho.seo@hsgbiz.com");
		String subject = "[HS�۷ι� ���� ����] " + request.getParameter("name");
		String contents = "������ �̸� : " + request.getParameter("name") + "\r\n" + 
					  "�о� : " + request.getParameter("field") + "\r\n" + 
					  "����ó : " + request.getParameter("phone") + "\r\n" + 
					  "�̸��� : " + request.getParameter("email") + "\r\n" + 
					  "��»��� : " + request.getParameter("career");
		// ����
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
