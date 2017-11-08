package xupt.se.ttms.view.usecase.seat;

import java.awt.Color;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

import java.util.List;
import java.util.Iterator;

import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.SeatSrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.component.CircleButton;
import xupt.se.ttms.view.component.CircleTextField;
import xupt.se.ttms.view.tmpl.*;


class SeatTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jt;

	public SeatTable(JScrollPane jp) {
		
		DefaultTableModel tabModel=new DefaultTableModel(){
			private static final long serialVersionUID = 1L;

			@Override              
			public boolean isCellEditable(int row,int column){
				return false;              
			};
		};
		tabModel.addColumn("id");
		tabModel.addColumn("演出厅名称");
		tabModel.addColumn("座位所在行");
		tabModel.addColumn("座位所在列");
		tabModel.addColumn("座位状态");
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

		
		jp.add(jt);
		jp.setViewportView(jt);
		
	}
	
	public Seat getSeat() {
		int rowSel=jt.getSelectedRow();
		if(rowSel>=0){
			Seat seat = new Seat();
			seat.setId(Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));
			//seat.setStudioId(Integer.parseInt(jt.getValueAt(rowSel, 1).toString()));
			List<Studio> stuList = new StudioSrv().FetchAll();
			Iterator<Studio> it1 = stuList.iterator();
			Studio stud = null;
			for(;it1.hasNext();){
				stud = it1.next();
	            if (stud.getName().equals(jt.getValueAt(rowSel, 1).toString())){
	            	seat.setStudioId(stud.getID());
	            }
			}
			
			seat.setRow(Integer.parseInt(jt.getValueAt(rowSel, 2).toString())); 
			seat.setColumn(Integer.parseInt(jt.getValueAt(rowSel, 3).toString()));
			//seat.setSeatStatus(Integer.parseInt(jt.getValueAt(rowSel, 4).toString()));
			if(jt.getValueAt(rowSel, 4).toString() == "正常") {
				seat.setSeatStatus(1);
			} 
			else if(jt.getValueAt(rowSel, 4).toString() == "损坏") {
				seat.setSeatStatus(0);
			}

			return seat;
		}
		else{
			return null;
		}
			
	}
	
	// 创建JTable
	public void showSeatList(List<Seat> seaList,List<Studio> stuList) {
		try {
			DefaultTableModel tabModel = (DefaultTableModel) jt.getModel();
			tabModel.setRowCount(0);
			
			Iterator<Seat> itr = seaList.iterator();
			
			while (itr.hasNext()) {
				Seat tmpSeat = itr.next();
				Object data[] = new Object[5];
				data[0] = Integer.toString(tmpSeat.getId());
				
				//
				//list studio
				Iterator<Studio> it1 = stuList.iterator();
				Studio stud = null;
				
				for(;it1.hasNext();){
					stud = it1.next();
		            if (stud.getID() == tmpSeat.getStudioId()){
		            	data[1] = stud.getName();
		            }
				}
				
				//data[1] = Integer.toString(tmpSeat.getStudioId());
				
				data[2] = Integer.toString(tmpSeat.getRow());
				data[3] = Integer.toString(tmpSeat.getColumn());
				//data[4] = Integer.toString(tmpSeat.getSeatStatus());
				if(tmpSeat.getSeatStatus() == 1) {
					data[4] = "正常";
				} 
				else if(tmpSeat.getSeatStatus() == 0) {
					data[4] = "损坏";
				}

				tabModel.addRow(data);;
			}
			jt.invalidate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class SeatMgrUI extends MenuUITmpl {
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
	
	SeatTable tms; //显示座位列表

	public SeatMgrUI() {
	}

	// To be override by the detailed business block interface
	@Override
	protected void initContent() {
		Rectangle rect = contPan.getBounds();

		this.setTitle("这就是命剧院票务管理系统——座位管理");
		
		ca1 = new JLabel("座位管理", JLabel.CENTER);
		ca1.setBounds(0, 45, rect.width, 30);
		ca1.setFont(new java.awt.Font("华文行楷", 1, 20));
		ca1.setForeground(Color.blue);
		contPan.add(ca1);

		jsc = new JScrollPane();
		jsc.setBounds(0, 80, rect.width, rect.height - 130);
		contPan.add(jsc);

		hint = new JLabel("请输入所在影厅:", JLabel.RIGHT);
		hint.setBounds(60, rect.height - 45, 150, 30);
		hint.setFont(new java.awt.Font("华文行楷", 1, 15));
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
		
		tms = new SeatTable(jsc);
		
		showTable();
	}

	private void btnAddClicked() {
		SeatAddUI addStuUI=null;
		
		addStuUI = new SeatAddUI();
		addStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addStuUI.setWindowName("添加座位");
		addStuUI.toFront();
		addStuUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		addStuUI.setVisible(true);
		if (addStuUI.getReturnStatus()) {
			showTable();
		}
	}

	private void btnModClicked() {
			
		Seat stud = tms.getSeat();
		if(null== stud){
			JOptionPane.showMessageDialog(null, "请选择要修改的座位");
			return; 
		}
		
		SeatEditUI modStuUI = new SeatEditUI(stud);
		modStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		modStuUI.setWindowName("修改座位");
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
		Seat stud = tms.getSeat();
		if(null== stud){
			JOptionPane.showMessageDialog(null, "请选择要删除的座位");
			return; 
		}		
		
		int confirm = JOptionPane.showConfirmDialog(null, "确认删除所选？", "删除", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			SeatSrv stuSrv = new SeatSrv();
			stuSrv.delete(stud.getId());
			showTable();
		}
	}

	private void btnQueryClicked() {
		if (!input.getText().equals("")) {
			showTable(); // 还没有处理，暂时显示全部内容

		} else {
			JOptionPane.showMessageDialog(null, "请输入检索条件");
		}
	}

//	public void showTable() {
//		SeatTable tms = new SeatTable(seat);
//		Object[] in = { "id", "演出厅Id", "座位所在行", "座位所在列" };
//		List<Seat> stuList = new SeatSrv().FetchAll();
//
//		tms.createTable(jsc, in, stuList);
//		jsc.repaint();
//	}
	private void showTable() {
		List<Seat> seaList = new SeatSrv().FetchAll();
		
		
		//
		List<Studio> stuList = new StudioSrv().FetchAll();
		
		tms.showSeatList(seaList,stuList);
	}

	public static void main(String[] args) {
		SeatMgrUI frmStuMgr = new SeatMgrUI();
		frmStuMgr.setVisible(true);
	}
}
