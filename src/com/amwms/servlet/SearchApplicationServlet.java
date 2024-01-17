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
import com.amwms.utils.DateUtils;

public class SearchApplicationServlet extends HttpServlet{
	
	private HttpSession session;
	private List<JSONObject> list;
	private String userId;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = req.getSession();
		userId = (String) session.getAttribute("userId");
		list = new ArrayList<JSONObject>();
		
		if(!req.getParameterMap().isEmpty()&&req.getParameterMap().containsKey("apply")) {
			if(req.getParameter("apply").equals("inapplication")) {
				searchInApplication(req);
			}else if(req.getParameter("apply").equals("outapplication")) {
				searchOutApplication(req);
			}
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().print(list);
		}
		
	}
	
	public void searchInApplication(HttpServletRequest req) {
		List<Entity> lists = new ArrayList<Entity>();
		try {
			QueryOperation op = new QueryOperation();
			op.queryPutInApplicationFormByApplicant(userId);
			QueryResult res = (QueryResult) op.result();
			lists.addAll(res.getLists());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(req.getParameterMap().containsKey("formId")&& !req.getParameter("formId").trim().equals("")) {
			for(Entity entity:lists) {
				PutInApplicationForms form = (PutInApplicationForms) entity;
				if(form.getFormId().equals(req.getParameter("formId"))) {
					list.add(new JSONObject(form));
					return;
				}
			}
		}else if(!req.getParameter("state").trim().equals("") && !req.getParameter("date").trim().equals("")) {
			for(Entity entity:lists) {
				PutInApplicationForms form = (PutInApplicationForms) entity;
				if(form.getStatement().equals(req.getParameter("state"))
						&&form.getReplenishDate().equals(DateUtils.toDate(req.getParameter("date")))) {
					list.add(new JSONObject(form));
				}
			}
		}else if(!req.getParameter("state").trim().equals("") && !req.getParameter("storage").trim().equals("")) {
			for(Entity entity:lists) {
				PutInApplicationForms form = (PutInApplicationForms) entity;
				if(form.getStatement().equals(req.getParameter("state"))
						&&form.getCommodityName().equals(req.getParameter("storage"))) {
					list.add(new JSONObject(form));
				}
			}
		}else if(req.getParameterMap().containsKey("state")&& !req.getParameter("state").trim().equals("")){
			for(Entity entity:lists) {
				PutInApplicationForms form = (PutInApplicationForms) entity;
				if(form.getStatement().equals(req.getParameter("state"))) {
					list.add(new JSONObject(form));
				}
			}
		}else if(req.getParameterMap().containsKey("date")&& !req.getParameter("date").trim().equals("")) {
			for(Entity entity:lists) {
				PutInApplicationForms form = (PutInApplicationForms) entity;
				if(form.getReplenishDate().equals(DateUtils.toDate(req.getParameter("date")))) {
					list.add(new JSONObject(form));
				}
			}
		}else if(req.getParameterMap().containsKey("storage")&& !req.getParameter("storage").trim().equals("")) {
			for(Entity entity:lists) {
				PutInApplicationForms form = (PutInApplicationForms) entity;
				if(form.getCommodityName().equals(req.getParameter("storage"))) {
					list.add(new JSONObject(form));
				}
			}
		}
		
	}
	
	public void searchOutApplication(HttpServletRequest req) {
		List<Entity> lists = new ArrayList<Entity>();
		try {
			QueryOperation op = new QueryOperation();
			op.queryRemovalApplicationFormByApplicant(userId);
			QueryResult res = (QueryResult) op.result();
			lists.addAll(res.getLists());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(req.getParameterMap().containsKey("formId")&& !req.getParameter("formId").trim().equals("")) {
			for(Entity entity:lists) {
				RemovalApplicationForms form = (RemovalApplicationForms) entity;
				if(form.getFormId().equals(req.getParameter("formId"))) {
					list.add(new JSONObject(form));
					return;
				}
			}
		}else if(req.getParameterMap().containsKey("state")&& !req.getParameter("state").trim().equals("")){
			for(Entity entity:lists) {
				RemovalApplicationForms form = (RemovalApplicationForms) entity;
				if(form.getStatement().equals(req.getParameter("state"))) {
					list.add(new JSONObject(form));
				}
			}
		}else if(req.getParameterMap().containsKey("date")&& !req.getParameter("date").trim().equals("")) {
			for(Entity entity:lists) {
				RemovalApplicationForms form = (RemovalApplicationForms) entity;
				if(form.getRemovalDate().equals(DateUtils.toDate(req.getParameter("date")))) {
					list.add(new JSONObject(form));
				}
			}
		}
	}
}
