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
import xupt.se.ttms.view.frame.EmpMenuFrame;
import xupt.se.ttms.view.tmpl.PopUITmpl;
import xupt.se.ttms.view.tmpl.PopUITmplClerk;
import xupt.se.ttms.view.tmpl.PopUITmplEmp;
import xupt.se.ttms.view.usecase.clerk.ClerkMgrUI;
import xupt.se.ttms.view.usecase.customer.CustomerMgrUI;
import xupt.se.ttms.view.usecase.movie.MovieMgrUI;
import xupt.se.ttms.view.usecase.ticket.RemoveTicketUI;
import xupt.se.ttms.view.usecase.ticket.RemoveTicketUIEmp;
import xupt.se.ttms.view.usecase.ticket.SellTicketUI;
import xupt.se.ttms.view.usecase.ticket.SellTicketUIEmp;

public class SysTicketEmpMgrUI extends PopUITmplEmp {

	private static final long serialVersionUID = 1025028999012028956L;
	private static String username;
	private static int userid;

	public SysTicketEmpMgrUI(String name, int id){
		super(name, id);
		username = name;
		userid = id;
		initContent();
	}

	@Override
	protected void initContent() {
		this.setTitle("这就是命剧院票务管理系统——票务管理");

		JPanel workPanel = new JPanel();
		workPanel.setLayout(null);
		workPanel.setBounds(0, 0, 800, 630);
		
		JButton sale = new CircleButton("");
		sale.setVerticalTextPosition(SwingConstants.BOTTOM);
		sale.setHorizontalTextPosition(SwingConstants.CENTER);
		sale.setIcon(new ImageIcon("resource/image/ticketsale.png"));
		sale.setBackground(Color.WHITE);
		sale.setText("售票");
		sale.setBounds(160, 200, 160, 160);
		sale.setFont(new Font("华文行楷", 1, 15));

		sale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				SellTicketUIEmp sellTicket = new SellTicketUIEmp(username, userid);
				sellTicket.setVisible(true);
				SysTicketEmpMgrUI.this.dispose();
			}
		});

		JButton refund = new CircleButton("");
		refund.setVerticalTextPosition(SwingConstants.BOTTOM);
		refund.setHorizontalTextPosition(SwingConstants.CENTER);
		refund.setIcon(new ImageIcon("resource/image/ticketremove.png"));
		refund.setBackground(Color.WHITE);
		refund.setText("退票");
		refund.setBounds(460, 200, 160, 160);
		refund.setFont(new Font("华文行楷", 1, 15));

		refund.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
//				MovieMgrUI movie = new MovieMgrUI();
//				movie.setVisible(true);
//				SysTicketEmpMgrUI.this.dispose();
				RemoveTicketUIEmp remove = new RemoveTicketUIEmp(username, userid);
				remove.setVisible(true);
				remove.setTitle("退票");
				remove.setWindowName("退票");
				SysTicketEmpMgrUI.this.dispose();
			}
		});
		
		workPanel.add(sale);
		workPanel.add(refund);
		contPan.add(workPanel);
		contPan.validate();
		
	}

	public static void main(String[] args) {
		SysTicketEmpMgrUI menu = new SysTicketEmpMgrUI(username,userid);
		menu.setVisible(true);
	}
}

