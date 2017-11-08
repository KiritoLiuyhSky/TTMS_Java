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






import xupt.se.ttms.model.Customer;
import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.CustomerSrv;
import xupt.se.ttms.service.SeatSrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.component.CircleButton;
import xupt.se.ttms.view.component.ImageJPanel;
import xupt.se.ttms.view.tmpl.*;

public class SeatAddUI extends PopUITmpl implements ActionListener {
	
	private static final long serialVersionUID = 1L;

	private JButton btnCancel, btnSave; 	//取消，保存按鈕

	protected boolean rst=false; 				//操作结果
	private JLabel lblStudioId, lblRow, lblColumn, lblStatus;
	protected JTextField txtStudioId, txtRow, txtColumn, txtStatus;
	protected JComboBox studiolist, status;

	@Override
	protected void initContent(){
		this.setTitle("座位管理");

		lblStudioId = new JLabel("演出厅名称：");
		lblStudioId.setBounds(350, 150, 110, 30);
		lblStudioId.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblStudioId);
//		txtStudioId = new JTextField();
//		txtStudioId.setBounds(450, 150, 200, 30);
//		contPan.add(txtStudioId);
		List<Studio> stuList = new StudioSrv().FetchAll();
		Iterator<Studio> it1 = stuList.iterator();
		Studio stud = null;
		studiolist = new JComboBox();
		studiolist.addItem(null);
		for(;it1.hasNext();){
			stud = it1.next();
			studiolist.addItem(stud.getName());
		}	
		studiolist.setBounds(450, 150, 200, 30);
		contPan.add(studiolist);

//		lblRow = new JLabel("座位所在行：");
//		lblRow.setBounds(350, 170, 180, 30);
//		lblRow.setFont(new Font("华文行楷", 1, 15));
//		contPan.add(lblRow);
//		txtRow = new JTextField();
//		txtRow.setBounds(450, 170, 200, 30);
//		contPan.add(txtRow);
//
//		lblColumn = new JLabel("座位所在列：");
//		lblColumn.setBounds(350, 230, 180, 30);
//		lblColumn.setFont(new Font("华文行楷", 1, 15));
//		contPan.add(lblColumn);
//		txtColumn = new JTextField();
//		txtColumn.setBounds(450, 230, 200, 30);
//		contPan.add(txtColumn);
		
		lblStatus = new JLabel("座位状态：");
		lblStatus.setBounds(350, 250, 180, 30);
		lblStatus.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblStatus);
//		txtStatus = new JTextField();
//		txtStatus.setBounds(450, 250, 200, 30);
//		contPan.add(txtStatus);
		status = new JComboBox();
		status.addItem(null);
		status.addItem("正常");
		status.addItem("损坏");
		status.setBounds(450, 250, 200, 30);
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
		if (studiolist.getSelectedItem() != null && status.getSelectedItem() != null) {

			
			//
			List<Studio> stuList = new StudioSrv().FetchAll();
			Iterator<Studio> it = stuList.iterator();
			Studio stud = null;
			int Row = 0;
			int Col = 0;
			for(;it.hasNext();){
				stud = it.next();
	            if (stud.getName().equals(studiolist.getSelectedItem().toString())){
	            	Row = stud.getRowCount();
	            	Col = stud.getColCount();
	            }
	        }
			

			SeatSrv seatSrv = new SeatSrv();
			Seat seat=new Seat();
			
			for (int i = 1; i <= Row; i++){
				for (int j = 1; j<= Col; j++){
					seat.setRow(i);
					seat.setColumn(j);
					
					//list studio
					List<Studio> stuList2 = new StudioSrv().FetchAll();
					Iterator<Studio> it2 = stuList2.iterator();
					Studio stud2 = null;
					for(;it2.hasNext();){
						stud2 = it2.next();
			            if (stud2.getName().equals(studiolist.getSelectedItem().toString())){
			            	seat.setStudioId(stud2.getID());
			            }
			        }
					
					//seat.setStudioId(Integer.parseInt(txtStudioId.getText()));
					//seat.setSeatStatus(Integer.parseInt(txtStatus.getText()));
					if(status.getSelectedItem().toString() == "正常") {
						seat.setSeatStatus(1);
					} 
					else if(status.getSelectedItem().toString() == "损坏") {
						seat.setSeatStatus(0);
					}
					
					seatSrv.add(seat);
				}
			}
			
			//
			
			this.setVisible(false);
			rst=true;
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}
}
