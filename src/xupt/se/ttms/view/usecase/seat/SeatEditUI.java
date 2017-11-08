package xupt.se.ttms.view.usecase.seat;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.SeatSrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.component.CircleButton;
import xupt.se.ttms.view.component.ImageJPanel;
import xupt.se.ttms.view.tmpl.*;

public class SeatEditUI extends PopUITmpl implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	private JButton btnCancel, btnSave; 	//取消，保存按鈕

	protected boolean rst=false; 				//操作结果
	private JLabel lblStudioId, lblRow, lblColumn, lblStatus;
	protected JTextField txtStudioId, txtRow, txtColumn, txtStatus;
	protected JComboBox studiolist,status;

	private Seat se;
	
	public SeatEditUI(Seat seat) {
		this.setTitle("修改");
		initData(seat);
	}

	public void initData(Seat seat) {
		if(null== seat){
			return;
		}
		//txtStudioId.setText(Integer.toString(seat.getStudioId()));
		List<Studio> stuList = new StudioSrv().FetchAll();
		Iterator<Studio> it1 = stuList.iterator();
		Studio studi = null;
		for(;it1.hasNext();){
			studi = it1.next();
            if (seat.getStudioId() == studi.getID()){
            	//studiolist.setSelectedItem(String.valueOf(studi.getName()));
            	txtStudioId.setText(studi.getName());
            }
        }
		txtRow.setText(Integer.toString(seat.getRow()));
		txtColumn.setText(Integer.toString(seat.getColumn()));
		//txtStatus.setText(Integer.toString(seat.getSeatStatus()));
		if(seat.getSeatStatus() == 1) {
			status.setSelectedItem("正常");
		} else {
			status.setSelectedItem("损坏");
		}
		
		se=seat;
		this.invalidate();
	}
	
	@Override
	protected void initContent(){
		this.setTitle("座位管理");

		lblStudioId = new JLabel("演出厅名称：");
		lblStudioId.setBounds(350, 110, 110, 30);
		lblStudioId.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblStudioId);
		txtStudioId = new JTextField();
		txtStudioId.setBounds(450, 110, 200, 30);
		txtStudioId.setEditable(false);
		contPan.add(txtStudioId);
//		List<Studio> stuList = new StudioSrv().FetchAll();
//		Iterator<Studio> it1 = stuList.iterator();
//		Studio stud = null;
//		studiolist = new JComboBox();
//		studiolist.addItem(null);
//		for(;it1.hasNext();){
//			stud = it1.next();
//			studiolist.addItem(stud.getName());
//		}	
//		studiolist.setBounds(450, 110, 200, 30);
//		contPan.add(studiolist);

		lblRow = new JLabel("座位所在行：");
		lblRow.setBounds(350, 170, 180, 30);
		lblRow.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblRow);
		txtRow = new JTextField();
		txtRow.setBounds(450, 170, 200, 30);
		txtRow.setEditable(false);
		contPan.add(txtRow);

		lblColumn = new JLabel("座位所在列：");
		lblColumn.setBounds(350, 230, 180, 30);
		lblColumn.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblColumn);
		txtColumn = new JTextField();
		txtColumn.setBounds(450, 230, 200, 30);
		txtColumn.setEditable(false);
		contPan.add(txtColumn);
		
		lblStatus = new JLabel("座位状态：");
		lblStatus.setBounds(350, 290, 180, 30);
		lblStatus.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblStatus);
//		txtStatus = new JTextField();
//		txtStatus.setBounds(450, 290, 200, 30);
//		contPan.add(txtStatus);
		status = new JComboBox();
		status.addItem(null);
		status.addItem("正常");
		status.addItem("损坏");
		status.setBounds(450, 290, 200, 30);
		contPan.add(status);

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
		
		JLabel title = new JLabel("The Seat →");
		title.setBounds(140, 355, 170, 10);
		title.setFont(new Font("华文行楷", 1, 15));
		contPan.add(title);

