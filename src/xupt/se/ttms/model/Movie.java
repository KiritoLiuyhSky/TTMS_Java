package xupt.se.ttms.model;

public class Movie {
	private int mv_id;					//电影id			//主键
	private String img;					//图片
	private String name;				//名称
	private float score;				//影评	
	private String director;			//导演
	private String actor;				//主演
	private String type;				//类型
	private String time;				//时长
	private String date;					//日期
	private String introduction=""; 	//简介
	private float price;				//票价
	private String status;				//状态
	
	public void setMv_id(int mv_id) {
		this.mv_id = mv_id;
	}
	public int getMv_id() {
		return mv_id;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getImg() {
		return img;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public float getScore() {
		return score;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getDirector() {
		return director;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getActor() {
		return actor;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}

	public void setTime(String time) {
		this.time = time;
	}
	public String getTime() {
		return time;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDate() {
		return date;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getPrice() {
		return price;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	
}
