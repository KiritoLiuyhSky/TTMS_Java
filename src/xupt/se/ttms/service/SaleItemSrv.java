package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iSaleItemDAO;
import xupt.se.ttms.model.SaleItem;


public class SaleItemSrv {
	private iSaleItemDAO stuDAO=DAOFactory.creatSaleItemDAO();
	
	public List<SaleItem> Fetch(String condt){
		return stuDAO.select(condt);		
	}
	
	public List<SaleItem> FetchAll(){
		return stuDAO.select("");		
	}
}
