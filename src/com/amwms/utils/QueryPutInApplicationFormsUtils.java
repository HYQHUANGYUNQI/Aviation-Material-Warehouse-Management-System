package com.amwms.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amwms.entities.PutInApplicationForms;
import com.amwms.entities.Entity;

public class QueryPutInApplicationFormsUtils {

	public static List<Entity> getEntiyList(ResultSet results) throws SQLException{
		List<Entity> list = new ArrayList<Entity>();
		while(results.next()) {
			PutInApplicationForms put = new PutInApplicationForms();
			put.setFormId(results.getString("formId"));
			put.setCommodityNumber(results.getString("commodityNumber"));
			put.setCommodityName(results.getString("commodityName"));
			put.setReplenishDate(results.getDate("replenishDate"));
			put.setApplicant(results.getString("applicant"));
			put.setAuditor(results.getString("auditor"));
			put.setSupplierId(results.getString("supplierId"));
			put.setStatement(results.getString("statement"));
			
			list.add(put);
		}
		return list;
	}
}
