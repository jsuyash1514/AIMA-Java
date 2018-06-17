import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/*	This program generates random 8Puzzle and solves it(if solvable) by Best-First Search algorithm.		*/
/*	(It adds only child/children with minimum heuristic in frontier ).		*/

/*	Computed solution may or may not be optimal.	*/


class ProblemSolvingAgent {
	
	static Matrix state = new Matrix();
	static Matrix goal = new Matrix();
	static Matrix leaf = new Matrix();
	static int g=0,h;
	
	public static void initiate(){
		state.createMatrix();
		
//		int[][] initial = new int[3][3];
//		initial[0][0] = 7;
//		initial[0][1] = 2;
//		initial[0][2] = 4;
//		initial[1][0] = 5;
//		initial[1][1] = 0;
//		initial[1][2] = 6;
//		initial[2][0] = 8;
//		initial[2][1] = 3;
//		initial[2][2] = 1;
//		state.setMatrix(initial);
		
		
		state.setParent(null);
		h = state.getHeuristic();
		state.print(g);
		System.out.println("Initial Matrix");
		
		int k=0;
		int[][] goalMatrix = new int[3][3];
		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				goalMatrix[i][j] = k;
				k++;
			}
		}
		
		if (!state.isSolvable()){
			System.out.println("\n\nThis matrix is not solvable");
			System.exit(0);
		}
		else {
			goal.setMatrix(goalMatrix);
		}
		
		
		System.out.println("\n******************************\n");
		System.out.println("Process initiated...");
		
		
		goal.setMatrix(goalMatrix);
		
		leaf = state;
	}
	
	public static void main(String[] args){
		List<Matrix> explored = new ArrayList<>();
		List<Matrix> frontier = new ArrayList<>();
		initiate();
		frontier.add(state);
		while (true){
			expand(explored, frontier);
		}
		
	}
	
	public static void expand(List<Matrix> explored, List<Matrix> frontier){
		leaf = frontier.get(0);
		frontier.remove(0);
		explored.add(leaf);
		if (leaf.equals(goal)){
			System.out.println("\n\n\nGOAL REACHED!!!");
			printSolution(leaf);
			System.exit(0);
		}
		List<Matrix> queue = new ArrayList<>();
		queue = leaf.getChildren();
		int min = queue.get(0).getHeuristic();
		for (int i=0; i<queue.size(); i++){
			if (queue.get(i).getHeuristic() <= min && !queue.get(i).existsIn(explored)){
				min = queue.get(i).getHeuristic();
			}
		}
		for (int i=0; i<queue.size(); i++){
			if (queue.get(i).existsIn(explored) || queue.get(i).existsIn(frontier)){
				continue;
			}
			else if (queue.get(i).getHeuristic() == min){
			queue.get(i).setParent(leaf);
			frontier.add(queue.get(i));
			}
		}
	}
	
	public static void printSolution(Matrix leaf){
		System.out.println("\n\nPrinting Solution");
		List<Matrix> matrices = new ArrayList<>();
		matrices.add(leaf);
		while (leaf.getParent() != null){
			matrices.add(leaf.getParent());
			leaf = leaf.getParent();
		}
		
		while (matrices.size() != 0){
			matrices.get(matrices.size()-1).print(g);
			matrices.remove(matrices.size()-1);
			g++;
		}
	}
	
	
}

class Matrix{
	int[][] matrix = new int[3][3];
	List<Integer> goal = new ArrayList<>();
	int f;
	Matrix parent;
	
	public Matrix getParent() {
		return parent;
	}
	
	public void setParent(Matrix parent) {
		this.parent = parent;
	}
	
