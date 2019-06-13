package com.hsg.intro.common.contents.model.vo;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Contents implements Serializable {
	private int id;
	private String pageId;
	private int category;
	private String title;
	private String contents;
	private String text;
	private String url;
	private String postDate;
	private String origin;
	
	public Contents() {
		super();
	}
	
	public Contents(int id, String pageId, int category, String title, String contents, String text, String url,
			String postDate, String origin) {
		super();
		this.id = id;
		this.pageId = pageId;
		this.category = category;
		this.title = title;
		this.contents = contents;
		this.text = text;
		this.url = url;
		this.postDate = postDate;
		this.origin = origin;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	@Override
	public String toString() {
		return "Contents [id=" + id + ", pageId=" + pageId + ", category=" + category + ", title=" + title
				+ ", contents=" + contents + ", text=" + text + ", url=" + url + ", postDate=" + postDate + ", origin="
				+ origin + "]";
	}
	
	
}
