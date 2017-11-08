package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.Customer;


public interface iCustomerDAO {
	public int insert(Customer stu);
	public int update(Customer stu);
	public int delete(int ID);
	public List<Customer> select(String condt); 
}
