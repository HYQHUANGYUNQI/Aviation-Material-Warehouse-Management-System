package com.amwms.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amwms.entities.AirMaterial;
import com.amwms.entities.Entity;

public class QueryAirMaterialUtils {

	public static List<Entity> getEntiyList(ResultSet results) throws SQLException{
		List<Entity> list = new ArrayList<Entity>();
		while(results.next()) {
			AirMaterial material = new AirMaterial();
			material.setCommodityNumber(results.getString("commodityNumber"));
			material.setLocationId(results.getString("locationId"));
			material.setMaterialName(results.getString("materialName"));
			material.setMaterialSpecification(results.getString("materialSpecification"));
			material.setPartsTypeId(results.getString("materialType"));
			material.setPurchaseDate(results.getDate("purchaseDate"));
			material.setSerial(results.getString("serialNumber"));
			material.setServiceLife(results.getDate("serviceLife"));
			material.setState(results.getString("state"));
			material.setSupplierId(results.getString("supplierId"));
			material.setStatement(results.getString("statement"));
			material.setUnitCost(results.getInt("unitCost"));
			
			list.add(material);
		}
		return list;
	}
	
	public  List<Entity> getEntityListBySerial(List<Entity> all, String serial){
		List<Entity> list = new ArrayList<Entity>();
		for(Entity entity:all) {
			AirMaterial material = (AirMaterial)entity;
			if(material.getSerial().equals(serial)) {
				list.add(material);
				return list;
			}
		}
		return list;
	}

	public  List<Entity> getEntityListByType(List<Entity> all, String typeId){
		List<Entity> list = new ArrayList<Entity>();
		for(Entity entity:all) {
			AirMaterial material = (AirMaterial)entity;
			if(material.getPartsTypeId().equals(typeId)) {
				list.add(material);
			}
		}
		return list;
	}
	public  List<Entity> getEntityListByNameAndSupplier(List<Entity> all, String name,String supplier){
		List<Entity> list = new ArrayList<Entity>();
		for(Entity entity:all) {
			AirMaterial material = (AirMaterial)entity;
			if(material.getMaterialName().equals(name) && material.getSupplierId().equals(supplier)) {
				list.add(material);
			}
		}
		return list;
	}
	public  List<Entity> getEntityListBySupplier(List<Entity> all, String supplier){
		List<Entity> list = new ArrayList<Entity>();
		for(Entity entity:all) {
			AirMaterial material = (AirMaterial)entity;
			if( material.getSupplierId().equals(supplier)) {
				list.add(material);
			}
		}
		return list;
	}
	public  List<Entity> getEntityListByName(List<Entity> all, String name){
		List<Entity> list = new ArrayList<Entity>();
		for(Entity entity:all) {
			AirMaterial material = (AirMaterial)entity;
			if(material.getMaterialName().equals(name) ) {
				list.add(material);
			}
		}
		return list;
	}
	public  List<Entity> getEntityListByCommodity(List<Entity> all, String commodity){
		List<Entity> list = new ArrayList<Entity>();
		for(Entity entity:all) {
			AirMaterial material = (AirMaterial)entity;
			if(material.getCommodityNumber().equals(commodity) ) {
				list.add(material);
			}
		}
		return list;
	}
	
}
