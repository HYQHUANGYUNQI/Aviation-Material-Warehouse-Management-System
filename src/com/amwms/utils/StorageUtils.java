package com.amwms.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.amwms.entities.AirMaterial;
import com.amwms.entities.Entity;
import com.amwms.entities.QueryResult;
import com.amwms.entities.Storage;
import com.amwms.operations.QueryOperation;

public class StorageUtils {

	public static JSONObject getStorageInfo(AirMaterial material) throws SQLException {
		JSONObject json = new JSONObject();
		QueryOperation op = new QueryOperation();
		String[] location = material.getLocationId().split("-");
		op.queryStorageById(location[0]);
		json.put("storageId", location[0]);
		Storage storage = (Storage)((QueryResult)op.result()).getLists().get(0);
		json.put("storageName",storage.getName());
		json.put("areaId", location[1]);
		json.put("locationId", location[2]+"-"+location[3] + "-" + location[4]);
		json.put("materialId", material.getSerial());
		json.put("state", "已入库");
		return json;
	}
	
	public static List<Entity> getEntityList(ResultSet results) throws SQLException{
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
	
	public static String getLocation(String stoNum, String areaNum) throws SQLException {
		PreparedStatement statement = JDBCUtils.getConnection().prepareStatement("SELECT locationId FROM airmaterials "
				+ "WHERE locationId  REGEXP ?");
		statement.setString(1, "^"+stoNum+"-"+areaNum);
		ResultSet res = statement.executeQuery();
		
		String max = getMaxLocation(res);
		String[] str = max.split("-");
		Integer i = Integer.valueOf(str[str.length-1]);
		if(i < 50) {
			str[str.length-1] = String.format("%02d", (i+1));
		}else {
			Integer j  = Integer.valueOf(str[str.length-2]);
			if(j < 6) {
				str[str.length-2] = String.format("%02d", j+1);
				str[str.length-1] = String.format("%02d", 1);
			}else {
				Integer k  = Integer.valueOf(str[str.length-3]);
				str[str.length-3] = String.format("%02d", k+1);
				str[str.length-2] = String.format("%02d", 1);
				str[str.length-1] = String.format("%02d", 1);
			}
			
		}
		StringBuilder sub = new StringBuilder();
		for(String strs:str) {
			sub.append(strs);
			sub.append("-");
		}
		sub.delete(sub.lastIndexOf("-"), sub.length());
		return sub.toString();
	}
	
	public static String getMaxLocation(ResultSet res) throws SQLException {
		List<String[]>locations = new ArrayList<String[]>();
		while(res.next()) {
			String[] location = res.getString("locationId").split("-");
			locations.add(location);
		}
		String[] max = locations.get(0);
		for(int j = 1; j < locations.size();j++) {
			String[] str1 =locations.get(j);
			for(int i = 2;i < 5; i++) {
				Integer inter1 = Integer.valueOf(str1[i]);
				Integer inter2 = Integer.valueOf(max[i]);
				if(inter1>inter2) {
					max = locations.get(j);
					break;
				}else if(inter2>inter1) {
					break;
				}
			}
		}
		
		StringBuilder sub = new StringBuilder();
		for(String str:max) {
			sub.append(str);
			sub.append("-");
		}
		sub.delete(sub.lastIndexOf("-"), sub.length());
		return sub.toString();

	}
}
