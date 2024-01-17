package com.amwms.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amwms.entities.AirMaterial;
import com.amwms.entities.Entity;
import com.amwms.entities.Storage;

public class QueryStorageUtils {
	public static List<Entity> getEntiyList(ResultSet results) throws SQLException{
		List<Entity> list = new ArrayList<Entity>();
		while(results.next()) {
			Storage storage = new Storage();
			storage.setId(results.getString("storageId"));
			storage.setName(results.getString("storageName"));
			storage.setCapacity(results.getInt("storageCapacity"));
			storage.setCurCapacity(results.getInt("curCapacity"));
			storage.setStatement(results.getString("statement"));
			
			list.add(storage);
		}
		return list;
	}
}
