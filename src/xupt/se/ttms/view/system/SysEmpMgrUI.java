package xupt.se.ttms.view.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import xupt.se.ttms.model.Clerk;
import xupt.se.ttms.model.Customer;
import xupt.se.ttms.service.ClerkSrv;
import xupt.se.ttms.service.CustomerSrv;
import xupt.se.ttms.view.component.CircleButton;
import xupt.se.ttms.view.frame.ClerkMenuFrame;
import xupt.se.ttms.view.tmpl.PopUITmpl;
import xupt.se.ttms.view.tmpl.PopUITmplClerk;
import xupt.se.ttms.view.tmpl.PopUITmplCus;
import xupt.se.ttms.view.tmpl.PopUITmplEmp;
import xupt.se.ttms.view.usecase.clerk.ClerkEditUI;
import xupt.se.ttms.view.usecase.clerk.ClerkMgrUI;
import xupt.se.ttms.view.usecase.customer.CustomerMgrUI;
import xupt.se.ttms.view.usecase.customer.CustomerMgrUIEmp;

public class SysEmpMgrUI extends PopUITmplEmp {

	private static final long serialVersionUID = 1025028999012028956L;
	private static String username;
	private static int userid;

	public SysEmpMgrUI(String name, int id){
		super(name, id);
		username = name;
		userid = id;
		initContent();
	}

	@Override
	protected void initContent() {
		this.setTitle("这就是命剧院票务管理系统——帐号管理");

		JPanel workPanel = new JPanel();
		workPanel.setLayout(null);
		workPanel.setBounds(0, 0, 800, 630);
		
		JButton clerk = new CircleButton("");
		clerk.setVerticalTextPosition(SwingConstants.BOTTOM);
		clerk.setHorizontalTextPosition(SwingConstants.CENTER);
		clerk.setIcon(new ImageIcon("resource/image/user105.png"));
		clerk.setBackground(Color.WHITE);
		clerk.setText("用户管理");
		clerk.setBounds(160, 160, 160, 160);
		clerk.setFont(new Font("华文行楷", 1, 15));
		
		clerk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				CustomerMgrUIEmp users = new CustomerMgrUIEmp(username, userid);
				users.setVisible(true);
				SysEmpMgrUI.this.dispose();
			}
		});
		
		JButton user = new CircleButton("");
		user.setVerticalTextPosition(SwingConstants.BOTTOM);
		user.setHorizontalTextPosition(SwingConstants.CENTER);
		user.setIcon(new ImageIcon("resource/image/user106.png"));
		user.setBackground(Color.WHITE);
		user.setText("个人信息");
		user.setBounds(460, 160, 160, 160);
		user.setFont(new Font("华文行楷", 1, 15));
		
		user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				List<Clerk> cuslist = new ClerkSrv().Fetch("ck_id = " + userid);
				Iterator<Clerk> it1 = cuslist.iterator();
				Clerk stud = it1.next();
				
				ClerkEditUI modStuUI = new ClerkEditUI(stud);
				modStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				modStuUI.setWindowName("个人信息");
				modStuUI.initData(stud);
				modStuUI.toFront();
				modStuUI.setModal(true);
				modStuUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
				modStuUI.setVisible(true);
			}
		});
		
		workPanel.add(clerk);
		workPanel.add(user);
		
		contPan.add(workPanel);
		contPan.validate();
		
	}

	public static void main(String[] args) {
		SysEmpMgrUI menu = new SysEmpMgrUI(username, userid);
		menu.setVisible(true);
	}
}
