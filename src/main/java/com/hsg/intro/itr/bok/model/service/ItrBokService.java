package com.hsg.intro.itr.bok.model.service;

import java.util.List;

import com.hsg.intro.Exception.ItrBokException;
import com.hsg.intro.itr.bok.model.vo.ItrBok;

public interface ItrBokService {

	void insertIbkBok(ItrBok b) throws ItrBokException;

	List<ItrBok> selectitrBokList() throws ItrBokException;

	ItrBok selectItrBok(String bId) throws ItrBokException;

	void updateIbkBok(ItrBok b) throws ItrBokException;

	void deleteIbkBok(String bid) throws ItrBokException;

}
