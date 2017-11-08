package xupt.se.ttms.model;

import java.text.DateFormat;
import java.util.Date;

public class Schedule {
	private int sched_id;
	private int studio_id;
	private int mv_id;
	private Date sched_time;
	private float sched_ticket_price;
	
	public String toString(){
		if(sched_time==null)
			return "0:00:00";
		else
			return DateFormat.getTimeInstance().format(sched_time);
	}
	
	public int getSched_id() {
		return sched_id;
	}
	public void setSched_id(int sched_id) {
		this.sched_id = sched_id;
	}
	public int getStudio_id() {
		return studio_id;
	}
	public void setStudio_id(int studio_id) {
		this.studio_id = studio_id;
	}
	public int getMv_id() {
		return mv_id;
	}
	public void setMv_id(int mv_id) {
		this.mv_id = mv_id;
	}
	public Date getSched_time() {
		return sched_time;
	}
	public void setSched_time(Date sched_time) {
		this.sched_time = sched_time;
	}
	public float getSched_ticket_price() {
		return sched_ticket_price;
	}
	public void setSched_ticket_price(float sched_ticket_price) {
		this.sched_ticket_price = sched_ticket_price;
	}

}
