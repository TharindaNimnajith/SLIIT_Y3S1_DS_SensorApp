package com.services;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.json.JSONException;

import com.models.Sensor;

public interface ISensorService {

	public void addSensor(Sensor sensor) throws  Exception;

	public void updateSensor(String sensorId, Sensor sensor) throws Exception;

	public void removeSensor(String sensorId);

	public Sensor getSensor(String sensorId) throws  Exception;

	public ArrayList<Sensor> getSensorsList() throws Exception;
}
