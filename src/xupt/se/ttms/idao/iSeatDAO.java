/**
 * 
 */
package xupt.se.ttms.idao;
import xupt.se.ttms.model.Seat;

import java.util.List;

/**
 * @author Administrator
 *
 */
public interface iSeatDAO {
	public int insert(Seat stu);
	public int update(Seat stu);
	public int delete(int ID);
	public List<Seat> select(String condt); 
}
