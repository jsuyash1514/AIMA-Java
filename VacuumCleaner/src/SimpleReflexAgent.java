import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimpleReflexAgent {
	static String location;
	static String status;
	
	// location = A || B;
	// status = Clean || Dirty;
	
	public static void INITIATE(String location, String status) {
		List<String> percept = new ArrayList(2);
		String statusA = null, statusB = null;
		percept.add(0, location);
		percept.add(1, status);
		SIMPLE_REFLEX_VACUUM_CLEANER(percept, statusA, statusB);
	}
	
	public static String getLocation(List<String> percept) {
		//Senses location of agent.
		return percept.get(0);
	}
	
	public static String getStatus(List<String> percept) {
		//senses status of current location whether room is dirty or clean
		return percept.get(1);
	}
	
	public static void ACTION_SUCK(String location, String statusA, String statusB, List<String> percept) {
		//Sucks the dirt
		percept.set(1, "CLEAN");
		if (location == "A") {
			statusA = "Clean";
		} else {
			statusB = "Clean";
		}
		return;
	}
	
	public static void ACTION_MOVE_LEFT(String statusA, String statusB, List<String> percept) {
		// Moves the agent one unit left of its current location
		percept.set(0, "A");
		if (statusA != "CLEAN") {
			String[] strings = {"DIRTY", "CLEAN"};
			int index = new Random().nextInt(strings.length);
			percept.set(1, strings[index]);
		} else {
			percept.set(1, "CLEAN");
		}
		return;
	}
	
	public static void ACTION_MOVE_RIGHT(String statusA, String statusB, List<String> percept) {
		//Moves the agent one unit right of its current location
		percept.set(0, "B");
		if (statusB != "CLEAN") {
			String[] strings = {"DIRTY", "CLEAN"};
			int index = new Random().nextInt(strings.length);
			percept.set(1, strings[index]);
		} else {
			percept.set(1, "CLEAN");
		}
		return;
	}
	
	public static void NoOp() {
		System.out.println("Both the rooms are clean.!!");
		System.out.println("***************************************************");
		return;
	}
	
	public static void SIMPLE_REFLEX_VACUUM_CLEANER(List percept, String statusA, String statusB) {
		location = getLocation(percept);
		System.out.println("Initially in room " + location);
		while (statusA != "CLEAN" || statusB != "CLEAN") {
			status = getStatus(percept);
			if (status == "DIRTY") {
				System.out.println("Status: DIRTY");
				ACTION_SUCK(location, statusA, statusB,percept);
				System.out.println("Sucked the dirt of room " + location);
			} else if (location == "A") {
				statusA = "CLEAN";
				System.out.println(location + " is clean");
				ACTION_MOVE_RIGHT(statusA, statusB, percept);
				location = getLocation(percept);
				System.out.println("Entered room " + location);
				status = getStatus(percept);
				if (status == "CLEAN") {
					System.out.println(location + " is clean");
					NoOp();
					break;
				}
			} else if (location == "B") {
				statusB = "CLEAN";
				System.out.println(location + " is clean");
				ACTION_MOVE_LEFT(statusA, statusB, percept);
				location = getLocation(percept);
				System.out.println("Entered room " + location);
				status = getStatus(percept);
				if (status == "CLEAN") {
					System.out.println(location + " is clean");
					NoOp();
					break;
				}
			}
		}
		return;
	}
	
	public static void main(String[] args){
		System.out.println("Case 1: [A, CLEAN]");
		INITIATE("A","CLEAN");
		
		
		System.out.println("Case 2: [A, DIRTY]");
		INITIATE("A","DIRTY");
		
		
		System.out.println("Case 3: [B, CLEAN]");
		INITIATE("B","CLEAN");
		
		
		System.out.println("Case 4: [B, DIRTY]");
		INITIATE("B","DIRTY");
	}
}
