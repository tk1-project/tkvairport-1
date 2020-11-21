package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Flight implements Serializable {

	public Flight() {
		
	}
	
	public Flight(String iataCode, String airline, String model, String flightNumber, String departureAirport, 
			String arrivalAirport, LocalDate originDate, FlightStatus status, LocalDateTime sTime, String terminal, String gates, LocalDateTime eTime) {
		this.iataCode = iataCode;
		this.airline = airline;
		this.model = model;
		this.flightNumber = flightNumber;
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.originDate = originDate;
		this.status = status;
		
		this.sTime = sTime;
		this.terminal = terminal;
		this.gates = gates;
		this.eTime = eTime;
	}
	
	private String iataCode;
	private String airline;
	private String model;
	private String flightNumber;
	private String departureAirport;
	private String arrivalAirport;
	private LocalDate originDate;
	private FlightStatus status;
	
	private LocalDateTime sTime;
	private String terminal;
	private String gates;
	private LocalDateTime eTime;
	
	public String getIataCode() {
		return iataCode;
	}

	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	public String getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public LocalDate getOriginDate() {
		return originDate;
	}

	public void setOriginDate(LocalDate originDate) {
		this.originDate = originDate;
	}

	public FlightStatus getStatus() {
		return status;
	}

	public void setStatus(FlightStatus status) {
		this.status = status;
	}
	
	public LocalDateTime getsTime() {
		return sTime;
	}

	public void setsTime(LocalDateTime sTime) {
		this.sTime = sTime;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getGates() {
		return gates;
	}

	public void setGates(String gates) {
		this.gates = gates;
	}
	
	public LocalDateTime geteTime() {
		return eTime;
	}
	
	public void seteTime(LocalDateTime eTime) {
		this.eTime = eTime;
	}
	
}
