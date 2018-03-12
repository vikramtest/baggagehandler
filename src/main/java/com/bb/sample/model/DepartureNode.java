package com.bb.sample.model;

public class DepartureNode {

	String flightNumber;
	
	BaggageNode flightNode;
	
	String flightCode;
	
	String departureTime;
	
	public DepartureNode()
	{
		
	}
	
	public DepartureNode(String flightNum)
	{
		flightNumber = flightNum;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public BaggageNode getFlightNode() {
		return flightNode;
	}

	public void setFlightNode(BaggageNode flightNode) {
		this.flightNode = flightNode;
	}

	public String getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	
	
}
