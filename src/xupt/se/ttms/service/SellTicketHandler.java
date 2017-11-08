package xupt.se.ttms.service;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import xupt.se.ttms.model.Ticket;


public class SellTicketHandler {
	private List<Ticket> curTickets;
	private double price;
	
	public void makeNewSale(){
		curTickets = new ArrayList<Ticket>();
	}

	public void addTicket(Ticket ticket) {
		curTickets.add(ticket);
		Date date = new Date();
    	final TicketSrv ticketSrv = new TicketSrv();
    	ticketSrv.lockTicket(ticket.getId(), DateFormat.getDateTimeInstance().format(date));
    	
    	final int id = ticket.getId();
    	
    	//
    	TimerTask task = new TimerTask() {  
    	      @Override  
    	      public void run() {  
    	        // task to run goes here  
    	        ticketSrv.unlockTicket(id);
    	      }  
    	    };  
    	 Timer timer = new Timer();  
    	 long delay = 20* 1000;  
    	 long intevalPeriod = 1000;  
    	 // schedules the task to be run in an interval  
    	 timer.scheduleAtFixedRate(task, delay,intevalPeriod); 
    	
    	ticket.setCurrent_locked_time(date);
	} 
	
	public void removeTicket(Ticket ticket) {
		curTickets.remove(ticket);
    	TicketSrv ticketSrv = new TicketSrv();
    	ticketSrv.unlockTicket(ticket.getId());
    	ticket.setCurrent_locked_time(null);
	} 
	
    public String getInfo(){
    	int i=0;
    	price = 0;
    	String info = "";
    	for(Ticket t: curTickets){
    		info+="影片："+t.getPlayName()+"\n";
    		info+="场次："+DateFormat.getDateTimeInstance().format(t.getSchedule().getSched_time())+"\n";
    		info+="座位："+t.getSeat().getRow()+"排"+t.getSeat().getColumn()+"座\n";
    		info+="价格："+t.getSchedule().getSched_ticket_price()+"元\n\n";
    		i++;
    		price+=t.getSchedule().getSched_ticket_price();
    	}
    	if(curTickets.size()>0){
    		info+="=====================\n";
    		info+="共"+i+"张票  "+ price + "元\n";
    	}
    	return info;
    }
    
    public boolean isTicketSelected(Ticket ticket){
    	for(Ticket t: curTickets){
    		if(t.getId()==ticket.getId())
    			return true;
    	}
    	return false;
    }
    
    public void clearSale(){
    	while(curTickets.size()>0){
    		removeTicket(curTickets.get(0));
    	}
    	makeNewSale();
    }

    public boolean doSale(){
    	if(new SaleSrv().doSale(curTickets, price)){
    		makeNewSale();
    		return true;
    	}
    	return false;
    }
}
