package com.amwms.operations;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.amwms.entities.AirMaterial;
import com.amwms.entities.PutInApplicationForms;
import com.amwms.entities.QueryResult;
import com.amwms.entities.RemovalApplicationForms;
import com.amwms.entities.Result;
import com.amwms.entities.UpdateResult;
import com.amwms.entities.User;
import com.amwms.utils.JDBCUtils;

/**
 * 
 * @author lenovo
 *
 */
public class UpdateOperation implements Operate{

	private UpdateResult result;
	private PreparedStatement statement;
	
	@Override
	public Result result() {
		// TODO Auto-generated method stub
		return result;
	}
	
	public UpdateOperation() {
		
		result = new UpdateResult();
	}
	
	//根据用户Id更新用户信息
	public void updateUser(User user, String userId) throws SQLException {
		
		statement = JDBCUtils.getConnection().prepareStatement("UPDATE user set userName = ? and duty = ?"
				+ "and pwd = ? and statement = ? where userId = ?");
		if(user.getUserName() != null) {
			statement.setObject(1, user.getUserName());
		}
		if(user.getDuty() != null){
			statement.setObject(2, user.getDuty());
		}
		if(user.getPwd() != null) {
			statement.setObject(3, user.getPwd());
		}
		if(user.getStatement() != null) {
			statement.setObject(4, user.getStatement());
		}
		statement.setObject(5, userId);
		statement.execute();
		QueryOperation op = new QueryOperation();
		op.queryUsersById(userId);
		QueryResult res = (QueryResult) op.result();
		if(!res.getLists().isEmpty()) {			
			result.setFlag(true);  //看有没有记录判断是否成功失败  默认false
			result.getLists().addAll(res.getLists());
		}
	}
	
	public void updateUserDuty(String duty,String userId) throws SQLException {
		statement = JDBCUtils.getConnection().prepareStatement("UPDATE users set duty = ? where userId = ?");
		statement.setString(1, duty);
		statement.setString(2, userId);		
		statement.execute();
		
		QueryOperation op = new QueryOperation();
		op.queryUsersById(userId);
		QueryResult res = (QueryResult) op.result();
		if(((User)res.getLists().get(0)).getDuty().equals(duty)) {
			result.setFlag(true);
			result.getLists().addAll(res.getLists());
		}
	}
	
	//根据序列号更新航材信息
	//舍弃，报错
	public void updateMaterial(AirMaterial material, String serialNumber) throws SQLException {
		statement = JDBCUtils.getConnection().prepareStatement("UPDATE airmaterials set purchaseDate = '"+material.getPurchaseDate()+"'"
				+ "and materialSpecification = ? and commodityNumber = ? and locationId = ? and "
				+ "state = ? and unitCost = ? where serialNumber = ?");
		if(material.getMaterialSpecification() != null) {
			statement.setObject(1, material.getMaterialSpecification());
		}
		if(material.getCommodityNumber() != null) {
			statement.setObject(2, material.getCommodityNumber());
		}
		if(material.getLocationId() != null) {
			statement.setObject(3, material.getLocationId());
		}
		if(material.getState() != null) {
			statement.setObject(4, material.getState());
		}
		if(String.valueOf(material.getUnitCost()) != null) {
			statement.setObject(5, material.getUnitCost());
		}
		statement.setObject(6, serialNumber);
		statement.execute();
		QueryOperation op = new QueryOperation();
		op.queryAirMaterialsBySerial(serialNumber);
		QueryResult res = (QueryResult) op.result();
		if(!res.getLists().isEmpty()) {			
			result.setFlag(true);  //看有没有记录判断是否成功失败  默认false
			result.getLists().addAll(res.getLists());
		}
	}
	
	public void updateMaterialLocationId(String locationId, String serialNumber) throws SQLException {
		statement = JDBCUtils.getConnection().prepareStatement("UPDATE airmaterials SET locationId = ? WHERE serialNumber = ?");
		statement.setString(1, locationId);
		statement.setString(2, serialNumber);
		statement.execute();
		QueryOperation op = new QueryOperation();
		op.queryAirMaterialsBySerial(serialNumber);
		QueryResult res = (QueryResult) op.result();
		if(!res.getLists().isEmpty()) {			
			result.setFlag(true);  //看有没有记录判断是否成功失败  默认false
			result.getLists().addAll(res.getLists());
		}
	}
	public void updateMaterialSpecifaction(String spe, String serialNumber) throws SQLException {
		statement = JDBCUtils.getConnection().prepareStatement("UPDATE airmaterials SET materialSpecification = ? WHERE serialNumber = ?");
		statement.setString(1, spe);
		statement.setString(2, serialNumber);
		statement.execute();
		QueryOperation op = new QueryOperation();
		op.queryAirMaterialsBySerial(serialNumber);
		QueryResult res = (QueryResult) op.result();
		if(!res.getLists().isEmpty()) {			
			result.setFlag(true);  //看有没有记录判断是否成功失败  默认false
			result.getLists().addAll(res.getLists());
		}
	}

