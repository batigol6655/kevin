package com.kevin.common;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;


/**
* * ORACLE 分页函数 * *
* 
* @author Z
*/
public class PageHelpper extends JdbcDaoSupport {


	/**
	* 分页函数
	* 
	* @param sql
	*            根据传入的sql语句得到一些基本分页信息
	* @param currentPage
	*            当前页
	* @param numPerPage
	*            每页记录数
	* @param jTemplate
	*            JdbcTemplate实例
	* @author Z
	*/
	public Pagination getPagination(String sql, int currentPage, int numPerPage, JdbcTemplate jTemplate,RowMapper rowMapper) {
   
		if (jTemplate == null) {
	    throw new IllegalArgumentException( "Dao.Implement.Oracle.PageHelpper.jTemplate is null,please initial it first. ");
	   } else if (sql == null || sql.equals("")) {
	    throw new IllegalArgumentException( "Dao.Implement.Oracle.PageHelpper.sql is empty,please initial it first. ");
	   }
		Pagination pagination = new Pagination();
	   // 设置每页显示记录数
		pagination.setNumPerPage(numPerPage);
	   // 设置要显示的页数
		pagination.setCurrentPage(currentPage);
	   // 计算总记录数
	   StringBuffer totalSQL = new StringBuffer(" SELECT count(*) FROM ( ");
	   totalSQL.append(sql);
	   totalSQL.append(" ) totalTable ");

	   // 总记录数
	   pagination.setTotalRows(jTemplate.queryForInt(totalSQL.toString()));
	   // 计算总页数
	   pagination.setTotalPages();
	   // 计算起始行数
	   pagination.setStartIndex();
	   // 计算结束行数
	   pagination.setLastIndex();
	   
	   // 构造oracle数据库的分页语句
	   StringBuffer paginationSQL = new StringBuffer(" SELECT * FROM ( ");
	   paginationSQL.append(" SELECT temp.* ,ROWNUM num FROM ( ");
	   paginationSQL.append(sql);
	   paginationSQL.append("　) temp where ROWNUM <= " + pagination.getLastIndex());
	   paginationSQL.append(" ) WHERE　num > " + pagination.getStartIndex());
	

	   // 装入结果集
	   pagination.setResultList(jTemplate.query(paginationSQL.toString(),rowMapper));
	
	   return pagination;
	}




}

