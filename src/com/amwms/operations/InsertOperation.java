package com.amwms.operations;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.amwms.entities.AirMaterial;
import com.amwms.entities.Entity;
import com.amwms.entities.PutInApplicationForms;
import com.amwms.entities.QueryResult;
import com.amwms.entities.RemovalApplicationForms;
import com.amwms.entities.Result;
import com.amwms.entities.Supplier;
import com.amwms.entities.TransferApplicationForms;
import com.amwms.entities.UpdateResult;
import com.amwms.entities.User;
import com.amwms.servlet.AddMaterialServlet;
import com.amwms.utils.JDBCUtils;
import com.amwms.utils.UserUtils;

public class InsertOperation implements Operate{

	private UpdateResult result;
	private PreparedStatement statement;
	@Override
	public Result result() {
		// TODO Auto-generated method stub
		return result;
	}
	
	public InsertOperation() {
		// TODO Auto-generated constructor stub
		result = new UpdateResult();
	}
	
	//增加用户，管理员的增加需要update或直接增加管理员（注册）
	//防止重复注册;
	public void insertUser(User user) throws SQLException {
		statement = JDBCUtils.getConnection().prepareStatement("INSERT INTO users (userId,userName,duty,pwd,statement) VALUES(?,?,?,?,?)");
		String userId = UserUtils.getNewUserId();
		statement.setString(1, userId);
		statement.setString(2, user.getUserName());
		statement.setString(3, "user");
		statement.setString(4, user.getPwd());
		statement.setString(5, user.getStatement());
		
		statement.execute();
		QueryOperation op = new QueryOperation();
		op.queryUsersById(userId);
		QueryResult res = (QueryResult) op.result();
		if(!res.getLists().isEmpty() &&((User) res.getLists().get(0)).getUserId().equals(userId)) {
			result.setFlag(true);
			result.setLists(res.getLists());
		}
	}
	
	//由root用户直接增加管理员，注意若用户已存在，则需要升级成为管理员
	public void insertManager(User user) {
		
	}
	
	public void insertSupplier(Supplier supplier) {
		
	}

	//入库申请审核并入库
	public void insertMaterial(AirMaterial material) {
		
	}

