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
	public List<Contents> findByPageId(String pageId) throws ContentsException {
		List<Contents> contents = sqlSession.selectList("Contents.findByPageId", pageId);
		
		return contents;
	}
	
	@Override
	public void updateContents(Contents c) throws ContentsException {
		try {
			sqlSession.update("Contents.updateContents", c);			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void updateContentsImg(Contents c) throws ContentsException {
		try {
			sqlSession.update("Contents.updateImgContents", c);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
