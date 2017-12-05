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
import java.util.ArrayList;

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
	JLabel stats_rehpts;
	JLabel stats_dollars;
	JLabel stats_credits;
	JLabel stats_icon;
	JLabel[] player_tokens;
	
	JButton bMove;
	JButton bTakeRole;
	JButton bAct;
	JButton bRehearse;
	JButton bUpgrade;
	JButton[] extraButtons;
	JButton[] buttons;
	
	ImageIcon die1;
	ImageIcon die2;
	ImageIcon die3;
	ImageIcon die4;
	ImageIcon die5;
	ImageIcon die6;
	ImageIcon die_disabled;
	ImageIcon player_icon;
	
	JTextArea log;
	
	ArrayList<JLabel> sceneCards;
	
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
		stats_player.setBounds(1250, 620, 400, 20);
		bPane.add(stats_player, new Integer(2));
		
		stats_location = new JLabel("Location: ", JLabel.LEFT);
		stats_location.setFont(new Font("Arial", Font.BOLD, 14));
		stats_location.setBounds(1250, 640, 400, 20);
		bPane.add(stats_location, new Integer(2));
		
		stats_role = new JLabel("Role: ", JLabel.LEFT);
		stats_role.setFont(new Font("Arial", Font.BOLD, 14));
		stats_role.setBounds(1250, 660, 400, 20);
		bPane.add(stats_role, new Integer(2));
		
		stats_rehpts = new JLabel("Rehearsal Points: ", JLabel.LEFT);
		stats_rehpts .setFont(new Font("Arial", Font.BOLD, 14));
		stats_rehpts .setBounds(1250, 680, 400, 20);
		bPane.add(stats_rehpts , new Integer(2));
		
		stats_dollars = new JLabel("Dollars: ", JLabel.LEFT);
		stats_dollars.setFont(new Font("Arial", Font.BOLD, 14));
		stats_dollars.setBounds(1250, 700, 400, 20);
		bPane.add(stats_dollars, new Integer(2));
		
		stats_credits = new JLabel("Credits: ", JLabel.LEFT);
		stats_credits.setFont(new Font("Arial", Font.BOLD, 14));
		stats_credits.setBounds(1250, 720, 400, 20);
		bPane.add(stats_credits, new Integer(2));
		
		stats_icon = new JLabel();
		stats_icon.setBounds(1510, 640, 40,40);
		stats_icon.setIcon(player_icon);
		bPane.add(stats_icon, new Integer(2));
		
		buttons = new JButton[5];
		
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
		buttons[0] = bMove;
		
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
		buttons[1] = bTakeRole;
		
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
		buttons[2] = bAct;
		
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
		buttons[3] = bRehearse;
		
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
		buttons[4] = bUpgrade;
		
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
		
		sceneCards = new ArrayList<JLabel>();
		

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
		sceneLabel.setName(filename);
		bPane.add(sceneLabel,new Integer(2));
		sceneCards.add(sceneLabel);
	}
	
	public void createTokens(int numPlayers) {
		
		player_tokens = new JLabel[numPlayers];
		for (int i = 0; i < numPlayers; i++) {
			JLabel thisPlayer = new JLabel();
			bPane.add(thisPlayer, new Integer(3));
			thisPlayer.setVisible(false);
			player_tokens[i] = thisPlayer;
		}	
		
	}
	
	public void setLog(String text) {
		log.setText(text);
	}
	
	public void appendLog(String text) {
		log.append(text);
	}
	
	
	public void discardScene(Scene scene) {
		String name = scene.getFileName();
		for (int i = 0; i < sceneCards.size(); i++) {
			if (name == sceneCards.get(i).getName()) {
				sceneCards.get(i).setVisible(false);
			}
		}
	}
	
	public void resetScenes() {
		sceneCards = new ArrayList<JLabel>();
	}
	
	public void placeShotToken() {
		
	}
	
	
	// updateStats
	// Preconditions:
	//		- A player's attribute(s) have changed
	// Postconditions:
	//		- Updates all player's information on the stats board
	//		- Moves player token to correct spot
	public void updateStats(Player player, int num) {
		player_icon = new ImageIcon("images/dice/"+player.getFileName()); //shows player's icon
		player_tokens[num].setIcon(player_icon);
		stats_icon.setIcon(player_icon);

		if (player.isInRole()) {
			stats_role.setText("Role: " + player.getRole().getName());
			player_tokens[num].setVisible(true);
			
			int[] roledims = player.getRole().getDims();
			int[] locationdims = player.getLocation().getDims();
			int playerx = 0;
			int playery = 0;
			
			if (player.getRole().isOnCard()) {
				playerx = roledims[0] + locationdims[0] + 10;
				playery = roledims[1] + locationdims[1] + 10;
			} else {
				playerx = roledims[0] + 11;
				playery = roledims[1] + 11;
			}
			player_tokens[num].setBounds(playerx,playery,40,40); // set the icon to correct bounds using player's attributes
		} else {
			stats_role.setText("Role: ");
			player_tokens[num].setVisible(false);
		} 
		
		
		stats_player.setText("Current Player: " + player.getName());
		stats_location.setText("Location: " + player.getLocation().getName());
		stats_rehpts.setText("Rehearsal Points: " + player.getRehearsePoints());
		stats_dollars.setText("Dollars: " + player.getCurrency());
		stats_credits.setText("Credits: " + player.getFame());
		
	}
	
	
	// showExtras
	// Preconditions:
	//		- Player has clicked on either take role or move
	// Postconditions:
	//		- Reveals the correct amount of buttons
	//		- Properly labels the buttons
	public void showExtras(String[] labels) {
		//needs to get rid of extra buttons after
		int numButtons = labels.length;
		for (int i = 0; i < 7; i++) {
			if (i < numButtons) { //set these buttons to visible
			extraButtons[i].setText(labels[i]);
			extraButtons[i].setVisible(true);
			extraButtons[i].setEnabled(true);
			}
			else { //any buttons after disappear
				extraButtons[i].setVisible(false);
				extraButtons[i].setEnabled(false);
			}
		}
	}
	
	public void updateButtons(boolean[] actions) {
		for (int i = 0; i < actions.length; i++) {
			if (actions[i] == true) {
				buttons[i].setEnabled(true);
			}
			else {
				buttons[i].setEnabled(false);
			}
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

