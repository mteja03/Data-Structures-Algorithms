package F28DA_CW2;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.jgrapht.graph.*;
import org.jgrapht.Graphs;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;;



public class FlyingPlannerMainPartA {


	public static SimpleDirectedWeightedGraph<String, Integer> createGraph() {

	    SimpleDirectedWeightedGraph<String, Integer> airport = new SimpleDirectedWeightedGraph<String, Integer>(Integer.class);

	    String v1 = new String("Edinburgh");
	    String v2 = new String("Heathrow");
	    String v3 = new String("Dubai");
	    String v4 = new String("Kuala Lumpur");
	    String v5 = new String("Sydney");
	    
	    airport.addVertex(v1);
	    airport.addVertex(v2);
	    airport.addVertex(v3);
	    airport.addVertex(v4);
	    airport.addVertex(v5);
	    
	    
	  
	    airport.addEdge(v1, v2, 80);
	   
	    airport.addEdge(v2, v1, 80);
	   
	    airport.addEdge(v2, v3, 130);
	   
	    airport.addEdge(v3, v2, 130);
	  
	    airport.addEdge(v3, v4, 170);
	  
	    airport.addEdge(v4, v3, 170);

	    airport.addEdge(v1, v3, 190);

	    airport.addEdge(v3, v1, 190);
	 
	    airport.addEdge(v2, v5, 570);

	    airport.addEdge(v5, v2, 570);
	   
	    airport.addEdge(v4, v5, 150);
	 
	    airport.addEdge(v5, v4, 150);
	    
	    return airport;
	}

	public static void itinerary(SimpleDirectedWeightedGraph<String, Integer> airport, String departure,
	        String destination) {
	    DijkstraShortestPath<String, Integer> p = new DijkstraShortestPath<String, Integer>(airport);
	    int cost=0;
	    GraphPath<String, Integer> g=p.getPath(departure,destination);
	    List<Integer> list=g.getEdgeList();
	    Iterator<Integer> itr=list.iterator();
	    while(itr.hasNext())
	    {
	    	Integer fi=(Integer)itr.next();
	    	cost+=fi.intValue();
	    }
	    System.out.println("Cost of shortest (i.e cheapest) path = £" + cost);
	}

	public static void main(String args[]) {

	    SimpleDirectedWeightedGraph<String, Integer> airport = createGraph();
	    System.out.println("The following airports are in use:" + airport.vertexSet());
	    @SuppressWarnings("resource")
	    Scanner s = new Scanner(System.in);
	    System.out.println("Please enter the starting airport:");
	    String departure = s.nextLine();
	    System.out.println("Please enter the destination aiport:");
	    String destination = s.nextLine();
	    itinerary(airport, departure, destination);


	}

}
