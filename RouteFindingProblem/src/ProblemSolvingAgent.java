import java.util.ArrayList;
import java.util.List;


public class ProblemSolvingAgent {
	
	
	public static void main(String[] args){
		initiate("Oradea","Eforie");
		
	}
	
	public static void initiate(String mCurrent, String mFinal){
		System.out.println("Process initiated!!");
		
		Node currentState = new Node();
		currentState.setNode(mCurrent);
		currentState.setParent(null);
		State state = new State();
		state.setState(currentState);
		
		Node finalState = new Node();
		finalState.setNode(mFinal);
		Goal goal = new Goal();
		goal.setGoal(finalState);
		
		GRAPH_SEARCH(state, goal);
	}
	
	public static void GRAPH_SEARCH(State state, Goal goal){
		Queue frontier = new Queue();
		frontier.insert(state.getState());
		System.out.println("Inserted " + state.getState().getNode() + " in frontier");
		
		Queue explored = new Queue();
		
		while (true){
			LeafNode leafNode = new LeafNode();
			leafNode.setLeaf(frontier.pop());
			System.out.println(leafNode.getLeaf().getNode() + " removed from frontier and set as leaf node...");
			
			if (leafNode.getLeaf().getNode().equals(goal.getGoal().getNode())){
				printSolution(leafNode.getLeaf());
				break;
			}
			
			explored.insert(leafNode.getLeaf());
			System.out.println("Inserted " + leafNode.getLeaf().getNode() + " in explored");
			
			expand(leafNode.getLeaf(), frontier, explored);
		}
	}
	
	private static void expand(Node node, Queue frontier, Queue explored) {
		System.out.println("Expanding " + node.getNode() + " into child nodes...");
		Queue queue = new Queue();
		if (node.getNode() == "Arad"){
			Node node1 = new Node(); node1.setNode("Zerind"); node1.setParent(node); queue.insert(node1);
			Node node2 = new Node(); node2.setNode("Timisoara"); node2.setParent(node); queue.insert(node2);
			Node node3 = new Node(); node3.setNode("Sibiu"); node3.setParent(node); queue.insert(node3);
		}
		
		else if (node.getNode() == "Zerind"){
			Node node1 = new Node(); node1.setNode("Oradea"); node1.setParent(node); queue.insert(node1);
			Node node2 = new Node(); node2.setNode("Arad"); node2.setParent(node); queue.insert(node2);
		}
		
		else if (node.getNode() == "Sibiu"){
			Node node1 = new Node(); node1.setNode("Arad"); node1.setParent(node); queue.insert(node1);
			Node node2 = new Node(); node2.setNode("Fagaras"); node2.setParent(node); queue.insert(node2);
			Node node3 = new Node(); node3.setNode("Rimnicu Vilcea"); node3.setParent(node); queue.insert(node3);
			Node node4 = new Node(); node4.setNode("Oradea"); node4.setParent(node); queue.insert(node4);
		}
		else if (node.getNode() == "Oradea"){
			Node node1 = new Node(); node1.setNode("Zerind"); node1.setParent(node); queue.insert(node1);
			Node node2 = new Node(); node2.setNode("Sibiu"); node2.setParent(node); queue.insert(node2);
		}
		else if (node.getNode() == "Timisoara"){
			Node node1 = new Node(); node1.setNode("Arad"); node1.setParent(node); queue.insert(node1);
			Node node2 = new Node(); node2.setNode("Lugoj"); node2.setParent(node); queue.insert(node2);
		}
		else if (node.getNode() == "Lugoj"){
			Node node1 = new Node(); node1.setNode("Timisoara"); node1.setParent(node); queue.insert(node1);
			Node node2 = new Node(); node2.setNode("Mehadia"); node2.setParent(node); queue.insert(node2);
		}
		else if (node.getNode() == "Mehadia"){
			Node node1 = new Node(); node1.setNode("Lugoj"); node1.setParent(node); queue.insert(node1);
			Node node2 = new Node(); node2.setNode("Drobeta"); node2.setParent(node); queue.insert(node2);
		}
		else if (node.getNode() == "Drobeta"){
			Node node1 = new Node(); node1.setNode("Mehadia"); node1.setParent(node); queue.insert(node1);
			Node node2 = new Node(); node2.setNode("Craiova"); node2.setParent(node); queue.insert(node2);
		}
		else if (node.getNode() == "Fagaras"){
			Node node1 = new Node(); node1.setNode("Sibiu"); node1.setParent(node); queue.insert(node1);
			Node node2 = new Node(); node2.setNode("Bucharest"); node2.setParent(node); queue.insert(node2);
		}
		else if (node.getNode() == "Iasi"){
			Node node1 = new Node(); node1.setNode("Neamt"); node1.setParent(node); queue.insert(node1);
			Node node2 = new Node(); node2.setNode("Vaslui"); node2.setParent(node); queue.insert(node2);
		}
		else if (node.getNode() == "Vaslui"){
			Node node1 = new Node(); node1.setNode("Iasi"); node1.setParent(node); queue.insert(node1);
			Node node2 = new Node(); node2.setNode("Urziceni"); node2.setParent(node); queue.insert(node2);
		}
		else if (node.getNode() == "Hirsova"){
			Node node1 = new Node(); node1.setNode("Urziceni"); node1.setParent(node); queue.insert(node1);
			Node node2 = new Node(); node2.setNode("Eforie"); node2.setParent(node); queue.insert(node2);
		}
		else if (node.getNode() == "Rimnicu Vilcea"){
			Node node1 = new Node(); node1.setNode("Sibiu"); node1.setParent(node); queue.insert(node1);
			Node node2 = new Node(); node2.setNode("Craiova"); node2.setParent(node); queue.insert(node2);
			Node node3 = new Node(); node3.setNode("Pitesti"); node3.setParent(node); queue.insert(node3);
		}
		else if (node.getNode() == "Pitesti"){
			Node node1 = new Node(); node1.setNode("Bucharest"); node1.setParent(node); queue.insert(node1);
			Node node2 = new Node(); node2.setNode("Craiova"); node2.setParent(node); queue.insert(node2);
			Node node3 = new Node(); node3.setNode("Rimnicu Vilcea"); node3.setParent(node); queue.insert(node3);
		}
		else if (node.getNode() == "Craiova"){
			Node node1 = new Node(); node1.setNode("Pitesti"); node1.setParent(node); queue.insert(node1);
			Node node2 = new Node(); node2.setNode("Drobeta"); node2.setParent(node); queue.insert(node2);
			Node node3 = new Node(); node3.setNode("Rimnicu Vilcea"); node3.setParent(node); queue.insert(node3);
		}
		else if (node.getNode() == "Urziceni"){
			Node node1 = new Node(); node1.setNode("Bucharest"); node1.setParent(node); queue.insert(node1);
			Node node2 = new Node(); node2.setNode("Hirsova"); node2.setParent(node); queue.insert(node2);
			Node node3 = new Node(); node3.setNode("Vaslui"); node3.setParent(node); queue.insert(node3);
		}
		else if (node.getNode() == "Neamt") {
			Node node1 = new Node(); node1.setNode("Iasi"); node1.setParent(node); queue.insert(node1);
		}
		else if (node.getNode() == "Eforie") {
			Node node1 = new Node(); node1.setNode("Hirsova"); node1.setParent(node); queue.insert(node1);
		}
		else if (node.getNode() == "Giurgiu") {
			Node node1 = new Node(); node1.setNode("Bucharest"); node1.setParent(node); queue.insert(node1);
		}
		else if (node.getNode() == "Bucharest"){
			Node node1 = new Node(); node1.setNode("Pitesti"); node1.setParent(node); queue.insert(node1);
			Node node2 = new Node(); node2.setNode("Fagaras"); node2.setParent(node); queue.insert(node2);
			Node node3 = new Node(); node3.setNode("Urziceni"); node3.setParent(node); queue.insert(node3);
			Node node4 = new Node(); node4.setNode("Giurgiu"); node4.setParent(node); queue.insert(node4);
		}
		else{
			System.out.println("ERROR 404: Not Found!!");
			System.exit(0);
		}
		
		while (!queue.isEmpty()){
			Node check = new Node();
			check = queue.pop();
			if (!frontier.containsInString(check.getNode()) && !explored.containsInString(check.getNode())){
				frontier.insert(check);
				System.out.println("Inserted " + check.getNode() + " in frontier");
			}
		}
	}
	
