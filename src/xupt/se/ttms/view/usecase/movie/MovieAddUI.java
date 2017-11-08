package xupt.se.ttms.view.usecase.movie;

import javax.swing.JDialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import xupt.se.ttms.model.Movie;
import xupt.se.ttms.service.MovieSrv;
import xupt.se.ttms.view.component.CircleButton;
import xupt.se.ttms.view.component.CircleTextField;
import xupt.se.ttms.view.component.ImageJPanel;
import xupt.se.ttms.view.tmpl.*;

public class MovieAddUI extends PopUITmpl implements ActionListener {

	protected JButton btnCancel; 	//取消，保存按鈕

	protected JButton btnSave;

	protected boolean rst=false; 				//操作结果
	private JLabel lblName, lblType, lblDirector, lblActor, lblGrade, lbllength, lbltime, lblIntroduction, lblStatus;
	protected JTextField txtName, txtType, txtDirector, txtActor, txtGrade, txtlength, txttime;
	protected JComboBox status;
	JTextArea txtIntroduction;
	protected ImageIcon image;
	protected static String imgurl="";
	protected JLabel img1;
	
	public MovieAddUI() {
	}

	@Override
	protected void initContent(){
		this.setTitle("添加影片");

		lblName = new JLabel("影片名称：");
		lblName.setBounds(220, 60, 100, 30);
		lblName.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblName);
		txtName = new JTextField(10);
		txtName.setBounds(300, 60, 120, 30);
		contPan.add(txtName);
		
		lblType = new JLabel("影片类型：");
		lblType.setBounds(220, 110, 100, 30);
		lblType.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblType);
		txtType = new JTextField(10);
		txtType.setBounds(300, 110, 120, 30);
		contPan.add(txtType);
		
		lblDirector = new JLabel("导演：");
		lblDirector.setBounds(220, 160, 100, 30);
		lblDirector.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblDirector);
		txtDirector = new JTextField(10);
		txtDirector.setBounds(300, 160, 120, 30);
		
		contPan.add(txtDirector);
		
		lblActor = new JLabel("主演：");
		lblActor.setBounds(220, 210, 100, 30);
		lblActor.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblActor);
		txtActor = new JTextField(10);
		txtActor.setBounds(300, 210, 120, 30);
		contPan.add(txtActor);
		
		lblGrade = new JLabel("评分：");
		lblGrade.setBounds(220, 260, 100, 30);
		lblGrade.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblGrade);
		txtGrade = new JTextField(10);
		txtGrade.setBounds(300, 260, 120, 30);
		contPan.add(txtGrade);
		
		lbllength = new JLabel("时长：");
		lbllength.setBounds(220, 310, 100, 30);
		lbllength.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lbllength);
		txtlength = new JTextField(10);
		txtlength.setBounds(300, 310, 120, 30);
		contPan.add(txtlength);
		
		lbltime = new JLabel("上映时间：");
		lbltime.setBounds(220, 360, 100, 30);
		lbltime.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lbltime);
		txttime = new JTextField(10);
		txttime.setBounds(300, 360, 120, 30);
		contPan.add(txttime);
		
		lblStatus = new JLabel("影片状态：");
		lblStatus.setBounds(450, 60, 100, 30);
		lblStatus.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblStatus);
		status = new JComboBox();
		status.addItem(null);
		status.addItem("正在上映");
		status.addItem("即将上映");
		status.addItem("下架");
		status.setBounds(530, 60, 120, 30);
		contPan.add(status);
		
		lblIntroduction = new JLabel("影片简介：");
		lblIntroduction.setBounds(450, 110, 100, 30);
		lblIntroduction.setFont(new Font("华文行楷", 1, 15));
		contPan.add(lblIntroduction);
		txtIntroduction = new JTextArea();
		txtIntroduction.setLineWrap(true);
		txtIntroduction.setWrapStyleWord(true);
		txtIntroduction.setBorder(BorderFactory.createLineBorder(Color.gray,1));
		txtIntroduction.setBounds(450, 150, 290, 240);
		contPan.add(txtIntroduction);

		btnSave = new CircleButton("保存");

		btnSave.addActionListener(this);
		btnSave.setBounds(250, 420, 60, 30);
		btnSave.setFont(new Font("华文行楷", 1, 12));
		contPan.add(btnSave);

		btnCancel = new CircleButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(450, 420, 60, 30);
		btnCancel.setFont(new Font("华文行楷", 1, 12));
		contPan.add(btnCancel);

		JFileChooser fc=new JFileChooser();
		String img=imgurl;
		img1= new JLabel("      添加图片");
		 img1.setFont(new Font("华文行楷", 1, 15));
		 img1.setBorder(BorderFactory.createLineBorder(Color.decode("#c0c0c0")));
		 img1.setBounds(60 ,130, 130, 180);
		 image = new ImageIcon(imgurl);
		 img1.setIcon(image);
		 
		 contPan.add(img1);
		 img1.addMouseListener(new  MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO 自动生成的方法存根
				 JFileChooser jfc = new JFileChooser(new File("E:\\workspace\\TTMS_JAVA\\resource\\movie"));
	    	     if(jfc.showOpenDialog(MovieAddUI.this)==JFileChooser.APPROVE_OPTION ){
	    	    	 File file=jfc.getSelectedFile();
	    	    	 String URL=file.getPath();
	    	    	 //MovieAddUI.imgurl=URL;
	    	    	 imgurl=URL;
	    	    	 ImageIcon image = new ImageIcon(URL);
	    	    	 img1.setIcon(image);
	             
			}
			}
		});
		
//		ImageJPanel imageJP = new ImageJPanel(new ImageIcon(
//				"resource/image/pencil.jpg").getImage());
//		imageJP.setBounds(360, 160, 100, 100);
//		imageJP.setLayout(null);
//		this.add(imageJP);
	}
	
	
	public boolean getReturnStatus(){
		   return rst;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			rst=false;
			this.setVisible(false);

		} else if (e.getSource() == btnSave) {
			btnSaveClicked();		//以前未调用，新添加的调用语句
		}

	}
	
	protected void btnSaveClicked(){
		if (txtName.getText() != null && txtType.getText() != null
				&& txtDirector.getText() != null && txtActor.getText() != null
				&& txtGrade.getText() != null && txtlength.getText() != null
				&& txttime.getText() != null && status.getSelectedItem()!= null
				&&  imgurl != null) {
			MovieSrv movSrv = new MovieSrv();
			Movie mov=new Movie();
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
			
			movSrv.add(mov);
			this.setVisible(false);
			rst=true;
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}
}
