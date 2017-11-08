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
import xupt.se.ttms.view.usecase.ticket.RemoveTicketUI;
import xupt.se.ttms.view.usecase.ticket.SellTicketUI;
import xupt.se.ttms.view.usecase.ticket.TicketMgrUI;

public class SysTicketMgrUI extends PopUITmplClerk {

	private static final long serialVersionUID = 1025028999012028956L;

	public SysTicketMgrUI(){
		initContent();
	}

	@Override
	protected void initContent() {
		this.setTitle("这就是命剧院票务管理系统——票务管理");

		JPanel workPanel = new JPanel();
		workPanel.setLayout(null);
		workPanel.setBounds(0, 0, 800, 630);
		
		JButton clerk = new CircleButton("");
		clerk.setVerticalTextPosition(SwingConstants.BOTTOM);
		clerk.setHorizontalTextPosition(SwingConstants.CENTER);
		clerk.setIcon(new ImageIcon("resource/image/ticketsale.png"));
		clerk.setBackground(Color.WHITE);
		clerk.setText("售票");
		clerk.setBounds(90, 160, 160, 160);
		clerk.setFont(new Font("华文行楷", 1, 15));
		
		clerk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				SellTicketUI clr = new SellTicketUI();
				clr.setVisible(true);
				SysTicketMgrUI.this.dispose();
			}
		});
		
		JButton user = new CircleButton("");
		user.setVerticalTextPosition(SwingConstants.BOTTOM);
		user.setHorizontalTextPosition(SwingConstants.CENTER);
		user.setIcon(new ImageIcon("resource/image/ticketremove.png"));
		user.setBackground(Color.WHITE);
		user.setText("退票");
		user.setBounds(300, 160, 160, 160);
		user.setFont(new Font("华文行楷", 1, 15));
		
		user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
//				UserMgrUI users = new UserMgrUI();
//				users.setVisible(true);
//				SysTicketMgrUI.this.dispose();
				RemoveTicketUI remove = new RemoveTicketUI();
				remove.setVisible(true);
				remove.setTitle("退票");
				remove.setWindowName("退票");
				SysTicketMgrUI.this.dispose();
			}
		});
		
		JButton ticket = new CircleButton("");
		ticket.setVerticalTextPosition(SwingConstants.BOTTOM);
		ticket.setHorizontalTextPosition(SwingConstants.CENTER);
		ticket.setIcon(new ImageIcon("resource/image/ticketmgr.png"));
		ticket.setBackground(Color.WHITE);
		ticket.setText("影票管理");
		ticket.setBounds(510, 160, 160, 160);
		ticket.setFont(new Font("华文行楷", 1, 15));
		
		ticket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				TicketMgrUI tic = new TicketMgrUI();
				tic.setVisible(true);
				SysTicketMgrUI.this.dispose();
			}
		});
		
		workPanel.add(clerk);
		workPanel.add(user);
		workPanel.add(ticket);
		
		contPan.add(workPanel);
		contPan.validate();
		
	}

	public static void main(String[] args) {
		SysUserMgrUI menu = new SysUserMgrUI();
		menu.setVisible(true);
	}
}
