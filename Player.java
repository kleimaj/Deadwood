/* 
 * Player.java
 * 
 * Contributors: Jacob Kleiman, Eric Eagan, Ryan McGinnis
 * November 2017
 */
import java.util.*;
//import java.io.*;

//import javax.tools.JavaFileManager.Location;


public class Player implements Comparable<Player> {
	
	// Attributes
	
	String name;					// Name of the player
	Location currentLocation;		// The Current location of the player
	Role currentRole;				// The current role of the player
	int rank;						// The player's rank
	int currency;					// Player's money
	int fame;						// Player's amount of fame
	int RehearsePoints;				// Player's rehearse points
	int score;						// Total score at end of game
	
	// Constructor
	
	// Player
	// Preconditions:
	//		- a user has typed in their name
	//
	// Postconditions:
	//		- player's default location is trailer
	//		- board is ready to be constructed (when all players are constructed)
	//
	public Player(String name, Location trailer) {
		this.name = name;
		currentLocation = trailer;
		currentRole = null;
		rank = 1;
		currency = 0;
		fame = 0;
		RehearsePoints = 0;
		score = 0;
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
	
	// getLocation
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- returns the current location
	public Location getLocation() {
		return currentLocation;
	}
	
	// getRole
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- returns the current role
	public Role getRole() {
		return currentRole;
	}
	
	// getRank
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- returns rank
	public int getRank() {
		return rank;
	}
	
	// getCurrency
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- returns currency
	public int getCurrency() {
		return currency;
	}
	
	// getFame
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- returns fame
	public int getFame() {
		return fame;
	}
	
	// getRehearsePoints
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- returns RehearsePoints
	public int getRehearsePoints() {
		return RehearsePoints;
	}
	
	// getScore
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- returns score
	public int getScore() {
		return score;
	}
	
	// isInRole
	// Preconditions:
	//		- none	
	// Postconditions:
	//		- returns true if player is in role, false if player isn't in role
	public boolean isInRole() {
		if (currentRole == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean isInCastingOffice() {
		if (currentLocation.getName().equals("Casting Office")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// Upgrade
	// Preconditions:
	//		- player is in Casting office
	//
	// Postconditions:
	//	 	- returns true if the move was valid, false if invalid	
	//		- player's new rank is set if was valid
	// Notes:currentLocation
	//	 
	public boolean Upgrade() {
		
		boolean validMove = false;
		if (currentLocation.getName().equals("Casting Office")) {
			rank = this.getRank();
			if (rank == 6) {
				System.out.println("You cannot Upgrade anymore!");
				return false;
			} else {
				Scanner UpgradeManager = new Scanner(System.in);
				// display all ranks
				System.out.println("New Rank:      2    3    4    5    6");
				System.out.println("Price (Money): 4   10   18   28   40");
				System.out.println("Price (Fame):  5   10   15   20   25");
				
				int[] moneyUp = {4,10,18,28,40}; 
				int[] fameUp = {5,10,15,20,25};
				
				if (this.currency >= moneyUp[rank - 1] || this.fame >= fameUp[rank - 1]) {
					System.out.print("With money you can upgrade to ranks: ");
					for (int i = rank - 1; i < 5; i++) {
						if(this.currency >= moneyUp[i]) {
							System.out.print(i+2 + " ");
						}
					}
					System.out.println();
					System.out.print("With fame you can upgrade to ranks: ");
					for (int i = rank - 1; i < 5; i++) {
						if (this.fame >= fameUp[i]) {
							System.out.print(i+2 + " ");
						}
					}
					System.out.println();
					
					while(true) {
						System.out.println("What rank would you like to Upgrade to? (Select your rank, "+rank+", to quit)");
						int desiredRank;
						int paymentMethod;
						
						while (true) {
							desiredRank = UpgradeManager.nextInt();
							
							if (desiredRank > rank && desiredRank <= 6) { 
								// it's a valid rank, need to check if they can afford it, or else they will be stuck
								if (this.currency >= moneyUp[desiredRank - 2] || this.fame >= fameUp[desiredRank -2]) {
									break;
								} else {
									System.out.println("You lack the amount of currency and fame to Upgrade to this rank, Select a new one from options given.");
								}
							} else if(desiredRank == rank) {
								validMove = true;
								break;
							} else {
								System.out.println(desiredRank + " is not a valid rank. Select a new one from the options given.");
							}
						}
						
						if (validMove == true) {
							validMove = false;
							break;
						}
						
						System.out.println("Please select your payment method. 1 for money, 2 for fame.");
						
						while (true) {
							paymentMethod = UpgradeManager.nextInt();
							
							if (paymentMethod == 1 || paymentMethod == 2) {
								break;
							} else {
								System.out.println(paymentMethod + " is not a valid payment method. Select a new one from the options given.");
							}
						}
						
						if (paymentMethod == 1 && this.currency >= moneyUp[desiredRank-2] || paymentMethod == 2 && this.fame >= fameUp[desiredRank - 2]) {
							// do stuff, update info
							if (paymentMethod == 1) {
								this.setCurrency(this.currency - moneyUp[desiredRank-2]);
								this.setRank(desiredRank);
								System.out.println("You are now rank: "+this.rank+" and have $+"+this.currency);
							} else {
								this.setFame(this.fame - fameUp[desiredRank-2]);
								this.setRank(desiredRank);
								System.out.println("You are now rank: "+this.rank+" and have "+this.fame+" fame");
							}
							validMove = true;
							break;
						}
					}
					UpgradeManager.close();
					return validMove;
					
				} else {
					System.out.println("You do not have enough money nor enough fame to upgrade.");
					UpgradeManager.close();
					return false;
				}	
			}
		} else {
			System.out.println("Must be in the Casting Office to Upgrade your Rank!");
			return false;
		}
	}
	
	// Move
	// Preconditions:
	//		- currentLocation has neighbors
	//		- player is not in a role
	// Postconditions:
	//		- currentLocation has been set to new Location
	//
	// Notes:
	//		- scanner MoveManager interacts with User prompting which neighbor they'd like to move to
	//   	- is action selection method (NOTE, if player selects MOVE and they CAN move, they must move.
	//
	public void Move(ArrayList<Location> locations) {
		
		if (this.currentRole == null) {
			Scanner MoveManager = new Scanner(System.in);
			boolean validMove = false;
			while (validMove != true){
				String[] neighbors = currentLocation.getNeighbors();
				int count = 0;
				System.out.println("Choose any of these available Locations");
				for(int i = 0; i < neighbors.length; i++){
					System.out.println(neighbors[i]+" ("+count+")");
				}
				
				String UserMove = MoveManager.next();
				Location neighbor = null;
				for (int i = 0; i < neighbors.length; i++){
					if (UserMove.equals(neighbors[i])){//have correct name of neighbor, need to get neighbor
						
						//System.out.println(locations.size());
						for (int j = 0; j < locations.size(); j++) {
							if (locations.get(j).getName().equals(neighbors[i])) {
								neighbor = locations.get(j);
							}
						}
						this.setLocation(neighbor);
						validMove = true;
					}
				}
				if (validMove==false){
					System.out.println("Not a valid Location, try again");
				}
			}
			
			//MoveManager.close();
		} else { //never get here
			System.out.println("Cannot move while currently in a role!");
		}
	}
	
	// Act
	// Preconditions:
	//		- player is in a role
	//
	// Postconditions:
	//		- player's turn is over
	//		- player earns potential fame and currency
	// Notes:
	//
	//
	public void Act() {
		
		if (currentRole != null) {
			
			int budget = this.currentLocation.getScene().getBudget();
			System.out.println("Must roll greater than or equal to " + budget +"!");
			
			int diceRoll = 1+(int)(6*Math.random());
			
			System.out.println("You rolled a " + diceRoll);
			System.out.println("Along with your Rehearsal Points: "+ RehearsePoints);
			System.out.println("Your overall dice roll = "+(RehearsePoints+diceRoll));
			
			// if they don't roll higher
			if (diceRoll+RehearsePoints < budget) {
				System.out.println("You did not roll at least a "+budget);
				// off card earns dollar, on cards earn nothing
				if (currentRole.isOnCard()==false) {
					System.out.println("Because you are an Off-Card Actor, you've earned $1 !");
					setCurrency(1+this.getCurrency()); //increments currency
					System.out.println("you now have $"+this.getCurrency());
				} else {
					System.out.println("Sorry, you don't win anything");
				}
				
			// if they roll equal to or higher
			} else {
				System.out.println("You succeeded in rolling greater than or equal to "+budget+"!");
				// if they're an extra
				if (currentRole.isOnCard() == false) {
					System.out.println("You earned yourself $1 and 1 Fame!");
					setCurrency(1+this.getCurrency());
					setFame(1+this.getFame());
					System.out.println("You now have $"+this.getCurrency()+" and "+this.getFame()+" Fame!");
				} else { // they're an on-card actor
					System.out.println("You earned yourself 2 Fame!");
					setFame(2+this.getFame());
					System.out.println("You now have "+this.getFame()+" Fame!");
				}
				// increment shot token, Deadwood.java will check for scene wrap up at end of player's turn
					this.currentLocation.addShot();
			}
		} else { //never get here
			System.out.println("You must Take a Role to Act!"); //should never happen
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
		
		if (currentRole != null) {
			if (RehearsePoints < currentLocation.getScene().budget){
				RehearsePoints++;
			} else {
				System.out.println("Unable to Rehearse anymore!"); //should never happen
			}
		} else {
			System.out.println("Cannot Rehearse if not in a Role!"); //should never happen
		}
	}
	
	// TakeRole
	// Preconditions:
	//		- player is not in a role
	//
	// Postconditions:
	//		- player takes the role entered by index if it is valid
	//
	// Notes:
	//		- scanner RoleManager interacts with User prompting which Role they'd like to take
	//
	public void TakeRole() {
		
		if (currentLocation.isLot()) {
			
			if(currentRole==null) {
				
				ArrayList<Role> offCard = new ArrayList<Role>();
				offCard = currentLocation.getRoles();
				ArrayList<Role> onCard = new ArrayList<Role>();
				onCard = currentLocation.getScene().getAvailableRoles();
				
				Scanner RoleManager = new Scanner(System.in);
				
				
				while (this.currentRole == null) {
					System.out.println("Please choose one of the following roles by entering the index (0 if none): ");
					System.out.println("Off Card:");
					
					for (int i = 1; i <= offCard.size(); i++) {
						System.out.println(i + ". " + "'"+ offCard.get(i-1).getName() + "' - Rank: " + offCard.get(i-1).getRank() + ".");
					}
					
					System.out.println();
					System.out.println("On Card:");
					
					int idx = offCard.size()+1;
					for (int i = idx; i < onCard.size() + idx; i++) { //may be problem here
						System.out.println(i + ". " + "'"+ onCard.get(i - idx).getName() + "' - Rank is: " + onCard.get(i - idx).getRank() + ".");
					}
					
					int playerIndex = RoleManager.nextInt();
					
					if (playerIndex >= 1 && playerIndex <=onCard.size()+idx) {
						// check if oncard or off card
						Role chosenRole = null;
						System.out.println(this.getRank());
						if (playerIndex < idx) { //it's off card
							chosenRole = offCard.get(playerIndex-1);	
						} else { // it's on card
							chosenRole = onCard.get(playerIndex - idx);
						}
						if (this.rank >= chosenRole.getRank()) {
							setRole(chosenRole);
						} else {
							System.out.println("You are not a high enough rank, try again");
						}
					} 
					else if(playerIndex == 0) {
						break;
					}
					else {
						System.out.println("Not a valid index, try again");
					}
				} // end of while loop
				
				//RoleManager.close();
				
			} else {
				System.out.println("You cannot Take a Role if you are already in a Role!");
			}
		} else {
			System.out.println("You must be in a lot to Take a Role!");
		}
	}
	
	// Setters
	
	// Role
	// Preconditions:
	//		- upgrade is valid
	//
	// Postconditions:
	//		- player's new rank is set
	//
	// Notes:
	//		- will be called in Upgrade()
	//
	private void setRank(int newRank) {
		rank = newRank;
	}
	
	// SetRole
	// Preconditions:
	//		- player chooses a role or scene is wrapped
	//
	// Postconditions:
	//		- new role is assigned or is null
	//
	// Notes:
	//		- will be called in TakeRole(), is also called in GameBoard's WrapScene
	//
	public void setRole(Role newRole) {
		currentRole = newRole;
	}
	
	// SetCurrency
	// Preconditions:
	//
	//
	// Postconditions:
	//		- sets this player's currency to a new value
	//
	public void setCurrency(int newCurrency) {
		currency = newCurrency;
	}
	
	// SetFame
	// Preconditions:
	//		- player has earned fame through acting
	//
	// Postconditions:
	//		- sets this player's fame to a new value
	//
	// Notes:
	//		
	private void setFame(int newFame) {
		fame = newFame;
	}
	
	// SetLocation
	// Preconditions:
	//		- player moves or day is ended
	//
	// Postconditions:
	//		- player's location is changed to newLocation
	//
	//
	public void setLocation(Location newLocation) {
		//System.out.println(currentLocation.getName());
		this.currentLocation  = newLocation;
		//System.out.println(currentLocation.getName());
		System.out.println(name+" is now in "+newLocation.getName());
	}
	
	// SetLocation
	// Preconditions:
	//		- game is over
	//
	// Postconditions:
	//		- total score is set
	//
	public void setScore() {
		int score = currency + fame + (5 * rank);
		this.score = score;
	}
	
	public int compareTo(Player other) { //ordering in descending order
		if (this.getScore() == other.getScore()) {
			return 0;
		}
		else if (this.getScore() > other.getScore()) {
			return -1;
		}
		else {
			return 1;
		}
	}
	
}
