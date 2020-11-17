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

public class FlightServer implements IFlightServer {

	private static Logger logger = Logger.getLogger(FlightServer.class.getName());
	
	private Map<String, IFlightClient> clients;
	private Map<String, Flight> flights;
	private static Registry r;


	protected FlightServer() {
		super();

		flights = new HashMap<>();
		
//		String iataCode, String airline, String model, String flightNumber, String departureAirport, String arrivalAirport, 
//		LocalDate originDate, FlightStatus status, LocalDateTime sTime, String terminal, ArrayList<String> gates, 
//		String cLocation, String cCounter, LocalDateTime cTimeMin, LocalDateTime cTimeMax
		
//		String iataCode, String airline, String model, String flightNumber, String departureAirport, String arrivalAirport, 
//		LocalDate originDate, FlightStatus status, LocalDateTime sTime, String terminal, ArrayList<String> gates, LocalDateTime eTime
		
		
		ArrayList<String> arrivalGates = new ArrayList();
		arrivalGates.add("B54");
		arrivalGates.add("B55");

		ArrayList<String> departureGates = new ArrayList();
		departureGates.add("A11");
		departureGates.add("B13");
		
		
		Flight[] initialFlights = new Flight[] {
				new DepartureFlight("LH", "Lufthansa", "A380", "591", "NBO", "FRA", 
						LocalDate.of(2020, 11, 11), FlightStatus.B, LocalDateTime.of(2020, 11, 11, 12, 0), "terminal", departureGates, 
						"cLocation", "cCounter", LocalDateTime.of(2020, 11, 11, 13, 0), LocalDateTime.of(2020, 11, 11, 13, 0)),
				new ArrivalFlight("LH", "Lufthansa", "A380", "999", "BER", "SFO", 
						LocalDate.of(2019,  11,  11), FlightStatus.D, LocalDateTime.of(2020, 12, 12, 12, 0), "1", 
						arrivalGates, LocalDateTime.of(2020,  12, 12, 13, 0))
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
	}

	@Override
	public void logout(String clientName) throws RemoteException {
		this.clients.remove(clientName);
		logger.log(Level.INFO, "Client logged out: " + clientName);
	}

	@Override
	public void updateFlight(String clientName, Flight flight) throws RemoteException {
		String flightKey = flight.getAirline() + flight.getFlightNumber();
		if(this.flights.containsKey(flightKey)) {
			this.flights.replace(flightKey, flight);
		}
		this.informAllClients(flight, false);
		logger.log(Level.INFO, "Update flight: " + flight.toString());
	}

	@Override
	public void deleteFlight(String clientName, Flight flight) throws RemoteException {
		String flightKey = flight.getAirline() + flight.getFlightNumber();
		if(this.flights.containsKey(flightKey)) {
			this.flights.remove(flightKey);
		}
		this.informAllClients(flight, true);
		logger.log(Level.INFO, "Delete flight: " + flight.toString());
	}

	private void informAllClients(Flight flight, boolean deleted) {
		for(IFlightClient client: this.clients.values()){  
			client.receiveUpdatedFlight(flight, deleted);
		}
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
