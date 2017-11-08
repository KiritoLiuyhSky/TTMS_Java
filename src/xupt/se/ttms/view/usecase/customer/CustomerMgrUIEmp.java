package xupt.se.ttms.view.usecase.customer;

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

import xupt.se.ttms.model.Customer;
import xupt.se.ttms.service.CustomerSrv;
import xupt.se.ttms.view.component.CircleButton;
import xupt.se.ttms.view.component.CircleTextField;
import xupt.se.ttms.view.tmpl.*;

class ClerkTableEmp {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jt;

	public ClerkTableEmp(JScrollPane jp) {
		
		DefaultTableModel tabModel=new DefaultTableModel(){
			private static final long serialVersionUID = 1L;

			@Override              
			public boolean isCellEditable(int row,int column){
				return false;              
			};
		};
		tabModel.addColumn("id");
		tabModel.addColumn("昵称");
		tabModel.addColumn("密码");
		tabModel.addColumn("姓名");
		tabModel.addColumn("邮箱");
		tabModel.addColumn("性别");
		tabModel.addColumn("出生日期");
		tabModel.addColumn("年龄");
		tabModel.addColumn("联系方式");
		tabModel.addColumn("个性签名");
		
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
        
        column = columnModel.getColumn(2);	//密码
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setWidth(0);
        column.setPreferredWidth(0);

        column = columnModel.getColumn(3);
        column.setPreferredWidth(20);
        column = columnModel.getColumn(4);	
        column.setPreferredWidth(20);
        column = columnModel.getColumn(5);
        column.setPreferredWidth(20);
        column = columnModel.getColumn(6);
        column.setPreferredWidth(20);
        column = columnModel.getColumn(7);
        column.setPreferredWidth(20);
        column = columnModel.getColumn(8);
        column.setPreferredWidth(20);
        column = columnModel.getColumn(9);
        column.setPreferredWidth(200);        

		
		jp.add(jt);
		jp.setViewportView(jt);
		
	}
	
	public Customer getStudio() {
		int rowSel=jt.getSelectedRow();
		if(rowSel>=0){
			Customer clr = new Customer();
			clr.setID(Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));
			clr.setNick(jt.getValueAt(rowSel, 1).toString());
			clr.setPsd(jt.getValueAt(rowSel, 2).toString());
			clr.setName(jt.getValueAt(rowSel, 3).toString());
			clr.setEmail(jt.getValueAt(rowSel, 4).toString());
			clr.setSex(jt.getValueAt(rowSel, 5).toString());
			clr.setDate(jt.getValueAt(rowSel, 6).toString());
			clr.setAge(jt.getValueAt(rowSel, 7).toString());
			clr.setPhone(jt.getValueAt(rowSel, 8).toString());
			if (jt.getValueAt(rowSel, 9) != null)
				clr.setIntroduction(jt.getValueAt(rowSel, 9).toString());
			else
				clr.setIntroduction("");

			return clr;
		}
		else{
			return null;
		}
			
	}
	
	// 创建JTable
	public void showCustomerList(List<Customer> stuList) {
		try {
			DefaultTableModel tabModel = (DefaultTableModel) jt.getModel();
			tabModel.setRowCount(0);
			
			Iterator<Customer> itr = stuList.iterator();
			while (itr.hasNext()) {
				Customer clr = itr.next();
				Object data[] = new Object[10];
				data[0] = Integer.toString(clr.getID());
				data[1] = clr.getNick();
				data[2] = clr.getPsd();
				data[3] = clr.getName();
				data[4] = clr.getEmail();
				data[5] = clr.getSex();
				data[6] = clr.getDate();
				data[7] = clr.getAge();
				data[8] = clr.getPhone();
				data[9] = clr.getIntroduction();
				tabModel.addRow(data);;
			}
			jt.invalidate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class CustomerMgrUIEmp extends MenuUITmplEmp {
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
	
	ClerkTableEmp tms; //显示演出厅列表
	private static String username;
	private static int userid;


	public CustomerMgrUIEmp(String name, int id) {
		super(name, id);
		username = name;
		userid = id;
		initContent();
	}

	// To be override by the detailed business block interface
	@Override
	protected void initContent() {
		Rectangle rect = contPan.getBounds();

		this.setTitle("这就是命剧院票务管理系统——用户管理");
		
		ca1 = new JLabel("用户管理", JLabel.CENTER);
		ca1.setBounds(0, 45, rect.width, 30);
		ca1.setFont(new java.awt.Font("华文行楷", 1, 20));
		ca1.setForeground(Color.blue);
		contPan.add(ca1);

		jsc = new JScrollPane();
		jsc.setBounds(0, 80, rect.width, rect.height - 130);
		contPan.add(jsc);

		hint = new JLabel("请输入用户信息:", JLabel.RIGHT);
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

		btnAdd = new CircleButton("注册");
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
		
		tms = new ClerkTableEmp(jsc);
		
		showTable();
	}

	private void btnAddClicked() {

		CustomerAddUI addStuUI=null;
		
		addStuUI = new CustomerAddUI();
		addStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addStuUI.setWindowName("用户注册");
		addStuUI.toFront();
		addStuUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		addStuUI.setVisible(true);
		if (addStuUI.getReturnStatus()) {
			showTable();
		}
	}

	private void btnModClicked() {
		Customer stud = tms.getStudio();
		if(null== stud){
			JOptionPane.showMessageDialog(null, "请选择要修改的用户");
			return; 
		}
		
		CustomerEditUI modStuUI = new CustomerEditUI(stud);
		modStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		modStuUI.setWindowName("修改信息");
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
		Customer stud = tms.getStudio();
		if(null== stud){
			JOptionPane.showMessageDialog(null, "请选择要删除的用户");
			return; 
		}		
		
		int confirm = JOptionPane.showConfirmDialog(null, "确认删除所选？", "删除", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			CustomerSrv stuSrv = new CustomerSrv();
			stuSrv.delete(stud.getID());
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
		List<Customer> stuList = new CustomerSrv().FetchAll();
		tms.showCustomerList(stuList);
	}
	

	public static void main(String[] args) {
		CustomerMgrUIEmp frmStuMgr = new CustomerMgrUIEmp(username,userid);
		frmStuMgr.setVisible(true);
	}
}
