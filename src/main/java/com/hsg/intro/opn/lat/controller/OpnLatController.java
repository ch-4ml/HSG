package com.hsg.intro.opn.lat.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hsg.intro.Exception.ContentsException;
import com.hsg.intro.common.PageInfo;
import com.hsg.intro.common.contents.model.service.ContentsServiceImpl;
import com.hsg.intro.common.contents.model.vo.Contents;
import com.hsg.intro.common.files.model.service.FilesServiceImpl;
import com.hsg.intro.common.files.model.vo.Files;

@Controller
@SessionAttributes("loginUser")
public class OpnLatController {
	@Autowired
	private ContentsServiceImpl csi;
	@Autowired
	private FilesServiceImpl fsi;

	private String pageId = "opn/lat";
	private String dirName = pageId.replace("/", "") + "_upload_files";

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.KOREA);
	Date currentDate = new Date();
	String postDate = formatter.format(currentDate);

	private String root = "/hsglobal03/tomcat/webapps/var/HSG/uploadFiles";
	String filePath = "/" + dirName;
	
	// 페이지 이동
	@RequestMapping(value = "view.ol")
	public ModelAndView view(ModelAndView mv) {
		
		mv.setViewName("opn/lat/opn_lat_00001");
		return mv;		
	}
	
	// 데이터 ajax call
	@RequestMapping(value = "get.ol", produces = "application/json;charset=utf8") // DI 의존성 주입
	public ModelAndView get(ModelAndView mv, @RequestParam(defaultValue = "1") Integer currentPage) {
		/**
		 * param currentPage : 현재페이지 (defaultValue : 1)
		 * 
		 */
		Contents c = new Contents();

		int limit;
		int maxPage;
		int startPage;
		int endPage;

		limit = 5; // 페이지 당 row 수 / 블록 당 페이지 수

		System.out.println("currentPage : " + currentPage);

		int listCount = 0;

		try {
			listCount = csi.getListCount(pageId); // 총 row 수
		} catch (ContentsException e) {
			e.printStackTrace();
		}

		maxPage = (int) ((double) listCount / limit + 0.9); // 총 페이지 수
		startPage = ((int) ((double) currentPage / limit + 0.9) - 1) * limit + 1; // 해당 블록의 시작 페이지
		endPage = startPage + limit - 1; // 해당 블록의 끝 페이지
		if (maxPage < endPage) {
			endPage = maxPage;
		}

		PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage, currentPage);

		c.setPageId(pageId);
		c.setPostDate(postDate);

		mv.addObject("pi", pi);

		try {
			List<Contents> cs = csi.findByPageId(pageId, pi);
			mv.addObject("cs", cs);
			mv.setViewName("jsonView");
		} catch (ContentsException e) {
			e.printStackTrace();
		}
		
		return mv;
	}
	
	// contents 검색
	@RequestMapping(value = "find.ol", method = RequestMethod.GET) // DI 의존성 주입
	public ModelAndView find(
			ModelAndView mv,
			@RequestParam(defaultValue="1") String con,
			@RequestParam(defaultValue="") String value,
			@RequestParam(defaultValue = "1") Integer currentPage){
			
			HashMap<String, String> hmap = new HashMap<String, String>();
			
			hmap.put("pageId", pageId);
			hmap.put("con", con); // 조건 : 1 : 제목 2 : 내용 3 : 제목+내용
			hmap.put("value", value); // 검색어
			
		try {
			int limit;
			int maxPage;
			int startPage;
			int endPage;
			int listCount = 0;

			limit = 5; //

			System.out.println("currentPage : " + currentPage);
			
			listCount = csi.getListCount(hmap); // 총페이지수

			maxPage = (int) ((double) listCount / limit + 0.9);
			startPage = ((int) ((double) currentPage / limit + 0.9) - 1) * limit + 1;
			endPage = startPage + limit - 1;
			if (maxPage < endPage) {
				endPage = maxPage;
			}

			PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage, currentPage);
			

			try {
				List<Contents> cList = csi.findByPageId(hmap, pi);
				mv.addObject("cList", cList);
			} catch(ContentsException e) {
				e.printStackTrace();
			}
			
			mv.addObject("pi", pi);

			
			mv.setViewName("redirect:view.ol");
		} catch (ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("redirect:/common/errorPage");
		}
		return mv;
	}

	// contents 상세
	@RequestMapping(value = "detail.ol")
	public ModelAndView detail(ModelAndView mv, @RequestParam(value="id") int id) {
		try {
			Contents c = csi.findById(id);
			List<Files> fs = fsi.findByContentsId(id);
			mv.addObject("c", c);
			mv.addObject("fs", fs);
			mv.setViewName("opn/lat/opn_lat_00003");
		} catch (Exception e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");	  
		}
		return mv;
	}
	
	// contents 추가 폼
	@RequestMapping(value = "insertView.ol", method = RequestMethod.GET) // DI 의존성 주입
	public ModelAndView insertView(ModelAndView mv) {
		
		mv.setViewName("opn/lat/opn_lat_00002");
		return mv;
	}
	
	// contents 추가
	@RequestMapping(value = "insert.ol", method = RequestMethod.POST) // DI 의존성 주입 
	public ModelAndView insert(Contents c, ModelAndView mv,
			@RequestParam(required=false) MultipartFile file, HttpServletRequest request) {
		// MultipartHttpServletRequest request
		
		/*
		// 멀티파일 업로드 구현 예정
		List<MultipartFile> files = request.getFiles("file");	
		if(!files.isEmpty()) { 
			try {
				fsi.insertFile(files, csi.getListCount() + 1, dirName);
			} catch (Exception e) {
				mv.addObject("message",e.getMessage());
				mv.setViewName("common/errorPage");	  
			}
		}		  
		*/
		// ################### 파일 업로드 ################### 
		if(!file.isEmpty()) {  
			String fileName = "";
			String originFileName = "";
			
			try { // 파일명 새이름 설정 
				
				int randomNumber = (int)((Math.random()*10000)+1);
				SimpleDateFormat format = new SimpleDateFormat ( "yyyyMMddHHmmss"); 
				Date nowTime = new Date(); String newfileName = format.format(nowTime) + String.valueOf(randomNumber);
				  
				// 확장자 구하기 
				int pos = file.getOriginalFilename().lastIndexOf("."); 
				String ext = file.getOriginalFilename().substring(pos); 
				
				// /xxxxxx_upload_files/파일명.ext 형태로 객체에 넣음 
				fileName = filePath + "/" + newfileName + ext;
				originFileName = file.getOriginalFilename();
				c.setContents(fileName);
				c.setOrigin(originFileName);
				
				// 폴더 없으면 생성
				File uploadPath = new File(root + filePath);
				if(!uploadPath.exists()) {
					uploadPath.mkdirs();
				}
			
				// 해당 폴더에 파일 생성
				file.transferTo(new File(root + fileName));
				  
			} catch (IllegalStateException e) { 
				mv.addObject("message",e.getMessage());
				mv.setViewName("common/errorPage");	  
			} catch (IOException e) { 
				mv.addObject("message",e.getMessage());
				mv.setViewName("common/errorPage");	  
			} 
		}
		
		c.setPageId(pageId);
		c.setPostDate(postDate);

		try {

			csi.insert(c); 
			mv.setViewName("redirect:view.ol");
		  
		} catch (Exception e) { 
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");	  
		} 
		
		return mv;  
	}
	
	// contents 수정 폼
	@RequestMapping(value = "updateView.ol")
	public ModelAndView updateView(ModelAndView mv, @RequestParam(value="id") int id) {
		try {
			Contents c = csi.findById(id);
			mv.addObject("c", c);
			mv.setViewName("opn/lat/opn_lat_00004");
		} catch (ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("common/errorPage");	  
		}
		return mv;
	}
	// contents 수정
	@RequestMapping(value = "update.ol", method = RequestMethod.POST) // DI 의존성 주입
	public ModelAndView update(Contents c, ModelAndView mv, 
			@RequestParam(required=false) MultipartFile file, 
			HttpServletRequest request) throws ContentsException {
		try {
			System.out.println("#################### update.lt file.isEmpty() : " + file.isEmpty() + "####################");
			System.out.println("#################### update.lt content : " + c + "####################");
				
			if(!file.isEmpty()) { //
				String fileName = "";
				String originFileName = "";
				
				// ##################### 파일 삭제 처리 #######################
				// String deleteFileName = csi.findById(c.getId()).getContents();

				// 파일명 새이름 설정
				int randomNumber = (int)((Math.random()*10000)+1);
				SimpleDateFormat format = new SimpleDateFormat ("yyyyMMddHHmmss");
				Date nowTime = new Date();
				String newfileName = format.format(nowTime) + String.valueOf(randomNumber);	
				String deleteFileName = csi.findById(c.getId()).getContents();
				
				// 확장자 구하기
				int pos = file.getOriginalFilename().lastIndexOf(".");
				String ext = file.getOriginalFilename().substring(pos);
				// /xxxxxx_upload_files/파일명.ext 형태로 객체에 넣음 
				fileName = filePath + "/" + newfileName + ext;
				originFileName = file.getOriginalFilename();
				c.setContents(fileName);
				c.setOrigin(originFileName);
				
				
				// 폴더 없으면 생성
				File uploadPath = new File(root + filePath);
				if(!uploadPath.exists()) {
					uploadPath.mkdirs();
				}
			
				// 해당 폴더에 파일 생성
				file.transferTo(new File(root + fileName));
				
				// ##################### 파일 삭제 처리 #######################
				String deleteFilePath = root + deleteFileName;
				
				// ##################### 파일 삭제 처리 #######################
				File deleteFile = new File(deleteFilePath); // 파일 URL
				
				System.out.println("#################### update.ee deleteFilePath : " + deleteFilePath + "####################");
				
				if(deleteFile.exists()) {
					if(deleteFile.delete()) {
						System.out.println("파일 삭제 완료");
					} else {
						System.out.println("파일 삭제 실패");
					}
				} else {
					System.out.println("파일이 존재하지 않습니다.");
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
			
			c = csi.findById(c.getId());
			mv.addObject("c", c);
			mv.setViewName("redirect:detail.ol?id=" + c.getId());
		} catch (Exception e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("redirect:/common/errorPage");

		}
		return mv;
	}
	
	// contents 삭제 
	@RequestMapping(value = "delete.ol", method = RequestMethod.GET) // DI 의존성 주입
	public ModelAndView delete(
			ModelAndView mv,
			@RequestParam(value="id") int id,
			HttpServletRequest request){
		
		try {
			String deleteFileName = csi.findById(id).getContents();
			// ##################### 파일 삭제 처리 #######################
			String deleteFilePath = root + deleteFileName;
			
			// ##################### 파일 삭제 처리 #######################
			File deleteFile = new File(deleteFilePath); // 파일 URL
			
			System.out.println("#################### update.ib deleteFilePath : " + deleteFilePath + "####################");
			
			if(deleteFile.exists()) {
				if(deleteFile.delete()) {
					System.out.println("파일 삭제 완료");
				} else {
					System.out.println("파일 삭제 실패");
				}
			} else {
				System.out.println("파일이 존재하지 않습니다.");
			}
			
			csi.delete(id);
			mv.setViewName("redirect:view.ol");
		} catch (ContentsException e) {
			mv.addObject("message",e.getMessage());
			mv.setViewName("redirect:/common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="download.ol")
	public void download(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="id") int id) {
	
		try {
			Contents c = csi.findById(id);
			String downloadFileName = c.getText();
			String downloadFilePath = root + downloadFileName;
			byte fileByte[] = FileUtils.readFileToByteArray(new File(downloadFilePath));
			
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; fileName=" + URLEncoder.encode(c.getOrigin(), "UTF-8") + ";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.getOutputStream().write(fileByte);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch(ContentsException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}