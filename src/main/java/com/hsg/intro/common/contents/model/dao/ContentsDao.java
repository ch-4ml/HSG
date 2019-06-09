package com.hsg.intro.common.contents.model.dao;

import java.util.List;

import com.hsg.intro.Exception.ContentsException;
import com.hsg.intro.common.contents.model.vo.Contents;

public interface ContentsDao {
	void insert(Contents c) throws ContentsException;
	Contents findOneByPageId(String pageId) throws ContentsException;
	List<Contents> findByPageId(String pageId) throws ContentsException;
	Contents findById(int id) throws ContentsException;
	void update(Contents c) throws ContentsException;
	void updateImage(Contents c) throws ContentsException;
	void delete(int id) throws ContentsException;
}
