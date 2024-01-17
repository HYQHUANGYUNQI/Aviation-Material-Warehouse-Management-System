package com.amwms.entities;

import java.util.ArrayList;
import java.util.List;

public class ApplicationMaterials extends Entity{
	private List<String> materials;
	private String formId;
	public List<String> getMaterials() {
		return materials;
	}
	public void setMaterials(List<String> materials) {
		this.materials = materials;
	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public ApplicationMaterials(List<String> materials, String formId) {
		super();
		this.materials = materials;
		this.formId = formId;
	}
	
	public ApplicationMaterials() {
		// TODO Auto-generated constructor stub
		materials = new ArrayList<String>();
	}
}
