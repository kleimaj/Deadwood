/* 
 * Scene.java
 * 
 * Contributors: Jacob Kleiman, Eric Eagan, Ryan McGinnis
 * November 2017
 */
import java.util.*;



public class Scene {
	
	// Attributes
	
	String name;				// Name of the scene (card)
	String description;			// Description of the scene
	int budget;					// Budget (requirement) of the scene
	int number;					// Number displayed on the card
	Role[] cardRoles;			// An array of roles on the card
	boolean isWrappedUp;		// Flag notifying if scene is wrapped up
	String filename;
	 
	// Constructor
	
	// Scene
	// Preconditions:
	//		- scenes are created during XML parse
	//
	// Postconditions:
	//		- the scenes are composed of roles which are also created during the XML parse
	//		- by default the scene is not wrapped up
	//
	public Scene(String name, String desc, int budget, int number, Role[] cardRoles, String filename) {
		this.name = name;
		this.description = desc;
		this.budget = budget;
		this.number = number;
		this.cardRoles = cardRoles;
		isWrappedUp = false;
		this.filename = filename;
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
		
	// getDesc
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- returns description
	public String getDesc() {
		return description;
	}
		
	// getBudget
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- returns budget
	public int getBudget() {
		return budget;
	}
	
	public int getNumber() {
		return number;
	}
	
	// isWrappedUp
	// Preconditions:
	// 		- none
	// Postconditions:
	//		- returns attribute isWrappedUp
	public boolean isWrappedUp() {
		return isWrappedUp;
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
	// 		- none
	// Postconditions:
	//		- sets isWrappedUp to false
	public void unWrap() {
		isWrappedUp = false;
	}
	
	// getAllRoles
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- returns an array of all roles
	public Role[] getAllRoles() {
		return cardRoles;
	}
	
	// getAvailableRoles
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- returns a linked list of available roles
	public ArrayList<Role> getAvailableRoles() {
		ArrayList<Role> roles = new ArrayList<Role>();
		for (int i = 0; i < cardRoles.length; i++) {
			 if (cardRoles[i].isTaken() == false) {
				 roles.add(cardRoles[i]);
			 }
		}
		return roles;
	}
}
