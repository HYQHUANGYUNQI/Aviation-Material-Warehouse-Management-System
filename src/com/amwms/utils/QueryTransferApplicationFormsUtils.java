package com.amwms.utils;

import java.sql.Date;

/**
 * 
 * @author m^_^m
 *
 * @Statement: 查询移库申请单信息
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amwms.entities.TransferApplicationForms;
import com.amwms.entities.Entity;

public class QueryTransferApplicationFormsUtils {
	public static List<Entity> getEntiyList(ResultSet results) throws SQLException{
		List<Entity> list = new ArrayList<Entity>();
		while(results.next()) {
			TransferApplicationForms form = new TransferApplicationForms();
		    form.setFormID(results.getString("formID"));
			form.setCommodityNumber(results.getString("commodityNumber"));
			form.setCommodityName(results.getString("commodityName"));
			form.setTransferDate(results.getDate("transferDate"));
			form.setApplicant(results.getString("applicant"));
			form.setAuditor(results.getString("auditor"));
			form.setTargetLocation(results.getString("targetLocation"));
			form.setOrginalLocation(results.getString("orginalLocation"));
			form.setStatement(results.getString("statement"));
			
			list.add(form);
		}
		return list;
	}

}
