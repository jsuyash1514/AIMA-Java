import java.util.*;

// Graph Search Algorithm (Uninformed Search Strategies)

public class ProblemSolvingAgent {
	public  static  AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix();
	public static  Node state;
	public static  Node goal;
	public static Node leafNode;
	
	
	public static void main(String[] args){
		adjacencyMatrix.setMap();
		initiate("Arad","Iasi");
		
	}
	
	public static void initiate(String mCurrent, String mFinal){
		System.out.println("Process initiated!!");
		
		Node currentState = new Node();
		currentState.setNode(mCurrent);
		currentState.setParent(null);
		state = currentState;
		
		Node finalState = new Node();
		finalState.setNode(mFinal);
		goal = finalState;
		
//		BreadthFirstSearch.GRAPH_SEARCH(state, goal);   	/*Uncomment this line to run Breadth-First search.*/
//		UniformCostSearch.GRAPH_SEARCH(state, goal);		/*Uncomment this line to run Uniform Cost search.*/
//		DepthLimitedSearch.GRAPH_SEARCH(state, goal, 9);	/*Uncomment this line to run Depth Limited search.*/
//		IterativeDeepeningSearch.GRAPH_SEARCH(state,goal );	/*Uncomment this line to run Iterative Deepening search.*/
//		BidirectionalSearch.GRAPH_SEARCH(state,goal);		/*Uncomment this line to run Bidirectional Search*/
	}
	
	
	static class BreadthFirstSearch {
		public static void GRAPH_SEARCH(Node state, Node goal){
			Queue frontier = new Queue();
			frontier.insert(state);
			System.out.println("Inserted " + state.getNode() + " in frontier");
			
			Queue explored = new Queue();
			
			while (true){
				leafNode = frontier.pop();
				System.out.println(leafNode.getNode() + " removed from frontier and set as leaf node...");
				explored.insert(leafNode);
				System.out.println("Inserted " + leafNode.getNode() + " in explored");

				expand_By_BreadthFirstSearch(leafNode, frontier, explored);
			}
		}
		
		private static void expand_By_BreadthFirstSearch(Node node, Queue frontier, Queue explored) {
			System.out.println("Expanding " + node.getNode() + " into child nodes...");
			Queue queue = new Queue();
			queue = adjacencyMatrix.getQueue(node);
			while (!queue.isEmpty()){
				Node check = new Node();
				check = queue.pop();
				if (!frontier.containsInString(check.getNode()) && !explored.containsInString(check.getNode())){
					check.setParent(node);
					if (check.getNode().equals(goal.getNode())){
						printSolution(check,"by Breadth First Search");
						break;
					}
					frontier.insert(check);
					System.out.println("Inserted " + check.getNode() + " in frontier and its parent node is " + check.getParent().getNode());
				}
			}
		}
	}
	
	
	
	static class UniformCostSearch {
		public static void GRAPH_SEARCH(Node state, Node goal){
			Queue frontier = new Queue();
			frontier.insert(state);
			System.out.println("Inserted " + state.getNode() + " in frontier");
			
			Queue explored = new Queue();
			
			while (true){
				leafNode = frontier.popByPriority();
				System.out.println(leafNode.getNode() + " removed from frontier and set as leaf node...and total path cost is: " + leafNode.getPath_cost());
				if (leafNode.getNode().equals(goal.getNode())){
					printSolution(leafNode,"by Uniform Cost Search");
					break;
				}
				explored.insert(leafNode);
				System.out.println("Inserted " + leafNode.getNode() + " in explored");
				
				expand_By_UniformCostSearch(leafNode, frontier, explored);
			}
		}
		
		private static void expand_By_UniformCostSearch(Node node, Queue frontier, Queue explored) {
			System.out.println("Expanding " + node.getNode() + " into child nodes...");
			Queue queue = new Queue();
			queue = adjacencyMatrix.getQueue(node);
			while (!queue.isEmpty()){
				Node check = new Node();
				check = queue.pop();
				int cost = check.getPath_cost();
				if (!frontier.containsInString(check.getNode()) && !explored.containsInString(check.getNode())){
					check.setPath_cost(node.getPath_cost() + adjacencyMatrix.getStepCost(node, check));
					check.setParent(node);
					frontier.insert(check);
					System.out.println("Inserted " + check.getNode() + " in frontier with path cost:  " + check.getPath_cost() +  " and its parent node is " + check.getParent().getNode());
				}
				
				else if (frontier.containsInString(check.getNode())){
					check.setPath_cost(node.getPath_cost() + adjacencyMatrix.getStepCost(node, check));
					if (cost >= check.getPath_cost()){
						System.out.println("Updated path cost of " + check.getNode() + " from " + cost + " to " + check.getPath_cost());
						check.setParent(node);
					}
					else {
						check.setPath_cost(cost);
					}
				}
			}
		}
	}
	
	
	static class DepthLimitedSearch{
		public static void GRAPH_SEARCH(Node state, Node goal , int limit){
			Queue frontier = new Queue();
			frontier.insert(state);
			System.out.println("Inserted " + state.getNode() + " in frontier");
			Queue explored = new Queue();
			
			Recursive_DepthLimitedSearch(state, frontier, explored, limit);
		
		}
		
