package com.ftsafe.iccd.db.source.impl;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.ftsafe.iccd.db.config.MysqlConfigProperty;
import com.ftsafe.iccd.db.source.AbstractDataSource;

public class DruidDataSourceMaria extends AbstractDataSource{

	private volatile static DruidDataSourceMaria instance;	
	
	
	private DruidDataSourceMaria() throws Exception {
		dataSource = DruidDataSourceFactory.createDataSource(
				MysqlConfigProperty.getInstance().getProperties());		
	}

	public static DruidDataSourceMaria getInstance() throws Exception {
		if (instance == null) {
			synchronized (DruidDataSourceMaria.class) {
				if (instance == null) {
					instance = new DruidDataSourceMaria();
				}
			}
		}
		return instance;
	}	
	

}
