package com.home.amazon.provisional.model;

import com.google.gson.annotations.SerializedName;

public class Transcation {

	@SerializedName("id")
	private final int id;

	@SerializedName("garageId")
	private Long garageId;

	@SerializedName("transactionId")
	private Long transactionId;

	@SerializedName("transactionTimestamp")
	private String transactionTimeStamp;

	@SerializedName("garageName")
	private String garageName;

	@SerializedName("transitDay")
	private String transitDay;

	@SerializedName("transactionTypeId")
	private Long transactionTypeId;

	@SerializedName("transactionTypeDescription")
	private String transactionTypeDescription;

	@SerializedName("driverNumber")
	private Long driverNumber;

	@SerializedName("busNumber")
	private Long busNumber;

	@SerializedName("routeNumber")
	private Long routeNumber;

	@SerializedName("runNumber")
	private Long runNumber;

	@SerializedName("tripNumber")
	private Long tripNumber;

	@SerializedName("productName")
	private String productName;

	@SerializedName("passCategory")
	private String passCategory;

	@SerializedName("ridershipFlag")
	private boolean isRidership;

	public Transcation(int id) {
		this.id = id;
	}

	public Long getGarageId() {
		return garageId;
	}

	public void setGarageId(Long garageId) {
		this.garageId = garageId;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionTimeStamp() {
		return transactionTimeStamp;
	}

	public void setTransactionTimeStamp(String transactionTimeStamp) {
		this.transactionTimeStamp = transactionTimeStamp;
	}

	public String getGarageName() {
		return garageName;
	}

	public void setGarageName(String garageName) {
		this.garageName = garageName;
	}

	public String getTransitDay() {
		return transitDay;
	}

	public void setTransitDay(String transitDay) {
		this.transitDay = transitDay;
	}

	public Long getTransactionTypeId() {
		return transactionTypeId;
	}

	public void setTransactionTypeId(Long transactionTypeId) {
		this.transactionTypeId = transactionTypeId;
	}

	public String getTransactionTypeDescription() {
		return transactionTypeDescription;
	}

	public void setTransactionTypeDescription(String transactionTypeDescription) {
		this.transactionTypeDescription = transactionTypeDescription;
	}

	public Long getDriverNumber() {
		return driverNumber;
	}

	public void setDriverNumber(Long driverNumber) {
		this.driverNumber = driverNumber;
	}

	public Long getBusNumber() {
		return busNumber;
	}

	public void setBusNumber(Long busNumber) {
		this.busNumber = busNumber;
	}

	public Long getRouteNumber() {
		return routeNumber;
	}

	public void setRouteNumber(Long routeNumber) {
		this.routeNumber = routeNumber;
	}

	public Long getRunNumber() {
		return runNumber;
	}

	public void setRunNumber(Long runNumber) {
		this.runNumber = runNumber;
	}

	public Long getTripNumber() {
		return tripNumber;
	}

	public void setTripNumber(Long tripNumber) {
		this.tripNumber = tripNumber;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPassCategory() {
		return passCategory;
	}

	public void setPassCategory(String passCategory) {
		this.passCategory = passCategory;
	}

	public boolean isRidership() {
		return isRidership;
	}

	public void setRidership(boolean isRidership) {
		this.isRidership = isRidership;
	}

	public int getId() {
		return id;
	}

	}
