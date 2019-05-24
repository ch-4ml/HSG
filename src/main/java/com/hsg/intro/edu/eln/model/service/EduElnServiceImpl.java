package com.hsg.intro.edu.eln.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsg.intro.Exception.EduElnException;
import com.hsg.intro.edu.eln.model.dao.EduElnDaoImpl;
import com.hsg.intro.edu.eln.model.vo.EduEln;

@Service
public class EduElnServiceImpl implements EduElnService {

	@Autowired
	private EduElnDaoImpl eed;
	
	@Override
	public void insertEduEln(EduEln e) throws EduElnException {
		System.out.println("service eed : " + eed.toString());
		eed.insertEduEln(e);
	}

	@Override
	public List<EduEln> findAll() throws EduElnException {
		List<EduEln> eln = eed.findAll();
		return eln;
	}

	@Override
	public EduEln findById(int id) throws EduElnException {
		EduEln eln = eed.findById(id);
		return eln;
	}

	@Override
	public void updateEduEln(EduEln e) throws EduElnException {
		System.out.println("service eed : " + eed.toString());
		eed.updateEduEln(e);
	}

	@Override
	public void deleteEduEln(int id) throws EduElnException {
		eed.deleteEduEln(id);
	}

}
