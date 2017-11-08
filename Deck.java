/* 
 * Deck.java
 * 
 * Contributors: Jacob Kleiman, Eric Eagan, Ryan McGinnis
 * November 2017
 */
import java.util.*;
import java.io.*;


public class Deck {
	
	// Attributes
	
	LinkedList<Scene> deck = new LinkedList<Scene>();			// The active deck as a linked list of scene objects
	LinkedList<Scene> discardDeck = new LinkedList<Scene>();	// The discard deck of used (inactive) scene objects
	
	// Constructor
	
	// Deck
	// Preconditions:
	//
	//
	// Postconditions:
	//
	//
	// Notes:
	//
	//
	public Deck(LinkedList<Scene> cards) {
		deck = cards;
	}
	
	// Draw
	// Preconditions:
	//
	//
	// Postconditions:
	//
	//
	// Notes:
	//
	//
	public Scene draw() {
		return deck.remove((int)(deck.size()*Math.random()));
	}
	
	// resetDeck
	// Preconditions:
	//
	//
	// Postconditions:
	//
	//
	// Notes:
	//
	//
	public void resetDeck() {
		while(discardDeck.size() != 0) {
			deck.add(discardDeck.removeFirst());
		}
	}
	
	// Shuffle
	// Preconditions:
	//
	//
	// Postconditions:
	//
//	//
//	// Notes:
//	//
//	//
//	public void shuffle() {
//		while(discardDeck.size() != 0){
//			//get a random number between 0 and 1 - the number of cards in deck
//			int randomNumber = (int)(discardDeck.size()*Math.random());
//			deck.add(discardDeck.remove(randomNumber));
//		
//		}
//	}
	
	// Discard
	// Preconditions:
	//
	//
	// Postconditions:
	//
	//
	// Notes:
	//
	//
	public void discard(Scene newScene) {
		discardDeck.add(newScene);
	}
}
