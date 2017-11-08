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

import xupt.se.ttms.model.SaleItem;
import xupt.se.ttms.service.SaleItemSrv;
import xupt.se.ttms.view.component.CircleButton;
import xupt.se.ttms.view.component.CircleTextField;
import xupt.se.ttms.view.tmpl.*;

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
		tabModel.addColumn("票价");
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

public class SaleItemTableUI extends PopUITmplEmp {
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
	private static String username;
	private static int userid;

	public SaleItemTableUI(String name,int id) {
		super(name,id);
		username = name;
		userid = id;
		initContent();
	}

	// To be override by the detailed business block interface
	@Override
	protected void initContent() {
		Rectangle rect = contPan.getBounds();

		this.setTitle("销售明细");
		
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
		
		tms = new SaleItemTable(jsc);
		
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
		List<SaleItem> stuList = new SaleItemSrv().FetchAll();
		tms.showSaleItemList(stuList);
	}
	

	public static void main(String[] args) {
		SaleItemTableUI frmStuMgr = new SaleItemTableUI(username,userid);
		frmStuMgr.setVisible(true);
	}
}

