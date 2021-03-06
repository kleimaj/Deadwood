/* 
 * Board.java
 *
 * Contributors: Jacob Kleiman, Eric Eagan, Ryan McGinnis
 * November 2017
 */
import java.util.*;
import java.util.concurrent.TimeUnit;



public class Board {

	// Attributes

	//int dayCounter;				// The number of days played in the game
	Location[] locations;		// Array of all locations on the board
	Deck deck;					// Deck that contains the discard pile and active pile
	ArrayList<Player> players;			// Array of all active players. SORTED IN TURN ORDER.

	// Constructor

	// Board
	// Preconditions:
	//		- XML parse is complete
	//
	// Postconditions:
	//		- Game is ready to begin
	//
	public Board(Location[] newLocations, Deck newDeck) { //my need to get rid of the Player[] parameter
	//	dayCounter = 0;
		locations = newLocations;
		deck = newDeck;
		players = new ArrayList<Player>(); //set players to null?
	}

	// CycleDay
	// Preconditions:
	//		- The day is over
	//
	// Postconditions:
	//		- Players are moved to Trailer
	//		- Scenes are reset
	//
	public void CycleDay() throws InterruptedException {

		System.out.println("The day is over");
		// need to get trailer location
		Location trailer = null;

		for (int i = 0; i < locations.length; i++) {
			if (locations[i].getName().equals("trailer")) { 
				trailer = locations[i];
			}
			/*if (locations[i].isLot()) { // reset scenes
				deck.discard(locations[i].getScene()); // discards currentScene in Location
				locations[i].setScene(deck.draw());// sets a new random scene in Location
				locations[i].resetShots();
				locations[i].getScene().unWrap();
			}*/
		}

		// change all player locations to trailer
		for (int i = 0; i < players.size(); i++) {
			players.get(i).setLocation(trailer);
		}

		//UpdateDayCount();
		System.out.println("A new day is dawning...");
		//TimeUnit.SECONDS.sleep(2);

	}



