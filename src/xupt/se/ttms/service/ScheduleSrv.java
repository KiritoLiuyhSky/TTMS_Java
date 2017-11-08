package xupt.se.ttms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iScheduleDAO;
import xupt.se.ttms.model.Schedule;

public class ScheduleSrv {
	private iScheduleDAO stuDAO=DAOFactory.creatScheduleDAO();
	
	public int add(Schedule stu){
		return stuDAO.insert(stu); 		
	}
	
	public int modify(Schedule stu){
		return stuDAO.update(stu); 		
	}
	
	public int delete(int ID){
		return stuDAO.delete(ID); 		
	}
	
	public List<Schedule> Fetch(String condt){
//		List<Schedule> list = new ArrayList<Schedule>();
//		Schedule s1 = new Schedule();
//		s1.setSched_time(new Date());
//		list.add(s1);
//		Schedule s2 = new Schedule();
//		s2.setSched_time(new Date());
//		list.add(s2);
//		return list;		
		return stuDAO.select(condt);
	}
	
	public List<Schedule> FetchAll(){
		return stuDAO.select("");		
	}
}
