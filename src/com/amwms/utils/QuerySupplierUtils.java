package com.amwms.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amwms.entities.Supplier;
import com.amwms.entities.Entity;

public class QuerySupplierUtils {

	public static List<Entity> getEntityList(ResultSet results) throws SQLException{
		List<Entity> list = new ArrayList<Entity>();
		while(results.next()) {
			Supplier supplier = new Supplier();
			supplier.setSupplierId(results.getString("supplierId"));
			supplier.setSupplierName(results.getString("supplierName"));
			supplier.setStatement(results.getString("statement"));
			
			list.add(supplier);
		}
		return list;
	}
}
