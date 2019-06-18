package com.hsg.intro.common.files.model.vo;

public class Files {
	private int id;
	private int contentsId;
	private String pageId;
	private String origin;
	private String stored;
	private int size;
	private int category;
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
	public int getContentsId() {
		return contentsId;
	}
	public void setContentsId(int contentsId) {
		this.contentsId = contentsId;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getStored() {
		return stored;
	}
	public void setStored(String stored) {
		this.stored = stored;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	@Override
	public String toString() {
		return "Files [id=" + id + ", contentsId=" + contentsId + ", pageId=" + pageId + ", origin=" + origin
				+ ", stored=" + stored + ", size=" + size + ", category=" + category + ", postDate=" + postDate + "]";
	}
	
}
