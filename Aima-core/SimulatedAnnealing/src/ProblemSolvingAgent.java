import aima.core.agent.Action;
import aima.core.search.local.SimulatedAnnealingSearch;

public class ProblemSolvingAgent {
	
	public static void main(String[] args) {
		SimulatedAnnealingSearch<String, Action> search = new SimulatedAnnealingSearch<>(null);
		int deltaE = -1; //bad move as ΔE<0
		double lowerTemperature = 1.0;
		double higherTemperature = 20.0;//bad moves are likely to be accepted at higher temperature
		
		System.out.println("Probability of acceptance of move at lower temperature: " + search.probabilityOfAcceptance(lowerTemperature, deltaE));
		System.out.println("Probability of acceptance of move at higher temperature: " + search.probabilityOfAcceptance(higherTemperature, deltaE));
	}
}
