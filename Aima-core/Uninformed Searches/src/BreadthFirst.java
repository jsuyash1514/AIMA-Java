import aima.core.environment.map.*;
import aima.core.search.framework.problem.*;
import aima.core.search.framework.*;
import aima.core.search.framework.qsearch.*;
import aima.core.search.uninformed.BreadthFirstSearch;

import java.util.Queue;

public class BreadthFirst {
	static QueueFactory queueFactory = new QueueFactory();
	static Queue<Node> frontier ;
	
	
	
	public static void main(String[] args){
	
		/*Method-1: Using GraphSearcBFS*/
		
		Map romaniaMap = new SimplifiedRoadMapOfPartOfRomania();
		Problem<String, MoveToAction> problem = new GeneralProblem<>(
				SimplifiedRoadMapOfPartOfRomania.ARAD,
				MapFunctions.createActionsFunction(romaniaMap),
				MapFunctions.createResultFunction(),
				GoalTest.isEqual(SimplifiedRoadMapOfPartOfRomania.BUCHAREST),
				MapFunctions.createDistanceStepCostFunction(romaniaMap));
	
		GraphSearchBFS graphSearchBFS = new GraphSearchBFS();
		frontier = queueFactory.createFifoQueue();
	
		System.out.println(	graphSearchBFS.findNode(problem, frontier));
		
		System.out.println("\n\n***********************************************************\n\n");
		
		
		/*Method-2: Using Breadth First Search*/
		
		BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();
		System.out.println(breadthFirstSearch.findActions(problem));
	}
}
