/* 
 * Location.java
 * 
 * Contributors: Jacob Kleiman, Eric Eagan, Ryan McGinnis
 * November 2017
 */
import java.util.*;


public class Location {
	
	// Attributes 
	
	String name;				// (All Locations) Name of the location - ie. church, lot, hotel...
	String[] neighbors;		// (All Locations) Array of neighbors
	boolean isLot;				// (All locations) 1 if the location's name == lot
	int dims[];

	int ShotMax;				// (Lot only)
	int ShotsTaken;				// (Lot only)
	Scene currentScene;			// (Lot only)
	Role[] offCard;				// (Lot only)
	
	// Constructors
	
	// Location
	// Preconditions:
	//		- location is not a lot
	//		 (i.e Trailier, Casting office)
	//		- location is created during the XML parse
	// Postconditions:
	//		- name is assigned, neighbors are found? and assigned
	//		- isLot is false
	// 
	public Location(String name){
		this.name = name;
		isLot = false;
	}
	
	// Location
	// Preconditions:
	//		- location is a lot
	//		- location is created during the XML parse
	// Postconditions:
	//		- isLot is true
	// 
	public Location(String name, int shotMax,int shotsTaken, Scene currScene, Role[] offCard, int[] dims) {
		this.name = name;
		ShotMax = shotMax;
		ShotsTaken=shotsTaken;
		currentScene = currScene;
		this.offCard = offCard;
		isLot = true;
		this.dims = dims;
	}
	
	// Accessors
	
	// isLot
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- Returns isLot
	public boolean isLot() {
		return isLot;
	}
	
	// getShotsTaken
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- returns ShotsTaken
	public int getShotsTaken() {
		return ShotsTaken;
	}
	
	// getShotsMax
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- returns ShotMax
	public int getShotsMax() {
		return ShotMax;
	}
	
	public void resetShots() {
		ShotsTaken = 0;
	}
	
	// getName
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- returns name
	public String getName() {
		return name;
	}
	
	// isWrappedUp
	// Preconditions:
	//		- none
	// PostConditions:
	//		-returns true if scene is wrapped up, false if not
	public boolean isWrappedUp() {
		if (isLot) {
			if (ShotMax == ShotsTaken) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	// getNeighbors
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- returns neighbors
	public String[] getNeighbors() {
		return neighbors;
	}
	
    // getScene
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- returns currentScene
	public Scene getScene() {
		return currentScene;
	}
	// setScene
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- sets CurrentScene to a new scene
	public void setScene(Scene newScene) {
		currentScene = newScene;
	}
	
	// getAllRoles
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- returns all offCard roles in this location
	public Role[] getAllRoles() {
		return offCard;
	}
	
	// getRoles
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- returns all available offCard roles
	public ArrayList<Role> getRoles() {
		if (this.isLot == true) {
			ArrayList<Role> roles = new ArrayList<Role>();
			for (int i = 0; i < offCard.length; i++) {
				 if (offCard[i].isTaken() == false) {
					 roles.add(offCard[i]);
				 }
			 }
			 return roles;
		}
		else {
			System.out.println("This should never happen");
			return null;
		}
	}
	
	// addShot
	// Preconditions:
	//		- player wants to add a shot
	// Postconditions:
	//		- shot is added
	//
	public void addShot() {
		ShotsTaken+=1;
	}
	
	// setNeighbors
	// Preconditions:
	//
	//
	// Postconditions:
	//
	//
	// Notes:
	//
	//
	public void setNeighbors(String[] neighbors) {
		this.neighbors = neighbors;
	}
}

