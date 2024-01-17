package com.amwms.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.amwms.entities.QueryResult;
import com.amwms.entities.User;
import com.amwms.operations.QueryOperation;

public class LoginServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(!req.getParameterMap().isEmpty() && !req.getParameter("username").equalsIgnoreCase("")) {
			String userId = req.getParameter("username");
			String password = req.getParameter("password");
			QueryOperation op = null;
			QueryResult res = null;
			JSONObject json = new JSONObject();
			try {
				op = new QueryOperation();
				op.queryUsersById(userId);
				res = (QueryResult) op.result();
				if(!res.getLists().isEmpty()) {
					User user = (User) res.getLists().get(0);
					if(user.getPwd().equals(password)) {
						json.put("flag", true);
						if(user.getDuty().equals("manager")) {
							json.put("identity", "manager");
						}else if(user.getDuty().equals("user")){
							json.put("identity", "user");
						}else if(user.getDuty().equals("root")) {
							json.put("identity", "root");
						}
					}else {
						json.put("flag", false);
						
					}									
				}
				resp.setContentType("text/html;charset=utf-8");
				resp.getWriter().print(json);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				req.getRequestDispatcher("/login.html").forward(req, resp);
			}
		}else {
			req.getRequestDispatcher("/login.html").forward(req, resp);
		}
	}
}
