package xupt.se.ttms.view.usecase.movie;


import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import xupt.se.ttms.model.Movie;
import xupt.se.ttms.service.MovieSrv;

public class MovieEditUI extends MovieAddUI {

	private static final long serialVersionUID = 1L;
	private Movie movie;
	
	public MovieEditUI(Movie mov) {
		this.setTitle("修改");
		initData(mov);
	}

	public void initData(Movie mov) {
		
		//imgurl = img;
		
		if(null== mov){
			return;
		}
		txtName.setText(mov.getName());
		txtType.setText(mov.getType());
		txtDirector.setText(mov.getDirector());
		txtActor.setText(mov.getActor());
		txtGrade.setText(Float.toString(mov.getScore()));
		txtlength.setText(mov.getTime());
		txttime.setText(mov.getDate());
		txtIntroduction.setText(mov.getIntroduction());
		status.setSelectedItem(mov.getStatus());
		img1.setIcon(new ImageIcon(mov.getImg()));
		
		movie = mov;
		this.invalidate();
	}

	
	@Override
	protected void btnSaveClicked(){
		if (txtName.getText() != null && txtType.getText() != null
				&& txtDirector.getText() != null && txtActor.getText() != null
				&& txtGrade.getText() != null && txtlength.getText() != null
				&& txttime.getText() != null && status.getSelectedItem() != null
				&&  imgurl != null) {
			MovieSrv movSrv = new MovieSrv();
			Movie mov = movie;
			mov.setName(txtName.getText());
			mov.setType(txtType.getText());
			mov.setDirector(txtDirector.getText());
			mov.setActor(txtActor.getText());
			mov.setScore(Float.parseFloat(txtGrade.getText()));
			mov.setTime(txtlength.getText());
			mov.setDate(txttime.getText());
			mov.setIntroduction(txtIntroduction.getText());
			mov.setStatus(status.getSelectedItem().toString());
			mov.setImg(imgurl);

			movSrv.modify(mov);
			this.setVisible(false);
			rst=true;
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}

}
