package xupt.se.ttms.view.usecase.customer;

import javax.swing.JOptionPane;

import xupt.se.ttms.model.Customer;
import xupt.se.ttms.service.CustomerSrv;



public class CustomerEditUI extends CustomerAddUI{

	private static final long serialVersionUID = 1L;
	private Customer stud;

	public CustomerEditUI(Customer clr){
		this.setTitle("用户修改");
		initData(clr);
	}
	
	public void initData(Customer clr) {
		if(null== clr){
			return;
		}
		
		txtNick.setText(clr.getNick());
		txtPsd.setText(clr.getPsd());
		txtName.setText(clr.getName());
		txtEmail.setText(clr.getEmail());
		if(clr.getSex().equals("男")) {
			man.setSelected(true); 
		} else {
			woman.setSelected(true);
		}
		//txtSex.setText(clr.getSex());
		txtDate.setText(clr.getDate());
		txtAge.setText(clr.getAge());
		txtPhone.setText(clr.getPhone());
		txtIntro.setText(clr.getIntroduction());
		stud=clr;
		this.invalidate();
	}

	@Override
	protected void btnSaveClicked(){
		if (txtName.getText() != null && txtPsd.getText() != null
				&& (man.getText() != null || woman.getText() != null) && txtEmail.getText() != null 
				&& txtAge.getText() != null && txtDate.getText() != null 
				&& txtPhone.getText() != null && txtNick.getText() != null) {
			CustomerSrv clrSrv = new CustomerSrv();
			Customer clr= stud;
			
			clr.setNick(txtNick.getText());
			clr.setPsd(txtPsd.getText());
			clr.setName(txtName.getText());
			clr.setEmail(txtEmail.getText());
			//clr.setSex(txtSex.getText());
			if(man.isSelected()) {
				clr.setSex(man.getText());
			} else {
				clr.setSex(woman.getText());
			}
			clr.setDate(txtDate.getText());
			clr.setAge(txtAge.getText());
			clr.setPhone(txtPhone.getText());
			clr.setIntroduction(txtIntro.getText());
			
			clrSrv.modify(clr);
			this.setVisible(false);
			rst=true;
			
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}
	
}

