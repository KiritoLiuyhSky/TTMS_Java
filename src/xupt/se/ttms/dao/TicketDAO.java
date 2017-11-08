package xupt.se.ttms.dao;

import java.util.LinkedList;
import java.util.List;
import java.sql.ResultSet;

import xupt.se.ttms.idao.iTicketDAO;
import xupt.se.ttms.model.Ticket;
import xupt.se.util.DBUtil;

public class TicketDAO implements iTicketDAO {
	 
	public int insert(Ticket stu) {
		try {
			String sql = "insert into ticket(seat_id, sched_id, ticket_price, ticket_status, ticket_locked_time )"
					+ " values("
					+ stu.getSeatId()
					+ ", "
					+ stu.getScheduleId()
					+ ", "
					+ stu.getPrice()
					+ ", " + stu.getStatus() 
					+ ", " + stu.getLocked_time()
					+ " )";
			DBUtil db = new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sql);
//			if (rst!=null && rst.first()) {
//				stu.setID(rst.getInt(1));
//			}
			db.close(rst);
			db.close();
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	 
	public int update(Ticket stu) {

		int rtn=0;
		try {
			String sql = "update ticket set " + " seat_id= "
					+ stu.getSeatId() + ", " + " sched_id = "
					+ stu.getScheduleId() + ", " + " ticket_price = "
					+ stu.getPrice() + ", "  + " ticket_status = "
					+ stu.getStatus() + ", " + " ticket_locked_time = "
					+ stu.getLocked_time();

			sql += " where ticket_id = " + stu.getId();
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
		int rtn = 0;
		try {
			String sql = "delete from ticket ";
			sql += " where ticket_id = " + ID;
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn = db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	 
	public List<Ticket> select(String condt) {
		List<Ticket> stuList = null;
		stuList = new LinkedList<Ticket>();
		try {
			String sql = "select * from ticket ";
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
					Ticket stu = new Ticket();
					stu.setId(rst.getInt("ticket_id"));
					stu.setSeatId(rst.getInt("seat_id"));
					stu.setScheduleId(rst.getInt("sched_id"));
					stu.setPrice(rst.getFloat("ticket_price"));
					stu.setStatus(rst.getInt("ticket_status"));
					stu.setLocked_time(rst.getTimestamp("ticket_locked_time"));
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

	 
	public int lockTicket(int ID, String time) {
		int rtn=0;
		try {
			String sql = "update ticket set ticket_status=1, ticket_locked_time='" + time + "'";
			sql += " where ticket_id = " + ID;
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn =db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	 
	public int unlockTicket(int ID) {
		int rtn=0;
		try {
			String sql = "update ticket set ticket_status=0";
			sql += " where ticket_id = " + ID;
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn =db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}
}
