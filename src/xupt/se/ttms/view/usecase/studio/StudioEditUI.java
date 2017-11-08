package xupt.se.ttms.view.usecase.studio;


import javax.swing.JOptionPane;

import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.usecase.studio.StudioAddUI;

public class StudioEditUI extends StudioAddUI{

	private static final long serialVersionUID = 1L;
	private Studio stud;

	public StudioEditUI(Studio stu){
		this.setTitle("修改");
		initData(stu);
	}
	
	public void initData(Studio stu) {
		if(null== stu){
			return;
		}
		txtName.setText(stu.getName());
		txtRow.setText(Integer.toString(stu.getRowCount()));
		txtColumn.setText(Integer.toString(stu.getColCount()));
		txtIntro.setText(stu.getIntroduction());
		stud=stu;
		this.invalidate();
	}

	@Override
	protected void btnSaveClicked(){
		if (txtName.getText() != null && txtRow.getText() != null
				&& txtColumn.getText() != null) {
			StudioSrv stuSrv = new StudioSrv();
			Studio stu= stud;
			stu.setName(txtName.getText());
			stu.setRowCount(Integer.parseInt(txtRow.getText()));
			stu.setColCount(Integer.parseInt(txtColumn.getText()));
			stu.setIntroduction(txtIntro.getText());
			stuSrv.modify(stu);
			this.setVisible(false);
			rst=true;
			
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}
	
}



