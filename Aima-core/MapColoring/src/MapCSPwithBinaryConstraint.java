import aima.core.search.csp.CSP;
import aima.core.search.csp.Domain;
import aima.core.search.csp.Variable;

import java.util.Iterator;

public class MapCSPwithBinaryConstraint extends CSP<Variable, String> {
	
	public MapCSPwithBinaryConstraint() {
		super(Variables.list);
		Iterator<Domain> domainIter = Domains.list.listIterator();
		Variables.list.forEach(
				variable -> super.setDomain(variable, domainIter.next())
		);
		BinaryConstraint.list.forEach(super::addConstraint);
	}
	
}