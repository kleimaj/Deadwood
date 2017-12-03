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
	
	// ATTRIBUTES 
	
	JLayeredPane bPane;
	
	JLabel boardLabel;
	JLabel background;
	JLabel dieLabel;
	JLabel player1;
	JLabel player2;
	JLabel player3;
	JLabel player4;
	JLabel player5;
	JLabel player6;
	JLabel player7;
	JLabel player8;
	JLabel stats_player;
	JLabel stats_location;
	JLabel stats_role;
	JLabel stats_dollars;
	JLabel stats_credits;
	JLabel stats_icon;
	
	JButton bMove;
	JButton bTakeRole;
	JButton bAct;
	JButton bRehearse;
	JButton bUpgrade;
	JButton[] extraButtons;
	
	ImageIcon die1;
	ImageIcon die2;
	ImageIcon die3;
	ImageIcon die4;
	ImageIcon die5;
	ImageIcon die6;
	ImageIcon die_disabled;
	ImageIcon player_icon;
	
	JTextArea log;
	
	// CONSTRUCTORS
	
	public Visual() {

		setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
		setTitle("Deadwood");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		bPane = getLayeredPane();
		
		background = new JLabel();
		background.setIcon(new ImageIcon("images/bkg.jpg"));
		background.setBounds(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
		bPane.add(background, new Integer(0));
		
		boardLabel = new JLabel();
		ImageIcon boardIcon = new ImageIcon("images/board.jpg");
		boardLabel.setIcon(boardIcon);
		boardLabel.setBounds(10,10,boardIcon.getIconWidth(),boardIcon.getIconHeight());
		bPane.add(boardLabel, new Integer(1));
		
		stats_player = new JLabel("Current Player: ", JLabel.LEFT);
		stats_player.setFont(new Font("Arial", Font.BOLD, 14));
		stats_player.setBounds(1250, 640, 400, 20);
		bPane.add(stats_player, new Integer(2));
		
		stats_location = new JLabel("Location: ", JLabel.LEFT);
		stats_location.setFont(new Font("Arial", Font.BOLD, 14));
		stats_location.setBounds(1250, 660, 400, 20);
		bPane.add(stats_location, new Integer(2));
		
		stats_role = new JLabel("Role: ", JLabel.LEFT);
		stats_role.setFont(new Font("Arial", Font.BOLD, 14));
		stats_role.setBounds(1250, 680, 400, 20);
		bPane.add(stats_role, new Integer(2));
		
		stats_dollars = new JLabel("Dollars: ", JLabel.LEFT);
		stats_dollars.setFont(new Font("Arial", Font.BOLD, 14));
		stats_dollars.setBounds(1250, 700, 400, 20);
		bPane.add(stats_dollars, new Integer(2));
		
		stats_credits = new JLabel("Credits: ", JLabel.LEFT);
		stats_credits.setFont(new Font("Arial", Font.BOLD, 14));
		stats_credits.setBounds(1250, 720, 400, 20);
		bPane.add(stats_credits, new Integer(2));
		
		//player_icon = new ImageIcon("images/dice/g1.png");
		/*stats_icon = new JLabel();
		stats_icon.setBounds(1510, 640, 40,40);
		stats_icon.setIcon(player_icon);
		bPane.add(stats_icon, new Integer(2));
		*/
		ImageIcon wood = new ImageIcon("images/button.jpg");
		ImageIcon wood_dis = new ImageIcon("images/disabled_button.jpg");
		bMove = new JButton("Move", wood);
		bMove.setHorizontalTextPosition(JButton.CENTER);
		bMove.setVerticalTextPosition(JButton.CENTER);
		bMove.setFont(new Font("Arial", Font.BOLD, 14));
		bMove.setForeground(Color.white);
		bMove.setBounds(1250, 50, 129, 36);
		bMove.setDisabledIcon(wood_dis);
		bMove.addMouseListener(new BoardMouseListener());
		bPane.add(bMove, new Integer(2));
		
		bTakeRole = new JButton("Take Role", wood);
		bTakeRole.setHorizontalTextPosition(JButton.CENTER);
		bTakeRole.setVerticalTextPosition(JButton.CENTER);
		bTakeRole.setFont(new Font("Arial", Font.BOLD, 14));
		bTakeRole.setForeground(Color.white);
		bTakeRole.setBounds(1250, 90, 129, 36);
		bTakeRole.setDisabledIcon(wood_dis);
		bTakeRole.addMouseListener(new BoardMouseListener());
		bPane.add(bTakeRole, new Integer(2));	
		bTakeRole.setEnabled(false);
		
		bAct = new JButton("Act", wood);
		bAct.setHorizontalTextPosition(JButton.CENTER);
		bAct.setVerticalTextPosition(JButton.CENTER);
		bAct.setFont(new Font("Arial", Font.BOLD, 14));
		bAct.setForeground(Color.white);
		bAct.setBounds(1250, 130, 129, 36);
		bAct.setDisabledIcon(wood_dis);
		bAct.addMouseListener(new BoardMouseListener());
		bPane.add(bAct, new Integer(2));
		bAct.setEnabled(false);
		
		bRehearse = new JButton("Rehearse", wood);
		bRehearse.setHorizontalTextPosition(JButton.CENTER);
		bRehearse.setVerticalTextPosition(JButton.CENTER);
		bRehearse.setFont(new Font("Arial", Font.BOLD, 14));
		bRehearse.setForeground(Color.white);
		bRehearse.setBounds(1250, 170, 129, 36);
		bRehearse.setDisabledIcon(wood_dis);
		bRehearse.addMouseListener(new BoardMouseListener());
		bPane.add(bRehearse, new Integer(2));
		bRehearse.setEnabled(false);
		
		bUpgrade = new JButton("Upgrade", wood);
		bUpgrade.setHorizontalTextPosition(JButton.CENTER);
		bUpgrade.setVerticalTextPosition(JButton.CENTER);
		bUpgrade.setFont(new Font("Arial", Font.BOLD, 14));
		bUpgrade.setForeground(Color.white);
		bUpgrade.setBounds(1250, 210, 129, 36);
		bUpgrade.setDisabledIcon(wood_dis);
		bUpgrade.addMouseListener(new BoardMouseListener());
		bPane.add(bUpgrade, new Integer(2));
		bUpgrade.setEnabled(false);
		
		// Extra Buttons (for move and take role)
		extraButtons = new JButton[7];
		int yCord = 50;
		for (int i = 1; i <= 7; i++) {
			JButton button = new JButton("", wood);
			button.setHorizontalTextPosition(JButton.CENTER);
			button.setVerticalTextPosition(JButton.CENTER);
			button.setFont(new Font("Arial", Font.ITALIC, 12));
			button.setForeground(Color.white);
			button.setBounds(1400, yCord, 129, 36);
			button.addMouseListener(new BoardMouseListener());
			button.setDisabledIcon(wood_dis);
			bPane.add(button, new Integer(2));
			button.setVisible(false);
			button.setEnabled(false);
			extraButtons[i-1] = button;
			yCord += 40;
		}
		
		die1 = new ImageIcon("images/Dice/DieOne.png");
		die2 = new ImageIcon("images/Dice/DieTwo.png");
		die3 = new ImageIcon("images/Dice/DieThree.png");
		die4 = new ImageIcon("images/Dice/DieFour.png");
		die5 = new ImageIcon("images/Dice/DieFive.png");
		die6 = new ImageIcon("images/Dice/DieSix.png");
		die_disabled = new ImageIcon("images/Dice/disabled.png");
		dieLabel = new JLabel();
		dieLabel.setIcon(die1);
		dieLabel.setBounds(1250, 420, 85, 85);
		bPane.add(dieLabel, new Integer(2));
		
		
		log = new JTextArea("Welcome to Deadwood!");
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
		textLabel.setFont(new Font("Arial", Font.ITALIC, 14));
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
	
	
	// showScene
	// Preconditions:
	//		- A scene is drawn
	// Postconditions:
	//		- Scene card is displayed on the given dims
	public void showScene(String filename, int[] dims) {
		
		ImageIcon sceneIcon = new ImageIcon("images/cards/"+filename);
		JLabel sceneLabel = new JLabel();
		sceneLabel.setIcon(sceneIcon);
		sceneLabel.setBounds(dims[0]+10, dims[1]+10, sceneIcon.getIconWidth(), sceneIcon.getIconHeight());
		bPane.add(sceneLabel,new Integer(2));
	}
	
	
	public void discardScene() {
		
	}
	
	
	public void placeShotToken() {
		
	}
	
	
	// updateStats
	// Preconditions:
	//		- A player's attribute(s) have changed
	// Postconditions:
	//		- Updates all player's information on the stats board
	//		- Moves player token to correct spot
	public void updateStats(Player player) {
		player_icon = new ImageIcon("images/dice/"+player.getFileName()); //shows player's icon
		stats_icon = new JLabel();
		stats_icon.setBounds(1510, 640, 40,40);
		stats_icon.setIcon(player_icon);
		bPane.add(stats_icon, new Integer(2));
		
		stats_player.setText("Current Player: " + player.getName());
		stats_location.setText("Location: " + player.getLocation().getName());
		if(player.isInRole()) {
			stats_role.setText("Role: " + player.getRole().getName());
		}
		else {
			stats_role.setText("Role: ");
		}
		stats_dollars.setText("Dollars: " + player.getCurrency());
		stats_credits.setText("Credits: " + player.getFame());
		
		// move player token on or off a role
		
	}
	
	
	// showExtras
	// Preconditions:
	//		- Player has clicked on either take role or move
	// Postconditions:
	//		- Reveals the correct amount of buttons
	//		- Properly labels the buttons
	public void showExtras(String[] labels) {
		
		int numButtons = labels.length;
		for (int i = 0; i < numButtons; i++) {
			extraButtons[i].setText(labels[i]);
			extraButtons[i].setVisible(true);
			extraButtons[i].setEnabled(true);
		}
	}
	
}


// LISTENERS

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

class BoardMouseListener implements MouseListener {
	
	static String command;
	
	public void mouseClicked(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		if(button.isEnabled()) {
    	command = button.getText();
		}
	}
	
    public void mouseExited(MouseEvent e) {
    	
    }
    public void mouseEntered(MouseEvent e) {
    	
    }
    public void mouseReleased(MouseEvent e) {
    	
    }
    public void mousePressed(MouseEvent e) {
    	
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

