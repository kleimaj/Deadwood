
public class Role {
//Attributes
String name;
String dialogue;
int rank;
boolean isTaken;

//Role constructor
public Role(String name,String dialogue,int rank) {
	this.name = name;
	this.dialogue = dialogue;
	this.rank = rank;
	isTaken = false;
}

//Getter methods
public String getName() {
	return name;
}

public String getDialogue() {
	return dialogue;
}

public int getRank() {
	return rank;
}

public boolean isTaken() {
	return isTaken;
}

//Setter Methods
public void roleTaken() {
	isTaken = true;
}

public void notTaken() {
	isTaken = false;
}
}

