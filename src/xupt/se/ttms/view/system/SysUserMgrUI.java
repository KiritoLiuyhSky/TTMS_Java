package xupt.se.ttms.view.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import xupt.se.ttms.view.component.CircleButton;
import xupt.se.ttms.view.frame.ClerkMenuFrame;
import xupt.se.ttms.view.tmpl.PopUITmpl;
import xupt.se.ttms.view.tmpl.PopUITmplClerk;
import xupt.se.ttms.view.usecase.clerk.ClerkMgrUI;
import xupt.se.ttms.view.usecase.customer.CustomerMgrUI;

public class SysUserMgrUI extends PopUITmplClerk {

	private static final long serialVersionUID = 1025028999012028956L;

	public SysUserMgrUI(){
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
		clerk.setText("员工管理");
		clerk.setBounds(160, 160, 160, 160);
		clerk.setFont(new Font("华文行楷", 1, 15));
		
		clerk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				ClerkMgrUI clr = new ClerkMgrUI();
				clr.setVisible(true);
				SysUserMgrUI.this.dispose();
			}
		});
		
		JButton user = new CircleButton("");
		user.setVerticalTextPosition(SwingConstants.BOTTOM);
		user.setHorizontalTextPosition(SwingConstants.CENTER);
		user.setIcon(new ImageIcon("resource/image/user106.png"));
		user.setBackground(Color.WHITE);
		user.setText("用户管理");
		user.setBounds(460, 160, 160, 160);
		user.setFont(new Font("华文行楷", 1, 15));
		
		user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				CustomerMgrUI users = new CustomerMgrUI();
				users.setVisible(true);
				SysUserMgrUI.this.dispose();
			}
		});
		
		workPanel.add(clerk);
		workPanel.add(user);
		
		contPan.add(workPanel);
		contPan.validate();
		
	}

	public static void main(String[] args) {
		SysUserMgrUI menu = new SysUserMgrUI();
		menu.setVisible(true);
	}
}
