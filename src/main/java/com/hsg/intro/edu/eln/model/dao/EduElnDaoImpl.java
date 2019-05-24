package com.hsg.intro.edu.eln.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hsg.intro.Exception.EduElnException;
import com.hsg.intro.edu.eln.model.vo.EduEln;

@Repository
public class EduElnDaoImpl implements EduElnDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public void insertEduEln(EduEln e) throws EduElnException {
		try {
			sqlSession.insert("EduEln.insert", e);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public List<EduEln> findAll() throws EduElnException {
		List<EduEln> eln = sqlSession.selectList("EduEln.findAll");
		return eln;
	}

	@Override
	public EduEln findById(int id) throws EduElnException {
		EduEln eln = sqlSession.selectOne("EduEln.findById", id);
		return eln;
	}

	@Override
	public void updateEduEln(EduEln e) throws EduElnException {
		try {
			sqlSession.update("EduEln.update", e);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void deleteEduEln(int id) throws EduElnException {
		try {
			sqlSession.delete("EduEln.delete", id);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
