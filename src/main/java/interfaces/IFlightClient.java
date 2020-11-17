package interfaces;

import java.util.List;

import model.Flight;

public interface IFlightClient {
	
	public void receiveListOfFlights(List<Flight> flights);
	
	public void receiveUpdatedFlight(Flight flight, boolean deleted);

}