	public void createMatrix(){
		for (int i=0;i<9;i++){
			goal.add(i);
		}
		Collections.shuffle(goal);
		int k = 0;
		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				matrix[i][j] = goal.get(k);
				k++;
			}
		}
	}
	
	public void setMatrix(int[][] matrix1){
		for (int i=0;i<3;i++){
			for (int j=0; j<3; j++){
				matrix[i][j] = matrix1[i][j];
			}
		}
	}
	
	public int getIndex(int value){
		int index = -1;
		for (int i=0; i<3; i++){
			for (int j=0;j<3;j++){
				if (matrix[i][j] == value){
					index =  ((i*10) + j);
				}
			}
		}
		return index;
	}
	
	public boolean existsIn(List<Matrix> explored){
		for (int i=0; i<explored.size(); i++){
			int c =0;
			for (int j=0; j<3; j++){
				for (int k=0; k<3; k++){
					if (explored.get(i).getMatrix()[j][k] != getMatrix()[j][k]) c++;
				}
			}
			if (c == 0){
				return true;
			}
		}
		return false;
	}
	
	public int getHeuristic(){
		int index, row, column;
		int heuristic = 0;
		int k=1;
		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				if (i==0 && j==0) continue;
				index = getIndex(k);
				row = index/10;
				column = index%10;
				heuristic += (Math.abs(row-i) + Math.abs(column-j));
				k++;
			}
		}
		return heuristic;
	}
	
	public void print(int g){
		System.out.println("\n******************************\n");
		for (int i=0; i<3; i++){
			for (int j=0; j<3; j++){
				System.out.print(matrix[i][j] + "	");
			}
			System.out.println();
		}
		System.out.println("\nh(n): " + getHeuristic() + "\ng(n): " + g);
	}
	
	public int[][] getMatrix(){
		return matrix;
	}
	
	public boolean equals(Matrix matrix1){
		int c =0;
		for (int i=0;i<3;i++){
			for (int j=0; j<3; j++){
				if (matrix1.getMatrix()[i][j] != matrix[i][j]) {
					c++;
				}
			}
		}
		if (c == 0){
			return true;
		}
		return false;
	}
	
	public boolean isSolvable(){
		List<Integer> check = new ArrayList<>();
		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				check.add(matrix[i][j]);
			}
		}
		int inversion = 0;
		for (int i=0;i<check.size();i++){
			for (int j=i+1; j<check.size(); j++){
				if (check.get(j) == 0) continue;
				if (check.get(i)>check.get(j)){
					inversion++;
				}
			}
		}
		System.out.println("Inversion: " + inversion);
		if (inversion%2 == 0){
			return true;
		}
		return false;
	}
	
	public void setF(int g, int h){
		f=g+h;
	}
	
	public int getF(){
		return f;
	}
	
	
	public List<Matrix> getChildren(){
		List<Matrix> child = new ArrayList<Matrix>();
		int index = getIndex(0);
		if (index == -1){
			System.out.println("Negative index of 0");
			System.exit(0);
		}
		int row = index/10;
		int column = index%10;
		if (row-1 >= 0){
			int[][] childmatrix = new int[3][3];
			Matrix children = new Matrix();
			for (int i=0; i<3; i++){
				for (int j=0; j<3; j++){
					childmatrix[i][j] = matrix[i][j];
				}
			}
			int temp = matrix[row-1][column];
			childmatrix[row][column] = temp;
			childmatrix[row-1][column] = 0;
			children.setMatrix(childmatrix);
			child.add(children);
		}
		if (row+1 < 3){
			int[][] childmatrix = new int[3][3];
			Matrix children = new Matrix();
			for (int i=0; i<3; i++){
				for (int j=0; j<3; j++){
					childmatrix[i][j] = matrix[i][j];
				}
			}
			int temp = matrix[row+1][column];
			childmatrix[row][column] = temp;
			childmatrix[row+1][column] = 0;
			children.setMatrix(childmatrix);
			child.add(children);
		}
		if (column-1 >= 0){
			int[][] childmatrix = new int[3][3];
			Matrix children = new Matrix();
			for (int i=0; i<3; i++){
				for (int j=0; j<3; j++){
					childmatrix[i][j] = matrix[i][j];
				}
			}
			int temp = matrix[row][column-1];
			childmatrix[row][column] = temp;
			childmatrix[row][column-1] = 0;
			children.setMatrix(childmatrix);
			child.add(children);
		}
		if (column+1 < 3){
			int[][] childmatrix = new int[3][3];
			Matrix children = new Matrix();
			for (int i=0; i<3; i++){
				for (int j=0; j<3; j++){
					childmatrix[i][j] = matrix[i][j];
				}
			}
			int temp = matrix[row][column+1];
			childmatrix[row][column] = temp;
			childmatrix[row][column+1] = 0;
			children.setMatrix(childmatrix);
			child.add(children);
		}
		return child;
	}
}