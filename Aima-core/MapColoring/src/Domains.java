import aima.core.search.csp.Domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Domains {
	private static final List<String> commonDomain = Arrays.asList(Values.RED, Values.GREEN, Values.BLUE);
	public static final Domain<String> WA = new Domain(new ArrayList<String>(commonDomain));
	public static final Domain<String> NT = new Domain(new ArrayList<String>(commonDomain));
	public static final Domain<String> Q = new Domain(new ArrayList<String>(commonDomain));
	public static final Domain<String> NSW = new Domain(new ArrayList<String>(commonDomain));
	public static final Domain<String> V = new Domain(new ArrayList<String>(commonDomain));
	public static final Domain<String> SA = new Domain(new ArrayList<String>(commonDomain));
	public static final Domain<String> T = new Domain(new ArrayList<String>(commonDomain));
	
	public static final List<Domain> list = Arrays.asList(WA, NT, Q, NSW, V, SA, T);
	
	private Domains() {}
}