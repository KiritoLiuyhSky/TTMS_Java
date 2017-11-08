package xupt.se.ttms.view.tmpl;

import java.awt.BorderLayout;

import java.awt.Button;

import java.awt.CardLayout;

import java.awt.FlowLayout;

import java.awt.Frame;

import java.awt.GridLayout;

import java.awt.Panel;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.WindowAdapter;

import java.awt.event.WindowEvent;

public class testGUI extends Frame {
	private static final long serialVersionUID = 1L;

	Panel borderLayoutPanel;

	Panel cardLayoutPanel;

	Panel flowLayoutPanel;

	Panel gridLayoutPanel;

	private void generateGridLayoutPanel() {

		gridLayoutPanel = new Panel();

		gridLayoutPanel.setLayout(new GridLayout(2, 2));

		Button button1 = new Button("button1");

		Button button2 = new Button("button2");

		Button button3 = new Button("button3");

		Button button4 = new Button("button4");

		gridLayoutPanel.add(button1);

		gridLayoutPanel.add(button2);

		gridLayoutPanel.add(button3);

		gridLayoutPanel.add(button4);

	}

	private void generateFlowLayoutPanel() {

		flowLayoutPanel = new Panel();

		flowLayoutPanel.setLayout(new FlowLayout());

		Button button1 = new Button("button1");

		Button button2 = new Button("button2");

		Button button3 = new Button("button3");

		Button button4 = new Button("button4");

		Button button5 = new Button("button5");

		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				((Button) e.getSource()).setLabel("welcome ");

			}

		});

		flowLayoutPanel.add(button1);

		flowLayoutPanel.add(button2);

		flowLayoutPanel.add(button3);

		flowLayoutPanel.add(button4);

		flowLayoutPanel.add(button5);

	}

	private void generateBorderLayoutPanel() {

		borderLayoutPanel = new Panel();

		borderLayoutPanel.setLayout(new BorderLayout());

		Button button1 = new Button("South");

		Button button2 = new Button("West");

		Button button3 = new Button("East");

		Button button4 = new Button("North");

		Button button5 = new Button("Center");

		borderLayoutPanel.add(button1, BorderLayout.SOUTH);

		borderLayoutPanel.add(button2, BorderLayout.WEST);

		borderLayoutPanel.add(button3, BorderLayout.EAST);

		borderLayoutPanel.add(button4, BorderLayout.NORTH);

		borderLayoutPanel.add(button5, BorderLayout.CENTER);

	}

	private void genrateCardLayoutPanel() {

		cardLayoutPanel = new Panel();

		final CardLayout cl = new CardLayout();

		cardLayoutPanel.setLayout(cl);

		Button button1 = new Button("black");

		Button button2 = new Button("red");

		ActionListener al = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				cl.next(cardLayoutPanel);

			}

		};

		button1.addActionListener(al);

		button2.addActionListener(al);

		cardLayoutPanel.add(button1, "1");

		cardLayoutPanel.add(button2, "2");

	}

	public testGUI(String panelName) {

		super("panelName");

		generateBorderLayoutPanel();

		generateFlowLayoutPanel();

		generateGridLayoutPanel();

		genrateCardLayoutPanel();

		setLayout(new GridLayout(2, 2));

		add(borderLayoutPanel);

		add(flowLayoutPanel);

		add(gridLayoutPanel);

		add(cardLayoutPanel);

		setSize(800, 800);

		setLocation(100, 100);

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {

				System.exit(0);

			}

		});

	}

	public static void main(String[] args) {

		testGUI yourFrame = new testGUI("welcome");

		yourFrame.setVisible(true);

	}

}
