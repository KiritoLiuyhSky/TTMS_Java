package xupt.se.ttms.view.usecase.schedule;



import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import xupt.se.ttms.model.Movie;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.MovieSrv;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.usecase.studio.StudioAddUI;

public class ScheduleEditUI extends ScheduleAddUI{

	private static final long serialVersionUID = 1L;
	private Schedule stud;

	public ScheduleEditUI(Schedule stu){
		this.setTitle("修改");
		initData(stu);
	}
	
	public void initData(Schedule stu) {
		if(null== stu){
			return;
		}
		dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm" );
		
		//list studio
		List<Studio> stuList = new StudioSrv().FetchAll();
		Iterator<Studio> it1 = stuList.iterator();
		Studio studi = null;
		for(;it1.hasNext();){
			studi = it1.next();
            if (stu.getStudio_id() == studi.getID()){
            	studiolist.setSelectedItem(String.valueOf(studi.getName()));
            }
        }
		
		//list movie
		List<Movie> movList = new MovieSrv().FetchAll();
		Iterator<Movie> it2 = movList.iterator();
		Movie movi = null;
		for(;it2.hasNext();){
			movi = it2.next();
            if (stu.getMv_id() == movi.getMv_id()){
            	movielist.setSelectedItem(String.valueOf(movi.getName()));
            }
        }
		
//		txtStdio.setText(Integer.toString(stu.getStudio_id()));
//		txtMovie.setText(Integer.toString(stu.getMv_id()));
		txtDate.setText(dateFormat.format(stu.getSched_time()));
		txtPrice.setText(Double.toString(stu.getSched_ticket_price()));
		stud=stu;
		this.invalidate();
	}

	@Override
	protected void btnSaveClicked(){
		if (/*txtStdio.getText() != null && txtMovie.getText() != null*/
				studiolist.getSelectedItem() != null && movielist.getSelectedItem() != null
				&& txtDate.getText() != null && txtPrice != null ) {
			ScheduleSrv stuSrv = new ScheduleSrv();
			Schedule stu= stud;
			
			//list studio
			List<Studio> stuList = new StudioSrv().FetchAll();
			Iterator<Studio> it1 = stuList.iterator();
			Studio stud = null;
			for(;it1.hasNext();){
				stud = it1.next();
	            if (stud.getName().equals(studiolist.getSelectedItem().toString())){
	            	stu.setStudio_id(stud.getID());
	            }
	        }
			
			//list movie
			List<Movie> movList = new MovieSrv().FetchAll();
			Iterator<Movie> it2 = movList.iterator();
			Movie movi = null;
			for(;it2.hasNext();){
				movi = it2.next();
	            if (movi.getName().equals(movielist.getSelectedItem().toString())){
	            	stu.setMv_id(movi.getMv_id());
	            }
	        }
			
//			stu.setStudio_id(Integer.parseInt(txtStdio.getText()));
//			stu.setMv_id(Integer.parseInt(txtMovie.getText()));		
			try {
				stu.setSched_time(dateFormat.parse(txtDate.getText()));
			} catch (ParseException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}	
			stu.setSched_ticket_price(Float.parseFloat(txtPrice.getText()));
			stuSrv.modify(stu);
			this.setVisible(false);
			rst=true;
			
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}
	
}