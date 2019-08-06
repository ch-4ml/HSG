package com.hsg.intro.common.files.model.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hsg.intro.Exception.ContentsException;
import com.hsg.intro.Exception.FilesException;
import com.hsg.intro.common.files.model.dao.FilesDaoImpl;
import com.hsg.intro.common.files.model.vo.Files;

@Service
public class FilesServiceImpl implements FilesService {

	@Autowired
	private FilesDaoImpl fdi;
	
	private String root = "/hsglobal03/tomcat/webapps/var/HSG/uploadFiles";
	
	public void insertFile(List<MultipartFile> files, int contentsId, String dirName) {
		Files f = new Files();
		
		String filePath = root + dirName;
		for(MultipartFile file: files) {
		
			String originFileName = file.getOriginalFilename();
			
			// ���� �� �̸� ����(�ߺ� ����)
			int randomNumber = (int)((Math.random()*10000)+1);
			SimpleDateFormat format = new SimpleDateFormat ("yyyyMMddHHmmss");
			Date nowTime = new Date();
			String storedFileName = format.format(nowTime) + String.valueOf(randomNumber);
		
			// Ȯ���� ���ϱ�
			int pos = file.getOriginalFilename().lastIndexOf(".");
			String ext = file.getOriginalFilename().substring(pos);
			System.out.println(pos + " " + ext);
			originFileName = originFileName + ext;
			storedFileName = dirName + "/" + storedFileName + ext;
	
			f.setContentsId(contentsId);
			// f.setPageId(pageId);
			f.setOrigin(originFileName);
			f.setStored(storedFileName);
			f.setCategory(0); // image
			f.setSize(file.getSize());
			
			// ���丮�� ������ ���� �����
			File uploadPath = new File(filePath);
			if(!uploadPath.exists()) {
				uploadPath.mkdirs();
			}
			
			// ���� ���� ��� + �̸�
			String uploadFilePath = root + storedFileName;
			
			// ���� ���� �� DB�� ���� ���� ����
			try {
				insert(f);
				file.transferTo(new File(uploadFilePath));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (FilesException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateFile(List<MultipartFile> files, int contentsId, String dirName) {
		
		String filePath = root + dirName;
		
		// ����
		try {
			List<Files> fs = fdi.findByContentsId(contentsId);
			for(Files f: fs) {
				String deleteFileName = findById(f.getId()).getStored();
				String deleteFilePath = root + deleteFileName;
				File deleteFile = new File(deleteFilePath);
				
				if(deleteFile.exists()) {
					if(deleteFile.delete()) System.out.println("���� ���� �Ϸ�");
					else System.out.println("���� ���� ����");
				} else System.out.println("������ �������� �ʽ��ϴ�.");
				
				for(MultipartFile file: files) {
					String originFileName = file.getOriginalFilename();
					// ���� �� �̸� ����(�ߺ� ����)
					int randomNumber = (int)((Math.random()*10000)+1);
					SimpleDateFormat format = new SimpleDateFormat ("yyyyMMddHHmmss");
					Date nowTime = new Date();
					String storedFileName = format.format(nowTime) + String.valueOf(randomNumber);
				
					// Ȯ���� ���ϱ�
					int pos = file.getOriginalFilename().lastIndexOf(".");
					String ext = file.getOriginalFilename().substring(pos);
					System.out.println(pos + " " + ext);
					originFileName = originFileName + ext;
					storedFileName = dirName + "/" + storedFileName + ext;
					
					f.setContentsId(contentsId);
					// f.setPageId(pageId);
					f.setOrigin(originFileName);
					f.setStored(storedFileName);
					f.setCategory(0); // image
					f.setSize(file.getSize());
					
					// ���丮�� ������ ���� �����
					File uploadPath = new File(filePath);
					if(!uploadPath.exists()) {
						uploadPath.mkdirs();
					}
					
					// ���� ���� ��� + �̸�
					String uploadFilePath = root + storedFileName;
					
					// ���� ���� �� DB�� ���� ���� ����
					try {
						update(f);
						file.transferTo(new File(uploadFilePath));
					} catch (IOException e) {
						e.printStackTrace();
					} catch (FilesException e) {
						e.printStackTrace();
					}
				}		
			}
		} catch(FilesException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteFile(int contentsId, String dirName) {		
		// ����
		try {
			List<Files> fs = fdi.findByContentsId(contentsId);
			for(Files f: fs) {
				String deleteFileName = findById(f.getId()).getStored();
				String deleteFilePath = root + deleteFileName;
				File deleteFile = new File(deleteFilePath);
				
				if(deleteFile.exists()) {
					if(deleteFile.delete()) System.out.println("���� ���� �Ϸ�");
					else System.out.println("���� ���� ����");
				} else System.out.println("������ �������� �ʽ��ϴ�.");
				
				delete(f.getId());
			}			
			
		} catch(FilesException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void insert(Files f) throws FilesException {
		fdi.insert(f);
	}

	@Override
	public Files findById(int id) throws FilesException {
		Files file = fdi.findById(id);
		return file;
	}

	@Override
	public List<Files> findByContentsId(int contentsId) throws FilesException {
		List<Files> files = fdi.findByContentsId(contentsId);
		return files;
	}

	@Override
	public List<Files> findByPageId(String pageId) throws FilesException {
		List<Files> files = fdi.findByPageId(pageId);
		return files;
	}

	@Override
	public List<Files> findByPageId(String pageId, Integer currentCount) throws FilesException {
		List<Files> files = fdi.findByPageId(pageId, currentCount);
		return files;
	}
	
	@Override
	public List<Integer> findIdByPageId(String pageId) throws FilesException {
		List<Integer> ids = fdi.findIdByPageId(pageId);
		return ids;
	}
	
	@Override
	public void update(Files f) throws FilesException {
		fdi.update(f);
	}
	
	@Override
	public void updateOrder(Map<String, Integer> map) throws FilesException {
		fdi.updateOrder(map);
	}

	@Override
	public void delete(int id) throws FilesException {
		fdi.delete(id);
	}
	
	@Override
	public void deleteByStored(String stored) throws FilesException {
		fdi.deleteByStored(stored);
	}
	
	public int getListCount(String pageId) throws ContentsException {
		return fdi.getListCount(pageId);
	}
}
