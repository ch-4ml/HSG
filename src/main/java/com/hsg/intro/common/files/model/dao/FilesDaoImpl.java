package com.hsg.intro.common.files.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hsg.intro.Exception.FilesException;
import com.hsg.intro.common.files.model.vo.Files;

@Repository
public class FilesDaoImpl implements FilesDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public void insert(Files f) throws FilesException {
		try {
			sqlSession.insert(statement)
		}

	}

	@Override
	public Files findById(int id) throws FilesException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Files> findByContentsId(int contentsId) throws FilesException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Files> findByPageId(String pageId) throws FilesException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Files f) throws FilesException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) throws FilesException {
		// TODO Auto-generated method stub

	}

}
