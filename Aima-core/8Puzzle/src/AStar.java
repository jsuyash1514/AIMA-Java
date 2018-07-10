import aima.core.environment.eightpuzzle.EightPuzzleBoard;
import aima.core.environment.eightpuzzle.EightPuzzleFunctions;
import aima.core.agent.Action;
import aima.core.search.agent.SearchAgent;
import aima.core.search.framework.Node;
import aima.core.search.framework.SearchForActions;
import aima.core.search.framework.problem.GoalTest;
import aima.core.search.framework.problem.GeneralProblem;
import aima.core.search.framework.problem.Problem;
import aima.core.search.framework.qsearch.GraphSearch;
import aima.core.search.informed.AStarSearch;

import java.util.*;

public class AStar {
	
	public static void main(String[] args){
		
		EightPuzzleBoard board = new EightPuzzleBoard(new int[] { 7, 1, 8, 0, 4, 6, 2, 3, 5 });
		Problem<EightPuzzleBoard, Action> problem = new GeneralProblem<>(board, EightPuzzleFunctions::getActions,
				EightPuzzleFunctions::getResult,
				GoalTest.isEqual(EightPuzzleFunctions.GOAL_STATE));
		
		// By Manhattan Heuristic function

		int[] state = problem.getInitialState().getState();
		EightPuzzleFunctions eightPuzzleFunctions = new EightPuzzleFunctions();
		
		try {
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>(new GraphSearch<>(),
					EightPuzzleFunctions.createManhattanHeuristicFunction());
			SearchAgent<EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			List<Action> actions = agent.getActions().subList(0, agent.getActions().size());
			for (int i=0;i<actions.size();i++){
				System.out.print("Current State = " + Arrays.toString(state));
				EightPuzzleBoard eightPuzzleBoard = new EightPuzzleBoard(state);
				System.out.print("\t\tManhattan Heuristic = " + eightPuzzleFunctions.createManhattanHeuristicFunction().applyAsDouble(new Node<>(eightPuzzleBoard)));
				System.out.print("\t\tisGoal = "+ problem.testGoal(eightPuzzleBoard));
				System.out.print("\t\tPath length = " + i);
				System.out.println("\t\t" + actions.get(i));
				state = problem.getResult(eightPuzzleBoard, actions.get(i)).getState();
				System.out.println();
			}
			EightPuzzleBoard eightPuzzleBoard = new EightPuzzleBoard(state);
			System.out.print("Final State = " + Arrays.toString(state));
			System.out.print("\t\tManhattan Heuristic = " + eightPuzzleFunctions.createManhattanHeuristicFunction().applyAsDouble(new Node<>(eightPuzzleBoard)));
			System.out.print("\t\tisGoal = "+ problem.testGoal(eightPuzzleBoard));
			System.out.print("\t\tPath length = " + agent.getActions().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("\n\n**************************\n\n");
		
		// By Misplaced Tiles Heuristic function
		
		state = problem.getInitialState().getState();
		
		try {
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>(new GraphSearch<>(),
					EightPuzzleFunctions.createMisplacedTileHeuristicFunction());
			SearchAgent<EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			List<Action> actions = agent.getActions().subList(0, agent.getActions().size());
			for (int i=0;i<actions.size();i++){
				System.out.print("Current State = " + Arrays.toString(state));
				EightPuzzleBoard eightPuzzleBoard = new EightPuzzleBoard(state);
				System.out.print("\t\tMisplaced Tiles Heuristic = " + eightPuzzleFunctions.createMisplacedTileHeuristicFunction().applyAsDouble(new Node<>(eightPuzzleBoard)));
				System.out.print("\t\tisGoal = "+ problem.testGoal(eightPuzzleBoard));
				System.out.print("\t\tPath length = " + i);
				System.out.println("\t\t" + actions.get(i));
				state = problem.getResult(eightPuzzleBoard, actions.get(i)).getState();
				System.out.println();
			}
			EightPuzzleBoard eightPuzzleBoard = new EightPuzzleBoard(state);
			System.out.print("Final State = " + Arrays.toString(state));
			System.out.print("\t\tMisplaced Tiles Heuristic = " + eightPuzzleFunctions.createMisplacedTileHeuristicFunction().applyAsDouble(new Node<>(eightPuzzleBoard)));
			System.out.print("\t\tisGoal = "+ problem.testGoal(eightPuzzleBoard));
			System.out.print("\t\tPath length = " + agent.getActions().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
