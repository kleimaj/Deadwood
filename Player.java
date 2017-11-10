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
	public boolean Upgrade() {
		boolean returnValue = false;
		if (CurrentLocation.getName().equals("Casting Office")){
			rank = this.getRank();
			if (rank == 6) {
				System.out.println("You cannot Upgrade anymore!");
				return false;
			}
			else {
				Scanner UpgradeManager = new Scanner(System.in);
				System.out.println("New Rank:      2    3    4    5    6");
				System.out.println("Price (Money): 4   10   18   28   40");
				System.out.println("Price (Fame):  5   10   15   20   25");
				
				int[] moneyUp = {4,10,18,28,40}; 
				int[] fameUp = {5,10,15,20,25};
				
				if(this.currency >= moneyUp[rank - 1] || this.fame >= fameUp[rank - 1]) {
					System.out.print("With money you can upgrade to ranks: ");
					for(int i = rank - 1; i < 5; i++) {
						if(this.currency >= moneyUp[i]) {
							System.out.print(i+2 + " ");
						}
					}
					System.out.println();
					System.out.print("With fame you can upgrade to ranks: ");
					for(int i = rank - 1; i < 5; i++) {
						if(this.fame >= fameUp[i]) {
							System.out.print(i+2 + " ");
						}
					}
					System.out.println();
					while(true) {
						System.out.println("What rank would you like to Upgrade to? (Select your rank, "+rank+", to quit)");
						int desiredRank;
						int paymentMethod;
						
						while(true) {
							desiredRank = UpgradeManager.nextInt();
							
							if (desiredRank > rank && desiredRank <= 6) {
								break;
							}
							else if(desiredRank == rank) {
								returnValue = true;
								break;
							}
							else {
								System.out.println(desiredRank + " is not a valid rank. Select a new one from the options given.");
							}
						}
						
						if(returnValue == true) {
							returnValue = false;
							break;
						}
						
						System.out.println("Please select your payment method. 1 for money, 2 for fame.");
						while(true) {
							paymentMethod = UpgradeManager.nextInt();
							
							if (paymentMethod == 1 || paymentMethod == 2) {
								break;
							}
							else {
								System.out.println(paymentMethod + " is not a valid payment method. Select a new one from the options given.");
							}
						
						}
						
						if(paymentMethod == 1 && this.currency >= moneyUp[desiredRank-2] || paymentMethod == 2 && this.fame >= fameUp[desiredRank - 2]) {
							//do stuff, update info
							returnValue = true;
							break;
						}
					}
					UpgradeManager.close();
					return returnValue;
					
				}
				else {
					System.out.println("You do not have enough money nor enough fame to upgrade.");
					return false;
				}
				
				
				
			}
		}
		else{
			System.out.println("Must be in the Casting Office to Upgrade your Rank!");
			return false;
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
	//   is action selection method (NOTE, if player selects MOVE and they CAN move, they must move.
	public boolean Move() {
		
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
			return true;
			}
		else {
			System.out.println("Cannot move while currently in a role!");
			return false;
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
	public boolean Act() {
		if (CurrentRole != null) {
			int budget = CurrentLocation.getScene().getBudget();
			System.out.println("Must roll greater than or equal to " + budget +"!");
			
			int diceRoll = 1+(int)(6*Math.random());
			
			System.out.println("You rolled a " + diceRoll);
			System.out.println("Along with your Rehearsal Points: "+ RehearsePoints);
			System.out.println("Your overall dice roll = "+RehearsePoints+diceRoll);
			
			//if they don't roll higher
			if (diceRoll+RehearsePoints < budget) {
				System.out.println("You did not roll at least a "+budget);
				//off card earns dollar, on cards earn nothing
				if (CurrentRole.isOnCard()==false) {
					System.out.println("you earned $1 !");
					setCurrency(1+this.getCurrency()); //increments currency
					System.out.println("you now have $"+this.getCurrency());
					return true;
				}
				else {
					System.out.println("Sorry, you don't win anything");
					return true;
				}
				
			}
			//if they roll equal to or higher
			else {
				System.out.println("You succeeded in rolling greater than or equal to "+budget+"!");
				//if they're an extra
				if (CurrentRole.isOnCard() == false) {
					System.out.println("You earned yourself $1 and 1 Fame!");
					setCurrency(1+this.getCurrency());
					setFame(1+this.getFame());
					System.out.println("You now have $"+this.getCurrency()+" and "+this.getFame()+" Fame!");
				}
				else { //they're an on-card actor
					System.out.println("You earned yourself 2 Fame!");
					setFame(2+this.getFame());
					System.out.println("You now have "+this.getFame()+" Fame!");
				}
				// increment shot token, Deadwood.java will check for scene wrap up at end of player's turn
					CurrentLocation.addShot();
					return true;
			}
		}
		else {
			System.out.println("You must Take a Role to Act!");
			return false;
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
	public boolean Rehearse() {
		if (CurrentRole != null)
		{
			if (RehearsePoints < CurrentLocation.getScene().budget){
				RehearsePoints++;
				return true;
			}
			else{
				System.out.println("Unable to Rehearse anymore!");
				return false;
			}
		}
		else{
			System.out.println("Cannot Rehearse if not in a Role!");
			return false;
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
	//	Scanner RoleManager interacts with User prompting which Role they'd like to take
	//
	public void TakeRole() {
		if (CurrentLocation.isLot) {
			if(CurrentRole==null) {
				ArrayList<Role> offCard = new ArrayList<Role>();
				offCard = CurrentLocation.getRoles();
				ArrayList<Role> onCard = new ArrayList<Role>();
				onCard = CurrentLocation.getScene().getAvailableRoles();
				
				Scanner RoleManager = new Scanner(System.in);
				System.out.println("Please choose one of the following roles by entering the index: ");
				while(this.CurrentRole == null) {
					System.out.println("Off Card:");
					for (int i = 1; i <= offCard.size(); i++) {
						System.out.println(i + ". " + "'"+ offCard.get(i-1).getName() + "' - Rank: " + offCard.get(i-1).getRank() + ".");
					}
					System.out.println();
					System.out.println("On Card:");
					int idx = offCard.size()+1;
					for (int i = idx; i <= onCard.size() + idx; i++) {
						System.out.println(i + ". " + "'"+ onCard.get(i - idx).getName() + "' - Rank is: " + onCard.get(i - idx).getRank() + ".");
					}
					
					int playerIndex = RoleManager.nextInt();
					
					if (playerIndex >= 1 && playerIndex <=onCard.size()+idx) {
						//check if oncard or off card
						Role chosenRole = null;
						if (playerIndex < idx) { //it's off card
							 chosenRole = offCard.get(playerIndex-1);
							
						}
						else { //it's on card
							 chosenRole = onCard.get(playerIndex - idx);
						}
						if (this.rank >= chosenRole.getRank()) {
							setRole(chosenRole);
						}
						else {
							System.out.println("You are not a high enough rank, try again");
						}
					}
					else {
						System.out.println("Not a valid index, try again");
					}
				}
				
				RoleManager.close();
			}
			else {
				System.out.println("You cannot Take a Role if you are already in a Role!");
			}
		}
		else {
			System.out.println("You must be in a lot to Take a Role!");
		}
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
	
	private void setCurrency(int newCurrency) {
		currency = newCurrency;
	}
	
	private void setFame(int newFame) {
		fame = newFame;
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
	public void setLocation(Location newLocation) {
		CurrentLocation  = newLocation;
	}
	
	
}
