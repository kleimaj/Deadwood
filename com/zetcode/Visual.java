package com.zetcode;

import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class Visual extends JFrame{
	
	JLabel boardLabel;
	
	JLayeredPane bPane;
	
	public Visual() {
		setSize(1500,900);
		setTitle("Deadwood");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		bPane = getLayeredPane();
		boardLabel = new JLabel();
		ImageIcon icon = new ImageIcon("board.jpg");
		boardLabel.setIcon(icon);
		boardLabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
		bPane.add(boardLabel, new Integer(0));
	}
	public Visual(int number) {
		setSize(300,300);
		setTitle("Main Menu");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(new Insets(40,60,40,60)));
		String[] buttons = { "1","2","3", "4","5","6","7","8"};
		
		JTextPane textPane = new JTextPane();
		textPane.setContentType("text");
		textPane.setEditable(false);
		textPane.setText("Select Number of Players");
		panel.add(textPane);
		
		for (int i = 0; i < buttons.length; i++) {
			JButton button = new JButton(buttons[i]);
			button.addMouseListener((MouseListener) new Controller()); //problem here
			panel.add(button);
			panel.add(Box.createRigidArea(new Dimension(0,5)));
		}
		add(panel);
		
	}
}
