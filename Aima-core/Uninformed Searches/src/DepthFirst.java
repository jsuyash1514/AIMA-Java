import aima.core.environment.map.*;
import aima.core.search.framework.Node;
import aima.core.search.framework.NodeExpander;
import aima.core.search.framework.problem.*;
import aima.core.search.framework.qsearch.QueueSearch;
import aima.core.search.uninformed.DepthFirstSearch;

import java.util.Stack;

public class DepthFirst {
	public static void main(String[] args){
		Stack<Node> stack = new Stack<>();

		QueueSearch queueSearch = new QueueSearch(new NodeExpander()) {
			@Override
			protected void addToFrontier(Node node) {
				if (!frontier.contains(node.getState()))
				stack.add(node);
				updateMetrics(frontier.size());
			}

			@Override
			protected Node removeFromFrontier() {
				Node result = stack.pop();
				frontier.add(result.getState());
				return result;
				
			}

			@Override
			protected boolean isFrontierEmpty() {
				return stack.isEmpty();
			}
		};

		Map romaniaMap = new SimplifiedRoadMapOfPartOfRomania();
		Problem<String, MoveToAction> problem = new GeneralProblem<>(
				SimplifiedRoadMapOfPartOfRomania.ARAD,
				MapFunctions.createActionsFunction(romaniaMap),
				MapFunctions.createResultFunction(),
				GoalTest.isEqual(SimplifiedRoadMapOfPartOfRomania.BUCHAREST),
				MapFunctions.createDistanceStepCostFunction(romaniaMap));

		DepthFirstSearch depthFirstSearch = new DepthFirstSearch(queueSearch);
		System.out.println(depthFirstSearch.findActions(problem));
	}
}


