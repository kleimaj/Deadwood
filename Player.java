/* 
 * Player.java
 * 
 * Contributors: Jacob Kleiman, Eric Eagan, Ryan McGinnis
 * November 2017
 */
import java.util.*;


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
	//	CurrentLocation has neighbors
	//  Player is not in a role
	// Postconditions:
	//	CurrentLocation has been set to new Location
	//
	// Notes:
	//	Scanner MoveManager interacts with User prompting which neighbor they'd like to move to
	//
	public void Move() {
		
		if (CurrentRole != null)
			{
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
		else {
			System.out.println("Cannot move while currently in a role!");
		}
		}
	
	// Act
	// Preconditions:
	//	-player is in a role
	//
	// Postconditions:
	//	
	//
	// Notes:
	//
	//
	public void Act() {
		if (CurrentRole != null) {
			int budget = CurrentLocation.getScene().getBudget();
			System.out.println("Must roll greater than or equal to " + budget +"!");
			
			int diceRoll = 1+(int)(6*Math.random());
			
			System.out.println("You rolled a " + diceRoll);
			System.out.println("Along with your Rehearsal Points: "+ RehearsePoints);
			System.out.println("Your overall dice roll = "+RehearsePoints+diceRoll);
			
			//if they don't roll higher
			if (diceRoll+RehearsePoints < budget) {
				//off card earns dollar, on cards earn nothing
				
				
			}
			//if they roll equal to or higher
			else {
				
			}
		}
		else {
			System.out.println("You must Take a Role to Act!");
		}
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
