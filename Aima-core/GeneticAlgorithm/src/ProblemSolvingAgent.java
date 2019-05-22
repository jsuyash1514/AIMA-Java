import aima.core.environment.nqueens.NQueensGenAlgoUtil;
import aima.core.search.framework.problem.GoalTest;
import aima.core.search.local.FitnessFunction;
import aima.core.search.local.GeneticAlgorithm;
import aima.core.search.local.Individual;

import java.util.*;

public class ProblemSolvingAgent {
	
	public static void main(String[] args) {
		int boardSize = 8;
		try {
			FitnessFunction<Integer> fitnessFunction = NQueensGenAlgoUtil.getFitnessFunction();
			GoalTest<Individual<Integer>> goalTest = NQueensGenAlgoUtil.getGoalTest();
			// Generate an initial population
			Set<Individual<Integer>> population = new HashSet<>();
			for (int i = 0; i < 50; i++) {
				population.add(NQueensGenAlgoUtil.generateRandomIndividual(boardSize));
			}
			
			GeneticAlgorithm<Integer> ga = new GeneticAlgorithm<>(boardSize,
					NQueensGenAlgoUtil.getFiniteAlphabetForBoardOfSize(boardSize), 0.15);
			
			Individual<Integer> bestIndividual  = ga.geneticAlgorithm(population, fitnessFunction, goalTest, 0L);
			
			System.out.println("");
			System.out.println("Goal Test Best Individual=\n" + NQueensGenAlgoUtil.getBoardForIndividual(bestIndividual));
			System.out.println("Board Size      = " + boardSize);
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.test(bestIndividual));
			System.out.println("Population Size = " + ga.getPopulationSize());
			System.out.println("Itertions       = " + ga.getIterations());
			System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
