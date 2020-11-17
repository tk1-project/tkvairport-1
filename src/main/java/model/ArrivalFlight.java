package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ArrivalFlight extends Flight {

	private LocalDateTime eTime;
	
	public ArrivalFlight() {
		super();
	}
	
	public ArrivalFlight(String iataCode, String airline, String model, String flightNumber, String departureAirport, String arrivalAirport, 
			LocalDate originDate, FlightStatus status, LocalDateTime sTime, String terminal, ArrayList<String> gates, LocalDateTime eTime) {
		super(iataCode, airline, model, flightNumber, departureAirport, arrivalAirport, originDate, status, sTime, terminal, gates);
		
		this.eTime = eTime;
	}

	public LocalDateTime geteTime() {
		return eTime;
	}

	public void seteTime(LocalDateTime eTime) {
		this.eTime = eTime;
	}
	
	
}
