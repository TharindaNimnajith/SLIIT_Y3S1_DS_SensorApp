package com.models;

public class Sensor {

	private String sensorId;
	private String sensorName;
	private int roomNo;
	private int floorNo;
	private int CO2Level;
	private int smokeLevel;
	private boolean isActive;

	public Sensor() {
		sensorId = "";
		sensorName = "";
		roomNo = 0;
		floorNo = 0;
		CO2Level = 0;
		smokeLevel = 0;
		setActive(true);
	}

	public Sensor(String sensorId, String sensorName, int roomNo, int floorNo, int CO2Level, int smokeLevel,
			boolean isActive) {
		this.sensorId = sensorId;
		this.sensorName = sensorName;
		this.roomNo = roomNo;
		this.floorNo = floorNo;
		this.CO2Level = CO2Level;
		this.smokeLevel = smokeLevel;
		this.setActive(isActive);
	}

	public Sensor(String sensorId, String sensorName, int roomNo, int floorNo, boolean isActive) {
		this.sensorId = sensorId;
		this.sensorName = sensorName;
		this.roomNo = roomNo;
		this.floorNo = floorNo;
		this.setActive(isActive);
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getSensorName() {
		return sensorName;
	}

	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public int getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(int floorNo) {
		this.floorNo = floorNo;
	}

	public int getCO2Level() {
		return CO2Level;
	}

	public void setCO2Level(int cO2Level) {
		CO2Level = cO2Level;
	}

	public int getSmokeLevel() {
		return smokeLevel;
	}

	public void setSmokeLevel(int smokeLevel) {
		this.smokeLevel = smokeLevel;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
