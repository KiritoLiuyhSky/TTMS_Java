package xupt.se.ttms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iMovieDAO;
import xupt.se.ttms.model.Movie;
import xupt.se.util.DBUtil;

public class MovieDAO implements iMovieDAO {
	public int insert(Movie stu) {
		try {
			String sql = "insert into movie(mv_img, mv_name, mv_score, mv_director, mv_actor, mv_type, mv_time, mv_date, mv_introduction, mv_status )"
					+ " values('" + stu.getImg()
					+ "', '" + stu.getName()
					+ "', " + stu.getScore()
					+ ", '" + stu.getDirector()
					+ "', '" + stu.getActor()
					+ "', '" + stu.getType()
					+ "', '" + stu.getTime()
					+ "', '" + stu.getDate()
					+ "', '" + stu.getIntroduction()
					+ "', '" + stu.getStatus()
					+ "' )";
			DBUtil db = new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sql);
			if (rst!=null && rst.first()) {
				stu.setMv_id(rst.getInt(1));
			}
			db.close(rst);
			db.close();
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	 
	public int update(Movie stu) {
		int rtn=0;
		try {
			String sql = "update movie set " + " mv_img ='"
					+ stu.getImg() + "', " + " mv_name = '"
					+ stu.getName() + "', " + " mv_score = "
					+ stu.getScore() + ", " + " mv_director = '"
					+ stu.getDirector() + "', " + " mv_actor = '"
					+ stu.getActor() + "', " + " mv_type = '"
					+ stu.getType() + "', " + " mv_time = '"
					+ stu.getTime() + "', " + " mv_date = '"
					+ stu.getDate() + "', " + " mv_introduction = '"
					+ stu.getIntroduction() + "', " + " mv_status = '"
					+ stu.getStatus() + "' ";
			
			sql += " where mv_id = " + stu.getMv_id();
			
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
			String sql = "delete from  movie ";
			sql += " where mv_id = " + ID;
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn=db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;		
	}

	 
	public List<Movie> select(String condt) {
		List<Movie> stuList = null;
		stuList=new LinkedList<Movie>();
		try {
			String sql = "select mv_id, mv_img, mv_name, mv_score, mv_director, mv_actor, mv_type, mv_time, mv_date, mv_introduction, mv_status from movie ";
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
					Movie stu=new Movie();
					stu.setMv_id(rst.getInt("mv_id"));
					stu.setImg(rst.getString("mv_img"));
					stu.setName(rst.getString("mv_name"));
					stu.setScore(rst.getFloat("mv_score"));
					stu.setDirector(rst.getString("mv_director"));
					stu.setActor(rst.getString("mv_actor"));
					stu.setType(rst.getString("mv_type"));
					stu.setTime(rst.getString("mv_time"));
					stu.setDate(rst.getString("mv_date"));
					stu.setIntroduction(rst.getString("mv_introduction"));
					stu.setStatus(rst.getString("mv_status"));
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