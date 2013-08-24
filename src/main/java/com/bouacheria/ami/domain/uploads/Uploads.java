package com.bouacheria.ami.domain.uploads;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "amischema", name = "UPLOADS")
public class Uploads {

	@Id
	@GeneratedValue
	protected Long id = null;
	
	@Column(name="REQUEST_ID")
	private Long requestId;

	@Column(name="REQUEST_NUMBER", length=200)
	private String requestNumber;

	@Column(name="HOSPITAL_NAME", length=200)
	private String hospitalName;
	
	@Column(name="FILE_PATH", length=300)
	private String filePath;
	
	@Column(name="FILE_NAME", length=300)
	private String fileName;
	
	@Column(name="CONTENT_TYPE", length=100)
	private String contentType;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	
}
