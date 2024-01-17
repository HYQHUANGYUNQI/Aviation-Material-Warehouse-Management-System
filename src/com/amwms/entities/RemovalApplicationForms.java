package com.amwms.entities;

import java.util.Date;

public class RemovalApplicationForms extends Entity{

	private String formId;
	private String commodityNumber;
	private String commodityName;
	private Date removalDate;
	private String applicant;
	private String auditor;
	private String currentLocationId;
	private String statement;
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public String getCommodityNumber() {
		return commodityNumber;
	}
	public void setCommodityNumber(String commodityNumber) {
		this.commodityNumber = commodityNumber;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public Date getRemovalDate() {
		return removalDate;
	}
	public void setRemovalDate(Date removalDate) {
		this.removalDate = removalDate;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public String getCurrentLocationId() {
		return currentLocationId;
	}
	public void setCurrentLocationId(String currentLocationId) {
		this.currentLocationId = currentLocationId;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public RemovalApplicationForms(String formId, String commodityNumber, String commodityName, Date removalDate,
			String applicant, String auditor, String currentLocationId, String statement) {
		super();
		this.formId = formId;
		this.commodityNumber = commodityNumber;
		this.commodityName = commodityName;
		this.removalDate = removalDate;
		this.applicant = applicant;
		this.auditor = auditor;
		this.currentLocationId = currentLocationId;
		this.statement = statement;
	}
	
	public RemovalApplicationForms() {
		super();
	}
	
}
