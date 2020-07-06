package F28DA_CW2;

import java.util.HashSet;
import java.util.Set;

public class Airport implements IAirportPartB, IAirportPartC {
	private String airportName,airportCode,airportCity;
	private Set<Airport> dicrectlyConnected;
	private int order;
	
	public Airport(String airportName,String airportCode,String airportCity)
	{
		this.airportName=airportName;
		this.airportCode=airportCode;
		this.airportCity=airportCity;
		dicrectlyConnected =new HashSet<Airport>();
	}
	
	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return airportCode;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return airportName;
	}

	@Override
	public void setDicrectlyConnected(Set<Airport> dicrectlyConnected) {
		// TODO Auto-generated method stub
		this.dicrectlyConnected.addAll(dicrectlyConnected);

	}

	@Override
	public Set<Airport> getDicrectlyConnected() {
		// TODO Auto-generated method stub
		return dicrectlyConnected;
	}


	@Override
	public void setDicrectlyConnectedOrder(int order) {
		// TODO Auto-generated method stub
		this.order=order;
		

	}

	@Override
	public int getDirectlyConnectedOrder() {
		// TODO Auto-generated method stub
		return order;
	}

}
