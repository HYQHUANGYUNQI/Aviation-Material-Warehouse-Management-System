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

import com.amwms.entities.Entity;
import com.amwms.entities.QueryResult;
import com.amwms.entities.Storage;
import com.amwms.operations.QueryOperation;

public class StorageArrangeServlet extends HttpServlet{

	private QueryOperation op = null;
	private QueryResult res = null;
	private List<JSONObject> list = null;
	private List<Entity> allStorage = null;
	private List<Entity> addedStorage = null;
	private List<Entity> addableStorage = null;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(req.getParameterMap().isEmpty()) {			
			try {
				allStorage.clear();
				op = new QueryOperation();
				op.queryAllStorage();
				res = (QueryResult) op.result();
				allStorage.addAll(res.getLists());
				
				addableStorage = new ArrayList<Entity>();
				addableStorage.addAll(allStorage);
				addedStorage = new ArrayList<Entity>();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if(!req.getParameterMap().isEmpty()&&req.getParameterMap().containsKey("req")) {
			if(req.getParameter("req").equals("addablestorage")) {
				if(req.getParameterMap().containsKey("operate")) {
					if(req.getParameter("operate").equals("add")) {
						addableStorage.add(get(req.getParameter("id")));
					}else if(req.getParameter("operate").equals("del")) {
						del(addableStorage, req.getParameter("id"));
					}else if(req.getParameter("operate").equals("reset")) {
						addableStorage = new ArrayList<Entity>();
						addableStorage.addAll(allStorage);
					}
				}
				
				list = new ArrayList<JSONObject>();				
				for(Entity entity : addableStorage) {
					Storage storage = (Storage) entity;
					list.add(new JSONObject(storage));
				}
				resp.setContentType("text/html;charset=utf-8");
				resp.getWriter().print(list);
			}else if(req.getParameter("req").equals("addedstorage")) {
				if(req.getParameterMap().containsKey("operate")) {					
					if(req.getParameter("operate").equals("add")){
						addedStorage.add(get(req.getParameter("id")));
					}else if(req.getParameter("operate").equals("del")) {
						del(addedStorage, req.getParameter("id"));
					}else if(req.getParameter("operate").equals("reset")){
						addedStorage.clear();
					}
				}
					
				list = new ArrayList<JSONObject>();				
				for(Entity entity : addedStorage) {
					Storage storage = (Storage) entity;
					list.add(new JSONObject(storage));
				}
				
				resp.setContentType("text/html;charset=utf-8");
				resp.getWriter().print(list);
			}
		}
	}
	
	public StorageArrangeServlet() {
		// TODO Auto-generated constructor stub
		addableStorage = new ArrayList<Entity>();
		allStorage = new ArrayList<Entity>();
		addedStorage = new ArrayList<Entity>();
	}
	
	public Entity get(String id) {
		for(Entity entity : allStorage) {
			Storage storage = (Storage) entity;
			if(storage.getId().equals(id)) {
				return entity;
			}
		}
		return null;
	}
	
	public void del(List<Entity> list, String id) {
		for(int i = 0 ; i < list.size();i++) {
			Storage storage = (Storage) list.get(i);
			if(storage.getId().equals(id)) {
				list.remove(i);
				return;
			}
		}
	}
}
