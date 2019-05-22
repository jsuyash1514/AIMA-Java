import aima.core.search.csp.Constraint;
import aima.core.search.csp.Variable;
import aima.core.search.csp.Assignment;
import aima.core.search.csp.examples.NotEqualConstraint;
import aima.core.util.math.permute.CombinationGenerator;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

public class AllDiffConstraint<VAR extends Variable, VAL> implements Constraint<VAR, VAL>, Cloneable {
	
	private List<VAR> variables;
	
	public AllDiffConstraint(List<VAR> variables) {
		this.variables = variables;
	}
	
	@Override
	public List<VAR> getScope() {
		return variables;
	}
	
	@Override
	public boolean isSatisfiedWith(Assignment<VAR, VAL> assignment) {
		HashSet<VAL> valueSet = new HashSet();
		for (VAR variable: variables) {
			if (assignment.contains(variable)) {
				VAL value = assignment.getValue(variable);
				if (!valueSet.add(value)) {
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sbl = new StringBuilder();
		sbl.append("AD(");
		for (Variable variable: variables.subList(0, variables.size() - 1)) {
			sbl.append(variable.toString()).append(", ");
		}
		sbl.append(variables.get(variables.size() - 1)).append(")");
		return sbl.toString();
	}
	
	public List<NotEqualConstraint> toNotEqualConstraint() {
		List<NotEqualConstraint> constraints = new ArrayList();
		CombinationGenerator.generateCombinations(variables, 2).forEach(
				list -> constraints.add(new NotEqualConstraint(list.get(0), list.get(1)))
		);
		return constraints;
	}
	
}