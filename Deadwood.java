/* 
 * Deadwood.java
 * 
 * Contributors: Jacob Kleiman, Eric Eagan, Ryan McGinnis
 * November 2017
 */
import java.util.*;


public class Deadwood {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Scanner
		Scanner console = new Scanner(System.in);
		
		System.out.println("Welcome to Deadwood!");
		System.out.println("How many Players do we have today? (maximum of 8)");
		
		int numPlayers;
		//loops until user inputs valid number of players
			while(true){
			numPlayers = console.nextInt();
			if (numPlayers > 0 && numPlayers <= 8) break;
			else 
				System.out.println("Invalid Number of Players, try again");
			}
			
			console.close();
	}
}
