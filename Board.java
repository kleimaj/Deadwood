
public class Board {
//attributes
	int dayCounter;
	Location[] locations;
	Deck deck;
	Player[] players;
	
	public Board(Location[] newLocations, Deck newDeck, Player[] newPlayers) {
		locations = newLocations;
		deck = newDeck;
		players = newPlayers;
	}
	
	public void CycleDay() {
		
	}
	
	public void WrapScene() {
		
	}
	
	public void EndDay() {
		
	}
	
	//will be called in EndDay()
	private void resetScenes() {
		
	}
	
	private void UpdateDayCount() {
		dayCounter++;
	}
	
	private void moveToTrailers() {
		
	}
	
	public void Bonus() {
		
	}
	public void TallyScore() {
		
	}

}
