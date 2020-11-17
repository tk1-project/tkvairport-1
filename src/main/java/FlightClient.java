import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import interfaces.IFlightClient;
import interfaces.IFlightServer;
import model.Flight;

public class FlightClient implements IFlightClient, Serializable {

	private static Logger logger = Logger.getLogger(FlightServer.class.getName());

	// ui
	private transient ClientUI ui = new ClientUI();
	
	private static final String HOSTNAME = "localhost";
	private static final int PORT = 1099;
	
	private String clientName;
	
	private Map<String, Flight> flights;

	// global state
	public FlightClient(String clientName) {
		this.clientName = clientName;

		this.flights = new HashMap<String, Flight>();
	}

	@Override
	public void receiveListOfFlights(List<Flight> flights) {
		
		for(Flight f: flights) {
			String flightKey = f.getAirline() + f.getFlightNumber();
			if(this.flights.containsKey(flightKey)) {
				this.receiveUpdatedFlight(f, false);
			} else {
				this.flights.put(flightKey, f);	
			}
		}
		logger.log(Level.INFO, "List of flights received: " + flights.size());
	}

	@Override
	public void receiveUpdatedFlight(Flight flight, boolean deleted) {
		String flightKey = flight.getAirline() + flight.getFlightNumber();
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
	}

	public void startup() {
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry();
			
			IFlightServer stub = (IFlightServer) registry
					.lookup("Flight Server");
			
			stub.login(this.clientName, this);
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ui.launch();
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
			
			client.startup();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Server exception", ex);
		}
	
	}

}
