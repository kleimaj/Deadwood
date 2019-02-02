package com.zetcode;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.ImageIcon;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import java.awt.*;import javax.swing.*;import javax.swing.ImageIcon;import javax.imageio.ImageIO;import java.awt.event.*;

public class guiTest extends JFrame {
	
	public guiTest() {
		super("Deadwood");
		initUI();
	}
	
	private void initUI() {
		 ImageIcon cowboyHat = new ImageIcon("cowboy.jpeg"); //creates icon		 
		 setIconImage(cowboyHat.getImage()); //sets image to be displayed in window
		 
		 JPanel panel = new JPanel(); //creates panel
		 
		 JButton quitButton = new JButton("Quit"); //creates new button with String label as parameter
		 
		 
		 quitButton.setToolTipText("Quits Program"); //creates tooltip
		 
		 quitButton.addActionListener((ActionEvent event) -> { //plug action listener to button, action terminates application
	            System.exit(0);
	        });
		 panel.add(quitButton);
		 panel.add(new JButton("Button"));
		 add(panel);
		 pack();
	        createLayout(quitButton); //calls create layout to place child components into containers
		 
		//setTitle("Simple GUI example"); //title of window
		setSize(300,200); //size of window in pixels
		setLocationRelativeTo(null); //will center window
		setDefaultCloseOperation(EXIT_ON_CLOSE); //closes window if click on Close button
	}
	
	private void createLayout(JComponent... arg) {
		
		Container pane = getContentPane();		//content pane of a JFrame: area where child components are placed
		GroupLayout gl = new GroupLayout(pane);	//layout manager organizes children
		pane.setLayout(gl);
		
		gl.setAutoCreateContainerGaps(true); //creates gaps between componenets and edges of container
		
		gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(arg[0]));
		
		gl.setVerticalGroup(gl.createSequentialGroup().addComponent(arg[0])); //grouplayout manager defines layout of each dimension independently
	}

	public static void main(String[] args) { //creates an instance of the code above, makes it visible.
		EventQueue.invokeLater(() -> { //places event on Swing Event Queue
			guiTest ex = new guiTest();
			ex.setVisible(true);
		});

	}

}
