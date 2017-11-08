package xupt.se.ttms.view.usecase.ticket;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import xupt.se.ttms.model.Movie;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Ticket;
import xupt.se.ttms.service.MovieSrv;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.service.SeatSrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.service.TicketSrv;
import xupt.se.ttms.view.component.CircleButton;
import xupt.se.ttms.view.component.ImageJPanel;
import xupt.se.ttms.view.tmpl.*;

public class TicketAddUI extends PopUITmpl implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton btnCancel, btnSave; 	//取消，保存按鈕

	protected boolean rst=false; 				//操作结果
	private JLabel lblSeatid, lblScheid, lblPrice, lblStatus, lblLocked;
	protected JTextField txtSeatid, txtScheid, txtPrice, txtStatus, txtLocked;
	protected SimpleDateFormat dateFormat;
	protected JComboBox schedulelist, status;
	
	private String mvname, stuname;
	private int sch_id = 0;
	

	@Override
	protected void initContent(){
		this.setTitle("添加影票");

//		lblSeatid = new JLabel("座位id:");
//		lblSeatid.setBounds(350, 80, 110, 30);
//		lblSeatid.setFont(new Font("华文行楷", 1, 15));
//		contPan.add(lblSeatid);
//		txtSeatid = new JTextField();
//		txtSeatid.setBounds(450, 80, 200, 30);
//		contPan.add(txtSeatid);

		lblScheid = new JLabel("演出计划id:");
		lblScheid.setBounds(350, 120, 110, 30);
		lblScheid.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblScheid);
//		txtScheid = new JTextField();
//		txtScheid.setBounds(450, 140, 200, 30);
//		contPan.add(txtScheid);
		final List<Schedule> stuList = new ScheduleSrv().FetchAll();
		Iterator<Schedule> it1 = stuList.iterator();
		Schedule stud = null;
		schedulelist = new JComboBox();
		schedulelist.addItem(null);
		for(;it1.hasNext();){
			stud = it1.next();
			
			
			List<Studio> stuList1 = new StudioSrv().FetchAll();
			Iterator<Studio> its1 = stuList1.iterator();
			Studio stud1 = null;
			for(;its1.hasNext();){
				stud1 = its1.next();
				if(stud.getStudio_id() == stud1.getID()) {
					stuname = stud1.getName();
				}
			}	
			
			List<Movie> stuList2 = new MovieSrv().FetchAll();
			Iterator<Movie> its2 = stuList2.iterator();
			Movie stud2 = null;
			for(;its2.hasNext();){
				stud2 = its2.next();
				if(stud.getMv_id() == stud2.getMv_id()) {
					mvname = stud2.getName();
				}
			}	
			
			schedulelist.addItem(stuname + " , " + mvname + " , " + stud.getSched_time() + " , " + stud.getSched_ticket_price());
			
			//schedulelist.setToolTipText(stuname + " , " + mvname + " , " + stud.getSched_time() + " , " + stud.getSched_ticket_price());
		}		
		
		//
		schedulelist.addItemListener(new ItemListener() {
			public void itemStateChanged(final ItemEvent e) {
				int index = schedulelist.getSelectedIndex();
				sch_id = stuList.get(index).getSched_id();
				if (index != 0) { // ==0表示选中的事第一个
					String content = schedulelist.getSelectedItem().toString();
					//System.out.println("index222=" + index + ", content=" + content);
				}
			}
		});
		
		schedulelist.setBounds(450, 120, 200, 30);
		contPan.add(schedulelist);

		lblPrice = new JLabel("票价:");
		lblPrice.setBounds(350, 200, 110, 30);
		lblPrice.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblPrice);
		txtPrice = new JTextField();
		txtPrice.setBounds(450, 200, 200, 30);
		contPan.add(txtPrice);
		
		lblStatus = new JLabel("影票状态:");
		lblStatus.setBounds(350, 280, 110, 30);
		lblStatus.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblStatus);
//		txtStatus = new JTextField();
//		txtStatus.setBounds(450, 260, 200, 30);
//		contPan.add(txtStatus);
		status = new JComboBox();
		status.addItem(null);
		status.addItem("无座");
		status.addItem("待售");
		status.addItem("锁定");
		status.addItem("已售");
		status.setBounds(450, 280, 200, 30);
		contPan.add(status);
		
