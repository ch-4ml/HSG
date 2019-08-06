package com.hsg.intro.common.contents.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsg.intro.Exception.ContentsException;
import com.hsg.intro.common.PageInfo;
import com.hsg.intro.common.contents.model.dao.ContentsDaoImpl;
import com.hsg.intro.common.contents.model.vo.Contents;

@Service
public class ContentsServiceImpl implements ContentsService {

	@Autowired
	private ContentsDaoImpl cdi;
	
	@Override
	public void insert(Contents c) throws ContentsException {
		cdi.insert(c);
	}
	
	@Override
	public Contents findOneByPageId(String pageId) throws ContentsException {
		Contents content = cdi.findOneByPageId(pageId);
		return content;
	}
	
	@Override
	public List<Contents> findByPageId(String pageId) throws ContentsException {
		List<Contents> contents = cdi.findByPageId(pageId);		
		return contents;
	}
	
	@Override
	public List<Contents> findByPageId(String pageId, PageInfo pi) throws ContentsException {
		List<Contents> contents = cdi.findByPageId(pageId, pi);		
		return contents;
	}
	
	@Override
	public List<Contents> findByPageId(HashMap<String, String> hmap, PageInfo pi) throws ContentsException {
		List<Contents> contents = cdi.findByPageId(hmap, pi);
		return contents;
	}
	
	@Override
	public Contents findById(int id) throws ContentsException {
		Contents content = cdi.findById(id);
		return content;
	}
	
	@Override
	public void update(Contents c) throws ContentsException {		
		cdi.update(c);
	}

	@Override
	public void delete(int id) throws ContentsException {
		cdi.delete(id);
	}

	public int getListCount() throws ContentsException {
		return cdi.getListCount();
	}
	
	public int getListCount(String pageId) throws ContentsException {
		return cdi.getListCount(pageId);
	}

	public int getListCount(HashMap<String, String> hmap) throws ContentsException {		
		return cdi.getListCount(hmap);
	}
}








