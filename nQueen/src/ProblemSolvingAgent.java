public class ProblemSolvingAgent {
	
	
	public static void main(String[] args) {
		int n = 8;
		int[] q = new int[n];
		enumerate(q, 0);
	}
	
	public static void enumerate(int[] q, int k) {
		int n = q.length;
		if (k == n) System.exit(0);
		else {
			for (int i = 0; i < n; i++) {
				q[k] = i;
				System.out.println("\nk: " + k  + "\n");
				if (isConsistent(q, k)) {
					printQueens(q);
					enumerate(q, k+1);
				}
			}
		}
	}
	
	public static boolean isConsistent(int[] q, int k) {
		for (int i = 0; i < k; i++) {
			if (q[i] == q[k])             return false;   // same column
			if ((q[i] - q[k]) == (k - i)) return false;   // same major diagonal ("\")
			if ((q[k] - q[i]) == (k - i)) return false;   // same minor diagonal ("/")
		}
		return true;
	}
	
	public static void printQueens(int[] q) {
		int n = q.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (q[j] == i) System.out.print("Q ");
				else           System.out.print("* ");
			}
			System.out.println();
		}
		System.out.println("\n\n----------------------------------------- \n\n");
	}
}
