/* 
 * Role.java
 * 
 * Contributors: Jacob Kleiman, Eric Eagan, Ryan McGinnis
 * November 2017
 */
import java.util.*;


public class Role {
	
	// Attributes
	
	String name;			// Name of the role
	String dialogue;		// Dialogue on the role
	int rank;				// Rank
	boolean isTaken;		// Returns true if the role is taken, else returns false
	boolean OnCard;			// Returns true if the role is a "On-Card" Role
	int[] dims;
	
	// Constructor
	
	// Role
	// Preconditions:
	//		- roles are created during XML parse
	//
	// Postconditions:
	//		- onCard is true if role is from a scene
	//		- names, dialogue, and ranks are set
	//		- by default a role is not taken
	//
	public Role(String name, String dialogue, int rank, boolean onCard, int[] dims) {
		this.name = name;
		this.dialogue = dialogue;
		this.rank = rank;
		isTaken = false;
		this.OnCard = onCard;
		this.dims = dims;
	}
	
	// Accessors
	
	// getName
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- returns name
	public String getName() {
		return name;
	}
	
	// getDialogue
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- returns dialogue
	public String getDialogue() {
		return dialogue;
	}
	
	// getRank
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- returns rank
	public int getRank() {
		return rank;
	}
	
	// isTaken
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- returns true or false
	public boolean isTaken() {
		return isTaken;
	}
	
	// isOnCard
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- returns true if the role is a on-card role, false if not
	public boolean isOnCard() {
		return OnCard;
	}
	
	// roleTaken
	// Preconditions:
	//		- none
	// Postconditions:
	//		- returns attribute isTaken
	public void roleTaken() {
		isTaken = true;
	}
	
	// notTaken
	// Preconditions:
	//		- none
	// Postconditions:
	//		- sets variable isTaken to false
	public void notTaken() {
		isTaken = false;
	}
}