//		lblLocked = new JLabel("锁票时间:");
//		lblLocked.setBounds(350, 320, 110, 30);
//		lblLocked.setFont(new Font("华文行楷", 1, 15));
//		contPan.add(lblLocked);
//		txtLocked = new JTextField();
//		txtLocked.setBounds(450, 320, 200, 30);
//		contPan.add(txtLocked);
		
		btnSave = new CircleButton("保存");
		btnSave.addActionListener(this);
		btnSave.setBounds(250, 400, 60, 30);
		btnSave.setFont(new Font("华文行楷", 1, 12));
		contPan.add(btnSave);

		btnCancel = new CircleButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(450, 400, 60, 30);
		btnCancel.setFont(new Font("华文行楷", 1, 12));
		contPan.add(btnCancel);

		ImageJPanel imageJP = new ImageJPanel(new ImageIcon(
				"resource/image/stdio.jpg").getImage());
		imageJP.setBounds(100, 90, 170, 250);
		imageJP.setLayout(null);
		contPan.add(imageJP);
		
		JLabel title = new JLabel("The Ticket →");
		title.setBounds(140, 355, 170, 10);
		title.setFont(new Font("华文行楷", 1, 15));
		contPan.add(title);
	}
	
	
	public boolean getReturnStatus(){
		   return rst;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			rst=false;
			this.setVisible(false);
		} else if (e.getSource() == btnSave) {
			btnSaveClicked();
		}
	}
	
	protected void btnSaveClicked(){
		int id = 0;
		if (/*txtSeatid.getText() != null &&*/ schedulelist.getSelectedItem() != null
				&& txtPrice.getText() != null && status.getSelectedItem() != null) {
			TicketSrv stuSrv = new TicketSrv();
			Ticket stu=new Ticket();
			dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm" );
			
			//stu.setSeatId(Integer.parseInt(txtSeatid.getText()));
			
			List<Schedule> schList = new ScheduleSrv().FetchAll();
			Iterator<Schedule> it = schList.iterator();
			
			List<Seat> seaList = new SeatSrv().FetchAll();
			Iterator<Seat> its = seaList.iterator();
			
			Schedule sche = null;
			for(;it.hasNext();){
				sche = it.next();
//				if(sche.getSched_id() == Integer.parseInt(schedulelist.getSelectedItem().toString())) {
				if(sche.getSched_id() == sch_id) {
					Seat seat = null;
					for(;its.hasNext();){
						seat = its.next();
						if (sche.getStudio_id() == seat.getStudioId()){
							id = seat.getStudioId();
							break;
							//stu.setSeatId(seat.getId());
						}

					}
				}

	        }
			
			List<Seat> seList = new SeatSrv().FetchAll();
			Iterator<Seat> itse = seList.iterator();
			Seat se = null;
			
			for(;itse.hasNext();){
				se = itse.next();
				if(se.getStudioId() == id) {
					
					stu.setSeatId(se.getId());
					//stu.setScheduleId(Integer.parseInt(txtScheid.getText()));
					
					stu.setScheduleId(sch_id);
					
					stu.setPrice(Float.parseFloat(txtPrice.getText()));
					
					//stu.setStatus(Integer.parseInt(txtStatus.getText()));
					if(status.getSelectedItem().toString() == "无座") {
						stu.setStatus(-1);
					} 
					else if(status.getSelectedItem().toString() == "待售") {
						stu.setStatus(0);
					}
					else if(status.getSelectedItem().toString() == "锁定") {
						stu.setStatus(1);
					}
					else if(status.getSelectedItem().toString() == "已售") {
						stu.setStatus(9);
					}
					
					stuSrv.add(stu);
//					try {
//						//stu.setLocked_time(dateFormat.parse(txtLocked.getText()));
//						stu.setLocked_time(dateFormat.parse("NULL"));
//					} catch (ParseException e) {
//						// TODO 自动生成的 catch 块
//						e.printStackTrace();
//					}
				}
			
			}
//			stuSrv.add(stu);
			this.setVisible(false);
			rst=true;
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}

}

