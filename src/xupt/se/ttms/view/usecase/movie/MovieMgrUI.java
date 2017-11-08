package xupt.se.ttms.view.usecase.movie;

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

import xupt.se.ttms.model.Movie;
import xupt.se.ttms.service.MovieSrv;
import xupt.se.ttms.view.component.CircleButton;
import xupt.se.ttms.view.component.CircleTextField;
import xupt.se.ttms.view.tmpl.*;

class MovieTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jt;

	public MovieTable(JScrollPane jp) {
		
		DefaultTableModel tabModel=new DefaultTableModel(){
			private static final long serialVersionUID = 1L;

			@Override              
			public boolean isCellEditable(int row,int column){
				return false;              
			};
		};
		tabModel.addColumn("id");
		tabModel.addColumn("img");
		tabModel.addColumn("影片名称");
		tabModel.addColumn("评分");
		tabModel.addColumn("导演");
		tabModel.addColumn("主演");
		tabModel.addColumn("影片类型");
		tabModel.addColumn("时长");
		tabModel.addColumn("上映时间");
		tabModel.addColumn("影片状态");
		tabModel.addColumn("影片简介");
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
        column = columnModel.getColumn(6);
        column.setPreferredWidth(10);   
        column = columnModel.getColumn(7);
        column.setPreferredWidth(10);   
        column = columnModel.getColumn(8);
        column.setPreferredWidth(10);   
        column = columnModel.getColumn(9);
        column.setPreferredWidth(10);   
        column = columnModel.getColumn(10);
        column.setPreferredWidth(100);       

		
		jp.add(jt);
		jp.setViewportView(jt);
		
	}
	
	public Movie getMovie() {
		int rowSel=jt.getSelectedRow();
		if(rowSel>=0){
			Movie mov = new Movie();
			mov.setMv_id(Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));
			mov.setImg(jt.getValueAt(rowSel, 1).toString());
			mov.setName(jt.getValueAt(rowSel, 2).toString());
			mov.setScore(Float.parseFloat(jt.getValueAt(rowSel, 3).toString()));
			mov.setDirector(jt.getValueAt(rowSel, 4).toString());
			mov.setActor(jt.getValueAt(rowSel, 5).toString());
			mov.setType(jt.getValueAt(rowSel, 6).toString());
			mov.setTime(jt.getValueAt(rowSel, 7).toString());
			mov.setDate(jt.getValueAt(rowSel, 8).toString());	
			mov.setStatus(jt.getValueAt(rowSel, 9).toString());
			if (jt.getValueAt(rowSel, 10) != null)
				mov.setIntroduction(jt.getValueAt(rowSel, 10).toString());
			else
				mov.setIntroduction("");

			return mov;
		}
		else{
			return null;
		}
			
	}
	
	// 创建JTable
	public void showMovieList(List<Movie> movList) {
		try {
			DefaultTableModel tabModel = (DefaultTableModel) jt.getModel();
			tabModel.setRowCount(0);
			
			Iterator<Movie> itr = movList.iterator();
			while (itr.hasNext()) {
				Movie mov = itr.next();
				Object data[] = new Object[11];
				data[0] = Integer.toString(mov.getMv_id());		
				data[1] = mov.getImg();
				data[2] = mov.getName();
				data[3] = Float.toString(mov.getScore());
				data[4] = mov.getDirector();
				data[5] = mov.getActor();
				data[6] = mov.getType();
				data[7] = mov.getTime();
				data[8] = mov.getDate();
				data[9] = mov.getStatus();
				data[10] = mov.getIntroduction();
				tabModel.addRow(data);
			}
			jt.invalidate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class MovieMgrUI extends MenuUITmpl {
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
	
	MovieTable tms; //显示演出厅列表


	public MovieMgrUI() {
		
	}

	// To be override by the detailed business block interface
	@Override
	protected void initContent() {
		Rectangle rect = contPan.getBounds();

		this.setTitle("这就是命剧院票务管理系统——影片管理");

		ca1 = new JLabel("影片管理", JLabel.CENTER);
		ca1.setBounds(0, 45, rect.width, 30);
		ca1.setFont(new java.awt.Font("华文行楷", 1, 20));
		ca1.setForeground(Color.blue);
		contPan.add(ca1);

		jsc = new JScrollPane();
		jsc.setBounds(0, 80, rect.width, rect.height - 130);
		contPan.add(jsc);

		hint = new JLabel("请输入影片名称:", JLabel.RIGHT);
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
		
		tms = new MovieTable(jsc);
		
		showTable();
	}

	private void btnAddClicked() {

		MovieAddUI addPlayUI=null;
		
		addPlayUI = new MovieAddUI();
		addPlayUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addPlayUI.setWindowName("添加影片");
		addPlayUI.toFront();
		addPlayUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		addPlayUI.setVisible(true);
		if (addPlayUI.getReturnStatus()) {
			showTable();
		}
	}

	private void btnModClicked() {
		Movie mov = tms.getMovie();
		if(null== mov){
			JOptionPane.showMessageDialog(null, "请选择要修改的影片");
			return; 
		}
		
		//String imgurl = mov.getImg();
		
		MovieEditUI modPlayUI = new MovieEditUI(mov);
		modPlayUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		modPlayUI.setWindowName("修改影片");
		modPlayUI.initData(mov);
		modPlayUI.toFront();
		modPlayUI.setModal(true);
		modPlayUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		modPlayUI.setVisible(true);

		if (modPlayUI.getReturnStatus()) {
			showTable();
		}	
	}

	private void btnDelClicked() {
		Movie mov = tms.getMovie();
		if(null== mov){
			JOptionPane.showMessageDialog(null, "请选择要删除的影片");
			return; 
		}		
		
		int confirm = JOptionPane.showConfirmDialog(null, "确认删除所选？", "删除", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			MovieSrv stuSrv = new MovieSrv();
			stuSrv.delete(mov.getMv_id());
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
		List<Movie> stuList = new MovieSrv().FetchAll();
		tms.showMovieList(stuList);
	}
	

	public static void main(String[] args) {
		MovieMgrUI frmStuMgr = new MovieMgrUI();
		frmStuMgr.setVisible(true);
	}
}
