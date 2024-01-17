package com.amwms.operations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amwms.entities.ApplicationMaterials;
import com.amwms.entities.Entity;
import com.amwms.entities.QueryResult;
import com.amwms.entities.Result;
import com.amwms.utils.JDBCUtils;
import com.amwms.utils.QueryAirMaterialUtils;
import com.amwms.utils.QueryPartstypeUtils;
import com.amwms.utils.QueryPutInApplicationFormsUtils;
import com.amwms.utils.QueryRemovalApplicationFormsUtils;
import com.amwms.utils.QueryStorageUtils;
import com.amwms.utils.QuerySupplierUtils;
import com.amwms.utils.QueryTransferApplicationFormsUtils;
import com.amwms.utils.QueryUsersUtils;
import com.amwms.utils.StorageUtils;

import jdk.nashorn.internal.scripts.JD;

/**
 * 
 * @author BigBing
 * @Statement:
 * 查询操作实现类
 *
 */
public class QueryOperation implements Operate {

	private QueryResult result;
	private PreparedStatement statement;
	
	@Override
	public Result result() {
		// TODO Auto-generated method stub
		return result;
	}
	
	public QueryOperation() throws SQLException {
		// TODO Auto-generated constructor stub
		result = new QueryResult();
	}
	
	public void	queryAllUsers() throws SQLException {
		//preparedstament 的 简单使用
		//这部分内容之后修改，改成查询所有用户的信息
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM users");
		ResultSet results = statement.executeQuery();
		List<Entity>lists = QueryUsersUtils.getEntiyList(results);
		for(Entity entity : lists) {
			//System.out.println(entity);
			result.add(entity);
		}
	}
	public void	queryUsers(String duty) throws SQLException {
		//preparedstament 的 简单使用
		//这部分内容之后修改，改成查询所有用户的信息
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM users WHERE duty=?");
		statement.setString(1, duty);
		ResultSet results = statement.executeQuery();
		List<Entity>lists = QueryUsersUtils.getEntiyList(results);
		for(Entity entity : lists) {
			//System.out.println(entity);
			result.add(entity);
		}
	}
	
