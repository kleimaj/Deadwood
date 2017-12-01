

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class Controller implements MouseListener {

    // Attributes
    Visual visual;
    Board board;
    Deck deck;

    /*public Controller(Deck deck, Board board, Visual visual){
    	this.deck = deck;
    	this.board = board;
    	this.visual = visual;
    }*/
    
    public void mouseClicked(ActionEvent e) {
    	JButton button = (JButton) e.getSource();
    	switch(button.getText()) {
    	case "1": ;
    		
    	case "2": ;
    	
    	case "3": ;
    	
    	case "4": ;
    	
    	case "5": ;
    	
    	case "6": ;
    		
    	case "7": ;
    		
    	case "8": ; //num players
    	
    	case "Act": ; //action selection
    	
    	case "Move": ;
    	
    	case "Rehearse": ;
    	
    	case "Upgrade": ;
    	
    	case "Take Role": ;
    	}
    	
    	//String label = button.getText(); //name of button
    	
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}
