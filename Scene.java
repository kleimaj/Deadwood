/* 
 * Scene.java
 * 
 * Contributors: Jacob Kleiman, Eric Eagan, Ryan McGinnis
 * November 2017
 */
import java.util.*;
import java.io.*;


public class Scene {
	
	// Attributes
	
	String name;				// Name of the scene (card)
	String description;			// Description of the scene
	int budget;					// Budget (requirement) of the scene
	Role[] onCard;				// An array of roles on the card
	boolean isWrappedUp;		//flag notifying if scene is wrapped up
	
	// Constructor
	
	// Scene
	// Preconditions:
	//
	//
	// Postconditions:
	//
	//
	// Notes:
	//
	//
	public Scene(String name, String desc, int budget, Role[] onCard) {
		this.name = name;
		this.description = desc;
		this.budget = budget;
		this.onCard = onCard;
		isWrappedUp = false;
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
		
	// getDesc
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- Returns description
	public String getDesc() {
		return description;
	}
		
	// getBudget
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- Returns budget
	public int getBudget() {
		return budget;
	}
	
	// isWrappedUp
	// Preconditions:
	// 		-none
	// Postconditions:
	//		-returns attribute isWrappedUp
	public boolean isWrappedUp() {
		return isWrappedUp();
	}
	
	// wrapUp
	// Preconditions:
	// 		-none
	// Postconditions:
	//		-sets boolean isWrappedUp to true
	public void wrapUp() {
		isWrappedUp = true;
	}
	
	// unWrap
	// Preconditions:
	// 		-none
	// Postconditions:
	//		-sets isWrappedUp to false
	public void unWrap() {
		isWrappedUp = false;
	}
	
	// getAllRoles
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- Returns an array of all roles
	public Role[] getAllRoles() {
		return onCard;
	}
	
	// getAvailableRoles
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- Returns a linked list of available roles
	public ArrayList<Role> getAvailableRoles() {
		ArrayList<Role> roles = new ArrayList<Role>();
		for (int i = 0; i < onCard.length; i++) {
			 if (onCard[i].isTaken() == false) {
				 roles.add(onCard[i]);
			 }
		}
		return roles;

	}
}
