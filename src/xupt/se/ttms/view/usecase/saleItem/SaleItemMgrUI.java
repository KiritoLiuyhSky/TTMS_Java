package xupt.se.ttms.view.usecase.saleItem;


import java.awt.Color;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

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

import xupt.se.ttms.model.SaleItem;
import xupt.se.ttms.service.SaleItemSrv;
import xupt.se.ttms.view.component.CircleButton;
import xupt.se.ttms.view.component.CircleTextField;
import xupt.se.ttms.view.tmpl.*;
import xupt.se.ttms.view.usecase.sale.JXLSale;

class SaleItemTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jt;

	public SaleItemTable(JScrollPane jp) {
		
		DefaultTableModel tabModel=new DefaultTableModel(){
			private static final long serialVersionUID = 1L;

			@Override              
			public boolean isCellEditable(int row,int column){
				return false;              
			};
		};
		tabModel.addColumn("销售明细id");
		tabModel.addColumn("影票id");
		tabModel.addColumn("销售id");
		tabModel.addColumn("金额");
		//初始化列明
		jt=new JTable(tabModel);	
		
		//设置各列的宽度
	    TableColumnModel columnModel = jt.getColumnModel();
	    
	    //隐藏ID这一列
        TableColumn column = columnModel.getColumn(0);
        column.setPreferredWidth(10);

        column = columnModel.getColumn(1);
        column.setPreferredWidth(10);
        column = columnModel.getColumn(2);
        column.setPreferredWidth(10);
        column = columnModel.getColumn(3);
        column.setPreferredWidth(10); 

		
		jp.add(jt);
		jp.setViewportView(jt);
		
	}
	
	public SaleItem getSaleItem() {
		int rowSel=jt.getSelectedRow();
		if(rowSel>=0){
			SaleItem stud = new SaleItem();
			stud.setId(Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));
			stud.setTicketId(Integer.parseInt(jt.getValueAt(rowSel, 1).toString()));
			stud.setSaleId(Integer.parseInt(jt.getValueAt(rowSel, 2).toString())); 
			stud.setPrice(Float.parseFloat(jt.getValueAt(rowSel, 3).toString()));

			return stud;
		}
		else{
			return null;
		}
			
	}
	
	// 创建JTable
	public void showSaleItemList(List<SaleItem> stuList) {
		try {
			DefaultTableModel tabModel = (DefaultTableModel) jt.getModel();
			tabModel.setRowCount(0);
			
			Iterator<SaleItem> itr = stuList.iterator();
			while (itr.hasNext()) {
				SaleItem stu = itr.next();
				Object data[] = new Object[4];
				data[0] = Integer.toString(stu.getId());
				data[1] = Integer.toString(stu.getTicketId());
				data[2] = Integer.toString(stu.getSaleId());
				data[3] = Float.toString(stu.getPrice());
				tabModel.addRow(data);;
			}
			jt.invalidate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class SaleItemMgrUI extends MenuUITmpl {
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
	
	SaleItemTable tms; //显示演出厅列表


	public SaleItemMgrUI() {
		
	}

	// To be override by the detailed business block interface
	@Override
	protected void initContent() {
		Rectangle rect = contPan.getBounds();

		this.setTitle("这就是命剧院票务管理系统——销售明细");
		
		ca1 = new JLabel("销售明细", JLabel.CENTER);
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

		btnAdd = new CircleButton("导出并生成Excel文件");
		btnAdd.setBounds(rect.width - 225, rect.height - 45, 200, 30);
		btnAdd.setFont(new java.awt.Font("华文行楷", 1, 12));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				int confirm = JOptionPane.showConfirmDialog(null, "导出并生成Excel文件？", "确定", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					JXLSaleItem sale = new JXLSaleItem();
					sale.excle();
					JOptionPane.showMessageDialog(null, "导出并生成Excel文件成功!!!");
					try {
						java.awt.Desktop.getDesktop().open(new java.io.File("E:\\workspace\\TTMS_JAVA\\resource\\excel\\saleitem"));
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					} 
				}
			}
		});
		contPan.add(btnAdd);

//		btnEdit = new CircleButton("生成");
//		btnEdit.setBounds(rect.width - 150, rect.height - 45, 60, 30);
//		btnEdit.setFont(new java.awt.Font("华文行楷", 1, 12));
//		btnEdit.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent Event) {
//				btnModClicked();
//			}
//		});
//		contPan.add(btnEdit);
//
//		btnDel = new CircleButton("删除");
//		btnDel.setBounds(rect.width - 80, rect.height - 45, 60, 30);
//		btnDel.setFont(new java.awt.Font("华文行楷", 1, 12));
//		btnDel.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent Event) {
//				btnDelClicked();
//			}
//		});
//		contPan.add(btnDel);
		contPan.add(ca1);
		
		tms = new SaleItemTable(jsc);
		
		showTable();
	}

	private void btnAddClicked() {

//		SaleItemAddUI addStuUI=null;
//		
//		addStuUI = new SaleItemAddUI();
//		addStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		addStuUI.setWindowName("添加演出厅");
//		addStuUI.toFront();
//		addStuUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
//		addStuUI.setVisible(true);
//		if (addStuUI.getReturnStatus()) {
//			showTable();
//		}
	}

//	private void btnModClicked() {
//		SaleItem stud = tms.getSaleItem();
//		if(null== stud){
//			JOptionPane.showMessageDialog(null, "请选择要修改的数据");
//			return; 
//		}
//		
//		SaleItemEditUI modStuUI = new SaleItemEditUI(stud);
//		modStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		modStuUI.setWindowName("修改数据");
//		modStuUI.initData(stud);
//		modStuUI.toFront();
//		modStuUI.setModal(true);
//		modStuUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
//		modStuUI.setVisible(true);
//
//		if (modStuUI.getReturnStatus()) {
//			showTable();
//		}	
//	}

//	private void btnDelClicked() {
//		SaleItem stud = tms.getSaleItem();
//		if(null== stud){
//			JOptionPane.showMessageDialog(null, "请选择要删除的数据");
//			return; 
//		}		
//		
//		int confirm = JOptionPane.showConfirmDialog(null, "确认删除所选？", "删除", JOptionPane.YES_NO_OPTION);
//		if (confirm == JOptionPane.YES_OPTION) {
//			SaleItemSrv stuSrv = new SaleItemSrv();
//			stuSrv.delete(stud.getID());
//			showTable();
//		}
//	}

	private void btnQueryClicked() {
		if (!input.getText().equals("")) {
			//请自行补充

		} else {
			JOptionPane.showMessageDialog(null, "请输入检索条件");
		}
	}

	private void showTable() {
		List<SaleItem> stuList = new SaleItemSrv().FetchAll();
		tms.showSaleItemList(stuList);
	}
	

	public static void main(String[] args) {
		SaleItemMgrUI frmStuMgr = new SaleItemMgrUI();
		frmStuMgr.setVisible(true);
	}
}
