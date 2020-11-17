package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DepartureFlight extends Flight {
	
	private String cLocation;
	private String cCounter;
	private LocalDateTime cTimeMin;
	private LocalDateTime cTimeMax;
	
	public DepartureFlight() {
		super();
	}
	
	public DepartureFlight(String iataCode, String airline, String model, String flightNumber, String departureAirport, String arrivalAirport, 
			LocalDate originDate, FlightStatus status, LocalDateTime sTime, String terminal, ArrayList<String> gates, 
			String cLocation, String cCounter, LocalDateTime cTimeMin, LocalDateTime cTimeMax) {
		super(iataCode, airline, model, flightNumber, departureAirport, arrivalAirport, 
				originDate, status, sTime, terminal, gates);
		
		this.cLocation = cLocation;
		this.cCounter = cCounter;
		this.cTimeMin = cTimeMin;
		this.cTimeMax = cTimeMax;
		
		
	}
	
	
}
