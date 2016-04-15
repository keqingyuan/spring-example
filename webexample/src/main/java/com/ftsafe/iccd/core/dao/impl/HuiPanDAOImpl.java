package com.ftsafe.iccd.core.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.axis.common.Log;
import com.axis.common.log.LogWrapper;
import com.ftsafe.iccd.core.dao.HuiPanDAO;
import com.ftsafe.iccd.core.model.HuiPan;
import com.ftsafe.iccd.core.model.mapper.HuiPanMapper;

public class HuiPanDAOImpl implements HuiPanDAO {

	private final static LogWrapper logger = Log.get();
	
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	@Override
	public void setDataSource(DataSource ds) {
		this.dataSource = ds;
		this.jdbcTemplateObject = new JdbcTemplate(ds);
	}

	@Override
	public int create(String tableName, String projectName, String orderName, String huipanData,
			Integer status) {
		if (tableName == null)
			return 1;
		String SQL = "insert into "+tableName+" (PROJECT_NAME, ORDER_NAME, HUIPAN_DATA, STATUS) values (?, ?, ?, ?)";
		try{
			jdbcTemplateObject.update(SQL, projectName, orderName, huipanData, status);
		} catch(Exception e) {
			logger.error(e.getMessage(),e);
			return 1;
		}
		return 0;
	}

	@Override
	public HuiPan getHuiPan(String tableName, Integer id) {
		if (tableName == null)
			return null;
		String SQL = "select * from "+tableName+" where id = ?";
		HuiPan hp = null;
		try{
			hp = jdbcTemplateObject.queryForObject(SQL, new Object[] {id}, new HuiPanMapper());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
		return hp;
	}

	@Override
	public List<HuiPan> listHuiPans(String tableName) {
		if (tableName == null)
			return null;
		String SQL = "select * from "+tableName;
		List<HuiPan> hps = null;
		try {
			hps = jdbcTemplateObject.query(SQL, new HuiPanMapper());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
		return hps;
	}

	@Override
	public List<HuiPan> listHuiPan2(String tableName, Timestamp from, Timestamp to) {
		if (tableName == null)
			return null;
		String SQL = "select * from "+tableName+" where createtime > ? and createtime < ?";
		List<HuiPan> hps = null;
		try {
			hps = jdbcTemplateObject.query(SQL, new Object[] {from, to},new HuiPanMapper());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
		return hps;
	}

	@Override
	public int delete(String tableName, Integer id) {
		if (tableName == null)
			return 1;
		String SQL = "delete from "+tableName+" where id = ?";
		try {
			jdbcTemplateObject.update(SQL, id);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return 1;
		}
		return 0;
	}

	@Override
	public int updateStatus(String tableName, Integer id, Integer status) {
		if (tableName == null)
			return 1;
		String SQL = "update "+tableName+" set status = ? where id = ?";
		try {
			jdbcTemplateObject.update(SQL, status, id);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return 1;
		}
		return 0;
	}

}