	//添加航材到用户管理区
	public void addMaterial(AirMaterial material,String userId) throws SQLException {
		statement = JDBCUtils.getConnection().prepareStatement("INSERT INTO airmaterials (serialNumber,"
				+ "materialName,commodityNumber,materialType, materialSpecification,purchaseDate,supplierId,"
				+ "locationId,unitCost,state,serviceLife,statement) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
		PreparedStatement statement2 = JDBCUtils.getConnection().prepareStatement("INSERT INTO usermanagment VALUE (?,?,?)");
		
		statement.setObject(1, material.getSerial());
		statement.setObject(2, material.getMaterialName());
		statement.setObject(3, material.getCommodityNumber());
		statement.setObject(4, material.getPartsTypeId());
		statement.setObject(5, null);//是否合格待检验
		statement.setObject(6, material.getPurchaseDate());
		statement.setObject(7, material.getSupplierId());
		statement.setObject(8, null);//入库后自动分配货位
		statement.setObject(9, material.getUnitCost());
		statement.setObject(10, "未入库");
		statement.setObject(11, material.getServiceLife());
		statement.setObject(12, material.getStatement());
		
		statement2.setObject(1, userId);
		statement2.setObject(2, material.getSerial());
		statement2.setObject(3, "等待操作");
		
		statement.execute();
		statement2.execute();
		QueryOperation op = new QueryOperation();
		op.queryAirMaterialsBySerial(material.getSerial());
		QueryResult res = (QueryResult) op.result();
		if(!res.getLists().isEmpty()) {			
			result.setFlag(true);  //看有没有记录判断是否成功失败  默认false
			result.getLists().addAll(res.getLists());
		}
	}
	
	public void addMaterialOutApp(String materialId,String userId) throws SQLException {
		statement = JDBCUtils.getConnection().prepareStatement("INSERT INTO usermanagment VALUE (?,?,?)");
		statement.setString(1, userId);
		statement.setString(2, materialId);
		statement.setString(3, "申请出库中");
		statement.execute();
		QueryOperation op = new QueryOperation();
		op.queryAirMaterialsBySerial(materialId);
		QueryResult res = (QueryResult) op.result();
		if(!res.getLists().isEmpty()) {			
			result.setFlag(true);  //看有没有记录判断是否成功失败  默认false
			result.getLists().addAll(res.getLists());
		}
	}
	
	/**
	 * 
	 * 增加入库申请单  （单号系统自动生成，插入后的查询方法暂时采用查询所有的入库申请单）
	 * @throws SQLException
	 */
	public void insertPutInApplicationForms(PutInApplicationForms put) throws SQLException {
		statement = JDBCUtils.getConnection().prepareStatement("INSERT INTO putInApplicationForms (applicant,"
				+ "auditor, commodityName, commodityNumber, formId, replenishDate, statement, supplierId) "
				+ " VALUES (?,?,?,?,?,?,?,?)");
		statement.setObject(1, put.getApplicant());
		statement.setObject(2, put.getAuditor());
		statement.setObject(3, put.getCommodityName());
		statement.setObject(4, put.getCommodityNumber());
		statement.setObject(5, put.getFormId());//单号系统自动生成
		statement.setObject(6, put.getReplenishDate());
		statement.setObject(7, put.getStatement());
		statement.setObject(8, put.getSupplierId());
		statement.execute();
		
		QueryOperation op = new QueryOperation();
		op.queryPutInApplicationFormById(put.getFormId()); //此处的查询方法暂时采用直接查询所有的入库申请单
		QueryResult res = (QueryResult) op.result();
		if(!res.getLists().isEmpty()) {			
			result.setFlag(true);  //看有没有记录判断是否成功失败  默认false
			result.getLists().addAll(res.getLists());
		}	
	}

	/**
	 * 
	 * 增加出库申请单（单号系统自动生成，插入后得查询方法暂时采用查询所有的出库申请单）
	 * @throws SQLException
	 */
	public void insertRemovalApplicationForms(RemovalApplicationForms removal) throws SQLException{
		statement = JDBCUtils.getConnection().prepareStatement("INSERT INTO removalApplicationForms (applicant,"
				+ "auditor, commodityName, commodityNumber, currentLocationId, formId, removalDate, statement)"
				+ "VALUES (?,?,?,?,?,?,?,?)");
		statement.setObject(1, removal.getApplicant());
		statement.setObject(2, removal.getAuditor());
		statement.setObject(3, removal.getCommodityName());
		statement.setObject(4, removal.getCommodityNumber());
		statement.setObject(5, removal.getCurrentLocationId());
		statement.setObject(6, removal.getFormId());//单号系统自动生成
		statement.setObject(7, removal.getRemovalDate());
		statement.setObject(8, removal.getStatement());
		statement.execute();
		
		QueryOperation op = new QueryOperation();
		op.queryRemovalApplicationFormById(removal.getFormId()); //此处的查询方法暂时采用直接查询所有的出库申请单
		QueryResult res = (QueryResult) op.result();
		if(!res.getLists().isEmpty()) {			
			result.setFlag(true);  //看有没有记录判断是否成功失败  默认false
			result.getLists().addAll(res.getLists());
		}			
	}
	
	/**
	 * 
	 * 增加移库申请单（单号系统自动生成，插入后得查询方法暂时采用查询所有的移库申请单）
	 * @throws SQLException
	 */
	public void insertTransferApplicationForms(TransferApplicationForms transfer) throws SQLException{
		statement = JDBCUtils.getConnection().prepareStatement("INSERT INTO transferApplicationForms (applicant,"
				+ "auditor, commodityName, commodityNumber, formId, orginalLocationId, statement, targetLocationId,"
				+ "transferDate) VALUES (?,?,?,?,?,?,?,?,?)");
		statement.setObject(1, transfer.getApplicant());
		statement.setObject(2, transfer.getAuditor());
		statement.setObject(3, transfer.getCommodityName());
		statement.setObject(4, transfer.getCommodityNumber());
		statement.setObject(5, null); //单号由系统自动生成
		statement.setObject(6, transfer.getOrginalLocation());
		statement.setObject(7, transfer.getStatement());
		statement.setObject(8, transfer.getTargetLocation());
		statement.setObject(9, transfer.getTransferDate());
		
		QueryOperation op = new QueryOperation();
		op.queryAllTransferApplicationForms(); //此处的查询方法暂时采用直接查询所有的移库申请单
		QueryResult res = (QueryResult) op.result();
		if(!res.getLists().isEmpty()) {			
			result.setFlag(true);  //看有没有记录判断是否成功失败  默认false
			result.getLists().addAll(res.getLists());
		}
		
	}
	
	public void insertMaterialApplication(String materialId, String formId) throws SQLException {
		statement = JDBCUtils.getConnection().prepareStatement("INSERT INTO materialapplication (materialId,formId) VALUES (?,?)");
		statement.setString(1, materialId);
		statement.setString(2, formId);
		
		statement.execute();
		QueryOperation op = new QueryOperation();
		op.queryAllApplicationOf(formId);
		QueryResult res = (QueryResult) op.result();
		if(!res.getLists().isEmpty()) {			
			result.setFlag(true);  //看有没有记录判断是否成功失败  默认false
			result.getLists().addAll(res.getLists());
		}
	}
}
