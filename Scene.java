import java.util.*;
public class Scene {
//Attributes
String name;
String description;
int budget;
Role[] onCard;

//Scene Constructor
public Scene(String name, String desc, int budget, Role[] onCard) {
	this.name = name;
	this.description = desc;
	this.budget = budget;
	this.onCard = onCard;
}
//Getter methods
public String getName() {
	return name;
}
public String getDesc() {
	return description;
}
public int getBudget() {
	return budget;
}
public Role[] getAllRoles() {
	return onCard;
}
public Role[] getAvailableRoles() {
	LinkedList<Role> list = new LinkedList<Role>();
	int count = 0;
	for (int i = 0; i < onCard.length; i++) {
		if (onCard[i].isTaken() == false) {
			list.add(onCard[i]);
			count+=1;
		}
	}
	Role[] result = list.toArray(new Role[count]);
	return result;
	}
}
