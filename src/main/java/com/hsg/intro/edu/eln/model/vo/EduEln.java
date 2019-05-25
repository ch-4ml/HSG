package com.hsg.intro.edu.eln.model.vo;

import java.util.Date;

public class EduEln {
	private int id;
	private int category;
	private String title;
	private String text;
	private String image;
	private String postDate;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		return "EduEln [id=" + id + ", category=" + category + ", title=" + title + ", text=" + text + ", image="
				+ image + ", postDate=" + postDate + "]";
	}
	
		
}
