package com.services;

import java.io.IOException;
import java.util.ArrayList;

import com.models.Sensor;

public interface ISensorService {

	public void addSensor(Sensor sensor) throws IOException;

	public void updateSensor(String sensorId, Sensor sensor) throws IOException;

	public void removeSensor(String sensorId) throws IOException;

	public Sensor getSensor(String sensorId) throws IOException;

	public ArrayList<Sensor> getSensorsList() throws IOException;
}
