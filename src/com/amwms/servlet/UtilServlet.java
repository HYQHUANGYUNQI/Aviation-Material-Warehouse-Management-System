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

import com.amwms.entities.AirMaterial;
import com.amwms.entities.Entity;
import com.amwms.entities.PutInApplicationForms;
import com.amwms.entities.QueryResult;
import com.amwms.entities.RemovalApplicationForms;
import com.amwms.operations.QueryOperation;
import com.amwms.utils.UserManagementUtils;
import com.mysql.cj.Session;

public class UtilServlet extends HttpServlet{

	private QueryOperation op;
	private QueryResult result;
	private List<JSONObject>list;
	private HttpSession session;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = req.getSession();	
		list = new ArrayList<JSONObject>();
		if(req.getParameterMap().isEmpty()) {
		}else if(!req.getParameterMap().isEmpty()&&req.getParameterMap().containsKey("apply")) {
			if(req.getParameter("apply").equals("inApplication")) {
				try {
					op = new QueryOperation();
					op.queryPutInApplicationFormByApplicant((String) session.getAttribute("userId"));
					result = (QueryResult) op.result();
					if(!result.getLists().isEmpty()) {						
						for(Entity entity : result.getLists()) {
							list.add(new JSONObject((PutInApplicationForms)entity));
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if (req.getParameter("apply").equals("outApplication")) {
				try {
					op = new QueryOperation();
					op.queryRemovalApplicationFormByApplicant((String) session.getAttribute("userId"));
					result = (QueryResult) op.result();
					if(!result.getLists().isEmpty()) {
						
						for(Entity entity:result.getLists()) {
							JSONObject json = new JSONObject((RemovalApplicationForms)entity);
							list.add(json);
						}
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
	
	public UtilServlet() {
		// TODO Auto-generated constructor stub
		
	}
}
