package com.chen.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BaseDao {
private ResourceBundle rb = ResourceBundle.getBundle("database");
	
	String driver = rb.getString("driver");
	String url=rb.getString("url");
	String username=rb.getString("user");
	String password=rb.getString("password");
	
	public Connection getConnection(){
		Connection connection = null;
		try {
			Class.forName(driver);
			connection=DriverManager.getConnection(url,username,password);
			return connection;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	

	public void closeAll(ResultSet rs,PreparedStatement pstmt,Connection connection){
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(connection!=null) connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int executeUpdate(String sql,Object... params){
		Connection connection=null;
		PreparedStatement pstmt;
		int row=0;
		try {
			connection=this.getConnection();
			pstmt=connection.prepareStatement(sql);
			for(int i=0;i<params.length;i++){
				pstmt.setObject(i+1, params[i]);
			}
			row=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	
	
	public int getTotalCount(String sql) {
		Connection connection=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		
		try {
			connection=this.getConnection();
			pstmt=connection.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				count=rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeAll(rs, pstmt, connection);
		}
		return count;
	}
	
	
	public boolean existColumn(ResultSet rs,String columnName){
		boolean result = false;
		try {
			if(rs.findColumn(columnName) > 0){
				result = true;
			}
		} catch (SQLException e) {
			result = false;
		}
		return result;
	}

}
