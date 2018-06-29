import aima.core.environment.map.*;
import aima.core.search.framework.problem.*;
import aima.core.search.uninformed.UniformCostSearch;

public class UniformCost {
	
	public static void main(String[] args){
		
		Map romaniaMap = new SimplifiedRoadMapOfPartOfRomania();
		Problem<String, MoveToAction> problem = new GeneralProblem<>(
				SimplifiedRoadMapOfPartOfRomania.ARAD,
				MapFunctions.createActionsFunction(romaniaMap),
				MapFunctions.createResultFunction(),
				GoalTest.isEqual(SimplifiedRoadMapOfPartOfRomania.BUCHAREST),
				MapFunctions.createDistanceStepCostFunction(romaniaMap));
		
		UniformCostSearch uniformCostSearch = new UniformCostSearch();
		System.out.println(uniformCostSearch.findActions(problem));
		
	}
}
