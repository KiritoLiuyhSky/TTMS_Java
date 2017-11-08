package xupt.se.ttms.service;

import java.util.ArrayList;
import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iPlayDAO;
import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Schedule;

public class PlaySrv {
	private iPlayDAO stuDAO=DAOFactory.creatPlayDAO();
	
	public int add(Play stu){
		return stuDAO.insert(stu); 		
	}
	
	public int modify(Play stu){
		return stuDAO.update(stu); 		
	}
	
	public int delete(int ID){
		return stuDAO.delete(ID); 		
	}
	
	public List<Play> Fetch(String condt){
		return stuDAO.select(condt);		
	}
	
	public List<Play> FetchAll(){
		return stuDAO.select("");		
	}
	
	public List<Play> selectScheduledPlay(String condt){
		List<Play> list = new ArrayList<Play>();
		Play play1 = new Play();
		//play1.setName = ;
		play1.setName("刀剑神域");
		Play play2 = new Play();
		play2.setName("天将雄狮");
		Play play3 = new Play();
		play3.setName("金刚狼");
		Play play4 = new Play();
		play4.setName("敢死队");
		Play play5 = new Play();
		play5.setName("功夫熊猫");
		Play play6 = new Play();
		play6.setName("宝葫芦的秘密");
		list.add(play1);
		list.add(play2);
		list.add(play3);
		list.add(play4);
		list.add(play5);
		list.add(play6);
		return list;
	}
}
