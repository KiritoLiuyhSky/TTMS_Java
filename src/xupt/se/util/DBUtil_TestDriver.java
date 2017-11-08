package xupt.se.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.ResultSet;

import java.sql.Blob;


public class DBUtil_TestDriver {

	//测试一般数据写入
	private static void test_insert(){
		DBUtil db = new DBUtil();
		db.openConnection();
		String sql = "insert into studio(studio_name, studio_row_count, studio_col_count, studio_introduction )"
				+ " values(?,?, ?, ?)";
		Object [] params = new Object[4];
		params[0]=new String("test2");
		params[1]=new Integer(2);
		params[2]=new Integer(2);
		params[3]=new String("just a test");
		try {
			ResultSet rst= db.getInsertObjectIDs(sql, params);
			
			if (rst!=null && rst.first()) {
				System.out.println(rst.getInt(1));
			}
			
			db.close(rst);
			db.close();		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	//测试lob数据写入
	private static void test_insert_lob(){
			
		String sql = "insert into play(play_type_id, play_lang_id, play_name, play_ticket_price, play_image )"
				+ " values(?,?, ?, ?, ?)";
		Object [] params = new Object[5];
		params[0]=null;
		params[1]=null;
		params[2]=new String("just a test");
		params[3]=new Float(5);
		
		FileInputStream fis = null;
		File file = new File("resource/image/header.jpg"); //测试写图片
		 
		try {
			DBUtil db = new DBUtil();
			db.openConnection();
			 fis = new FileInputStream(file);
			 params[4]=fis;
			 
			ResultSet rst= db.getInsertObjectIDs(sql, params);
			
			if (rst!=null && rst.first()) {
				System.out.println(rst.getInt(1));
			}
			
			db.close(rst);
			db.close();		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	//测试lob数据读取
	private static void test_read_lob(){
		String sql = "select * from play";
		
		FileInputStream fis = null;
		 
		try {
			DBUtil db = new DBUtil();
			db.openConnection();
			ResultSet rst = db.execQuery(sql);
			if (rst!=null) {
				while(rst.next()){
					System.out.println(rst.getString("play_name"));
					System.out.println(rst.getFloat("play_ticket_price"));
					int playID=rst.getInt("play_id");
					
					byte[] buf = new byte[256];    
  				    Blob blob = rst.getBlob("play_image");
  				    if(blob!=null ){
  				    	//需要在在工程目录下建立路径Cache/Play_Image/，然后将照片缓存到该路径下
  						File file = new File("Cache/Play_Image/"+ playID + ".jpg");
  						FileOutputStream sout = new FileOutputStream(file);  
	  				    InputStream in = blob.getBinaryStream();//获取BLOB数据的输入数据流   
						
						for (int i = in.read(buf); i != -1;) {    
						     sout.write(buf);    
						     i = in.read(buf);   
						 }
						in.close();
						 sout.close();  				    
  				    }
					 
				}
			}
			
			db.close(rst);
			db.close();		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//test_insert();
		test_insert_lob();
		test_read_lob();
	}

}
