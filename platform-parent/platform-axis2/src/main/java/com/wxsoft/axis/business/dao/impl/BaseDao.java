package com.wxsoft.axis.business.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
/**
 * @author Asdpboy
 * @date Apr 12, 2010
 * @description Dao基类
 */
public class BaseDao<T> {
	private JdbcTemplate jdbcTemplate ;
	private SimpleJdbcTemplate simplejdbcTemplate ;
	
	//ApplicationContext applicationContext=servletcontext;
	
	@Autowired(required = true)
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.simplejdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	public List<T> query(String sql , Object[] objs , RowMapper rowMapper){
		try {
			return this.jdbcTemplate.query(sql, objs , rowMapper);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList() ;
		}
	}
	
	@SuppressWarnings("unchecked")
	public T queryForObject(String sql , Object[] objs , RowMapper rowMapper){
		try {
			return (T)this.jdbcTemplate.queryForObject(sql, objs , rowMapper) ;
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}
	}
	
	/**
	 * 批量更新或者插入
	 * @param sql
	 * @param data
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public int[] batchUpdate(String sql, final String[][] data) 
		throws SQLException,Exception{
		return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter(){
			public int getBatchSize() {
				return data.length;
			}
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				String[] s=data[i];
				for(int x=0;x<s.length;x++){
					ps.setString(x+1, s[x]);
				}
			}});
	}
	
	public JdbcTemplate getJdbcTemplate() {
		System.out.println(jdbcTemplate.toString());
		return jdbcTemplate;
	}

	public SimpleJdbcTemplate getSimplejdbcTemplate() {
		return simplejdbcTemplate;
	}
}
