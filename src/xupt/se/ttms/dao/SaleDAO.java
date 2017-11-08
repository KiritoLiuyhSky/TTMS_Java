package xupt.se.ttms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iSaleDAO;
import xupt.se.ttms.model.Sale;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Ticket;
import xupt.se.util.DBUtil;

public class SaleDAO implements iSaleDAO {
	DBUtil db;
	Connection con;

	 
	public boolean doSale(List<Ticket> tickets, double totalPrice) {
		try {
			int id = -1;
			db = new DBUtil();
			db.openConnection();
			con = db.getConn();
			con.setAutoCommit(false);

	        String sql = "insert into sale(sale_time, sale_payment, sale_type, sale_status) VALUES(?, " + totalPrice + ",1,1)";  
	        PreparedStatement prep = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);  
	        prep.setTimestamp(1, new Timestamp(new Date().getTime()));  
	        prep.executeUpdate();  
	        ResultSet rs = prep.getGeneratedKeys();  
	        //
//	        PreparedStatement prep2 = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);  
//	        prep2.setDouble(2, totalPrice);
//	        prep2.executeUpdate();  
	        //
	        if (rs.next()) {  
	            id = rs.getInt(1);  
	        }
	        if(id>0){
	        	for(Ticket t : tickets){
		        	double price = t.getSchedule().getSched_ticket_price();
			        String sql2 = "insert into sale_item(ticket_id, sale_ID, sale_item_price) VALUES(" +
		        	t.getId() + ", " + id + ", " + price + ")";  
		        	int flag = db.execCommand(sql2);
		        	if(flag==1){
				        String sql3 = "update ticket set ticket_status=9 where ticket_id = " + t.getId();
			        	int flag2 = db.execCommand(sql3);
			        	if(flag2!=1){
			        		return false;
			        	}
		        	}else
		        		return false;
	        	}
	        }
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				return false;
			}
			return false;
		} finally {
			try {
				con.setAutoCommit(true);
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	public List<Sale> select(String condt) {
		List<Sale> stuList = null;
		stuList=new LinkedList<Sale>();
		try {
			String sql = "select sale_ID, emp_id, sale_time, sale_payment, sale_change, sale_type, sale_status from sale ";
			condt.trim();
			if(!condt.isEmpty())
				sql+= " where " + condt;
			DBUtil db = new DBUtil();
			if(!db.openConnection()){
				System.out.print("fail to connect database");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if (rst!=null) {
				while(rst.next()){
					Sale stu=new Sale();
					stu.setId(rst.getInt("sale_ID"));
					stu.setEmpId(rst.getInt("emp_id"));
					stu.setTime(rst.getTimestamp("sale_time"));
					stu.setPayment(rst.getFloat("sale_payment"));
					stu.setChange(rst.getFloat("sale_change"));
					stu.setType(rst.getInt("sale_type"));
					stu.setStatus(rst.getInt("sale_status"));
					stuList.add(stu);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			
		}
		
		return stuList;
	}
	
	
}
