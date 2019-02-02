/*
 * Player.java
 *
 * Contributors: Jacob Kleiman, Eric Eagan, Ryan McGinnis
 * November 2017
 */
import java.util.*;
import java.util.concurrent.TimeUnit;

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
	int rehearsePoints;				// Player's rehearse points
	int score;						// Total score at end of game
	String filename;

	// Constructor

	// Player
	// Preconditions:
	//		- a user has typed in their name
	//
	// Postconditions:
	//		- player's default location is trailer
	//		- board is ready to be constructed (when all players are constructed)
	//
	public Player(String name, Location trailer, int rank, int fame, String filename) {
		this.name = name;
		currentLocation = trailer;
		currentRole = null;
		this.rank = rank;
		currency = 0;
		this.fame = fame;
		rehearsePoints = 0;
		score = 0;
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
		return rehearsePoints;
	}
	
	public String getFileName() {
		return filename;
	}

	public void resetRehearsePoints() {
		this.rehearsePoints = 0;
	}
	public void setRehearsePoints(int points) {
		this.rehearsePoints = points;
	}
	public void setFileName(String newFile) {
		this.filename = newFile;
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
		if (currentLocation.getName().equals("office")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean[] allActions() { //same format as buttons
		boolean[] actions = new boolean[5];
		if (this.isInRole()) { //if they're in a role
			actions[0] = false; //cant move
			actions[1] = false; //cant take a role
			actions[2] = true; //able to act
			if (this.rehearsePoints < this.currentLocation.getScene().getBudget()-1) {
				actions[3] = true; //able to rehearse																	
			}
			else {
				actions[3] = false;
			}
			actions[4] = false;
		}
		else { //if not in role
			actions[0] = true;
			if (this.currentLocation.isLot()) {
				ArrayList<Role> roles = this.currentLocation.getAllLotRoles(this.rank);
				if (roles.size() == 0 || this.currentLocation.isWrappedUp()) {
					actions[1] = false;
				}
				else {
					actions[1] = true;
				}
			}
			else {
				actions[1] = false;
			}
			actions[2] = false;
			actions[3] = false;
			
			if (this.isInCastingOffice()&&this.canUpgrade()) { //need to check for more things (make new method in player called canUpgrade() returns boolean
				actions[4] = true;
			}
			else {
				actions[4] = false;
			}
		}
		return actions;
	}
	
	public boolean canUpgrade() {
		switch(rank) {
		case 1:
			if (currency >= 4 || fame >= 5) {
				return true;
			}
			else {
				return false;
			}
		case 2:
			if (currency >= 10 || fame >= 10) {
				return true;
			}
			else {
				return false;
			}
		case 3:
			if (currency >= 18 || fame >= 15) {
				return true;
			}
			else {
				return false;
			}
		case 4:
			if (currency >= 28 || fame >= 20) {
				return true;
			}
			else {
				return false;
			}
		case 5:
			if (currency >= 40 || fame >= 25) {
				return true;
			}
			else {
				return false;
			}
		}
		return false; //this means they're rank 6
	}
	
	public String[] getAvailableRanks() {
		ArrayList<String> ranks = new ArrayList<String>();
		for (int i = this.rank; i < 7; i++) {
			switch(i) {
				case 1:
					if (currency >= 4 || fame >= 5) {
						ranks.add("2");
						break;
					}
				case 2:
					if (currency >= 10 || fame >= 10) {
						ranks.add("3");
						break;
					}
				case 3:
					if (currency >= 18 || fame >=15) {
						ranks.add("4");
						break;
					}
				case 4:
					if (currency >= 28 || fame >= 20) {
						ranks.add("5");
						break;
					}
				case 5:
					if (currency >= 40 || fame >= 25) {
						ranks.add("6");
						break;
					}
			}
		}
		String[] returnedRanks = ranks.toArray(new String[ranks.size()]);
		
		return returnedRanks;
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
	public boolean Upgrade() throws InterruptedException {

		boolean validMove = false;
		if (currentLocation.getName().equals("office")) {
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
					System.out.print("With money ($"+this.getCurrency()+") you can upgrade to ranks: ");
					for (int i = rank - 1; i < 5; i++) {
						if(this.currency >= moneyUp[i]) {
							System.out.print(i+2 + " ");
						}
					}
					System.out.println();
					System.out.print("With fame ("+this.getFame()+") you can upgrade to ranks: ");
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
								System.out.println("You are now rank: "+this.rank+" and have $"+this.currency);
								TimeUnit.SECONDS.sleep(1);

							} else {
								this.setFame(this.fame - fameUp[desiredRank-2]);
								this.setRank(desiredRank);
								System.out.println("You are now rank: "+this.rank+" and have "+this.fame+" fame");
								TimeUnit.SECONDS.sleep(1);

							}
							validMove = true;
							break;
						}
					}
					//UpgradeManager.close();
					return validMove;

				} else {
					System.out.println("You do not have enough money nor enough fame to upgrade.");
					//UpgradeManager.close();
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
	public void Move(ArrayList<Location> locations) throws InterruptedException {

		if (this.currentRole == null) {
			Scanner MoveManager = new Scanner(System.in);
			boolean validMove = false;
			while (validMove != true){
				String[] neighbors = currentLocation.getNeighbors();
				int count = 0;
				System.out.println("Choose any of these available Locations (#)");
				TimeUnit.SECONDS.sleep(1);

				for(int i = 0; i < neighbors.length; i++){
					System.out.println(neighbors[i]+" ("+(count+1)+")");
					count++;
				}

				int UserMove = MoveManager.nextInt()-1; //index of neighbor
				Location neighbor = null;
				for (int i = 0; i < locations.size(); i++){
					if(neighbors[UserMove].equals("trailer")&&locations.get(i).getName().equals("trailer")) {//for lowercase trailer
						neighbor = locations.get(i);
						this.setLocation(neighbor);
						validMove = true;
					}
					if (neighbors[UserMove].equals("office")&&locations.get(i).getName().equals("office")) {
						neighbor = locations.get(i);
						this.setLocation(neighbor);
						validMove = true;
					}
					if (neighbors[UserMove].equals(locations.get(i).getName())){//have correct name of neighbor, need to get neighbor
						neighbor = locations.get(i);
						this.setLocation(neighbor);
						validMove = true;
					}
				}
				if (validMove==false){
					System.out.println("Not a valid Location, try again");
					TimeUnit.SECONDS.sleep(2);

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
	public void Act() throws InterruptedException {

		if (currentRole != null) {

			int budget = this.currentLocation.getScene().getBudget();
			System.out.println("Must roll greater than or equal to " + budget +"!");
			TimeUnit.SECONDS.sleep(1);


			int diceRoll = 1+(int)(6*Math.random());

			System.out.println("You rolled a " + diceRoll);
			TimeUnit.SECONDS.sleep(1);

			System.out.println("Along with your Rehearsal Points: "+ rehearsePoints);
			System.out.println("Your overall dice roll = "+(rehearsePoints+diceRoll));
			TimeUnit.SECONDS.sleep(1);


			// if they don't roll higher
			if (diceRoll+rehearsePoints < budget) {
				System.out.println("You did not roll at least a "+budget);
				TimeUnit.SECONDS.sleep(1);

				// off card earns dollar, on cards earn nothing
				if (currentRole.isOnCard()==false) {
					System.out.println("Because you are an Off-Card Actor, you've earned $1 !");
					TimeUnit.SECONDS.sleep(1);
					setCurrency(1+this.getCurrency()); //increments currency
					System.out.println("you now have $"+this.getCurrency());
					TimeUnit.SECONDS.sleep(1);

				} else {
					System.out.println("Sorry, you don't win anything");
					TimeUnit.SECONDS.sleep(1);

				}

			// if they roll equal to or higher
			} else {
				System.out.println("You succeeded in rolling greater than or equal to "+budget+"!");
				TimeUnit.SECONDS.sleep(1);

				// if they're an extra
				if (currentRole.isOnCard() == false) {
					System.out.println("You earned yourself $1 and 1 Fame!");
					TimeUnit.SECONDS.sleep(1);

					setCurrency(1+this.getCurrency());
					setFame(1+this.getFame());
					System.out.println("You now have $"+this.getCurrency()+" and "+this.getFame()+" Fame!");
					TimeUnit.SECONDS.sleep(1);

				} else { // they're an on-card actor
					System.out.println("You earned yourself 2 Fame!");
					TimeUnit.SECONDS.sleep(1);

					setFame(2+this.getFame());
					System.out.println("You now have "+this.getFame()+" Fame!");
					TimeUnit.SECONDS.sleep(1);

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
			if (rehearsePoints < currentLocation.getScene().budget){
				rehearsePoints++;
				System.out.println(this.name+" now has "+this.rehearsePoints+" Rehearse Points");

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
	public void TakeRole() throws InterruptedException {

		if (currentLocation.isLot()) {

			if(currentRole==null) {

				ArrayList<Role> offCard = new ArrayList<Role>();
				offCard = currentLocation.getRoles();
				ArrayList<Role> onCard = new ArrayList<Role>();
				onCard = currentLocation.getScene().getAvailableRoles();

				//Scanner RoleManager = new Scanner(System.in);


				while (this.currentRole == null) {
					System.out.println("Please choose one of the following roles by entering the index (0 if none): ");
					TimeUnit.SECONDS.sleep(1);

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

					int playerIndex = 0;//RoleManager.nextInt();

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
							chosenRole.roleTaken();
						} else {
							System.out.println("You are not a high enough rank, try again");
							TimeUnit.SECONDS.sleep(1);

						}
					}
					else if(playerIndex == 0) {
						break;
					}
					else {
						System.out.println("Not a valid index, try again");
						//TimeUnit.SECONDS.sleep(2);

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
	public void setRank(int newRank) {
		rank = newRank;
		
		String firstLetter = this.name.substring(0, 1);
		
		firstLetter = firstLetter.toLowerCase();
		
		this.setFileName(firstLetter+newRank+".png"); //changes filename
		
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
		if (newRole != null) {
		newRole.roleTaken();
		}
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
	public void setFame(int newFame) {
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
	public void setLocation(Location newLocation) throws InterruptedException {
		//System.out.println(currentLocation.getName());
		this.currentLocation  = newLocation;
		//System.out.println(currentLocation.getName());
		System.out.println(name+" is now in "+newLocation.getName());
		TimeUnit.SECONDS.sleep(1);

	}
	public void resetStats(int numPlayers) {
		this.currency = 0;
		this.fame = 0;
		this.setRank(1);
		
		if (numPlayers == 5) {
			this.fame = 2;
		}
		else if(numPlayers == 6) {
			this.fame = 4;
		}
		else if(numPlayers == 7 || numPlayers == 8) {
			this.setRank(2);
			
		}
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
