package com.amwms.operations;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.amwms.utils.JDBCUtils;
import com.amwms.entities.AirMaterial;
import com.amwms.entities.QueryResult;
import com.amwms.entities.Result;
import com.amwms.entities.UpdateResult;
import com.amwms.operations.QueryOperation;

public class DeleteOperation implements Operate{

	private UpdateResult result ;
	private PreparedStatement statement;
	
	@Override
	public Result result() {
		// TODO Auto-generated method stub
		result = new UpdateResult();
		return null;
	}

	public DeleteOperation() {
		// TODO Auto-generated constructor stub
		result = new UpdateResult();
	}
	
	//通过件号查询删除航材信息
	public void deleteMaterialByCommodity(AirMaterial material,String commodityNumber,String userId) throws SQLException {
		statement = JDBCUtils.getConnection().prepareStatement("DELETE FROM airmaterials WHERE commodityNumber =? ");
		statement.setString(1, commodityNumber);
		PreparedStatement statement2 = JDBCUtils.getConnection().prepareStatement("INSERT INTO usermanagment VALUE (?,?,?)");
		statement2.setObject(1, userId);
		statement2.setObject(2, material.getSerial());
		statement2.setObject(3, "申请出库");
		//查询
		QueryOperation op = new QueryOperation();
		op.queryAirMaterialsByCommodity(commodityNumber);
		QueryResult res = (QueryResult) op.result();
		result.getLists().addAll(res.getLists());
		
		result.setFlag(statement.execute());
		
	}
	
	//通过序列号查询删除航材信息
	public void deleteMaterialBySerial(AirMaterial material,String serial,String userId) throws SQLException {
		statement = JDBCUtils.getConnection().prepareStatement("DELETE FROM airmaterials WHERE serial =? ");
		statement.setString(1, serial);
		PreparedStatement statement2 = JDBCUtils.getConnection().prepareStatement("INSERT INTO usermanagment VALUE (?,?,?)");
		statement2.setObject(1, userId);
		statement2.setObject(2, material.getSerial());
		statement2.setObject(3, "申请出库");
		//查询
		QueryOperation op = new QueryOperation();
		op.queryAirMaterialsBySerial(serial);
		QueryResult res = (QueryResult) op.result();
		result.getLists().addAll(res.getLists());
		if(!res.getLists().isEmpty()) {
			result.setFlag(true);
		}
		statement.execute();
		
	}
			
	//通过状态查询删除航材信息
	public void deleteMaterialByState(AirMaterial material,String state,String userId) throws SQLException {
		statement = JDBCUtils.getConnection().prepareStatement("DELETE FROM airmaterials WHERE state =? ");
		statement.setString(1, state);
		PreparedStatement statement2 = JDBCUtils.getConnection().prepareStatement("INSERT INTO usermanagment VALUE (?,?,?)");
		statement2.setObject(1, userId);
		statement2.setObject(2, material.getSerial());
		statement2.setObject(3, "申请出库");
		//查询
		QueryOperation op = new QueryOperation();
		op.queryAirMaterialsByState(state);
		QueryResult res = (QueryResult) op.result();
		result.getLists().addAll(res.getLists());
		if(!res.getLists().isEmpty()) {
			result.setFlag(true);
		}
		statement.execute();
		
	}
				
	//通过件号和供应商查询删除航材信息
	public void deleteMaterialByNameAndSupplier(AirMaterial material,String materialName,String supplier,String userId) throws SQLException {
		statement = JDBCUtils.getConnection().prepareStatement("DELETE FROM airmaterials WHERE materialName =? AND supplier =?");
		statement.setString(1, materialName);
		statement.setString(2, supplier);
		PreparedStatement statement2 = JDBCUtils.getConnection().prepareStatement("INSERT INTO usermanagment VALUE (?,?,?)");
		statement2.setObject(1, userId);
		statement2.setObject(2, material.getSerial());
		statement2.setObject(3, "申请出库");
		//查询
		QueryOperation op = new QueryOperation();
		op.queryAirMaterialsByNameAndSupplier(materialName,supplier);
		QueryResult res = (QueryResult) op.result();
		result.getLists().addAll(res.getLists());
		if(!res.getLists().isEmpty()) {
			result.setFlag(true);
		}
		statement.execute();
		
	}
			
	//通过账号查询删除用户信息
	public void deleteUserByUserId(String userId) throws SQLException {
		statement = JDBCUtils.getConnection().prepareStatement("DELETE FROM users WHERE userID =? ");
		statement.setString(1, userId);
		
		//查询
		QueryOperation op = new QueryOperation();
		op.queryUsersById(userId);
		QueryResult res = (QueryResult) op.result();
		result.getLists().addAll(res.getLists());
		
		if(!res.getLists().isEmpty()) {
			result.setFlag(true);
		}
		statement.execute();
		
	}
	
	public void deleteUserManagement(String userId,String material) throws SQLException {
		statement = JDBCUtils.getConnection().prepareStatement("DELETE FROM usermanagment WHERE userId = ? AND materialId = ? "); 
		statement.setString(1, userId);
		statement.setString(2, material);
		
		statement.execute();
	}
				
}
