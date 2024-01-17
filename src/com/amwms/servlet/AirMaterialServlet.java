package com.amwms.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.amwms.entities.AirMaterial;
import com.amwms.entities.Entity;
import com.amwms.entities.QueryResult;
import com.amwms.operations.QueryOperation;

public class AirMaterialServlet extends HttpServlet{
	private List<Entity> allMaterials = null;
	private List<JSONObject> list = null ;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		list = new ArrayList<JSONObject>();
		if(!req.getParameterMap().isEmpty()) {
			try {
				queryMaterial(req.getParameterMap());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			queryAllMaterials();
		}
					
		
		for(Entity entity:allMaterials) {
			list.add(new JSONObject((AirMaterial)entity));
		}
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().print(list);
		
	}
	
	public void queryAllMaterials() {
		allMaterials = new ArrayList<Entity>();
		//通过query得到相应的结果
		QueryOperation query = null;
		try {
			query = new QueryOperation();
			query.queryAllAirMaterials();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		QueryResult result = (QueryResult)query.result();
		allMaterials = result.getLists();
	}
	
	/**
	  *  根据请求参数查询结果
	 * @param map :parameterMap，请求参数的map集合;
	 * @throws SQLException 
	 * 
	 */
	public void queryMaterial(Map<String,String[]> map) throws SQLException {
		allMaterials = new ArrayList<Entity>();
		QueryOperation operation = new QueryOperation();
		QueryResult result = null;
		
		if(!map.get("serial")[0].equalsIgnoreCase("")) {
			operation.queryAirMaterialsBySerial(map.get("serial")[0]);
		}else if(!map.get("materialName")[0].equalsIgnoreCase("") && !map.get("supplierId")[0].equals("")){
			operation.queryAirMaterialsByNameAndSupplier(map.get("materialName")[0], map.get("supplierId")[0]);
		}else if(!map.get("materialName")[0].equalsIgnoreCase("")) {
			operation.queryAirMaterialsByName(map.get("materialName")[0]);
		}else if(!map.get("commodityNumber")[0].equalsIgnoreCase("")) {
			operation.queryAirMaterialsByCommodity(map.get("commodityNumber")[0]);
		}else if(!map.get("partsTypeId")[0].equalsIgnoreCase("")) {
			
			operation.queryAirMaterialsByType(map.get("partsTypeId")[0]);
		}else {
			operation.queryAirMaterialsBySerial("##########");//不存在的航材
		}
		
		result = (QueryResult)operation.result();
		allMaterials.addAll(result.getLists());

	}
}
