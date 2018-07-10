import aima.core.environment.eightpuzzle.EightPuzzleBoard;
import aima.core.environment.eightpuzzle.EightPuzzleFunctions;
import aima.core.agent.Action;
import aima.core.search.framework.problem.GoalTest;
import aima.core.search.framework.problem.GeneralProblem;
import aima.core.search.framework.problem.Problem;
import java.util.*;

public class Eight_Puzzle {

	public static void main(String[] args){
		
		EightPuzzleBoard board = new EightPuzzleBoard(new int[] { 7, 1, 8, 0, 4, 6, 2, 3, 5 });
		Problem<EightPuzzleBoard, Action> problem = new GeneralProblem<>(board, EightPuzzleFunctions::getActions,
				EightPuzzleFunctions::getResult,
				GoalTest.isEqual(EightPuzzleFunctions.GOAL_STATE));
		System.out.println("Initial State = " + Arrays.toString(problem.getInitialState().getState()));
		System.out.println("Available Actions = " + problem.getActions(problem.getInitialState()).toString());
		System.out.println("Resulting State = " + Arrays.toString(problem.getResult(problem.getInitialState(),
				problem.getActions(problem.getInitialState()).get(0))
				.getState()));
		System.out.println("isGoal\t"+ problem.testGoal(problem.getInitialState()));
	}
	
}
