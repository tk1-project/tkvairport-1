package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.Flight;

public interface IFlightClient extends Remote {
	
	public void receiveListOfFlights(List<Flight> flights) throws RemoteException;
	
	public void receiveUpdatedFlight(Flight flight, boolean deleted) throws RemoteException;

}
