package com.hsg.intro.common.contents.model.vo;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Contents implements Serializable {
	private int id;
	private String pageId;
	private int category;
	private String title;
	private String text;
	private String image;
	private String postDate;
	
	public Contents() {
		super();
	}

	public Contents(int id, String pageId, int category, String title, String text, String image,
			String postDate) {
		super();
		this.id = id;
		this.pageId = pageId;
		this.category = category;
		this.title = title;
		this.text = text;
		this.image = image;
		this.postDate = postDate;
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

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	@Override
	public String toString() {
		return "Contents [id=" + id + ", pageId=" + pageId + ", category=" + category + ", title="
				+ title + ", text=" + text + ", image=" + image + ", postDate=" + postDate + "]";
	}
}
