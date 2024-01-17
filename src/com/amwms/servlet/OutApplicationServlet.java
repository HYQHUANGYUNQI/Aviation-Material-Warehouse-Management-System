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
import com.amwms.utils.ApplicationUtils;

public class OutApplicationServlet extends HttpServlet{

	private List<Entity> allmaterials;
	private List<Entity> appliablematerials;
	private List<Entity> applicationmaterials;
	private List<JSONObject> list;
	private QueryOperation op;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(req.getParameterMap().isEmpty()) {
			try {
				op = new QueryOperation();
				op.queryAllAirMaterials();
				allmaterials = ((QueryResult)op.result()).getLists();
				appliablematerials = new ArrayList<Entity>();
				appliablematerials.addAll(allmaterials);
				
				applicationmaterials = new ArrayList<Entity>();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JSONObject json = new JSONObject();		
			json.put("formId", ApplicationUtils.getNewFormId());
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().print(json);
		}
		if(!req.getParameterMap().isEmpty() && req.getParameter("apply").equals("mytable")) {
			if(req.getParameterMap().containsKey("type")) {
				if(req.getParameter("type").equals("reset")) {
					appliablematerials.clear();
					appliablematerials.addAll(allmaterials);
				}else if(req.getParameter("type").equals("add")) {
					appliablematerials.add(getEntity(req.getParameter("serial")));
				}else if (req.getParameter("type").equals("del")) {
					removeEntity(req.getParameter("serial"), appliablematerials);
				}
			}
			list = getList(appliablematerials);
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().print(list);			
		}else if(!req.getParameterMap().isEmpty() && req.getParameter("apply").equals("table")) {
			if(req.getParameterMap().containsKey("type")) {	
				if(req.getParameter("type").equals("reset")) {
					applicationmaterials.clear();
				}else if(req.getParameter("type").equals("add")) {
					applicationmaterials.add(getEntity(req.getParameter("serial")));
				}else if (req.getParameter("type").equals("del")) {
					removeEntity(req.getParameter("serial"), applicationmaterials);
				}
			}
			list = getList(applicationmaterials);
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().print(list);
		}
		
	}
	
	public Entity getEntity(String serial) {
		
		for(Entity entity : allmaterials) {
			if(((AirMaterial) entity).getSerial().equals(serial)) {
				return entity;
			}
		}
		return null;
	}
	
	public void removeEntity(String serial,List<Entity> list) {
		for(int i = 0 ; i < list.size() ; i++) {
			if(((AirMaterial)list.get(i)).getSerial().equals(serial)) {
				list.remove(i);
			}
		}
	}
	
	public List<JSONObject> getList(List<Entity> list){
		List<JSONObject> lists = new  ArrayList<JSONObject>();
		for(Entity entity : list) {
			lists.add(new JSONObject((AirMaterial)entity));
		}
		return lists;
	}
	
	public OutApplicationServlet() {
		// TODO Auto-generated constructor stub
	}
}
