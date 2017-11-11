/* 
 * Board.java
 * 
 * Contributors: Jacob Kleiman, Eric Eagan, Ryan McGinnis
 * November 2017
 */
import java.util.*;


public class Board {
	
	// Attributes
	
	int dayCounter;				// The number of days played in the game
	Location[] locations;		// Array of all locations on the board
	Deck deck;					// Deck that contains the discard pile and active pile
	Player[] players;			// Array of all active players
	
	// Constructor
	
	// Board
	// Preconditions:
	//
	//
	// Postconditions:
	//
	//
	// Notes:
	//
	//
	public Board(Location[] newLocations, Deck newDeck, Player[] newPlayers) {
		dayCounter = 0;
		locations = newLocations;
		deck = newDeck;
		players = newPlayers;
	}
	
	// CycleDay
	// Preconditions:
	//		- The day is over
	//
	// Postconditions:
	//		- Players are moved to Trailer
	//		- Scenes are reset
	//		-
	// Notes:
	//
	//
	public void CycleDay() {
		System.out.println("The day is over");
		//need to get trailer location
		Location castingOffice = null;
		for (int i = 0; i < locations.length; i++) {
			if (locations[i].getName().equals("Casting Office")) { //may need to change this after parsing xml
				castingOffice = locations[i];
			}
			if (locations[i].isLot()) { //reset scenes
				deck.discard(locations[i].getScene()); //discards currentScene in Location
				locations[i].setScene(deck.draw()); //sets a new random scene in Location
			}
		}
		for (int i = 0; i < players.length; i++) { //changes all player locations to casting office
			players[i].setLocation(castingOffice);
		}
		UpdateDayCount(); //increments daycount
		System.out.println("A new day is dawning...");
	}
	//returns true if all scenes are wrapped
	public boolean isEndDay() {
		for (int i = 0 ; i < locations.length; i++) {
			if (locations[i].getScene().isWrappedUp()==false) {
				return false;
			}
		}
		return true;
	}
	
	// WrapScene
	// Preconditions:
	//		-parameter givenLocation is the location of the wrapped scene
	//		- shotMax == shotsTaken
	// Postconditions:
	//		-scene attribute isWrappedUp is set to true;
	//		-players in scene leave their roles, roles attribute isTaken set to false
	// 		-if isEndDay(), call cycleDay()
	// Notes:
	// will call Bonus if anyone is on-card
	//
	public void WrapScene(Location givenLocation) {
		//check if any player is onCard
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
			//call bonus
			Bonus(givenLocation,theScene);
		}
		//wrap the scene
		theScene.wrapUp();
		
		//take all players in this scene out of their roles
		for (int i = 0; i < players.length; i++) {
			if (players[i].getLocation().getName().equals(givenLocation.getName()) && players[i].isInRole()) {
				players[i].getRole().notTaken();
				players[i].setRole(null);
			}
		}
		if (isEndDay()==false) {
		System.out.println("Scene is Over! All Players participating in "+ theScene.getName()+ " must Move!");
		}
		else {
			CycleDay();
		}
	}
	// ResetScenes
	// Preconditions:
	//
	//
	// Postconditions:
	//
	//
	// Notes:
	//		- Will be called in Cycle Day
	//
//	private void resetScenes() {
//		
//	}
//	
	// Update Day Count
	// Preconditions:
	//
	//
	// Postconditions:
	// dayCounter is incremented by 1
	//
	// Notes:
	//
	//
	private void UpdateDayCount() {
		dayCounter++;
	}
	
	// Move to Trailers
	// Preconditions:
	//		- The day has ended
	//
	// Postconditions:
	//		- All players are moved to the Trailer location
	//
	// Notes:
	//
	//
//	private void moveToTrailers() {
//		
//	}
	
	// Bonus
	// Preconditions:
	//		-at least one player is onCard
	//
	// Postconditions:
	//		-players receive bonus payout
	//
	// Notes:
	// maybe should be private
	//
	private void Bonus(Location givenLocation,Scene theScene ) {
		System.out.println("Bonus Time!");
		//oncard player payout
		int budget = theScene.getBudget();
		int diceRolls[] = new int[budget];
		for (int i = 0; i <diceRolls.length; i++) {
			diceRolls[i] = 1+(int)(6*Math.random());
		}
		Arrays.sort(diceRolls); //should be sorted now.
		Role[] onCard = theScene.getAllRoles();
		
		//offcard player payout (windfall bonus)
		Role[] offCard = givenLocation.getAllRoles();
		for (int i = 0; i < offCard.length; i++) { //iterate through off card roles
			for (int j = 0; j < players.length; j++) { //if one of these roles is equal to a player's, pay them
				if (players[j].getRole().getName().equals(offCard[i].getName())) {
					int payout = offCard[i].getRank();
					System.out.println(players[j].getName()+" earns a bonus of $"+payout);
					players[j].setCurrency(payout+players[j].getCurrency());
					System.out.println(players[j].getName()+" now has a currency of $"+players[j].getCurrency());
				}
			}
		}
		
	}
	
	// Tally Score
	// Preconditions:
	// end of game
	//
	// Postconditions:
	// returns game winner
	//
	// Notes:
	//
	//
	public Player TallyScore() {
		
	}

}
