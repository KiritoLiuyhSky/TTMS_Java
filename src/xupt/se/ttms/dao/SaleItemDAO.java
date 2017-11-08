package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iSaleDAO;
import xupt.se.ttms.idao.iSaleItemDAO;
import xupt.se.ttms.model.SaleItem;
import xupt.se.util.DBUtil;

public class SaleItemDAO implements iSaleItemDAO {

	public List<SaleItem> select(String condt) {
		List<SaleItem> stuList = null;
		stuList=new LinkedList<SaleItem>();
		try {
			String sql = "select sale_item_id, ticket_id, sale_ID, sale_item_price from sale_item ";
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
					SaleItem stu=new SaleItem();
					stu.setId(rst.getInt("sale_item_id"));
					stu.setTicketId(rst.getInt("ticket_id"));
					stu.setSaleId(rst.getInt("sale_ID"));
					stu.setPrice(rst.getInt("sale_item_price"));
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
