package com.amwms.utils;

/**
 * @author m^_^m
 * 
 * @Statement: 查询报废申请单
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amwms.entities.Entity;
import com.amwms.entities.ScrapApplicationForm;

public class QueryScrapApplicationFormUtils {
	public static List<Entity> getEntiyList(ResultSet results) throws SQLException{
		List<Entity> list = new ArrayList<Entity>();
		while(results.next()) {
			ScrapApplicationForm material = new ScrapApplicationForm();
			material.setFormID(results.getString("formID"));
			material.setCommodityNumber(results.getString("commodityNumber"));
			material.setCommodityName(results.getString("commodityName"));
			material.setReplenishDate(results.getDate("replenishDate"));
			material.setApplicant(results.getString("applicant"));
			material.setAuditor(results.getString("auditor"));
			material.setQualityInspector(results.getString("qualityInspector"));
			material.setCause(results.getString("cause"));
			material.setStatement(results.getString("statement"));
			
			list.add(material);
		}
		return list;
	}
}
