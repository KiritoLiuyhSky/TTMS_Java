package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iCustomerDAO;
import xupt.se.ttms.model.Customer;


public class CustomerSrv {
	private iCustomerDAO stuDAO=DAOFactory.creatCustomerDAO();
	
	public int add(Customer stu){
		return stuDAO.insert(stu); 		
	}
	
	public int modify(Customer stu){
		return stuDAO.update(stu); 		
	}
	
	public int delete(int ID){
		return stuDAO.delete(ID); 		
	}
	
	public List<Customer> Fetch(String condt){
		return stuDAO.select(condt);		
	}
	
	public List<Customer> FetchAll(){
		return stuDAO.select("");		
	}
}
