package com.hsg.intro.itr.bok.model.dao;

import java.util.List;

import com.hsg.intro.Exception.ItrBokException;
import com.hsg.intro.itr.bok.model.vo.ItrBok;

public interface ItrBokDao {

	void insertIbkBok(ItrBok b) throws ItrBokException;

	List<ItrBok> selectitrBokList() throws ItrBokException;

	ItrBok selectItrBok(String bid) throws ItrBokException;

	void updateIbkBok(ItrBok b) throws ItrBokException;

	void deleteIbkBok(String bid) throws ItrBokException;

}
