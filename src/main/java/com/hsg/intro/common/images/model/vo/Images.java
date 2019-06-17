package com.hsg.intro.common.images.model.vo;

public class Images {
	private int id;
	private int pageId;
	private int contentsId;
	private String oImageName;
	private String sImageName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public int getContentsId() {
		return contentsId;
	}
	public void setContentsId(int contentsId) {
		this.contentsId = contentsId;
	}
	public String getoImageName() {
		return oImageName;
	}
	public void setoImageName(String oImageName) {
		this.oImageName = oImageName;
	}
	public String getsImageName() {
		return sImageName;
	}
	public void setsImageName(String sImageName) {
		this.sImageName = sImageName;
	}	
}
