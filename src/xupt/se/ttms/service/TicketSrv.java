package xupt.se.ttms.service;

import java.util.ArrayList;
import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iTicketDAO;
import xupt.se.ttms.model.Ticket;

public class TicketSrv {
	private iTicketDAO stuDAO=DAOFactory.creatTicketDAO();
	
	public int add(Ticket stu){
		return stuDAO.insert(stu); 		
	}
	
	public int modify(Ticket stu){
		return stuDAO.update(stu); 		
	}
	
	public int delete(int ID){
		return stuDAO.delete(ID); 		
	}
	
	public List<Ticket> Fetch(String condt){
		return stuDAO.select(condt);		
	}
	
	public List<Ticket> FetchAll(){
		return stuDAO.select("");		
	}
	
	public int lockTicket(int ID, String time) {
		return stuDAO.lockTicket(ID, time);
	}

	public int unlockTicket(int ID) {
		return stuDAO.unlockTicket(ID);
	}
}
