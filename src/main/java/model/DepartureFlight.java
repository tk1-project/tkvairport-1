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
			LocalDate originDate, FlightStatus status, LocalDateTime sTime, String terminal, String gates, LocalDateTime eTime,
			String cLocation, String cCounter, LocalDateTime cTimeMin, LocalDateTime cTimeMax) {
		super(iataCode, airline, model, flightNumber, departureAirport, arrivalAirport, 
				originDate, status, sTime, terminal, gates, eTime);
		
		this.cLocation = cLocation;
		this.cCounter = cCounter;
		this.cTimeMin = cTimeMin;
		this.cTimeMax = cTimeMax;
	}

	public String getcLocation() {
		return cLocation;
	}

	public void setcLocation(String cLocation) {
		this.cLocation = cLocation;
	}

	public String getcCounter() {
		return cCounter;
	}

	public void setcCounter(String cCounter) {
		this.cCounter = cCounter;
	}

	public LocalDateTime getcTimeMin() {
		return cTimeMin;
	}

	public void setcTimeMin(LocalDateTime cTimeMin) {
		this.cTimeMin = cTimeMin;
	}

	public LocalDateTime getcTimeMax() {
		return cTimeMax;
	}

	public void setcTimeMax(LocalDateTime cTimeMax) {
		this.cTimeMax = cTimeMax;
	}
	
	
	
}
