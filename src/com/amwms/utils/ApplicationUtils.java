package com.amwms.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationUtils {

	public static String getNewFormId() {
		PreparedStatement statment01;
		PreparedStatement statment02;
		PreparedStatement statment03;
		ResultSet res1 = null;
		ResultSet res2 = null;
		ResultSet res3 = null;
		Long i = null;
		Long j = null;
		Long k = null;
		try {
			statment01 = JDBCUtils.getConnection().prepareStatement("SELECT MAX(formId) FROM putinapplicationforms;");
			statment02 = JDBCUtils.getConnection().prepareStatement("SELECT MAX(formId) FROM removalapplicationforms;");
			statment03 = JDBCUtils.getConnection().prepareStatement("SELECT MAX(formId) FROM transferapplicationforms;");
			
			res1 = statment01.executeQuery();
			res2 = statment02.executeQuery();
			res3 = statment03.executeQuery();
			res1.next();
			res2.next();
			res3.next();
			i = Long.parseLong(res1.getString("MAX(formId)").trim());
			j = Long.parseLong(res1.getString("MAX(formId)").trim());
			k = Long.parseLong(res1.getString("MAX(formId)").trim());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		Long temp = i;
		long max = j>k?j:k;
		if(temp < max) {
			temp = max;
		}
		
		return String.format("%020d", (temp+1));
	}
}