	private static void printSolution(Node node) {
		ArrayList<String> solution = new ArrayList<>();
		solution.add(node.getNode());
		System.out.println("\n************************************************\nPrinting Solution\n");
		while (node.getParent() != null){
			solution.add(node.getParent().getNode());
			node = node.getParent();
		}
		while (solution.size() != 0){
			int i = solution.size();
			System.out.print(solution.get(i-1) );
			if (solution.size() != 1){
				System.out.print("	---->	");
			}
			solution.remove(i-1);
		}
		
	}
	
	
}

class Goal {
	Node goal;
	
	public Node getGoal() {
		return goal;
	}
	
	public void setGoal(Node goal) {
		this.goal = goal;
	}
}

class State {
	Node state;
	
	public Node getState() {
		return state;
	}
	
	public void setState(Node state) {
		this.state = state;
	}
}

class LeafNode {
	Node leaf;
	
	public Node getLeaf() {
		return leaf;
	}
	
	public void setLeaf(Node leaf) {
		this.leaf = leaf;
	}
}

class Node {
	String node;
	Node parent;
	
	public String getNode() {
		return node;
	}
	
	public void setNode(String node) {
		this.node = node;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
}


class Queue {
	List<Node> list = new ArrayList();
	public boolean isEmpty(){
		return list.isEmpty();
	}
	
	public Node pop(){
		Node node = list.get(0);
		list.remove(0);
		return node;
	}
	
	public void insert(Node node){
		list.add(node);
	}
	
	public boolean containsInString( String s){
		List<String> list_String = new ArrayList<>();
		for (int i =0; i<list.size(); i++){
			list_String.add(list.get(i).getNode());
		}
		return  list_String.contains(s);
	}
	
}




