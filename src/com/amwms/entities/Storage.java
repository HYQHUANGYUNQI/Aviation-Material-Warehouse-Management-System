package com.amwms.entities;

public class Storage extends Entity{

	private String id;
	private String name;
	private int capacity;
	private int curCapacity;
	private String statement;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getCurCapacity() {
		return curCapacity;
	}
	public void setCurCapacity(int curCapacity) {
		this.curCapacity = curCapacity;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public Storage(String id, String name, int capacity, int curCapacity, String statement) {
		super();
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.curCapacity = curCapacity;
		this.statement = statement;
	}
	public Storage() {
		super();
	}
	
	
}
