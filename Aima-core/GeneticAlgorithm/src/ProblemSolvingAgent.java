import aima.core.search.framework.problem.GoalTest;
import aima.core.search.local.FitnessFunction;
import aima.core.search.local.GeneticAlgorithm;
import aima.core.search.local.Individual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProblemSolvingAgent {
	
	public static void main(String[] args){
		
		List<Integer> list = new ArrayList<>();
		for(int i = 1; i <= 8; i++){
			list.add(i);
		}
		
		
		FitnessFunction fitnessFunction = new FitnessFunction() {
			@Override
			public double apply(Individual individual) {
				double h=0;
				for (int i=0; i<8; i++){
					double pos =  Double.valueOf(individual.getRepresentation().get(i).toString());
					for (int j=i+1; j<8; j++){
						if(pos == Double.valueOf(individual.getRepresentation().get(j).toString())){
							h++;
						}
					}
					int t = 1;
					for (int j = i+1; j<8 && (pos-t)>0 ; j++,t++){
						if(pos-t == Double.valueOf(individual.getRepresentation().get(j).toString())){
							h++;
						}
					}
					t = 1;
					for (int j = i+1; j<8 && (pos+t)<=8 ; j++,t++){
						if(pos+t == Double.valueOf(individual.getRepresentation().get(j).toString())){
							h++;
						}
					}
				}
				return 28-h;
			}
		};
		
		GoalTest goalTest = new GoalTest() {
			@Override
			public boolean test(Object o) {
				if(fitnessFunction.apply((Individual) o)==28) return true;
				else return false;
			}
		};
		
		List<Individual> initPopulation = new ArrayList<>();
		for (int i=0; i<4; i++){
			Collections.shuffle(list);
			Individual individual = new Individual(list);
			System.out.println("Individual of initial population: " + individual.getRepresentation() + "\t Fitness: " + fitnessFunction.apply(individual));
			initPopulation.add(individual);
		}
		
		GeneticAlgorithm algorithm = new GeneticAlgorithm(8,list, 0);
		Individual resultIndividual = algorithm.geneticAlgorithm(initPopulation, fitnessFunction, 20);
		System.out.println(resultIndividual.getRepresentation());
		System.out.println("Fitness: " + fitnessFunction.apply(resultIndividual));
		
	}
}
