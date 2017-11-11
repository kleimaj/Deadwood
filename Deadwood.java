/* 
 * Deadwood.java
 * 
 * Contributors: Jacob Kleiman, Eric Eagan, Ryan McGinnis
 * November 2017
 */
import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Deadwood {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		parseXML();
		
		// Scanner
		Scanner console = new Scanner(System.in);
		
		System.out.println("Welcome to Deadwood!");
		System.out.println("How many Players do we have today? (maximum of 8)");
		
		int numPlayers;
		// loops until user inputs valid number of players
		while (true) {
			numPlayers = console.nextInt();
			if (numPlayers > 0 && numPlayers <= 8) {
				break;
			} else { 
			System.out.println("Invalid Number of Players, try again");
			}
		}
			
		console.close();
	}
	
	
	// Parse XML
	// Preconditions:
	//
	//
	// Postconditions:
	//		- scenes and locations are all created
	//
	// Notes:
	//
	//
	public void parseXML(inputFile) {
		
		// Create a document builder
		DocumentBuilderFactory dbFactory  = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    
	    // Create a document from a file
	    Document doc = dBuilder.parse(inputFile);    // the .xml file
	    
	    // Extract the root and other elements
	    Element root = document.getDocumentElement();
	    	
	    // Get all elements and attributes
	}
}
