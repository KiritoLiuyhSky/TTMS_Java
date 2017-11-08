package xupt.se.ttms.view.usecase.clerk;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import xupt.se.ttms.model.Clerk;
import xupt.se.ttms.service.ClerkSrv;
import xupt.se.ttms.view.component.CircleButton;
import xupt.se.ttms.view.component.ImageJPanel;
import xupt.se.ttms.view.tmpl.*;

public class ClerkAddUI extends PopUITmpl implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton btnCancel, btnSave; 	//取消，保存按鈕

	protected boolean rst=false; 				//操作结果
	private JLabel lblNick, lblPsd, lblName, lblId, lblSex, lblDate, lblAge, lblPhone, lblIntro;
	protected JTextField txtNick, txtPsd, txtName, txtId, txtSex, txtDate, txtAge, txtPhone;
	protected JTextArea txtIntro;
	protected JRadioButton man, woman;
	
	@Override
	protected void initContent(){
		this.setTitle("员工注册");

		lblNick = new JLabel("昵称:");
		lblNick.setBounds(75, 70, 140, 30);
		lblNick.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblNick);
		txtNick = new JTextField();
		txtNick.setBounds(135, 70, 180, 30);
		contPan.add(txtNick);

		lblPsd = new JLabel("密码:");
		lblPsd.setBounds(415, 70, 140, 30);
		lblPsd.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblPsd);
		txtPsd = new JTextField();
		txtPsd.setBounds(500, 70, 180, 30);
		contPan.add(txtPsd);

		lblName = new JLabel("姓名:");
		lblName.setBounds(75, 130, 140, 30);
		lblName.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblName);
		txtName = new JTextField();
		txtName.setBounds(135, 130, 180, 30);
		contPan.add(txtName);
		
		lblId = new JLabel("工号:");
		lblId.setBounds(415, 130, 140, 30);
		lblId.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblId);
		txtId = new JTextField();
		txtId.setBounds(500, 130, 180, 30);
		contPan.add(txtId);
		
		lblSex = new JLabel("性别:");
		lblSex.setBounds(75, 190, 120, 30);
		lblSex.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblSex);
//		txtSex = new JTextField();
//		txtSex.setBounds(135, 190, 180, 30);
//		contPan.add(txtSex);
		man = new JRadioButton("男");
		man.setBounds(135, 190, 60, 30);
		woman = new JRadioButton("女");
		woman.setBounds(200, 190, 60, 30);
		ButtonGroup group = new ButtonGroup();
		group.add(man);
		group.add(woman);
		contPan.add(man);
		contPan.add(woman);
		
		lblDate = new JLabel("出生日期:");
		lblDate.setBounds(415, 190, 120, 30);
		lblDate.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblDate);
		txtDate = new JTextField();
		txtDate.setBounds(500, 190, 180, 30);
		contPan.add(txtDate);
		
		lblAge = new JLabel("年龄:");
		lblAge.setBounds(75, 250, 120, 30);
		lblAge.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblAge);
		txtAge = new JTextField();
		txtAge.setBounds(135, 250, 180, 30);
		contPan.add(txtAge);
		
		
		lblPhone = new JLabel("联系方式:");
		lblPhone.setBounds(415, 250, 120, 30);
		lblPhone.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblPhone);
		txtPhone = new JTextField();
		txtPhone.setBounds(500, 250, 180, 30);
		contPan.add(txtPhone);
		
		
		lblIntro = new JLabel("个性签名:");
		lblIntro.setBounds(75, 300, 120, 30);
		lblIntro.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblIntro);
		txtIntro = new JTextArea();
		txtIntro.setBorder(BorderFactory.createLineBorder(Color.gray,1));
		txtIntro.setBounds(75, 335, 605, 90);
		contPan.add(txtIntro);
		
		btnSave = new CircleButton("注册");
		btnSave.addActionListener(this);
		btnSave.setBounds(250, 450, 60, 30);
		btnSave.setFont(new Font("华文行楷", 1, 12));
		contPan.add(btnSave);

		btnCancel = new CircleButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(450, 450, 60, 30);
		btnCancel.setFont(new Font("华文行楷", 1, 12));
		contPan.add(btnCancel);

		/*ImageJPanel imageJP = new ImageJPanel(new ImageIcon(
				"resource/image/stdio.jpg").getImage());
		imageJP.setBounds(45, 80, 170, 250);
		imageJP.setLayout(null);
		contPan.add(imageJP);
		
		JLabel title = new JLabel("The Stdio →");
		title.setBounds(85, 345, 170, 10);
		title.setFont(new Font("华文行楷", 1, 15));
		contPan.add(title);*/
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
		if (txtName.getText() != null && txtPsd.getText() != null
				&& (man.getText() != null || woman.getText() != null) && txtId.getText() != null 
				&& txtAge.getText() != null && txtDate.getText() != null 
				&& txtPhone.getText() != null && txtNick.getText() != null) {
			ClerkSrv stuSrv = new ClerkSrv();
			Clerk clr=new Clerk();
			clr.setClerkNick(txtNick.getText());
			clr.setClerkName(txtName.getText());
			clr.setClerkNum(txtId.getText());
			clr.setClerkPsd(txtPsd.getText());
			//clr.setClerkSex(txtSex.getText());
			if(man.isSelected()) {
				clr.setClerkSex(man.getText());
			} else {
				clr.setClerkSex(woman.getText());
			}
			clr.setClerkDate(txtDate.getText());
			clr.setClerkAge(txtAge.getText());
			clr.setClerkPhone(txtPhone.getText());
			clr.setClerkIntroduction(txtIntro.getText());

			stuSrv.add(clr);
			this.setVisible(false);
			rst=true;
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}

}

