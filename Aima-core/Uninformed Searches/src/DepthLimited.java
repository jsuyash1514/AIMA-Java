import aima.core.environment.map.*;
import aima.core.search.framework.problem.*;
import aima.core.search.uninformed.DepthLimitedSearch;

import java.util.HashSet;


public class DepthLimited {
	public static void main(String[] args){
		
		Map romaniaMap = new SimplifiedRoadMapOfPartOfRomania();
		Problem<String, MoveToAction> problem = new GeneralProblem<>(
				SimplifiedRoadMapOfPartOfRomania.ARAD,
				MapFunctions.createActionsFunction(romaniaMap),
				MapFunctions.createResultFunction(),
				GoalTest.isEqual(SimplifiedRoadMapOfPartOfRomania.BUCHAREST),
				MapFunctions.createDistanceStepCostFunction(romaniaMap));
		DepthLimitedSearch depthLimitedSearch = new DepthLimitedSearch(20);
		System.out.println(depthLimitedSearch.findActions(problem));
	}
}