	//根据序列号更新航材的备注信息
	public void updateMaterialStatement(String statement1, String serialNumber) throws SQLException {
		statement = JDBCUtils.getConnection().prepareStatement("UPDATE airmaterials set statement = ?"
				+ " where serialNumber = ?");
		statement.setObject(1, statement1);
		statement.setObject(2, serialNumber);
		statement.execute();
		QueryOperation op = new QueryOperation();
		op.queryAirMaterialsBySerial(serialNumber);
		QueryResult res = (QueryResult) op.result();
		if(!res.getLists().isEmpty()) {			
			result.setFlag(true);  //看有没有记录判断是否成功失败  默认false
			result.getLists().addAll(res.getLists());
		}
	}
	//根据序列号更新航材的状态信息
	public void updateMaterialState(String state, String serialNumber) throws SQLException {
		statement = JDBCUtils.getConnection().prepareStatement("UPDATE airmaterials set state = ?"
				+ " where serialNumber = ?");
		statement.setObject(1, state);
		statement.setObject(2, serialNumber);
		
		statement.execute();
		QueryOperation op = new QueryOperation();
		op.queryAirMaterialsBySerial(serialNumber);
		QueryResult res = (QueryResult) op.result();
		
		if(!res.getLists().isEmpty()) {			
			result.setFlag(true);  //看有没有记录判断是否成功失败  默认false
			result.getLists().addAll(res.getLists());
		}
		
	}
	
	//根据备件类型Id更新备件类型的备注信息
	public void updatePartstypeStatement(String statement1, String partstypeId) throws SQLException{
		statement = JDBCUtils.getConnection().prepareStatement("UPDATE partstype set statement = ?"
				+ " where partstypeId = ?");
		statement.setObject(1, statement1);
		statement.setObject(2, partstypeId);
		statement.execute();
		QueryOperation op = new QueryOperation();
		op.queryPartstypeById(partstypeId);
		QueryResult res = (QueryResult) op.result();
		if(!res.getLists().isEmpty()) {			
			result.setFlag(true);  //看有没有记录判断是否成功失败  默认false
			result.getLists().addAll(res.getLists());
		}
	}
	
	//根据供应商Id更新供应商的备注信息
	public void updateSupplierStatement(String statement1, String supplierId) throws SQLException{
		statement = JDBCUtils.getConnection().prepareStatement("UPDATE suppliers set statement = ?"
				+ " where supplierId = ?");
		statement.setObject(1, statement1);
		statement.setObject(2, supplierId);
		statement.execute();
		QueryOperation op = new QueryOperation();
		op.querySupplierById(supplierId);
		QueryResult res = (QueryResult) op.result();
		if(!res.getLists().isEmpty()) {			
			result.setFlag(true);  //看有没有记录判断是否成功失败  默认false
			result.getLists().addAll(res.getLists());
		}
	}
	
	//根据用户Id更新用户的备注信息
	public void updateUserStatement(String statement1, String userId) throws SQLException{
		statement = JDBCUtils.getConnection().prepareStatement("UPDATE users set statement = ?"
				+ " where userId = ?");
		statement.setObject(1, statement1);
		statement.setObject(2, userId);
		statement.execute();
		QueryOperation op = new QueryOperation();
		op.queryUsersById(userId);
		QueryResult res = (QueryResult) op.result();
		if(!res.getLists().isEmpty()) {			
			result.setFlag(true);  //看有没有记录判断是否成功失败  默认false
			result.getLists().addAll(res.getLists());
		}
	}
	
	public void updateUserManagmentStatement(String statement1, String userId,String materialId)throws SQLException{
		statement = JDBCUtils.getConnection().prepareStatement("UPDATE usermanagment set statement = ?"
				+ " where userId = ? AND  materialId = ?");
		statement.setObject(1, statement1);
		statement.setObject(2, userId);
		statement.setObject(3, materialId);
		statement.execute();
		
		QueryOperation op = new QueryOperation();
		op.queryAllMaterialsOfUser(userId, statement1);
		QueryResult res = (QueryResult) op.result();
		if(!res.getLists().isEmpty()) {			
			result.setFlag(true);  //看有没有记录判断是否成功失败  默认false
			result.getLists().addAll(res.getLists());
		}

	}
	
	public void updateStateInApplication(String formId,String state) throws SQLException {
		statement = JDBCUtils.getConnection().prepareStatement("UPDATE putinapplicationforms SET statement = ? WHERE formId = ?;");
		statement.setString(1, state);
		statement.setString(2, formId);
		
		statement.execute();
		QueryOperation op2 = new QueryOperation();
		op2.queryPutInApplicationFormById(formId);
		QueryResult results = (QueryResult) op2.result();
		if(((PutInApplicationForms)results.getLists().get(0)).getStatement().equals(state)) {
			result.setFlag(true);
			result.getLists().addAll(results.getLists());
		}
	}

	public void updateStateOutApplication(String formId, String state) throws SQLException {
		// TODO Auto-generated method stub
		statement = JDBCUtils.getConnection().prepareStatement("UPDATE removalapplicationforms SET statement = ? WHERE formId = ?;");
		statement.setString(1, state);
		statement.setString(2, formId);
		
		statement.execute();
		QueryOperation op2 = new QueryOperation();
		op2.queryRemovalApplicationFormById(formId);
		QueryResult results = (QueryResult) op2.result();
		if(((RemovalApplicationForms)results.getLists().get(0)).getStatement().equals(state)) {
			result.setFlag(true);
			result.getLists().addAll(results.getLists());
		}
	}
	
	public void updateAuditorInApplication(String auditorId,String formId) throws SQLException {
		statement = JDBCUtils.getConnection().prepareStatement("UPDATE putinapplicationforms SET auditor = ? WHERE formId = ?;");
		statement.setString(1, auditorId);
		statement.setString(2, formId);
		statement.execute();
	}
	
	public void updateAuditorOutApplication(String auditorId,String formId) throws SQLException {
		statement = JDBCUtils.getConnection().prepareStatement("UPDATE removalapplicationforms SET auditor = ? WHERE formId = ?;");
		statement.setString(1, auditorId);
		statement.setString(2, formId);
		statement.execute();
	}
}
