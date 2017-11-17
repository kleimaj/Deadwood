/* 
 * Deadwood.java
 * 
 * Contributors: Jacob Kleiman, Eric Eagan, Ryan McGinnis
 * November 2017
 */
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.util.concurrent.TimeUnit;

public class Deadwood {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		File cardsFile = new File(args[0]);
		File boardFile = new File(args[1]);
		Deck deck = getDeck(cardsFile);
		ArrayList<Location> locations = getLocations(boardFile);
		
		//need to give locations scenes
		
		for (int i = 0; i < locations.size(); i++) {
			locations.get(i).setScene(deck.draw());
		}
		// Scanner
		Scanner console = new Scanner(System.in);
		
		System.out.println("Welcome to Deadwood!");
		TimeUnit.SECONDS.sleep(1);
		while(true) { //want to play again loop
		System.out.println("How many Players do we have today? (maximum of 8)");
		
		Location[] allLocations = new Location[locations.size()];
		for (int i = 0; i < locations.size(); i++) {
			allLocations[i] = locations.get(i);
		}
		
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
		
	//begin comment here
		Board game = new Board(allLocations, deck);
		Location trailer = game.getTrailer();
		for (int i = 0; i < numPlayers; i++) {
			 String name = console.next();
			 game.addPlayer(new Player(name, trailer));
			 
		}
		//at this point, the first day starts, scenes need to be added to locations
		Player[] players = game.getPlayers();
		for (int i = 0; i < 4; i++) { //4 days
		//each day
			System.out.println("Day "+i+1);
			TimeUnit.SECONDS.sleep(1);
			int index = 0;
			while(game.isEndDay()==false) {
				if (index == numPlayers) { //cycles through players
					index = 0;
				}
					Player currentPlayer = players[index];
					System.out.println();
					System.out.println(currentPlayer.getName()+"'s turn!");
					TimeUnit.SECONDS.sleep(1);
					System.out.println("Player: "+currentPlayer.getName()+" ($"+currentPlayer.getCurrency()+", "+currentPlayer.getFame()+" Fame)");
					if (currentPlayer.getRole() != null) {
						System.out.println("Working as "+currentPlayer.getRole().getName()+", '"+currentPlayer.getRole().getDialogue()+"' at Scene "+currentPlayer.getLocation().getScene().getNumber());
					}
					if (currentPlayer.getLocation().isLot()) {
					System.out.print(currentPlayer.name+" is currently in the "+currentPlayer.getLocation().getName());
					System.out.println(" which has "+currentPlayer.currentLocation.getShotsTaken()+"/"+currentPlayer.currentLocation.getShotsMax()+" Shots Completed");
					}
					if (currentPlayer.getLocation().isLot()==false) {
						System.out.println(currentPlayer.name+" is currently in the "+currentPlayer.getLocation().getName());
					}
					
						//Start loop 1
				while(true) {
					//System.out.println("Select what you want to do: ");
					ArrayList<String> availableActions = new ArrayList<String>();
					int count = 1;
					int theNum = 0;
					System.out.println("Select (the number) what you want to do: ");
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
						if (currentPlayer.isInRole() == false && currentPlayer.getLocation().isWrappedUp() == false && currentPlayer.getLocation().isLot()==true) {
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
					String action = availableActions.get(theNum-1);
					if (action.equals("move")) {
						currentPlayer.Move(locations);//effect hasMoved = ...
						if (currentPlayer.getLocation().getName().equals("Office")) { //if the player moves to casting office they can upgrade
							System.out.println("Would you like to Upgrade? (y/n)");
							String reply = console.next();
							if (reply.equals("y")) {
								currentPlayer.Upgrade();
							}
							break;
						}
						if (currentPlayer.getLocation().isLot() && currentPlayer.getLocation().isWrappedUp() == false) {
							System.out.println("Would you like to Take a Role? (y/n)");
							String reply = console.next();
							if (reply.equals("y")) {
								currentPlayer.TakeRole();	//can a player move, take a role, and act all in one move? right now no
								break;
							}
							break;
						}
						break; //??
					}
					if (action.equals("upgrade")) {
						currentPlayer.Upgrade();//effect endTurn = ...    //can player upgrade then move? if yes, can they take a role after?
						//break;
					}
					if (action.equals("act")) {
						currentPlayer.Act();//effect endTurn = ...
						if (currentPlayer.getLocation().getShotsTaken() == currentPlayer.getLocation().getShotsMax()) {
							game.WrapScene(currentPlayer.getLocation());
						}
						break;
					}
					if (action.equals("rehearse")) {
						currentPlayer.Rehearse();//effect endTurn = ...
						break;
					}
					if (action.equals("take role")) {
						currentPlayer.TakeRole();
						if (currentPlayer.isInRole() == true) {
							System.out.println("Would you like to Act (1) or Rehearse? (2) ");
							int reply = console.nextInt();
							if (reply == 1) {
								currentPlayer.Act();
								break;
							}
							else if (reply == 2) {
								currentPlayer.Rehearse();
								break;
							}
							else {
								break;
							}
						}
					}
				}
					//End Loop 1
					index++; //end of player's turn
				
			}
			game.CycleDay();
		}
		//players[1].setCurrency(1); testing purposes
		System.out.println("The game is over here are the results: ");
		TimeUnit.SECONDS.sleep(1);
		Player winner = game.TallyScore();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Congratulations, "+winner.getName()+"!!");
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Would you like to play again? (y/n)"); //need to make loop
		
		String playAgain = console.next();
		
		if (!playAgain.equals("y")) {
			break;
		}
		
		}//end while loop here	
		console.close();	 //end comment here	*/		
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
	    Document doc = dBuilder.parse(inputFile);   
	    doc.getDocumentElement().normalize();
	    
	    // Extract the root and other elements
	    Element root = doc.getDocumentElement();
	    
	    if (root.getNodeName() == "cards") {
		    NodeList cardList = doc.getElementsByTagName("card"); 
		    
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
		    		name = cardElement.getAttribute("name");
		    		budget = Integer.parseInt(cardElement.getAttribute("budget"));
			    	NodeList pList = cardNode.getChildNodes(); 
			    	
			    	for (int p = 0; p < pList.getLength(); p++) {
			    		
			    		Node pNode = pList.item(p);
			    		if (pNode.getNodeType() == Node.ELEMENT_NODE) {
			    			Element pElement = (Element) pNode;
				    		switch (pNode.getNodeName()) {
				    		case "scene": 
				    			scene_num = Integer.parseInt(pElement.getAttribute("number"));
				    			scene_desc = cardElement.getElementsByTagName("scene").item(0).getTextContent();
				    			break;
				    		case "part":
				    			NodeList pList2 = pNode.getChildNodes();
				    			p_name = pElement.getAttribute("name");
				    			p_lvl = Integer.parseInt(pElement.getAttribute("level"));
				    			for (int q = 0; q < pList2.getLength(); q++) {
				    				Node qNode = pList2.item(q);		
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
	    	int takeNum = 0; // shot max
	    	Role[] parts = null;
	    	String[] _neighbors = null;
			int p_lvl = 0;
			String p_name = "";
			String p_line = "";
			
	    	for (int i = 0; i < setList.getLength(); i++) {
	    		takeNum = 0;
	    		parts = null;
	    		ArrayList<Role> roles = new ArrayList<Role>();
		    	ArrayList<String> neighbors = new ArrayList<String>();
		    	
	    		Node setNode = setList.item(i);
	    		if (setNode.getNodeType() == Node.ELEMENT_NODE) {
	    			Element setElement = (Element) setNode;
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
				    					neighbors.add(neighbor);
	    							}
	    						}
	    						_neighbors = new String[neighbors.size()];
	    						_neighbors = neighbors.toArray(_neighbors);
	    						break;
	    					case "takes":
	    						NodeList takeList = tNode.getChildNodes();
	    						for (int k = 0; k < takeList.getLength(); k++) {
	    							// create take objects here (GUI)
	    							Node takeNode = takeList.item(k);
	    							if (takeNode.getNodeType() == Node.ELEMENT_NODE) {
		    							takeNum++;
	    							}
	    						}
	    						break;
	    						
	    					case "parts":
	    						NodeList partsList = tNode.getChildNodes();
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
	        							//System.out.println("Role is: " + p_name + ". Level is: " + p_lvl + ". Line is: " + p_line);
		    							Role newRole = new Role(p_name, p_line, p_lvl, false);
		    							roles.add(newRole);
	    							}
	    						}
	    					}
	    				}
						parts = new Role[roles.size()];
				    	parts = roles.toArray(parts);
	    			}
	    		/*	System.out.println("SET: " + set_name + " " + takeNum + " ");
	    			System.out.println("ROLES:");
	    			for (int z = 0; z < parts.length; z++) {
	    				System.out.println(parts[z].getName());
	    			}
	    			System.out.println("NEIGHBORS:");
	    			for (int w = 0; w < _neighbors.length; w++) {
	    				System.out.println(_neighbors[w]);
	    			}   */
	    			Location newLocation = new Location(set_name, takeNum, 0, null, parts);
	    			newLocation.setNeighbors(_neighbors);
	    			locations.add(newLocation);
	    		}
	    	}
	    	// TRAILER ----------------------------------------------------------------->
	    	NodeList trailer = doc.getElementsByTagName("trailer");
	    	Node trailerNode = trailer.item(0);
	    	ArrayList<String> neighbors = new ArrayList<String>();
	    	_neighbors = null;
	    	
	    	NodeList neighborList = trailerNode.getChildNodes();
	    	for (int temp = 0; temp < neighborList.getLength(); temp++) {
	    		Node _nNode = neighborList.item(temp); 
	    		if (_nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element nElement = (Element) _nNode;
					switch (_nNode.getNodeName()) {
					case "neighbors":
						NodeList dList = _nNode.getChildNodes();
						for (int n = 0; n < dList.getLength(); n++) {
							Node eNode = dList.item(n);
							if (eNode.getNodeType() == Node.ELEMENT_NODE) {
		    					Element fElement = (Element) eNode;
		    					neighbor = fElement.getAttribute("name");
		    					neighbors.add(neighbor);
							}
						}
					}
	    			
				}
			}
	    	_neighbors = new String[neighbors.size()];
			_neighbors = neighbors.toArray(_neighbors);

	    	Location newLocation = new Location("Trailer");
	    	newLocation.setNeighbors(_neighbors);
	    	locations.add(newLocation);
	    	
	    	// CASTING OFFICE ---------------------------------------------------------->
	    	NodeList office = doc.getElementsByTagName("office");
	    	Node officeNode = office.item(0);
	    	neighbors = new ArrayList<String>();
	    	_neighbors = null;
	    	
	    	NodeList officeList = officeNode.getChildNodes();
	    	for (int temp = 0; temp < officeList.getLength(); temp++) {
	    		Node oNode = officeList.item(temp);
	    		if (oNode.getNodeType() == Node.ELEMENT_NODE) {
	    			switch (oNode.getNodeName()){
	    			case "neighbors":
	    				NodeList nList2 = oNode.getChildNodes();
						for (int n = 0; n < nList2.getLength(); n++) {
							Node n_Node = nList2.item(n);
							if (n_Node.getNodeType() == Node.ELEMENT_NODE) {
		    					Element n_Element = (Element) n_Node;
		    					neighbor = n_Element.getAttribute("name");
		    					neighbors.add(neighbor);
							}
						}
						_neighbors = new String[neighbors.size()];
						_neighbors = neighbors.toArray(_neighbors);
		    			break;
	    			case "upgrades":
	    				// Create GUI objects here
	    				break;
	    			}
	    		}
	    	}
	    	
	    	_neighbors = new String[neighbors.size()];
			_neighbors = neighbors.toArray(_neighbors);

			newLocation = new Location("Office");
	    	newLocation.setNeighbors(_neighbors);
	    	locations.add(newLocation);
	    	
	    } 
	    
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static Deck getDeck(File input) {
		try {
			
			// Create a document builder
			DocumentBuilderFactory dbFactory  = DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    
		    // Create a document from a file
		    Document doc = dBuilder.parse(input);   
		    doc.getDocumentElement().normalize();
		    
		    // Extract the root and other elements
		    Element root = doc.getDocumentElement();
		    
			    NodeList cardList = doc.getElementsByTagName("card"); 
			    
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
			    		name = cardElement.getAttribute("name");
			    		budget = Integer.parseInt(cardElement.getAttribute("budget"));
				    	NodeList pList = cardNode.getChildNodes(); 
				    	
				    	for (int p = 0; p < pList.getLength(); p++) {
				    		
				    		Node pNode = pList.item(p);
				    		if (pNode.getNodeType() == Node.ELEMENT_NODE) {
				    			Element pElement = (Element) pNode;
					    		switch (pNode.getNodeName()) {
					    		case "scene": 
					    			scene_num = Integer.parseInt(pElement.getAttribute("number"));
					    			scene_desc = cardElement.getElementsByTagName("scene").item(0).getTextContent();
					    			break;
					    		case "part":
					    			NodeList pList2 = pNode.getChildNodes();
					    			p_name = pElement.getAttribute("name");
					    			p_lvl = Integer.parseInt(pElement.getAttribute("level"));
					    			for (int q = 0; q < pList2.getLength(); q++) {
					    				Node qNode = pList2.item(q);		
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
				return deck;
			    }
		catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}
	
	public static ArrayList<Location> getLocations(File input) {
		
		//Location[] allLocations = null;
		try {
			
		// Create a document builder
		DocumentBuilderFactory dbFactory  = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    
	    // Create a document from a file
	    Document doc = dBuilder.parse(input);   
	    doc.getDocumentElement().normalize();
	    
	    // Extract the root and other elements
	    //Element root = doc.getDocumentElement();
		// root is board
    	// loop through sets first
    	
    	ArrayList<Location> locations = new ArrayList<Location>();
    	
    	NodeList setList = doc.getElementsByTagName("set");
    	
    	String set_name;
    	String neighbor;
    	int takeNum = 0; // shot max
    	Role[] parts = null;
    	String[] _neighbors = null;
		int p_lvl = 0;
		String p_name = "";
		String p_line = "";
		
    	for (int i = 0; i < setList.getLength(); i++) {
    		takeNum = 0;
    		parts = null;
    		ArrayList<Role> roles = new ArrayList<Role>();
	    	ArrayList<String> neighbors = new ArrayList<String>();
	    	
    		Node setNode = setList.item(i);
    		if (setNode.getNodeType() == Node.ELEMENT_NODE) {
    			Element setElement = (Element) setNode;
    			set_name = setElement.getAttribute("name");
    			NodeList bigList = setNode.getChildNodes();
    			
    			for (int j = 0; j < bigList.getLength(); j++) {
    				Node tNode = bigList.item(j);
    				if (tNode.getNodeType() == Node.ELEMENT_NODE) {
    					//Element tElement = (Element) tNode;
    					switch (tNode.getNodeName()) {
    					case "neighbors":
    						NodeList nList = tNode.getChildNodes();
    						for (int n = 0; n < nList.getLength(); n++) {
    							Node nNode = nList.item(n);
    							if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			    					Element nElement = (Element) nNode;
			    					neighbor = nElement.getAttribute("name");
			    					neighbors.add(neighbor);
    							}
    						}
    						_neighbors = new String[neighbors.size()];
    						_neighbors = neighbors.toArray(_neighbors);
    						break;
    					case "takes":
    						NodeList takeList = tNode.getChildNodes();
    						for (int k = 0; k < takeList.getLength(); k++) {
    							// create take objects here (GUI)
    							Node takeNode = takeList.item(k);
    							if (takeNode.getNodeType() == Node.ELEMENT_NODE) {
	    							takeNum++;
    							}
    						}
    						break;
    						
    					case "parts":
    						NodeList partsList = tNode.getChildNodes();
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
    				    					//Element qElement = (Element) qNode;
    				    					switch (qNode.getNodeName()) {
    				    					case "line":
    				    						p_line = pElement.getElementsByTagName("line").item(0).getTextContent();
    				    						break;
    				    					case "area":
    				    						break;
    				    					}
    				    				}
    								}
        							//System.out.println("Role is: " + p_name + ". Level is: " + p_lvl + ". Line is: " + p_line);
	    							Role newRole = new Role(p_name, p_line, p_lvl, false);
	    							roles.add(newRole);
    							}
    						}
    					}
    				}
					parts = new Role[roles.size()];
			    	parts = roles.toArray(parts);
    			}
    		/*	System.out.println("SET: " + set_name + " " + takeNum + " ");
    			System.out.println("ROLES:");
    			for (int z = 0; z < parts.length; z++) {
    				System.out.println(parts[z].getName());
    			}
    			System.out.println("NEIGHBORS:");
    			for (int w = 0; w < _neighbors.length; w++) {
    				System.out.println(_neighbors[w]);
    			}   */
    			Location newLocation = new Location(set_name, takeNum, 0, null, parts);
    			newLocation.setNeighbors(_neighbors);
    			locations.add(newLocation);
    		}
    	}
    	// TRAILER ----------------------------------------------------------------->
    	NodeList trailer = doc.getElementsByTagName("trailer");
    	Node trailerNode = trailer.item(0);
    	ArrayList<String> neighbors = new ArrayList<String>();
    	_neighbors = null;
    	
    	NodeList neighborList = trailerNode.getChildNodes();
    	for (int temp = 0; temp < neighborList.getLength(); temp++) {
    		Node _nNode = neighborList.item(temp); 
    		if (_nNode.getNodeType() == Node.ELEMENT_NODE) {
				//Element nElement = (Element) _nNode;
				switch (_nNode.getNodeName()) {
				case "neighbors":
					NodeList dList = _nNode.getChildNodes();
					for (int n = 0; n < dList.getLength(); n++) {
						Node eNode = dList.item(n);
						if (eNode.getNodeType() == Node.ELEMENT_NODE) {
	    					Element fElement = (Element) eNode;
	    					neighbor = fElement.getAttribute("name");
	    					neighbors.add(neighbor);
						}
					}
				}
    			
			}
		}
    	_neighbors = new String[neighbors.size()];
		_neighbors = neighbors.toArray(_neighbors);

    	Location newLocation = new Location("Trailer");
    	newLocation.setNeighbors(_neighbors);
    	locations.add(newLocation);
    	
    	// CASTING OFFICE ---------------------------------------------------------->
    	NodeList office = doc.getElementsByTagName("office");
    	Node officeNode = office.item(0);
    	neighbors = new ArrayList<String>();
    	_neighbors = null;
    	
    	NodeList officeList = officeNode.getChildNodes();
    	for (int temp = 0; temp < officeList.getLength(); temp++) {
    		Node oNode = officeList.item(temp);
    		if (oNode.getNodeType() == Node.ELEMENT_NODE) {
    			switch (oNode.getNodeName()){
    			case "neighbors":
    				NodeList nList2 = oNode.getChildNodes();
					for (int n = 0; n < nList2.getLength(); n++) {
						Node n_Node = nList2.item(n);
						if (n_Node.getNodeType() == Node.ELEMENT_NODE) {
	    					Element n_Element = (Element) n_Node;
	    					neighbor = n_Element.getAttribute("name");
	    					neighbors.add(neighbor);
						}
					}
					_neighbors = new String[neighbors.size()];
					_neighbors = neighbors.toArray(_neighbors);
	    			break;
    			case "upgrades":
    				// Create GUI objects here
    				break;
    			}
    		}
    	}
    	
    	_neighbors = new String[neighbors.size()];
		_neighbors = neighbors.toArray(_neighbors);

		newLocation = new Location("Office");
    	newLocation.setNeighbors(_neighbors);
    	locations.add(newLocation);
    	return locations;
    	
    	
   
    
	} catch (Exception e) {
		e.printStackTrace();
	}
		return null;
	}
}
