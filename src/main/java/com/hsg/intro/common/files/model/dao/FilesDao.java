package com.hsg.intro.common.files.model.dao;

import java.util.List;

import com.hsg.intro.Exception.FilesException;
import com.hsg.intro.common.files.model.vo.Files;

public interface FilesDao {
	void insert(Files f) throws FilesException;
	Files findById(int id) throws FilesException;
	List<Files> findByContentsId(int contentsId) throws FilesException;
	List<Files> findByPageId(String pageId) throws FilesException;
	void update(Files f) throws FilesException;
	void delete(int id) throws FilesException;
}
