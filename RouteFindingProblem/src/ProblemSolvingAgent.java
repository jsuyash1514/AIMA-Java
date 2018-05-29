import java.util.*;


public class ProblemSolvingAgent {
	public  static  AdjacencyMatrix adjacencyMatrix;
	
	public static void main(String[] args){
		adjacencyMatrix.setMap();
		initiate("Sibiu","Neamt");
		
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
		AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix();
		queue = adjacencyMatrix.getQueue(node);
		while (!queue.isEmpty()){
			Node check = new Node();
			check = queue.pop();
			if (!frontier.containsInString(check.getNode()) && !explored.containsInString(check.getNode())){
				check.setParent(node);
				frontier.insert(check);
				System.out.println("Inserted " + check.getNode() + " in frontier and its parent node is " + check.getParent().getNode());
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


class AdjacencyMatrix{
	public static int[][] matrix;
	public static HashMap<Integer, Node> hashMap ;
	
	public static void setMap(){
		hashMap = new HashMap<>();
		
		
		Node node = new Node(); node.setNode("Oradea"); hashMap.put(0, node);
		Node node1 = new Node(); node1.setNode("Zerind"); hashMap.put(1, node1);
		Node node2 = new Node(); node2.setNode("Arad"); hashMap.put(2, node2);
		Node node3 = new Node(); node3.setNode("Timisoara"); hashMap.put(3, node3);
		Node node4 = new Node(); node4.setNode("Lugoj"); hashMap.put(4, node4);
		Node node5 = new Node(); node5.setNode("Mehadia"); hashMap.put(5, node5);
		Node node6 = new Node(); node6.setNode("Drobeta"); hashMap.put(6, node6);
		Node node7 = new Node(); node7.setNode("Craiova"); hashMap.put(7, node7);
		Node node8 = new Node(); node8.setNode("Sibiu"); hashMap.put(8, node8);
		Node node9 = new Node(); node9.setNode("Rimnicu Vilcea"); hashMap.put(9, node9);
		Node node10 = new Node(); node10.setNode("Pitesti"); hashMap.put(10, node10);
		Node node11 = new Node(); node11.setNode("Fagaras"); hashMap.put(11, node11);
		Node node12 = new Node(); node12.setNode("Bucharest"); hashMap.put(12, node12);
		Node node13 = new Node(); node13.setNode("Giurgiu"); hashMap.put(13, node13);
		Node node14 = new Node(); node14.setNode("Urziceni"); hashMap.put(14, node14);
		Node node15 = new Node(); node15.setNode("Vaslui"); hashMap.put(15, node15);
		Node node16= new Node(); node16.setNode("Iasi"); hashMap.put(16, node16);
		Node node17 = new Node(); node17.setNode("Neamt"); hashMap.put(17, node17);
		Node node18 = new Node(); node18.setNode("Hirsova"); hashMap.put(18, node18);
		Node node19 = new Node(); node19.setNode("Eforie"); hashMap.put(19, node19);
		
		
		
		matrix = new int[20][20];
		matrix[0][1] = 1; matrix[0][8] = 1;
		matrix[1][0] = 1; matrix[1][2] = 1;
		matrix[2][1] = 1; matrix[2][3] = 1; matrix[2][8] = 1;
		matrix[3][2] = 1; matrix[3][4] = 1;
		matrix[4][3] = 1; matrix[4][5] = 1;
		matrix[5][4] = 1; matrix[5][6] = 1;
		matrix[6][5] = 1; matrix[6][7] = 1;
		matrix[7][6] = 1; matrix[7][9] = 1; matrix[7][10] = 1;
		matrix[8][0] = 1; matrix[8][2] = 1; matrix[8][9] = 1; matrix[8][11] = 1;
		matrix[9][7] = 1; matrix[9][8] = 1; matrix[9][10] = 1;
		matrix[10][7] = 1; matrix[10][9] = 1; matrix[10][12] = 1;
		matrix[11][8] = 1; matrix[11][12] = 1;
		matrix[12][10] = 1; matrix[12][11] = 1; matrix[12][13] = 1; matrix[12][14] = 1;
		matrix[13][12] = 1;
		matrix[14][12] = 1; matrix[14][15] = 1; matrix[14][18] = 1;
		matrix[15][14] = 1; matrix[15][16] = 1;
		matrix[16][15] = 1; matrix[16][17] = 1;
		matrix[17][16] = 1;
		matrix[18][14] = 1; matrix[18][19] = 1;
		matrix[19][18] = 1;
		
		
	}
	public Queue getQueue(Node node){
		Queue queue = new Queue();
		int i=0;
		int key = -1;
		while (i<hashMap.size()){
			if(node.getNode() == hashMap.get(i).getNode()){
				key = i;
				break;
			}
			i++;
		}
		if (key == -1){
			System.out.println(node.getNode() + " not found.");
			System.exit(0);
		}
		for (int k=0; k<20; k++){
			if (matrix[key][k] == 1){
				Node node1 = new Node();
				node1 = hashMap.get(k);
				queue.insert(node1);
			}
		}
		return queue;
	}
}





