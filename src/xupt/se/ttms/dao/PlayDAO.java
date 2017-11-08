package xupt.se.ttms.dao;

import java.util.LinkedList;
import java.util.List;
import java.sql.ResultSet;

import xupt.se.ttms.idao.iPlayDAO;
import xupt.se.ttms.model.Play;
import xupt.se.util.DBUtil;

public class PlayDAO implements iPlayDAO {
	 
	public int insert(Play play) {
		try {
			String sql = "insert into play(play_type_id, play_lang_id, play_name, play_introduction, play_image, play_length, play_ticket_price, play_status )"
					+ " values("
					+ play.getTypeId()
					+ ", "
					+ play.getLangId()
					+ ", '" + play.getName() 
					+ "', '" + play.getIntroduction()
					+ "', " + play.getImage()
					+ ", " + play.getLength()
					+ ", '" + play.getTicketPrice()
					+ "', " + play.getStatus()
					+ " )";
			DBUtil db = new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sql);
			if (rst!=null && rst.first()) {
				play.setId(rst.getInt(1));
				//System.out.println("if you see this that meaning your sql haven't connect."); 
		}
			db.close(rst);
			db.close();
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	 
	public int update(Play play) {
		int rtn=0;
		try {
			String sql = "update play set " + " play_type_id ="
					+ play.getTypeId() + ", " + " play_lang_id = "
					+ play.getLangId() + ", " + " play_name = '"
					+ play.getName() + "', " + " play_introduction = '"
					+ play.getIntroduction() + "', " + " play_image = "
					+ play.getImage() + ", " + " play_length = "
					+ play.getLength() + ", " + " play_ticket_price = '"
					+ play.getTicketPrice() + "', "+ " play_status = "
					+ play.getStatus();

			sql += " where studio_id = " + play.getId();
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
			String sql = "delete from  play ";
			sql += " where play_id = " + ID;
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn = db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	 
	public List<Play> select(String condt) {
		return null;
	}	
	
	 
	public List<Play> selectScheduledPlay(String condt) {
		List<Play> stuList = null;
		stuList = new LinkedList<Play>();
		
		try {
			String sql = "select play.play_id, play_name from play, schedule " +
		       "where play.play_id=schedule.play_id ";
			condt.trim();
			if (!condt.isEmpty())
				sql += " where " + condt;
			sql += " group by play_name";
			DBUtil db = new DBUtil();
			if (!db.openConnection()) {
				System.out.print("fail to connect database");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if (rst != null) {
				while (rst.next()) {
					Play stu = new Play();
					stu.setId(rst.getInt("play_id"));
					stu.setName(rst.getString("play_name"));
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
