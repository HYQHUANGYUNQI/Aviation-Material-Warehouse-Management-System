package com.amwms.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.amwms.entities.AirMaterial;
import com.amwms.entities.Entity;
import com.amwms.entities.QueryResult;
import com.amwms.operations.QueryOperation;
import com.amwms.utils.StorageUtils;

public class StorageServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		QueryOperation op = null;
		QueryResult result = null;
		List<Entity> list = null;
		List<JSONObject> lists = new ArrayList<JSONObject>();
		try {
			op = new QueryOperation();
			op.queryAllAirMaterials();
			result = (QueryResult) op.result();
			list = result.getLists();
			for(Entity entity : list) {
				lists.add(StorageUtils.getStorageInfo((AirMaterial)entity));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().print(lists);
	}
}