	// WrapScene
	// Preconditions:
	//		- parameter givenLocation is the location of the wrapped scene
	//		- shotMax == shotsTaken
	// Postconditions:
	//		- scene attribute isWrappedUp is set to true;
	//		- players in scene leave their roles, roles attribute isTaken set to false
	// 		- if isEndDay(), call cycleDay()
	//		- shot markers are reset?
	// Notes:
	// 		- will call Bonus if anyone is on-card
	//
	public void WrapScene(Location givenLocation,Visual visual) throws InterruptedException {

		// check if any player is onCard
		Scene theScene = givenLocation.getScene();
		Role[] OnCards = theScene.getAllRoles();
		boolean bonus = false;

		for (int i = 0; i < OnCards.length; i++) {
			if (OnCards[i].isTaken()) {
				bonus = true;
				break;
			}
		}
		if (bonus == true) {
			Bonus(givenLocation,theScene,visual);
		}

		// wrap the scene
		theScene.wrapUp();

		// take all players in this scene out of their roles
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getLocation().getName().equals(givenLocation.getName()) && players.get(i).isInRole()) {
				players.get(i).getRole().notTaken();
				players.get(i).setRole(null);
				players.get(i).resetRehearsePoints();
			}
		}

		if (isEndDay()==false) {
		System.out.println("Scene is Over! All Players participating in '"+ theScene.getName()+ "' must Move!");
		visual.setLog("Scene is Over! All Players participating in '"+ theScene.getName()+ "' must Move!");
		TimeUnit.SECONDS.sleep(2);

		} else {
			CycleDay();
		}
	}
	//adds player to players[]
	public void addPlayer(Player player) {
		//Player[] newPlayers = new Player[players.size()+1];

		players.add(player);
	}

	public Player[] getPlayers() {
		Player[] returnPlayers = new Player[players.size()];
		for (int i = 0; i < players.size(); i++) {
			returnPlayers[i] = players.get(i);
		}
		return returnPlayers;
	}

	public Location getTrailer() {
		Location trailer = null;
		for (int i = 0; i < locations.length; i++) {
			//System.out.println(locations[i].getName());
			if (locations[i].getName().equals("trailer")) {
				trailer = locations[i];
				break;
			}
		}
		return trailer;
	}
	
	public Location getLocation(String name) {
		Location location = null;
		for (int i = 0; i < locations.length; i++) {
			if (locations[i].getName().equals(name)) {
				location = locations[i];
				break;
			}
		}
		return location;
	}


	//returns true if all scenes are wrapped
	public boolean isEndDay() {
		for (int i = 0 ; i < locations.length; i++) {
			//System.out.println(locations[i].getScene().getName());
			if (locations[i].isLot() == true && locations[i].getScene().isWrappedUp() == false) {
				return false;
			}
		}
		return true;
	}

	// Bonus
	// Preconditions:
	//		- at least one player is onCard
	//
	// Postconditions:
	//		- players receive bonus payout
	//
	private void Bonus(Location givenLocation,Scene theScene,Visual visual ) throws InterruptedException {

		System.out.println("Bonus Time!");
		visual.setLog("Bonus Time!"+'\n');
		TimeUnit.SECONDS.sleep(1);

		// oncard player payout
		int budget = theScene.getBudget();
		int diceRolls[] = new int[budget];

		for (int i = 0; i <diceRolls.length; i++) {
			diceRolls[i] = 1+(int)(6*Math.random());
		}
		Arrays.sort(diceRolls); // should be sorted now.
		Role[] onCard = theScene.getAllRoles();
		Player[] onActors = new Player[onCard.length];
		for (int i = 0; i < onCard.length; i++) {

			for (int j = 0; j < players.size(); j++) {
				if (players.get(j).isInRole()) {
					if(players.get(j).getRole() == onCard[i]) {
						onActors[i] = players.get(j);
						break;
					}

				}
			}

		}
//		int[] payouts = new int[onActors.length];
//		for(int i = 0; i < payouts.length; i++) {
//			payouts[i] = 0;
//		}

		//now have array of dice rolls and array of players in roles
		int count = onActors.length - 1;
		for (int i = diceRolls.length -1; i >=0; i--) {
			if(onActors[count] != null) {
				onActors[count].setCurrency(onActors[count].getCurrency()+diceRolls[i]);
				visual.appendLog(onActors[count].getName()+" earns a bonus of $"+diceRolls[i]+'\n');
				TimeUnit.SECONDS.sleep(1);
			}

			count--;
			if (count == -1) {
				count = onActors.length -1;
			}
		}
		for (int i = 0; i < onActors.length; i++) {
			if (onActors[i] != null) {
				System.out.println(onActors[i].getName()+" now has currency of $"+onActors[i].getCurrency());
				visual.appendLog(onActors[i].getName()+" now has currency of $"+onActors[i].getCurrency()+'\n');
				TimeUnit.SECONDS.sleep(1);

			}
		}

		// offcard player payout (windfall bonus)
		Role[] offCard = givenLocation.getAllRoles();

		// iterate through off card roles
		for (int i = 0; i < offCard.length; i++) {
			for (int j = 0; j < players.size(); j++) {
				// if one of these roles is equal to a player's, pay them
				if (players.get(j).isInRole()) {
					if (players.get(j).getRole().getName().equals(offCard[i].getName())) {
						int payout = offCard[i].getRank();
						System.out.println(players.get(j).getName()+" earns a bonus of $"+payout);
						visual.appendLog(players.get(j).getName()+" earns a bonus of $"+payout+'\n');
						TimeUnit.SECONDS.sleep(1);
						players.get(j).setCurrency(payout+players.get(j).getCurrency());
						visual.appendLog(players.get(j).getName()+" now has a currency of $"+players.get(j).getCurrency()+'\n');
						System.out.println(players.get(j).getName()+" now has a currency of $"+players.get(j).getCurrency());
						TimeUnit.SECONDS.sleep(1);
	
					}
				}
			}
		}

	}

	// Tally Score
	// Preconditions:
	// 		- end of game
	//
	// Postconditions:
	// 		- returns game winner
	// Notes:
	//		- if there is a tie, winner goes to player who had the latest turn
	//
	public ArrayList<Player> TallyScore() {

		for (int i = 0; i < players.size(); i++) {
			players.get(i).setScore();
		}

		Player winner = players.get(0);

		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getScore() >= winner.getScore()) {
				winner = players.get(i);
			}
		}
		Collections.sort(players);
		int count = 1;
		for (int i = 0; i < players.size(); i++) {
			System.out.println("#"+count+" "+players.get(i).getName()+" Score: "+players.get(i).getScore());
			count++;
		}

		return players;
	}

}
