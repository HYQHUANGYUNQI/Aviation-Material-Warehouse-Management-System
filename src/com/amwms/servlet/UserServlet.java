package com.amwms.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.amwms.entities.Entity;
import com.amwms.entities.QueryResult;
import com.amwms.entities.User;
import com.amwms.operations.QueryOperation;

public class UserServlet extends HttpServlet{
	private HttpSession session;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = req.getSession();
		String userId = (String) session.getAttribute("userId");
		if(req.getParameterMap().isEmpty()) {
			QueryOperation op;
			QueryResult res = null;
			try {
				op = new QueryOperation();
				op.queryUsersById((String) session.getAttribute("userId"));
				res = (QueryResult) op.result();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			User user = (User)res.getLists().get(0);
			JSONObject json = new JSONObject(user);
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().print(json);
		}else if(req.getParameterMap().containsKey("identity")&&req.getParameter("identity").equals("manager")) {
			
			userReq(userId, resp);
		
		}else if(req.getParameterMap().containsKey("identity")&&req.getParameter("identity").equals("root")) {
			if(req.getParameterMap().containsKey("req")&&req.getParameter("req").equals("allUsers")) {				
				userListReq(resp);
			}else if(req.getParameterMap().containsKey("req")&&req.getParameter("req").equals("managers")) {
				
				ListReq("manager",resp);
			
			}else if(req.getParameterMap().containsKey("req")&&req.getParameter("req").equals("users")) {
				
				ListReq("user",resp);
			
			}else if(req.getParameterMap().containsKey("req")&&req.getParameter("req").equals("user")&&req.getParameterMap().containsKey("userId")) {
				userReq(req.getParameter("userId"),resp);
			}
		}
	}
	

	private void ListReq(String duty,HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		try {
			QueryOperation op = new QueryOperation();
			op.queryUsers(duty);
			QueryResult result = (QueryResult) op.result();
			List<JSONObject> list = new ArrayList<JSONObject>();
			for(Entity entity : result.getLists()) {
				list.add(new JSONObject((User)entity));
			}
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().print(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void userReq(String userId, HttpServletResponse resp) throws IOException {
		try {
			QueryOperation op = new QueryOperation();
			op.queryUsersById(userId);
			QueryResult result = (QueryResult) op.result();
			List<JSONObject> list = new ArrayList<JSONObject>();
			for(Entity entity : result.getLists()) {
				list.add(new JSONObject((User)entity));
			}
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().print(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void userListReq(HttpServletResponse resp) throws IOException {
		try {
			QueryOperation op = new QueryOperation();
			op.queryAllUsers();
			QueryResult result = (QueryResult) op.result();
			List<JSONObject> list = new ArrayList<JSONObject>();
			for(Entity entity : result.getLists()) {
				list.add(new JSONObject((User)entity));
			}
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().print(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
