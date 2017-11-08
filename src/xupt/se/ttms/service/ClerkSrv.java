package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iClerkDAO;
import xupt.se.ttms.model.Clerk;

public class ClerkSrv {
	private iClerkDAO stuDAO=DAOFactory.creatClerkDAO();
	
	public int add(Clerk stu){
		return stuDAO.insert(stu); 		
	}
	
	public int modify(Clerk stu){
		return stuDAO.update(stu); 		
	}
	
	public int delete(int ID){
		return stuDAO.delete(ID); 		
	}
	
	public List<Clerk> Fetch(String condt){
		return stuDAO.select(condt);		
	}
	
	public List<Clerk> FetchAll(){
		return stuDAO.select("");		
	}
}
