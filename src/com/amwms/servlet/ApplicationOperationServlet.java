package com.amwms.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amwms.entities.AirMaterial;
import com.amwms.entities.ApplicationMaterials;
import com.amwms.entities.Entity;
import com.amwms.entities.PutInApplicationForms;
import com.amwms.entities.QueryResult;
import com.amwms.entities.UpdateResult;
import com.amwms.operations.DeleteOperation;
import com.amwms.operations.QueryOperation;
import com.amwms.operations.UpdateOperation;
import com.amwms.utils.StorageUtils;

public class ApplicationOperationServlet extends HttpServlet{

	private UpdateOperation op = null;
	private UpdateResult result = null;
	private HttpSession session ;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("post");
		op = new UpdateOperation();
		session = req.getSession();
		if(!req.getParameterMap().isEmpty()) {
			if(req.getParameterMap().containsKey("apply")&&req.getParameter("apply").equals("inapplication")) {
				try {
					inapplicationOperation(req);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(req.getParameterMap().containsKey("apply")&&req.getParameter("apply").equals("outapplication")){
				try {
					outapplicationOperation(req);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		result = (UpdateResult) op.result();
		if(result.getFlag()) {
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().print("success");
		}
	}
	
	//1、通过申请、更新审核人信息
	//2、更新航材信息、
	//3、更新用户管理区信息
	public void inapplicationOperation(HttpServletRequest req) throws SQLException {
		String formId = req.getParameter("formId");
		QueryOperation op1 = new QueryOperation();
		op1.queryPutInApplicationFormById(formId);
		PutInApplicationForms form = (PutInApplicationForms)((QueryResult)op1.result()).getLists().get(0);
		QueryOperation qop = null;
		UpdateOperation uop = new UpdateOperation();
		if(req.getParameterMap().containsKey("operate")&&req.getParameter("operate").equals("access")) {
			qop = new QueryOperation();
			qop.queryAllApplicationOf(formId);
			QueryResult res = (QueryResult) qop.result();
			ApplicationMaterials materials= (ApplicationMaterials) res.getLists().get(0);

			for(String material:materials.getMaterials()) {
				uop.updateMaterialSpecifaction("标准", material);
				uop.updateMaterialLocationId(StorageUtils.getLocation(form.getCommodityName(), req.getParameter("area")), material);
				uop.updateMaterialState("已入库", material);
				uop.updateUserManagmentStatement("已入库", qop.queryApplicantOfInApplication(formId), material);
			}
			op.updateStateInApplication(formId, "已通过");
		}else if(req.getParameterMap().containsKey("operate")&&req.getParameter("operate").equals("refuse")) {
			qop = new QueryOperation();
			qop.queryAllApplicationOf(formId);
			QueryResult res = (QueryResult) qop.result();
			ApplicationMaterials materials= (ApplicationMaterials) res.getLists().get(0);

			for(String material:materials.getMaterials()) {
				uop.updateMaterialSpecifaction(null, material);
				uop.updateMaterialLocationId(null, material);
				uop.updateMaterialState("未入库", material);
				uop.updateUserManagmentStatement("等待操作", qop.queryApplicantOfInApplication(formId), material);
			}
			op.updateStateInApplication(formId, "已驳回");
		}
		op.updateAuditorInApplication((String) session.getAttribute("userId"), formId);
	}
	
	
	public void outapplicationOperation(HttpServletRequest req) throws SQLException {
		String formId = req.getParameter("formId");
		QueryOperation qop = null;
		UpdateOperation uop = new UpdateOperation();
		//通过：
		//1、申请表改为 已通过
		//2、航材信息更新为已出库
		//3、用户管理区更新
		
		//驳回：
		//1、申请表更新：驳回
		//2、航材状态更新：已入库
		//3、用户管理区删除记录
		
		//更新审核者
		if(req.getParameterMap().containsKey("operate")&&req.getParameter("operate").equals("access")) {
			System.out.println(formId);
			qop = new QueryOperation();
			qop.queryAllApplicationOf(formId);
			QueryResult res = (QueryResult) qop.result();
			ApplicationMaterials materials= (ApplicationMaterials) res.getLists().get(0);

			for(String material:materials.getMaterials()) {
				uop.updateMaterialLocationId(null, material);
				uop.updateMaterialState("已出库", material);
				uop.updateUserManagmentStatement("已出库", qop.queryApplicantOfOutApplication(formId), material);
			}
			op.updateStateOutApplication(formId, "已通过");
		}else if(req.getParameterMap().containsKey("operate")&&req.getParameter("operate").equals("refuse")) {
			qop = new QueryOperation();
			qop.queryAllApplicationOf(formId);
			QueryResult res = (QueryResult) qop.result();
			ApplicationMaterials materials= (ApplicationMaterials) res.getLists().get(0);

			for(String material:materials.getMaterials()) {
				uop.updateMaterialState("已入库", material);
				DeleteOperation dop = new DeleteOperation();
				dop.deleteUserManagement(qop.queryApplicantOfOutApplication(formId), material);
			}
			op.updateStateOutApplication(formId, "已驳回");
		}
		op.updateAuditorOutApplication((String)session.getAttribute("userId"), formId);
	}
	public void transferapplicationOperation(HttpServletRequest req) {
		
	}
}
