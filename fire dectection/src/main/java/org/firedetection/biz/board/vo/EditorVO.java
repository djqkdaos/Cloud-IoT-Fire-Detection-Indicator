package org.firedetection.biz.board.vo;

import org.springframework.web.multipart.MultipartFile;

public class EditorVO {
	private MultipartFile Filedata;

	public MultipartFile getFiledata() {
		return Filedata;
	}

	public void setFiledata(MultipartFile filedata) {
		Filedata = filedata;
	}
}
