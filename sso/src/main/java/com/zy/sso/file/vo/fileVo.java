package com.zy.sso.file.vo;

import java.sql.Timestamp;

public class fileVo {
	private Integer id;
	private String type;
	private String size;
	private String path;
	private Timestamp uploadTime;
	private String ct;
	private String titleOrig;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Timestamp getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getCt() {
		return ct;
	}
	public void setCt(String ct) {
		this.ct = ct;
	}
	public String getTitleOrig() {
		return titleOrig;
	}
	public void setTitleOrig(String titleOrig) {
		this.titleOrig = titleOrig;
	}
	@Override
	public String toString() {
		return "fileVo [id=" + id + ", type=" + type + ", size=" + size + ", path=" + path + ", uploadTime="
				+ uploadTime + ", ct=" + ct + ", titleOrig=" + titleOrig + "]";
	}

	
	
}
