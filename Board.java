/* 
 * Board.java
 * 
 * Contributors: Jacob Kleiman, Eric Eagan, Ryan McGinnis
 * November 2017
 */
import java.util.*;
import java.io.*;


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
		
	}
	
	// WrapScene
	// Preconditions:
	//
	//
	// Postconditions:
	//
	//
	// Notes:
	// will call Bonus if anyone is on-card
	//
	public void WrapScene() {
		
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
	private void resetScenes() {
		
	}
	
	// Update Day Count
	// Preconditions:
	//
	//
	// Postconditions:
	//
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
	private void moveToTrailers() {
		
	}
	
	// Bonus
	// Preconditions:
	//
	//
	// Postconditions:
	//
	//
	// Notes:
	// maybe should be private
	//
	public void Bonus() {
		
	}
	
	// Tally Score
	// Preconditions:
	//
	//
	// Postconditions:
	//
	//
	// Notes:
	//
	//
	public void TallyScore() {
		
	}

}
