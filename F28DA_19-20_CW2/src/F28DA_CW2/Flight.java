package F28DA_CW2;

public class Flight implements IFlight {

	private String flightCode,fromGMTime,toGMTime;
	private Airport to,from;
	int cost;
	
	public Flight(String flightCode,String fromGMTime, String toGMTime, Airport to, Airport from, int cost)
	{
		this.flightCode=flightCode;
		this.fromGMTime=fromGMTime;
		this.toGMTime=toGMTime;
		this.to=to;
		this.from=from;
		this.cost=cost;
	}

	public String getFlightCode() {
		return flightCode;
	}

	@Override
	public Airport getTo() {
		// TODO Auto-generated method stub
		return to;
	}

	@Override
	public Airport getFrom() {
		// TODO Auto-generated method stub
		return from;
	}

	@Override
	public String getFromGMTime() {
		// TODO Auto-generated method stub
		return fromGMTime;
	}

	@Override
	public String getToGMTime() {
		// TODO Auto-generated method stub
		return toGMTime;
	}

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return cost;
	}


}
