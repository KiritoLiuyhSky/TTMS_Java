package xupt.se.ttms.view.frame;


import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;

import xupt.se.ttms.model.Clerk;
import xupt.se.ttms.model.Customer;
import xupt.se.ttms.model.Employee;
import xupt.se.ttms.model.Movie;
import xupt.se.ttms.service.ClerkSrv;
import xupt.se.ttms.service.CustomerSrv;
import xupt.se.ttms.view.component.CircleButton;
import xupt.se.ttms.view.usecase.customer.CustomerAddUI;


public class LoginFrame extends JFrame implements ActionListener {
		
	GridLayout grid;
	JLabel title, people;
	Box box, box1, box2, box3, box4, baseBox, picBox, buttonBox;
	JLabel labelLoginPsw, check;
	JTextField textField;
	JPasswordField password;
	JButton confirm, cancel, register, findpsd;
	ImageIcon background,sculpture;
	JPanel imagePanel,sculpturePanel;
	private JLabel labelLoginAct;
	private JComboBox jcb;
    private JCheckBox jc1;
    private JCheckBox jc2;
    private JRadioButton jr1, jr2, jr3;
	
	public LoginFrame() {
		init();
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	
	public void init(){
	
		setTitle("欢迎使用");
		setLayout(new FlowLayout());
		background = new ImageIcon("resource/image/LoginBackground.jpg");
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, background.getIconWidth(),
				background.getIconHeight());
		imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		imagePanel.setLayout(new FlowLayout());
//		sculpturePanel = (JPanel) this.getContentPane();
//		sculpturePanel.setOpaque(false);
//		sculpturePanel.setLayout(new FlowLayout());
		// this.getLayeredPane().setLayout(null);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		this.setSize(background.getIconWidth(), background.getIconHeight());

		sculpture = new ImageIcon("resource/image/sculpture4.jpg");
		JLabel la = new JLabel(sculpture);
		la.setBounds(10, 100, 5, 5);
		imagePanel.add(la);
		
		labelLoginAct = new JLabel("账号:");
		labelLoginAct.setForeground(Color.WHITE);
		labelLoginAct.setFont(new Font("华文行楷", 1, 15));
		
        // 输入框下方文字
//        jc1 = new JCheckBox("记住密码");
//        jc1.setForeground(Color.WHITE);
//        jc1.setFont(new Font("华文行楷", 1, 15));
//        jc1.setOpaque(false);
//        jc2 = new JCheckBox("自动登录");
//        jc2.setForeground(Color.white);
//        jc2.setFont(new Font("华文行楷", 1, 15));
//        jc2.setOpaque(false);
        
		// 用户登录状态选择
//        check = new JLabel("登录选项:");
//        check.setForeground(Color.orange);
//        check.setFont(new Font("华文行楷", 1, 15));
        jr1 = new JRadioButton("管理员登录");
        jr1.setFont(new Font("华文行楷", 1, 15));
        jr1.setForeground(Color.GREEN);
        jr1.setSelected(true);
        jr1.setOpaque(false);
        jr2 = new JRadioButton("员工登录");
        jr2.setFont(new Font("华文行楷", 1, 15));
        jr2.setForeground(Color.GREEN);
        jr2.setOpaque(false);
        jr3 = new JRadioButton("用户登录");
        jr3.setFont(new Font("华文行楷", 1, 15));
        jr3.setForeground(Color.GREEN);
        jr3.setOpaque(false);
        ButtonGroup group = new ButtonGroup();
        group.add(jr1);
        group.add(jr2);
        group.add(jr3);
//        jcb = new JComboBox();
//        jcb.addItem("管理员");
//        jcb.addItem("员工");
//        jcb.addItem("用户");
//        jcb.setFont(new Font("华文行楷", 1, 13));
        
        
        register = new JButton("<HTML><U>用户注册</U></HTML>");
        register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				//LoginFrame.this.dispose();
				CustomerAddUI s =new CustomerAddUI();
				s.setWindowName("用户注册");
				s.setVisible(true);
			}
		});
        register.setForeground(Color.CYAN);
        register.setFont(new Font("华文行楷", 1, 15));
        register.setBorderPainted(false);
        register.setContentAreaFilled(false);
        
		labelLoginPsw = new JLabel("密码:");
		labelLoginPsw.setForeground(Color.WHITE);
		labelLoginPsw.setFont(new Font("华文行楷", 1, 15));
		
        findpsd = new JButton("<HTML><U>忘记密码</U></HTML>");
        findpsd.setForeground(Color.CYAN);
        findpsd.setFont(new Font("华文行楷", 1, 15));
        findpsd.setBorderPainted(false);
        findpsd.setContentAreaFilled(false);
        findpsd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
