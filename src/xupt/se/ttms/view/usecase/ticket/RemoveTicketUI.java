package xupt.se.ttms.view.usecase.ticket;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import xupt.se.ttms.view.component.ImageJPanel;
import xupt.se.ttms.view.tmpl.ImagePanel;
import xupt.se.ttms.view.tmpl.PopUITmplClerk;

public class RemoveTicketUI extends PopUITmplClerk {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2617889545969620119L;
	private ImagePanel background = new ImagePanel("resource/image/remove1.jpg");

	public RemoveTicketUI() {
//		background.setBounds(0, 100, 800, 500);
//		background.setLayout(null);
//		this.add(background);
		JLabel back = new JLabel("本影院退票系统维护中……");
		back.setBounds(200, 100, 500, 300);
		back.setFont(new Font("华文行楷", 1, 30));
		back.setForeground(Color.MAGENTA);
		contPan.add(back);
		
		ImageJPanel imageJP = new ImageJPanel(new ImageIcon(
				"resource/image/fire-toy.png").getImage());
		imageJP.setBounds(550, 250, 256, 256);
		imageJP.setLayout(null);
		contPan.add(imageJP);
	}
	
	public static void main(String[] args) {
		RemoveTicketUI remove = new RemoveTicketUI();
		remove.setTitle("退票");
		remove.setVisible(true);
	}
}
