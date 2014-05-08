package com.kevin.common;


import java.sql.ResultSet;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.RowSetDynaClass;


/**
 * 获取码表数据，并放到内存中，随程序启动自动读取
 * @author fanzl
 *
 */
@SuppressWarnings("serial")
public class LoadCodeServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoadCodeServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	@SuppressWarnings("unchecked")
	public void init() throws ServletException {
		// Put your code here
		DBConnect db = null;
		try {
			//连接数据库，取得码表全部数据
			db = new DBConnect();
			ResultSet rs = (ResultSet) db.executeQuery("select * from sys_code ");
			RowSetDynaClass rsdc = new RowSetDynaClass(rs);
			//按行保存在内存中
			List<DynaBean> codes = rsdc.getRows();
			DisposalCode.setCodes(codes);
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(db!=null){
        		try {
					db.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
		}
	}

}
