package org.firedetection.biz.board.vo;

import java.util.Date;

public class CsBoardVO {
	private int cs_num;
	private String title;
	private String content;
	private Date regDate;
	private int hit;
	private String id;
	
	public CsBoardVO() {
		super();
	}

	public CsBoardVO(int cs_num, String title, String content, Date regDate, int hit, String id) {
		super();
		this.cs_num = cs_num;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		this.hit = hit;
		this.id = id;
	}

	public CsBoardVO(String title, String content, Date regDate, int hit, String id) {
		super();
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		this.hit = hit;
		this.id = id;
	}
	
	public CsBoardVO(int cs_num, String title, String content, String id) {
		super();
		this.cs_num = cs_num;
		this.title = title;
		this.content = content;
		this.id = id;
	}

	public int getCs_num() {
		return cs_num;
	}

	public void setCs_num(int cs_num) {
		this.cs_num = cs_num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CsBoardVO [cs_num=" + cs_num + ", title=" + title + ", content=" + content + ", regDate=" + regDate
				+ ", hit=" + hit + ", id=" + id + "]";
	}

		
}
