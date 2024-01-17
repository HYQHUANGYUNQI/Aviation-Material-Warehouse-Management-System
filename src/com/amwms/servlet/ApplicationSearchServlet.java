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

public class ApplicationSearchServlet extends HttpServlet{
	
	private QueryOperation op = null;
	private QueryResult result = null;
	private HttpSession session = null;
	private List<JSONObject> list = null;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = req.getSession();
		try {
			op = new QueryOperation();
			list = new ArrayList<JSONObject>();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!req.getParameterMap().isEmpty()&&req.getParameterMap().containsKey("apply")) {
			if(req.getParameter("apply").equals("inapplication")) {
				inApplicationOperation(req);
			}else if(req.getParameter("apply").equals("outapplication")) {
				outApplicationOperation(req);
			}
			
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().print(list);
			list.clear();
		}
	}
	
	public void inApplicationOperation(HttpServletRequest req) {
		String formId = req.getParameter("formId");
		if(req.getParameterMap().containsKey("type")&&req.getParameter("type").equals("form")) {
			try {
				op.queryPutInApplicationFormById(formId);
				result = (QueryResult) op.result();
				for(Entity entity : result.getLists()) {
					PutInApplicationForms form = (PutInApplicationForms)entity;
					form.setApplicant(op.queryUserNameById(form.getApplicant()));
					list.add(new JSONObject(form));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(req.getParameterMap().containsKey("type")&&req.getParameter("type").equals("material")) {
			queryMaterial(formId);
		}else if(req.getParameterMap().containsKey("operate")&&req.getParameter("operate").equals("search")) {
			try {
				queryInApplicationforms(req);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public void outApplicationOperation(HttpServletRequest req) {
		String formId = req.getParameter("formId");
		if(req.getParameterMap().containsKey("type")&&req.getParameter("type").equals("form")) {
			try {
				op.queryRemovalApplicationFormById(formId);
				result = (QueryResult) op.result();
				for(Entity entity : result.getLists()) {
					RemovalApplicationForms form = (RemovalApplicationForms)entity;
					form.setApplicant(op.queryUserNameById(form.getApplicant()));
					list.add(new JSONObject(form));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(req.getParameterMap().containsKey("type")&&req.getParameter("type").equals("material")) {
			queryMaterial(formId);
		}else if(req.getParameterMap().containsKey("operate")&&req.getParameter("operate").equals("search")) {
			try {
				queryOutApplicationforms(req);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void queryMaterial(String formId) {
		try {
			op.queryAllMaterialsOfApp(formId);
			result = (QueryResult) op.result();
			for(Entity entity : result.getLists()) {
				list.add(new JSONObject((AirMaterial)entity));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void queryOutApplicationforms(HttpServletRequest req) throws SQLException {
		if(!req.getParameter("formId").trim().equals("")) {
			op.queryRemovalApplicationFormById(req.getParameter("formId").trim());
		}else if(!req.getParameter("applicant").trim().equals("")) {
			op.queryRemovalApplicationFormByApplicant(req.getParameter("applicant"));
		}else if(!req.getParameter("state").trim().equals("")) {
			op.queryAllRemovalApplicationFormsIn(op.queryManagerDutyStorage((String) session.getAttribute("userId")),req.getParameter("state"));
		}
		result = (QueryResult)op.result();
		for(Entity entity : result.getLists()) {
			RemovalApplicationForms form = (RemovalApplicationForms)entity;
			form.setApplicant(op.queryUserNameById(form.getApplicant()));
			list.add(new JSONObject(form));
		}
	}
	
	public void queryInApplicationforms(HttpServletRequest req) throws SQLException {
		if(!req.getParameter("formId").trim().equals("")) {
			op.queryPutInApplicationFormById(req.getParameter("formId").trim());
		}else if(!req.getParameter("applicant").trim().equals("")) {
			op.queryPutInApplicationFormByApplicant(req.getParameter("applicant"));
		}else if(!req.getParameter("state").trim().equals("")) {
			op.queryAllPutInApplicationFormsIn(op.queryManagerDutyStorage((String) session.getAttribute("userId")),req.getParameter("state"));
		}
		
		result = (QueryResult) op.result();
		for(Entity entity : result.getLists()) {
			PutInApplicationForms form = (PutInApplicationForms)entity;
			form.setApplicant(op.queryUserNameById(form.getApplicant()));
			list.add(new JSONObject(form));
		}
	}
	
}
