package com.hsg.intro.itr.bok.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hsg.intro.Exception.ItrBokException;
import com.hsg.intro.itr.bok.model.vo.ItrBok;
@Repository
public class ItrBokDaoImpl implements ItrBokDao{
	@Autowired
	private SqlSessionTemplate sqlSession;
	@Override
	public void insertIbkBok(ItrBok b) throws ItrBokException {
		
		sqlSession.insert("ItrBok.itrBokInsert", b);
	}
	@Override
	public List<ItrBok> selectitrBokList() throws ItrBokException {
		
		return sqlSession.selectList("ItrBok.selectitrBokList");
	}
	@Override
	public ItrBok selectItrBok(String bId) throws ItrBokException{
		
		return sqlSession.selectOne("ItrBok.selectitrBok", Integer.parseInt(bId));
	}
	@Override
	public void updateIbkBok(ItrBok b) throws ItrBokException{
		
		sqlSession.update("ItrBok.itrBokUpdate", b);
	}
	@Override
	public void deleteIbkBok(String bid) throws ItrBokException{
		
		sqlSession.delete("ItrBok.itrBokDelete", Integer.parseInt(bid));
		
	}
	
}
