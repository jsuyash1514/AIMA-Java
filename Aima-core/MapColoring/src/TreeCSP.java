import aima.core.search.csp.CSP;
import aima.core.search.csp.Variable;
import aima.core.search.csp.Domain;
import aima.core.search.csp.examples.NotEqualConstraint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TreeCSP extends CSP<Variable, String> {
	
		public static final Variable WA = new Variable("WA");
		public static final Variable NT = new Variable("NT");
		public static final Variable Q = new Variable("Q");
		public static final Variable NSW = new Variable("NSW");
		public static final Variable V = new Variable("V");
		
		public static final List<Variable> variableList = Arrays.asList(WA, NT, Q, NSW, V);
		
		
		public static final String RED = "red";
		public static final String GREEN = "green";
		public static final String BLUE = "blue";
		
		private static final List<String> commonDomain = Arrays.asList(Values.RED, Values.GREEN, Values.BLUE);
		public static final Domain<String> domainWA = new Domain(new ArrayList<String>(commonDomain));
		public static final Domain<String> domainNT = new Domain(new ArrayList<String>(commonDomain));
		public static final Domain<String> domainQ = new Domain(new ArrayList<String>(commonDomain));
		public static final Domain<String> domainNSW = new Domain(new ArrayList<String>(commonDomain));
		public static final Domain<String> domainV = new Domain(new ArrayList<String>(commonDomain));
		
		public static final List<Domain> domainList = Arrays.asList(domainWA, domainNT, domainQ, domainNSW, domainV);
		
		
		public static final List<NotEqualConstraint> constraintList = Arrays.asList(
				new NotEqualConstraint<>(WA, NT),
				new NotEqualConstraint<>(NT, Q),
				new NotEqualConstraint<>(Q, NSW),
				new NotEqualConstraint<>(NSW, V)
		);
	
	public TreeCSP() {
		super(variableList);
		Iterator<Domain> domainIter = domainList.listIterator();
		variableList.forEach(
				variable -> super.setDomain(variable, domainIter.next())
		);
		constraintList.forEach(super::addConstraint);
	}
	
}