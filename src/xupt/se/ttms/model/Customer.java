package xupt.se.ttms.model;

public class Customer {
	private int id=0      ; 
	private String nick="";
	private String psd="";
	private String name="" ;
	private String email="";
	private String sex="";
	private String date="";
	private String age="";
	private String phone="";
	private String intro="";
	
	public void setID(int ID){
		this.id=ID;
	}
	
	public int getID(){
		return id;
	}
	
	public void setNick(String nick){
		this.nick=nick;
	}
	
	public String getNick(){
		return nick;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPsd(String psd) {
		this.psd = psd;
	}
	
	public String getPsd() {
		return psd;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setAge(String age) {
		this.age = age;
	}
	
	public String getAge() {
		return age;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setIntroduction(String intro){
		this.intro=intro;
	}
	
	public String getIntroduction(){
		return intro;
	}	
	
}

