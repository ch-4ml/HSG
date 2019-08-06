package com.hsg.intro.common.contents.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hsg.intro.Exception.ContentsException;
import com.hsg.intro.common.PageInfo;
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
	public Contents findOneByPageId(String pageId) throws ContentsException {
		Contents content = sqlSession.selectOne("Contents.findOneByPageId", pageId);
		return content;
	}
	
	@Override
	public List<Contents> findByPageId(String pageId) throws ContentsException {
		List<Contents> contents = sqlSession.selectList("Contents.findByPageId", pageId);
		return contents;
	}
	
	@Override
	public List<Contents> findByPageId(String pageId, PageInfo pi) throws ContentsException {
		int offset = (pi.getCurrentPage() - 1) * pi.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getLimit());
		List<Contents> contents = sqlSession.selectList("Contents.findByPageId", pageId, rowBounds);
		return contents;
	}
	
	@Override
	public List<Contents> findByPageId(HashMap<String, String> hmap, PageInfo pi) throws ContentsException {
		
		int offset = (pi.getCurrentPage() - 1) * pi.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getLimit());
		List<Contents> contents = sqlSession.selectList("Contents.findByPageIdMap", hmap, rowBounds);
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
	public void delete(int id) throws ContentsException {
		try {
			sqlSession.delete("Contents.delete", id);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public int getListCount() throws ContentsException {
		Integer result = 0;
		try {
			result = sqlSession.selectOne("Contents.getListCount");
			if(result == null) {
				result = 0;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("count in daoimpl : " + result);
		return result;
	}
	
	public int getListCount(String pageId) throws ContentsException {
		Integer result = 0;
		try {
			result = sqlSession.selectOne("Contents.getListCount", pageId);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getListCount(HashMap<String, String> hmap) throws ContentsException{
		Integer result = 0;
		try {
			result = sqlSession.selectOne("Contents.getListCount", hmap);
			
			if(result == null) {
				result = 0;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
