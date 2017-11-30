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
	//		- scenes (cards) have been created from the XML parse
	//
	// Postconditions:
	//		- the array of cards are assigned as the deck
	//
	public Deck(LinkedList<Scene> cards) {
		deck = cards;
	}

	// Draw
	// Preconditions:
	//		- lot needs a new scene
	// Postconditions:
	//		- the top card is removed and returned
	//
	public Scene draw() {
		return deck.remove((int)(deck.size()*Math.random()));
	}

	// resetDeck
	// Preconditions:
	//		- deck has run out
	//
	// Postconditions:
	//		- discard deck is emptied
	//		- regular deck is filled
	//
	public void resetDeck() {
		while(discardDeck.size() != 0) {
			deck.add(discardDeck.removeFirst());
		}
		for (int i = 0; i < deck.size(); i++) { //unwraps all scenes
			deck.get(i).unWrap();
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
	//		- scene is wrapped
	//
	// Postconditions:
	//		- scene is added to the discard deck
	//
	public void discard(Scene newScene) {
		discardDeck.add(newScene);
	}
}
