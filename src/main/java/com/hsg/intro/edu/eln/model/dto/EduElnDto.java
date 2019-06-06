package com.hsg.intro.edu.eln.model.dto;

public class EduElnDto {
	private int id;
	private String pageId;
	private int category;
	private String title;
	private String text;
	private String comment;
	private String image;
	private String postDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public EduElnDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "EduElnDto [id=" + id + ", pageId=" + pageId + ", category=" + category + ", title=" + title + ", text="
				+ text + ", comment=" + comment + ", image=" + image + ", postDate=" + postDate + "]";
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
}
