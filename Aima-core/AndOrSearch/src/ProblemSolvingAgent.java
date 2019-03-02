import aima.core.agent.Action;
import aima.core.environment.vacuum.*;
import aima.core.search.agent.NondeterministicSearchAgent;
import aima.core.search.nondeterministic.AndOrSearch;
import aima.core.search.nondeterministic.NondeterministicProblem;
import static aima.core.environment.vacuum.VacuumEnvironment.*;

public class ProblemSolvingAgent {
	public static void main(String[] args){
		NondeterministicSearchAgent<VacuumEnvironmentState, Action> agent = new NondeterministicSearchAgent<>(percept -> (VacuumEnvironmentState) percept);
		
		NondeterministicVacuumEnvironment world = new NondeterministicVacuumEnvironment(LocationState.Dirty, LocationState.Dirty);
		world.addAgent(agent, LOCATION_A);
		
		NondeterministicProblem<VacuumEnvironmentState, Action> problem = new NondeterministicProblem<>(
				(VacuumEnvironmentState) world.getCurrentState(),
				VacuumWorldFunctions::getActions,
				VacuumWorldFunctions.createResultsFunction(agent),
				VacuumWorldFunctions::testGoal,
				(s, a, sPrimed) -> 1.0);
		
		AndOrSearch andOrSearch = new AndOrSearch();

		agent.makePlan(problem);
		System.out.println(agent.getPlan());
		
		System.out.println(andOrSearch.search(problem));
	}
}
