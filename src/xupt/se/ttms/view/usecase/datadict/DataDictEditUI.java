package xupt.se.ttms.view.usecase.datadict;

import javax.swing.JDialog;






import java.awt.Color;
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
import xupt.se.ttms.view.usecase.datadict.DataDictAddUI;

public class DataDictEditUI extends DataDictAddUI{

	int superid ;
	public DataDictEditUI(DataDict ddict) {
		this.setTitle("修改");
		initData(ddict);
	}

	private void initData(DataDict ddict) {
		superid = ddict.getSuperId();
		txtIndex.setText(Integer.toString(ddict.getIndex()));
		txtName.setText(ddict.getName());
		txtValue.setText(ddict.getValue());
	}

	@Override
	protected void btnSaveClicked(){
		if (txtIndex.getText() != null && txtName.getText() != null
				&& txtValue.getText() != null) {
			DataDictSrv dictSrv = new DataDictSrv();
			DataDict ddict=new DataDict();
			ddict.setSuperId(superid); 
			ddict.setIndex(Integer.parseInt(txtIndex.getText()));
			ddict.setName(txtName.getText());  
			ddict.setValue(txtValue.getText()); 

			dictSrv.modify(ddict);
			this.setVisible(false);
			rst=true;
			getParent().setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}

}
