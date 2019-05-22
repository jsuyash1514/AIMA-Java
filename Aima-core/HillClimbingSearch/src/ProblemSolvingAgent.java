import aima.core.agent.Action;
import aima.core.environment.nqueens.NQueensBoard;
import aima.core.environment.nqueens.NQueensFunctions;
import aima.core.environment.nqueens.QueenAction;
import aima.core.search.agent.SearchAgent;
import aima.core.search.framework.problem.Problem;
import aima.core.search.local.HillClimbingSearch;

public class ProblemSolvingAgent {
	public static void main(String[] args) throws Exception {
		int boardSize = 8;
		Problem<NQueensBoard, QueenAction> problem =
				NQueensFunctions.createCompleteStateFormulationProblem(boardSize, NQueensBoard.Config.QUEEN_IN_EVERY_COL);
		HillClimbingSearch<NQueensBoard, QueenAction> search = new HillClimbingSearch<>
				(NQueensFunctions.createAttackingPairsHeuristicFunction());
		SearchAgent<NQueensBoard, QueenAction> agent = new SearchAgent<>(problem, search);
		
		for (Action action : agent.getActions()) {
			System.out.println(action.toString());
		}
		System.out.println("Search Outcome=" + search.getOutcome());
		System.out.println("Final State=\n" + search.getLastSearchState());
	}
}
