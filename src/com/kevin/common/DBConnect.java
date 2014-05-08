package com.kevin.common;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

public class DBConnect {
	private String dbUrl;
	private String jdbcClassName;
	private String username;
	private String password;
	private Connection conn = null;
	private Statement stmt = null;
	private Properties pro = null;
	private FileInputStream fis=null;

	public DBConnect() throws Exception {
		//获取路径
		String path = DBConnect.class.getClassLoader().getResource("").toURI().getPath();
		
		pro = new Properties();
		
		//读取配置文件
		fis = new FileInputStream(new File(path + "datebase.properties"));
		pro.load(fis);
		
		jdbcClassName = pro.getProperty("db.driver");
		dbUrl = pro.getProperty("db.url");
		username = pro.getProperty("db.username");
		password = pro.getProperty("db.password");
		
		connect();
	}

	public DBConnect(String inUrl, String inJdbcClassName, String inUserName,
			String inPassWord) throws Exception {
		dbUrl = inUrl;
		jdbcClassName = inJdbcClassName;
		connect();
	}

	private boolean connect() throws Exception {
		boolean opened = false;
		Driver driver = (Driver) Class.forName(jdbcClassName).newInstance();
		DriverManager.registerDriver(driver);
		conn = DriverManager.getConnection(dbUrl,username,password);
		conn.setAutoCommit(false);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		opened = true;
		return opened;
	}

	public Connection getDBConnection() {
		return conn;
	}

	public ResultSet executeQuery(String sql) throws SQLException {
		if (stmt != null) {
			return stmt.executeQuery(sql);
		} else
			return null;
	}

	public void executeUpdate(String sql) throws SQLException {
		if (stmt != null)
			stmt.executeUpdate(sql);
		conn.commit();
	}
	
	public void executeBatch(List<String> sqls) throws SQLException {
		if (stmt != null){
			if(sqls.size()>0)
			{
				for(String sql : sqls){
					stmt.addBatch(sql);
				}
			}
			stmt.executeBatch();
		}
		conn.commit();
	}

	public void close() throws Exception {
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
		if (conn != null)
			conn.close();
		if (fis != null)
			fis.close();
	}
}