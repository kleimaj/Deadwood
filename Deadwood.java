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
		
		File cardsFile = new File(args[0]);
		File boardFile = new File(args[1]);
		parseXML(cardsFile);
		
		// Scanner
		Scanner console = new Scanner(System.in);
		
		System.out.println("Welcome to Deadwood!");
		System.out.println("How many Players do we have today? (maximum of 8)");
		
/*		int numPlayers;
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
//		Deck deck = new Deck();
//		Location[] allLocations = new Location[0];
		
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
					
					System.out.println("Select what you want to do: ");
					
					while(true) {
						if (currentPlayer.isInRole()==false) {
							System.out.println("Move");
						}
				}
				
			}
			game.CycleDay();
		}
		
			
		console.close();				*/
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
	public void parseXML(File inputFile) {
		
		try {
		// Create a document builder
		DocumentBuilderFactory dbFactory  = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    
	    // Create a document from a file
	    Document doc = dBuilder.parse(inputFile);    // the .xml file
	    doc.getDocumentElement().normalize();
	    
	    // Extract the root and other elements
	    Element root = document.getDocumentElement();
	    
	    // if root == cards
	    NodeList cList = doc.getElementsByTagName("card");
	    
	    for (int i = 0; i < clist.getLength(); i++) {
	    	Node cNode = cList.item(i);
	    	NodeList scList = doc.getElementsByTagName("scene");
	    	for (int s = 0; s < scList.getLength(); s++) {
	    		
	    		
	    	}
	    	Node cNode = cList.item(i);
	    	if (cNode.getNodeType() == Node.ELEMENT_NODE) {
	    		Element cElement = (Element) cNode;
	    		System.out.println("Card name: " + cElement.getAttribute("card"));
	    		System.out.println("Scene name: " + cElement.getAttribute("scene"));
	    	}
	    }
	    // if root == board
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
