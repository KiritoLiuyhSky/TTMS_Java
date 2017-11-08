package xupt.se.ttms.view.tmpl;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import xupt.se.ttms.view.frame.ClerkMenuFrame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PopUITmplClerk extends JDialog {
	private static final long serialVersionUID = 1L;
	private int frmWidth=800;
	private int frmHeight=600;
	public final ImagePanel headPan = new ImagePanel("resource/image/title.jpg");
	public JPanel contPan = new JPanel();
	public JLabel windowName = new JLabel();

	public PopUITmplClerk(){
		this.setSize(frmWidth, frmHeight);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				onWindowClosing();
			}
		});		
		
		headPan.setBounds(0, 0, frmWidth, 100);
		headPan.setLayout(null);
		this.add(headPan);
		
		contPan.setBounds(0, 60, frmWidth, this.frmHeight-80);
		contPan.setLayout(null);
		this.add(contPan);	
		
		initHeader();
		initContent();		
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new PopUITmpl().setVisible(true);;
					
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null, e, "Exception", 0);
					e.printStackTrace();
				}
			}
		});
		
	}
	
	private void initHeader() {
		try {


			windowName.setBounds(frmWidth-160, 20, 160, 50);
			windowName.setFont(new java.awt.Font("华文行楷", 1, 20));
			windowName.setForeground(Color.blue);	
			headPan.add(windowName);
			setWindowName("模块名称");
			
			
		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, e, "Exception", 0);
			e.printStackTrace();
		}
	}
	

	//Set the name of the popup window 
	public void setWindowName(String name){
		windowName.setText(name);
	}
	
	//To be override by the detailed business block interface 
	protected void onWindowClosing(){
		ClerkMenuFrame clerkMenuFrame = new ClerkMenuFrame();
		clerkMenuFrame.setVisible(true);
		this.dispose();
	}
	
	
	//To be override by the detailed business block interface 
	protected void initContent(){
		
	}
	

}
