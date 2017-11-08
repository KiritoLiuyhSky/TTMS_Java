package xupt.se.ttms.view.usecase.seat;

import javax.swing.JDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Ticket;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.component.CircleButton;
import xupt.se.ttms.view.component.CircleTextField;
import xupt.se.ttms.view.component.ImageJPanel;
import xupt.se.ttms.view.tmpl.*;
import xupt.se.ttms.view.usecase.ticket.SellTicketUI;

public class SeatChooseUI extends PopUITmplTic implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	protected JButton btnCancel; 	//取消，保存按鈕

	protected JButton btnSave;

	protected boolean rst=false; 				//作结果
	
	private JScrollPane jsc;
	private JPanel middlePanel;
	private static Ticket[][] ticketArray;

	@Override
	protected void initContent(){
		this.setTitle("选购座位");
		
		Rectangle rect = contPan.getBounds();

//		ImageJPanel imageJP = new ImageJPanel(new ImageIcon(
//				"resource/image/selectsite.png").getImage());
//		imageJP.setBounds(40, 40, 720, 463);
//		imageJP.setLayout(null);
//		contPan.add(imageJP);
		
		jsc = new JScrollPane();
		jsc.setBounds(0, 0, rect.width, rect.height - 45);
//		contPan.add(jsc);
		
		btnSave = new CircleButton("确定");

		btnSave.addActionListener(this);
		btnSave.setBounds(270, 507, 60, 30);
		btnSave.setFont(new Font("华文行楷", 1, 12));
//		contPan.add(btnSave);

		btnCancel = new CircleButton("确定");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(380, 510, 60, 30);
		btnCancel.setFont(new Font("华文行楷", 1, 12));
				
		contPan.add(btnCancel);
	}
	
	
	public boolean getReturnStatus(){
		   return rst;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			rst=false;
			this.setVisible(false);
			

		} else if (e.getSource() == btnSave) {
			btnSaveClicked();		//以前未调用，新添加的调用语句
		}
	}
	
	public void setMiddlePanel(int m, int n, List<Ticket> tickets){
		if(middlePanel==null)
			middlePanel = new JPanel();
		else
			middlePanel.removeAll();
		JLabel lmainview = new JLabel();

		ImageIcon selectsite = new ImageIcon("resource/image/selectsite.png");
		lmainview.setIcon(selectsite);

		JPanel sites = new JPanel();
		GridLayout gridLayout = new GridLayout(m+1, n+1);
		gridLayout.setHgap(8);
		gridLayout.setVgap(3);
		sites.setLayout(gridLayout);
		sites.setOpaque(false); // 设置背景为透明
		sites.setBounds(105, 120, 510, 300);

		final ImageIcon siteimgwhite = new ImageIcon("resource/image/white.png");
		final ImageIcon siteimggreen = new ImageIcon("resource/image/green.png");
		final ImageIcon siteimgred = new ImageIcon("resource/image/red.jpg");
		

		Action act = new AbstractAction() {
			private static final long serialVersionUID = -144569051730123316L;

			public void actionPerformed(ActionEvent e) {
				JButton site = (JButton) e.getSource();
				String name = site.getName();
				String tmp[] = name.split(",");
				int i = Integer.valueOf(tmp[0]);
				int j = Integer.valueOf(tmp[1]);
				if (ticketArray[i][j].getStatus()==0) {
					ticketArray[i][j].setStatus(2);
					site.setIcon(siteimggreen);
					SellTicketUI.handler.addTicket(ticketArray[i][j]);
					SellTicketUI.detail.setText(SellTicketUI.handler.getInfo());
				} else if (ticketArray[i][j].getStatus()==2) {
					ticketArray[i][j].setStatus(0);
					site.setIcon(siteimgwhite);
					SellTicketUI.handler.removeTicket(ticketArray[i][j]);
					SellTicketUI.detail.setText(SellTicketUI.handler.getInfo());
				}
			}
		};

		// 座位标示   -1:无座, 0:待销售   1:锁定   2:已选   9:卖出
		int[][] seats = new int[m+1][n+1];
		ticketArray = new Ticket[m+1][n+1];
		for (int i = 0; i < m+1; i++) {
			for (int j = 0; j < n+1; j++) {
				seats[i][j] = -1;
				ticketArray[i][j] = null;
			}
		}
		
		for(Ticket t : tickets){
			seats[t.getSeat().getRow()][t.getSeat().getColumn()] = t.getStatus();
			ticketArray[t.getSeat().getRow()][t.getSeat().getColumn()] = t;
		}
		
		for (int i = 0; i < m+1; i++) {
			for (int j = 0; j < n+1; j++) {
				if(i==0){
					if(j==0)
						sites.add(new JLabel("  "));
					else
						sites.add(new JLabel(" " + j + "座"));
				}else if(j==0){
					if(i>0)
						sites.add(new JLabel(i + "排"));
				}else{
					if (seats[i][j] == -1) {
						sites.add(new JLabel("  "));
					} else if (seats[i][j] == 0) {
						JButton site = new JButton(act);
						site.setBackground(Color.WHITE);
						site.setIcon(siteimgwhite);
						site.setName(i+","+j);
						sites.add(site);
					} else if (seats[i][j] == 2) {
						JButton site = new JButton(act);
						site.setBackground(Color.WHITE);
						site.setIcon(siteimggreen);
						site.setName(i+","+j);
						sites.add(site);
					} else{
						JButton site = new JButton();
						site.setBackground(Color.WHITE);
						site.setIcon(siteimgred);
						sites.add(site);
					}
				}
			}
		}

		middlePanel.setBounds(0, 50, 800, 600);
		lmainview.add(sites);
		middlePanel.add(lmainview);
		contPan.add(middlePanel);
		middlePanel.updateUI();
	}
	
	protected void btnSaveClicked(){
//		if (txtName.getText() != null && txtRow.getText() != null
//				&& txtColumn.getText() != null) {
//			StudioSrv stuSrv = new StudioSrv();
//			Studio stu=new Studio();
//			stu.setName(txtName.getText());
//			stu.setRowCount(Integer.parseInt(txtRow.getText()));
//			stu.setColCount(Integer.parseInt(txtColumn.getText()));
//			stu.setIntroduction("");
//
//			stuSrv.add(stu);
//			this.setVisible(false);
//			rst=true;
//			getParent().setVisible(true);
//		} else {
//			JOptionPane.showMessageDialog(null, "数据不完整");
//		}		
	}
	
}
