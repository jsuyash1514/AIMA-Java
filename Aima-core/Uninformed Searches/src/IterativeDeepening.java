import aima.core.environment.map.*;
import aima.core.search.framework.problem.*;
import aima.core.search.uninformed.IterativeDeepeningSearch;

public class IterativeDeepening {
	public static void main (String[] args){
		
		Map romaniaMap = new SimplifiedRoadMapOfPartOfRomania();
		Problem<String, MoveToAction> problem = new GeneralProblem<>(
				SimplifiedRoadMapOfPartOfRomania.ARAD,
				MapFunctions.createActionsFunction(romaniaMap),
				MapFunctions.createResultFunction(),
				GoalTest.isEqual(SimplifiedRoadMapOfPartOfRomania.BUCHAREST),
				MapFunctions.createDistanceStepCostFunction(romaniaMap));
		
		IterativeDeepeningSearch iterativeDeepeningSearch = new IterativeDeepeningSearch();
		System.out.println(iterativeDeepeningSearch.findActions(problem));
	}
}
