package xupt.se.ttms.dao;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.sql.ResultSet;

import xupt.se.ttms.idao.iScheduleDAO;
import xupt.se.ttms.model.Schedule;
import xupt.se.util.DBUtil;

public class ScheduleDAO implements iScheduleDAO {
	
	SimpleDateFormat s =new SimpleDateFormat( "yyyy-MM-dd HH:mm" );
	
	public int insert(Schedule stu) {
		
//		System.out.println(s.format(stu.getSched_time()));
		try {
			String sql = "insert into Schedule(studio_id, mv_id, sched_time, sched_ticket_price)"
					+ " values('"
					+ stu.getStudio_id()
					+ "', "
					+ stu.getMv_id()
					+ ", '"
					+ s.format(stu.getSched_time())
					+ "', '"
					+ stu.getSched_ticket_price()
					+ "' )";
			DBUtil db = new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sql);
			if (rst != null && rst.first()) {
				stu.setSched_id(rst.getInt(1));
			}
			db.close(rst);
			db.close();
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	 
	public int update(Schedule stu) {
		int rtn = 0;
		try {
			String sql = "update Schedule set " + " studio_id ='"
					+ stu.getStudio_id() + "', " + " mv_id = "
					+ stu.getMv_id() + ", " + " sched_time = '"
					+ s.format(stu.getSched_time()) + "', " + " sched_ticket_price = '"
					+ stu.getSched_ticket_price() + "' ";

			sql += " where sched_id = " + stu.getSched_id();
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn = db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	 
	public int delete(int ID) {
		int rtn = 0;
		try {
			String sql = "delete from  Schedule ";
			sql += " where sched_id = " + ID;
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn = db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	 
	public List<Schedule> select(String condt) {
		List<Schedule> stuList = null;
		stuList = new LinkedList<Schedule>();
		try {
			String sql = "select sched_id, studio_id, mv_id, sched_time, sched_ticket_price from Schedule ";
			condt.trim();
			if (!condt.isEmpty())
				sql += " where " + condt;
			DBUtil db = new DBUtil();
			if (!db.openConnection()) {
				System.out.print("fail to connect database");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if (rst != null) {
				while (rst.next()) {
					Schedule stu = new Schedule();
					stu.setSched_id(rst.getInt("sched_id"));
					stu.setStudio_id(rst.getInt("studio_id"));
					stu.setMv_id(rst.getInt("mv_id"));
					stu.setSched_time(rst.getTimestamp("sched_time"));
					stu.setSched_ticket_price(rst.getFloat("sched_ticket_price"));
					stuList.add(stu);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return stuList;
	}
}
