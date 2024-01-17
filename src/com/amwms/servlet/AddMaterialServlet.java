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

import com.amwms.entities.AirMaterial;
import com.amwms.entities.QueryResult;
import com.amwms.entities.UpdateResult;
import com.amwms.operations.InsertOperation;
import com.amwms.utils.DateUtils;

public class AddMaterialServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String,String[]> map = req.getParameterMap();
		if(!map.isEmpty()) {
			AirMaterial material = new AirMaterial();
			material.setCommodityNumber(map.get("commodityNumber")[0]);
			material.setMaterialName(map.get("materialName")[0]);
			material.setPartsTypeId(map.get("partsTypeId")[0]);
			material.setPurchaseDate(DateUtils.toDate(map.get("purchaseDate")[0]));
			material.setSerial(map.get("serial")[0]);
			material.setServiceLife(DateUtils.toDate(map.get("serviceLife")[0]));
			material.setState("未入库");
			material.setSupplierId(map.get("supplierId")[0]);
			material.setStatement(map.get("statement")[0]);
			if(!map.get("unitCost")[0].equals("")) {//还需要考虑一些奇怪的输入的问题
				material.setUnitCost(Integer.parseInt(map.get("unitCost")[0]));
			}
			
			InsertOperation op = new InsertOperation();
			HttpSession session = req.getSession();
			try {
				op.addMaterial(material, (String)session.getAttribute("userId"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			UpdateResult res = (UpdateResult)op.result();
			JSONObject json = new JSONObject();
			json.put("flag", res.getFlag());
			json.put("num", res.getLists().size());

			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().print(json);
		}
	}
}
