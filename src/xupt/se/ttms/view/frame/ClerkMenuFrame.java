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
import xupt.se.ttms.view.system.SysSaleMgrUI;
import xupt.se.ttms.view.system.SysTicketMgrUI;
import xupt.se.ttms.view.system.SysUserMgrUI;
import xupt.se.ttms.view.tmpl.MainUITmpl;
import xupt.se.ttms.view.usecase.clerk.ClerkMgrUI;
import xupt.se.ttms.view.usecase.datadict.DataDictMgrUI;
import xupt.se.ttms.view.usecase.movie.MovieMgrUI;
import xupt.se.ttms.view.usecase.schedule.ScheduleMgrUI;
import xupt.se.ttms.view.usecase.seat.SeatMgrUI;
import xupt.se.ttms.view.usecase.studio.StudioMgrUI;
import xupt.se.ttms.view.usecase.ticket.SellTicketUI;

public class ClerkMenuFrame extends MainUITmpl{

	private static final long serialVersionUID = 1025028999012028956L;
	
	public ClerkMenuFrame(){
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
		sale.setBounds(50, 100, 160, 160);
		sale.setFont(new Font("华文行楷", 1, 15));
		
		sale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				SysTicketMgrUI tic = new SysTicketMgrUI();
				tic.setVisible(true);
				tic.setWindowName("票务管理");
				ClerkMenuFrame.this.dispose();
			}
		});
		
		JButton refund = new CircleButton("");
		refund.setVerticalTextPosition(SwingConstants.BOTTOM);
		refund.setHorizontalTextPosition(SwingConstants.CENTER);
		refund.setIcon(new ImageIcon("resource/image/videoplayer.png"));
		refund.setBackground(Color.WHITE);
		refund.setText("影片管理");
		refund.setBounds(300, 100, 160, 160);
		refund.setFont(new Font("华文行楷", 1, 15));
		
		refund.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				MovieMgrUI movie = new MovieMgrUI();
				movie.setVisible(true);
				ClerkMenuFrame.this.dispose();
			}
		});
		
		JButton checkList = new CircleButton("");
		checkList.setVerticalTextPosition(SwingConstants.BOTTOM);
		checkList.setHorizontalTextPosition(SwingConstants.CENTER);
		checkList.setIcon(new ImageIcon("resource/image/cinema.png"));
		checkList.setBackground(Color.WHITE);
		checkList.setText("演出厅管理");
		checkList.setBounds(550, 100, 160, 160);
		checkList.setFont(new Font("华文行楷", 1, 15));
		
		checkList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				StudioMgrUI studio = new StudioMgrUI();
				studio.setVisible(true);
				ClerkMenuFrame.this.dispose();
			}
		});
		
		JButton seat = new CircleButton("");
		seat.setVerticalTextPosition(SwingConstants.BOTTOM);
		seat.setHorizontalTextPosition(SwingConstants.CENTER);
		seat.setIcon(new ImageIcon("resource/image/seat.png"));
		seat.setBackground(Color.WHITE);
		seat.setText("座位管理");
		seat.setBounds(800, 100, 160, 160);
		seat.setFont(new Font("华文行楷", 1, 15));
		
		seat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				SeatMgrUI seats = new SeatMgrUI();
				seats.setVisible(true);
				ClerkMenuFrame.this.dispose();
			}
		});
		
		workPanel.add(sale);
		workPanel.add(refund);
		workPanel.add(checkList);
		workPanel.add(seat);
		
		JButton sche = new CircleButton("");
		sche.setVerticalTextPosition(SwingConstants.BOTTOM);
		sche.setHorizontalTextPosition(SwingConstants.CENTER);
		sche.setIcon(new ImageIcon("resource/image/settings.png"));
		sche.setBackground(Color.WHITE);
		sche.setText("演出计划");
		sche.setBounds(50, 340, 160, 160);
		sche.setFont(new Font("华文行楷", 1, 15));
		
		sche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				ScheduleMgrUI schedule = new ScheduleMgrUI();
				schedule.setVisible(true);
				ClerkMenuFrame.this.dispose();
			}
		});
		
		JButton user = new CircleButton("");
		user.setVerticalTextPosition(SwingConstants.BOTTOM);
		user.setHorizontalTextPosition(SwingConstants.CENTER);
		user.setIcon(new ImageIcon("resource/image/VIP.png"));
		user.setBackground(Color.WHITE);
		user.setText("帐号管理");
		user.setBounds(300, 340, 160, 160);
		user.setFont(new Font("华文行楷", 1, 15));
		
		user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				SysUserMgrUI users = new SysUserMgrUI();
				users.setWindowName("帐号管理");
				users.setVisible(true);
				ClerkMenuFrame.this.dispose();
			}
		});
		
		JButton anay = new CircleButton("");
		anay.setVerticalTextPosition(SwingConstants.BOTTOM);
		anay.setHorizontalTextPosition(SwingConstants.CENTER);
		anay.setIcon(new ImageIcon("resource/image/salesman.png"));
		anay.setBackground(Color.WHITE);
		anay.setText("销售管理");
		anay.setBounds(550, 340, 160, 160);
		anay.setFont(new Font("华文行楷", 1, 15));
		
		anay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				SysSaleMgrUI sales = new SysSaleMgrUI();
				sales.setWindowName("销售管理");
				sales.setVisible(true);
				ClerkMenuFrame.this.dispose();
			}
		});
		
		JButton dic = new CircleButton("");
		dic.setVerticalTextPosition(SwingConstants.BOTTOM);
		dic.setHorizontalTextPosition(SwingConstants.CENTER);
		dic.setIcon(new ImageIcon("resource/image/stardict.png"));
		dic.setBackground(Color.WHITE);
		dic.setText("数据字典管理");
		dic.setBounds(800, 340, 160, 160);
		dic.setFont(new Font("华文行楷", 1, 15));
		
		dic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				DataDictMgrUI data = new DataDictMgrUI();
				data.setVisible(true);
				ClerkMenuFrame.this.dispose();
			}
		});
		
		workPanel.add(sche);
		workPanel.add(user);
		workPanel.add(anay);
		workPanel.add(dic);
		
		contPan.add(workPanel);
		contPan.validate();
		
	}

	public static void main(String[] args) {
		ClerkMenuFrame menu = new ClerkMenuFrame();
		menu.setVisible(true);
	}
}
