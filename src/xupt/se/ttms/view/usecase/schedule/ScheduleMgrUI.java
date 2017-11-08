package xupt.se.ttms.view.usecase.schedule;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Iterator;













import xupt.se.ttms.model.Movie;

import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.MovieSrv;
import xupt.se.ttms.service.StudioSrv;

import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.view.component.CircleButton;
import xupt.se.ttms.view.component.CircleTextField;
import xupt.se.ttms.view.tmpl.*;

class ScheduleTable {
	/**
	 * 
	 */
	
//	private static final long serialVersionUID = 1L;
	private JTable jt;

	public ScheduleTable(JScrollPane jp) {
		
		DefaultTableModel tabModel=new DefaultTableModel(){
			private static final long serialVersionUID = 1L;

			@Override              
			public boolean isCellEditable(int row,int column){
				return false;              
			};
		};
		tabModel.addColumn("演出计划id");
		tabModel.addColumn("演出厅名称");
		tabModel.addColumn("影片名称");
		tabModel.addColumn("放映时间");
		tabModel.addColumn("票价");
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
	
	public Schedule getStudio() {
		int rowSel=jt.getSelectedRow();
		if(rowSel>=0){
			Schedule sche = new Schedule();
			SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm" );
			
			//list studio
			List<Studio> stuList = new StudioSrv().FetchAll();
			Iterator<Studio> it1 = stuList.iterator();
			Studio stud = null;
			for(;it1.hasNext();){
				stud = it1.next();
	            if (stud.getName().equals(jt.getValueAt(rowSel, 1).toString())){
	            	sche.setStudio_id(stud.getID());
	            }
	        }
			
			//list movie
			List<Movie> movList = new MovieSrv().FetchAll();
			Iterator<Movie> it2 = movList.iterator();
			Movie movi = null;
			for(;it2.hasNext();){
				movi = it2.next();
	            if (movi.getName().equals(jt.getValueAt(rowSel, 2).toString())){
	            	sche.setMv_id(movi.getMv_id());
	            }
	        }
			
			sche.setSched_id(Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));
//			sche.setStudio_id(Integer.parseInt(jt.getValueAt(rowSel, 1).toString()));
//			sche.setMv_id(Integer.parseInt(jt.getValueAt(rowSel, 2).toString()));
			try {
				sche.setSched_time(dateFormat.parse(jt.getValueAt(rowSel, 3).toString()));
			} catch (ParseException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			sche.setSched_ticket_price(Float.parseFloat(jt.getValueAt(rowSel, 4).toString()));

			return sche;
		}
		else{
			return null;
		}
			
	}
	
	// 创建JTable
	public void showScheduleList(List<Schedule> schList, List<Studio> stuList, List<Movie> movList) {
		try {
			DefaultTableModel tabModel = (DefaultTableModel) jt.getModel();
			tabModel.setRowCount(0);
			
			Iterator<Schedule> itr = schList.iterator();
			while (itr.hasNext()) {
				Schedule sche = itr.next();
				Object data[] = new Object[5];
				data[0] = Integer.toString(sche.getSched_id());
				
				//list studio
				Iterator<Studio> it1 = stuList.iterator();
				Studio stud = null;
				
				for(;it1.hasNext();){
					stud = it1.next();
		            if (stud.getID() == sche.getStudio_id()){
		            	data[1] = stud.getName();
		            }
		        }
				
				//list movie
				Iterator<Movie> it2 = movList.iterator();
				Movie movi = null;
				
				for(;it2.hasNext();){
					movi = it2.next();
		            if (movi.getMv_id() == sche.getMv_id()){
		            	data[2] = movi.getName();
		            }
		        }
				
				//data[1] = Integer.toString(sche.getStudio_id());
				//data[2] = Integer.toString(sche.getMv_id());
				
				data[3] = sche.getSched_time();
				data[4] = Double.toString(sche.getSched_ticket_price());
				tabModel.addRow(data);
			}
			jt.invalidate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class ScheduleMgrUI extends MenuUITmpl {
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
//	private JPanel rightPanel;
//	private JTextArea detail;
//	private JPanel salePanel;
	
	ScheduleTable tms; //显示演出计划列表


	public ScheduleMgrUI() {
		
	}

	// To be override by the detailed business block interface
	@Override
	protected void initContent() {
		Rectangle rect = contPan.getBounds();

		this.setTitle("这就是命剧院票务管理系统——演出计划");
		
//		salePanel = new JPanel();
//		salePanel.setLayout(new BorderLayout());
//		setRightPanel();
//		contPan.add(salePanel);
		
		ca1 = new JLabel("演出计划", JLabel.CENTER);
		ca1.setBounds(0, 45, rect.width, 30);
		ca1.setFont(new java.awt.Font("华文行楷", 1, 20));
		ca1.setForeground(Color.blue);
		contPan.add(ca1);

		jsc = new JScrollPane();
		jsc.setBounds(0, 80, rect.width, rect.height - 130);
		contPan.add(jsc);

		hint = new JLabel("请输入查询信息:", JLabel.RIGHT);
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
		
//		JTextArea help = new JTextArea();
//		help.setBounds(500, 50, 100, 700);
//		JScrollPane scrollm = new JScrollPane(help);
//		scrollm.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
//		help.setLineWrap(true);
//		scrollm.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//		contPan.add(scrollm);
		
		tms = new ScheduleTable(jsc);
		
		showTable();
	}

	private void btnAddClicked() {

		ScheduleAddUI addStuUI=null;
		
		addStuUI = new ScheduleAddUI();
		addStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addStuUI.setWindowName("添加演出计划");
		addStuUI.toFront();
		addStuUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		addStuUI.setVisible(true);
		if (addStuUI.getReturnStatus()) {
			showTable();
		}
	}

	private void btnModClicked() {
		Schedule stud = tms.getStudio();
		
		//
		
		
		if(null == stud){
			JOptionPane.showMessageDialog(null, "请选择要修改的演出计划");
			return; 
		}
		
		ScheduleEditUI modStuUI = new ScheduleEditUI(stud);
		modStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		modStuUI.setWindowName("修改演出计划");
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
		Schedule stud = tms.getStudio();
		if(null== stud){
			JOptionPane.showMessageDialog(null, "请选择要删除的演出计划");
			return; 
		}		
		
		int confirm = JOptionPane.showConfirmDialog(null, "确认删除所选？", "删除", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			ScheduleSrv stuSrv = new ScheduleSrv();
			stuSrv.delete(stud.getSched_id());
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
		
		List<Schedule> schList = new ScheduleSrv().FetchAll();
		
		//
		List<Studio> stuList = new StudioSrv().FetchAll();
		List<Movie> movList = new MovieSrv().FetchAll();
		
		tms.showScheduleList(schList,stuList,movList);
	}
	

	public static void main(String[] args) {
		ScheduleMgrUI frmStuMgr = new ScheduleMgrUI();
		frmStuMgr.setVisible(true);
	}
}
