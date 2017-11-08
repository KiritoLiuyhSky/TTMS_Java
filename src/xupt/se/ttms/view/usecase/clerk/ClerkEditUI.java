package xupt.se.ttms.view.usecase.clerk;

import javax.swing.JOptionPane;

import xupt.se.ttms.model.Clerk;
import xupt.se.ttms.service.ClerkSrv;


public class ClerkEditUI extends ClerkAddUI{

	private static final long serialVersionUID = 1L;
	private Clerk stud;

	public ClerkEditUI(Clerk clr){
		this.setTitle("修改");
		initData(clr);
	}
	
	public void initData(Clerk clr) {
		if(null== clr){
			return;
		}
		
		txtNick.setText(clr.getClerkNick());
		txtName.setText(clr.getClerkName());
		txtId.setText(clr.getClerkNum());
		txtPsd.setText(clr.getClerkPsd());
		if(clr.getClerkSex().equals("男")) {
			man.setSelected(true); 
		} else {
			woman.setSelected(true);
		}
		//txtSex.setText(clr.getClerkSex());
		txtDate.setText(clr.getClerkDate());
		txtAge.setText(clr.getClerkAge());
		txtPhone.setText(clr.getClerkPhone());
		txtIntro.setText(clr.getClerkIntroduction());
		stud=clr;
		this.invalidate();
	}

	@Override
	protected void btnSaveClicked(){
		if (txtName.getText() != null && txtPsd.getText() != null
				&& (man.getText() != null || woman.getText() != null) && txtId.getText() != null 
				&& txtAge.getText() != null && txtDate.getText() != null 
				&& txtPhone.getText() != null && txtNick.getText() != null) {
			ClerkSrv clrSrv = new ClerkSrv();
			Clerk clr= stud;
			
			clr.setClerkNick(txtNick.getText());
			clr.setClerkName(txtName.getText());
			clr.setClerkNum(txtId.getText());
			clr.setClerkPsd(txtPsd.getText());
			//clr.setClerkSex(txtSex.getText());
			if(man.isSelected()) {
				clr.setClerkSex(man.getText());
			} else {
				clr.setClerkSex(woman.getText());
			}
			clr.setClerkDate(txtDate.getText());
			clr.setClerkAge(txtAge.getText());
			clr.setClerkPhone(txtPhone.getText());
			clr.setClerkIntroduction(txtIntro.getText());
			
			clrSrv.modify(clr);
			this.setVisible(false);
			rst=true;
			
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}
	
}
