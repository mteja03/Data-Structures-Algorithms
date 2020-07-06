package F28DA_CW2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class Journey implements IJourneyPartB<Airport, Flight>, IJourneyPartC<Airport, Flight> {

	Airport source,dest;
	List<Flight> f;
	SimpleDirectedWeightedGraph<Airport, Flight> graph;
	DijkstraShortestPath<Airport, Flight> p;
	 GraphPath<Airport, Flight> g;
	public Journey(Airport source,Airport dest,SimpleDirectedWeightedGraph<Airport,Flight> graph)
	{
		
		this.source=source;
		this.dest=dest;
		this.graph=graph;
		p = new DijkstraShortestPath<Airport, Flight>(graph);
	    g=p.getPath(source,dest);
	    f=g.getEdgeList();
	}
	@Override
	public List<String> getStops() {
		List<String> acodes=new ArrayList<String>();
		List<Airport> port=g.getVertexList();
		Iterator<Airport> aitr=port.iterator();
		while(aitr.hasNext())
		{
			Airport ap=aitr.next();
			acodes.add(ap.getCode());
		}
		
	   return acodes;
	}

	@Override
	public List<String> getFlights() {
		Iterator<Flight> fitr=f.iterator();
		List<String> fcode=new ArrayList<String>();
		while(fitr.hasNext())
		{
			Flight ft=fitr.next();
			fcode.add(ft.getFlightCode());
		}
		return fcode;
	}

	@Override
	public int totalHop() {
		// TODO Auto-generated method stub
		
		return f.size();
	}

	@Override
	public int totalCost() {
		Iterator<Flight> fitr=f.iterator();
		int cost=0;
		while(fitr.hasNext())
		{
			Flight ft=fitr.next();
			cost+=ft.getCost();
		}
		return cost;
	}

	@Override
	public int airTime() {
		Iterator<Flight> fitr=f.iterator();
		int hr=0;
		int min=0;
		while(fitr.hasNext())
		{
			Flight ft=fitr.next();
			hr+=Integer.parseInt(ft.getToGMTime())/100-Integer.parseInt(ft.getFromGMTime())/100;
			min+=Integer.parseInt(ft.getToGMTime())%100-Integer.parseInt(ft.getFromGMTime())%100;
		}
		if(min<0)
		{
			hr--;
			min=min+60;
		}
		if(min>=60)
		{
			
		hr+=(min/60);
		min%=60;
		}
		
		return Integer.parseInt(Integer.toString(hr)+Integer.toString(min));
	}

	@Override
	public int connectingTime() {
		Flight start=f.get(0);
		Flight end=f.get(f.size()-1);
		
		int hr=Integer.parseInt(end.getToGMTime())/100-Integer.parseInt(start.getFromGMTime())/100-airTime()/100;
		int min=Integer.parseInt(end.getToGMTime())%100-Integer.parseInt(start.getFromGMTime())%100-airTime()%100;
		if(min<0)
		{
			hr--;
			min=min+60;
		}
		if(min>=60)
		{
			
		hr+=(min/60);
		min%=60;
		}
		
		return Integer.parseInt(Integer.toString(hr)+Integer.toString(min));
	}

	@Override
	public int totalTime() {
		// TODO Auto-generated method stub
		return airTime()+connectingTime();
	}

}