	public void queryAllAirMaterials() throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM airmaterials WHERE state = '已入库';");
		execute();
	}
	
	/**
	 * 查询所有的备件类型
	 * @throws SQLException
	 */
	public void queryAllPartstype() throws SQLException{
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM partstype");
		ResultSet results = statement.executeQuery();
		List<Entity>lists = QueryPartstypeUtils.getEntityList(results);
		for(Entity entity : lists) {
			//System.out.println(entity);
			result.add(entity);
		}
	}
	
	/**
	 * 根据id查询备件类型
	 * @throws SQLException
	 */
	public void queryPartstypeById(String partstypeId) throws SQLException{
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM partstype where partstypeId = ?");
		statement.setString(1,partstypeId);
		ResultSet results = statement.executeQuery();
		List<Entity>lists = QueryPartstypeUtils.getEntityList(results);
		for(Entity entity : lists) {
			//System.out.println(entity);
			result.add(entity);
		}
	}
	
	/**
	 * 查询所有的供应商
	 * @throws SQLException
	 */
	public void queryAllSuppliers() throws SQLException{
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM suppliers");
		ResultSet results = statement.executeQuery();
		List<Entity>lists = QuerySupplierUtils.getEntityList(results);
		for(Entity entity : lists) {
			//System.out.println(entity);
			result.add(entity);
		}
	}
	
	/**
	 * 根据id查询供应商
	 * @throws SQLException
	 */
	public void querySupplierById(String supplierId) throws SQLException{
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM suppliers where supplierId = ?");
		statement.setString(1, supplierId);
		ResultSet results = statement.executeQuery();
		List<Entity>lists = QuerySupplierUtils.getEntityList(results);
		for(Entity entity : lists) {
			//System.out.println(entity);
			result.add(entity);
		}
	}
	
	/**
	 * 查询所有的入库申请单
	 * @throws SQLException
	 */
	public void queryAllPutInApplicationForms() throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM putInApplicationForms");
		ResultSet results = statement.executeQuery();
		List<Entity>lists = QueryPutInApplicationFormsUtils.getEntiyList(results);
		for(Entity entity : lists) {
			result.add(entity);
		}
	}
	public void queryAllPutInApplicationFormsOf(String storage) throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM putInApplicationForms WHERE commodityName = ?");
		statement.setString(1, storage);
		ResultSet results = statement.executeQuery();
		List<Entity>lists = QueryPutInApplicationFormsUtils.getEntiyList(results);
		for(Entity entity : lists) {
			result.add(entity);
		}
	}
	public void queryAllPutInApplicationFormsIn(String storage,String state) throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM putInApplicationForms WHERE commodityName = ? AND statement = ?");
		statement.setString(1, storage);
		statement.setString(2, state);
		ResultSet results = statement.executeQuery();
		List<Entity>lists = QueryPutInApplicationFormsUtils.getEntiyList(results);
		for(Entity entity : lists) {
			result.add(entity);
		}
	}
	
	
	
	public void queryPutInApplicationFormById(String formId) throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM putInApplicationForms WHERE formId = ?");
		statement.setString(1, formId);
		ResultSet results = statement.executeQuery();
		List<Entity>lists = QueryPutInApplicationFormsUtils.getEntiyList(results);
		if(!lists.isEmpty()) {
			result.add(lists.get(0));
		}
	}
	
	public void queryPutInApplicationFormByApplicant(String userId) throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM putInApplicationForms WHERE applicant = ?");
		statement.setString(1, userId);
		ResultSet results = statement.executeQuery();
		
		List<Entity>lists = QueryPutInApplicationFormsUtils.getEntiyList(results);
		if(!lists.isEmpty()) {
			for(Entity entity:lists) {
				result.add(entity);
			}
		}
	}
	
	public void queryRemovalApplicationFormById(String formId) throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM removalApplicationForms WHERE formId = ?");
		statement.setString(1, formId);
		ResultSet results = statement.executeQuery();
		List<Entity>lists = QueryRemovalApplicationFormsUtils.getEntiyList(results);
		if(!lists.isEmpty()) {
			result.add(lists.get(0));
		}
	}
	
	public void queryRemovalApplicationFormByApplicant(String userId) throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM removalApplicationForms WHERE applicant = ?");
		statement.setString(1, userId);
		ResultSet results = statement.executeQuery();
		
		List<Entity>lists = QueryRemovalApplicationFormsUtils.getEntiyList(results);
		if(!lists.isEmpty()) {
			for(Entity entity:lists) {
				result.add(entity);
			}
		}
	}
	
	/**
	 * 查询所有的出库申请单
	 * @throws SQLException
	 */
	public void queryAllRemovalApplicationForms() throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM removalApplicationForms ");
		ResultSet results = statement.executeQuery();
		List<Entity>lists = QueryRemovalApplicationFormsUtils.getEntiyList(results);
		for(Entity entity : lists) {
			result.add(entity);
		}
	}
	public void queryAllRemovalApplicationFormsIn(String storage,String state) throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM removalApplicationForms WHERE commodityName = ? AND statement = ?");
		statement.setString(2, state);
		statement.setString(1, storage);
		ResultSet results = statement.executeQuery();
		List<Entity>lists = QueryRemovalApplicationFormsUtils.getEntiyList(results);
		for(Entity entity : lists) {
			result.add(entity);
		}
	}
	public void queryAllRemovalApplicationFormsOf(String storage) throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM removalApplicationForms WHERE commodityName = ?");
		statement.setString(1, storage);
		ResultSet results = statement.executeQuery();
		List<Entity>lists = QueryRemovalApplicationFormsUtils.getEntiyList(results);
		for(Entity entity : lists) {
			result.add(entity);
		}
	}
	
	/**
	 * 根据序列号查询航材
	 * @throws SQLException
	 */
	public void queryAirMaterialsBySerial(String serialNumber) throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM airmaterials where serialNumber = ? ");
		statement.setString(1, serialNumber);
		execute();
	}
	
	public void queryAirMaterialsByState(String state) throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM airmaterials where state = ? ");
		statement.setString(1, state);
		execute();
	}

	/**
	 * 根据件号查询航材
	 * @throws SQLException
	 */
	public void queryAirMaterialsByCommodity(String commodityNumber) throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM airmaterials where commodityNumber = ? AND state = '已入库';");
		statement.setString(1, commodityNumber);
		execute();
	}
	
	/**
	 * 根据供应商查询航材
	 * @throws SQLException
	 */
	public void queryAirMaterialsBySupplier(String supplierId) throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM airmaterials where supplierId = ? AND state = '已入库';");
		statement.setString(1, supplierId);
		execute();
	}
	
	/**
	 * 根据航材名称查询航材
	 * @throws SQLException
	 */
	public void queryAirMaterialsByName(String name) throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM airmaterials where materialName = ? AND state = '已入库';");
		statement.setString(1, name);
		execute();
	}
	
	/**
	 * 根据备件类型查询航材
	 * @throws SQLException
	 */
	public void queryAirMaterialsByType(String materialType) throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM airmaterials where materialType = ? AND state = '已入库';");
		statement.setString(1, materialType);
		execute();
	}
	
	/**
	 * 根据航材名称和供应商查询航材
	 * @throws SQLException
	 */
	public void queryAirMaterialsByNameAndSupplier(String name,String supplierId) throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM airmaterials where materialName = ? AND supplierId = ? AND state = '已入库';");
		statement.setString(1, name);
		statement.setString(2, supplierId);
		execute();
	}
	
	
	public String queryUserPwdByUserId(String userId) throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT pwd FROM users where userId = ? ");
		statement.setString(1, userId);
		
		ResultSet res = statement.executeQuery();
		if(res.next()) {
			return res.getString("pwd");
		}
		return null;
	}
	
	public void queryUsersById(String userId) throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM users where userId = ? ");
		statement.setString(1, userId);
		
		ResultSet res = statement.executeQuery();
		result.getLists().addAll(QueryUsersUtils.getEntiyList(res));

	}
	
	public void queryAllMaterialsOfUser(String userId) throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM usermanagment where userId = ? AND statement = ?");
		statement.setString(1, userId);
		statement.setString(2, "等待操作");
		List<String> list = new ArrayList<String>();
		
		ResultSet res = statement.executeQuery();
		while(res.next()) {
			list.add(res.getString("materialId"));
		}
		
		for(String serial:list) {
			queryAirMaterialsBySerial(serial);
		}
	}
	public void queryAllMaterialsOfUser(String userId,String statements) throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM usermanagment where userId = ? AND statement = ?");
		statement.setString(1, userId);
		statement.setString(2, statements);
		List<String> list = new ArrayList<String>();
		
		ResultSet res = statement.executeQuery();
		while(res.next()) {
			list.add(res.getString("materialId"));
		}
		
		for(String serial:list) {
			queryAirMaterialsBySerial(serial);
		}
	}
	
	public void queryStorageById(String id) throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM storageinfo	where storageId = ?");
		statement.setString(1, id);
		ResultSet results = statement.executeQuery();
		List<Entity> lists = StorageUtils.getEntityList(results);
		if(!lists.isEmpty()) {
			result.add(lists.get(0));
		}
	}
	public void execute() throws SQLException {
		ResultSet results = statement.executeQuery();
		List<Entity>lists = QueryAirMaterialUtils.getEntiyList(results);
		for(Entity entity : lists) {
			result.add(entity);
		}
	}

	public void queryAllTransferApplicationForms() throws SQLException {
		// TODO Auto-generated method stub
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM transferapplicationforms;");
		ResultSet results = statement.executeQuery();
		List<Entity> list = QueryTransferApplicationFormsUtils.getEntiyList(results);
		for(Entity entity: list) {
			result.add(entity);
		}
	}
	
	public void queryAllApplicationOf(String formId) throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM materialapplication WHERE formId = ?;");
		statement.setString(1, formId);
		ResultSet results = statement.executeQuery();
		ApplicationMaterials app = new ApplicationMaterials();
		app.setFormId(formId);
		while(results.next()) {
			app.getMaterials().add(results.getString("materialId"));
		}
		result.add(app);
	}
	
	public void queryAllMaterialsOfApp(String formId) throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT materialId FROM materialapplication WHERE formId = ?");
		statement.setString(1, formId);		
		ResultSet res = statement.executeQuery();		
		while(res.next()) {
			queryAirMaterialsBySerial(res.getString("materialId"));
		}
	}
	
	public String queryUserNameById(String userId) throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT userName FROM users WHERE userId = ?");
		statement.setString(1, userId);
		ResultSet res = statement.executeQuery();
		res.next();
		return res.getString("userName");
	}
	
	public String queryApplicantOfInApplication(String formId) throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT applicant FROM putinapplicationforms WHERE formId = ?;");
		statement.setString(1, formId);
		
		ResultSet res = statement.executeQuery();
		res.next();
		return res.getString("applicant");
		
	}
	
	public String queryApplicantOfOutApplication(String formId) throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT applicant FROM removalapplicationforms WHERE formId = ?;");
		statement.setString(1, formId);
		
		ResultSet res = statement.executeQuery();
		res.next();
		return res.getString("applicant");
		
	}
	
	public String queryManagerDutyStorage(String userId) throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT statement FROM users WHERE userId = ?");
		statement.setString(1, userId);
		
		ResultSet res = statement.executeQuery();
		if(res.next()) {
			return res.getString("statement");
		}
		return null;
	}
	
	public void queryAllStorage() throws SQLException {
		this.statement = JDBCUtils.getConnection().prepareStatement("SELECT * FROM storageinfo WHERE storageId NOT IN (SELECT storageId FROM storageinfo WHERE storageId IN (SELECT statement FROM users));");
		ResultSet res = statement.executeQuery();
		
		List<Entity> list = QueryStorageUtils.getEntiyList(res);
		result.getLists().addAll(list);
	}
}
