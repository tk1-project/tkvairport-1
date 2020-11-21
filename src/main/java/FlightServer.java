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
	
	private Map<String, IFlightClient> clients;
	private Map<String, Flight> flights;
	private static Registry r;
	
	private static final int PORT = 1099;


	protected FlightServer() throws RemoteException {
		super();

		flights = new HashMap<>();
		
//		String iataCode, String airline, String model, String flightNumber, String departureAirport, String arrivalAirport, 
//		LocalDate originDate, FlightStatus status, LocalDateTime sTime, String terminal, ArrayList<String> gates, 
//		String cLocation, String cCounter, LocalDateTime cTimeMin, LocalDateTime cTimeMax
		
//		String iataCode, String airline, String model, String flightNumber, String departureAirport, String arrivalAirport, 
//		LocalDate originDate, FlightStatus status, LocalDateTime sTime, String terminal, ArrayList<String> gates, LocalDateTime eTime		
		
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
		
		for(Flight f: initialFlights) {
			this.flights.put(f.getIataCode() + f.getFlightNumber(), f);
		}
		
		System.out.println("Added <" +flights.size()+ "> new Flights...");
				
		// ...
		clients = new HashMap<>();
		
		// ...
	}

	@Override
	public void login(String clientName, IFlightClient client) throws RemoteException {
		
		if(this.clients.containsKey(clientName)) {
			clientName += UUID.randomUUID().toString();
			FlightClient handle = (FlightClient) client;
			handle.setClientName(clientName);
		}
		
		this.clients.put(clientName, client);
	
		client.receiveListOfFlights(new ArrayList<>(this.flights.values()));
		logger.log(Level.INFO, "New client logged in: " + clientName);
		logger.log(Level.INFO, "Sending flights: " + this.flights.values());
	}

	@Override
	public void logout(String clientName) throws RemoteException {
		this.clients.remove(clientName);
		logger.log(Level.INFO, "Client logged out: " + clientName);
	}

	@Override
	public void updateFlight(String clientName, Flight flight) throws RemoteException {
		String flightKey = flight.getIataCode() + flight.getFlightNumber();
		if(this.flights.containsKey(flightKey)) {
			this.flights.replace(flightKey, flight);
		} else {
			this.flights.put(flightKey, flight);
		}
		this.informAllClients(flight, false);
		logger.log(Level.INFO, "Update flight: " + flight.toString());
	}

	@Override
	public void deleteFlight(String clientName, Flight flight) throws RemoteException {
		String flightKey = flight.getIataCode() + flight.getFlightNumber();
		if(this.flights.containsKey(flightKey)) {
			this.flights.remove(flightKey);
		}
		this.informAllClients(flight, true);
		logger.log(Level.INFO, "Delete flight: " + flight.toString());
	}

	private void informAllClients(Flight flight, boolean deleted) throws RemoteException {
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

			// Registry registry = LocateRegistry.getRegistry();
			
			// bind the stub to the registry
			registry.rebind("Flight Server", flightserver);
			
			logger.info("Server is ready");
			
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Server exception", ex);
		}
	}

}
