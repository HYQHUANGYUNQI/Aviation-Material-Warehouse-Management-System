package com.amwms.entities;

import java.util.Date;

public class PutInApplicationForms extends Entity{

	private String formId;
	private String commodityNumber;
	private String commodityName;
	private Date replenishDate;
	private String applicant;
	private String auditor;
	private String supplierId;
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
	public Date getReplenishDate() {
		return replenishDate;
	}
	public void setReplenishDate(Date replenishDate) {
		this.replenishDate = replenishDate;
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
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	
	public PutInApplicationForms(String formId, String commodityNumber, String commodityName, Date replenishDate,
			String applicant, String auditor, String supplierId, String statement) {
		super();
		this.formId = formId;
		this.commodityNumber = commodityNumber;
		this.commodityName = commodityName;
		this.replenishDate = replenishDate;
		this.applicant = applicant;
		this.auditor = auditor;
		this.supplierId = supplierId;
		this.statement = statement;
	}
	
	public PutInApplicationForms() {
		super();
	}
	
}
