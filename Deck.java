import java.util.*;
public class Deck {
//attributes
	LinkedList<Scene> deck = new LinkedList<Scene>();
	LinkedList<Scene> discardDeck = new LinkedList<Scene>();
	
//Deck Constructor
	public Deck() {
		
	}
	
//Methods
	public Scene draw() {
		return deck.removeFirst();
	}
	public void resetDeck() {
		while(discardDeck.size() != 0) {
			deck.add(discardDeck.removeFirst());
		}
	}
	public void shuffle() {
		
	}
	public void discard(Scene newScene) {
		discardDeck.add(newScene);
	}
}
