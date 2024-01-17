package com.amwms.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amwms.entities.Partstype;
import com.amwms.entities.Entity;
/**
 * 
 * @author lenovo
 *
 */
public class QueryPartstypeUtils {

	public static List<Entity> getEntityList(ResultSet results) throws SQLException{
		List<Entity> list = new ArrayList<Entity>();
		while(results.next()) {
			Partstype parts = new Partstype();
			parts.setPartstypeId(results.getString("partstypeId"));
			parts.setPartypeName(results.getString("partypeName"));
			parts.setStatement(results.getString("statement"));
			
			list.add(parts);
			
		}
		return list;
	}
}
