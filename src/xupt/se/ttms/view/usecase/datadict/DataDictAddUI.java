package xupt.se.ttms.view.usecase.datadict;

import javax.swing.JDialog;







import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;





//import view.studioUI.ImageJPanel;
import xupt.se.ttms.model.DataDict;
import xupt.se.ttms.service.DataDictSrv;
import xupt.se.ttms.view.component.CircleButton;
import xupt.se.ttms.view.component.ImageJPanel;
import xupt.se.ttms.view.tmpl.*;

public class DataDictAddUI extends PopUITmpl implements ActionListener {

	private JButton btnCancel, btnSave; 	//取消，保存按鈕

	protected boolean rst=false; 				//操作结果
	private JLabel lblIndex, lblName, lblValue;
	protected JTextField txtIndex, txtName, txtValue;

	public DataDictAddUI() {
	}

	@Override
	protected void initContent(){
		this.setTitle("添加数据字典");

		lblIndex = new JLabel("排列顺序：");
		lblIndex.setBounds(350, 130, 110, 30);
		lblIndex.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblIndex);
		txtIndex = new JTextField();
		txtIndex.setBounds(450, 130, 200, 30);
		contPan.add(txtIndex);

		lblName = new JLabel("字典名称：");
		lblName.setBounds(350, 190, 110, 30);
		lblName.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblName);
		txtName = new JTextField();
		txtName.setBounds(450, 190, 200, 30);
		contPan.add(txtName);

		lblValue = new JLabel("字典值：");
		lblValue.setBounds(350, 250,110, 30);
		lblValue.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblValue);
		txtValue = new JTextField();
		txtValue.setBounds(450, 250, 200, 30);
		contPan.add(txtValue);

		btnSave = new CircleButton("保存");

		btnSave.addActionListener(this);
		btnSave.setBounds(250, 400, 60, 30);
		btnSave.setFont(new Font("华文行楷", 1, 12));
		contPan.add(btnSave);

		btnCancel = new CircleButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(450, 400, 60, 30);
		btnCancel.setFont(new Font("华文行楷", 1, 12));
		contPan.add(btnCancel);

		ImageJPanel imageJP = new ImageJPanel(new ImageIcon(
				"resource/image/stdio.jpg").getImage());
		imageJP.setBounds(100, 90, 170, 250);
		imageJP.setLayout(null);
		contPan.add(imageJP);
		
		JLabel title = new JLabel("The Dict →");
		title.setBounds(140, 355, 170, 10);
		title.setFont(new Font("华文行楷", 1, 15));
		contPan.add(title);
	}
	
	
	public boolean getReturnStatus(){
		   return rst;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			rst=false;
			this.dispose();
			getParent().setVisible(true);

		} else if (e.getSource() == btnSave) {
			btnSaveClicked();
		}

	}
	
	protected void btnSaveClicked(){
		if (txtIndex.getText() != null && txtName.getText() != null
				&& txtValue.getText() != null) {
			DataDictSrv dictSrv = new DataDictSrv();
			DataDict ddict=new DataDict();
			ddict.setSuperId(0);
			ddict.setIndex(Integer.parseInt(txtIndex.getText()));
			ddict.setName(txtName.getText());
			ddict.setValue(txtValue.getText());

			dictSrv.add(ddict);
			this.setVisible(false);
			rst=true;
			getParent().setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}
}