		private static void Recursive_DepthLimitedSearch(Node node, Queue frontier, Queue explored, int limit) {
			leafNode = frontier.popLast();
			System.out.println(leafNode.getNode() + " removed from frontier and set as leaf node..." );
			if (leafNode.getNode().equals(goal.getNode())){
				printSolution(leafNode,"by Depth Limited Search");
			}
			else if (limit == 0){
				return;
			}
			explored.insert(leafNode);
			System.out.println("Inserted " + leafNode.getNode() + " in explored");
			System.out.println("Expanding " + node.getNode() + " into child nodes...");
			Queue queue = new Queue();
			queue = adjacencyMatrix.getQueue(node);
			queue.printList();
			while (!queue.isEmpty()){
				Node check = new Node();
				check = queue.popLast();
				System.out.println(check.getNode() + " popped out of queue.");
				if (!frontier.containsInString(check.getNode()) && !explored.containsInString(check.getNode())){
					check.setParent(node);
					frontier.insert(check);
					System.out.println("Inserted " + check.getNode() + " in frontier and its parent node is " + check.getParent().getNode());
					Recursive_DepthLimitedSearch(check, frontier, explored, limit-1);
				}
				else {
					System.out.println(check.getNode()  + " already in explored.");
				}
			}
		}
	}
	
	
	static class IterativeDeepeningSearch{
		public static void GRAPH_SEARCH(Node state, Node goal){
			for (int limit = 0;;limit++){
				System.out.println("***********************************\n\nLimit: " + limit + "\n\n");
				DepthLimitedSearch.GRAPH_SEARCH(state, goal, limit);
			}
		}
	}
	
	
	static class BidirectionalSearch{
		public static Node leafNode1, leafNode2;
		public static void GRAPH_SEARCH(Node state, Node goal){
			Queue frontier1 = new Queue();
			Queue frontier2 = new Queue();
			frontier1.insert(state);
			frontier2.insert(goal);
			System.out.println("Inserted " + state.getNode() + " in frontier1\t||\tInserted " + goal.getNode() + " in frontier2");
			
			Queue explored1 = new Queue();
			Queue explored2 = new Queue();
			
			while (true){
				leafNode1 = frontier1.pop();
				leafNode2 = frontier2.pop();
				System.out.println(leafNode1.getNode() + " removed from frontier1 and set as leaf node\t||\t" + leafNode2.getNode() + " removed from frontier2 and set as leaf node");
				explored1.insert(leafNode1);
				explored2.insert(leafNode2);
				System.out.println("Inserted " + leafNode1.getNode() + " in explored1\t||\tInserted " + leafNode2.getNode() + " in explored2");
				
				expand_By_BidirectionalSearch(leafNode1, leafNode2, frontier1 , frontier2, explored1, explored2);
			}
		}
		
		private static void expand_By_BidirectionalSearch(Node node1, Node node2, Queue frontier1, Queue frontier2, Queue explored1, Queue explored2) {
			System.out.println("Expanding " + node1.getNode() + " into child nodes\t||\tExpanding " + node2.getNode() + " into child nodes");
			Queue queue1 = new Queue();
			Queue queue2 = new Queue();
			queue1 = adjacencyMatrix.getQueue(node1);
			queue2 = adjacencyMatrix.getQueue(node2);
			Node other_parent = null;
			Node intersecting_node = null;
			while (!queue1.isEmpty()){
				Node check1 = new Node();
				check1 = queue1.pop();
				if (!frontier1.containsInString(check1.getNode()) && !explored1.containsInString(check1.getNode())){
					if (check1.getParent() != null){
						other_parent = check1.getParent();
						intersecting_node = check1;
					}
					check1.setParent(node1);
					frontier1.insert(check1);
					System.out.println("Inserted " + check1.getNode() + " in frontier1 and its parent node is " + check1.getParent().getNode());
				}
			}
			System.out.print("Frontier1: ");
			frontier1.printList();
			while (!queue2.isEmpty()){
				Node check2 = new Node();
				check2 = queue2.pop();
				if (!frontier2.containsInString(check2.getNode()) && !explored2.containsInString(check2.getNode())){
					if (check2.getParent() != null){
						other_parent = check2.getParent();
						intersecting_node = check2;
					}
					check2.setParent(node2);
					frontier2.insert(check2);
					System.out.println("Inserted " + check2.getNode() + " in frontier2 and its parent node is " + check2.getParent().getNode());
				}
			}
			System.out.print("Frontier2: ");
			frontier2.printList();
			if (other_parent != null ){
				System.out.println("Intersecting node: " + intersecting_node.getNode() + " with parents " + intersecting_node.getParent().getNode() + " and " + other_parent.getNode());
			}
			if (frontier1.intersect(frontier2)){
				System.out.println("INTERSECTION FOUND");
				printBidirectionalSolution(intersecting_node, other_parent);
				System.exit(0);
			}
		}
		
