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
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.amwms.entities.AirMaterial;
import com.amwms.entities.Entity;
import com.amwms.entities.QueryResult;
import com.amwms.operations.QueryOperation;
import com.amwms.utils.QueryAirMaterialUtils;

public class PersonalMaterialServlet extends HttpServlet{
	private List<Entity> allMaterials = null;
	private List<Entity> materials = null;
	private List<JSONObject> list = null;
	HttpSession session = null;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		list = new ArrayList<JSONObject>();
		session = req.getSession();

		if(!req.getParameterMap().isEmpty()&&!req.getParameterMap().containsKey("type")) {
			try {
				queryMaterialOfUser(req.getParameterMap());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(req.getParameterMap().containsKey("type") && req.getParameter("type").equals("del")) {
			for(int i = 0 ; i < materials.size() ;  i ++) {
				if(((AirMaterial)materials.get(i)).getSerial().equals(req.getParameter("serial"))) {
					materials.remove(i);
					break;
				};
			}
		}else if(req.getParameterMap().containsKey("type") && req.getParameter("type").equals("add")) {
			try {
				QueryOperation op = new QueryOperation();
				op.queryAirMaterialsBySerial(req.getParameter("serial"));
				QueryResult result = (QueryResult) op.result();
				
				materials.add(result.getLists().get(0));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(req.getParameterMap().containsKey("type") && req.getParameter("type").equals("reset")) {
			queryAllMaterialsOf((String)session.getAttribute("userId"));
		}else {
			queryAllMaterialsOf((String)session.getAttribute("userId"));
		}
					

		for(Entity entity:materials) {
			list.add(new JSONObject((AirMaterial)entity));
		}
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().print(list);
		
	}
	
	public void queryAllMaterialsOf(String userId) {
		allMaterials = new ArrayList<Entity>();
		materials = new ArrayList<Entity>();
		//通过query得到相应的结果
		QueryOperation query = null;
		try {
			query = new QueryOperation();
			query.queryAllMaterialsOfUser(userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		QueryResult result = (QueryResult)query.result();
		allMaterials = result.getLists();
		materials = result.getLists();
	}
	
	/**
	  *  根据请求参数查询结果
	 * @param map :parameterMap，请求参数的map集合;
	 * @throws SQLException 
	 * 
	 */
	public void queryMaterialOfUser(Map<String,String[]> map) throws SQLException {
		materials = new ArrayList<Entity>();
		QueryAirMaterialUtils utils = new QueryAirMaterialUtils();

		if(!map.get("serial")[0].equalsIgnoreCase("")) {			
			materials = utils.getEntityListBySerial(allMaterials, map.get("serial")[0]);
		}else if(!map.get("materialName")[0].equalsIgnoreCase("") && !map.get("supplierId")[0].equals("")){
			materials = utils.getEntityListByNameAndSupplier(allMaterials, map.get("materialName")[0], map.get("supplierId")[0]);
		}else if(!map.get("materialName")[0].equalsIgnoreCase("")) {
			materials = utils.getEntityListByName(allMaterials, map.get("materialName")[0]);
		}else if(!map.get("commodityNumber")[0].equalsIgnoreCase("")) {
			materials = utils.getEntityListByCommodity(allMaterials, map.get("commodityNumber")[0]);
		}else if(!map.get("partsTypeId")[0].equalsIgnoreCase("")) {
			materials = utils.getEntityListByType(allMaterials, map.get("partsTypeId")[0]);
		}else if(!map.get("supplierId")[0].equalsIgnoreCase("")){
			materials = utils.getEntityListBySupplier(allMaterials, map.get("supplierId")[0]);
		}else {
			return;
		}
		
	}
}
