package com.amwms.entities;

import java.util.Date;
/**
 * 
 * @author m^_^m
 *
 * @Statement:移库申请单，主键为单号，所有人员信息与人员表统一为char(3)
 * 
 */
public class TransferApplicationForms extends Entity {
 
	private String formID;
	private String commodityNumber;
	private String commodityName;
	private Date transferDate;
	private String auditor;
	private String applicant;
	private String targetLocation;
	private String orginalLocation;
	private String statement;
	
	public String getFormID() {
		return formID;
	}
	public void setFormID(String formID) {
		this.formID = formID;
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
	
	public Date getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}
	
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}	
	
	public String getTargetLocation() {
		return targetLocation;
	}
	public void setTargetLocation(String targetLocation) {
		this.targetLocation = targetLocation;
	}
	
	public String getOrginalLocation() {
		return orginalLocation;
	}
	public void setOrginalLocation(String orginalLocation) {
		this.orginalLocation = orginalLocation;
	}
	
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	
	public TransferApplicationForms( String formID, String commodityNumber, String commodityName, 
			Date transferDate, String applicant, String targetLocation, String orginalLocation,
			String statement) {
		super();
		this.formID = formID;
		this.commodityNumber = commodityNumber;
		this.commodityName = commodityName;
		this.transferDate = transferDate;
		this.applicant = applicant;
		this.targetLocation = targetLocation;
		this.orginalLocation = orginalLocation;
		this.statement = statement;
	}
	
	public TransferApplicationForms() {
		// TODO Auto-generated constructor stub
		super();
	}
	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	
}


