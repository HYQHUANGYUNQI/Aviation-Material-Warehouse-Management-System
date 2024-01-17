package com.amwms.entities;

import java.util.Date;

/**
 * 
 * @author BigBing
 * @Statement: 件号序列号都添加在航材内容中
 *
 */
public class AirMaterial extends Entity {

	private String materialName;
	private String serial;
	private String partsTypeId;
	private String materialSpecification;
	private String commodityNumber;
	private Date purchaseDate;
	private String supplierId;
	private String locationId;
	private int unitCost;
	private String state;
	private Date serviceLife;
	private String statement;
	
	public String getCommodityNumber() {
		return commodityNumber;
	}
	public void setCommodityNumber(String commodityNumber) {
		this.commodityNumber = commodityNumber;
	}
	
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	
	public String getPartsTypeId() {
		return partsTypeId;
	}
	public void setPartsTypeId(String materialType) {
		this.partsTypeId = materialType;
	}
	
	public String getMaterialSpecification() {
		return materialSpecification;
	}
	public void setMaterialSpecification(String materialSpecification) {
		this.materialSpecification = materialSpecification;
	}
	
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	
	public int getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(int unitCost) {
		this.unitCost = unitCost;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public Date getServiceLife() {
		return serviceLife;
	}
	public void setServiceLife(Date serviceLife) {
		this.serviceLife = serviceLife;
	}
	
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	
	public AirMaterial(String materialName, String serial, String materialType, String materialSpecification,
			String commodityNumber, Date purchaseDate, String supplierId, int unitCost, String state,
			Date serviceLife, String statement) {
		super();
		this.materialName = materialName;
		this.serial = serial;
		this.partsTypeId = materialType;
		this.materialSpecification = materialSpecification;
		this.commodityNumber = commodityNumber;
		this.purchaseDate = purchaseDate;
		this.supplierId = supplierId;
		this.unitCost = unitCost;
		this.state = state;
		this.serviceLife = serviceLife;
		this.statement = statement;
	}
	
	public AirMaterial() {
		super();
	}
	
	
	
}
