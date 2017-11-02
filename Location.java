
public class Location {
//attributes every Location shares
String name;
Location[] neighbors;
boolean isLot;
//if isLot = true, will have the following attributes

//int ShotToken;
int ShotMax;
int ShotsTaken;
Scene currentScene;
Role[] offCard;
//constructor for a normal location (i.e Trailer, Casting Office)
public Location(String name){
	this.name = name;
	//this.neighbors = neighbors;
	isLot = false;
}

//constructor for a lot
public Location(String name, int shotMax,int shotsTaken, Scene currScene, Role[] offCard) {
	this.name = name;
	//this.neighbors = neighbors;
	ShotMax = shotMax;
	ShotsTaken=shotsTaken;
	currentScene = currScene;
	this.offCard = offCard;
	isLot = true;
}
//getter methods
public boolean isLot() {
	return isLot;
}

public int getShotsTaken() {
	return ShotsTaken;
}

public int getShotsMax() {
	return ShotMax;
}

public String getName() {
	return name;
}

public Location[] getNeighbors() {
	return neighbors;
}
/*
public Role[] getRoles() {
	if (this.isLot == true) {
		
	}
	else {
		
	}
}*/

//setter methods
public void addShot() {
	ShotsTaken+=1;
}

public void setNeighbors(Location[] neighbors) {
	
}
}

