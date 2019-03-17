import aima.core.search.csp.CSP;
import aima.core.search.csp.Variable;
import aima.core.search.csp.Domain;
import java.util.Iterator;

public class MapCSP extends CSP<Variable, String> {
	
	public MapCSP() {
		super(Variables.list);
		Iterator<Domain> domainIter = Domains.list.listIterator();
		Variables.list.forEach(
				variable -> super.setDomain(variable, domainIter.next())
		);
		Constraints.list.forEach(super::addConstraint);
	}
	
}