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
		
	}
	
	public Visual(int number) {
		
		setSize(500,300);
		setTitle("Main Menu");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(SCREEN_WIDTH/2 - getWidth()/2, SCREEN_HEIGHT/2 - getHeight()/2);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(new Insets(40,60,40,60)));
		String[] buttons = {"1","2","3","4","5","6","7","8"};
		
		JLabel textLabel = new JLabel("Select number of players!");
		//textLabel.setVerticalAlignment(CENTER);
		panel.add(textLabel);
		
		for(int i = 0; i < buttons.length; i++) {
			JButton button = new JButton(buttons[i]);
			button.addMouseListener(new MenuMouseListener()); //problem here
			panel.add(button);
			panel.add(Box.createRigidArea(new Dimension(0,5)));
		}
		add(panel);
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

