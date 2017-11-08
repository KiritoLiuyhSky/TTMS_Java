
package xupt.se.util;

import java.io.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {
	private final String dbConnFile = "resource/database/jdbc.properties";
	private Connection conn=null;
	private String dbDriver;	//定义驱动  
    private String dbURL;		//定义URL  
    private String userName;	//定义用户名  
    private String password;	//定义密码	
    
    //从配置文件取数据库链接参数  
    private void loadConnProperties(){  
        Properties props = new Properties();  
        try {  
            props.load(new FileInputStream(dbConnFile));//根据配置文件路径Conf加载配置文件  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        this.dbDriver = props.getProperty("driver");//从配置文件中取得相应的参数并设置类变量  
        this.dbURL = props.getProperty("url");  
        this.userName = props.getProperty("username");  
        this.password = props.getProperty("password");  
     
    }
    
	public boolean openConnection(){
		try {  
			loadConnProperties();
            Class.forName(dbDriver);  
            this.conn = DriverManager.getConnection(dbURL,userName,password);
            return true;
        } catch(ClassNotFoundException classnotfoundexception) {  
              classnotfoundexception.printStackTrace();  
            System.err.println("db: " + classnotfoundexception.getMessage());  
        } catch(SQLException sqlexception) {  
            System.err.println("db.getconn(): " + sqlexception.getMessage());  
        }
		return	false;
	}
	
	
	protected void finalize() throws Exception{
		try {
		if(null!=conn)
			conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
     }
	
	// 查询并得到结果集
	public ResultSet execQuery(String sql) throws Exception {
		ResultSet rstSet = null;
		try {
			if (null == conn)
				throw new Exception("Database not connected!");
			Statement stmt = conn.createStatement();
			rstSet = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rstSet;
	}

	// 插入一条新纪录，并获取标识列的值
	public ResultSet getInsertObjectIDs(String insertSql) throws Exception{
		ResultSet rst = null;
		try {
			if(null==conn)
				throw new Exception("Database not connected!");
			
			Statement stmt = conn.createStatement();
			
			stmt.executeUpdate(insertSql, Statement.RETURN_GENERATED_KEYS);
			rst = stmt.getGeneratedKeys();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rst;
	}
	
	//以参数SQL模式插入新纪录，并获取标识列的值
	public ResultSet getInsertObjectIDs(String insertSql, Object[] params) throws Exception {
		ResultSet rst = null;
		PreparedStatement pstmt = null ;
		try {
			if (null == conn)
				throw new Exception("Database not connected!");
			pstmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
			
			if(null != params){  
	            for (int i = 0; i < params.length; i++) {  
	            	pstmt.setObject(i + 1, params[i]);  
	            }  
	        }
			pstmt.executeUpdate();
			rst = pstmt.getGeneratedKeys();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rst;
	}
	
	

	// 插入、更新、删除
	public int execCommand(String sql) throws Exception{
		int flag = 0;
		try {
			if(null==conn)
				throw new Exception("Database not connected!");
			
			Statement stmt = conn.createStatement();
			flag = stmt.executeUpdate(sql);
			
			stmt.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
/*	// 存储过程调用
	public void callStordProc(String sql, Object[] inParams, SqlParameter[] outParams) throws Exception {
		CallableStatement  cst = null ;
		try {
			if (null == conn)
				throw new Exception("Database not connected!");
			cst = conn.prepareCall(sql);
			
			if(null != inParams){  
	            for (int i = 0; i < inParams.length; i++) {  
	            	cst.setObject(i + 1, inParams[i]);  
	            }  
	        }
			
			if (null!=outParams){
				for (int i = 0; i < inParams.length; i++) {
					cst.registerOutParameter(outParams[i].getName(), outParams[i].getType());  
	            }  				
			}
			cst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
*/
	// 释放资源
	public void close(ResultSet rst) throws Exception {
		try {
			Statement stmt = rst.getStatement();
			rst.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// add by @author Wang
	// 2016 5 29
	public PreparedStatement execPrepared(String psql) throws Exception {
		PreparedStatement pstmt = null ;
		try {
			if (null == conn)
				throw new Exception("Database not connected!");
			pstmt = conn.prepareStatement(psql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}


	// 释放资源
	public void close(Statement stmt) throws Exception {
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 释放资源
	public void close() throws SQLException, Exception{
		if(null!=conn){
			conn.close();
			conn=null;
		}
	}
	
	public Connection getConn() {
		return conn;
	}
	

	public static void main(String[] args) {
		
	}
}
