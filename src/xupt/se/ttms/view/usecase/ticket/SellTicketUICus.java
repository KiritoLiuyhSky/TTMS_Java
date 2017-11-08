package xupt.se.ttms.view.usecase.ticket;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import xupt.se.ttms.model.Movie;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Ticket;
import xupt.se.ttms.service.MovieSrv;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.service.SeatSrv;
import xupt.se.ttms.service.SellTicketHandler;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.service.TicketSrv;
import xupt.se.ttms.view.component.CircleButton;
import xupt.se.ttms.view.component.CircleTextField;
import xupt.se.ttms.view.tmpl.*;
import xupt.se.ttms.view.usecase.seat.SeatChooseUICus;


public class SellTicketUICus extends MenuUITmplCus {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel ca1 = null; // 界面提示
	// 用来放表格的滚动控件
	private JScrollPane jsc;
	// 查找的提示和输出
	private JLabel hint;
	private JTextField input;

	// 查找，编辑和删除按钮
	private JButton btnCho, btnTic, btnDel, btnQuery;
	
	private JTabbedPane tabPane;
	public JPanel salePanel;
	private JPanel salePanel2;
	private JPanel salePanel3;
	private JPanel upPanel;
	private JPanel leftPanel;
//	private JPanel middlePanel;
	private JPanel rightPanel;
	private JButton left;
	private JButton right;
	private JTextArea movie, movie2, movie3;
	private static int index;
	
	public static SellTicketHandler handler;
	private Movie curPlay;
	private Schedule curSchedule;
	private DefaultMutableTreeNode curNode;
	private List<Movie> scheduledPlay;
	private JTree tree;
	public static JTextArea detail;
	private Ticket[][] ticketArray;
	private static String username;
	private static int userid;

	public SellTicketUICus() {
	}

	public SellTicketUICus(String name, int id) {
		super(name, id);
		//initContent();
		username = name;
		userid = id;
	}
	
