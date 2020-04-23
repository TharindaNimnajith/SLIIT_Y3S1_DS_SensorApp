package com.services;

import java.util.ArrayList;

import com.models.Sensor;

public interface ISensorService {

	public void addSensor(Sensor sensor);

	public void updateSensor(String sensorId, Sensor sensor);

	public void removeSensor(String sensorId);

	public Sensor getSensor(String sensorId);

	public ArrayList<Sensor> getSensorsList();
}
