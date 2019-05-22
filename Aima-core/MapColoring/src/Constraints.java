import java.util.Arrays;
import java.util.List;


public class Constraints {
	public static final List<AllDiffConstraint> list = Arrays.asList(
			new AllDiffConstraint(Arrays.asList(Variables.WA, Variables.NT, Variables.SA)),
			new AllDiffConstraint(Arrays.asList(Variables.NT, Variables.SA, Variables.Q)),
			new AllDiffConstraint(Arrays.asList(Variables.SA, Variables.Q, Variables.NSW)),
			new AllDiffConstraint(Arrays.asList(Variables.SA, Variables.NSW, Variables.V))
	);
	private Constraints() {}
}