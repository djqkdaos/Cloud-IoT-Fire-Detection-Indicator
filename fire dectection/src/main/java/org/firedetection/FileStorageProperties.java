package org.firedetection;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
	private String uploadDir;
	private String pdfDir;

	public String getPdfdDir() {
		return pdfDir;
	}

	public void setPdfDir(String pdfDir) {
		this.pdfDir = pdfDir;
	}
	
	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
}
