package com.amwms.entities;

import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

/**
 * 
 * @author BigBing
 * @Statement:
 * 实现查询结果
 *
 */
public class QueryResult extends Result{
	private List<Entity> lists = null;
	
	public QueryResult() {
		// TODO Auto-generated constructor stub
		lists= new ArrayList<Entity>();
	}

	/**
	 * 
	 * @return 存放查询结果的List
	 */
	public List<Entity> getLists() {
		return lists;
	}

	public void setLists(List<Entity> lists) {
		this.lists = lists;
	}

	public void add(Entity entity) {
		this.lists.add(entity);
	}
}
