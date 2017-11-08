package xupt.se.ttms.view.usecase.studio;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.component.CircleButton;
import xupt.se.ttms.view.component.ImageJPanel;
import xupt.se.ttms.view.tmpl.*;

public class StudioAddUI extends PopUITmpl implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton btnCancel, btnSave; 	//取消，保存按鈕

	protected boolean rst=false; 				//操作结果
	private JLabel lblName, lblRow, lblColumn, lblIntro;
	protected JTextField txtName, txtRow, txtColumn;
	protected JTextArea txtIntro;
	

	@Override
	protected void initContent(){
		this.setTitle("添加演出厅");

		lblName = new JLabel("演出厅名称:");
		lblName.setBounds(235, 60, 110, 30);
		lblName.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblName);
		txtName = new JTextField();
		txtName.setBounds(335, 60, 400, 30);
		contPan.add(txtName);

		lblRow = new JLabel("座位  行数:");
		lblRow.setBounds(235, 110, 180, 30);
		lblRow.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblRow);
		txtRow = new JTextField();
		txtRow.setBounds(335, 110, 120, 30);
		contPan.add(txtRow);

		lblColumn = new JLabel("座位  列数:");
		lblColumn.setBounds(515, 110, 180, 30);
		lblColumn.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblColumn);
		txtColumn = new JTextField();
		txtColumn.setBounds(615, 110, 120, 30);
		contPan.add(txtColumn);
		
		lblIntro = new JLabel("演出厅简介:");
		lblIntro.setBounds(235, 160, 110, 30);
		lblIntro.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblIntro);
		txtIntro = new JTextArea();
		txtIntro.setBorder(BorderFactory.createLineBorder(Color.gray,1));
		txtIntro.setBounds(335, 160, 400, 200);
		contPan.add(txtIntro);

		
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
		imageJP.setBounds(45, 80, 170, 250);
		imageJP.setLayout(null);
		contPan.add(imageJP);
		
		JLabel title = new JLabel("The Stdio →");
		title.setBounds(85, 345, 170, 10);
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
		if (txtName.getText() != null && txtRow.getText() != null
				&& txtColumn.getText() != null) {
			StudioSrv stuSrv = new StudioSrv();
			Studio stu=new Studio();
			stu.setName(txtName.getText());
			stu.setRowCount(Integer.parseInt(txtRow.getText()));
			stu.setColCount(Integer.parseInt(txtColumn.getText()));
			stu.setIntroduction(txtIntro.getText());

			stuSrv.add(stu);
			this.setVisible(false);
			rst=true;
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}

}
