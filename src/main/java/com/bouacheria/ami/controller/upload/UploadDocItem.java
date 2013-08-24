package com.bouacheria.ami.controller.upload;


public class UploadDocItem {
	
	private Long requestId;
	private String requestNumber;
	private String hospitalName;
	private String filePath;
	private String fileName;
	private String contentType;
	
	
	public Long getRequestId() {
		return requestId;
	}
	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
	public String getRequestNumber() {
		return requestNumber;
	}
	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
		
}
