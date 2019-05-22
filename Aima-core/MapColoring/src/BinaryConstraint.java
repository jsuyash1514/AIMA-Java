import aima.core.search.csp.examples.NotEqualConstraint;

import java.util.Arrays;
import java.util.List;

public class BinaryConstraint {
	public static final List<NotEqualConstraint> list = Arrays.asList(
			new NotEqualConstraint<>(Variables.WA, Variables.NT),
			new NotEqualConstraint<>(Variables.WA, Variables.SA),
			new NotEqualConstraint<>(Variables.NT, Variables.SA),
			new NotEqualConstraint<>(Variables.NT, Variables.Q),
			new NotEqualConstraint<>(Variables.SA, Variables.Q),
			new NotEqualConstraint<>(Variables.SA, Variables.NSW),
			new NotEqualConstraint<>(Variables.SA, Variables.V),
			new NotEqualConstraint<>(Variables.Q, Variables.NSW),
			new NotEqualConstraint<>(Variables.NSW, Variables.V)
	);
	private BinaryConstraint() {}
}
