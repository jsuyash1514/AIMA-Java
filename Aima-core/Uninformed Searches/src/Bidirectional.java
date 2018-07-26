import aima.core.environment.map.*;
import aima.core.search.framework.Node;
import aima.core.search.framework.QueueFactory;
import aima.core.search.framework.problem.Problem;
import aima.core.search.framework.qsearch.BidirectionalSearch;

import java.util.Queue;


public class Bidirectional {
	
	static QueueFactory queueFactory = new QueueFactory();
	static Queue<Node> frontier ;
	
	public static void main(String[] args){
		
		Map romaniaMap = new SimplifiedRoadMapOfPartOfRomania();
		Problem bidirectionalMapProblem = new BidirectionalMapProblem(romaniaMap, SimplifiedRoadMapOfPartOfRomania.ARAD,SimplifiedRoadMapOfPartOfRomania.HIRSOVA );
		
		frontier = queueFactory.createFifoQueue();
		BidirectionalSearch bidirectionalSearch = new BidirectionalSearch();
		System.out.println(bidirectionalSearch.findNode(bidirectionalMapProblem,frontier));
	}
}
