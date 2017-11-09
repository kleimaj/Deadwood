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
