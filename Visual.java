
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
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
	}
}
