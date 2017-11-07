/* 
 * Location.java
 * 
 * Contributors: Jacob Kleiman, Eric Eagan, Ryan McGinnis
 * November 2017
 */
import java.util.*;
import java.io.*;


public class Location {
	
	// Attributes 
	
	String name;				// (All Locations) Name of the location - ie. church, lot, hotel...
	Location[] neighbors;		// (All Locations) Array of neighbors
	boolean isLot;				// (All locations) 1 if the location's name == lot

	int ShotMax;				// (Lot only)
	int ShotsTaken;				// (Lot only)
	Scene currentScene;			// (Lot only)
	Role[] offCard;				// (Lot only)
	
	// Constructors
	
	// Location
	// Preconditions:
	//		- Location is not a lot
	//		- (i.e Trailier, Casting office)
	// Postconditions:
	//		- Name is assigned, neighbors are found? and assigned
	//		- isLot is false
	// Notes:
	//
	//
	public Location(String name){
		this.name = name;
		//this.neighbors = neighbors;
		isLot = false;
	}
	
	// Location
	// Preconditions:
	//		- Location is a lot
	//		
	// Postconditions:
	//		- 
	//		- isLot is true
	// Notes:
	//
	//
	public Location(String name, int shotMax,int shotsTaken, Scene currScene, Role[] offCard) {
		this.name = name;
		//this.neighbors = neighbors;
		ShotMax = shotMax;
		ShotsTaken=shotsTaken;
		currentScene = currScene;
		this.offCard = offCard;
		isLot = true;
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
	//		- Returns ShotsTaken
	public int getShotsTaken() {
		return ShotsTaken;
	}
	
	// getShotsMax
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- Returns ShotMax
	public int getShotsMax() {
		return ShotMax;
	}
	
	// getName
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- Returns name
	public String getName() {
		return name;
	}
	
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
	//		- Returns neighbors
	public Location[] getNeighbors() {
		return neighbors;
	}
	
	// getScene
		// Preconditions:
		//		- none	
		// Postconditions:
		//		- Returns currentScene
	public Scene getScene() {
		return currentScene;
	}
	
	/*
	public Role[] getRoles() {
		if (this.isLot == true) {
			
		}
		else {
			
		}
	}*/
	
	// addShot
	// Preconditions:
	//
	//
	// Postconditions:
	//
	//
	// Notes:
	//
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
	public void setNeighbors(Location[] neighbors) {
		this.neighbors = neighbors;
	}
}

