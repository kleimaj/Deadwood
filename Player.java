
public class Player {
	//Player Attributes
	String name;
	Location CurrentLocation;
	Role CurrentRole;
	int rank;
	int currency;
	int fame;
	int RehearsePoints;
	
	//Player Constructor
	public Player(String name,Location trailer) {
		this.name = name;
		CurrentLocation = trailer;
		CurrentRole = null;
		rank = 0;
		currency = 0;
		fame = 0;
		RehearsePoints = 0;
	}
	
	//Player Methods
	//get methods
	public String getName() {
		return name;
	}
	
	public Location getLocation() {
		return CurrentLocation;
	}
	
	public Role getRole() {
		return CurrentRole;
	}

	public int getRank() {
		return rank;
	}
	
	public int getCurrency() {
		return currency;
	}
	
	public int getFame() {
		return fame;
	}
	
	public int getRehearsePoints() {
		return RehearsePoints;
	}
	
	public void Upgrade() {
		
	}
	
	
	public void Move() {
		
	}
	
	public void Act() {
		
	}
	
	public void Rehearse() {
		RehearsePoints++;
	}
	
	public void TakeRole() {
		
	}
	
	//setters
	//will be called in Upgrade()
		private void setRank(int newRank) {
			rank = newRank;
		}
		
	//will be called in TakeRole()
		private void setRole(Role newRole) {
			CurrentRole = newRole;
		}
		
		public void setLocation(Location newLocation) {
			
		}
	
	
}
