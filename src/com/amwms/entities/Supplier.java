package com.amwms.entities;

/**
 * 
 * @author lenovo
 *
 */
public class Supplier extends Entity{

	private String supplierId; //供应商代码
	private String supplierName; //名称
	private String statement; //备注
	public Supplier(String supplierId, String supplierName, String statement) {
		super();
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.statement = statement;
	}
	public Supplier() {
		super();
	}
	
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	
	
	
}
