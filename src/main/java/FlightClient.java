import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import interfaces.IFlightClient;
import interfaces.IFlightServer;
import model.Flight;

public class FlightClient extends UnicastRemoteObject implements IFlightClient, Serializable {

	private static Logger logger = Logger.getLogger(FlightServer.class.getName());
	private static ClientUI ui;
	
	public IFlightServer stub;
	
	private static final String HOSTNAME = "localhost";
	private static final int PORT = 1099;
	
	private String clientName;
	
	// HastMap for storing flight objects
	private Map<String, Flight> flights;

	public FlightClient(String clientName) throws RemoteException {
		
		this.clientName = clientName;
		this.flights = new HashMap<String, Flight>();
		
	}

	@Override
	public void receiveListOfFlights(List<Flight> flights) {
		logger.log(Level.INFO, "List of flights received: " + flights.size());
		
		// check if received flight is already in the hashmap
		for(Flight f: flights) {
			String flightKey = f.getIataCode() + f.getFlightNumber();
			if(this.flights.containsKey(flightKey)) {
				this.receiveUpdatedFlight(f, false);
			} else {
				this.flights.put(flightKey, f);	
			}
		}
		// show newly received flights to the UI
		ui.updateUI(new ArrayList<>(this.flights.values()));
	}

	@Override
	public void receiveUpdatedFlight(Flight flight, boolean deleted) {
		String flightKey = flight.getIataCode() + flight.getFlightNumber();
		
		// check if the flight is deleted or edited
		if(deleted) {
			if(this.flights.containsKey(flightKey)) {
				this.flights.remove(flightKey);
			}
		} else {
			if(this.flights.containsKey(flightKey)) {
				this.flights.replace(flightKey, flight);
			} else {
				this.flights.put(flightKey, flight);
			}
		}
		logger.log(Level.INFO, "Flight updated: " + flight.toString());
		
		// show newly updated flight to the UI
		ui.updateUI(new ArrayList<>(this.flights.values()));
	}

	public void startup() {
		
		Registry registry;
		try {
			
			// find the rmi registry
			registry = LocateRegistry.getRegistry(HOSTNAME, PORT);
			
			// bind the stub to the registry
			stub = (IFlightServer) registry
					.lookup("Flight Server");
			
			// invocate remote method from FlightServer 'login'
			stub.login(this.clientName, this);
			
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public static void main(String[] args) {
		try {
			String clientname = UUID.randomUUID().toString();
			FlightClient client = new FlightClient(clientname);
			ui = new ClientUI(client);
					
			client.startup();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Server exception", ex);
		}
	}

}
