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
	int dims[];						// area of the location: x, y, h, w


	int shotMax;				// (Lot only) Total amount of shot markers
	int takeDims[][];			// (Lot only) Area of the shot markers, in order
	int shotsTaken;				// (Lot only) Amount of used shot markers 
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
	public Location(String name, int[] dims){
		this.name = name;
		isLot = false;
		this.dims = dims;
	}

	// Location
	// Preconditions:
	//		- location is a lot
	//		- location is created during the XML parse
	// Postconditions:
	//		- isLot is true
	//
	public Location(String name, int shotMax,int shotsTaken, Scene currScene, Role[] offCard, int[] dims, int[][] takeDims) {
		this.name = name;
		this.shotMax = shotMax;
		this.shotsTaken=shotsTaken;
		currentScene = currScene;
		this.offCard = offCard;
		isLot = true;
		this.dims = dims;
		this.takeDims = takeDims;
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
		return shotsTaken;
	}

	// getShotsMax
	// Preconditions:
	//		- none
	// Postconditions:
	//		- returns ShotMax
	public int getShotsMax() {
		return shotMax;
	}

	public void resetShots() {
		shotsTaken = 0;
	}

	// getName
	// Preconditions:
	//		- none
	// Postconditions:
	//		- returns name
	public String getName() {
		return name;
	}
	
	public int[] getDims() {
		return dims;
	}

	// isWrappedUp
	// Preconditions:
	//		- none
	// PostConditions:
	//		-returns true if scene is wrapped up, false if not
	public boolean isWrappedUp() {
		if (isLot) {
			if (shotMax == shotsTaken) {
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
	//will return all roles that are not taken and that are of equal or less rank than rank
	public ArrayList<Role> getAllLotRoles(int rank){
		ArrayList<Role> returnedRoles = new ArrayList<Role>();
		ArrayList<Role> offCardRoles = this.getRoles();
		ArrayList<Role> onCard = this.currentScene.getAvailableRoles();

		offCardRoles.addAll(onCard);
		
		for(int i = 0; i < offCardRoles.size(); i++) {
			if (offCardRoles.get(i).getRank() <= rank) {
				returnedRoles.add(offCardRoles.get(i));
			}
		}
		return returnedRoles;
	}

	// addShot
	// Preconditions:
	//		- player wants to add a shot
	// Postconditions:
	//		- shot is added
	//
	public void addShot() {
		shotsTaken+=1;
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
