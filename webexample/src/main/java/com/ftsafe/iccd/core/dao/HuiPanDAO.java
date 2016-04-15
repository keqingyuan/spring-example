package com.ftsafe.iccd.core.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import com.ftsafe.iccd.core.model.HuiPan;

public interface HuiPanDAO {

	public void setDataSource(DataSource ds);
	
	public int create(String tableName, String projectName, String orderName, String huipanData, Integer status);
	
	public HuiPan getHuiPan(String tableName, Integer id);
	
	public List<HuiPan> listHuiPans(String tableName);
	
	public List<HuiPan> listHuiPan2(String tableName, Timestamp from, Timestamp to);
	
	public int delete(String tableName, Integer id);
	
	public int updateStatus(String tableName, Integer id, Integer status);
	
}
