import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import java.util.logging.Level;
import java.util.logging.Logger;

import interfaces.IFlightClient;
import interfaces.IFlightServer;
import model.Flight;

public class FlightServer implements IFlightServer {

	private static Logger logger = Logger.getLogger(FlightServer.class.getName());


	protected FlightServer() {
		super();

		// initialize with some flights
		Flight flight1 = new Flight();
		Flight flight2 = new Flight();
		Flight flight3 = new Flight();
		
		// ...
	}

	@Override
	public void login(String clientName, IFlightClient client) {
		logger.log(Level.INFO, "New client logged in: " + clientName);
	}

	@Override
	public void logout(String clientName) {
		logger.log(Level.INFO, "Client logged out: " + clientName);
	}

	@Override
	public void updateFlight(String clientName, Flight flight) {
		logger.log(Level.INFO, "Update flight: " + flight.toString());
	}

	@Override
	public void deleteFlight(String clientName, Flight flight) {
		logger.log(Level.INFO, "Delete flight: " + flight.toString());
	}

	private void informAllClients(Flight flight, boolean deleted) {
		
	}

	public static void main(String[] args) {
		
		try {
			// generate local registry

			// generate game server

			IFlightServer flightserver = new FlightServer();
			
			// export the remote object to the stub, '0' means we don't care which
			// port that exportObject uses
			IFlightServer stub = (IFlightServer) UnicastRemoteObject
					.exportObject((IFlightServer) flightserver, 0);
			
			/* create a local registry
			 * the registry can be bound by server and discovered by client 
			 * by default, an RMI registry runs on port 1099
			 */
			Registry registry = LocateRegistry.createRegistry(1099);
			/* to create a registry as a separate stand-alone service
			 * passing the hostname and port number as parameters
			 */
			// Registry registry = LocateRegistry.getRegistry();
			
			// bind the stub to the registry
			registry.bind("Flight Server", stub);
			
			logger.info("Server is ready");
			
			
			//flightserver.logout("xxx827282");
			
			//Flight flight1 = new Flight();
			//flightserver.updateFlight("xxx827282", flight1);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Server exception", ex);
		}
	}

}