	// To be override by the detailed business block interface
	@Override
	protected void initContent() {
		
		tabPane = new JTabbedPane();
		tabPane.setBounds(0, 60, 1024, 490);

		salePanel = new JPanel();
		salePanel.setLayout(new BorderLayout());
		
		salePanel2 = new JPanel();
		salePanel2.setLayout(new BorderLayout());
		
		salePanel3 = new JPanel();
		salePanel3.setLayout(new BorderLayout());

		handler = new SellTicketHandler();
		handler.makeNewSale();
		
		setUpPanel();
		setUpPanel2();
		setUpPanel3();
		if(scheduledPlay.size()>0){
			setLeftPanel(scheduledPlay.get(0).getMv_id(),scheduledPlay.get(0).getName());
			curPlay = scheduledPlay.get(0);
		}
		else
			setLeftPanel(0,"【无信息】");
		setRightPanel();

		tabPane.addTab("正在上映", salePanel);
		tabPane.addTab("即将上映", salePanel2);
		tabPane.addTab("全部电影", salePanel3);
		tabPane.setFont(new java.awt.Font("华文行楷", 1, 13));
		contPan.add(tabPane);
		contPan.validate();
		
		Rectangle rect = contPan.getBounds();
		
		this.setTitle("这就是命剧院票务管理系统——售票");

		ca1 = new JLabel("售票", JLabel.CENTER);
		ca1.setBounds(0, 45, rect.width, 30);
		ca1.setFont(new java.awt.Font("华文行楷", 1, 20));
		ca1.setForeground(Color.blue);
		contPan.add(ca1);
		
		//JTextArea movie = new JTextArea("        《刀剑神域 -序列之争-》是由伊藤智彦指导，并和《刀剑神域》轻小说原作者川原砾共同谱写剧本，A-1 Pictures负责制作的动画电影。其剧情为原创，讲述原作第7卷圣母圣咏篇与第9卷Alicization篇之间的故事。\n        2017年2月18日在日本上映，2月24日在中国台湾上映，3月30日在中国香港上映。\n        中国大陆代理商北京次元矩阵文化传播有限公司确认了电影在中国大陆的引进计划，上映时间待定。\n\n中文名 刀剑神域 -序列之争- 外文名 劇場版 ソードアート・オンライン -オーディナル・スケール- 其它译名 刀剑神域 -序列争战-（中国香港、台湾译名）\n出品时间 2017年2月18日 出品公司 SAO MOVIE project[2]  发行公司Aniplex 制片地区 日本 \n导演伊藤智彦 编    剧川原砾、伊藤智彦 类    型 动画，动作，竞技，冒险 主    演松冈祯丞，户松遥，竹达彩奈，伊藤加奈惠，日高里菜，高垣彩阳，泽城美雪，平田广明，安元洋贵，山寺宏一，神田沙也加，井上芳雄，鹿贺丈史 \n片长 119分钟 上映时间 2017年2月18日（日本） \n票房 6亿日元（日本，截止2017年3月21日） \n分级 PG-12 对白语言 日语色彩 彩色 imdb编码 tt5544384[3]  \n原  作 川原砾 人设、总作监 足立慎吾 音乐梶浦由记 动画制作A-1 Pictures \n中国台湾上映 2017年2月24日（木棉花代理） 中国香港上映 2017年3月30日（新映影片代理） 中国大陆上映 预计2017年（北京次元矩阵代理）\n\n剧情简介:\n\n        2022年，天才编程者茅场晶彦所开发的世界最早的完全潜行专用装备设备“NERvGear”。\n        这个革命性的机器给VR（假想现实）世界带来了无限的可能性。那之后经过了4年。\n        “NERvGear”的后继品VR机为了对抗“AmuSphere”（第二代民用完全潜行机），发售了一个次世代的可穿戴设备“Augma”。\n        替换了完全潜行机能，是一个对AR（增强现实）功能进行了最大限度扩大的最先进机种。\n        由于“Augma”在觉醒状态下也可以安全和便利地使用，因此一瞬间便在玩家当中传开了。\n        这个杀手级内容，被叫做“Ordinal Scale序列之争（OS）”，是“Augma”专用的ARMMO RPG。\n        亚丝娜和伙伴们会玩的这个游戏，桐人也准备参战了。");
		movie = new JTextArea();
		JScrollPane scrollm = new JScrollPane(movie);
		scrollm.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
		movie.setLineWrap(true);
		scrollm.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		salePanel.add(scrollm);
		
		movie2 = new JTextArea();
		JScrollPane scrollm2 = new JScrollPane(movie2);
		scrollm2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
		movie2.setLineWrap(true);
		scrollm2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		salePanel2.add(scrollm2);
		
		movie3 = new JTextArea();
		JScrollPane scrollm3 = new JScrollPane(movie3);
		scrollm3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
		movie3.setLineWrap(true);
		scrollm3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		salePanel3.add(scrollm3);

		jsc = new JScrollPane();
		jsc.setBounds(0, 80, rect.width, rect.height - 130);
		contPan.add(jsc);

		hint = new JLabel("请输入影片名称:", JLabel.RIGHT);
		hint.setBounds(60, rect.height - 45, 150, 30);
		hint.setFont(new java.awt.Font("华文行楷", 1, 15));
		contPan.add(hint);

		input = new CircleTextField(10);
		input.setBounds(220, rect.height - 45, 200, 30);
		contPan.add(input);

		// 查找 ，删除和编辑的按钮，其中含有相关的事件处理！
		btnQuery = new CircleButton("查找");
		btnQuery.setBounds(440, rect.height - 45, 60, 30);
		btnQuery.setFont(new java.awt.Font("华文行楷", 1, 12));
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnQueryClicked();
			}
		});
		contPan.add(btnQuery);

		btnCho = new CircleButton("选座");
		btnCho.setBounds(rect.width - 220, rect.height - 45, 60, 30);
		btnCho.setFont(new java.awt.Font("华文行楷", 1, 12));
		btnCho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnChoClicked();
			}
		});
		//contPan.add(btnCho);
		
		btnTic = new CircleButton("出票");
		btnTic.setBounds(rect.width - 150, rect.height - 45, 60, 30);
		btnTic.setFont(new java.awt.Font("华文行楷", 1, 12));
		btnTic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				if(handler.doSale()){
					detail.setText("");					
					getTickets(curNode);
					JOptionPane.showMessageDialog(null, "出票成功。");
				}else{
					JOptionPane.showMessageDialog(null, "出现错误，请重试。");					
				}
			}
		});
		contPan.add(btnTic);
		
		btnDel = new CircleButton("清除");
		btnDel.setBounds(rect.width - 80, rect.height - 45, 60, 30);
		btnDel.setFont(new java.awt.Font("华文行楷", 1, 12));
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
					handler.clearSale();
					detail.setText("");
					getTickets(curNode);
			}
		});
		contPan.add(btnDel);
		contPan.add(ca1);
	}

	private void btnChoClicked() {
		SeatChooseUICus addMovie = new SeatChooseUICus();
		addMovie.setWindowName("选购座位");
		addMovie.setFont(new java.awt.Font("华文行楷", 1, 15));
		addMovie.toFront();
		addMovie.setModal(true);
		addMovie.setVisible(true);

	}


	private void btnQueryClicked() {
		if (!input.getText().equals("")) {

		} else {
			JOptionPane.showMessageDialog(null, "请输入检索条件");
		}
	}

	private void setUpPanel() {
		MovieSrv service = new MovieSrv();
		scheduledPlay = service.selectScheduledMovie("");
		upPanel = new JPanel();
		upPanel.setLayout(new BorderLayout());
		//left = new JButton(new ImageIcon("resource/image/left1.png"));
		//upPanel.add(left, BorderLayout.WEST);
		//right = new JButton(new ImageIcon("resource/image/right1.png"));
		//upPanel.add(right, BorderLayout.EAST);
		JPanel filmPanel = new JPanel();
		filmPanel.setLayout(new GridLayout(1, 1));
		
		JPanel bigone = new JPanel();
		
		JScrollPane scrollm = new JScrollPane(bigone);
		scrollm.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		scrollm.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
//		upPanel.add(scrollm, BorderLayout.SOUTH);
		filmPanel.add(scrollm);
		
		
		Iterator<Movie> it2 = scheduledPlay.iterator();
		int n = scheduledPlay.size();
		int[] list = new int[n];
		int count = 0;
//		for(int i = 0;it2.hasNext();movi = it2.next(),i++){
//            if(movi.getStatus().equals("正在上映")) {
//            	list[i] = 1;
//            } else {
//            	list[i] = 0;
//            }
//            
//        }
		
		Movie movi = null;
		
		for (int i = 0; it2.hasNext(); i++) {
			movi = it2.next();
			if(movi.getStatus().equals("正在上映")) {
            	list[i] = 1;
            } else {
            	list[i] = 0;
            }
		}
		
		//int n = scheduledPlay.size();
		JButton[] btn = new JButton[count];
		
//		int[] list = new int[n];
		
		Action act = new AbstractAction() {
			private static final long serialVersionUID = -144569051730123316L;

			public void actionPerformed(ActionEvent e) {
				JButton site = (JButton) e.getSource();
				String name = site.getName();
				int i = Integer.valueOf(name);
	
				if(scheduledPlay.size()>i){
					if(scheduledPlay.get(i).getStatus().equals("正在上映")) {
						setLeftPanel(scheduledPlay.get(i).getMv_id(),scheduledPlay.get(i).getName());
						setTextPanel(scheduledPlay.get(i).getMv_id(), i);
						curPlay = scheduledPlay.get(i);
					}
				}

			}
		};
		
		
		for(int i=0; i<n; i++) {
			index = i;
//			if(scheduledPlay.get(i).getStatus().equals("正在上映")) {
			if(list[i] == 1) {
				JButton jb = new JButton(act);
				jb.setIcon(new ImageIcon(scheduledPlay.get(i).getImg()));
				jb.setName(i+"");
				jb.setPreferredSize(new Dimension(150,80));
				bigone.add(jb);
			}
		}
		
//		//JButton btn1 = new JButton(new ImageIcon("resource/image/film1.jpg"));
//		JButton btn1 = new JButton(new ImageIcon(scheduledPlay.get(0).getImg()));
//		btn1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(scheduledPlay.size()>0){
//					setLeftPanel(scheduledPlay.get(0).getMv_id(),scheduledPlay.get(0).getName());
//					setTextPanel(scheduledPlay.get(0).getMv_id(), 0);
//					curPlay = scheduledPlay.get(0);
//				}
//			}
//		});
//		filmPanel.add(btn1);
//		
//		//JButton btn2 = new JButton(new ImageIcon("resource/image/film2.jpg"));
//		JButton btn2 = new JButton(new ImageIcon(scheduledPlay.get(1).getImg()));
//		btn2.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(scheduledPlay.size()>1){
//					setLeftPanel(scheduledPlay.get(1).getMv_id(),scheduledPlay.get(1).getName());
//					setTextPanel(scheduledPlay.get(1).getMv_id(), 1);
//					curPlay = scheduledPlay.get(1);
//				}
//			}
//		});
//		filmPanel.add(btn2);
//
//		//JButton btn3 = new JButton(new ImageIcon("resource/image/film3.jpg"));
//		JButton btn3 = new JButton(new ImageIcon(scheduledPlay.get(2).getImg()));
//		btn3.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(scheduledPlay.size()>2){
//					setLeftPanel(scheduledPlay.get(2).getMv_id(),scheduledPlay.get(2).getName());
//					setTextPanel(scheduledPlay.get(2).getMv_id(), 2);
//					curPlay = scheduledPlay.get(2);
//				}
//			}
//		});
//		filmPanel.add(btn3);
//		
//		//JButton btn4 = new JButton(new ImageIcon("resource/image/film4.jpg"));
//		JButton btn4 = new JButton(new ImageIcon(scheduledPlay.get(3).getImg()));
//		btn4.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(scheduledPlay.size()>3){
//					setLeftPanel(scheduledPlay.get(3).getMv_id(),scheduledPlay.get(3).getName());
//					setTextPanel(scheduledPlay.get(3).getMv_id(), 3);
//					curPlay = scheduledPlay.get(3);
//				}
//			}
//		});
//		filmPanel.add(btn4);
//		
//		//JButton btn5 = new JButton(new ImageIcon("resource/image/film5.jpg"));
//		JButton btn5 = new JButton(new ImageIcon(scheduledPlay.get(4).getImg()));
//		btn5.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(scheduledPlay.size()>4){
//					setLeftPanel(scheduledPlay.get(4).getMv_id(),scheduledPlay.get(4).getName());
//					setTextPanel(scheduledPlay.get(4).getMv_id(), 4);
//					curPlay = scheduledPlay.get(4);
//				}
//			}
//		});
//		filmPanel.add(btn5);
//		
//		//JButton btn6 = new JButton(new ImageIcon("resource/image/film6.jpg"));
//		JButton btn6 = new JButton(new ImageIcon(scheduledPlay.get(5).getImg()));
//		btn6.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(scheduledPlay.size()>5){
//					setLeftPanel(scheduledPlay.get(5).getMv_id(),scheduledPlay.get(5).getName());
//					setTextPanel(scheduledPlay.get(5).getMv_id(), 5);
//					curPlay = scheduledPlay.get(5);
//				}
//			}
//		});
//		filmPanel.add(btn6);
		
		upPanel.add(filmPanel, BorderLayout.CENTER);
		salePanel.add(upPanel, BorderLayout.NORTH);
	}
	
	private void setUpPanel2() {
		MovieSrv service = new MovieSrv();
		scheduledPlay = service.selectScheduledMovie("");
		upPanel = new JPanel();
		upPanel.setLayout(new BorderLayout());
		//left = new JButton(new ImageIcon("resource/image/left1.png"));
		//upPanel.add(left, BorderLayout.WEST);
		//right = new JButton(new ImageIcon("resource/image/right1.png"));
		//upPanel.add(right, BorderLayout.EAST);
		JPanel filmPanel = new JPanel();
		filmPanel.setLayout(new GridLayout(1, 1));
		
		JPanel bigone = new JPanel();
		
		JScrollPane scrollm = new JScrollPane(bigone);
		scrollm.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		scrollm.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
//		upPanel.add(scrollm, BorderLayout.SOUTH);
		filmPanel.add(scrollm);
		
		Iterator<Movie> it2 = scheduledPlay.iterator();
		int n = scheduledPlay.size();
		int[] list = new int[n];
		int count = 0;
		
//		for(int i = 0;it2.hasNext();movi = it2.next(),i++){
//            if(movi.getStatus().equals("即将上映")) {
//            	list[i] = 1;
//            } else {
//            	list[i] = 0;
//            }
//            movi = it2.next();
//            System.out.println(i);
//        }
		
		
		Movie movi = null;
		
		for (int i = 0; it2.hasNext(); i++) {
			movi = it2.next();
			if(movi.getStatus().equals("即将上映")) {
            	list[i] = 1;
            } else {
            	list[i] = 0;
            }
		}

		
		//int n = scheduledPlay.size();
		JButton[] btn = new JButton[count];
		
//		int[] list = new int[n];
		
		Action act = new AbstractAction() {
			private static final long serialVersionUID = -144569051730123316L;

			public void actionPerformed(ActionEvent e) {
				JButton site = (JButton) e.getSource();
				String name = site.getName();
				int i = Integer.valueOf(name);
	
				if(scheduledPlay.size()>i){
					if(scheduledPlay.get(i).getStatus().equals("即将上映")) {
						setLeftPanel(scheduledPlay.get(i).getMv_id(),scheduledPlay.get(i).getName());
						setTextPanel2(scheduledPlay.get(i).getMv_id(), i);
						curPlay = scheduledPlay.get(i);
					}
				}

			}
		};
		
		for(int i=0; i<n; i++) {
			index = i;
			if(list[i] == 1) {
				JButton jb = new JButton(act);
				jb.setIcon(new ImageIcon(scheduledPlay.get(i).getImg()));
				jb.setName(i+"");
				jb.setPreferredSize(new Dimension(150,80));
				bigone.add(jb);
			}
		}
		
		
		
		upPanel.add(filmPanel, BorderLayout.CENTER);
		salePanel2.add(upPanel, BorderLayout.NORTH);
//		MovieSrv service = new MovieSrv();
//		scheduledPlay = service.selectScheduledMovie("");
//		upPanel = new JPanel();
//		upPanel.setLayout(new BorderLayout());
//		left = new JButton(new ImageIcon("resource/image/left1.png"));
//		upPanel.add(left, BorderLayout.WEST);
//		right = new JButton(new ImageIcon("resource/image/right1.png"));
//		upPanel.add(right, BorderLayout.EAST);
//		JPanel filmPanel = new JPanel();
//		filmPanel.setLayout(new GridLayout(1, 6));
//		
//		JButton btn1 = new JButton(new ImageIcon("resource/image/film7.jpg"));
//		btn1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(scheduledPlay.size()>0){
//					setLeftPanel(scheduledPlay.get(0).getMv_id(),scheduledPlay.get(0).getName());
//					setTextPanel(scheduledPlay.get(0).getMv_id(), 0);
//					curPlay = scheduledPlay.get(0);
//				}
//			}
//		});
//		filmPanel.add(btn1);
//		
//		JButton btn2 = new JButton(new ImageIcon("resource/image/film8.jpg"));
//		btn2.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(scheduledPlay.size()>1){
//					setLeftPanel(scheduledPlay.get(1).getMv_id(),scheduledPlay.get(1).getName());
//					setTextPanel(scheduledPlay.get(1).getMv_id(), 1);
//					curPlay = scheduledPlay.get(1);
//				}
//			}
//		});
//		filmPanel.add(btn2);
//
//		JButton btn3 = new JButton(new ImageIcon("resource/image/film9.jpg"));
//		btn3.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(scheduledPlay.size()>2){
//					setLeftPanel(scheduledPlay.get(2).getMv_id(),scheduledPlay.get(2).getName());
//					setTextPanel(scheduledPlay.get(2).getMv_id(), 2);
//					curPlay = scheduledPlay.get(2);
//				}
//			}
//		});
//		filmPanel.add(btn3);
//		
//		JButton btn4 = new JButton(new ImageIcon("resource/image/film10.jpg"));
//		btn4.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(scheduledPlay.size()>3){
//					setLeftPanel(scheduledPlay.get(3).getMv_id(),scheduledPlay.get(3).getName());
//					setTextPanel(scheduledPlay.get(3).getMv_id(), 3);
//					curPlay = scheduledPlay.get(3);
//				}
//			}
//		});
//		filmPanel.add(btn4);
//		
//		JButton btn5 = new JButton(new ImageIcon("resource/image/film11.jpg"));
//		btn5.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(scheduledPlay.size()>4){
//					setLeftPanel(scheduledPlay.get(4).getMv_id(),scheduledPlay.get(4).getName());
//					setTextPanel(scheduledPlay.get(4).getMv_id(), 4);
//					curPlay = scheduledPlay.get(4);
//				}
//			}
//		});
//		filmPanel.add(btn5);
//		
//		JButton btn6 = new JButton(new ImageIcon("resource/image/film12.jpg"));
//		btn6.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(scheduledPlay.size()>5){
//					setLeftPanel(scheduledPlay.get(5).getMv_id(),scheduledPlay.get(5).getName());
//					setTextPanel(scheduledPlay.get(5).getMv_id(), 5);
//					curPlay = scheduledPlay.get(5);
//				}
//			}
//		});
//		filmPanel.add(btn6);
//		
//		upPanel.add(filmPanel, BorderLayout.CENTER);
//		salePanel2.add(upPanel, BorderLayout.NORTH);
	}

	private void setUpPanel3() {
		MovieSrv service = new MovieSrv();
		scheduledPlay = service.selectScheduledMovie("");
		upPanel = new JPanel();
		upPanel.setLayout(new BorderLayout());
		//left = new JButton(new ImageIcon("resource/image/left1.png"));
		//upPanel.add(left, BorderLayout.WEST);
		//right = new JButton(new ImageIcon("resource/image/right1.png"));
		//upPanel.add(right, BorderLayout.EAST);
		JPanel filmPanel = new JPanel();
		filmPanel.setLayout(new GridLayout(1, 1));
		
		JPanel bigone = new JPanel();
		
		JScrollPane scrollm = new JScrollPane(bigone);
		scrollm.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		scrollm.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
//		upPanel.add(scrollm, BorderLayout.SOUTH);
		filmPanel.add(scrollm);
		
		int n = scheduledPlay.size();
		JButton[] btn = new JButton[n];
		
		Action act = new AbstractAction() {
			private static final long serialVersionUID = -144569051730123316L;

			public void actionPerformed(ActionEvent e) {
				JButton site = (JButton) e.getSource();
				String name = site.getName();
				int i = Integer.valueOf(name);
	
				if(scheduledPlay.size()>i){
					setLeftPanel(scheduledPlay.get(i).getMv_id(),scheduledPlay.get(i).getName());
					setTextPanel3(scheduledPlay.get(i).getMv_id(), i);
					curPlay = scheduledPlay.get(i);
				}

			}
		};
		
		
		for(int i=0; i<n; i++) {
			index = i;
			JButton jb = new JButton(act);
			jb.setIcon(new ImageIcon(scheduledPlay.get(i).getImg()));
			jb.setName(i+"");
			jb.setPreferredSize(new Dimension(150,80));
			bigone.add(jb);
		}
		
		upPanel.add(filmPanel, BorderLayout.CENTER);
		salePanel3.add(upPanel, BorderLayout.NORTH);
		
//		MovieSrv service = new MovieSrv();
//		scheduledPlay = service.selectScheduledMovie("");
//		upPanel = new JPanel();
//		upPanel.setLayout(new BorderLayout());
//		left = new JButton(new ImageIcon("resource/image/left1.png"));
//		upPanel.add(left, BorderLayout.WEST);
//		right = new JButton(new ImageIcon("resource/image/right1.png"));
//		upPanel.add(right, BorderLayout.EAST);
//		JPanel filmPanel = new JPanel();
//		filmPanel.setLayout(new GridLayout(1, 6));
//		
//		JButton btn1 = new JButton(new ImageIcon("resource/image/film7.jpg"));
//		btn1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(scheduledPlay.size()>0){
//					setLeftPanel(scheduledPlay.get(0).getMv_id(),scheduledPlay.get(0).getName());
//					setTextPanel(scheduledPlay.get(0).getMv_id(), 0);
//					curPlay = scheduledPlay.get(0);
//				}
//			}
//		});
//		filmPanel.add(btn1);
//		
//		JButton btn2 = new JButton(new ImageIcon("resource/image/film8.jpg"));
//		btn2.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(scheduledPlay.size()>1){
//					setLeftPanel(scheduledPlay.get(1).getMv_id(),scheduledPlay.get(1).getName());
//					setTextPanel(scheduledPlay.get(1).getMv_id(), 1);
//					curPlay = scheduledPlay.get(1);
//				}
//			}
//		});
//		filmPanel.add(btn2);
//
//		JButton btn3 = new JButton(new ImageIcon("resource/image/film9.jpg"));
//		btn3.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(scheduledPlay.size()>2){
//					setLeftPanel(scheduledPlay.get(2).getMv_id(),scheduledPlay.get(2).getName());
//					setTextPanel(scheduledPlay.get(2).getMv_id(), 2);
//					curPlay = scheduledPlay.get(2);
//				}
//			}
//		});
//		filmPanel.add(btn3);
//		
//		JButton btn4 = new JButton(new ImageIcon("resource/image/film10.jpg"));
//		btn4.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(scheduledPlay.size()>3){
//					setLeftPanel(scheduledPlay.get(3).getMv_id(),scheduledPlay.get(3).getName());
//					setTextPanel(scheduledPlay.get(3).getMv_id(), 3);
//					curPlay = scheduledPlay.get(3);
//				}
//			}
//		});
//		filmPanel.add(btn4);
//		
//		JButton btn5 = new JButton(new ImageIcon("resource/image/film11.jpg"));
//		btn5.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(scheduledPlay.size()>4){
//					setLeftPanel(scheduledPlay.get(4).getMv_id(),scheduledPlay.get(4).getName());
//					setTextPanel(scheduledPlay.get(4).getMv_id(), 4);
//					curPlay = scheduledPlay.get(4);
//				}
//			}
//		});
//		filmPanel.add(btn5);
//		
//		JButton btn6 = new JButton(new ImageIcon("resource/image/film12.jpg"));
//		btn6.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(scheduledPlay.size()>5){
//					setLeftPanel(scheduledPlay.get(5).getMv_id(),scheduledPlay.get(5).getName());
//					setTextPanel(scheduledPlay.get(5).getMv_id(), 5);
//					curPlay = scheduledPlay.get(5);
//				}
//			}
//		});
//		filmPanel.add(btn6);
//	
//		upPanel.add(filmPanel, BorderLayout.CENTER);
//		salePanel3.add(upPanel, BorderLayout.NORTH);
	}
	
	private void setTextPanel(int mv_id, int n) {
		
		movie.setText("影片名称：" + scheduledPlay.get(n).getName());
		movie.append("\n影片类型：" + scheduledPlay.get(n).getType());
		movie.append("\n导演：" + scheduledPlay.get(n).getDirector());
		movie.append("\n主演：" + scheduledPlay.get(n).getActor());
		movie.append("\n上映时间：" + scheduledPlay.get(n).getDate());
		movie.append("\n时长：" + scheduledPlay.get(n).getTime());
		movie.append("\n评分：" + scheduledPlay.get(n).getScore());
		movie.append("\n影片简介：" + scheduledPlay.get(n).getIntroduction());
	}
	
	private void setTextPanel2(int mv_id, int n) {

		movie2.setText("影片名称：" + scheduledPlay.get(n).getName());
		movie2.append("\n影片类型：" + scheduledPlay.get(n).getType());
		movie2.append("\n导演：" + scheduledPlay.get(n).getDirector());
		movie2.append("\n主演：" + scheduledPlay.get(n).getActor());
		movie2.append("\n上映时间：" + scheduledPlay.get(n).getDate());
		movie2.append("\n时长：" + scheduledPlay.get(n).getTime());
		movie2.append("\n评分：" + scheduledPlay.get(n).getScore());
		movie2.append("\n影片简介：" + scheduledPlay.get(n).getIntroduction());
	}
	
	private void setTextPanel3(int mv_id, int n) {
		movie3.setText("影片名称：" + scheduledPlay.get(n).getName());
		movie3.append("\n影片类型：" + scheduledPlay.get(n).getType());
		movie3.append("\n导演：" + scheduledPlay.get(n).getDirector());
		movie3.append("\n主演：" + scheduledPlay.get(n).getActor());
		movie3.append("\n上映时间：" + scheduledPlay.get(n).getDate());
		movie3.append("\n时长：" + scheduledPlay.get(n).getTime());
		movie3.append("\n评分：" + scheduledPlay.get(n).getScore());
		movie3.append("\n影片简介：" + scheduledPlay.get(n).getIntroduction());
	}
	
	private void setLeftPanel(int mv_id, String mv_name) {
		if(leftPanel==null)
			leftPanel = new JPanel();
		else
			leftPanel.removeAll();
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(mv_name);
		ScheduleSrv service = new ScheduleSrv();
		List<Schedule> list = service.Fetch("mv_id="+mv_id);
		if (list.size() > 0) {
			List<String> dates = new ArrayList<String>();
			for (Schedule i : list) {
				String s = DateFormat.getDateInstance().format(i.getSched_time());
				if(!dates.contains(s)){
					dates.add(s);
				}
			}
			for(String i:dates){
				root.add(new DefaultMutableTreeNode(i));									
			}
			for (Schedule i : list) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)root.getFirstChild();
				for(int j=0; j<dates.size(); j++){
					if(node.getUserObject().toString().equals(DateFormat.getDateInstance().format(i.getSched_time()))){
						node.add(new DefaultMutableTreeNode(i));
						break;
					}else
						node = node.getNextSibling();
				}
			}
		}
		tree = new JTree(root);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
		    public void valueChanged(TreeSelectionEvent e) {		    	
		        DefaultMutableTreeNode selectedNode=(DefaultMutableTreeNode) tree.getLastSelectedPathComponent();  
		        curNode = selectedNode;
		    
		        getTickets(selectedNode);
		    }  
		});  
		leftPanel.add(tree);
		salePanel.add(leftPanel, BorderLayout.WEST);
		leftPanel.updateUI();
	}
	
	private void setRightPanel() {
		rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		detail = new JTextArea("");
		JScrollPane scroll = new JScrollPane(detail);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		rightPanel.add(scroll, BorderLayout.CENTER);
		
		JPanel buttons = new JPanel();
		JLabel pic = new JLabel("This Is Your Tickets ↑");
		pic.setFont(new java.awt.Font("华文行楷", 1, 15));
		buttons.add(pic);
		rightPanel.add(buttons, BorderLayout.SOUTH);
		salePanel.add(rightPanel, BorderLayout.EAST);
	}
	
	private void getTickets(DefaultMutableTreeNode node){
		
        if(node!=null && node.isLeaf()){
        	
        	int m=0, n=0;
        	
        	Schedule schedule = (Schedule)node.getUserObject();
        	curSchedule = schedule;
        	//System.out.println(schedule.getSched_id());
        	TicketSrv ticketSrv = new TicketSrv();
        	SeatSrv seatSrv = new SeatSrv();
        	StudioSrv studioSrv = new StudioSrv();
        	List<Ticket> tickets = ticketSrv.Fetch("sched_id = "+ schedule.getSched_id());
        	
        	
        	for(Ticket t : tickets){
        		List<Seat> tmp = seatSrv.Fetch("seat_id = " + t.getSeatId());
        		if(tmp.size()>0){       			
        			t.setPlayName(curPlay.getName());
        			t.setSchedule(curSchedule);
        			t.setSeat(tmp.get(0));
        			if(m==0){
        				List<Studio> studios = studioSrv.Fetch("studio_id = " + tmp.get(0).getStudioId());
        				if(studios.size()>0){
        					m = studios.get(0).getRowCount();
        					n = studios.get(0).getColCount();
        				}
        			}
        			if(handler.isTicketSelected(t)){
        				t.setStatus(2);
        			}
        		}
        	}
        	
    		SeatChooseUICus Seat = new SeatChooseUICus();
    		Seat.setWindowName("选购座位");
    		Seat.setFont(new java.awt.Font("华文行楷", 1, 15));
    		Seat.toFront();
    		Seat.setMiddlePanel(m, n, tickets); 
    		Seat.setModal(true);
    		Seat.setVisible(true);
        }
	}
	
	public static void main(String[] args) {
		SellTicketUICus frmStuMgr = new SellTicketUICus(username,userid);
		frmStuMgr.setVisible(true);
	}
}
