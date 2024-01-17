package com.amwms.entities;

/**
 * @author m^_^m
 * 
 * @Statement: 报废申请单，主键formID
 */

import java.util.Date;

public class ScrapApplicationForm extends Entity {
  
	private String formID ;
	private String commodityNumber ;
	private String commodityName ;
	private Date replenishDate ;
	private String applicant ; 
	private String auditor;
	private String qualityInspector ;
	private String cause ;
	private String statement ;
	
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
		
		public String getQualityInspector() {
			return qualityInspector;
		}
		public void setQualityInspector(String qualityInspector) {
			this.qualityInspector = qualityInspector;
		}
		
		public String getCause() {
			return cause;
		}
		public void setCause(String cause) {
			this.cause = cause;
		}
		
		public String getStatement() {
			return statement;
		}
		public void setStatement(String statement) {
			this.statement = statement;
		}
	
		public ScrapApplicationForm() {
			// TODO Auto-generated constructor stub
			super();
		}
		public ScrapApplicationForm( String formID, String commodityNumber, String commodityName, 
				Date replenishDate ,String applicant, String auditor, String qualityInspector, String cause, 
				String statement) {
			super();
			this.formID = formID;
			this.commodityNumber = commodityNumber;
			this.commodityName = commodityName;
			this.replenishDate = replenishDate;
			this.applicant = applicant;
			this.auditor = auditor;
			this.qualityInspector = qualityInspector;
			this.cause = cause;
			this.statement = statement;
		}
}
