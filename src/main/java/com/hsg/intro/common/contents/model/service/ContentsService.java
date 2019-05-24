package com.hsg.intro.common.contents.model.service;

import java.util.List;

import com.hsg.intro.Exception.ContentsException;
import com.hsg.intro.common.contents.model.vo.Contents;

public interface ContentsService {
	List<Contents> findByPageId(String pageId) throws ContentsException;
	void updateContents(Contents c) throws ContentsException;
	void updateContentsImg(Contents c) throws ContentsException;
}
