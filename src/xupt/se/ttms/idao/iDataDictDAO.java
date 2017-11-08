package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.DataDict;

public interface iDataDictDAO {
	public int insert(DataDict ddict);
	public int update(DataDict ddict);
	public int delete(int ID);
	public List<DataDict> select(String condt); 
	public List<DataDict> findByID(int id) ;
	public void findAllSonByID(List<DataDict> list, int id);
	public boolean hasChildren(int id) ;
}
