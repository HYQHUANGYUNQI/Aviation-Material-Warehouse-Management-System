package com.amwms.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.amwms.entities.PutInApplicationForms;
import com.amwms.entities.RemovalApplicationForms;
import com.amwms.entities.UpdateResult;
import com.amwms.operations.InsertOperation;
import com.amwms.operations.QueryOperation;
import com.amwms.operations.UpdateOperation;
import com.amwms.utils.DateUtils;

public class ApplicationServlet extends HttpServlet{

	private UpdateResult result = new UpdateResult();
	private HttpSession session;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = req.getSession();
		if(!req.getParameterMap().isEmpty() && req.getParameter("apply").equals("inapply")) {
			try {
				addInApplicationForm(req.getParameterMap());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(!req.getParameterMap().isEmpty() && req.getParameter("apply").equals("outapply")) {
			try {
				addOutApplicationForm(req.getParameterMap());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		JSONObject json = new JSONObject();
		json.put("flag", result.getFlag());
		json.put("num", result.getLists().size());
		
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().print(json);
	}
	
	
	//需要做的事情： 1、添加申请表，申请表的状态设置成审核中
		//			2、申请表中的航材的信息改为申请入库中，用户管理中的内容不做修改
		//			3、用户管理区的航材的备注改为申请入库中
	public void addInApplicationForm(Map<String, String[]> parameterMap) throws SQLException {
		// TODO Auto-generated method stub
		InsertOperation op = new InsertOperation();
		PutInApplicationForms form = new PutInApplicationForms();
		UpdateOperation op2 = new UpdateOperation();
		UpdateOperation op3 = new UpdateOperation();
		InsertOperation op4 = new InsertOperation();
		form.setApplicant((String) session.getAttribute("userId"));
		form.setAuditor(null);
		form.setFormId(parameterMap.get("formId")[0]);
		form.setCommodityNumber(null);
		form.setCommodityName(parameterMap.get("storage")[0]);
		form.setReplenishDate(DateUtils.toDate(parameterMap.get("date")[0]));
		form.setStatement("审核中");
		form.setSupplierId(null);
		op.insertPutInApplicationForms(form);
		
		String[] serials = parameterMap.get("materials[]");
		for(String serial:serials) {	
			op2.updateMaterialState("申请入库中", serial);
			op3.updateUserManagmentStatement("申请入库中", (String) session.getAttribute("userId"), serial);
			op4.insertMaterialApplication(serial, parameterMap.get("formId")[0]);
		}
		result = (UpdateResult) op.result();	
	}

	//1、添加申请表
	//2、修改航材表中的状态为申请出库中
	//3、向用户管理区添加航材，状态为申请出库中
	//4、向航材和申请表关系中添加关系
	public void addOutApplicationForm(Map<String, String[]> parameterMap) throws SQLException {
		InsertOperation op = new InsertOperation();
		RemovalApplicationForms form = new RemovalApplicationForms();
		UpdateOperation op2 = new UpdateOperation();
		InsertOperation op3 = new InsertOperation();
		InsertOperation op4 = new InsertOperation();
		form.setApplicant((String) session.getAttribute("userId"));
		form.setAuditor(null);
		form.setFormId(parameterMap.get("formId")[0]);
		form.setCommodityNumber(null);
		form.setCommodityName(parameterMap.get("storage")[0]);
		form.setRemovalDate(DateUtils.toDate(parameterMap.get("date")[0]));
		form.setStatement("审核中");
		
		form.setCurrentLocationId(null);
		op.insertRemovalApplicationForms(form);
		
		String[] serials = parameterMap.get("materials[]");
		for(String serial : serials) {	
			op2.updateMaterialState("申请出库中", serial);
			op3.addMaterialOutApp(serial, (String) session.getAttribute("userId"));
			op4.insertMaterialApplication(serial, parameterMap.get("formId")[0]);
		}
		result = (UpdateResult) op.result();
	}
		
}
