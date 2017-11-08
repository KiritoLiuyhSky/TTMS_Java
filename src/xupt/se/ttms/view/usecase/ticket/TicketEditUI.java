package xupt.se.ttms.view.usecase.ticket;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Ticket;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.service.SeatSrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.service.TicketSrv;
import xupt.se.ttms.view.component.CircleButton;
import xupt.se.ttms.view.component.ImageJPanel;
import xupt.se.ttms.view.tmpl.*;

public class TicketEditUI extends PopUITmpl implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton btnCancel, btnSave; 	//取消，保存按鈕

	protected boolean rst=false; 				//操作结果
	private JLabel lblSeatid, lblScheid, lblPrice, lblStatus, lblLocked;
	protected JTextField txtSeatid, txtScheid, txtPrice, txtStatus, txtLocked;
	protected SimpleDateFormat dateFormat;
	protected JComboBox schedulelist, status;
	
	protected int seat_id,sch_id;
	
	private Ticket stud;

	public TicketEditUI(Ticket tic){
		this.setTitle("修改");
		initData(tic);
	}
	
	public void initData(Ticket tic) {
		if(null== tic){
			return;
		}
		//dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm" );
		
		txtSeatid.setText(Integer.toString(tic.getSeatId()));
		
		seat_id = tic.getSeatId();
		List<Seat> seatList = new SeatSrv().FetchAll();
		Iterator<Seat> it1 = seatList.iterator();
		Seat se = null;
		for(;it1.hasNext();){
			se = it1.next();
            if (se.getId() == tic.getSeatId()){
				List<Studio> studiolist = new StudioSrv().FetchAll();
				Iterator<Studio> its = studiolist.iterator();
				Studio studio = null;
				for(;its.hasNext();) {
					studio = its.next();
					if(studio.getID() == se.getStudioId()){
						txtSeatid.setText(studio.getName() + se.getRow() + "排" + se.getColumn() + "座");
					}
				}
            	
            }
        }
		
		sch_id = tic.getScheduleId();
		
		txtScheid.setText(Integer.toString(tic.getScheduleId()));
		//schedulelist.setSelectedItem(tic.getScheduleId());
		txtPrice.setText(Float.toString(tic.getPrice()));
		//txtStatus.setText(Integer.toString(tic.getStatus()));
		if(tic.getStatus() == -1) {
			status.setSelectedItem("无座");
		} 
		else if(tic.getStatus() == 0){
			status.setSelectedItem("待售");
		}
		else if(tic.getStatus() == 1){
			status.setSelectedItem("锁定");
		}
		else if(tic.getStatus() == 9){
			status.setSelectedItem("已售");
		}
		
		//txtLocked.setText(dateFormat.format(tic.getLocked_time()));
		
		stud=tic;
		this.invalidate();
	}

	
	@Override
	protected void initContent(){
		this.setTitle("添加影票");

		lblSeatid = new JLabel("座位id:");
		lblSeatid.setBounds(350, 100, 110, 30);
		lblSeatid.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblSeatid);
		txtSeatid = new JTextField();
		txtSeatid.setBounds(450, 100, 200, 30);
		txtSeatid.setEditable(false);
		contPan.add(txtSeatid);

		lblScheid = new JLabel("演出计划id:");
		lblScheid.setBounds(350, 170, 110, 30);
		lblScheid.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblScheid);
		txtScheid = new JTextField();
		txtScheid.setBounds(450, 170, 200, 30);
		txtScheid.setEditable(false);
		contPan.add(txtScheid);
//		List<Schedule> stuList = new ScheduleSrv().FetchAll();
//		Iterator<Schedule> it1 = stuList.iterator();
//		Schedule stud = null;
//		schedulelist = new JComboBox();
//		schedulelist.addItem(null);
//		for(;it1.hasNext();){
//			stud = it1.next();
//			schedulelist.addItem(stud.getSched_id());
//		}		
//		schedulelist.setBounds(450, 170, 200, 30);
//		contPan.add(schedulelist);

		lblPrice = new JLabel("票价:");
		lblPrice.setBounds(350, 240, 110, 30);
		lblPrice.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblPrice);
		txtPrice = new JTextField();
		txtPrice.setBounds(450, 240, 200, 30);
		contPan.add(txtPrice);
		
		lblStatus = new JLabel("影票状态:");
		lblStatus.setBounds(350, 300, 110, 30);
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
		status.setBounds(450, 300, 200, 30);
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
		if (txtSeatid.getText() != null && txtScheid.getText() != null
				&& txtPrice.getText() != null && status.getSelectedItem() != null) {
			
			TicketSrv ticSrv = new TicketSrv();
			Ticket tic = stud;
			dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm" );
			
			
//			int id = 0;
//			List<Schedule> schList = new ScheduleSrv().FetchAll();
//			Iterator<Schedule> it = schList.iterator();
//			Schedule sche = null;
//			for(;it.hasNext();){
//				sche = it.next();
//				if(sche.getSched_id() == Integer.parseInt(schedulelist.getSelectedItem().toString())) {
//					List<Seat> seaList = new SeatSrv().FetchAll();
//					Iterator<Seat> its = seaList.iterator();
//					Seat seat = null;
//					for(;its.hasNext();){
//						seat = its.next();
//						if (sche.getStudio_id() == seat.getStudioId()){
//							id = seat.getStudioId();
//							//stu.setSeatId(seat.getId());
//						}
//					}
//				}
//	        }
			
			tic.setSeatId(seat_id);
			tic.setScheduleId(sch_id);
			tic.setPrice(Float.parseFloat(txtPrice.getText()));
			
//			List<Seat> seList = new SeatSrv().FetchAll();
//			Iterator<Seat> itse = seList.iterator();
//			Seat se = null;
			
			if(status.getSelectedItem().toString() == "无座") {
				tic.setStatus(-1);
			} 
			else if(status.getSelectedItem().toString() == "待售") {
				tic.setStatus(0);
			}
			else if(status.getSelectedItem().toString() == "锁定") {
				tic.setStatus(1);
			}
			else if(status.getSelectedItem().toString() == "已售") {
				tic.setStatus(9);
			}
			
//			for(;itse.hasNext();){
//				se = itse.next();
//				if(se.getStudioId() == id) {
//					tic.setSeatId(se.getId());
					//stu.setScheduleId(Integer.parseInt(txtScheid.getText()));
//					tic.setScheduleId(Integer.parseInt(schedulelist.getSelectedItem().toString()));
					
					

					
					//stu.setStatus(Integer.parseInt(txtStatus.getText()));
//					if(status.getSelectedItem().toString() == "无座") {
//						tic.setStatus(-1);
//					} 
//					else if(status.getSelectedItem().toString() == "待售") {
//						tic.setStatus(0);
//					}
//					else if(status.getSelectedItem().toString() == "锁定") {
//						tic.setStatus(1);
//					}
//					else if(status.getSelectedItem().toString() == "已售") {
//						tic.setStatus(9);
//					}
					
//					try {
//						//stu.setLocked_time(dateFormat.parse(txtLocked.getText()));
//						stu.setLocked_time(dateFormat.parse("NULL"));
//					} catch (ParseException e) {
//						// TODO 自动生成的 catch 块
//						e.printStackTrace();
//					}
//				}
			
//			}

			ticSrv.modify(tic);
			this.setVisible(false);
			rst=true;
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}

}

