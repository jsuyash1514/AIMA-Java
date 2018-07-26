import aima.core.agent.*;
import aima.core.environment.map.ExtendableMap;
import aima.core.environment.map.MapEnvironment;
import aima.core.environment.map.MapFunctions;
import aima.core.search.framework.problem.GeneralProblem;
import aima.core.search.framework.problem.GoalTest;
import aima.core.search.framework.problem.OnlineSearchProblem;
import aima.core.search.online.LRTAStarAgent;

import java.util.function.ToDoubleFunction;

public class LRTA_Star {
	private static ExtendableMap extendableMap;
	private static StringBuffer envChange;
	private static ToDoubleFunction<String>  h;
	
	public static void main(String[] args){
		extendableMap = new ExtendableMap();
		extendableMap.addBidirectionalLink("1,1", "1,2", 1.0);
		extendableMap.addBidirectionalLink("1,1", "2,1", 1.0);
		extendableMap.addBidirectionalLink("2,1", "3,1", 1.0);
		extendableMap.addBidirectionalLink("2,1", "2,2", 1.0);
		extendableMap.addBidirectionalLink("3,1", "3,2", 1.0);
		extendableMap.addBidirectionalLink("2,2", "2,3", 1.0);
		extendableMap.addBidirectionalLink("3,2", "3,3", 1.0);
		extendableMap.addBidirectionalLink("2,3", "1,3", 1.0);
		
		envChange = new StringBuffer();
		
		MapEnvironment mapEnvironment = new MapEnvironment(extendableMap);
		OnlineSearchProblem problem = new GeneralProblem(null,
				MapFunctions.createActionsFunction(extendableMap),
				null,
				GoalTest.isEqual("3,3"),
				MapFunctions.createDistanceStepCostFunction(extendableMap));
		
		h = (state) -> 1.0;
		
		LRTAStarAgent lrtaStarAgent = new LRTAStarAgent(problem,MapFunctions.createPerceptToStateFunction() ,h);
		
		
		mapEnvironment.addAgent(lrtaStarAgent, "1,1");
		mapEnvironment.addEnvironmentView(new EnvironmentView() {
			public void notify(String msg) {
				envChange.append(msg).append(" -> ");
			}
			
			public void agentAdded(Agent agent, Environment source) {
			}
			
			public void agentActed(Agent agent, Percept percept, Action action, Environment source) {
				envChange.append(action).append(" -> ");
			}
		});
		mapEnvironment.stepUntilDone();
		
		System.out.println(envChange.toString());
		
	
	}
}
