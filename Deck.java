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
	public Deck() {
		
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
		return deck.removeFirst();
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
	//
	// Notes:
	//
	//
	public void shuffle() {
		
	}
	
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
