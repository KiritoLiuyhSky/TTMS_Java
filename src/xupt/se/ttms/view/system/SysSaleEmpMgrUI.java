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
import xupt.se.ttms.view.table.SaleItemTableUI;
import xupt.se.ttms.view.table.SaleTableUI;
import xupt.se.ttms.view.tmpl.PopUITmpl;
import xupt.se.ttms.view.tmpl.PopUITmplClerk;
import xupt.se.ttms.view.tmpl.PopUITmplEmp;
import xupt.se.ttms.view.usecase.clerk.ClerkMgrUI;
import xupt.se.ttms.view.usecase.customer.CustomerMgrUI;
import xupt.se.ttms.view.usecase.movie.MovieMgrUI;
import xupt.se.ttms.view.usecase.ticket.SellTicketUI;

public class SysSaleEmpMgrUI extends PopUITmplEmp {

	private static final long serialVersionUID = 1025028999012028956L;
	private static String username;
	private static int userid;

	public SysSaleEmpMgrUI(String name, int id){
		super(name, id);
		username = name;
		userid = id;
		initContent();
	}

	@Override
	protected void initContent() {
		this.setTitle("这就是命剧院票务管理系统——销售信息");

		JPanel workPanel = new JPanel();
		workPanel.setLayout(null);
		workPanel.setBounds(0, 0, 800, 630);
		
		JButton sale = new CircleButton("");
		sale.setVerticalTextPosition(SwingConstants.BOTTOM);
		sale.setHorizontalTextPosition(SwingConstants.CENTER);
		sale.setIcon(new ImageIcon("resource/image/sales-report.png"));
		sale.setBackground(Color.WHITE);
		sale.setText("销售清单");
		sale.setBounds(160, 200, 160, 160);
		sale.setFont(new Font("华文行楷", 1, 15));

		sale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				SaleTableUI sellTicket = new SaleTableUI(username,userid);
				sellTicket.setWindowName("销售清单");
				sellTicket.setVisible(true);
				SysSaleEmpMgrUI.this.dispose();
			}
		});

		JButton refund = new CircleButton("");
		refund.setVerticalTextPosition(SwingConstants.BOTTOM);
		refund.setHorizontalTextPosition(SwingConstants.CENTER);
		refund.setIcon(new ImageIcon("resource/image/product-sales-report.png"));
		refund.setBackground(Color.WHITE);
		refund.setText("销售明细");
		refund.setBounds(460, 200, 160, 160);
		refund.setFont(new Font("华文行楷", 1, 15));

		refund.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				SaleItemTableUI movie = new SaleItemTableUI(username,userid);
				movie.setWindowName("销售明细");
				movie.setVisible(true);
				SysSaleEmpMgrUI.this.dispose();
			}
		});
		
		workPanel.add(sale);
		workPanel.add(refund);
		contPan.add(workPanel);
		contPan.validate();
		
	}

	public static void main(String[] args) {
		SysSaleEmpMgrUI menu = new SysSaleEmpMgrUI(username, userid);
		menu.setVisible(true);
	}
}

