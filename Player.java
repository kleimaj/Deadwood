/* 
 * Player.java
 * 
 * Contributors: Jacob Kleiman, Eric Eagan, Ryan McGinnis
 * November 2017
 */
import java.util.*;
import java.io.*;


public class Player {
	
	// Attributes
	
	String name;					// Name of the player
	Location CurrentLocation;		// The Current location of the player
	Role CurrentRole;				// The current role of the player
	int rank;						// The player's rank
	int currency;					// Player's money
	int fame;						// Player's amount of fame
	int RehearsePoints;				// Player's rehearse points
	
	// Constructor
	
	// Player
	// Preconditions:
	//
	//
	// Postconditions:
	//
	//
	// Notes:
	//
	//
	public Player(String name,Location trailer) {
		this.name = name;
		CurrentLocation = trailer;
		CurrentRole = null;
		rank = 0;
		currency = 0;
		fame = 0;
		RehearsePoints = 0;
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
	
	// getLocation
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- Returns the current location
	public Location getLocation() {
		return CurrentLocation;
	}
	
	// getRole
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- Returns the current role
	public Role getRole() {
		return CurrentRole;
	}
	
	// getRank
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- Returns rank
	public int getRank() {
		return rank;
	}
	
	// getCurrency
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- Returns currency
	public int getCurrency() {
		return currency;
	}
	
	// getFame
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- Returns fame
	public int getFame() {
		return fame;
	}
	
	// getRehearsePoints
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- Returns RehearsePoints
	public int getRehearsePoints() {
		return RehearsePoints;
	}
	
	
	
	// Upgrade
	// Preconditions:
	//		- Player is in Casting office
	//
	// Postconditions:
	//		
	//
	// Notes:
	//	  	- may need boolean to check if the Upgrade was valid
	//
	public void Upgrade() {
		if (CurrentLocation.getName().equals("Casting Office")){
			
		}
		else{
			System.out.println("Must be in the Casting Office to Upgrade your Rank!");
		}
	}
	
	// Move
	// Preconditions:
	//
	//
	// Postconditions:
	//
	//
	// Notes:
	//
	//
	public void Move() {
		Scanner MoveManager = new Scanner(System.in);
		boolean validMove = false;
		while (validMove != true){
			Location[] neighbors = CurrentLocation.getNeighbors();
			System.out.println("Choose any of these available Locations");
			for(int i = 0; i < neighbors.length; i++){
				System.out.println(neighbors[i].getName());
			}
			
			String UserMove = MoveManager.nextLine();
			
			for (int i = 0; i < neighbors.length; i++){
				if (UserMove.equals(neighbors[i].getName())){
					this.setLocation(neighbors[i]);
					validMove = true;
				}
			}
			if (validMove==false){
				System.out.println("Not a valid Location, try again");
			}
		}
		
		MoveManager.close();
	}
	
	// Act
	// Preconditions:
	//
	//
	// Postconditions:
	//
	//
	// Notes:
	//
	//
	public void Act() {
		
	}
	
	// Rehearse
	// Preconditions:
	//		- must be in a Role
	//
	// Postconditions:
	//		- increment RehearsePoints
	//
	// Notes:
	//		- may want to show Rehearsal points after Rehearse is called?
	//
	public void Rehearse() {
		if (CurrentRole != null)
		{
			if (RehearsePoints < CurrentLocation.getScene().budget){
				RehearsePoints++;
			}
			else{
				System.out.println("Unable to Rehearse anymore!");
			}
		}
		else{
			System.out.println("Cannot Rehearse if not in a Role!");
		}
	}
	
	// TakeRole
	// Preconditions:
	//
	//
	// Postconditions:
	//
	//
	// Notes:
	//
	//
	public void TakeRole() {
		
	}
	
	// Setters
	
	// Role
	// Preconditions:
	//
	//
	// Postconditions:
	//
	//
	// Notes:
	//		- Will be called in Upgrade()
	//
	private void setRank(int newRank) {
		rank = newRank;
	}
	
	// SetRole
	// Preconditions:
	//
	//
	// Postconditions:
	//
	//
	// Notes:
	//		- Will be called in TakeRole()
	//
	private void setRole(Role newRole) {
		CurrentRole = newRole;
	}
	
	// SetLocation
	// Preconditions:
	//
	//
	// Postconditions:
	//		- Player's location is changed to newLocation
	//
	// Notes:
	//
	//
	private void setLocation(Location newLocation) {
		CurrentLocation  = newLocation;
	}
	
	
}
