package com.amwms.entities;

public class User extends Entity{
	
	private String userId;
	private String userName;
	private String duty;
	private String pwd;
	private String statement;
	
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	
	public User(String userId, String userName, String duty, String pwd) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.duty = duty;
		this.pwd = pwd;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}
}
