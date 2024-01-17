package com.amwms.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amwms.entities.Entity;
import com.amwms.entities.RemovalApplicationForms;

public class QueryRemovalApplicationFormsUtils {

	public static List<Entity> getEntiyList(ResultSet results) throws SQLException{
		List<Entity> list = new ArrayList<Entity>();
		while(results.next()) {
			RemovalApplicationForms removal = new RemovalApplicationForms();
			removal.setFormId(results.getString("formId"));
			removal.setCommodityNumber(results.getString("commodityNumber"));
			removal.setCommodityName(results.getString("commodityName"));
			removal.setRemovalDate(results.getDate("removalDate"));
			removal.setApplicant(results.getString("applicant"));
			removal.setAuditor(results.getString("auditor"));
			removal.setCurrentLocationId(results.getString("currentLocationId"));
			removal.setStatement(results.getString("statement"));
			
			list.add(removal);
		}
		return list;
	}
}
