package com.amwms.utils;

import java.util.ArrayList;
import java.util.List;

public class UserManagementUtils {
	private String userId;
	private List<String> materials;
	public UserManagementUtils() {
		// TODO Auto-generated constructor stub
		materials = new ArrayList<String>();
	}
	public UserManagementUtils(String userId) {
		this.userId = userId;
		materials = new ArrayList<String>();
	}
	
	public void addMaterial(String serialNumber) {
		materials.add(serialNumber);
	}
	
	public void removeMaterial(String serialNumber) {
		materials.remove(serialNumber);
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<String> getMaterials() {
		return materials;
	}
	public void setMaterials(List<String> materials) {
		this.materials = materials;
	}
	
}