//				LoginFrame.this.dispose();
//				CustomerAddUI s =new CustomerAddUI();
//				s.setWindowName("忘记密码");
//				s.setVisible(true);
			}
		});
        
		textField = new JTextField(10);
		password = new JPasswordField(10);
		
		confirm = new CircleButton("登录");
		confirm.setFont(new java.awt.Font("华文行楷", 1, 15));
		confirm.setBackground(Color.LIGHT_GRAY);
		
		cancel = new CircleButton("退出");
		cancel.setFont(new java.awt.Font("华文行楷", 1, 15));
		cancel.setBackground(Color.LIGHT_GRAY);
		// register = new JButton("注册");
		
		
		box1 = Box.createHorizontalBox();
		box1.add(labelLoginAct);
		box1.add(Box.createHorizontalStrut(10));
		box1.add(textField);
		box1.add(Box.createHorizontalStrut(5));
		box1.add(register);

		box2 = Box.createHorizontalBox();
		box2.add(labelLoginPsw);
		box2.add(Box.createHorizontalStrut(10));
		box2.add(password);
		box2.add(Box.createHorizontalStrut(5));
		box2.add(findpsd);

//		box3 = Box.createHorizontalBox();
//		box3.add(jc1);
//		box3.add(Box.createHorizontalStrut(10));
//		box3.add(jc2);
//		box3.add(Box.createHorizontalStrut(10));
//		box3.add(check);
//		box3.add(Box.createHorizontalStrut(10));
//		box3.add(jcb);
		box3 = Box.createHorizontalBox();
//		box3.add(check);
//		box3.add(Box.createHorizontalStrut(10));
		box3.add(jr1);
		box3.add(Box.createHorizontalStrut(10));
		box3.add(jr2);
		box3.add(Box.createHorizontalStrut(10));
		box3.add(jr3);
		
		picBox = Box.createVerticalBox();
		picBox.add(Box.createVerticalStrut(150));
		picBox.add(la);

		baseBox = Box.createVerticalBox();
		baseBox.add(Box.createVerticalStrut(150));
		baseBox.add(box1);
		baseBox.add(Box.createVerticalStrut(30));
		baseBox.add(box2);
//		baseBox.add(Box.createVerticalStrut(30));
//		baseBox.add(box3);

		box = Box.createHorizontalBox();
		box.add(picBox);
		box.add(Box.createHorizontalStrut(10));
		box.add(baseBox);
		
		box4 = Box.createHorizontalBox();
		box4.add(confirm);
		box4.add(Box.createHorizontalStrut(30));
		box4.add(cancel);
		// box3.add(Box.createHorizontalStrut(10));
		// box3.add(register);
		
		buttonBox = Box.createVerticalBox();
		buttonBox.add(box);
		buttonBox.add(Box.createVerticalStrut(20));
		buttonBox.add(box3);
		buttonBox.add(Box.createVerticalStrut(20));
		buttonBox.add(box4);
		
		// add(title);
//		add(picBox);
//		add(box);
//		add(baseBox);
		add(buttonBox);
		
		
		// add(people);

		// title.setVisible(false);
		confirm.addActionListener(this);
		cancel.addActionListener(this);
		this.getRootPane().setDefaultButton(confirm); 
		
	}
