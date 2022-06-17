package com.home.amazon.provisional.model;

import com.google.gson.annotations.SerializedName;

public class Bus {

	@SerializedName("busId")
	private Long busId;

	@SerializedName("busNumber")
	private String busNumber;

	
	public Bus(Long busId) {
		super();
		this.busId = busId;
	}

	public Long getBusId() {
		return busId;
	}


	public void setBusId(Long busId) {
		this.busId = busId;
	}


	public String getBusNumber() {
		return busNumber;
	}


	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}

	}
