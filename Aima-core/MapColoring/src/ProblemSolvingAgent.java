import aima.core.search.csp.*;

public class ProblemSolvingAgent {

	public static void main(String[] args){
		MapCSP mapCSP = new MapCSP();
		System.out.println("Constraints = " + mapCSP.getConstraints());
		String line = new String(new char[101]).replace('\0', '-');
		System.out.println(line);
		System.out.format("| %-9S | %18S |%66S|\n", "variables","domains","corresponding constraints");
		System.out.println(line);
		mapCSP.getVariables().forEach(
				var -> System.out.format("| %-9s | %-18s | %-64s |%n", var,
						mapCSP.getDomain(var), mapCSP.getConstraints(var))
		);
		System.out.println(line);
		
		MapCSPwithBinaryConstraint mapCSPwithBinaryConstraint = new MapCSPwithBinaryConstraint();
		
		CspListener.StepCounter<Variable, String> stepCounter = new CspListener.StepCounter<>();
		CspSolver<Variable, String> solver;
		
		
		solver = new FlexibleBacktrackingSolver<>();
		solver.addCspListener(stepCounter);
		stepCounter.reset();
		System.out.println("Map Coloring (Backtracking with AllDiff constraint)");
		System.out.println(solver.solve(mapCSP));
		System.out.println(stepCounter.getResults() + "\n");
		
		solver = new FlexibleBacktrackingSolver<>();
		solver.addCspListener(stepCounter);
		stepCounter.reset();
		System.out.println("Map Coloring (Backtracking with Not Equal constraint)");
		System.out.println(solver.solve(mapCSPwithBinaryConstraint));
		System.out.println(stepCounter.getResults() + "\n");
		
		solver = new FlexibleBacktrackingSolver<Variable, String>().setAll();
		solver.addCspListener(stepCounter);
		stepCounter.reset();
		System.out.println("Map Coloring (Backtracking + MRV & DEG + LCV + AC3) with not equal constraint");
		System.out.println(solver.solve(mapCSPwithBinaryConstraint));
		System.out.println(stepCounter.getResults() + "\n");


		solver = new MinConflictsSolver<>(1000);
		solver.addCspListener(stepCounter);
		stepCounter.reset();
		System.out.println("Map Coloring (Minimum Conflicts) with AllDiff constraint");
		System.out.println(solver.solve(mapCSP));
		System.out.println(stepCounter.getResults() + "\n");
		
		solver = new MinConflictsSolver<>(1000);
		solver.addCspListener(stepCounter);
		stepCounter.reset();
		System.out.println("Map Coloring (Minimum Conflicts) with NotEqual constraint");
		System.out.println(solver.solve(mapCSPwithBinaryConstraint));
		System.out.println(stepCounter.getResults() + "\n");
		
		solver = new TreeCspSolver<>();
		solver.addCspListener(stepCounter);
		stepCounter.reset();
		System.out.println("Map Coloring (TreeCSP)");
		System.out.println(solver.solve(new TreeCSP()));
		System.out.println(stepCounter.getResults() + "\n");
	}
}
