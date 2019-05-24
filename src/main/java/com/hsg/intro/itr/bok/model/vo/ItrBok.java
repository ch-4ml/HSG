package com.hsg.intro.itr.bok.model.vo;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class ItrBok implements Serializable{
	
	private int bid;
	private int btype;
	private String btitle;
	private String bcontent;
	private String bimg;
	
	public ItrBok() {
		super();
	}
	
	public ItrBok(int bid, int btype, String btitle, String bcontent, String bimg) {
		super();
		this.bid = bid;
		this.btype = btype;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bimg = bimg;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getBtype() {
		return btype;
	}

	public void setBtype(int btype) {
		this.btype = btype;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public String getBimg() {
		return bimg;
	}

	public void setBimg(String bimg) {
		this.bimg = bimg;
	}

	@Override
	public String toString() {
		return "Book [bid=" + bid + ", btype=" + btype + ", btitle=" + btitle + ", bcontent=" + bcontent + ", bimg="
				+ bimg + "]";
	}
	
}
