package com.amwms.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.amwms.entities.UpdateResult;
import com.amwms.operations.UpdateOperation;

public class UserArrangeServlet extends HttpServlet{

	private UpdateOperation uop = null;
	private UpdateResult res = null;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		uop = new UpdateOperation();
		if(!req.getParameterMap().isEmpty()&&req.getParameterMap().containsKey("operate")&&req.getParameter("operate").equals("update")) {
			if(req.getParameterMap().containsKey("duty")&&req.getParameter("duty").equals("manager")) {
				try {
					uop.updateUserDuty("manager",req.getParameter("userId"));
					res = (UpdateResult) uop.result();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(req.getParameterMap().containsKey("duty")&&req.getParameter("duty").equals("user")) {
				try {
					uop.updateUserDuty("user",req.getParameter("userId"));
					uop.updateUserStatement(null, req.getParameter("userId"));
					res = (UpdateResult) uop.result();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(req.getParameterMap().containsKey("storageArrange")) {
				try {
					uop.updateUserStatement(req.getParameter("storageArrange"), req.getParameter("userId"));
					res = (UpdateResult) uop.result();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		JSONObject json = new JSONObject();
		json.put("flag", res.getFlag());
		json.put("num", res.getLists().size());
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().print(json);
		
	}
}
