import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import interfaces.IFlightClient;
import interfaces.IFlightServer;
import model.Flight;

public class FlightClient implements IFlightClient {

	private static Logger logger = Logger.getLogger(FlightServer.class.getName());

	// ui
	private ClientUI ui = new ClientUI();

	// global state

	public FlightClient(String clientName) {
	}

	@Override
	public void receiveListOfFlights(List<Flight> flights) {
		logger.log(Level.INFO, "List of flights received: " + flights.size());
	}

	@Override
	public void receiveUpdatedFlight(Flight flight, boolean deleted) {
		logger.log(Level.INFO, "Flight updated: " + flight.toString());
	}

	public void startup() {
		ui.launch();
	}

	public static void main(String[] args) {
		try {
			String clientname = UUID.randomUUID().toString();
			FlightClient client = new FlightClient(clientname);
			Registry registry = LocateRegistry.getRegistry();
			IFlightServer stub = (IFlightServer) registry
					.lookup("Flight Server");
			//test

			
			client.startup();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Server exception", ex);
		}
	
	}

}
