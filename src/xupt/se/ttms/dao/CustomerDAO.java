package xupt.se.ttms.dao;

import java.util.LinkedList;
import java.util.List;
import java.sql.ResultSet;

import xupt.se.ttms.idao.iCustomerDAO;
import xupt.se.ttms.model.Customer;
import xupt.se.util.DBUtil;



public class CustomerDAO implements iCustomerDAO {
	 
	public int insert(Customer clr) {
		try {
			String sql = "insert into customer( cs_nick, cs_psd, cs_name, cs_email, cs_sex, cs_date, cs_age, cs_phone, cs_intro )"
					+ " values('"
					+ clr.getNick()
					+ "', '"
					+ clr.getPsd()
					+ "', '"
					+ clr.getName()
					+ "', '"
					+ clr.getEmail()
					+ "', '"
					+ clr.getSex()
					+ "', '"
					+ clr.getDate()
					+ "', '"
					+ clr.getAge()
					+ "', '" 
					+ clr.getPhone()
					+ "', '" 
					+ clr.getIntroduction()
					+ "' )";
			DBUtil db = new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sql);
			if (rst!=null && rst.first()) {
				clr.setID(rst.getInt(1));
			}
			db.close(rst);
			db.close();
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	 
	public int update(Customer clr) {
		int rtn=0;
		try {
			String sql = "update customer set " + " cs_nick = '"
					+ clr.getNick() + "', " + " cs_psd ='"
					+ clr.getPsd() + "', " + " cs_name = '"
					+ clr.getName() + "', " + " cs_email = '"
					+ clr.getEmail() + "', " + " cs_sex = '"
					+ clr.getSex() + "', " + " cs_date = '"
					+ clr.getDate() + "', " + " cs_age = '"
					+ clr.getAge() + "', " + " cs_phone = '"
					+ clr.getPhone() + "', " + " cs_intro = '"
					+ clr.getIntroduction() + "' ";

			sql += " where cs_id = " + clr.getID();
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn =db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	 
	public int delete(int ID) {
		int rtn=0;		
		try{
			String sql = "delete from  customer ";
			sql += " where cs_id = " + ID;
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn=db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;		
	}

	 
	public List<Customer> select(String condt) {
		List<Customer> stuList = null;
		stuList=new LinkedList<Customer>();
		try {
			String sql = "select cs_id, cs_nick, cs_psd, cs_name, cs_email, cs_sex, cs_date, cs_age, cs_phone, cs_intro from customer ";
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
					Customer clr=new Customer();
					clr.setID(rst.getInt("cs_id"));
					clr.setNick(rst.getString("cs_nick"));
					clr.setPsd(rst.getString("cs_psd"));
					clr.setName(rst.getString("cs_name"));
					clr.setEmail(rst.getString("cs_email"));
					clr.setSex(rst.getString("cs_sex"));
					clr.setDate(rst.getString("cs_date"));
					clr.setAge(rst.getString("cs_age"));
					clr.setPhone(rst.getString("cs_phone"));
					clr.setIntroduction(rst.getString("cs_intro"));
					stuList.add(clr);
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