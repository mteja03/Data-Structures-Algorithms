package F28DA_CW2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class FlyingPlanner implements IFlyingPlannerPartB<Airport,Flight>, IFlyingPlannerPartC<Airport,Flight> {
	SimpleDirectedWeightedGraph<Airport, Flight> graph;
	HashSet<Airport> airportSet;
	HashSet<Flight> flightSet;
	FlyingPlanner()
	{
		graph=new SimpleDirectedWeightedGraph<Airport, Flight>(Flight.class);
		airportSet=new HashSet<Airport>();
		flightSet=new HashSet<Flight>();
	}
	@Override
	public boolean populate(FlightsReader fr) {
		HashSet<String []> airports=fr.getAirports();
		HashSet<String []> flights=fr.getFlights();
		Iterator<String []> airportItr=airports.iterator();
		Iterator<String []> flightItr=flights.iterator();
		int fCount=0,aCount=0;
		
		while(airportItr.hasNext())
		{
			String []airport=airportItr.next();
			Airport port=new Airport(airport[2],airport[0],airport[1]);
			airportSet.add(port);
			graph.addVertex(port);
			aCount++;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		}
		while(flightItr.hasNext())
		{
			String []flight=flightItr.next();
			String fCode=flight[0];
			String fACode=flight[1];
			String fDTime=flight[2];
			String tACode=flight[3];
			String tATime=flight[4];
			int cost=Integer.parseInt(flight[5]);
			Airport to=airport(tACode);
			Airport from=airport(fACode);
			Flight f=new Flight(fCode,fDTime,tATime,to,from,cost);
			flightSet.add(f);
			graph.addEdge(from,to, f);
			fCount++;
		}
		if(aCount==airports.size() && fCount==flights.size())
		return true;
		else 
		return false;
	}

	@Override
	public boolean populate(HashSet<String[]> airports, HashSet<String[]> flights) {
		Iterator<String []> airportItr=airports.iterator();
		Iterator<String []> flightItr=flights.iterator();
		int fCount=0,aCount=0;
		
		while(airportItr.hasNext())
		{
			String []airport=airportItr.next();
			Airport port=new Airport(airport[2],airport[0],airport[1]);
			airportSet.add(port);
			graph.addVertex(port);
			aCount++;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		}
		while(flightItr.hasNext())
		{
			String []flight=flightItr.next();
			String fCode=flight[0];
			String fACode=flight[1];
			String fDTime=flight[2];
			String tACode=flight[3];
			String tATime=flight[4];
			int cost=Integer.parseInt(flight[5]);
			Airport to=airport(tACode);
			Airport from=airport(fACode);
			Flight f=new Flight(fCode,fDTime,tATime,to,from,cost);
			flightSet.add(f);
			graph.addEdge(from,to, f);
			fCount++;
		}
		if(aCount==airports.size() && fCount==flights.size())
		return true;
		else 
		return false;

	}

	@Override
	public Airport airport(String code) {
		Iterator<Airport> aItr=airportSet.iterator();
		Airport a=null;
		while(aItr.hasNext())
		{
			Airport air=aItr.next();
			if(air.getCode().equals(code))
			{
				a=air;
			}
		}
		return a;
	}

	@Override
	public Flight flight(String code) {
		Iterator<Flight> aItr=flightSet.iterator();
		Flight a=null;
		while(aItr.hasNext())
		{
			Flight air=aItr.next();
			if(air.getFlightCode().equals(code))
			{
				a=air;
			}
		}
		return a;
	}

	@Override
	public Journey leastCost(String from, String to) throws FlyingPlannerException {
		
		Journey j=new Journey(airport(from),airport(to),graph);
		return j;
	}

	@Override
	public Journey leastHop(String from, String to) throws FlyingPlannerException {
		Journey j=new Journey(airport(from),airport(to),graph);
		return j;
	}

	@Override
	public Journey leastCost(String from, String to, List<String> excluding)
			throws FlyingPlannerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Journey leastHop(String from, String to, List<String> excluding)
			throws FlyingPlannerException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Set<Airport> directlyConnected(Airport airport) {
		// TODO Auto-generated method stub
		return airport.getDicrectlyConnected();
	}

	@Override
	public int setDirectlyConnected() {
		// TODO Auto-generated method stub
		return 0;
	}
 
	
	@Override
	public Set<Airport> getBetterConnectedInOrder(Airport airport) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String leastCostMeetUp(String at1, String at2) throws FlyingPlannerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String leastHopMeetUp(String at1, String at2) throws FlyingPlannerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String leastTimeMeetUp(String at1, String at2, String startTime) throws FlyingPlannerException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int setDirectlyConnectedOrder() {
		// TODO Auto-generated method stub
		return 0;
	}


}
