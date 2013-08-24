package com.bouacheria.ami.controller.upload;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadItem {
	
	private String name;
	private CommonsMultipartFile fileData;
	private long  svcReqId;
	private String  requestNumber;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CommonsMultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}

	
	public long getSvcReqId() {
		return svcReqId;
	}

	public void setSvcReqId(long svcReqId) {
		this.svcReqId = svcReqId;
	}

	public String getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}
}
