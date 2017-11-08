package xupt.se.ttms.view.table;


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

import java.util.List;
import java.util.Iterator;

import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.component.CircleButton;
import xupt.se.ttms.view.component.CircleTextField;
import xupt.se.ttms.view.tmpl.*;

class StudioTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jt;

	public StudioTable(JScrollPane jp) {
		
		DefaultTableModel tabModel=new DefaultTableModel(){
			private static final long serialVersionUID = 1L;

			@Override              
			public boolean isCellEditable(int row,int column){
				return false;              
			};
		};
		tabModel.addColumn("id");
		tabModel.addColumn("演出厅名称");
		tabModel.addColumn("座位 行数");
		tabModel.addColumn("座位 列数");
		tabModel.addColumn("演出厅简介");
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
        column.setPreferredWidth(500);        

		
		jp.add(jt);
		jp.setViewportView(jt);
		
	}
	
	public Studio getStudio() {
		int rowSel=jt.getSelectedRow();
		if(rowSel>=0){
			Studio stud = new Studio();
			stud.setID(Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));
			stud.setName(jt.getValueAt(rowSel, 1).toString());
			stud.setRowCount(Integer.parseInt(jt.getValueAt(rowSel, 2).toString())); // 0
			stud.setColCount(Integer.parseInt(jt.getValueAt(rowSel, 3).toString()));
			if (jt.getValueAt(rowSel, 4) != null)
				stud.setIntroduction(jt.getValueAt(rowSel, 4).toString());
			else
				stud.setIntroduction("");

			return stud;
		}
		else{
			return null;
		}
			
	}
	
	// 创建JTable
	public void showStudioList(List<Studio> stuList) {
		try {
			DefaultTableModel tabModel = (DefaultTableModel) jt.getModel();
			tabModel.setRowCount(0);
			
			Iterator<Studio> itr = stuList.iterator();
			while (itr.hasNext()) {
				Studio stu = itr.next();
				Object data[] = new Object[5];
				data[0] = Integer.toString(stu.getID());
				data[1] = stu.getName();
				data[2] = Integer.toString(stu.getRowCount());
				data[3] = Integer.toString(stu.getColCount());
				data[4] = stu.getIntroduction();
				tabModel.addRow(data);;
			}
			jt.invalidate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class StudioTableUI extends PopUITmplEmp {
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
	
	StudioTable tms; //显示演出厅列表
	private static String username;
	private static int userid;

	public StudioTableUI(String name,int id) {
		super(name,id);
		username = name;
		userid = id;
		initContent();
	}

	// To be override by the detailed business block interface
	@Override
	protected void initContent() {
		Rectangle rect = contPan.getBounds();

		this.setTitle("演出厅信息");
		
		jsc = new JScrollPane();
		jsc.setBounds(0, 40, rect.width, rect.height-90);
		contPan.add(jsc);

		hint = new JLabel("请输入查询信息:", JLabel.RIGHT);
		hint.setFont(new java.awt.Font("华文行楷", 1, 15));
		hint.setBounds(150, rect.height - 45, 150, 30);
		contPan.add(hint);

		input = new CircleTextField(10);
		input.setBounds(310, rect.height - 45, 200, 30);
		contPan.add(input);

		// 查找 ，删除和编辑的按钮，其中含有相关的事件处理！
		btnQuery = new CircleButton("查找");
		btnQuery.setBounds(530, rect.height - 45, 60, 30);
		btnQuery.setFont(new java.awt.Font("华文行楷", 1, 12));
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnQueryClicked();
			}
		});
		contPan.add(btnQuery);
		
		tms = new StudioTable(jsc);
		
		showTable();
	}


	private void btnQueryClicked() {
		if (!input.getText().equals("")) {
			//请自行补充

		} else {
			JOptionPane.showMessageDialog(null, "请输入检索条件");
		}
	}

	private void showTable() {
		List<Studio> stuList = new StudioSrv().FetchAll();
		tms.showStudioList(stuList);
	}
	

	public static void main(String[] args) {
		StudioTableUI frmStuMgr = new StudioTableUI(username,userid);
		frmStuMgr.setVisible(true);
	}
}
