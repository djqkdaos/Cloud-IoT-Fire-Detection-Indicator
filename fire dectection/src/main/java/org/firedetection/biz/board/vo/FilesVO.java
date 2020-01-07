package org.firedetection.biz.board.vo;

import java.sql.Date;

public class FilesVO {
	private String user_id;
	private int file_num;
	private String filePath;
	private String fileName;
	private int fileSize;
	private String fileHash;
	private Date regDate;
	private int raspb_num;
	
	public FilesVO() {
		super();
	}

	public FilesVO(String filePath, String fileName, int fileSize, String fileHash, String user_id) {
		super();
		this.filePath = filePath;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.fileHash = fileHash;
		this.user_id = user_id;
	}
	public int getFile_num() {
		return file_num;
	}

	public void setFile_num(int file_num) {
		this.file_num = file_num;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getFileSize() {
		return fileSize;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getRaspb_num() {
		return raspb_num;
	}

	public void setRaspb_num(int raspb_num) {
		this.raspb_num = raspb_num;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileHash() {
		return fileHash;
	}

	public void setFileHash(String fileHash) {
		this.fileHash = fileHash;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "FilesVO [u_idx=" + user_id + ", file_num=" + file_num + ", filePath=" + filePath + ", fileName="
				+ fileName + ", fileSize=" + fileSize + ", fileHash=" + fileHash + ", regDate=" + regDate
				+ ", raspb_num=" + raspb_num + "]";
	}

	
}
