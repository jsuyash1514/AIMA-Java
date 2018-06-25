import java.util.*;

public class ErraticVacuumWorld {
	static State goal;
	
	public static void main(String[] args){
		initiate("DIRTY","DIRTY","B");
	}
	
	public static void initiate(String a, String b, String location){
		goal = new State("CLEAN","CLEAN","");
		State currentNode = new State(a,b,location);
		ArrayList<State> path = new ArrayList<>();
		OR_Search(currentNode,path);
	}
	
	public static void OR_Search(State currentNode, ArrayList<State> path){
		
		if (currentNode.stateA == goal.stateA && currentNode.stateB == goal.stateB){
			path.add(currentNode);
			System.out.println("In OR_Search: Added " + currentNode.stateA + "\t" + currentNode.stateB + "\t" + currentNode.location +  "\t in path.");
			System.out.println("Goal state reached!!!\tPrinting solution...");
			printSolution(path);
			path.remove(currentNode);
			return;
		}
		
		if (currentNode.existsIn(path)) {
			System.out.println("Already exists in path!! returning back...");
			path.remove(currentNode);
			return;
		}
		
		for (int i=0;i<getAction(currentNode).size(); i++){
			path.add(currentNode);
			System.out.println("In OR_Search: Added " + currentNode.stateA + "\t" + currentNode.stateB + "\t" + currentNode.location +  "\t in path.");
			System.out.println("In OR_Search: " + getAction(currentNode).get(i));
			ArrayList<State> results = getResult(currentNode,getAction(currentNode).get(i) );
			AND_Search(results,path);
			path.remove(currentNode);
		}
		
	}
	
	public static void  AND_Search(ArrayList<State> results, ArrayList<State> path){
		for (int i=0;i<results.size(); i++){
			OR_Search(results.get(i),path);
		}
	}
	
	public static ArrayList<String> getAction(State currentNode){
		ArrayList<String> ACTIONS = new ArrayList<>();
		if (currentNode.location == "A"){
			if (currentNode.stateA == "DIRTY"){
				ACTIONS.add("ACTION_SUCK");
			}
			else{
				ACTIONS.add("ACTION_LITTER");
			}
			ACTIONS.add("ACTION_MOVE_RIGHT");
		}
		else {
			if (currentNode.stateB == "DIRTY"){
				ACTIONS.add("ACTION_SUCK");
			}
			else{
				ACTIONS.add("ACTION_LITTER");
			}
			ACTIONS.add("ACTION_MOVE_LEFT");
		}
		return ACTIONS;
	}
	
	public static ArrayList<State> getResult(State currentNode, String action){
		ArrayList<State> results = new ArrayList<>();
		State successor = new State();
		successor.clone(currentNode);
		State successor1 = new State();
		successor1.clone(currentNode);
		if (action == "ACTION_MOVE_LEFT"){
			successor.location = "A";
			results.add(successor);
		}
		else if (action=="ACTION_MOVE_RIGHT"){
			successor.location = "B";
			results.add(successor);
		}
		else if (action == "ACTION_LITTER"){
			if (successor.location == "A"){
				successor.stateA = "DIRTY";
				results.add(successor);
				successor1.stateA = "CLEAN";
				results.add(successor1);
			}
			else {
				
				successor.stateB = "DIRTY";
				results.add(successor);
				successor1.stateB = "CLEAN";
				results.add(successor1);
			}
		}
		else if (action == "ACTION_SUCK"){
			if (successor.location == "A"){
				successor.stateA = "CLEAN";
				successor1.stateA = "CLEAN";
				if (successor.stateB == "DIRTY") {
					successor.stateB = "DIRTY";
					results.add(successor);
					successor1.stateB = "CLEAN";
					results.add(successor1);
				}
				else{
					results.add(successor);
				}
			}
			else {
				successor.stateB = "CLEAN";
				successor1.stateB = "CLEAN";
				if (successor.stateA == "DIRTY") {
					successor.stateA = "DIRTY";
					results.add(successor);
					successor1.stateA = "CLEAN";
					results.add(successor1);
				}
				else {
					results.add(successor);
				}
			}
		}
		else{
			System.out.println("INVALID ACTION");
		}
		for (int i=0;i<results.size();i++){
			System.out.println("Added \t" + results.get(i).stateA + "\t" + results.get(i).stateB + "\t" + results.get(i).location + "\t in results");
		}
		return results;
	}
	
	
	public static void printSolution(ArrayList<State> path){
		System.out.println("\n**************************************************************\n");
		for (int i=0;i<path.size();i++){
			if (i>0){
				if (path.get(i).stateA==path.get(i-1).stateA && path.get(i).stateB==path.get(i-1).stateB && path.get(i).location==path.get(i-1).location){
					continue;
				}
			}
			System.out.println(path.get(i).stateA + "\t" + path.get(i).stateB + "\t" + path.get(i).location) ;
		}
		System.out.println("\n**************************************************************\n");
		
	}
	
}

class State{
	public String stateA, stateB, location;
	public State(){}
	public State(String a,String b, String location){
		stateA = a;
		stateB = b;
		this.location = location;
	}
	
	public boolean existsIn(ArrayList<State> path){
		for (int i=0;i<path.size();i++){
			if (path.get(i).stateA == this.stateA && path.get(i).stateB == this.stateB && path.get(i).location == this.location){
				return true;
			}
		}
		return false;
	}
	
	public void clone(State state){
		this.stateA = state.stateA;
		this.stateB = state.stateB;
		this.location = state.location;
	}
}
