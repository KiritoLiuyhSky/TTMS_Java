package xupt.se.ttms.model;

public class Clerk {
	private int id=0      ; 
	private String nick="";
	private String name="" ;
	private String num="";
	private String psd="";
	private String sex="";
	private String date="";
	private String age="";
	private String phone="";
	private String intro="";
	
	public void setClerkID(int ID){
		this.id=ID;
	}
	
	public int getClerkID(){
		return id;
	}
	
	public void setClerkNick(String nick){
		this.nick=nick;
	}
	
	public String getClerkNick(){
		return nick;
	}
	
	public void setClerkName(String name){
		this.name=name;
	}
	
	public String getClerkName(){
		return name;
	}
	
	public void setClerkNum(String num) {
		this.num = num;
	}
	
	public String getClerkNum() {
		return num;
	}
	
	public void setClerkPsd(String psd) {
		this.psd = psd;
	}
	
	public String getClerkPsd() {
		return psd;
	}
	
	public void setClerkSex(String sex) {
		this.sex = sex;
	}
	
	public String getClerkSex() {
		return sex;
	}
	
	public void setClerkDate(String date) {
		this.date = date;
	}
	
	public String getClerkDate() {
		return date;
	}
	
	public void setClerkAge(String age) {
		this.age = age;
	}
	
	public String getClerkAge() {
		return age;
	}
	
	public void setClerkPhone(String phone) {
		this.phone = phone;
	}
	
	public String getClerkPhone() {
		return phone;
	}
	
	public void setClerkIntroduction(String intro){
		this.intro=intro;
	}
	
	public String getClerkIntroduction(){
		return intro;
	}	
	
}