//		ImageJPanel imageJP = new ImageJPanel(new ImageIcon(
//				"files/imgs/pencil.jpg").getImage());
//		imageJP.setBounds(360, 160, 100, 100);
//		imageJP.setLayout(null);
//		contPan.add(imageJP);
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
		if (txtStudioId != null && txtRow.getText() != null
				&& txtColumn.getText() != null && status.getSelectedItem() != null) {
			
			SeatSrv seatSrv = new SeatSrv();
			Seat seat = se;
			//seat.setStudioId(Integer.parseInt(txtStudioId.getText())); 
			List<Studio> stuList = new StudioSrv().FetchAll();
			Iterator<Studio> it1 = stuList.iterator();
			Studio stud = null;
			for(;it1.hasNext();){
				stud = it1.next();
	            if (stud.getName().equals(txtStudioId.getText())){
	            	seat.setStudioId(stud.getID());
	            }
	        }
			
			seat.setRow(Integer.parseInt(txtRow.getText()));
			seat.setColumn(Integer.parseInt(txtColumn.getText()));  
			//seat.setSeatStatus(Integer.parseInt(txtStatus.getText()));
			if(status.getSelectedItem().toString() == "正常") {
				seat.setSeatStatus(1);
			}
			else if(status.getSelectedItem().toString() == "损坏") {
				seat.setSeatStatus(0);
			}

			seatSrv.modify(seat);
			this.setVisible(false);
			rst=true;
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}
}

//package xupt.se.ttms.view.usecase.seat;
//
//import java.util.Iterator;
//import java.util.List;
//
//import javax.swing.JOptionPane;
//
//import xupt.se.ttms.model.Seat;
//import xupt.se.ttms.model.Studio;
//import xupt.se.ttms.service.SeatSrv;
//import xupt.se.ttms.service.StudioSrv;
//import xupt.se.ttms.view.usecase.seat.SeatAddUI;
//
//public class SeatEditUI extends SeatAddUI{
//	
//	private static final long serialVersionUID = 1L;
//	private Seat se;
//
//	public SeatEditUI(Seat seat) {
//		initData(seat);
//	}
//
//	public void initData(Seat seat) {
//		if(null== seat){
//			return;
//		}
//		//txtStudioId.setText(Integer.toString(seat.getStudioId()));
//		List<Studio> stuList = new StudioSrv().FetchAll();
//		Iterator<Studio> it1 = stuList.iterator();
//		Studio studi = null;
//		for(;it1.hasNext();){
//			studi = it1.next();
//          if (seat.getStudioId() == studi.getID()){
//          	studiolist.setSelectedItem(String.valueOf(studi.getName()));
//          }
//      }
//		txtRow.setText(Integer.toString(seat.getRow()));
//		txtColumn.setText(Integer.toString(seat.getColumn()));
//		txtStatus.setText(Integer.toString(seat.getSeatStatus()));
//		se=seat;
//		this.invalidate();
//	}
//
//	@Override
//	protected void btnSaveClicked(){
//		if (studiolist.getSelectedItem() != null && txtRow.getText() != null
//				&& txtColumn.getText() != null && txtStatus.getText() != null) {
//			SeatSrv seatSrv = new SeatSrv();
//			Seat seat = se;
//			//seat.setStudioId(Integer.parseInt(txtStudioId.getText())); 
//			List<Studio> stuList = new StudioSrv().FetchAll();
//			Iterator<Studio> it1 = stuList.iterator();
//			Studio stud = null;
//			for(;it1.hasNext();){
//				stud = it1.next();
//	            if (stud.getName().equals(studiolist.getSelectedItem().toString())){
//	            	seat.setStudioId(stud.getID());
//	            }
//	        }
//			
//			seat.setRow(Integer.parseInt(txtRow.getText()));
//			seat.setColumn(Integer.parseInt(txtColumn.getText()));  
//			seat.setSeatStatus(Integer.parseInt(txtStatus.getText()));
//
//			seatSrv.modify(seat);
//			this.setVisible(false);
//			rst=true;
//		} else {
//			JOptionPane.showMessageDialog(null, "数据不完整");
//		}		
//	}
//
//}