package com.amwms.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.amwms.entities.UpdateResult;
import com.amwms.entities.User;
import com.amwms.operations.InsertOperation;

public class RegisterServlet extends HttpServlet{

	private String userName;
	private String userpwd;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		InsertOperation op ;
		UpdateResult res = new UpdateResult();
		if(!req.getParameterMap().isEmpty()) {
			userName = req.getParameter("username");
			userpwd = req.getParameter("password");
			User user = new User();
			user.setUserName(userName);
			user.setPwd(userpwd);
			op = new InsertOperation();
			try {
				op.insertUser(user);
				res = (UpdateResult) op.result();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(res.getFlag()) {
			JSONObject json = new JSONObject();
			json.put("flag", true);
			json.put("user", new JSONObject((User)res.getLists().get(0)));
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().print(json);
		}
	}
}
