/**
 * 
 */
package xupt.se.ttms.view.tmpl;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Insets;

import xupt.se.ttms.view.component.CircleButton;
import xupt.se.ttms.view.frame.ClerkMenuFrame;
import xupt.se.ttms.view.frame.CustomerMenuFrame;
import xupt.se.ttms.view.frame.EmpMenuFrame;
import xupt.se.ttms.view.frame.LoginFrame;
import xupt.se.ttms.view.system.SysUserModUI;
import xupt.se.ttms.view.usecase.customer.*;
import xupt.se.ttms.service.LoginedUser;

/**
 * @author Administrator
 *
 */

public class MenuUITmplCus extends JFrame  {

	private static final long serialVersionUID = 1L;
	private int frmWidth=1024;
	private int frmHeight=700;
	protected final ImagePanel headPan = new ImagePanel("resource/image/title.jpg");
	protected final ImagePanel background = new ImagePanel("resource/image/background.jpg");
	protected final JPanel contPan = new JPanel();
	protected JLabel usrLabel = new JLabel();
	protected JLabel usrName = new JLabel();
	protected JButton btnModPwd = new CircleButton("更换账号");
	protected JButton btnExit = new CircleButton("返回");
	private static String username;
	private static int userid;
	
	public MenuUITmplCus(){
		
		this.setSize(frmWidth, frmHeight);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("这就是命剧院票务管理系统");
		this.setLayout(null);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				onWindowClosing();
			}
		});		
		
		headPan.setBounds(0, 0, frmWidth, 120);
		headPan.setLayout(null);
		this.add(headPan);
		
//		background.setBounds(0, 120, frmWidth, this.frmHeight-120);
//		background.setLayout(null);
//		this.add(background);
		
		contPan.setBounds(0, 80, frmWidth, this.frmHeight-100);
		contPan.setLayout(null);
		this.add(contPan);	
		
		initHeader("");
		initContent();
	}

	public MenuUITmplCus(String name, int id){
		this.setSize(frmWidth, frmHeight);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("这就是命剧院票务管理系统");
		this.setLayout(null);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				onWindowClosing();
			}
		});		
		
		headPan.setBounds(0, 0, frmWidth, 120);
		headPan.setLayout(null);
		this.add(headPan);
		
//		background.setBounds(0, 120, frmWidth, this.frmHeight-120);
//		background.setLayout(null);
//		this.add(background);
		
		contPan.setBounds(0, 80, frmWidth, this.frmHeight-100);
		contPan.setLayout(null);
		this.add(contPan);	
		
		username = name;
		userid = id;
		initHeader(name);
		initContent();
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//static String name = null;
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MenuUITmpl(username).setVisible(true);;
					
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null, e, "Exception", 0);
					e.printStackTrace();
				}
			}
		});
		
	}
	public int getWidth(){
		return this.frmWidth;
		
	}
	public int getHeight(){
		return this.frmHeight;
		
	}
	
	private void initHeader( String name ) {
		try {

			usrLabel.setBounds(frmWidth-190, 20, 80, 30);
			usrLabel.setText("当前用户：");
			usrLabel.setFont(new java.awt.Font("华文行楷", 1, 15));
			usrLabel.setForeground(Color.cyan);	
			headPan.add(usrLabel);
			
			usrName.setBounds(frmWidth-110, 20, 80, 30);
			usrName.setText("匿名");
			usrName.setFont(new java.awt.Font("宋体", 1, 15));
			usrName.setForeground(Color.blue);				
			headPan.add(usrName);
			
			btnModPwd.setBounds(frmWidth-200, 60, 80, 30);
			btnModPwd.setMargin(new Insets(0,0,0,0));
			btnModPwd.setForeground(Color.red);
			btnModPwd.setFont(new Font("华文行楷", 1, 15));
			//btnModPwd.setBackground(Color.LIGHT_GRAY);
			btnModPwd.setContentAreaFilled(false);
			headPan.add(btnModPwd);
			btnModPwd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent Event) {
					//btnModUserClicked();
					new LoginFrame();
					MenuUITmplCus.this.dispose();
				}
			});
			
			btnExit.setBounds(frmWidth-120, 60, 80, 30);
			btnExit.setContentAreaFilled(false);
			btnExit.setForeground(Color.red);
			btnExit.setFont(new Font("华文行楷", 1, 15));
			//btnExit.setBackground(Color.LIGHT_GRAY);
			btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent Event) {
					btnExitClicked(Event);
				}
			});
			
			headPan.add(btnExit);	
			
			//Show the information of current user
			showCurrentUser(name);
			
		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, e, "Exception", 0);
			e.printStackTrace();
		}
	}
	
	
	private void btnModUserClicked(){
		SysUserModUI dlgUserMod=new SysUserModUI();
		dlgUserMod.setModal(true);
		dlgUserMod.setVisible(true);
	}	
	
	private void showCurrentUser(String name){
//		LoginedUser curUser = LoginedUser.getInstance();
//		String name = curUser.getEmpName();
		if(null==name ||  name.isEmpty())
			usrName.setText("admin");
		else
			usrName.setText(name);		
	}
	
	
	//To be override by the detailed business block interface 
	protected void onWindowClosing(){
		System.exit(0);
	}
	
	
	//To be override by the detailed business block interface 
	protected void initContent(){
	}
	
	//To be override by the detailed business block interface 
	protected void btnExitClicked(ActionEvent Event){
		CustomerMenuFrame customerMenuFrame = new CustomerMenuFrame(username, userid);
		customerMenuFrame.setVisible(true);
		this.dispose();
	}	

}
