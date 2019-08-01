package com.hsg.intro.common.files.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hsg.intro.Exception.ContentsException;
import com.hsg.intro.Exception.FilesException;
import com.hsg.intro.common.files.model.vo.Files;

@Repository
public class FilesDaoImpl implements FilesDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public void insert(Files f) throws FilesException {
		sqlSession.insert("Files.insert", f);
	}

	@Override
	public Files findById(int id) throws FilesException {
		Files file = sqlSession.selectOne("Files.findById", id);
		return file;
	}

	@Override
	public List<Files> findByContentsId(int contentsId) throws FilesException {
		List<Files> files = sqlSession.selectList("Files.findByContentsId", contentsId);
		return files;
	}

	@Override
	public List<Files> findByPageId(String pageId) throws FilesException {
		List<Files> files = sqlSession.selectList("Files.findByPageId", pageId);
		return files;
	}

	@Override
	public List<Files> findByPageId(String pageId, Integer currentCount) throws FilesException {
		
		RowBounds rowBounds = new RowBounds(currentCount, 1);
		List<Files> files = sqlSession.selectList("Files.findByPageId", pageId, rowBounds);
		return files;
	}
	
	@Override
	public void update(Files f) throws FilesException {
		sqlSession.update("Files.update", f);
	}

	@Override
	public void updateOrder(List<Integer> order) throws FilesException {
		sqlSession.update("Files.updateOrder", order);
	}
	
	@Override
	public void delete(int id) throws FilesException {
		sqlSession.delete("Files.delete", id);
	}
	
	@Override
	public void deleteByStored(String stored) throws FilesException {
		sqlSession.delete("Files.deleteByStored", stored);
	}
	
	public int getListCount(String pageId) throws ContentsException {
		Integer result = 0;
		try {
			result = sqlSession.selectOne("Files.getListCount", pageId);
			if(result == null) result = 0;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
