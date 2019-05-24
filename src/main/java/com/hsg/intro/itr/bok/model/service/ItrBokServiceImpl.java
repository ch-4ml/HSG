package com.hsg.intro.itr.bok.model.service;

import java.util.List;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsg.intro.Exception.ItrBokException;
import com.hsg.intro.itr.bok.model.dao.ItrBokDaoImpl;
import com.hsg.intro.itr.bok.model.vo.ItrBok;


@Service
public class ItrBokServiceImpl implements ItrBokService{
	@Autowired
	private ItrBokDaoImpl ibd;

	@Override
	public void insertIbkBok(ItrBok b) throws ItrBokException{
		System.out.println("serveice b : " + b.toString());
		
		ibd.insertIbkBok(b);
		
	}
	
	@Override
	public List<ItrBok> selectitrBokList() throws ItrBokException{
		
		return ibd.selectitrBokList();
	}
	
	@Override
	public ItrBok selectItrBok(String bId) throws ItrBokException{
		
		return ibd.selectItrBok(bId);
	}
	
	@Override
	public void updateIbkBok(ItrBok b) throws ItrBokException{
		
		ibd.updateIbkBok(b);
	}
	
	@Override
	public void deleteIbkBok(String bid) throws ItrBokException{
		
		ibd.deleteIbkBok(bid);
		
	}

}
