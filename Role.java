/* 
 * Role.java
 * 
 * Contributors: Jacob Kleiman, Eric Eagan, Ryan McGinnis
 * November 2017
 */
import java.util.*;
import java.io.*;


public class Role {
	
	// Attributes
	
	String name;			// Name of the role
	String dialogue;		// Dialogue on the role
	int rank;				// Rank
	boolean isTaken;		// Returns true if the role is taken, else returns false
	boolean OnCard;			//Returns true if the role is a "On-Card" Role
	
	// Constructor
	
	// Role
	// Preconditions:
	//
	//
	// Postconditions:
	//
	//
	// Notes:
	//
	//
	public Role(String name,String dialogue,int rank) {
		this.name = name;
		this.dialogue = dialogue;
		this.rank = rank;
		isTaken = false;
	}
	
	// Accessors
	
	// getName
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- Returns name
	public String getName() {
		return name;
	}
	
	// getDialogue
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- Returns dialogue
	public String getDialogue() {
		return dialogue;
	}
	
	// getRank
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- Returns rank
	public int getRank() {
		return rank;
	}
	
	// isTaken
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- Returns true or false
	public boolean isTaken() {
		return isTaken;
	}
	
	public boolean isOnCard() {
		return OnCard;
	}
	
	// Setters
	
	// roleTaken
	// Preconditions:
	//
	//
	// Postconditions:
	//
	//
	// Notes:
	//
	//
	public void roleTaken() {
		isTaken = true;
	}
	
	// notTaken
	// Preconditions:
	//
	//
	// Postconditions:
	//
	//
	// Notes:
	//
	//
	public void notTaken() {
		isTaken = false;
	}
}

