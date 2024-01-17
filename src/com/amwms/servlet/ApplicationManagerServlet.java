package com.amwms.servlet;

import java.io.IOException;
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
import com.amwms.entities.PutInApplicationForms;
import com.amwms.entities.QueryResult;
import com.amwms.entities.RemovalApplicationForms;
import com.amwms.operations.QueryOperation;

public class ApplicationManagerServlet extends HttpServlet{
	
	private QueryOperation op ;
	private HttpSession session;
	private QueryResult result;
	private List<JSONObject> list;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			op = new QueryOperation();
			list = new ArrayList<JSONObject>();
			session = req.getSession();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!req.getParameterMap().isEmpty()&&req.getParameterMap().containsKey("apply")) {
			if(req.getParameter("apply").equals("inapplication")) {
				try {
					
					op.queryAllPutInApplicationFormsOf(op.queryManagerDutyStorage((String) session.getAttribute("userId")));
					list.clear();
					result = (QueryResult) op.result();
					for(Entity entity : result.getLists()) {
						list.add(new JSONObject((PutInApplicationForms)entity));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(req.getParameter("apply").equals("outapplication")) {
				try {
					op.queryAllRemovalApplicationFormsOf(op.queryManagerDutyStorage((String) session.getAttribute("userId")));
					list.clear();
					result = (QueryResult) op.result();
					for(Entity entity : result.getLists()) {
						list.add(new JSONObject((RemovalApplicationForms)entity));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().print(list);
			list.clear();
		}
	}
}
