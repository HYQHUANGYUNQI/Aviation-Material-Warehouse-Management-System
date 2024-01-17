package com.amwms.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amwms.entities.Entity;
import com.amwms.entities.User;

public class QueryUsersUtils {

	public static List<Entity> getEntiyList(ResultSet results) throws SQLException{
		List<Entity> list = new ArrayList<Entity>();
		while(results.next()) {
			User user = new User();
			user.setUserId(results.getString("userId"));
			user.setUserName(results.getString("userName"));
			user.setDuty(results.getString("duty"));
			user.setPwd(results.getString("pwd"));
			user.setStatement(results.getString("statement"));
			list.add(user);
		}
		return list;
	}
}
