/*
 * Visual.java
 *
 * Contributors: Jacob Kleiman, Eric Eagan, Ryan McGinnis
 * November 2017
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class Visual extends JFrame{
	
	private static final int SCREEN_WIDTH = 1600;
	private static final int SCREEN_HEIGHT = 920;
	
	JLabel boardLabel;
	JLabel background;
	
	JLayeredPane bPane;
	
	JButton bMove;
	JButton bTakeRole;
	JButton bAct;
	JButton bRehearse;
	JButton bUpgrade;
	
	JTextArea log;
	
	public Visual() {

		setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
		setTitle("Deadwood");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		bPane = getLayeredPane();
		
		background = new JLabel();
		background.setIcon(new ImageIcon("bkg.jpg"));
		background.setBounds(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
		bPane.add(background, new Integer(0));
		
		boardLabel = new JLabel();
		ImageIcon boardIcon = new ImageIcon("board.jpg");
		boardLabel.setIcon(boardIcon);
		boardLabel.setBounds(10,10,boardIcon.getIconWidth(),boardIcon.getIconHeight());
		bPane.add(boardLabel, new Integer(1));
		
		ImageIcon wood = new ImageIcon("images/button.jpg");
		ImageIcon wood_dis = new ImageIcon("images/disabled_button.jpg");
		bMove = new JButton("Move", wood);
		bMove.setHorizontalTextPosition(JButton.CENTER);
		bMove.setVerticalTextPosition(JButton.CENTER);
		bMove.setFont(new Font("Arial", Font.BOLD, 14));
		bMove.setForeground(Color.white);
		bMove.setBounds(1250, 50, 129, 36);
		bMove.setDisabledIcon(wood_dis);
		bPane.add(bMove, new Integer(2));
		
		bTakeRole = new JButton("Take Role", wood);
		bTakeRole.setHorizontalTextPosition(JButton.CENTER);
		bTakeRole.setVerticalTextPosition(JButton.CENTER);
		bTakeRole.setFont(new Font("Arial", Font.BOLD, 14));
		bTakeRole.setForeground(Color.white);
		bTakeRole.setBounds(1250, 90, 129, 36);
		bTakeRole.setDisabledIcon(wood_dis);
		bPane.add(bTakeRole, new Integer(2));	
		bTakeRole.setEnabled(false);
		
		bAct = new JButton("Act", wood);
		bAct.setHorizontalTextPosition(JButton.CENTER);
		bAct.setVerticalTextPosition(JButton.CENTER);
		bAct.setFont(new Font("Arial", Font.BOLD, 14));
		bAct.setForeground(Color.white);
		bAct.setBounds(1250, 130, 129, 36);
		bAct.setDisabledIcon(wood_dis);
		bPane.add(bAct, new Integer(2));
		bAct.setEnabled(false);
		
		bRehearse = new JButton("Rehearse", wood);
		bRehearse.setHorizontalTextPosition(JButton.CENTER);
		bRehearse.setVerticalTextPosition(JButton.CENTER);
		bRehearse.setFont(new Font("Arial", Font.BOLD, 14));
		bRehearse.setForeground(Color.white);
		bRehearse.setBounds(1250, 170, 129, 36);
		bRehearse.setDisabledIcon(wood_dis);
		bPane.add(bRehearse, new Integer(2));
		bRehearse.setEnabled(false);
		
		bUpgrade = new JButton("Upgrade", wood);
		bUpgrade.setHorizontalTextPosition(JButton.CENTER);
		bUpgrade.setVerticalTextPosition(JButton.CENTER);
		bUpgrade.setFont(new Font("Arial", Font.BOLD, 14));
		bUpgrade.setForeground(Color.white);
		bUpgrade.setBounds(1250, 210, 129, 36);
		bUpgrade.setDisabledIcon(wood_dis);
		bPane.add(bUpgrade, new Integer(2));
		bUpgrade.setEnabled(false);
		
		log = new JTextArea("Good morning! Have a great day of acting!");
		log.setBounds(1250,750,300, 150);
		log.setFont(new Font("Arial", Font.PLAIN, 13));
		log.setEditable(false);
		log.setLineWrap(true);
		log.setBorder(new EmptyBorder(new Insets(3,3,3,3)));
		bPane.add(log, new Integer(2));
	}
	
	public Visual(int number) {
		
		setSize(300,300);
		setTitle("Main Menu");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(SCREEN_WIDTH/2 - getWidth()/2, SCREEN_HEIGHT/2 - getHeight()/2);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(new Insets(40,60,40,60)));
		panel.setBackground(Color.getHSBColor(255, 191, 122));
		
		JLabel textLabel = new JLabel("<html> Welcome to Deadwood.<br>Select number of players!</html>", JLabel.CENTER);
		textLabel.setFont(new Font("Courier New", Font.ITALIC, 14));
		panel.add(textLabel);
		
		String[] buttons = {"1","2","3","4","5","6","7","8"};
		for(int i = 1; i < buttons.length; i++) {
			JButton button = new JButton(buttons[i]);
			button.addMouseListener(new MenuMouseListener()); 
			panel.add(button);
			panel.add(Box.createRigidArea(new Dimension(0,5)));
		}
		add(panel);
	}
	
	public void showScene(String filename, int[] dims) {
		ImageIcon sceneIcon = new ImageIcon("images/cards/"+filename);
		JLabel sceneLabel = new JLabel();
		sceneLabel.setIcon(sceneIcon);
		sceneLabel.setBounds(dims[0]+10, dims[1]+10, sceneIcon.getIconWidth(), sceneIcon.getIconHeight());
		bPane.add(sceneLabel,new Integer(2));
		
		
	}
}

class MenuMouseListener implements MouseListener {
	
	static String command;
	
    public void mouseClicked(MouseEvent e) {
    	JButton button = (JButton) e.getSource();
    	command = button.getText();
    }
    
    public void mouseExited(MouseEvent e) {
    	
    }
    public void mouseEntered(MouseEvent e) {
    	
    }
    public void mouseReleased(MouseEvent e) {
    	
    }
    public void mousePressed(MouseEvent e) {
    	
    }
    
   
    
    public int getNum() {
    	return 0;
    }
    
    public String getCommand() {
    	command = null;
    	while(command == null) {
    		try {
    			Thread.sleep(250);
    		}catch(InterruptedException e) {}
    	}
    	return command;
    }

}

/*class BoardMouseListener implements MouseListener {
	
	public void mouseClicked(MouseEvent e) {
	
	}
	
}*/

