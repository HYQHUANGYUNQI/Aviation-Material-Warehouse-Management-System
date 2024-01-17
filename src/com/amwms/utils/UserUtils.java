package com.amwms.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserUtils {
	public static String getNewUserId() throws SQLException {
		PreparedStatement statment = JDBCUtils.getConnection().prepareStatement("SELECT MAX(userId) FROM users");
		ResultSet res = statment.executeQuery();
		
		if(res.next()) {
			String cur = res.getString("max(userId)");
			int i = Integer.valueOf(cur);
			String newId = String.format("%010d", (i+1));
			return newId;
		}
		
		return "**********";
	}
}
