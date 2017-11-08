package xupt.se.ttms.view.frame;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import xupt.se.ttms.view.component.CircleButton;
import xupt.se.ttms.view.system.SysEmpMgrUI;
import xupt.se.ttms.view.system.SysInfoMgrUI;
import xupt.se.ttms.view.system.SysTicketEmpMgrUI;
import xupt.se.ttms.view.tmpl.MainUITmpl;
import xupt.se.ttms.view.usecase.clerk.ClerkMgrUI;
import xupt.se.ttms.view.usecase.customer.CustomerMgrUI;
import xupt.se.ttms.view.usecase.customer.CustomerMgrUIEmp;
import xupt.se.ttms.view.usecase.movie.MovieMgrUI;
import xupt.se.ttms.view.usecase.schedule.ScheduleMgrUI;
import xupt.se.ttms.view.usecase.studio.StudioMgrUI;
import xupt.se.ttms.view.usecase.ticket.SellTicketUI;

public class EmpMenuFrame extends MainUITmpl{

	private static final long serialVersionUID = 1025028999012028956L;
	private static String username;
	private static int userid;
	
	public EmpMenuFrame(String name, int id){
		super(name);
		username = name;
		userid = id;
		initContent();
	}

	@Override
	protected void initContent() {
		JPanel workPanel = new JPanel();
		workPanel.setLayout(null);
		workPanel.setBounds(0, 0, 1000, 500);
		
		JButton sale = new CircleButton("");
		sale.setVerticalTextPosition(SwingConstants.BOTTOM);
		sale.setHorizontalTextPosition(SwingConstants.CENTER);
		sale.setIcon(new ImageIcon("resource/image/ticket.png"));
		sale.setBackground(Color.WHITE);
		sale.setText("票务管理");
		sale.setBounds(140, 200, 160, 160);
		sale.setFont(new Font("华文行楷", 1, 15));
		
		sale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				SysTicketEmpMgrUI sellTicket = new SysTicketEmpMgrUI(username, userid);
				sellTicket.setWindowName("票务管理");
				sellTicket.setVisible(true);
				EmpMenuFrame.this.dispose();
			}
		});
		
		JButton refund = new CircleButton("");
		refund.setVerticalTextPosition(SwingConstants.BOTTOM);
		refund.setHorizontalTextPosition(SwingConstants.CENTER);
		refund.setIcon(new ImageIcon("resource/image/ticketmgr.png"));
		refund.setBackground(Color.WHITE);
		refund.setText("信息查询");
		refund.setBounds(440, 200, 160, 160);
		refund.setFont(new Font("华文行楷", 1, 15));
		
		refund.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				SysInfoMgrUI movie = new SysInfoMgrUI(username, userid);
				movie.setWindowName("信息查询");
				movie.setVisible(true);
				EmpMenuFrame.this.dispose();
			}
		});
			
		JButton user = new CircleButton("");
		user.setVerticalTextPosition(SwingConstants.BOTTOM);
		user.setHorizontalTextPosition(SwingConstants.CENTER);
		user.setIcon(new ImageIcon("resource/image/vip.png"));
		user.setBackground(Color.WHITE);
		user.setText("帐号管理");
		user.setBounds(740, 200, 160, 160);
		user.setFont(new Font("华文行楷", 1, 15));
		
		user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				SysEmpMgrUI users = new SysEmpMgrUI(username, userid);
				users.setVisible(true);
				EmpMenuFrame.this.dispose();
			}
		});
		
		workPanel.add(sale);
		workPanel.add(user);
		workPanel.add(refund);
		
		contPan.add(workPanel);
		contPan.validate();
		
	}

	public static void main(String[] args) {
		EmpMenuFrame menu = new EmpMenuFrame(username, userid);
		menu.setVisible(true);
		
	}
}
