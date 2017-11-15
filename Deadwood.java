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
		parseXML(boardFile);
		
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
		//Location trailer = new Location();//need to change this
		//Deck deck = new Deck();
		//Location[] allLocations = new Location[0];
		
	/*	Board game = new Board(allLocations, deck);
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
		
			
		console.close();	 */			
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
	    Element root = doc.getDocumentElement();
	    
	    if (root.getNodeName() == "cards") {
		    NodeList cardList = doc.getElementsByTagName("card"); 
		    // make it a single variable each time instead of lists
		    
		    int budget = 0;
		    int scene_num = 0;
		    String name = "";
		    String scene_desc = "";
		    String p_name;
		    String p_line = "";
		    int p_lvl;
		    LinkedList<Scene> scenes = new LinkedList<Scene>();
		    
		    for (int i = 0; i < cardList.getLength(); i++) {
		    	
		    	Node cardNode = cardList.item(i); 
		    	ArrayList<Role> roles = new ArrayList<Role>();
		    	
		    	if (cardNode.getNodeType() == Node.ELEMENT_NODE) {		
		    		Element cardElement = (Element) cardNode;
		    		//System.out.println("Card name: " + cardElement.getAttribute("name"));
		    		name = cardElement.getAttribute("name");
		    		budget = Integer.parseInt(cardElement.getAttribute("budget"));
			    	NodeList pList = cardNode.getChildNodes(); 
			    	
			    	for (int p = 0; p < pList.getLength(); p++) {
			    		
			    		Node pNode = pList.item(p);
			    		if (pNode.getNodeType() == Node.ELEMENT_NODE) {
			    			Element pElement = (Element) pNode;
				    		switch (pNode.getNodeName()) {
				    		case "scene": 
				    			//System.out.println("Scene num: " + pElement.getAttribute("number"));
				    			scene_num = Integer.parseInt(pElement.getAttribute("number"));
				    			//System.out.println("Scene desc: " + cardElement.getElementsByTagName("scene").item(0).getTextContent());
				    			scene_desc = cardElement.getElementsByTagName("scene").item(0).getTextContent();
				    			break;
				    		case "part":
				    			NodeList pList2 = pNode.getChildNodes();
				    			//System.out.println("Part name: " + pElement.getAttribute("name"));
				    			p_name = pElement.getAttribute("name");
				    			//System.out.println("Part level: " + pElement.getAttribute("level"));
				    			p_lvl = Integer.parseInt(pElement.getAttribute("level"));
				    			for (int q = 0; q < pList2.getLength(); q++) {
				    				Node qNode = pList2.item(q);		
				    				if (qNode.getNodeType() == Node.ELEMENT_NODE) {
				    					Element qElement = (Element) qNode;
				    					switch (qNode.getNodeName()) {
				    					case "line":
				    						//System.out.println("Part line: " + pElement.getElementsByTagName("line").item(0).getTextContent());
				    						p_line = pElement.getElementsByTagName("line").item(0).getTextContent();
				    						break;
				    					case "area":
				    						break;
				    					}
				    				}
				    			}
				    			//System.out.println(p_name + "-" + p_line + "-" + p_lvl);
				    			Role newRole = new Role(p_name, p_line, p_lvl, true);
				    			roles.add(newRole);
				    		}	
			    		}
			    	}
		    	}
		    	Role[] parts = new Role[roles.size()];
		    	parts = roles.toArray(parts);
		    	Scene newScene = new Scene(name, scene_desc, budget, scene_num, parts);
		    	scenes.add(newScene);
		    }
		    Deck deck = new Deck(scenes);
	    } else {
	    	// root is board
	    	// loop through sets first
	    	
	    	ArrayList<Location> locations = new ArrayList<Location>();
	    	
	    	NodeList setList = doc.getElementsByTagName("set");
	    	
	    	String set_name;
	    	String neighbor;
	    	ArrayList<String> neighbors = new ArrayList<String>();
	    	ArrayList<Role> roles = new ArrayList<Role>();
	    	int takeNum = 0; // shot max
	    	Role[] parts;
	    	
	    	for (int i = 0; i < setList.getLength(); i++) {
	    		Node setNode = setList.item(i);
	    		if (setNode.getNodeType() == Node.ELEMENT_NODE) {
	    			Element setElement = (Element) setNode;
	    			System.out.println("Set name: " + setElement.getAttribute("name"));
	    			set_name = setElement.getAttribute("name");
	    			NodeList bigList = setNode.getChildNodes();
	    			
	    			for (int j = 0; j < bigList.getLength(); j++) {
	    				Node tNode = bigList.item(j);
	    				if (tNode.getNodeType() == Node.ELEMENT_NODE) {
	    					Element tElement = (Element) tNode;
	    					switch (tNode.getNodeName()) {
	    					case "neighbors":
	    						NodeList nList = tNode.getChildNodes();
	    						for (int n = 0; n < nList.getLength(); n++) {
	    							Node nNode = nList.item(n);
	    							if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				    					Element nElement = (Element) nNode;
				    					neighbor = nElement.getAttribute("name");
				    					System.out.println("Neighbor name: " + neighbor);
				    					neighbors.add(neighbor);
	    							}
	    						}
	    						String[] _neighbors = new String[neighbors.size()];
	    						_neighbors = neighbors.toArray(_neighbors);
	    						break;
	    					case "takes":
	    						NodeList takeList = tNode.getChildNodes();
	    						for (int k = 0; k < takeList.getLength(); k++)
	    							takeNum++;
	    						System.out.println("Number of takes: " + takeNum);
	    						break;
	    						
	    					case "parts":
	    						NodeList partsList = tNode.getChildNodes();
	    						int p_lvl = 0;
	    						String p_name = "";
	    						String p_line = "";
	    						for (int p = 0; p < partsList.getLength(); p++) {
	    							Node partNode = partsList.item(p);
	    							if (partNode.getNodeType() == Node.ELEMENT_NODE) {
	    								Element pElement = (Element) partNode;
	    								p_name = pElement.getAttribute("name");
	    								p_lvl = Integer.parseInt(pElement.getAttribute("level"));
	    								NodeList partList2 = partNode.getChildNodes();	
	    								for (int q = 0; q < partList2.getLength(); q++) {
	    									Node qNode = partList2.item(q);		
	    				    				if (qNode.getNodeType() == Node.ELEMENT_NODE) {
	    				    					Element qElement = (Element) qNode;
	    				    					switch (qNode.getNodeName()) {
	    				    					case "line":
	    				    						p_line = pElement.getElementsByTagName("line").item(0).getTextContent();
	    				    						break;
	    				    					case "area":
	    				    						break;
	    				    					}
	    				    				}
	    								}
	    							}
	    							System.out.println("Role is: " + p_name + ". Level is: " + p_lvl + ". Line is: " + p_line);
	    							Role newRole = new Role(p_name, p_line, p_lvl, false);
	    							roles.add(newRole);
	    						}
	    						parts = new Role[roles.size()];
	    				    	parts = roles.toArray(parts);
	    					}
	    				}
	    			}

	    			Location newLocation = new Location(set_name, takeNum, 0, null, null);
	    			locations.add(newLocation);
		    		takeNum = 0;
	    		}
	    	}
	    	
	    	
	    	// node office
	    	// then trailer and office
	    }
	    
	    
	    // if root == board
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
