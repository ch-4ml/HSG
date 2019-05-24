package com.hsg.intro.edu.eln.model.dao;

import java.util.List;

import com.hsg.intro.Exception.EduElnException;
import com.hsg.intro.edu.eln.model.vo.EduEln;

public interface EduElnDao {
	void insertEduEln(EduEln e) throws EduElnException;
	List<EduEln> findAll() throws EduElnException;
	EduEln findById(int id) throws EduElnException;
	void updateEduEln(EduEln e) throws EduElnException;
	void deleteEduEln(int id) throws EduElnException;
}
