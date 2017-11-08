package xupt.se.ttms.dao;

import java.util.LinkedList;
import java.util.List;
import java.sql.ResultSet;

import xupt.se.ttms.idao.iClerkDAO;
import xupt.se.ttms.model.Clerk;
import xupt.se.util.DBUtil;



public class ClerkDAO implements iClerkDAO {
	 
	public int insert(Clerk clr) {
		try {
			String sql = "insert into clerk( ck_nick, ck_name, ck_num, ck_psd, ck_sex, ck_date, ck_age, ck_phone, ck_intro )"
					+ " values('"
					+ clr.getClerkNick()
					+ "', '"
					+ clr.getClerkName()
					+ "', '"
					+ clr.getClerkNum()
					+ "', '"
					+ clr.getClerkPsd()
					+ "', '"
					+ clr.getClerkSex()
					+ "', '"
					+ clr.getClerkDate()
					+ "', '"
					+ clr.getClerkAge()
					+ "', '" 
					+ clr.getClerkPhone()
					+ "', '" 
					+ clr.getClerkIntroduction()
					+ "' )";
			DBUtil db = new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sql);
			if (rst!=null && rst.first()) {
				clr.setClerkID(rst.getInt(1));
			}
			db.close(rst);
			db.close();
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	 
	public int update(Clerk clr) {
		int rtn=0;
		try {
			String sql = "update clerk set " + " ck_nick = '"
					+ clr.getClerkNick() + "', " + " ck_name ='"
					+ clr.getClerkName() + "', " + " ck_num = '"
					+ clr.getClerkNum() + "', " + " ck_psd = '"
					+ clr.getClerkPsd() + "', " + " ck_sex = '"
					+ clr.getClerkSex() + "', " + " ck_date = '"
					+ clr.getClerkDate() + "', " + " ck_age = '"
					+ clr.getClerkAge() + "', " + " ck_phone = '"
					+ clr.getClerkPhone() + "', " + " ck_intro = '"
					+ clr.getClerkIntroduction() + "' ";

			sql += " where ck_id = " + clr.getClerkID();
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
			String sql = "delete from  clerk ";
			sql += " where ck_id = " + ID;
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn=db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;		
	}

	 
	public List<Clerk> select(String condt) {
		List<Clerk> stuList = null;
		stuList=new LinkedList<Clerk>();
		try {
			String sql = "select ck_id, ck_nick, ck_name, ck_num, ck_psd, ck_sex, ck_date, ck_age, ck_phone, ck_intro from clerk ";
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
					Clerk clr=new Clerk();
					clr.setClerkID(rst.getInt("ck_id"));
					clr.setClerkNick(rst.getString("ck_nick"));
					clr.setClerkName(rst.getString("ck_name"));
					clr.setClerkNum(rst.getString("ck_num"));
					clr.setClerkPsd(rst.getString("ck_psd"));
					clr.setClerkSex(rst.getString("ck_sex"));
					clr.setClerkDate(rst.getString("ck_date"));
					clr.setClerkAge(rst.getString("ck_age"));
					clr.setClerkPhone(rst.getString("ck_phone"));
					clr.setClerkIntroduction(rst.getString("ck_intro"));
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