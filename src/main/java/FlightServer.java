import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import interfaces.IFlightClient;
import interfaces.IFlightServer;
import model.ArrivalFlight;
import model.DepartureFlight;
import model.Flight;
import model.FlightStatus;

public class FlightServer extends UnicastRemoteObject implements IFlightServer, Serializable {

	private static Logger logger = Logger.getLogger(FlightServer.class.getName());
	
	// HashMap for storing client objects
	private Map<String, IFlightClient> clients;
	
	// HastMap for storing flight objects
	private Map<String, Flight> flights;
	
	// RMI registry
	private static Registry r;
	
	private static final int PORT = 1099;


	protected FlightServer() throws RemoteException {
		super();

		// Initialize Flights with example data
		flights = new HashMap<>();		
		
		Flight[] initialFlights = new Flight[] {
				new DepartureFlight("LH", "Lufthansa", "A380", "591", "TK", "FRA", 
						LocalDate.of(2020, 11, 11), FlightStatus.B, LocalDateTime.of(2020, 11, 11, 15, 20), "1", "A03, A04", 
						LocalDateTime.of(2020,  11, 11, 15, 25), "A", "421-425", LocalDateTime.of(2020, 11, 11, 13, 0), 
						LocalDateTime.of(2020, 11, 11, 13, 50)),
				new ArrivalFlight("LH", "Lufthansa", "A380", "999", "BER", "TK", 
						LocalDate.of(2020,  11,  20), FlightStatus.D, LocalDateTime.of(2020, 11, 20, 9, 50), "2", 
						"B55, B76", LocalDateTime.of(2020,  11, 20, 9, 47)),
				new DepartureFlight("KLM", "KLM", "B738", "1407", "TK", "BER", 
						LocalDate.of(2020, 11, 20), FlightStatus.B, LocalDateTime.of(2020, 11, 20, 11, 50), "1", "A12, A13", 
						LocalDateTime.of(2020,  11, 20, 11, 45), "C", "1-10", LocalDateTime.of(2020, 11, 20, 10, 10), 
						LocalDateTime.of(2020, 11, 20, 10, 50)),
				new ArrivalFlight("LH", "Lufthansa", "A380", "4160", "AMS", "TK", 
						LocalDate.of(2020,  11,  21), FlightStatus.D, LocalDateTime.of(2020, 11, 21, 12, 0), "1", 
						"C1, C2, C3", LocalDateTime.of(2020,  11, 21, 12, 5))
		};
		
		// Saving flights as Key-Value Pairs
		for(Flight f: initialFlights) {
			this.flights.put(f.getIataCode() + f.getFlightNumber(), f);
		}
		
		logger.log(Level.INFO, "Added <" +flights.size()+ "> new Flights...");
				
		// Initializing client map
		clients = new HashMap<>();
		
	}

	@Override
	public void login(String clientName, IFlightClient client) throws RemoteException {
		// Check if clientName is already taken
		if(this.clients.containsKey(clientName)) {
			// generate new unique client name
			clientName += UUID.randomUUID().toString();
			
			// Update name in client object
			FlightClient handle = (FlightClient) client;
			handle.setClientName(clientName);
		}
		logger.log(Level.INFO, "New client logged in: " + clientName);
		
		// Saving Client as Key-Value Pair
		this.clients.put(clientName, client);
	
		// Sending Flights to client
		client.receiveListOfFlights(new ArrayList<>(this.flights.values()));
		logger.log(Level.INFO, "Sending flights: " + this.flights.values());
	}

	@Override
	public void logout(String clientName) throws RemoteException {
		// Remove client
		this.clients.remove(clientName);
		logger.log(Level.INFO, "Client logged out: " + clientName);
	}

	@Override
	public void updateFlight(String clientName, Flight flight) throws RemoteException {
		String flightKey = flight.getIataCode() + flight.getFlightNumber();
		
		// Check if flight already exists
		if(this.flights.containsKey(flightKey)) {
			// Update flight data
			this.flights.replace(flightKey, flight);
		} else {
			// Add new flight
			this.flights.put(flightKey, flight);
		}
		
		// Send flight information updates to all clients
		this.informAllClients(flight, false);
		logger.log(Level.INFO, "Update flight: " + flight.toString());
	}

	@Override
	public void deleteFlight(String clientName, Flight flight) throws RemoteException {
		String flightKey = flight.getIataCode() + flight.getFlightNumber();
		// Check if flight exists
		if(this.flights.containsKey(flightKey)) {
			// Remove flight
			this.flights.remove(flightKey);
		}
		
		// Send flight information updates to all clients
		this.informAllClients(flight, true);
		logger.log(Level.INFO, "Delete flight: " + flight.toString());
	}

	private void informAllClients(Flight flight, boolean deleted) throws RemoteException {
		// Send flight information to every client
		for(IFlightClient client: this.clients.values()){  
			client.receiveUpdatedFlight(flight, deleted);
		}
	}

	public static void main(String[] args) {
		
		try {
			FlightServer flightserver = new FlightServer();
			
			// export the remote object to the stub, '0' means we don't care which
			// port that exportObject uses
			// IFlightServer stub = (IFlightServer) UnicastRemoteObject
			// 		.exportObject((IFlightServer) flightserver, 0);
			
			// create a local registry
			Registry registry = LocateRegistry.createRegistry(PORT);

			// bind the stub to the registry
			registry.rebind("Flight Server", flightserver);
			
			logger.info("Server is ready");
			
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Server exception", ex);
		}
	}

}
