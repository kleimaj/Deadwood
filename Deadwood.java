/* 
 * Deadwood.java
 * 
 * Contributors: Jacob Kleiman, Eric Eagan, Ryan McGinnis
 * November 2017
 */
import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Deadwood {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		parseXML();
		
		// Scanner
		Scanner console = new Scanner(System.in);
		
		System.out.println("Welcome to Deadwood!");
		System.out.println("How many Players do we have today? (maximum of 8)");
		
		int numPlayers;
		// loops until user inputs valid number of players
		while (true) {
			numPlayers = console.nextInt();
			if (numPlayers > 0 && numPlayers <= 8) {
				break;
			} else { 
			System.out.println("Invalid Number of Players, try again");
			
			}
		}
		System.out.println("Input each player's name");
		//need to get trailer from xml file or from gameboard, need to change all of this
		Location trailer = new Location();//need to change this
		Deck deck = new Deck();
		Location[] allLocations = new Location[0];
		
		Board game = new Board(allLoctions, deck);
		for (int i = 0; i < numPlayers; i++) {
			 String name = console.next();
			 game.addPlayer(new Player(name, trailer));
			 
		}
		//at this point, the first day starts, scenes need to be added to locations
		Player[] players = game.getPlayers();
		for (int i = 0; i < 4; i++) { //4 days
		//each day
			System.out.println("Day "+i+1);
			int index = 0;
			while(game.isEndDay()==false) {
				if (index == numPlayers) { //cycles through players
					index = 0;
				}
					Player currentPlayer = players[index];
					System.out.println(currentPlayer.getName()+"'s turn!");
					
					System.out.println("Select what you want to do: ")
					ArrayList<String> availableActions = new ArrayList<String>();
					int count = 1;
					while(true) {
						if (currentPlayer.isInRole()==false) {
							availableActions.add("move");
							System.out.println("Move ("+count+")");
							count++;
						}
						if (currentPlayer.isInCastingOffice()) {
							availableActions.add("upgrade");
							System.out.println("Upgrade ("+count+")");
							count++;
						}
						if (currentPlayer.isInRole() && currentPlayer.getRehearsePoints() < 6) {
							availableActions.add("rehearse");
							System.out.println("Rehearse ("+count+")");
							count++;
						}
						if (currentPlayer.isInRole() == false && currentPlayer.getLocation().isWrappedUp() == false) {
							availableActions.add("take role");
							System.out.println("Take Role ("+count+")");
							count++;
						}
						if (currentPlayer.isInRole() && )
				}
				
			}
			game.CycleDay();
		}
		
			
		console.close();
	}
	
	
	// Parse XML
	// Preconditions:
	//
	//
	// Postconditions:
	//		- scenes and locations are all created
	//
	// Notes:
	//
	//
	public void parseXML(inputFile) {
		
		// Create a document builder
		DocumentBuilderFactory dbFactory  = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    
	    // Create a document from a file
	    Document doc = dBuilder.parse(inputFile);    // the .xml file
	    
	    // Extract the root and other elements
	    Element root = document.getDocumentElement();
	    	
	    // Get all elements and attributes
	}
}
