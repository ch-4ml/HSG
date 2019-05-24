package com.hsg.intro.common.contents.model.vo;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Contents implements Serializable {
	private int contentsId;
	private String pageId;
	private String text;
	private String image;
	
	public Contents() {
		super();
	}
	
	public Contents(int contentsId, String pageId, String text, String image) {
		super();
		this.contentsId = contentsId;
		this.pageId = pageId;
		this.text = text;
		this.image = image;
	}
	
	public int getContentsId() {
		return contentsId;
	}
	public void setContentsId(int contentsId) {
		this.contentsId = contentsId;
	}
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return "Contents [contentsId=" + contentsId + ", pageId=" + pageId + ", text=" + text + ", image=" + image
				+ "]";
	}
}
