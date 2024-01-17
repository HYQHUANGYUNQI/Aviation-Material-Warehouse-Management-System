package com.amwms.entities;

/**
 * 
 * @author lenovo
 *
 */
public class Partstype extends Entity{

	private String partstypeId; //类型编号
	private String partypeName; //名称
	private String statement; //备注
	
	public Partstype(String partstypeId, String partypeName, String statement) {
		super();
		this.partstypeId = partstypeId;
		this.partypeName = partypeName;
		this.statement = statement;
	}
	public Partstype() {
		super();
	}
	
	public String getPartstypeId() {
		return partstypeId;
	}
	public void setPartstypeId(String partstypeId) {
		this.partstypeId = partstypeId;
	}
	public String getPartypeName() {
		return partypeName;
	}
	public void setPartypeName(String partypeName) {
		this.partypeName = partypeName;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	
}
