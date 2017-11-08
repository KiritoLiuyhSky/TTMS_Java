package xupt.se.ttms.view.frame;


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

import xupt.se.ttms.model.Customer;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.CustomerSrv;
import xupt.se.ttms.view.component.CircleButton;
import xupt.se.ttms.view.system.SysTicketEmpMgrUI;
import xupt.se.ttms.view.tmpl.MainUITmpl;
import xupt.se.ttms.view.usecase.clerk.ClerkMgrUI;
import xupt.se.ttms.view.usecase.customer.CustomerEditUI;
import xupt.se.ttms.view.usecase.movie.MovieMgrUI;
import xupt.se.ttms.view.usecase.schedule.ScheduleMgrUI;
import xupt.se.ttms.view.usecase.studio.StudioMgrUI;
import xupt.se.ttms.view.usecase.ticket.RemoveTicketUICus;
import xupt.se.ttms.view.usecase.ticket.RemoveTicketUIEmp;
import xupt.se.ttms.view.usecase.ticket.SellTicketUI;
import xupt.se.ttms.view.usecase.ticket.SellTicketUICus;

public class CustomerMenuFrame extends MainUITmpl{

	private static final long serialVersionUID = 1025028999012028956L;
	private static String username;
	private static int userid;
	
	public CustomerMenuFrame(String name, int id){
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
		sale.setIcon(new ImageIcon("resource/image/ticketsale.png"));
		sale.setBackground(Color.WHITE);
		sale.setText("订票 ");
		sale.setBounds(140, 200, 160, 160);
		sale.setFont(new Font("华文行楷", 1, 15));
		
		sale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				SellTicketUICus sellTicket = new SellTicketUICus(username,userid);
				sellTicket.setVisible(true);
				CustomerMenuFrame.this.dispose();
			}
		});
		
		JButton refund = new CircleButton("");
		refund.setVerticalTextPosition(SwingConstants.BOTTOM);
		refund.setHorizontalTextPosition(SwingConstants.CENTER);
		refund.setIcon(new ImageIcon("resource/image/ticketremove.png"));
		refund.setBackground(Color.WHITE);
		refund.setText("退票");
		refund.setBounds(440, 200, 160, 160);
		refund.setFont(new Font("华文行楷", 1, 15));
		
		refund.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
//				MovieMgrUI movie = new MovieMgrUI();
//				movie.setVisible(true);
				RemoveTicketUICus remove = new RemoveTicketUICus(username, userid);
				remove.setVisible(true);
				remove.setTitle("退票");
				remove.setWindowName("退票");
				CustomerMenuFrame.this.dispose();
			}
		});
			
		JButton user = new CircleButton("");
		user.setVerticalTextPosition(SwingConstants.BOTTOM);
		user.setHorizontalTextPosition(SwingConstants.CENTER);
		user.setIcon(new ImageIcon("resource/image/user106.png"));
		user.setBackground(Color.WHITE);
		user.setText("个人信息");
		user.setBounds(740, 200, 160, 160);
		user.setFont(new Font("华文行楷", 1, 15));
		
		user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				
				//
				List<Customer> cuslist = new CustomerSrv().Fetch("cs_id = " + userid);
				Iterator<Customer> it1 = cuslist.iterator();
				Customer cus = it1.next();
				
				CustomerEditUI modStuUI = new CustomerEditUI(cus);
				modStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				modStuUI.setWindowName("个人信息");
				modStuUI.initData(cus);
				modStuUI.toFront();
				modStuUI.setModal(true);
				modStuUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
				modStuUI.setVisible(true);
				
//				SysUserMgrUI users = new SysUserMgrUI();
//				users.setVisible(true);
//				CustomerMenuFrame.this.dispose();
			}
		});
		
		workPanel.add(sale);
		workPanel.add(user);
		workPanel.add(refund);
		
		contPan.add(workPanel);
		contPan.validate();
		
	}

	public static void main(String[] args) {
		CustomerMenuFrame menu = new CustomerMenuFrame(username,userid);
		menu.setVisible(true);
	}
}
