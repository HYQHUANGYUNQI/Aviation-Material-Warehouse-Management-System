package com.amwms.utils;

/**
 * @author m^_^m
 *
 * @Statement: 查询报废信息
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amwms.entities.Entity;
import com.amwms.entities.Scrap;

public class QueryScrapUtils {
	public static List<Entity> getEntiyList(ResultSet results) throws SQLException{
		List<Entity> list = new ArrayList<Entity>();
		while(results.next()) {
			Scrap material = new Scrap();
		    material.setScrapID(results.getString("scrapID"));
			material.setScrapName(results.getString("scrapName"));
			material.setStatement(results.getString("statement"));
			
			list.add(material);
		}
		return list;
	}

}
