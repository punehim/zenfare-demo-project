package com.home.amazon.provisional.model;

import com.google.gson.annotations.SerializedName;

public class Garage {

	@SerializedName("garageId")
	private Long garageId;

	@SerializedName("garageName")
	private String garageName;

	public Long getGarageId() {
		return garageId;
	}

	public void setGarageId(Long garageId) {
		this.garageId = garageId;
	}

	public String getGarageName() {
		return garageName;
	}

	public void setGarageName(String garageName) {
		this.garageName = garageName;
	}

	public Garage(Long garageId) {
		super();
		this.garageId = garageId;
	}

	}
