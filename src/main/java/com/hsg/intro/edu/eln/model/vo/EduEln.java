package com.hsg.intro.edu.eln.model.vo;

import java.util.Date;

public class EduEln {
	private int id;
	private int category;
	private String title;
	private String text;
	private String video;
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
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	
	@Override
	public String toString() {
		return "EduEln [id=" + id + ", category=" + category + ", title=" + title + ", text=" + text + ", video="
				+ video + ", postDate=" + postDate + "]";
	}
		
}
