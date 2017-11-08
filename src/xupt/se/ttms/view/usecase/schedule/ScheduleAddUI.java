package xupt.se.ttms.view.usecase.schedule;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;

import xupt.se.ttms.model.Movie;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.MovieSrv;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.component.CircleButton;
import xupt.se.ttms.view.component.ImageJPanel;
import xupt.se.ttms.view.tmpl.*;

public class ScheduleAddUI extends PopUITmpl implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton btnCancel, btnSave; 	//取消，保存按鈕

	protected boolean rst=false; 				//操作结果
	private JLabel lblStdio, lblMovie, lblDate, lblPrice;
	protected JTextField txtStdio, txtMovie, txtDate, txtPrice;
	protected SimpleDateFormat dateFormat;
	protected JComboBox studiolist, movielist;
	

	@Override
	protected void initContent(){
		this.setTitle("添加演出计划");
		
		lblStdio = new JLabel("演出厅名称:");
		lblStdio.setBounds(350, 100, 110, 30);
		lblStdio.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblStdio);
//		txtStdio = new JTextField();
//		txtStdio.setBounds(450, 100, 200, 30);
//		contPan.add(txtStdio);
		List<Studio> stuList = new StudioSrv().FetchAll();
		Iterator<Studio> it1 = stuList.iterator();
		Studio stud = null;
		studiolist = new JComboBox();
		studiolist.addItem(null);
		for(;it1.hasNext();){
			stud = it1.next();
			studiolist.addItem(stud.getName());
		}	
		studiolist.setBounds(450, 100, 200, 30);
		contPan.add(studiolist);

		lblMovie = new JLabel("影片名称:");
		lblMovie.setBounds(350, 160, 110, 30);
		lblMovie.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblMovie);
//		txtMovie = new JTextField();
//		txtMovie.setBounds(450, 160, 200, 30);
//		contPan.add(txtMovie);
		List<Movie> movList = new MovieSrv().FetchAll();
		Iterator<Movie> it2 = movList.iterator();
		Movie mov = it2.next();
		movielist = new JComboBox();
		movielist.addItem(null);
		for(;it2.hasNext();mov = it2.next()) {
			movielist.addItem(mov.getName());
		}
		movielist.addItem(mov.getName());
		movielist.setBounds(450, 160, 200, 30);
		contPan.add(movielist);

		lblDate = new JLabel("放映时间:");
		lblDate.setBounds(350, 220, 110, 30);
		lblDate.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblDate);
		txtDate = new JTextField();
		txtDate.setBounds(450, 220, 200, 30);
		contPan.add(txtDate);
		
		lblPrice = new JLabel("票价:");
		lblPrice.setBounds(350, 280, 110, 30);
		lblPrice.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblPrice);
		txtPrice = new JTextField();
		txtPrice.setBounds(450, 280, 200, 30);
		contPan.add(txtPrice);
		
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
		imageJP.setBounds(100, 80, 170, 250);
		imageJP.setLayout(null);
		contPan.add(imageJP);
		
		JLabel title = new JLabel("The Schedule →");
		title.setBounds(140, 345, 170, 10);
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
		if (/*txtStdio.getText() != null && txtMovie.getText() != null*/
				studiolist.getSelectedItem() != null && movielist.getSelectedItem() != null
				&& txtDate.getText() != null && txtPrice.getText() != null ) {
			ScheduleSrv stuSrv = new ScheduleSrv();
			Schedule stu=new Schedule();
			
			//list studio
			List<Studio> stuList = new StudioSrv().FetchAll();
			Iterator<Studio> it1 = stuList.iterator();
			Studio stud = null;
			for(;it1.hasNext();){
				stud = it1.next();
	            if (stud.getName().equals(studiolist.getSelectedItem().toString())){
	            	stu.setStudio_id(stud.getID());
	            }
	        }
			
			//list movie
			List<Movie> movList = new MovieSrv().FetchAll();
			Iterator<Movie> it2 = movList.iterator();
			Movie movi = null;
			for(;it2.hasNext();){
				movi = it2.next();
	            if (movi.getName().equals(movielist.getSelectedItem().toString())){
	            	stu.setMv_id(movi.getMv_id());
	            }
	        }
			
//			stu.setStudio_id(Integer.parseInt(studiolist.getSelectedItem().toString()));
//			stu.setMv_id(Integer.parseInt(movielist.getSelectedItem().toString()));
//			stu.setStudio_id(Integer.parseInt(txtStdio.getText()));
//			stu.setMv_id(Integer.parseInt(txtMovie.getText()));
			
			dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm" );
			try {
				stu.setSched_time(dateFormat.parse(txtDate.getText()));		
			} catch (ParseException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
			stu.setSched_ticket_price((Float.parseFloat(txtPrice.getText())));

			stuSrv.add(stu);
			this.setVisible(false);
			rst=true;
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}

}
