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
	public List<Contents> findByPageId(String pageId) throws ContentsException {
		List<Contents> contents = cd.findByPageId(pageId);		
		return contents;
	}
	
	@Override
	public void updateContents(Contents c) throws ContentsException {
		System.out.println("service c : " + c.toString());
		
		cd.updateContents(c);
	}

	@Override
	public void updateContentsImg(Contents c) throws ContentsException {
		System.out.println("service c : " + c.toString());
		
		cd.updateContentsImg(c);
	}
}
