package xupt.se.ttms.service;


import java.util.ArrayList;
import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iMovieDAO;
import xupt.se.ttms.model.Movie;

public class MovieSrv {
	private iMovieDAO stuDAO=DAOFactory.creatMovieDAO();
	
	public int add(Movie stu){
		return stuDAO.insert(stu); 		
	}
	
	public int modify(Movie stu){
		return stuDAO.update(stu); 		
	}
	
	public int delete(int ID){
		return stuDAO.delete(ID); 		
	}
	
	public List<Movie> Fetch(String condt){
		return stuDAO.select(condt);		
	}
	
	public List<Movie> FetchAll(){
		return stuDAO.select("");		
	}
	
	public List<Movie> selectScheduledMovie(String condt){
		return stuDAO.select(condt);
	}
}
