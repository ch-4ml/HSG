package com.hsg.intro.common.contents.model.domain;

import org.springframework.stereotype.Component;

@Component
public class ContentsDomain {
	private int id;
	private String pageId;
	private int category;
	private String title;
	private String url;
	private String contents;
	private String comment;
	private String text;
	private String postDate;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	@Override
	public String toString() {
		return "ContentsDto [id=" + id + ", pageId=" + pageId + ", category=" + category + ", title=" + title + ", url="
				+ url + ", contents=" + contents + ", comment=" + comment + ", text=" + text + ", postDate=" + postDate + "]";
	}	
}
