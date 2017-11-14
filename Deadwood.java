/* 
 * Deadwood.java
 * 
 * Contributors: Jacob Kleiman, Eric Eagan, Ryan McGinnis
 * November 2017
 */
import java.util.*;
import java.io.*;
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
		
		Board game = new Board(allLocations, deck);
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
					
						//Start loop 1
					System.out.println("Select what you want to do: ");
					ArrayList<String> availableActions = new ArrayList<String>();
					int count = 1;
					int theNum = 0;
					//valid move
					System.out.println("Select (the number) what you want to do: ");
					// + Boolean variables endTurn, hasMoved
					while(true) {
						if (currentPlayer.isInRole()==false) {//+condition hasMoved == false && endTurn == false
							availableActions.add("move");
							System.out.println("Move ("+count+")");
							count++;
						}
						if (currentPlayer.isInCastingOffice()) {
							availableActions.add("upgrade");
							System.out.println("Upgrade ("+count+")");
							count++;
						}
						if (currentPlayer.isInRole()) {//+condition hasMoved == false
							availableActions.add("act");
							System.out.println("Act ("+count+")");
							count++;
						}
						if (currentPlayer.isInRole() && currentPlayer.getRehearsePoints()+1 < currentPlayer.getLocation().getScene().getBudget()) {//+ condition hasMoved == false
							availableActions.add("rehearse");
							System.out.println("Rehearse ("+count+")");
							count++;
						}
						if (currentPlayer.isInRole() == false && currentPlayer.getLocation().isWrappedUp() == false) {
							availableActions.add("take role");
							System.out.println("Take Role ("+count+")");
							count++;
						}
						theNum = console.nextInt();
						
						if (theNum <= 0 || theNum > count) {
							System.out.println("Invalid Action, please try again.");
						}
						else {
							break;
						}
						
				}
					String action = availableActions.get(count-1);
					if (action.equals("move")) {
						currentPlayer.Move();//effect hasMoved = ...
						if (currentPlayer.getLocation().getName().equals("Casting Office")) { //if the player moves to casting office they can upgrade
							System.out.println("Would you like to Upgrade? (y/n)");
							String reply = console.next();
							if (reply.equals("y")) {
								currentPlayer.Upgrade();
							}
						}
						if (currentPlayer.getLocation().isLot() && currentPlayer.getLocation().isWrappedUp() == false) {
							System.out.println("Would you like to Take a Role? (y/n)");
							String reply = console.next();
							if (reply.equals("y")) {
								currentPlayer.TakeRole();
							}
						}
					}
					if (action.equals("upgrade")) {
						currentPlayer.Upgrade();//effect endTurn = ...
					}
					if (action.equals("act")) {
						currentPlayer.Act();//effect endTurn = ...
					}
					if (action.equals("rehearse")) {
						currentPlayer.Rehearse();//effect endTurn = ...
					}
					if (action.equals("take role")) {
						currentPlayer.TakeRole();
					}
					
					//End Loop 1
					index++; //end of player's turn
				
			}
			game.CycleDay();
		}
		
		System.out.println("The game is over here are the results: ");
		Player winner = game.TallyScore();
		System.out.println("Congratulations, "+winner.getName()+"!!");
		
		System.out.println("Would you like to play again? (y/n)"); //need to make loop
		
		String playAgain = console.nextLine();
		
		if (playAgain.equals("n")) {
			//break
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
	public static void parseXML(File inputFile) {
		
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
