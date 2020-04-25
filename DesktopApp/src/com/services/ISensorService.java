package com.services;

import java.util.ArrayList;

import com.models.Sensor;

public interface ISensorService {

	public void addSensor(Sensor sensor) throws Exception;

	public void updateSensor(String sensorId, Sensor sensor) throws Exception;

	public void removeSensor(String sensorId) throws Exception;

	public Sensor getSensor(String sensorId) throws Exception;

	public ArrayList<Sensor> getSensorsList() throws Exception;
}
