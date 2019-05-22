import aima.core.search.csp.Variable;

import java.util.Arrays;
import java.util.List;

public class Variables {
	public static final Variable WA = new Variable("WA");
	public static final Variable NT = new Variable("NT");
	public static final Variable Q = new Variable("Q");
	public static final Variable NSW = new Variable("NSW");
	public static final Variable V = new Variable("V");
	public static final Variable SA = new Variable("SA");
	public static final Variable T = new Variable("T");
	
	public static final List<Variable> list = Arrays.asList(NSW, WA, NT, Q, SA, V, T);
	
	private Variables() {}
}