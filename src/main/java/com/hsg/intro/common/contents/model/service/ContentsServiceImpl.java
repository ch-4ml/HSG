package com.hsg.intro.common.contents.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsg.intro.Exception.ContentsException;
import com.hsg.intro.common.contents.model.dao.ContentsDaoImpl;
import com.hsg.intro.common.contents.model.vo.Contents;

@Service
public class ContentsServiceImpl implements ContentsService {

	@Autowired
	private ContentsDaoImpl cd;
	
	@Override
	public void insert(Contents c) throws ContentsException {
		cd.insert(c);
	}
	
	@Override
	public List<Contents> findByPageId(String pageId) throws ContentsException {
		List<Contents> contents = cd.findByPageId(pageId);		
		return contents;
	}
	
	@Override
	public Contents findById(int id) throws ContentsException {
		Contents content = cd.findById(id);
		return content;
	}
	
	@Override
	public void update(Contents c) throws ContentsException {		
		cd.update(c);
	}

	@Override
	public void updateImage(Contents c) throws ContentsException {		
		cd.updateImage(c);
	}

	@Override
	public void delete(int id) throws ContentsException {
		cd.delete(id);
	}
}
