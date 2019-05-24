package com.hsg.intro.member.model.vo;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class Member implements Serializable{
	
	private int mid;
	private String userId;
	private String userPw;
	private String phone;
	private Date enrollDate;
	private String status;
	public Member() {
		super();
	}
	
	public Member(int mid, String userId, String userPw, String phone, Date enrollDate, String status) {
		super();
		this.mid = mid;
		this.userId = userId;
		this.userPw = userPw;
		this.phone = phone;
		this.enrollDate = enrollDate;
		this.status = status;
	}

	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Member [mid=" + mid + ", userId=" + userId + ", userPw=" + userPw + ", phone=" + phone
				+ ", enrollDate=" + enrollDate + ", status=" + status + "]";
	}
	
}
