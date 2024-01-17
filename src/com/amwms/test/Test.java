package com.amwms.test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONObject;

import com.amwms.entities.AirMaterial;
import com.amwms.entities.ApplicationMaterials;
import com.amwms.entities.Entity;
import com.amwms.entities.QueryResult;
import com.amwms.operations.QueryOperation;
import com.amwms.utils.ApplicationUtils;
import com.amwms.utils.JDBCUtils;
import com.amwms.utils.StorageUtils;

public class Test {

	public static void main(String[] args) throws SQLException {
//		testCon();
//		testOperation();
//		System.out.println(StorageUtils.getLocation("01", "01"));
//		System.out.println(ApplicationUtils.getNewFormId());
//		System.out.println();
	}
	
	public static void testCon() {
		System.out.println(JDBCUtils.getConnection());
		PreparedStatement statement;
		try {
			statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM users");
			ResultSet results = statement.executeQuery(); 
			while(results.next()) {
				System.out.println(results.getString("userName")+"---"+results.getString("duty"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void testOperation() throws SQLException {
		QueryOperation query = new QueryOperation();
//		query.queryUsers();
//		query.queryAllAirMaterials();
//		query.queryAllPartstype();
//		query.queryAirMaterialsByCommodity("NAS15803R3");
//		query.queryAirMaterialsBySupplier("1002001SCR");
//		query.queryAirMaterialsBySerial("3535001011");
//		query.queryAirMaterialsByNameAndSupplier("五角螺丝", "1002001SCR");
//		query.queryAirMaterialsByNameAndSupplier("铆钉", "1002001SCR");
		
		QueryResult result = (QueryResult)query.result();
		List<Entity> list = result.getLists();
		for(Entity res:list) {
			System.out.println(new JSONObject((AirMaterial)res));
//			System.out.println(new JSONObject((User)res));
//			System.out.println(new JSONObject((Supplier)res));
//			System.out.println(new JSONObject((Partstype)res));
		}
	}	
	
}
