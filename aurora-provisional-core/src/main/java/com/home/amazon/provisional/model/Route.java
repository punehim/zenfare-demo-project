package com.home.amazon.provisional.model;

import com.google.gson.annotations.SerializedName;

public class Route {

	@SerializedName("routeId")
	private Long routeId;

	@SerializedName("routeNumber")
	private String routeNumber;

	public Long getRouteId() {
		return routeId;
	}

	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}

	public String getRouteNumber() {
		return routeNumber;
	}

	public void setRouteNumber(String routeNumber) {
		this.routeNumber = routeNumber;
	}

	public Route(Long routeId) {
		super();
		this.routeId = routeId;
	}

	
	
	}