		private static void printBidirectionalSolution(Node intersecting_node, Node other_parent){
			ArrayList<Node> solution1 = new ArrayList<>();
			ArrayList<Node> solution2 = new ArrayList<>();
			solution1.add(intersecting_node);
			solution2.add(other_parent);
			System.out.println("\n************************************************\nSolution by Bidirectional Search\n");
			while (intersecting_node.getParent() != null){
				solution1.add(intersecting_node.getParent());
				intersecting_node = intersecting_node.getParent();
			}
			while (other_parent.getParent() != null){
				solution2.add(other_parent.getParent());
				other_parent = other_parent.getParent();
			}
			while (solution2.size() != 0){
				int i = solution2.size();
				System.out.print(solution2.get(i-1).getNode());
				System.out.print("	---->	");
				solution2.remove(i-1);
			}
			while (solution1.size() != 0){
				System.out.print(solution1.get(0).getNode());
				if (solution1.size() != 1) {
					System.out.print("	<----	");
				}
				solution1.remove(0);
			}
		}
	}
	
	
	
	private static void printSolution(Node node, String string) {
		ArrayList<Node> solution = new ArrayList<>();
		solution.add(node);
		System.out.println("\n************************************************\nSolution " + string + "\n");
		while (node.getParent() != null){
			solution.add(node.getParent());
			node = node.getParent();
		}
		while (solution.size() != 0){
			int i = solution.size();
			System.out.print(solution.get(i-1).getNode() + "(" + solution.get(i-1).getPath_cost() +  ")" );
			if (solution.size() != 1){
				System.out.print("	---->	");
			}
			solution.remove(i-1);
		}
		System.exit(0);
		
	}
	
	
}
class Node {
	String node;
	Node parent;
	int path_cost = 0;
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
	public int getPath_cost() {
		return path_cost;
	}
	public void setPath_cost(int path_cost) {
		this.path_cost = path_cost;
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
	
	public Node popByPriority(){
		int min=list.get(0).getPath_cost();
		int index=0;
		for (int i=0; i<list.size(); i++){
			System.out.print(list.get(i).getNode() + ": " + list.get(i).getPath_cost() + "  ||  ");
			if(list.get(i).getPath_cost() <= min){
				min = list.get(i).getPath_cost();
				index = i;
			}
		}
		System.out.println();
		Node node = list.get(index);
		list.remove(index);
		return node;
	}
	
	public Node popLast(){
		Node node = list.get(list.size() - 1);
		list.remove(list.size()-1);
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
	
	public void printList(){
		for (int i=0; i<list.size(); i++){
			System.out.print(list.get(i).getNode() + "  ||  ");
		}
		System.out.println();
	}
	
	public boolean intersect(Queue queue){
		for (int i=0; i<list.size(); i++){
			if (queue.containsInString(list.get(i).getNode())){
				return true;
			}
		}
		return false;
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
		matrix[0][1] = 71; matrix[0][8] = 151;
		matrix[1][0] = 71; matrix[1][2] = 75;
		matrix[2][1] = 75; matrix[2][3] = 118; matrix[2][8] = 140;
		matrix[3][2] = 118; matrix[3][4] = 111;
		matrix[4][3] = 111; matrix[4][5] = 70;
		matrix[5][4] = 70; matrix[5][6] = 75;
		matrix[6][5] = 75; matrix[6][7] = 120;
		matrix[7][6] = 120; matrix[7][9] = 146; matrix[7][10] = 138;
		matrix[8][0] = 151; matrix[8][2] = 140; matrix[8][9] = 80; matrix[8][11] = 99;
		matrix[9][7] = 146; matrix[9][8] = 80; matrix[9][10] = 97;
		matrix[10][7] = 138; matrix[10][9] = 97; matrix[10][12] = 101;
		matrix[11][8] = 99; matrix[11][12] = 211;
		matrix[12][10] = 101; matrix[12][11] = 211; matrix[12][13] = 90; matrix[12][14] = 85;
		matrix[13][12] = 90;
		matrix[14][12] = 85; matrix[14][15] = 142; matrix[14][18] = 98;
		matrix[15][14] = 142; matrix[15][16] = 92;
		matrix[16][15] = 92; matrix[16][17] = 87;
		matrix[17][16] = 87;
		matrix[18][14] = 98; matrix[18][19] = 86;
		matrix[19][18] = 86;
		
		
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
			if (matrix[key][k] != 0){
				Node node1 = new Node();
				node1 = hashMap.get(k);
				queue.insert(node1);
			}
		}
		return queue;
	}
	
	public  int getStepCost(Node parent, Node child){
		int index_parent = -1, index_child = -1;
		int i =0;
		while (i<hashMap.size()){
			if(parent.getNode() == hashMap.get(i).getNode()){
				index_parent = i;
			}
			else if (child.getNode() == hashMap.get(i).getNode()){
				index_child = i;
			}
			i++;
		}
		return matrix[index_child][index_parent];
	}
	
	
}








