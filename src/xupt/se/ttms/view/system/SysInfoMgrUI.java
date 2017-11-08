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
import xupt.se.ttms.view.table.MovieTableUI;
import xupt.se.ttms.view.table.ScheduleTableUI;
import xupt.se.ttms.view.table.SeatTableUI;
import xupt.se.ttms.view.table.StudioTableUI;
import xupt.se.ttms.view.table.TicketTableUI;
import xupt.se.ttms.view.tmpl.PopUITmpl;
import xupt.se.ttms.view.tmpl.PopUITmplClerk;
import xupt.se.ttms.view.tmpl.PopUITmplEmp;
import xupt.se.ttms.view.usecase.clerk.ClerkMgrUI;
import xupt.se.ttms.view.usecase.sale.SaleMgrUI;
import xupt.se.ttms.view.usecase.saleItem.SaleItemMgrUI;
import xupt.se.ttms.view.usecase.ticket.SellTicketUI;
import xupt.se.ttms.view.usecase.ticket.TicketMgrUI;

public class SysInfoMgrUI extends PopUITmplEmp {

	private static final long serialVersionUID = 1025028999012028956L;
	private static String username;
	private static int userid;

	public SysInfoMgrUI(String name, int id){
		super(name, id);
		username = name;
		userid = id;
		initContent();
	}

	@Override
	protected void initContent() {
		this.setTitle("这就是命剧院票务管理系统——信息查询");

		JPanel workPanel = new JPanel();
		workPanel.setLayout(null);
		workPanel.setBounds(0, 0, 800, 630);
		
		JButton studio = new CircleButton("");
		studio.setVerticalTextPosition(SwingConstants.BOTTOM);
		studio.setHorizontalTextPosition(SwingConstants.CENTER);
		studio.setIcon(new ImageIcon("resource/image/cinema.png"));
		studio.setBackground(Color.WHITE);
		studio.setText("演出厅信息");
		studio.setBounds(105, 80, 160, 160);
		studio.setFont(new Font("华文行楷", 1, 15));
		
		studio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				StudioTableUI sa = new StudioTableUI(username,userid);
				sa.setWindowName("演出厅信息");
				sa.setVisible(true);
				SysInfoMgrUI.this.dispose();
			}
		});
		
		JButton movie = new CircleButton("");
		movie.setVerticalTextPosition(SwingConstants.BOTTOM);
		movie.setHorizontalTextPosition(SwingConstants.CENTER);
		movie.setIcon(new ImageIcon("resource/image/videoplayer.png"));
		movie.setBackground(Color.WHITE);
		movie.setText("影片信息");
		movie.setBounds(315, 80, 160, 160);
		movie.setFont(new Font("华文行楷", 1, 15));
		
		movie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				MovieTableUI si = new MovieTableUI(username,userid);
				si.setWindowName("影片信息");
				si.setVisible(true);
				SysInfoMgrUI.this.dispose();
			}
		});
		
		JButton schedule = new CircleButton("");
		schedule.setVerticalTextPosition(SwingConstants.BOTTOM);
		schedule.setHorizontalTextPosition(SwingConstants.CENTER);
		schedule.setIcon(new ImageIcon("resource/image/settings.png"));
		schedule.setBackground(Color.WHITE);
		schedule.setText("演出计划");
		schedule.setBounds(525, 80, 160, 160);
		schedule.setFont(new Font("华文行楷", 1, 15));
		
		schedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				ScheduleTableUI tic = new ScheduleTableUI(username,userid);
				tic.setWindowName("演出计划");
				tic.setVisible(true);
				SysInfoMgrUI.this.dispose();
			}
		});
		
		workPanel.add(studio);
		workPanel.add(movie);
		workPanel.add(schedule);
		
		JButton seat = new CircleButton("");
		seat.setVerticalTextPosition(SwingConstants.BOTTOM);
		seat.setHorizontalTextPosition(SwingConstants.CENTER);
		seat.setIcon(new ImageIcon("resource/image/seat.png"));
		seat.setBackground(Color.WHITE);
		seat.setText("座位信息");
		seat.setBounds(105, 310, 160, 160);
		seat.setFont(new Font("华文行楷", 1, 15));
		
		seat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				SeatTableUI sa = new SeatTableUI(username,userid);
				sa.setWindowName("座位信息");
				sa.setVisible(true);
				SysInfoMgrUI.this.dispose();
			}
		});
		
		JButton ticket = new CircleButton("");
		ticket.setVerticalTextPosition(SwingConstants.BOTTOM);
		ticket.setHorizontalTextPosition(SwingConstants.CENTER);
		ticket.setIcon(new ImageIcon("resource/image/ticket.png"));
		ticket.setBackground(Color.WHITE);
		ticket.setText("影票信息");
		ticket.setBounds(315, 310, 160, 160);
		ticket.setFont(new Font("华文行楷", 1, 15));
		
		ticket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				TicketTableUI si = new TicketTableUI(username,userid);
				si.setWindowName("影票信息");
				si.setVisible(true);
				SysInfoMgrUI.this.dispose();
			}
		});
		
		JButton sale = new CircleButton("");
		sale.setVerticalTextPosition(SwingConstants.BOTTOM);
		sale.setHorizontalTextPosition(SwingConstants.CENTER);
		sale.setIcon(new ImageIcon("resource/image/analytics.png"));
		sale.setBackground(Color.WHITE);
		sale.setText("销售信息");
		sale.setBounds(525, 310, 160, 160);
		sale.setFont(new Font("华文行楷", 1, 15));
		
		sale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				SysSaleEmpMgrUI tic = new SysSaleEmpMgrUI(username, userid);
				tic.setWindowName("销售信息");
				tic.setVisible(true);
				SysInfoMgrUI.this.dispose();
			}
		});
		
		workPanel.add(seat);
		workPanel.add(ticket);
		workPanel.add(sale);
		
		contPan.add(workPanel);
		contPan.validate();
		
	}

	public static void main(String[] args) {
		SysInfoMgrUI menu = new SysInfoMgrUI(username, userid);
		menu.setVisible(true);
	}
}
