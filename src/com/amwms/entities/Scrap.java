package com.amwms.entities;

/**
 * 
 * @author m^_^m
 *
 * @Statement: 报废方式 主键为scrapID
 */

public class Scrap extends Entity{
	
	 private String scrapID;
	 private String scrapName;
	 private String statement;
	 
	    public String getScrapID() {
			return scrapID;
		}
		public void setScrapID(String scrapID) {
			this.scrapID = scrapID;
		}
		
		public String getScrapName() {
			return scrapName;
		}
		public void setScrapName(String scrapName) {
			this.scrapName = scrapName;
		}
		
		public String getStatement() {
			return statement;
		}
		public void setStatement(String statement) {
			this.statement = statement;
		}
	 
		public Scrap() {
			// TODO Auto-generated constructor stub
			super();
		}
		
		public  Scrap( String scrapID, String scrapName, String statement) {
			super();
			this.scrapID = scrapID;
			this.scrapName = scrapName;
			this.statement = statement;
	}
		
}
