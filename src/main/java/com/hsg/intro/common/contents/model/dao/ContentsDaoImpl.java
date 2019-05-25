package com.hsg.intro.common.contents.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hsg.intro.Exception.ContentsException;
import com.hsg.intro.common.contents.model.vo.Contents;

@Repository
public class ContentsDaoImpl implements ContentsDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public void insert(Contents c) throws ContentsException {
		try {
			sqlSession.insert("Contents.insert", c);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Contents> findByPageId(String pageId) throws ContentsException {
		List<Contents> contents = sqlSession.selectList("Contents.findByPageId", pageId);
		return contents;
	}
	
	@Override
	public Contents findById(int id) throws ContentsException {
		Contents content = sqlSession.selectOne("Contents.findById", id);
		return content;
	}
	
	@Override
	public void update(Contents c) throws ContentsException {
		try {
			sqlSession.update("Contents.update", c);			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void updateImage(Contents c) throws ContentsException {
		try {
			sqlSession.update("Contents.updateImage", c);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) throws ContentsException {
		try {
			sqlSession.delete("Contents.delete", id);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