//	public void actionPerformed(ActionEvent e) {
//
//		if (e.getActionCommand().equals("登录")) {
//			if (textField.getText().equals("admin")
//					&& password.getText().equals("admin")) {
//				ClerkMenuFrame clerkMenuFrame = new ClerkMenuFrame();
//				clerkMenuFrame.setVisible(true);
//		
//				this.setVisible(false);
//			} else {
//
//				JOptionPane.showMessageDialog(this, "帐号或密码错误", "提示",
//						JOptionPane.ERROR_MESSAGE);
//				password.setText(null);
//			}
//		}
//		if (e.getActionCommand().equals("退出")) {
//
//			System.exit(0);
//		}
//	}
	
    public void actionPerformed(ActionEvent e) {
        String str=e.getActionCommand();
        	
            //管理员
            if(/*jcb.getSelectedIndex() == 0*/jr1.isSelected()){
            	if (e.getActionCommand().equals("登录")) {
        			if (textField.getText().equals("admin")
        					&& password.getText().equals("admin")) {
        				ClerkMenuFrame clerkMenuFrame = new ClerkMenuFrame();
        				clerkMenuFrame.setVisible(true);
        		
        				this.setVisible(false);
        			} else {
        
        				JOptionPane.showMessageDialog(this, "帐号或密码错误", "提示",
        						JOptionPane.ERROR_MESSAGE);
        				password.setText(null);
        			}
        		}
            }
        		if (e.getActionCommand().equals("退出")) {
        
        			System.exit(0);
        		}
        		//员工clerk
        		else if(/*jcb.getSelectedIndex() == 1*/jr2.isSelected()){
        			ClerkSrv clerk = new ClerkSrv();
        			List<Clerk> clerks = clerk.FetchAll();

        			String name = textField.getText();
        			String pw = password.getText();
        			boolean flag = true;
        			
        			Iterator<Clerk> it2 = clerks.iterator();
        			Clerk cle;
                 	if (e.getActionCommand().equals("登录")) {
                 		for(int i = 0;it2.hasNext();i++){
    	    				cle = it2.next();
    			            if (name.equals(cle.getClerkNick())){
    			            	if ( pw.equals(clerks.get(i).getClerkPsd()) ){
    			            		EmpMenuFrame empMenuFrame = new EmpMenuFrame(name, clerks.get(i).getClerkID());
    		         				empMenuFrame.setVisible(true);
    		         				this.setVisible(false);
    		         				break;
    			            	} else {
    			            		flag = false;
    			            	}
    			            } else {
    			            	
    			            }
    			        }
             		} 
                 	if(flag == false) {
	            		JOptionPane.showMessageDialog(this, "帐号或密码错误", "提示",
         						JOptionPane.ERROR_MESSAGE);
         				password.setText(null);
                 	}
             		if (e.getActionCommand().equals("退出")) {
             
             			System.exit(0);
             		}
                	
                }
                //顾客Customer
                else if(/*jcb.getSelectedIndex() == 2*/jr3.isSelected())
                {
                	CustomerSrv customer = new CustomerSrv();
        			List<Customer> customers = customer.FetchAll();

        			boolean flag = true;
        			String name = textField.getText();
        			String pw = password.getText();
        			
        			Iterator<Customer> it2 = customers.iterator();
        			Customer cle;
                 	if (e.getActionCommand().equals("登录")) {
                 		for(int i = 0;it2.hasNext();i++){
    	    				cle = it2.next();
    			            if (name.equals(cle.getNick())){
    			            	if ( pw.equals(customers.get(i).getPsd()) ){
    			            		CustomerMenuFrame customerMenuFrame = new CustomerMenuFrame(name, customers.get(i).getID());
    		         				customerMenuFrame.setVisible(true);
    		         				this.setVisible(false);
    		         				break;
    			            	} else {
    			            		flag = false;
    			            	}
    			            } else {
    			            	//flag = false;
    			            }
    			        }
             		}
                 	if(flag == false) {
	            		JOptionPane.showMessageDialog(this, "帐号或密码错误", "提示",
         						JOptionPane.ERROR_MESSAGE);
         				password.setText(null);
                 	}
             		if (e.getActionCommand().equals("退出")) {
             
             			System.exit(0);
             		}
                	
                }
        	}
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new LoginFrame();
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null, e, "Exception", 0);
					e.printStackTrace();
				}
			}
		});

	}

}
