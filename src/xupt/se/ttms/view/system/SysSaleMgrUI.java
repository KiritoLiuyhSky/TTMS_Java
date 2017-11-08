package xupt.se.ttms.view.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import xupt.se.ttms.view.component.BarChart;
import xupt.se.ttms.view.component.CircleButton;
import xupt.se.ttms.view.frame.ClerkMenuFrame;
import xupt.se.ttms.view.tmpl.PopUITmpl;
import xupt.se.ttms.view.tmpl.PopUITmplClerk;
import xupt.se.ttms.view.usecase.clerk.ClerkMgrUI;
import xupt.se.ttms.view.usecase.sale.SaleMgrUI;
import xupt.se.ttms.view.usecase.saleItem.SaleItemMgrUI;
import xupt.se.ttms.view.usecase.ticket.SellTicketUI;
import xupt.se.ttms.view.usecase.ticket.TicketMgrUI;


public class SysSaleMgrUI extends PopUITmplClerk {

	private static final long serialVersionUID = 1025028999012028956L;

	public SysSaleMgrUI(){
		initContent();
	}

	@Override
	protected void initContent() {
		this.setTitle("这就是命剧院票务管理系统——销售管理");

		JPanel workPanel = new JPanel();
		workPanel.setLayout(null);
		workPanel.setBounds(0, 0, 800, 630);
		
		JButton clerk = new CircleButton("");
		clerk.setVerticalTextPosition(SwingConstants.BOTTOM);
		clerk.setHorizontalTextPosition(SwingConstants.CENTER);
		clerk.setIcon(new ImageIcon("resource/image/sales-report.png"));
		clerk.setBackground(Color.WHITE);
		clerk.setText("销售清单");
		clerk.setBounds(90, 160, 160, 160);
		clerk.setFont(new Font("华文行楷", 1, 15));
		
		clerk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				SaleMgrUI sa = new SaleMgrUI();
				sa.setVisible(true);
				SysSaleMgrUI.this.dispose();
			}
		});
		
		JButton user = new CircleButton("");
		user.setVerticalTextPosition(SwingConstants.BOTTOM);
		user.setHorizontalTextPosition(SwingConstants.CENTER);
		user.setIcon(new ImageIcon("resource/image/product-sales-report.png"));
		user.setBackground(Color.WHITE);
		user.setText("销售明细");
		user.setBounds(300, 160, 160, 160);
		user.setFont(new Font("华文行楷", 1, 15));
		
		user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				SaleItemMgrUI si = new SaleItemMgrUI();
				si.setVisible(true);
				SysSaleMgrUI.this.dispose();
			}
		});
		
		JButton ticket = new CircleButton("");
		ticket.setVerticalTextPosition(SwingConstants.BOTTOM);
		ticket.setHorizontalTextPosition(SwingConstants.CENTER);
		ticket.setIcon(new ImageIcon("resource/image/analytics.png"));
		ticket.setBackground(Color.WHITE);
		ticket.setText("销售额统计");
		ticket.setBounds(510, 160, 160, 160);
		ticket.setFont(new Font("华文行楷", 1, 15));
		
		ticket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
//				TicketMgrUI tic = new TicketMgrUI();
//				tic.setVisible(true);
			       JFrame frame = new JFrame("销售额统计");

			        frame.add(new BarChart().getChartPanel()); // 添加柱形图

			        frame.setBounds(50, 50, 800, 600);
			        frame.setVisible(true);
				
			}
		});
		
		workPanel.add(clerk);
		workPanel.add(user);
		workPanel.add(ticket);
		
		contPan.add(workPanel);
		contPan.validate();
		
	}

	public static void main(String[] args) {
		SysSaleMgrUI menu = new SysSaleMgrUI();
		menu.setVisible(true);
	}
}
