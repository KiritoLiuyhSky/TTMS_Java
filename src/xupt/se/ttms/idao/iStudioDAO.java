/**
 * 
 */
package xupt.se.ttms.idao;
import xupt.se.ttms.model.Studio;

import java.util.List;

/**
 * @author Administrator
 *
 */
public interface iStudioDAO {
	public int insert(Studio stu);
	public int update(Studio stu);
	public int delete(int ID);
	public List<Studio> select(String condt); 
}
