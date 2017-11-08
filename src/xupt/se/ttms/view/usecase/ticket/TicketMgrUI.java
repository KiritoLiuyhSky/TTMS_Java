package xupt.se.ttms.view.usecase.ticket;

import java.awt.Color;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.lang.model.type.TypeKind;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Iterator;
import java.util.zip.DataFormatException;

import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Ticket;
import xupt.se.ttms.service.SeatSrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.service.TicketSrv;
import xupt.se.ttms.view.component.CircleButton;
import xupt.se.ttms.view.component.CircleTextField;
import xupt.se.ttms.view.tmpl.*;

class TicketTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jt;
	private String studioname;

	public TicketTable(JScrollPane jp) {
		
		DefaultTableModel tabModel=new DefaultTableModel(){
			private static final long serialVersionUID = 1L;

			@Override              
			public boolean isCellEditable(int row,int column){
				return false;              
			};
		};
		tabModel.addColumn("id");
		tabModel.addColumn("座位");
		tabModel.addColumn("演出计划id");
		tabModel.addColumn("票价");
		tabModel.addColumn("影票状态");
		tabModel.addColumn("锁票时间");
		//初始化列明
		jt=new JTable(tabModel);	
		
		//设置各列的宽度
	    TableColumnModel columnModel = jt.getColumnModel();
	    
	    //隐藏ID这一列
        TableColumn column = columnModel.getColumn(0);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setWidth(0);
        column.setPreferredWidth(0);

        column = columnModel.getColumn(1);
        column.setPreferredWidth(10);
        column = columnModel.getColumn(2);
        column.setPreferredWidth(10);
        column = columnModel.getColumn(3);
        column.setPreferredWidth(10);
        column = columnModel.getColumn(4);
        column.setPreferredWidth(10);   
        column = columnModel.getColumn(5);
        column.setPreferredWidth(10);   

		
		jp.add(jt);
		jp.setViewportView(jt);
		
	}
	
	public Ticket getStudio() {
		int rowSel=jt.getSelectedRow();
		if(rowSel>=0){
			Ticket tic = new Ticket();
			SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm" );
			tic.setId(Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));
			//tic.setSeatId(Integer.parseInt(jt.getValueAt(rowSel, 1).toString())); 
			List<Seat> seatList = new SeatSrv().FetchAll();
			Iterator<Seat> it1 = seatList.iterator();
			Seat se = null;
			for(;it1.hasNext();){
				se = it1.next();
				List<Studio> studiolist = new StudioSrv().FetchAll();
				Iterator<Studio> its = studiolist.iterator();
				Studio studio = null;
				for(;its.hasNext();) {
					studio = its.next();
					if(studio.getID() == se.getStudioId()) {
						if ((studio.getName() + se.getRow() + "排" + se.getColumn() + "座").equals(jt.getValueAt(rowSel, 1).toString())){
							tic.setSeatId(se.getId());
						}
					}
				}
	            
	        }
			
			tic.setScheduleId(Integer.parseInt(jt.getValueAt(rowSel, 2).toString()));
			tic.setPrice(Float.parseFloat(jt.getValueAt(rowSel, 3).toString()));
			//tic.setStatus(Integer.parseInt(jt.getValueAt(rowSel, 4).toString()));
			if(jt.getValueAt(rowSel, 4).toString() == "无座") {
				tic.setStatus(-1);
			} 
			else if(jt.getValueAt(rowSel, 4).toString() == "待售") {
				tic.setStatus(0);
			}
			else if(jt.getValueAt(rowSel, 4).toString() == "锁定") {
				tic.setStatus(1);
			}
			else if(jt.getValueAt(rowSel, 4).toString() == "已售") {
				tic.setStatus(9);
			}
			
			if (jt.getValueAt(rowSel, 5) != null) {
				try {
					tic.setLocked_time(dateFormat.parse(jt.getValueAt(rowSel, 5).toString()));
				} catch (ParseException e) {
				// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
			else
				tic.setLocked_time(null);
			

			return tic;
		}
		else{
			return null;
		}
			
	}
	
	// 创建JTable
	public void showTicketList(List<Ticket> stuList) {
		try {
			DefaultTableModel tabModel = (DefaultTableModel) jt.getModel();
			tabModel.setRowCount(0);
			
			Iterator<Ticket> itr = stuList.iterator();
			while (itr.hasNext()) {
				Ticket stu = itr.next();
				Object data[] = new Object[6];
				data[0] = Integer.toString(stu.getId());
				//data[1] = Integer.toString(stu.getSeatId());
				List<Seat> seatList = new SeatSrv().FetchAll();
				Iterator<Seat> it1 = seatList.iterator();
				Seat se = null;
				for(;it1.hasNext();){
					se = it1.next();
		            if (se.getId() == stu.getSeatId()){
						List<Studio> studiolist = new StudioSrv().FetchAll();
						Iterator<Studio> its = studiolist.iterator();
						Studio studio = null;
						for(;its.hasNext();) {
							studio = its.next();
							if(studio.getID() == se.getStudioId()){
								data[1] = studio.getName() + se.getRow() + "排" + se.getColumn() + "座";
							}
						}
		            	
		            }
		        }
				
				data[2] = Integer.toString(stu.getScheduleId());
				data[3] = Float.toString(stu.getPrice());
				//data[4] = Integer.toString(stu.getStatus());
				if(stu.getStatus() == -1) {
					data[4] = "无座";
				} 
				else if(stu.getStatus() == 0){
					data[4] = "待售";
				}
				else if(stu.getStatus() == 1){
					data[4] = "锁定";
				}
				else if(stu.getStatus() == 9){
					data[4] = "已售";
				}
				
				data[5] = stu.getLocked_time();
				tabModel.addRow(data);
			}
			jt.invalidate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class TicketMgrUI extends MenuUITmpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel ca1 = null; // 界面提示
	// 用来放表格的滚动控件
	private JScrollPane jsc;
	// 查找的提示和输出
	private JLabel hint;
	private JTextField input;

	// 查找，编辑和删除按钮
	private JButton btnAdd, btnEdit, btnDel, btnQuery;
	
	TicketTable tms; //显示演出厅列表


	public TicketMgrUI() {
		
	}

	// To be override by the detailed business block interface
	@Override
	protected void initContent() {
		Rectangle rect = contPan.getBounds();

		this.setTitle("这就是命剧院票务管理系统——影票管理");
		
		ca1 = new JLabel("影票管理", JLabel.CENTER);
		ca1.setBounds(0, 45, rect.width, 30);
		ca1.setFont(new java.awt.Font("华文行楷", 1, 20));
		ca1.setForeground(Color.blue);
		contPan.add(ca1);

		jsc = new JScrollPane();
		jsc.setBounds(0, 80, rect.width, rect.height - 130);
		contPan.add(jsc);

		hint = new JLabel("请输入影票名称:", JLabel.RIGHT);
		hint.setFont(new java.awt.Font("华文行楷", 1, 15));
		hint.setBounds(60, rect.height - 45, 150, 30);
		contPan.add(hint);

		input = new CircleTextField(10);
		input.setBounds(220, rect.height - 45, 200, 30);
		contPan.add(input);

		// 查找 ，删除和编辑的按钮，其中含有相关的事件处理！
		btnQuery = new CircleButton("查找");
		btnQuery.setBounds(440, rect.height - 45, 60, 30);
		btnQuery.setFont(new java.awt.Font("华文行楷", 1, 12));
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnQueryClicked();
			}
		});
		contPan.add(btnQuery);

		btnAdd = new CircleButton("添加");
		btnAdd.setBounds(rect.width - 220, rect.height - 45, 60, 30);
		btnAdd.setFont(new java.awt.Font("华文行楷", 1, 12));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnAddClicked();
			}
		});
		contPan.add(btnAdd);

		btnEdit = new CircleButton("修改");
		btnEdit.setBounds(rect.width - 150, rect.height - 45, 60, 30);
		btnEdit.setFont(new java.awt.Font("华文行楷", 1, 12));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnModClicked();
			}
		});
		contPan.add(btnEdit);

		btnDel = new CircleButton("删除");
		btnDel.setBounds(rect.width - 80, rect.height - 45, 60, 30);
		btnDel.setFont(new java.awt.Font("华文行楷", 1, 12));
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnDelClicked();
			}
		});
		contPan.add(btnDel);
		contPan.add(ca1);
		
		tms = new TicketTable(jsc);
		
		showTable();
	}

	private void btnAddClicked() {

		TicketAddUI addStuUI=null;
		
		addStuUI = new TicketAddUI();
		addStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addStuUI.setWindowName("添加影票");
		addStuUI.toFront();
		addStuUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		addStuUI.setVisible(true);
		if (addStuUI.getReturnStatus()) {
			showTable();
		}
	}

	private void btnModClicked() {
		Ticket stud = tms.getStudio();
		if(null== stud){
			JOptionPane.showMessageDialog(null, "请选择要修改的影票");
			return; 
		}
		
		TicketEditUI modStuUI = new TicketEditUI(stud);
		modStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		modStuUI.setWindowName("修改影票");
		modStuUI.initData(stud);
		modStuUI.toFront();
		modStuUI.setModal(true);
		modStuUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		modStuUI.setVisible(true);

		if (modStuUI.getReturnStatus()) {
			showTable();
		}	
	}

	private void btnDelClicked() {
		Ticket stud = tms.getStudio();
		if(null== stud){
			JOptionPane.showMessageDialog(null, "请选择要删除的影票");
			return; 
		}		
		
		int confirm = JOptionPane.showConfirmDialog(null, "确认删除所选？", "删除", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			TicketSrv stuSrv = new TicketSrv();
			stuSrv.delete(stud.getId());
			showTable();
		}
	}

	private void btnQueryClicked() {
		if (!input.getText().equals("")) {
			//请自行补充

		} else {
			JOptionPane.showMessageDialog(null, "请输入检索条件");
		}
	}

	private void showTable() {
		List<Ticket> stuList = new TicketSrv().FetchAll();
		tms.showTicketList(stuList);
	}
	

	public static void main(String[] args) {
		TicketMgrUI frmStuMgr = new TicketMgrUI();
		frmStuMgr.setVisible(true);
	}
}

