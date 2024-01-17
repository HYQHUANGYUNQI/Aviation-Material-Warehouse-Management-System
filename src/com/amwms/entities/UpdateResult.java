package com.amwms.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author BigBing
 * @Statement:
 * 更新结果，list存放被影响的记录（更新后），list.size()可以读取被影响的记录数
 * flag 指示操作成功与否
 *
 */
public class UpdateResult extends Result{

	private boolean flag;
	private List<Entity> lists;
	
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public List<Entity> getLists() {
		return lists;
	}
	public void setLists(List<Entity> lists) {
		this.lists = lists;
	}
	
	public UpdateResult() {
		this.flag = false;
		this.lists = new ArrayList<Entity>();
	}
	
}
