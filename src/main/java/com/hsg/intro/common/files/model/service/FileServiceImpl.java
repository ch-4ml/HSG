package com.hsg.intro.common.files.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hsg.intro.Exception.FilesException;
import com.hsg.intro.common.files.model.dao.FilesDaoImpl;
import com.hsg.intro.common.files.model.vo.Files;

public class FileServiceImpl implements FilesService {

	@Autowired
	private FilesDaoImpl fdi;
	
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
	public void update(Files f) throws FilesException {
		fdi.update(f);
	}

	@Override
	public void delete(int id) throws FilesException {
		fdi.delete(id);
	}
}
