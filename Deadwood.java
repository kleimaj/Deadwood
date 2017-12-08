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

		File cardsFile = new File("cards.xml");
		File boardFile = new File("board.xml");
		Deck deck = getDeck(cardsFile);
		ArrayList<Location> locations = getLocations(boardFile);

		Visual mainMenu = new Visual(0);
		mainMenu.setVisible(true);
		MenuMouseListener listener = new MenuMouseListener();
		String command = listener.getCommand();
		mainMenu.setVisible(false);
		int numPlayers = Integer.parseInt(command);



		Visual board_view = new Visual();
		board_view.setVisible(true);

		// Assign scenes to locations

		Location[] allLocations = new Location[locations.size()];
		for (int i = 0; i < locations.size(); i++) {
			allLocations[i] = locations.get(i);
			if (locations.get(i).isLot()) {
				board_view.placeShotTokens(locations.get(i));
			}
		}

		Board game = new Board(allLocations, deck);
		Location trailer = game.getTrailer();
		int numDays = 4;
		String name;
		int rank = 1;
		int fame = 0;

		switch(numPlayers) {
		case 2: //play 3 days
			numDays = 3;
			break;
		case 3:
			numDays = 3;
			break; //play 3 days

		case 5:
			fame = 2;
			break; //start with 2 credits

		case 6:
			fame = 4;
			break; //start with 4 credits

		case 7:
			rank = 2;
			break; //start with rank 2

		case 8:
			rank = 2;// start with rank 2
			break;

		}
		name = null;
		String playerFile = null;
		for (int i = 1; i <= numPlayers; i++) {
			switch(i) {
				case 1:
					name = "Blue";
					playerFile = "b1.png";
					if (numPlayers >= 7) {
						playerFile = "b2.png";
					}
					break;
				case 2:
					name = "Cyan";
					playerFile = "c1.png";
					if (numPlayers >= 7) {
						playerFile = "c2.png";
					}
					break;
				case 3:
					name = "Green";
					playerFile = "g1.png";
					if (numPlayers >= 7) {
						playerFile = "g2.png";
					}
					break;
				case 4:
					name = "Orange";
					playerFile = "o1.png";
					if (numPlayers >= 7) {
						playerFile = "o2.png";
					}
					break;
				case 5:
					name = "Pink";
					playerFile = "p1.png";
					if (numPlayers >= 7) {
						playerFile = "p2.png";
					}
					break;
				case 6:
					name = "Red";
					playerFile = "r1.png";
					if (numPlayers >= 7) {
						playerFile = "r2.png";
					}
					break;
				case 7:
					name = "Purple";
					playerFile = "v1.png";
					if (numPlayers >= 7) {
						playerFile = "v2.png";
					}
					break;
				case 8:
					name = "Yellow";
					playerFile = "y1.png";
					if (numPlayers >= 7) {
						playerFile = "y2.png";
					}
					break;
			}
			game.addPlayer(new Player(name, trailer,rank,fame,playerFile));
		}

		board_view.createTokens(numPlayers);
		int layer = 2;
		while(true) { //want to play again loop
			Player[] players = game.getPlayers();
			for (int i = 0; i < players.length; i++) {
				players[i].resetStats(numPlayers);
			}
			// For each day
			for (int i = 0; i < numDays; i++) {//days
				board_view.incrementDay(i+1);
				//shotMarkers
				board_view.resetShotTokens();
				//scene cards
				for (int j = 0; j < locations.size(); j++) {
					if(locations.get(j).isLot()) {
						Scene currentScene = deck.draw();
						deck.discard(locations.get(j).getScene());
						locations.get(j).setScene(currentScene); //need to pass dimensions from location to visual
						locations.get(j).resetShots();
						locations.get(j).getScene().unWrap();
						String filename = currentScene.getFileName();
						int[] dims = locations.get(j).getDims();
						// display cards on locations
						board_view.showScene(filename, dims, layer);
						TimeUnit.MILLISECONDS.sleep(50);
					}
					//layer++;
				}
				layer++;

				int index = 0;
				while(game.isEndDay()==false) {
					if (index == numPlayers) { // cycles through players
						index = 0;
					}
					Player currentPlayer = players[index];

					board_view.updateStats(currentPlayer, index);

					board_view.setLog(currentPlayer.getName()+"'s turn!"+ '\n'+ "Select what you want to do!");

					boolean[] actions = currentPlayer.allActions();

					board_view.updateButtons(actions);

					System.out.println("Player: "+currentPlayer.getName()+" ($"+currentPlayer.getCurrency()+", "+currentPlayer.getFame()+" Fame)");
				/*	if (currentPlayer.getRole() != null) {
						System.out.println("Working as "+currentPlayer.getRole().getName()+", '"+currentPlayer.getRole().getDialogue()+"' at Scene "+currentPlayer.getLocation().getScene().getNumber());
					}
					if (currentPlayer.getLocation().isLot()) {
						System.out.print(currentPlayer.name+" is currently in the "+currentPlayer.getLocation().getName());
						System.out.println(" which has "+currentPlayer.currentLocation.getShotsTaken()+"/"+currentPlayer.currentLocation.getShotsMax()+" Shots Completed");
					}
					if (currentPlayer.getLocation().isLot()==false) {
						System.out.println(currentPlayer.name+" is currently in the "+currentPlayer.getLocation().getName());
					}*/

					while(true) {
						BoardMouseListener boardListener = new BoardMouseListener();
						String action = boardListener.getCommand(); //action is name of action (button they pressed)


						if (action.equals("Move")) {
							//currentPlayer.Move(locations);//effect hasMoved = ...
							board_view.showExtras(currentPlayer.getLocation().getNeighbors());
							board_view.updateButtons(new boolean[] {false,false,false,false,false}); //removes move button
							boardListener = new BoardMouseListener();
							board_view.setLog("Select where you'd like to Move!");
							String subAction = boardListener.getCommand();
							Location newLocation = game.getLocation(subAction);
							currentPlayer.setLocation(newLocation); //logically changes player's location
							board_view.updateStats(currentPlayer, index); //doesn't work right now

							if (currentPlayer.getLocation().isLot() && currentPlayer.getLocation().isWrappedUp() == false) {
								//need to get all available roles
								ArrayList<Role> roles = currentPlayer.getLocation().getAllLotRoles(currentPlayer.getRank());
								if (roles.size() == 0) { //will end turn if no available roles
									break;
								}
								System.out.println("Would you like to Take a Role? (y/n)");
								board_view.setLog("Would you like to Take a Role?");

								String[] reply = {"Yes","No"};
								board_view.showExtras(reply);
								boardListener = new BoardMouseListener();
								subAction = boardListener.getCommand();
								if (subAction.equals("Yes")) {

									//currentPlayer.TakeRole();	//can a player move, take a role, and act all in one move? right now no
									String[] allRoles = new String[roles.size()];
									for (int j = 0; j < roles.size(); j++) {
										allRoles[j] = roles.get(j).getName();
									}
									board_view.showExtras(allRoles);
									boardListener = new BoardMouseListener();
									board_view.setLog("Which Role would you like to take?");
									subAction = boardListener.getCommand();
									Role chosenRole = null;
									for (int j = 0; j < roles.size(); j++) {
										if (subAction.equals(roles.get(j).getName())) {
											chosenRole = roles.get(j);
										}
									}
									currentPlayer.setRole(chosenRole);
									board_view.updateStats(currentPlayer, index);
									break; //after they take role
								}
								break; //if they dont want to take role
							}
							break; //end of move break
						}
						if (action.equals("Upgrade")) {
							//currentPlayer.Upgrade();//effect endTurn = ...    //can player upgrade then move? if yes, can they take a role after?
							board_view.updateButtons(new boolean[] {false,false,false,false,false});
							int playerRank = currentPlayer.getRank();
							int[] moneyUp = {4,10,18,28,40};
							int[] fameUp = {5,10,15,20,25};
							board_view.setLog("Select the Rank you'd like to Upgrade to!");
							board_view.showExtras(currentPlayer.getAvailableRanks()); //displays available ranks in extra buttons

							boardListener = new BoardMouseListener();
							String chosenRank = boardListener.getCommand();

							boolean moneyAndFame = false;
							int currency = currentPlayer.getCurrency();
							int playerFame = currentPlayer.getFame();
							switch(chosenRank) {
								case "2":
									if (currency >= 4 && playerFame >= 5) { //money and fame
										moneyAndFame = true;
										break;
									}
									else if (currency >= 4) { //just money
										currentPlayer.setCurrency(currency - 4);
										currentPlayer.setRank(2);
										break;
									}
									else { //just fame
										currentPlayer.setFame(playerFame - 5);
										currentPlayer.setRank(2);
										break;
									}
								case "3":
									if (currency >= 10 && playerFame >= 10) { //money and fame
										moneyAndFame = true;
										break;
									}
									else if (currency >= 10) { //just money
										currentPlayer.setCurrency(currency - 10);
										currentPlayer.setRank(3);
										break;
									}
									else { //just fame
										currentPlayer.setFame(playerFame - 10);
										currentPlayer.setRank(3);
										break;
									}
								case "4":
									if (currency >= 18 && playerFame >= 15) { //money and fame
										moneyAndFame = true;
										break;
									}
									else if (currency >= 18) { //just money
										currentPlayer.setCurrency(currency - 18);
										currentPlayer.setRank(4);
										break;
									}
									else { //just fame
										currentPlayer.setFame(playerFame - 15);
										currentPlayer.setRank(4);
										break;
									}
								case "5":
									if (currency >= 28 && playerFame >= 20) { //money and fame
										moneyAndFame = true;
										break;
									}
									else if (currency >= 28) { //just money
										currentPlayer.setCurrency(currency - 28);
										currentPlayer.setRank(5);
										break;
									}
									else { //just fame
										currentPlayer.setFame(playerFame - 20);
										currentPlayer.setRank(5);
										break;
									}
								case "6":
									if (currency >= 40 && playerFame >= 25) { //money and fame
										moneyAndFame = true;
										break;
									}
									else if (currency >= 40) { //just money
										currentPlayer.setCurrency(currency - 40);
										currentPlayer.setRank(6);
										break;
									}
									else { //just fame
										currentPlayer.setFame(playerFame - 25);
										currentPlayer.setRank(6);
										break;
									}
							}
							if (moneyAndFame == true) {
								String[] extraActions = {"Money", "Fame"};
								board_view.showExtras(extraActions);
								board_view.setLog("Select a payment option for Upgrading!");
								boardListener = new BoardMouseListener();
								String method = boardListener.getCommand();

								switch(chosenRank) {
									case "2":
										if (method.equals("Money")) {
											currentPlayer.setCurrency(currency - 4);
											currentPlayer.setRank(2);
											break;
										}
										else {
											currentPlayer.setFame(playerFame - 5);
											currentPlayer.setRank(2);
											break;
										}
									case "3":
										if (method.equals("Money")) {
											currentPlayer.setCurrency(currency - 10);
											currentPlayer.setRank(3);
											break;
										}
										else {
											currentPlayer.setFame(playerFame - 10);
											currentPlayer.setRank(3);
											break;
										}
									case "4":
										if (method.equals("Money")) {
											currentPlayer.setCurrency(currency - 18);
											currentPlayer.setRank(4);
											break;
										}
										else {
											currentPlayer.setFame(playerFame - 15);
											currentPlayer.setRank(4);
											break;
										}
									case "5":
										if (method.equals("Money")) {
											currentPlayer.setCurrency(currency - 28);
											currentPlayer.setRank(5);
											break;
										}
										else {
											currentPlayer.setFame(playerFame - 20);
											currentPlayer.setRank(5);
											break;
										}
									case "6":
										if (method.equals("Money")) {
											currentPlayer.setCurrency(currency - 40);
											currentPlayer.setRank(6);
											break;
										}
										else {
											currentPlayer.setFame(playerFame - 25);
											currentPlayer.setRank(6);
											break;
										}
								}
							}

							break;
						}

						if (action.equals("Act")) {

							//currentPlayer.Act();//effect endTurn = ...
							board_view.updateButtons(new boolean[] {false,false,false,false,false});
							int diceRoll = 1+(int)(6*Math.random());
							int budget = currentPlayer.getLocation().getScene().getBudget();
							int overAllRoll = diceRoll+currentPlayer.getRehearsePoints();

							board_view.setLog("In order to succeed, you must roll greater than or equal to "+budget+"!");
							TimeUnit.MILLISECONDS.sleep(500);
							board_view.rollDice(diceRoll);
						//	TimeUnit.MILLISECONDS.sleep(1000);
							board_view.setLog("You rolled a "+diceRoll+"!");

							board_view.appendLog('\n'+"Along with your Rehearsal Points: "+currentPlayer.getRehearsePoints());
							board_view.appendLog('\n'+"Your overall dice roll = "+overAllRoll+'\n');
							TimeUnit.MILLISECONDS.sleep(2000);
							Role theRole = currentPlayer.getRole();
							if (overAllRoll < budget) { //they failed
								board_view.setLog("Unfortunately, you did not roll at least a "+budget+'\n');
								if (theRole.isOnCard()) {
									board_view.appendLog("You do not win anything...");
								}
								else {
									board_view.appendLog("Because you're an Off-Card Actor, you win $1 !");
									currentPlayer.setCurrency(currentPlayer.getCurrency()+1);
								}
							}
							else {
								if (theRole.isOnCard()) {
									board_view.appendLog("You earned yourself 2 Fame!");
									currentPlayer.setFame(currentPlayer.getFame()+2);
								}
								else {
									board_view.appendLog("You earned yourself $1 and 1 Fame!");
									currentPlayer.setFame(currentPlayer.getFame()+1);
									currentPlayer.setCurrency(currentPlayer.getCurrency()+1);
								}
								currentPlayer.getLocation().addShot();
								board_view.addShot(currentPlayer.getLocation());
								int shots = currentPlayer.getLocation().getShotsTaken();
								//int currentDims = currentPlayer.getLocation().getTa
								//here need to add shot token in visual
							}
							TimeUnit.MILLISECONDS.sleep(2000);
							if (currentPlayer.getLocation().getShotsTaken() == currentPlayer.getLocation().getShotsMax()) {
								Scene wrapScene = currentPlayer.getLocation().getScene();
								game.WrapScene(currentPlayer.getLocation(),board_view);
								//check for bonus
								
								for (int j = 0; j < players.length; j++) {
								board_view.updateStats(players[j], j);
								}
								board_view.discardScene(wrapScene);
								//need to take players off scenes.
							}
							break;
						}
						if (action.equals("Rehearse")) {
							//currentPlayer.Rehearse();//effect endTurn = ...
							board_view.updateButtons(new boolean[] {false,false,false,false,false});
							int rehearsePoints = currentPlayer.getRehearsePoints();
							if (rehearsePoints < currentPlayer.getLocation().getScene().budget){
								currentPlayer.setRehearsePoints(rehearsePoints+1);
								System.out.println(currentPlayer.getName()+" now has "+rehearsePoints+" Rehearse Points");
								board_view.setLog(currentPlayer.getName()+" now has "+rehearsePoints+" Rehearse Points");

							} else {
								System.out.println("Unable to Rehearse anymore!"); //should never happen
							}
							break;
						}
						if (action.equals("Take Role")) {
							board_view.updateButtons(new boolean[] {false,false,false,false,false});
							ArrayList<Role> roles = currentPlayer.getLocation().getAllLotRoles(currentPlayer.getRank());
							String[] allRoles = new String[roles.size()];
							for (int j = 0; j < roles.size(); j++) {
								allRoles[j] = roles.get(j).getName();
							}
							board_view.showExtras(allRoles);
							boardListener = new BoardMouseListener();
							board_view.setLog("Which Role would you like to take?");
							String subAction = boardListener.getCommand();
							Role chosenRole = null;
							for (int j = 0; j < roles.size(); j++) {
								if (subAction.equals(roles.get(j).getName())) {
									chosenRole = roles.get(j);
								}
							}
							currentPlayer.setRole(chosenRole);
							board_view.updateStats(currentPlayer, index);

							//need to ask if they want to Act or Rehearse?
							board_view.setLog("Would you like to Act or Rehearse?"+'\n'+"Select Act, Rehearse, or Neither");
							boolean[] setButtons = {false, false, true, true, false};
							String[] neither = {"Neither"};
							board_view.updateButtons(setButtons);
							board_view.showExtras(neither);
							boardListener = new BoardMouseListener();
							subAction = boardListener.getCommand();
							//need to figure out input

							if (subAction.equals("Act")) {
								break;
							}
							else if (subAction.equals("Rehearse")) {
								int rehearsePoints = currentPlayer.getRehearsePoints();
								if (rehearsePoints < currentPlayer.getLocation().getScene().budget){
									currentPlayer.setRehearsePoints(rehearsePoints+1);
									System.out.println(currentPlayer.getName()+" now has "+rehearsePoints+" Rehearse Points");
									board_view.setLog(currentPlayer.getName()+" now has "+rehearsePoints+" Rehearse Points");
									TimeUnit.MILLISECONDS.sleep(1000);

								} else {
									System.out.println("Unable to Rehearse anymore!"); //should never happen
								}
								break;
							}
							else {
								break;
							}

						}
					}
					//End Loop 1
					String[] reset = new String[0];
					board_view.showExtras(reset);
					index++; //end of player's turn

				}
				board_view.setLog("The Day is Over"+'\n');
				TimeUnit.MILLISECONDS.sleep(100);
				
				if (i != numDays-1) { //doesn't cycle if last day
					board_view.appendLog("A New Day is Dawning...");
					TimeUnit.MILLISECONDS.sleep(100);
				game.CycleDay();
				//need to reset scenes, remove all shot tokens
				board_view.resetScenes();
				}
			}

			System.out.println("The game is over here are the results: ");
			board_view.setLog("The game is over, here are the results: "+'\n');
			TimeUnit.MILLISECONDS.sleep(100);

			ArrayList<Player> endGame = game.TallyScore();
			int count = 1;
			for(int i=0; i < endGame.size(); i++) {
				board_view.appendLog("#"+count+endGame.get(i).getName()+" Score: "+endGame.get(i).getScore()+'\n');
				count++;
				TimeUnit.MILLISECONDS.sleep(50);
			}
			TimeUnit.MILLISECONDS.sleep(100);
			System.out.println("Congratulations, "+endGame.get(0).getName()+"!!");
			board_view.appendLog("\n");
			board_view.appendLog("Congratulations, "+endGame.get(0).getName()+"!!");
			TimeUnit.MILLISECONDS.sleep(50);
			board_view.appendLog('\n'+"Would you like to play again?");
			

			System.out.println("Would you like to play again?)"); //need to make loop

			//String playAgain = console.next();
			String[] playAgain = {"Yes","No"};
			board_view.showExtras(playAgain);
			BoardMouseListener boardListener = new BoardMouseListener();
			String answer = boardListener.getCommand();

			if (answer.equals("No")) {
				board_view.setLog("Thanks for playing Deadwood!");
				TimeUnit.MILLISECONDS.sleep(2000);
				break;
			}
			String[] reset = new String[0];
			board_view.showExtras(reset);

		} // end while loop here
		board_view.dispose();
	}


	// getDeck
	// Preconditions:
	//		- File is the cards XML file
	// Postconditions:
	//		- Returns a deck object of all the scenes as a linked list
	//
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
    String filename = "";
    String scene_desc = "";
    String p_name;
    String p_line = "";
    int[] dims = null;
    int p_lvl;
    LinkedList<Scene> scenes = new LinkedList<Scene>();

    for (int i = 0; i < cardList.getLength(); i++) {

    	Node cardNode = cardList.item(i);
    	ArrayList<Role> roles = new ArrayList<Role>();

    	if (cardNode.getNodeType() == Node.ELEMENT_NODE) {
    		Element cardElement = (Element) cardNode;
    		name = cardElement.getAttribute("name");
    		budget = Integer.parseInt(cardElement.getAttribute("budget"));
    		filename = cardElement.getAttribute("img");
	    	NodeList pList = cardNode.getChildNodes();

	    	for (int p = 0; p < pList.getLength(); p++) {
	        	dims = new int[4];
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
		    						dims[0] = Integer.parseInt(qElement.getAttribute("x"));
		    						dims[1] = Integer.parseInt(qElement.getAttribute("y"));
		    						dims[2] = Integer.parseInt(qElement.getAttribute("h"));
		    						dims[3] = Integer.parseInt(qElement.getAttribute("w"));
		    						break;
		    					}
		    				}
		    			}
		    			Role newRole = new Role(p_name, p_line, p_lvl, true, dims);
		    			roles.add(newRole);
		    		}
	    		}
	    	}
    	}
    	Role[] parts = new Role[roles.size()];
    	parts = roles.toArray(parts);
    	Scene newScene = new Scene(name, scene_desc, budget, scene_num, parts, filename);
    	scenes.add(newScene);

    }
    Deck deck = new Deck(scenes);

		return deck;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	// getLocations
	// Preconditions:
	//		- File is the board XML file
	// Postconditions:
	//		- Returns an arraylist of location objects- all of them on the board
	//
	public static ArrayList<Location> getLocations(File input) {

		try {

		DocumentBuilderFactory dbFactory  = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

    Document doc = dBuilder.parse(input);
    doc.getDocumentElement().normalize();

  	ArrayList<Location> locations = new ArrayList<Location>();
  	NodeList setList = doc.getElementsByTagName("set");

  	String set_name;
  	String neighbor;
  	int takeNum = 0;
		int[][] takeDims = null;
  	Role[] parts = null;
  	String[] _neighbors = null;
		int p_lvl = 0;
		String p_name = "";
		String p_line = "";
    int[] dims = null;
    int[] area = null;

  	for (int i = 0; i < setList.getLength(); i++) {
  		area = new int[4];
  		takeNum = 0;
		takeDims = null;
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
							int col = 0;
  						for (int k = 0; k < takeList.getLength(); k++) {
  							// create take objects here (GUI)
  							Node takeNode = takeList.item(k);
  							if (takeNode.getNodeType() == Node.ELEMENT_NODE) {
									Element firstEl = (Element) takeNode;
									if (takeNum == 0){
										takeNum = Integer.parseInt(firstEl.getAttribute("number"));
										takeDims = new int[4][takeNum];
										col = takeNum - 1;
									// get area
									}
									NodeList takeList2 = takeNode.getChildNodes();
									for (int t = 0; t < takeList2.getLength(); t++) {
				    				Node takeNode2 = takeList2.item(t);
				    				if (takeNode2.getNodeType() == Node.ELEMENT_NODE) {
											Element takeElement = (Element) takeNode2;
											if (takeNode2.getNodeName() == "area"){
												takeDims[0][col] = Integer.parseInt(takeElement.getAttribute("x"));
				    						takeDims[1][col] = Integer.parseInt(takeElement.getAttribute("y"));
				    						takeDims[2][col] = Integer.parseInt(takeElement.getAttribute("h"));
				    						takeDims[3][col] = Integer.parseInt(takeElement.getAttribute("w"));
												col -= 1;
											}
										}
									}
  							}
  						}
  						break;
  					case "parts":
  						NodeList partsList = tNode.getChildNodes();
  						for (int p = 0; p < partsList.getLength(); p++) {
  							dims = new int[4];
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
  				    						dims[0] = Integer.parseInt(qElement.getAttribute("x"));
  				    						dims[1] = Integer.parseInt(qElement.getAttribute("y"));
  				    						dims[2] = Integer.parseInt(qElement.getAttribute("h"));
  				    						dims[3] = Integer.parseInt(qElement.getAttribute("w"));
  				    						break;
  				    					}
  				    				}
  								}
    							Role newRole = new Role(p_name, p_line, p_lvl, false, dims);
    							roles.add(newRole);
  							}
  						}
							break;
  					case "area":
  							Element ffElement = (Element) tNode;
								area[0] = Integer.parseInt(ffElement.getAttribute("x"));
								area[1] = Integer.parseInt(ffElement.getAttribute("y"));
								area[2] = Integer.parseInt(ffElement.getAttribute("h"));
								area[3] = Integer.parseInt(ffElement.getAttribute("w"));
								break;
  					}
  				}
				parts = new Role[roles.size()];
		    parts = roles.toArray(parts);
  			}
  			Location newLocation = new Location(set_name, takeNum, 0, null, parts, area, takeDims);
  			newLocation.setNeighbors(_neighbors);
  			locations.add(newLocation);
  		}
  	} // end of for loop

  	area = new int[4];
  	// TRAILER ----------------------------------------------------------------->
  	NodeList trailer = doc.getElementsByTagName("trailer");
  	Node trailerNode = trailer.item(0);
  	ArrayList<String> neighbors = new ArrayList<String>();
  	_neighbors = null;

  	NodeList neighborList = trailerNode.getChildNodes();
  	for (int temp = 0; temp < neighborList.getLength(); temp++) {
  		Node _nNode = neighborList.item(temp);
  		if (_nNode.getNodeType() == Node.ELEMENT_NODE) {

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
					break;
				case "area":
					Element aElement = (Element) _nNode;
					area[0] = Integer.parseInt(aElement.getAttribute("x"));
					area[1] = Integer.parseInt(aElement.getAttribute("y"));
					area[2] = Integer.parseInt(aElement.getAttribute("h"));
					area[3] = Integer.parseInt(aElement.getAttribute("w"));
				}
			}
		}
  	_neighbors = new String[neighbors.size()];
		_neighbors = neighbors.toArray(_neighbors);

  	Location newLocation = new Location("trailer", area);
  	newLocation.setNeighbors(_neighbors);
  	locations.add(newLocation);

  	area = new int[4];
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
				case "area":
					Element aElement = (Element) oNode;
					area[0] = Integer.parseInt(aElement.getAttribute("x"));
					area[1] = Integer.parseInt(aElement.getAttribute("y"));
					area[2] = Integer.parseInt(aElement.getAttribute("h"));
					area[3] = Integer.parseInt(aElement.getAttribute("w"));
					break;
  			}
  		}
  	}

  	_neighbors = new String[neighbors.size()];
		_neighbors = neighbors.toArray(_neighbors);

		newLocation = new Location("office", area);
  	newLocation.setNeighbors(_neighbors);
  	locations.add(newLocation);

  	return locations;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
