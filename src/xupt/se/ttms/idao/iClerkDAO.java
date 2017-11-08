package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.Clerk;

public interface iClerkDAO {
	public int insert(Clerk stu);
	public int update(Clerk stu);
	public int delete(int ID);
	public List<Clerk> select(String condt); 
}
