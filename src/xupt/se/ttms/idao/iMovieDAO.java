package xupt.se.ttms.idao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import xupt.se.ttms.model.Movie;
import xupt.se.ttms.model.Studio;

public interface iMovieDAO {
	public int insert(Movie stu);
	public int update(Movie stu);
	public int delete(int ID);
	public List<Movie> select(String condt); 
}
